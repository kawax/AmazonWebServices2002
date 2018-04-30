Attribute VB_Name = "AmazonMacros"
'
' Amazon Query Example
'
'

Sub AmazonQuery()
  Dim SelectedText As String
  Dim MSXML As Object
  Dim XMLURL As String
  Dim Loaded As Boolean
    
  ' Get text selection
  SelectedText = Selection
  
  ' Make sure that some text is selected
  If ((Len(SelectedText) = 0) Or (SelectedText < " ")) Then
    MsgBox "Please select some text and try again."
    Exit Sub
  End If
  
  ' Set Associate ID and Developer Token
  AssociatesID = "webservices-20"
  DeveloperToken = "D2ED5GR7A6RZ7Y"
  
  ' Create MSXML Object
  Set MSXML = CreateObject("MSXML.DOMDocument")
  
  ' Set MSXML Options
  MSXML.Async = False
  MSXML.preserveWhiteSpace = False
  MSXML.validateOnParse = True
  MSXML.resolveExternals = False
 
  ' Form an XML URL
  XMLURL = "http://xml.amazon.com/onca/xml2" + _
           "?t=" + AssociateID + _
           "&dev-t=" + DeveloperToken + _
           "&page=1" + _
           "&f=xml" + _
           "&mode=books" + _
           "&type=lite" + _
           "&KeywordSearch=" + SelectedText
  
  ' Issue the request and wait for it to be honored
  Loaded = MSXML.Load(XMLURL)

  If (Loaded) Then
    ProcessResults MSXML
  Else
    MsgBox "The service is not available."
  End If
    
End Sub

'
' ProcessResults -
'
'   Iterate through the given XML object and display each item.
'   Allow the user to choose some items and insert them into
'   the document.
'
Sub ProcessResults(MSXML As Object)
  Dim XML_Root As Object
  Dim XML_Child As Object
  Dim XML_Title As Object
  Dim XML_ListPrice As Object
  Dim XML_OurPrice As Object
  Dim XML_Mfr As Object
  Dim XML_Authors As Object
  Dim XML_Author As Object
  Dim XML_ReleaseDate As Object
  Dim TotalResults As Long
  Dim Title As String
  Dim ListPrice As String
  Dim OurPrice As String
  Dim Mfr As String
  Dim URL As String
  Dim Author As String
  Dim ReleaseDate As String
  Dim Updates As String
  Dim Footnote As String
  
  ' Get root of document
  Set XML_Root = MSXML.documentElement
  
  ' Reset list of items in document
  AmazonDetailsForm.ClearItems
  
  ' Process each top-level element
  For i = 0 To XML_Root.childNodes.Length - 1
    Set XML_Child = XML_Root.childNodes.Item(i)
    
    Select Case XML_Child.nodeName
      Case "TotalResults"
      TotalResults = CLng(XML_Child.Text)
      
      Case "Details"
        Set XML_Title = Nothing
        Set XML_ListPrice = Nothing
        Set XML_OurPrice = Nothing
        Set XML_Mfr = Nothing
        Set XML_Author = Nothing
        Set XML_ReleaseDate = Nothing
        Title = ""
        ListPrice = ""
        OurPrice = ""
        Mfr = ""
        Author = ""
        ReleaseDate = ""
        
        ' Locate the child nodes
        Set XML_Title = XML_Child.selectSingleNode("ProductName")
        Set XML_ListPrice = XML_Child.selectSingleNode("ListPrice")
        Set XML_OurPrice = XML_Child.selectSingleNode("OurPrice")
        Set XML_Mfr = XML_Child.selectSingleNode("Manufacturer")
        Set XML_Authors = XML_Child.selectSingleNode("Authors")
        Set XML_ReleaseDate = XML_Child.selectSingleNode("ReleaseDate")
        
        ' Extract the text from the child nodes
        If (Not (XML_Title Is Nothing)) Then Title = XML_Title.Text
        If (Not (XML_ListPrice Is Nothing)) Then ListPrice = XML_ListPrice.Text
        If (Not (XML_OurPrice Is Nothing)) Then OurPrice = XML_OurPrice.Text
        If (Not (XML_Mfr Is Nothing)) Then Mfr = XML_Mfr.Text
        If (Not (XML_ReleaseDate Is Nothing)) Then ReleaseDate = XML_ReleaseDate.Text
        URL = XML_Child.getAttribute("url")
        
        If (Not (XML_Authors Is Nothing)) Then
          Set XML_Author = XML_Authors.selectSingleNode("Author")
          If (Not (XML_Author Is Nothing)) Then Author = XML_Author.Text
        End If
        
        ' Add the text to the form
        AmazonDetailsForm.AddItem Title, ListPrice, OurPrice, Mfr, URL, Author, ReleaseDate
    End Select
  Next i
  
  ' Show the form
  AmazonDetailsForm.Show vbModal

  '
  ' Update the document:
  ' 1 - Insert document title inline
  ' 2 - Insert footnote (if requested) as footnote
  '
  Updates = AmazonDetailsForm.GetSelectedDetails
  
  If (Updates <> "") Then
    Selection.MoveRight 1
    Selection.Text = " [" + Updates + "] "
  End If
  
  Footnote = AmazonDetailsForm.GetSelectedFootnote
  If (Footnote <> "") Then
    Selection.Footnotes.Add Range:=Selection.Range, Text:=Footnote
  End If
  
End Sub

VERSION 5.00
Begin {C62A69F0-16DC-11CE-9E98-00AA00574A4F} AmazonDetailsForm 
   Caption         =   "Books Matching Keyword "
   ClientHeight    =   5400
   ClientLeft      =   45
   ClientTop       =   330
   ClientWidth     =   8775
   OleObjectBlob   =   "AmazonDetailsForm.frx":0000
   StartUpPosition =   1  'CenterOwner
End
Attribute VB_Name = "AmazonDetailsForm"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False

Dim lstCurrentItem As Object
Dim bInsertFootnote As Boolean

Private Declare Function ShellExecute Lib "shell32.dll" Alias "ShellExecuteA" (ByVal hwnd As Long, ByVal lpOperation As String, ByVal lpFile As String, ByVal lpParameters As String, ByVal lpDirectory As String, ByVal nShowCmd As Long) As Long

Public Sub ClearItems()
  DetailsList.ListItems.Clear
  Set lstCurrentItem = Nothing
  bInsertFootnote = False
End Sub

Public Sub AddItem(Title As String, ListPrice As String, OurPrice As String, Manufacturer As String, URL As String, Author As String, ReleaseDate As String)
  Dim lstNewItem As Object
  
  Set lstNewItem = DetailsList.ListItems.Add
  lstNewItem.Text = Title
  lstNewItem.subitems(1) = ListPrice
  lstNewItem.subitems(2) = OurPrice
  lstNewItem.subitems(3) = Manufacturer
  lstNewItem.subitems(4) = URL
  lstNewItem.subitems(5) = Author
  lstNewItem.subitems(6) = ReleaseDate
End Sub

Private Sub btnClose_Click()
  AmazonDetailsForm.hide
End Sub

Public Function GetSelectedDetails()
  If (lstCurrentItem Is Nothing) Then
    GetSelectedDetails = ""
  Else
    GetSelectedDetails = lstCurrentItem.Text
  End If
End Function

Public Function GetSelectedFootnote()
  If (lstCurrentItem Is Nothing) Then
    GetSelectedFootnote = ""
  Else
    If (bInsertFootnote) Then
      GetSelectedFootnote = _
        lstCurrentItem.subitems(5) + ", " + _
        """" + lstCurrentItem.Text + """, " + _
        "(" + lstCurrentItem.subitems(4) + ")"
    Else
      GetSelectedFootnote = ""
    End If
  End If
End Function

Private Sub btnDetails_Click()
  If (Not (DetailsList.SelectedItem Is Nothing)) Then
    Call ShellExecute(0, "Open", DetailsList.SelectedItem.subitems(4), "", "", 1)
  End If
End Sub

Private Sub btnInsert_Click()
  Set lstCurrentItem = DetailsList.SelectedItem
  bInsertFootnote = False
  AmazonDetailsForm.hide
End Sub

Private Sub btnFootnote_Click()
  Set lstCurrentItem = DetailsList.SelectedItem
  bInsertFootnote = True
  AmazonDetailsForm.hide
End Sub

Private Sub DetailsList_DblClick()
  If (Not (DetailsList.SelectedItem Is Nothing)) Then
    Call ShellExecute(0, "Open", DetailsList.SelectedItem.subitems(4), "", "", 1)
  End If
End Sub

Private Sub UserForm_Click()

End Sub

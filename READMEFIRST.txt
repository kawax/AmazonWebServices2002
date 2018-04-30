Overview
--------

Thanks for downloading the Amazon.com Web Services software development kit 3.0. Three audiences who will find this document useful are:

1.Web site owners who want to enhance their web site with Amazon content and features.

2.Sellers who would like to automate the uploading and management of their inventory.

3.Developers who wish to experiment with or create applications built on Amazon.com Web Services.



Getting Started
----------------

This SDK contains a lot of material, but the best place to get started is our "Web Services API Guide". To access it, open up the "index.html" file in the "API Guide" folder. The guide has search capabilities, and is easy to navigate using the table of contents in the left-hand margin.

We have also provided a forum to discuss Amazon.com Web Services with other developers at
http://forums.prospero.com/am-assocdevxml/start/. If you have feedback you'd like to send us, please feel free to do so - you can contact us at webservices@amazon.com.

In order to use Amazon.com Web Services, you will first need to register and obtain a valid developer token. You can get your token here: http://associates.amazon.com/exec/panama/associates/join/developer/application.html.

The full terms of use of Amazon Web Services are described in the file AMAZON.COM_LICENSE.TXT included in this kit.



Contents of the Kit
-------------------

This development kit includes:

* Amazon.com Web Services API and Integration Guide (open "index.html" in that folder)
* Example XSLT style sheets
* Examples of "lite" and "heavy" XML documents
* Sample SOAP requests and responses
* Code samples in Java and PHP
* A Web services VBA macro for Microsoft Word
* A marketplace seller price tracking program that uses Web services
* The Amazon.com Web Services license
* An Open Source license for the Java code sample



Technical Documents
--------------------

Other documents can be found at the following URIs:

"lite" DTD: http://xml.amazon.com/schemas3/dev-lite.dtd
"heavy" DTD: http://xml.amazon.com/schemas3/dev-heavy.dtd
"lite" XSD: http://xml.amazon.com/schemas3/dev-lite.xsd
"heavy" XSD: http://xml.amazon.com/schemas3/dev-heavy.xsd
SOAP WSDL: http://soap.amazon.com/schemas3/AmazonWebServices.wsdl

Please note that you will not be able to view the DTDs with Internet Explorer.



Consuming Amazon.com Web Services with .NET
--------------------------------------------

Please see the following helpful articles about using Amazon.com Web Services with .NET. (Please note -- some of these tutorials refer to version 1.0 of the platform.)

* http://aspalliance.com/wisemonk/view.aspx?id=AN071902
* http://www.xmlforasp.net/codeSection.aspx?csID=76
* http://www.ondotnet.com/pub/a/dotnet/2002/07/18/amazon.html
* http://www.dotnetcoders.com/web/Articles/ShowArticle.aspx?article=53
* http://www.superdotnet.com/show_article.aspx?pkID=53
* http://bibliophil.org/example.asp



Instructions on how to use the Java Sample
-------------------------------------------

1) Requires JDK 1.4.0 and Apache Axis Beta-3. 
2) Unpackage attached file to a directory 
3) Download newest WSDL, and place in the same directory 
4) Run client.axis.sh to generate client stubs 
5) javac *.java 
6) java run 



Instructions on how to use the Java Price Tracking Program
-----------------------------------------------------------

Installation:

1) Requires JDK 1.4.0 and Apache Axis Beta-3. 
2) Unpackage attached file to a directory
3) Download newest WSDL from Amazon.com WebServices web site, and place in the same    directory
4a) In Unix, run "export client.axis.sh" in the command line to generate client stubs
4b) In Windows, run "client.axis.bat" in the dos-prompt to generate client stubs
5) javac *.java
6) java run

How to use the program:

1) Enter the vendor name, asscociate id, token and seller id into the vendor info box
2) Enter the appropiate URL
   * for XML, the URL is http://xml.amazon.com/onca/xml2
   * for Soap, the URL is http://soap.amazon.com/onca/soap2
3) Enter the number of products for tracking in the "Number of Asins Entered" field
4) Insert the Asin numbers in the second column of the table
   (Note: please press "Enter" button after inserting the last Asin to ensure that all Asins    are properly entered)
5) Enter the mode, type, offer and page
6) Choose the method of either XML or Soap to send the request
7) The results (in table format) will be displayed in a new Window. Only the seller's own       products will have a recommend price (others will be "Not Applicable") When there is no      product from the other sellers to do the comparison, the seller's own products will not      have a recommended price ("No Comment")
8) The Result area in the main window will show the response received from the server (in       XML format)
9) The results will be recorded into the asinRecord.dat file



Release Notes for SDK 1.01:
---------------------------

* Removed DTDs, XSDs and WSDL from kit. These files can still be retrieved online.



Release Notes for SDK 2.0:
--------------------------

* Second published version of this SDK.  Contains significant changes, including expanded descriptions of SOAP calls; search, parameter, and sort type listings; expanded XSL descriptions; price tracker tool; PHP code samples and more.



Release Notes for SDK 2.1:
--------------------------

* Added new SOAP samples and information on how to search a third party seller's listings by specific keyword.


Release Notes for SDK 3.0:
--------------------------

* Added more information on Power searching, marketplace searches, filtering searches for price ranges and keywords, working with z-Shops products, and mobile access to AWS.
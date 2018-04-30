<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">
<xsl:template match="/"> 
<html>
  <head>
    <title>Amazon.com Seller Profile</title>
    <style type="text/css">
<!--.serif {
	FONT-SIZE: small; FONT-FAMILY: times,serif
}
.sans {
	FONT-SIZE: small; FONT-FAMILY: verdana,arial,helvetica,sans-serif
}
.small {
	FONT-SIZE: x-small; FONT-FAMILY: verdana,arial,helvetica,sans-serif
}
.h1 {
	FONT-SIZE: small; COLOR: #cc6600; FONT-FAMILY: verdana,arial,helvetica,sans-serif
}
.h3color {
	FONT-SIZE: x-small; COLOR: #cc6600; FONT-FAMILY: verdana,arial,helvetica,sans-serif
}
.tiny {
	FONT-SIZE: xx-small; FONT-FAMILY: verdana,arial,helvetica,sans-serif
}
.listprice {
	FONT-SIZE: x-small; FONT-FAMILY: arial,verdana,helvetica,sans-serif; TEXT-DECORATION: line-through
}
.price {
	FONT-SIZE: x-small; COLOR: #990000; FONT-FAMILY: verdana,arial,helvetica,sans-serif
}

--></style>
  </head>
  <body text="#000000" vLink="#996633" aLink="#ff9933" link="#003399" bgColor="#ffffff">
    <a name="top"></a>
    <table cellSpacing="0" cellPadding="0" width="80%" align="center" bgColor="#999999" border="0">
      <tbody>
        <tr>
          <td>
            <table cellSpacing="1" cellPadding="4" width="100%" border="0">
              <tbody>
                <tr>
                  <td bgColor="#ffffff" colSpan="2">
                    <b class="h1"><xsl:value-of disable-output-escaping="yes" select="SellerProfile/SellerProfileDetails/SellerNickname"/></b>
                    &#160;<![CDATA[ ]]>&#160;<![CDATA[ ]]>&#160;
                    <p>
                      <font face="verdana,arial,helvetica" size="-1">Feedback Rating:</font>
                      <font face="verdana,arial,helvetica" size="-1">
                        <xsl:value-of disable-output-escaping="yes" select="SellerProfile/SellerProfileDetails/OverallFeedbackRating"/><![CDATA[ out of 5.0 ]]><br/>
                        <![CDATA[( ]]><xsl:value-of disable-output-escaping="yes" select="SellerProfile/SellerProfileDetails/NumberOfFeedback"/><![CDATA[ ) ]]>
                      </font>
                    </p></td>
                </tr>
                <tr>
                  <td width="26%" bgColor="#ffffff">
                    <font face="verdana,arial,helvetica" size="-1"><xsl:value-of disable-output-escaping="yes" select="SellerProfile/SellerProfileDetails/SellerNickname"/>'s Listings:</font></td>
                  <td width="74%" bgColor="#ffffff">
                    <font face="verdana,arial,helvetica" size="-1">
                      <xsl:text disable-output-escaping="yes">&#60;</xsl:text>a href="http://s1.amazon.com/exec/varzea/ts/my-zshop/<xsl:value-of disable-output-escaping="yes" select="SellerProfile/SellerProfileDetails/StoreId"/>"<xsl:text disable-output-escaping="yes">&#62;</xsl:text>View  the <xsl:value-of disable-output-escaping="yes" select="SellerProfile/SellerProfileDetails/StoreName"/><xsl:text disable-output-escaping="yes">&#60;</xsl:text>/a<xsl:text disable-output-escaping="yes">&#62;</xsl:text>
                    </font></td>
                </tr>
               </tbody>
            </table></td>
        </tr>
      </tbody>
    </table>
    <table cellSpacing="0" cellPadding="0" width="80%" align="center" border="0">
      <tbody>
        <tr>
          <td>
            <table cellSpacing="3" cellPadding="3" width="100%" bgColor="#ffffff" border="0">
              <tbody>
                <tr>
                  <td>
                    <font face="verdana,arial,helvetica" size="-1">
                      <b>Recent Feedback:</b>
                    </font></td>
                </tr>
                
                <xsl:for-each select="SellerProfile/SellerProfileDetails/SellerFeedback/Feedback">
                <tr>
                  <td bgColor="#eeeeee">
                    <font face="verdana,arial,helvetica" size="-1">
                      <font color="#008000">
                        <b><xsl:value-of disable-output-escaping="yes" select="FeedbackRating"/></b>
                        <![CDATA[ out of ]]>
                        <b>5</b>
                        <![CDATA[: ]]>
                      </font>
                    </font>
                    &quot;<xsl:value-of disable-output-escaping="yes" select="FeedbackComments"/>&quot;<br/>
                    <font face="verdana,arial,helvetica" color="#666666" size="-1">
                      <b>Date:</b>
                      <xsl:value-of disable-output-escaping="yes" select="FeedbackDate"/>&#160;&#160;&#160;<b><![CDATA[ Rated by Buyer: ]]></b>
                    </font>
                    <xsl:value-of disable-output-escaping="yes" select="FeedbackRater"/></td>
                </tr>
             	   </xsl:for-each>
               </tbody>
            </table>
           </td>
        </tr>
      </tbody>
    </table>
  </body>
</html>
</xsl:template>
</xsl:stylesheet>

<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">
<xsl:template match="/"> 
<html>
  <head>
    <xsl:text disable-output-escaping="yes">&#60;</xsl:text>title<xsl:text disable-output-escaping="yes">&#62;</xsl:text>Amazon.com SellerSearch for <xsl:value-of disable-output-escaping="yes" select="SellerSearch/SellerSearchDetails/SellerNickname"/><xsl:text disable-output-escaping="yes">&#60;</xsl:text>/title<xsl:text disable-output-escaping="yes">&#62;</xsl:text>
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
                    <b class="h1"> <xsl:value-of disable-output-escaping="yes" select="SellerSearch/SellerSearchDetails/SellerNickname"/></b>
                    &#160;<![CDATA[ ]]>&#160;<![CDATA[ ]]>&#160;
                    <p>
                      <font face="verdana,arial,helvetica" size="-1">Feedback Rating:</font>
                      <font face="verdana,arial,helvetica" size="-1">
                         <xsl:value-of disable-output-escaping="yes" select="SellerSearch/SellerSearchDetails/ListingProductInfo/ListingProductDetails/ExchangeSellerRating"/><![CDATA[ out of 5.0 ]]><br/>
                        <![CDATA[( ]]><xsl:value-of disable-output-escaping="yes" select="SellerSearch/SellerSearchDetails/NumberOfOpenListings"/><![CDATA[ open listings ) ]]>
                      </font>
                    </p></td>
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
                      <b>Items Available from <xsl:value-of disable-output-escaping="yes" select="SellerSearch/SellerSearchDetails/SellerNickname"/></b>
                    </font></td>
                </tr>
                
                <xsl:for-each select="SellerSearch/SellerSearchDetails/ListingProductInfo/ListingProductDetails">
                <tr>
                  <td bgColor="#eeeeee">
                    <font face="verdana,arial,helvetica" size="-1">
                      <font color="#008000">
                        <xsl:text disable-output-escaping="yes">&#60;</xsl:text>a href="http://s1.amazon.com/exec/varzea/ts/exchange-glance/<xsl:value-of select="ExchangeId"/>" style="color: #000000"<xsl:text 		disable-output-escaping="yes">&#62;</xsl:text><xsl:text disable-output-escaping="yes">&#60;</xsl:text>img src="http://images.amazon.com/images/P/<xsl:value-of select="ExchangeAsin"/>.01.TZZZZZZZ.jpg"<xsl:text disable-output-escaping="yes">&#62;</xsl:text><xsl:text disable-output-escaping="yes">&#60;</xsl:text>/a<xsl:text disable-output-escaping="yes">&#62;</xsl:text>
                      </font>
                    </font>
                   <xsl:text disable-output-escaping="yes">&#60;</xsl:text>a href="http://s1.amazon.com/exec/varzea/ts/exchange-glance/<xsl:value-of select="ExchangeId"/>" style="color: #000000"<xsl:text 		disable-output-escaping="yes">&#62;</xsl:text><xsl:value-of select="ExchangeTitle"/><xsl:text disable-output-escaping="yes">&#60;</xsl:text>/a<xsl:text disable-output-escaping="yes">&#62;</xsl:text> , <xsl:value-of select="ExchangePrice"/> <br/>
                    <font face="verdana,arial,helvetica" color="#666666" size="-1">
                      <b>Condition: </b>
                      <xsl:value-of select="ExchangeOfferingType"/>&#160;&#160;&#160;<b><![CDATA[ Quantity: ]]></b>
                      <xsl:value-of select="ExchangeQuantity"/>&#160;&#160;&#160;<b><![CDATA[ Location: ]]></b> 
                    </font>
                    <xsl:value-of disable-output-escaping="yes" select="ExchangeSellerState"/>, <xsl:value-of disable-output-escaping="yes" select="ExchangeSellerCountry"/></td>
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

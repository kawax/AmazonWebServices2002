<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">
<xsl:template match="/"> 
<html>
  <head>
    <xsl:text disable-output-escaping="yes">&#60;</xsl:text>title<xsl:text disable-output-escaping="yes">&#62;</xsl:text>Amazon.com SellerSearch for <xsl:value-of disable-output-escaping="yes" select="ExchangeSearch/ListingProductDetails/ExchangeSellerNickname"/><xsl:text disable-output-escaping="yes">&#60;</xsl:text>/title<xsl:text disable-output-escaping="yes">&#62;</xsl:text>
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
                    <b class="h1"><![CDATA[Seller: ]]></b><xsl:value-of disable-output-escaping="yes" select="ExchangeSearch/ListingProductDetails/ExchangeSellerNickname"/>
                    &#160;<![CDATA[ ]]>&#160;<![CDATA[ ]]>&#160;
                    <br/>
                      <font face="verdana,arial,helvetica" size="-1">Offered Since: </font>
                      <font face="verdana,arial,helvetica" size="-1">
                         <xsl:value-of disable-output-escaping="yes" select="ExchangeSearch/ListingProductDetails/ExchangeStartDate"/></font><br/>
                        <font face="verdana,arial,helvetica" size="-1">Closes On: </font>
			    <font face="verdana,arial,helvetica" size="-1">
				<xsl:value-of disable-output-escaping="yes" select="ExchangeSearch/ListingProductDetails/ExchangeStartDate"/>
                      </font>
                    </td>
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
                      <b><xsl:value-of disable-output-escaping="yes" select="ExchangeSearch/ListingProductDetails/ExchangeTitle"/></b>
                    </font></td>
                </tr>
                
                <xsl:for-each select="ExchangeSearch/ListingProductDetails">
                <tr>
                  <td bgColor="#eeeeee">
			<table>
			<tr>
			<td width="50%" valign="top">
                    <font face="verdana,arial,helvetica" size="-1">
                      <font color="#008000">
                        <xsl:text disable-output-escaping="yes">&#60;</xsl:text>a href="http://s1.amazon.com/exec/varzea/ts/exchange-glance/<xsl:value-of select="ExchangeId"/>" style="color: #000000"<xsl:text 		disable-output-escaping="yes">&#62;</xsl:text><xsl:text disable-output-escaping="yes">&#60;</xsl:text>img src="http://images.amazon.com/images/P/<xsl:value-of select="ExchangeAsin"/>.01.TZZZZZZZ.jpg"<xsl:text disable-output-escaping="yes">&#62;</xsl:text><xsl:text disable-output-escaping="yes">&#60;</xsl:text>/a<xsl:text disable-output-escaping="yes">&#62;</xsl:text>
                      </font>
                    </font>
			</td>
			<td valign="top">
                   <b><![CDATA[ My Price: ]]></b><xsl:value-of select="ExchangePrice"/> <br/>
			<b><![CDATA[ Type: ]]></b><xsl:value-of select="ExchangeConditionType"/> <br/>
			<b><![CDATA[ Condition: ]]></b><xsl:value-of select="ExchangeCondition"/> <br/>
                    </td>
                    </tr>
                    <tr>
			<td>
                    <font face="verdana,arial,helvetica" color="#666666" size="-1"><b><![CDATA[ Quantity: ]]></b>
                      <xsl:value-of select="ExchangeQuantity"/>&#160;&#160;&#160;</font>
			</td>
			<td>
			<font face="verdana,arial,helvetica" color="#666666" size="-1"><b><![CDATA[ Location: ]]></b> 
                    <xsl:value-of disable-output-escaping="yes" select="ExchangeSellerState"/></font></td>
			<td></td>
			</tr>
			</table>
			</td>
                </tr>
             	   </xsl:for-each>
               </tbody>
            </table>
           </td>
        </tr>
      </tbody>
    </table>
    <table cellSpacing="0" cellPadding="0" width="80%" align="center" bgColor="#999999" border="0">
      <tbody>
        <tr>
          <td>
            <table cellSpacing="0" cellPadding="4" width="100%" border="0">
              <tbody>
                <tr>
                  <td bgColor="#ffffff" colSpan="0">
                  		<xsl:text disable-output-escaping="yes">&#60;</xsl:text>form method="POST" action="http://www.amazon.com/o/dt/assoc/handle-buy-box=<xsl:value-of select="ExchangeAsin"/>/stores/detail/one-click-thank-you-confirm"<xsl:text disable-output-escaping="yes">&#62;</xsl:text>
		<xsl:text disable-output-escaping="yes">&#60;</xsl:text>input type="hidden" name="exchange.<xsl:value-of select="ExchangeId"/>.<xsl:value-of select="ExchangeAsin"/>.<xsl:value-of select="ExchangeSellerId"/>" value="1"<xsl:text disable-output-escaping="yes">&#62;</xsl:text>
		<input type="hidden" name="template-name" value="stores/detail/one-click-thank-you-confirm "/>
	       <input type="hidden" name="tag-value" value="associates_tag"/>
		<input type="hidden" name="dev-tag-value" value="dev_tag"/>
		<center><input type="image" name="submit.add-to-cart" value="Buy from Amazon.com" border="0" alt="Buy from Amazon.com" src="http://g-images.amazon.com/images/G/01/detail/ol-add-to-cart.gif" width="126" height="22"/></center>
		<xsl:text disable-output-escaping="yes">&#60;</xsl:text>/form<xsl:text disable-output-escaping="yes">	&#62;	</xsl:text>
		     </td>
                </tr>
               </tbody>
            </table></td>
        </tr>
      </tbody>
    </table>
  </body>
</html>
</xsl:template>
</xsl:stylesheet>

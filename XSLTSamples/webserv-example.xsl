<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">
<xsl:template match="/"> 
<!--Add XSL HERE-->
<p align="left"><font color="000000">
<xsl:for-each select="ProductInfo/Details">
<table border="1">
	<tbody>
		<tr>
			<td>
				<xsl:text disable-output-escaping="yes">&#60;</xsl:text>img src="<xsl:value-of 		select="ImageUrlSmall"/>"<xsl:text disable-output-escaping="yes">&#62;</xsl:text><xsl:text disable-output-escaping="yes">&#60;</xsl:text>a href="<xsl:value-of select="@url"/>" style="color: #000000"<xsl:text 		disable-output-escaping="yes">&#62;</xsl:text><xsl:value-of select="ProductName"/><xsl:text disable-output-escaping="yes">&#60;</xsl:text>/a<xsl:text disable-output-escaping="yes">&#62;</xsl:text><br/><strong>		ASIN:</strong> <xsl:value-of disable-output-escaping="yes" select="Asin"/><br/><strong>Our Price: 		</strong> <xsl:value-of disable-output-escaping="yes" select="OurPrice"/><br/> <br/><strong>	Customer's 	Rating: </strong> <xsl:value-of disable-output-escaping="yes" 	select="Reviews/CustomerReview/Rating"/>	<br/><strong>Summary: </strong> <xsl:value-of disable-output-escaping="yes" 	select="Reviews/CustomerReview/Summary"/><br/><strong>Comments: </strong> 	<xsl:value-of disable-output-escaping="yes" select="Reviews/CustomerReview/Comment"/> <p/>
		<xsl:text disable-output-escaping="yes">&#60;</xsl:text>form method="POST" 		action="http://www.amazon.com/o/dt/assoc/handle-buy-box=<xsl:value-of 		select="Asin"/>"<xsl:text disable-output-escaping="yes">&#62;		</xsl:text>
		<xsl:text disable-output-escaping="yes">&#60;</xsl:text>input type="hidden" name="asin.<xsl:value-of 		select="Asin"/>" value="1"<xsl:text disable-output-escaping="yes">&#62;</xsl:text>
		<input type="hidden" name="tag-value" value="webservices-20"/>
		<input type="hidden" name="tag_value" value="webservices-20"/>
		<input type="hidden" name="dev-tag-value" value="dev_tag"/>
		<input type="Submit" value="Buy From Amazon" name="submit.add-to-cart"/>
		<xsl:text disable-output-escaping="yes">&#60;</xsl:text>/form<xsl:text disable-output-escaping="yes">	&#62;	</xsl:text>
			</td>
		</tr>
	</tbody>
</table>
</xsl:for-each>
</font>
</p>
<!--End of XSL-->
</xsl:template>
</xsl:stylesheet>


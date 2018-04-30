<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">
<xsl:template match="/"> 
<!--Add XSL HERE-->
<p align="left"><font color="000000">
<TABLE border="0" cellspacing="0" cellpadding="0"><tr valign="bottom" align="center"><td><xsl:text disable-output-escaping="yes">&#60;</xsl:text>A href="<xsl:value-of select="@url"/>" target="_top"<xsl:text disable-output-escaping="yes">&#62;</xsl:text><xsl:text disable-output-escaping="yes">&#60;</xsl:text>/A<xsl:text disable-output-escaping="yes">&#62;</xsl:text></td></tr> <tr valign="top" align="center"><td> <TABLE width="120" height="60" border="0" cellpadding="1" cellspacing="0" bgcolor="#616161"><TR><TD width="100%" height="100%"><TABLE width="100" height="100" border="0" cellpadding="0" cellspacing="0"><TR bgcolor="#FFFFFF"><TD align="center" valign="middle"><table cellpadding="2" cellspacing="0" border="0"><tr><td><table cellpadding="0" cellspacing="0" border="0">
<IMG src="http://rcm-images.amazon.com/images/G/01/rcm/120x60_banner.gif" width="135" border="0" alt="Amazon.com logo"/>
<xsl:for-each select="ProductInfo/Details">
<xsl:if test="position() &lt; 3">
<tr><td><xsl:text disable-output-escaping="yes">&#60;</xsl:text>A href="<xsl:value-of select="@url"/>" target="_top"<xsl:text disable-output-escaping="yes">&#62;</xsl:text><xsl:text disable-output-escaping="yes">&#60;</xsl:text>img src="<xsl:value-of select="ImageUrlSmall"/>" border="0" vspace="2" hspace="2" alt="cover art"<xsl:text disable-output-escaping="yes">&#62;</xsl:text><xsl:text disable-output-escaping="yes">&#60;</xsl:text>/A<xsl:text disable-output-escaping="yes">&#62;</xsl:text></td><TD><xsl:text disable-output-escaping="yes">&#60;</xsl:text>A href="<xsl:value-of select="@url"/>" target="_top"<xsl:text disable-output-escaping="yes">&#62;</xsl:text><FONT face="Arial" size="-2" color="3366FF"><xsl:value-of select="ProductName"/></FONT><xsl:text disable-output-escaping="yes">&#60;</xsl:text>/A<xsl:text disable-output-escaping="yes">&#62;</xsl:text><br/><FONT face="Arial" size="-2" color="000000"><xsl:value-of select="Manufacturer"/></FONT><br/><font face="Arial" size="-2"><font color="990000">New <xsl:value-of select="OurPrice"/><hr/></font></font></TD></tr>
</xsl:if>
</xsl:for-each>
<tr><td colspan="2" height="5"></td></tr></table></td></tr></table></TD></TR><TR bgcolor="#FFFFFF"><TD align="center"><FONT face="Arial" size="-2" color="A1A1A1">(Prices May Change)<br/><A href="http://rcm.amazon.com/e/cm/privacy-policy.html?o=1" target="_top"><FONT face="Arial" size="-2" color="A1A1A1">Privacy Information</FONT></A></FONT></TD></TR></TABLE></TD></TR></TABLE></td></tr></TABLE></font></p>
<!--End of XSL-->
</xsl:template>
</xsl:stylesheet>


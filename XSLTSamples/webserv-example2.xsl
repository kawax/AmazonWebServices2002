<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">
<xsl:template match="/"> 
<table width="100%" cellspacing="0" cellpadding="0" border="0">
    <tr>
        <td width="1" background="http://su.elnimages.net/img/colorscheme/0/0bg.gif" align="left"><img src="http://su.elnimages.net/img/colorscheme/0/0left.gif" border="0"/><br/></td>
        <td colspan="2" background="http://su.elnimages.net/img/colorscheme/0/0bg.gif" width="100%" class="orangeHeader"> Bookstore - Bestsellers</td>
        <td valign="bottom" background="http://su.elnimages.net/img/colorscheme/0/0bg.gif" align="right"></td>
        <td width="13" background="http://su.elnimages.net/img/colorscheme/0/0bg.gif" align="right"><img src="http://su.elnimages.net/img/colorscheme/0/0right.gif" border="0"/></td>
    </tr>
</table>


<table width="100%" cellspacing="0" cellpadding="0" border="0">
    <tr>
        <td width="1" background="http://su.elnimages.net/img/colorscheme/0/0leftbg.gif" rowspan="1"><img src="http://su.earthlink.net/img/x.gif" width="1" height="1" border="0"/><br/></td>
        <td>
            <table width="100%" cellspacing="0" cellpadding="0" border="0">

                


<tr bgcolor="#dedede">
  <td><img border="0" alt="" height="35" width="4" src="http://su.elnimages.net/img/x.gif"/></td>
  <td colspan="2" bgcolor="#dedede">
    
    <table border="0" cellpadding="4" cellspacing="0">
  <form name="amazon" action="http://www.amazon.com/exec/obidos/external-search" method="get">
    <input type="hidden" name="tag" value="bubba"/>
    <tr>
      <td><a href="http://www.amazon.com/exec/obidos/redirect?tag=earthpsp-logo&amp;path=tg/stores/browse/-/books/283155/ref=ns_books"><img src="http://su.elnimages.net/img/logos/logo_amazon.gif" width="94" height="24" border="0"/></a></td>
      <td> <input type="text" name="keyword" size="10" /> <input type="submit" name="Go" value="Go!"/></td>
    </tr>
  </form>
</table>
<table border="0" cellpadding="4" cellspacing="0" width="100%">
  <tr>
    <td align="center"><a href="http://www.amazon.com/exec/obidos/redirect?tag=earthfeature&amp;path=tg/browse/-/45"><small><b>Books<br/>under $10</b></small></a></td>
    <td align="center"><a href="http://www.amazon.com/exec/obidos/redirect?tag=earthoprah&amp;path=tg/browse/-/68100"><small><b>Oprah's<br/>Latest Pick</b></small></a></td>
    <td align="center"><a href="http://www.amazon.com/exec/obidos/redirect?tag=earthtop100&amp;path=subst/lists/best/bestsellers.html"><small><b>40% off<br/>Bestsellers</b></small></a></td>
  </tr>
</table>
  </td>
</tr>
<tr>
  <td align="middle" colspan="3">
    <table width="90%" cellspacing="0" cellpadding="0" border="0">
<tr>
<td colspan="2"><small> </small></td>
</tr>
<tr>
<td colspan="2"><small><b>Featured Selection:</b>
<br/>
<a href="http://www.amazon.com/exec/obidos/redirect?tag=earthfeature&amp;path=tg/browse/-/283155"><b>NEW</b> - Lower prices on books at Amazon.com! Save 30% on books over $20</a><p/></small></td>
</tr>
<tr>
<td colspan="2"><small> </small></td>
</tr>
<tr>
<td align="center" valign="top"><xsl:text disable-output-escaping="yes">&#60;</xsl:text>a href="http://www.amazon.com:80/exec/obidos/redirect?tag=earthbook&amp;path=ASIN/<xsl:value-of select="ProductInfo/Details/Asin"/>"<xsl:text disable-output-escaping="yes">&#62;</xsl:text><xsl:text disable-output-escaping="yes">&#60;</xsl:text>img src="<xsl:value-of select="ProductInfo/Details/ImageUrlMedium"/>" width="60" height="100" align="center" border="1"<xsl:text disable-output-escaping="yes">&#62;</xsl:text>
<xsl:text disable-output-escaping="yes">&#60;</xsl:text>/a<xsl:text disable-output-escaping="yes">&#62;</xsl:text><br/>
<xsl:text disable-output-escaping="yes">&#60;</xsl:text>form method="POST" action="http://www.amazon.com/o/dt/assoc/handle-buy-box=<xsl:value-of select="ProductInfo/Details/Asin"/>"<xsl:text disable-output-escaping="yes">&#62;</xsl:text>
<xsl:text disable-output-escaping="yes">&#60;</xsl:text>input type="hidden" name="asin.<xsl:value-of select="ProductInfo/Details/Asin"/>" value="1"<xsl:text disable-output-escaping="yes">&#62;</xsl:text>
<input type="hidden" name="tag-value" value="associates_tag"/>
<input type="hidden" name="tag_value" value="associates_tag"/>
<input type="hidden" name="dev-tag-value" value="dev_tag"/>
<input type="Submit" value="Buy From Amazon" name="submit.add-to-cart"/>
<xsl:text disable-output-escaping="yes">&#60;</xsl:text>/form<xsl:text disable-output-escaping="yes">&#62;</xsl:text>

</td><td rowspan="2" valign="top"><small>
<ol>
<xsl:for-each select="ProductInfo/Details">
<li>
<xsl:text disable-output-escaping="yes">&#60;</xsl:text>a href="http://www.amazon.com:80/exec/obidos/redirect?tag=earthbook&amp;path=ASIN/<xsl:value-of disable-output-escaping="yes" select="Asin"/>"<xsl:text disable-output-escaping="yes">&#62;</xsl:text><b><xsl:value-of select="ProductName"/></b><xsl:text disable-output-escaping="yes">&#60;</xsl:text>/a<xsl:text disable-output-escaping="yes">&#62;</xsl:text> , <xsl:value-of select="OurPrice"/><br/>
<br/>
</li>
</xsl:for-each>
</ol>
</small></td>
</tr>
<tr>
<td align="center" valign="bottom">
<font size="-1" face="Verdana"> <br/>
<b><a href="http://www.amazon.com/books">More<br/>Books!</a></b>
</font>
<br/> </td>
</tr>
</table>


    <small> </small>
  </td>
</tr>
                
            <tr>
      <td bgcolor="#ffffff" colspan="3"><small> </small></td>
    </tr>

            </table>
        </td>
        <td width="1" background="http://su.elnimages.net/img/colorscheme/0/0leftbg.gif" rowspan="1"><img src="http://su.earthlink.net/img/x.gif" width="1" height="1" border="0"/><br/></td>
    </tr>
    <tr>
        <td colspan="5" background="http://su.elnimages.net/img/colorscheme/0/0x.gif"><img src="http://su.earthlink.net/img/x.gif" width="50" height="1" border="0"/></td>
    </tr>
</table>
</xsl:template>
</xsl:stylesheet>

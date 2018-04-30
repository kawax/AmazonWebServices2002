<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">
<xsl:template match="/"> 
<html>
  <head>
    <title><xsl:value-of select="ProductName"/></title>
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
  <BODY text="#000000" vLink="#996633" aLink="#ff9933" link="#003399" bgColor="#ffffff">
    <a name="top"></a>
    <table width="100%" border="0" vspace="3">
      <tbody>
        <tr>
          <td class="small" vAlign="top" align="left">
           <xsl:text disable-output-escaping="yes">&#60;</xsl:text>a href="http://www.amazon.com:80/exec/obidos/redirect?tag=[your assoc-tag-goes-here]&amp;path=ASIN/<xsl:value-of select="ProductInfo/Details/Asin"/>"<xsl:text disable-output-escaping="yes">&#62;</xsl:text><xsl:text disable-output-escaping="yes">&#60;</xsl:text>img src="<xsl:value-of select="ProductInfo/Details/ImageUrlMedium"/>" width="60" height="100" align="left" border="0"<xsl:text disable-output-escaping="yes">&#62;</xsl:text>
<xsl:text disable-output-escaping="yes">&#60;</xsl:text>/a<xsl:text disable-output-escaping="yes">&#62;</xsl:text>
            <b class="sans"><xsl:value-of select="ProductInfo/Details/ProductName"/></b><br/>
            <b><xsl:value-of select="Media"/></b>
            <![CDATA[ ~  ]]><xsl:value-of select="ProductInfo/Details/Actors"/><xsl:value-of select="ProductInfo/Details/Authors/Author"/><br/>   <font size="-1">
	     <font color="#990000"><strong><![CDATA[ Format:  ]]></strong></font><xsl:value-of select="ProductInfo/Details/Media"/><br/>
            <font color="#990000"><strong><![CDATA[ Orig. Release Date:  ]]></strong></font><xsl:value-of select="ProductInfo/Details/ReleaseDate"/><br/>
            <font color="#990000"><strong><![CDATA[ Availability:  ]]></strong></font><xsl:value-of select="ProductInfo/Details/Availability"/><br/>
            </font>
            <p>
              <img height="11" hspace="0" src="http://g-images.amazon.com/images/G/01/icons/medium-orange-arrow-right.gif" width="12" align="left" vspace="5" border="0"/>
              <![CDATA[ Reviews, recommendations, and more: ]]>
              <xsl:text disable-output-escaping="yes">&#60;</xsl:text>a href="http://www.amazon.com/o/dt/assoc/tg/stores/detail/-/dvd/<xsl:value-of select="ProductInfo/Details/Asin"/>/102-1626247-7478531"<xsl:text disable-output-escaping="yes">&#62;</xsl:text>
                <img height="22" alt="View product details" hspace="0" src="http://g-images.amazon.com/images/G/01/offerings/view-product-details.gif" width="137" align="absMiddle" border="0"/>
              <xsl:text disable-output-escaping="yes">&#60;</xsl:text>/a<xsl:text disable-output-escaping="yes">&#62;</xsl:text>
            </p></td>
          <td class="small" vAlign="top" align="right" width="190">
            <table cellSpacing="0" cellPadding="0" width="190" border="0">
              <tbody>
                <tr>
                  <td>
                    <table cellSpacing="0" cellPadding="0" width="100%" align="left" bgColor="#4480dd" border="0">
                      <tbody>
                        <tr>
                          <td class="tiny" align="left">
                            <img height="18" src="http://g-images.amazon.com/images/G/01/offerings/price-at-a-glance-left.gif" width="128"/></td>
                          <td class="tiny" align="right">
                            <img height="18" src="http://g-images.amazon.com/images/G/01/offerings/price-at-a-glance-right.gif" width="13"/></td>
                        </tr>
                      </tbody>
                    </table></td>
                </tr>
                <tr>
                  <td>
                    <table cellSpacing="0" cellPadding="6" width="100%" align="left" bgColor="#99ccff" border="0">
                      <tbody>
                        <tr>
                          <td>
                            <table cellSpacing="0" cellPadding="4" width="100%" align="left" bgColor="#ffffff" border="0">
                              <tbody>
                                <tr>
                                  <td class="small"><b>
                                    <xsl:text disable-output-escaping="yes">&#60;</xsl:text>a href="http://www.amazon.com/o/dt/assoc/tg/stores/offering/list/-/<xsl:value-of select="ProductInfo/Details/Asin"/>"<xsl:text disable-output-escaping="yes">&#62;</xsl:text>List Price: <xsl:text disable-output-escaping="yes">&#60;</xsl:text>/a<xsl:text disable-output-escaping="yes">&#62;</xsl:text>
					   </b>
                                    <span class="listprice"><xsl:value-of select="ProductInfo/Details/ListPrice"/></span><br/>
                                    <nobr>
                                      <b>
                                        <xsl:text disable-output-escaping="yes">&#60;</xsl:text>a href="http://www.amazon.com/o/dt/assoc/tg/stores/offering/list/-/<xsl:value-of select="ProductInfo/Details/Asin"/>"<xsl:text disable-output-escaping="yes">&#62;</xsl:text>Amazon.com: <xsl:text disable-output-escaping="yes">&#60;</xsl:text>/a<xsl:text disable-output-escaping="yes">&#62;</xsl:text>
                                      </b>
                                      <span class="price"><xsl:value-of select="ProductInfo/Details/OurPrice"/></span>
                                    </nobr><br/>
                                    <nobr>
                                      <b>
                                        <xsl:text disable-output-escaping="yes">&#60;</xsl:text>a href="http://www.amazon.com/o/dt/assoc/tg/stores/offering/list/-/<xsl:value-of select="ProductInfo/Details/Asin"/>/used/"<xsl:text disable-output-escaping="yes">&#62;</xsl:text>Used <xsl:text disable-output-escaping="yes">&#60;</xsl:text>/a<xsl:text disable-output-escaping="yes">&#62;</xsl:text>
                                      </b>
                                      <span class="price"><xsl:value-of select="ProductInfo/Details/UsedPrice"/></span>
                                    </nobr><br/>
                                    <nobr>
                                      <b>
                                         <xsl:text disable-output-escaping="yes">&#60;</xsl:text>a href="http://www.amazon.com/o/dt/assoc/tg/stores/offering/list/-/<xsl:value-of select="ProductInfo/Details/Asin"/>/new"<xsl:text disable-output-escaping="yes">&#62;</xsl:text>New <xsl:text disable-output-escaping="yes">&#60;</xsl:text>/a<xsl:text disable-output-escaping="yes">&#62;</xsl:text>
                                      </b>
                                      <span class="price"><xsl:value-of select="ProductInfo/Details/ThirdPartyNewPrice"/></span>
                                    </nobr><br/>
                                    <nobr>
                                      <b>
                                         <xsl:text disable-output-escaping="yes">&#60;</xsl:text>a href="http://www.amazon.com/o/dt/assoc/tg/stores/offering/list/-/<xsl:value-of select="ProductInfo/Details/Asin"/>/collectible"<xsl:text disable-output-escaping="yes">&#62;</xsl:text>Collectible <xsl:text disable-output-escaping="yes">&#60;</xsl:text>/a<xsl:text disable-output-escaping="yes">&#62;</xsl:text>
                                      </b>
                                      <span class="price"><xsl:value-of select="ProductInfo/Details/CollectiblePrice"/></span>
                                    </nobr><br/>
                                    <nobr>
                                      <b>
                                         <xsl:text disable-output-escaping="yes">&#60;</xsl:text>a href="http://www.amazon.com/o/dt/assoc/tg/stores/offering/list/-/<xsl:value-of select="ProductInfo/Details/Asin"/>/refurbished/"<xsl:text disable-output-escaping="yes">&#62;</xsl:text>Refurbished <xsl:text disable-output-escaping="yes">&#60;</xsl:text>/a<xsl:text disable-output-escaping="yes">&#62;</xsl:text>
                                      </b>
                                      <span class="price"><xsl:value-of select="ProductInfo/Details/ProductName/RefurbishedPrice"/></span>
                                    </nobr><br/></td>
                                </tr>
                              </tbody>
                            </table></td>
                        </tr>
                      </tbody>
                    </table></td>
                </tr>
                <tr>
                  <td>
                    <table cellSpacing="0" cellPadding="0" width="100%" align="left" bgColor="#99ccff" border="0">
                      <tbody>
                        <tr>
                          <td align="left">
                            <img height="6" src="http://g-images.amazon.com/images/G/01/detail/buybox/bl-01.gif" width="6"/></td>
                          <td align="right">
                            <img height="6" src="http://g-images.amazon.com/images/G/01/detail/buybox/br-01.gif" width="6"/></td>
                        </tr>
                      </tbody>
                    </table></td>
                </tr>
              </tbody>
            </table></td>
        </tr>
      </tbody>
    </table>
    <table cellSpacing="0" cellPadding="0" width="100%" border="0">
      <tbody>
        <tr>
          <td class="small" width="100%">
            <table cellSpacing="0" cellPadding="0" width="100%" border="0">
              <tbody>
                <tr vAlign="top">
                  <td rowSpan="4">
                    <img height="23" alt="Browse by type:" src="http://g-images.amazon.com/images/G/01/offerings/browse-by-type.gif" width="106" border="0"/></td>
                  <td rowSpan="4">
                    <nobr>
                       <xsl:text disable-output-escaping="yes">&#60;</xsl:text>a href="http://www.amazon.com/o/dt/assoc/tg/stores/offering/list/-/<xsl:value-of select="ProductInfo/Details/Asin"/>/all"<xsl:text disable-output-escaping="yes">&#62;</xsl:text><img height="23" alt="all" src="http://g-images.amazon.com/images/G/01/offerings/mtt-all.gif" border="0"/>
<xsl:text disable-output-escaping="yes">&#60;</xsl:text>/a<xsl:text disable-output-escaping="yes">&#62;</xsl:text>
                      <xsl:text disable-output-escaping="yes">&#60;</xsl:text>a href="http://www.amazon.com/o/dt/assoc/tg/stores/offering/list/-/<xsl:value-of select="ProductInfo/Details/Asin"/>/new"<xsl:text disable-output-escaping="yes">&#62;</xsl:text><img height="23" alt="all" src="http://g-images.amazon.com/images/G/01/offerings/mtt-new.gif" border="0"/>
<xsl:text disable-output-escaping="yes">&#60;</xsl:text>/a<xsl:text disable-output-escaping="yes">&#62;</xsl:text>
<xsl:text disable-output-escaping="yes">&#60;</xsl:text>a href="http://www.amazon.com/o/dt/assoc/tg/stores/offering/list/-/<xsl:value-of select="ProductInfo/Details/Asin"/>/used"<xsl:text disable-output-escaping="yes">&#62;</xsl:text><img height="23" alt="all" src="http://g-images.amazon.com/images/G/01/offerings/mtt-used.gif" border="0"/>
<xsl:text disable-output-escaping="yes">&#60;</xsl:text>/a<xsl:text disable-output-escaping="yes">&#62;</xsl:text>
<xsl:text disable-output-escaping="yes">&#60;</xsl:text>a href="http://www.amazon.com/o/dt/assoc/tg/stores/offering/list/-/<xsl:value-of select="ProductInfo/Details/Asin"/>/collectible"<xsl:text disable-output-escaping="yes">&#62;</xsl:text><img height="23" alt="all" src="http://g-images.amazon.com/images/G/01/offerings/mtt-collectible.gif"  border="0"/>
<xsl:text disable-output-escaping="yes">&#60;</xsl:text>/a<xsl:text disable-output-escaping="yes">&#62;</xsl:text>
<xsl:text disable-output-escaping="yes">&#60;</xsl:text>a href="http://www.amazon.com/o/dt/assoc/tg/stores/offering/list/-/<xsl:value-of select="ProductInfo/Details/Asin"/>/sell-yours-here"<xsl:text disable-output-escaping="yes">&#62;</xsl:text><img height="23" alt="all" src="http://g-images.amazon.com/images/G/01/offerings/mtt-sell-yours-here.gif" border="0"/>
<xsl:text disable-output-escaping="yes">&#60;</xsl:text>/a<xsl:text disable-output-escaping="yes">&#62;</xsl:text>
                    </nobr></td>
                  <td width="100%" bgColor="#ffffff" height="3">
                    <img height="1" src="http://g-images.amazon.com/images/G/01/baby/nav/spacer1x1.gif" width="1"/></td>
                  <td rowSpan="4">
                    <img height="23" src="http://g-images.amazon.com/images/G/01/nav/3rd-level/right-corner-cap-top.gif" width="6" border="0"/></td>
                </tr>
                <tr>
                  <td bgColor="#999999" height="1">
                    <img height="1" src="http://g-images.amazon.com/images/G/01/baby/nav/spacer1x1.gif" width="1"/></td>
                </tr>
                <tr>
                  <td bgColor="#cccccc" height="18">
                    <img height="1" src="http://g-images.amazon.com/images/G/01/baby/nav/spacer1x1.gif" width="1"/></td>
                </tr>
                <tr>
                  <td bgColor="#666666" height="1">
                    <img height="1" src="http://g-images.amazon.com/images/G/01/baby/nav/spacer1x1.gif" width="1"/></td>
                </tr>
              </tbody>
            </table></td>
        </tr>
      </tbody>
    </table>
    <table cellSpacing="0" cellPadding="0" width="100%" border="0">
      <tbody>
        <tr>
          <td bgColor="#666666">
            <img height="1" src="http://g-images.amazon.com/images/G/01/baby/nav/spacer1x1.gif" width="1" border="0"/></td>
          <td>
            <table cellSpacing="8" cellPadding="0" width="100%" border="0">
              <tbody>
                <tr>
                  <td class="small">
                    <table cellSpacing="0" cellPadding="3" width="100%" border="0">
                      <tbody>
                        <tr>
                          <td>
                            <table cellSpacing="0" cellPadding="1" width="100%" bgColor="#c9d4d7" border="0">
                              <tbody>
                                <tr>
                                  <td class="small" align="left" width="50%">
                                    &#160;
                                   	</td>
                                  <td class="small" align="right" width="50%">
                                    <a href="http://www.amazon.com/o/dt/assoc/tg/browse/-/1060536/102-1626247-7478531">
                                      <img height="22" alt="How does this work?" src="http://g-images.amazon.com/images/G/01/offerings/help-howdoesthiswork.gif" width="151" border="0"/>
                                    </a></td>
                                </tr>
                              </tbody>
                            </table></td>
                        </tr>
                        <tr>
                          <td>
                            <table cellSpacing="0" cellPadding="0" width="100%" border="0">
                              <tbody>
                                <tr>
                                  <td class="tiny">&#160;</td>
                                  <td class="tiny">
                                    <![CDATA[Items usually ship in 1-2 business days (]]>
                                    <a href="http://www.amazon.com/o/dt/assoc/tg/browse/-/537734/102-1626247-7478531">see Amazon.com's delivery options</a>
                                    <![CDATA[). Buying is safe--]]>
                                    <a href="http://www.amazon.com/o/dt/assoc/tg/browse/-/537868/no-link/102-1626247-7478531">see Amazon.com's guarantee</a>
                                    <![CDATA[. ]]></td>
                                </tr>
                              </tbody>
                            </table></td>
                        </tr>
                        <tr>
                          <td>
                            <table cellSpacing="4" cellPadding="0" width="95%" align="center" bgColor="#ffffff" border="0">
                              <tbody>
                                <tr vAlign="top">
                                  <td class="small" vAlign="top" width="10%">
                                    <b>Price</b></td>
                                  <td class="small" width="20%">
                                    <b>Condition</b></td>
                                  <td class="small" colSpan="2">
                                    <b>Seller information</b></td>
                                </tr>

                                <!--begin XSL Transform -->
                                <xsl:for-each select="ProductInfo/Details/ThirdPartyProductInfo/ThirdPartyProductDetails">
                                <tr>
                                  <td colSpan="4" height="1">
                                    <hr noShade="noShade" SIZE="1"/></td>
                                </tr>
                                <tr>
                                  <td vAlign="top" width="10%">
                                    <b class="price"><![CDATA[$]]><xsl:value-of select="OfferingPrice"/></b><br/>
					</td>
                                  <td class="small" vAlign="top" width="20%"><xsl:value-of select="Condition"/></td>
                                  <td class="small" vAlign="top" width="65%">
                                    <p>
                                      <![CDATA[Seller: ]]>
                                      <xsl:text disable-output-escaping="yes">&#60;</xsl:text>a href="http://www.amazon.com/o/dt/assoc/redirect-to-external-url/102-1626247-7478531?path=http%3A//s1.amazon.com/exec/varzea/ts/customer-glance/<xsl:value-of disable-output-escaping="yes" select="SellerId"/>"<xsl:text disable-output-escaping="yes">&#62;</xsl:text><xsl:value-of select="SellerNickname"/><xsl:text disable-output-escaping="yes">&#60;</xsl:text>/a<xsl:text disable-output-escaping="yes">&#62;</xsl:text>
                                      <br/>
                                      <![CDATA[Ships from: ]]><xsl:value-of select="SellerState"/><![CDATA[, ]]><xsl:value-of select="SellerCountry"/><br/>
                                    </p>
                                    <p>Comments: - </p></td>
                                  <td align="right" width="126">
                                    <xsl:text disable-output-escaping="yes">&#60;</xsl:text>form action="http://www.amazon.com/o/dt/assoc/handle-buy-box=<xsl:value-of disable-output-escaping="yes" select="../../Asin"/>/stores/detail/one-click-thank-you-confirm/102-1626247-7478531" method="post"<xsl:text disable-output-escaping="yes">&#62;</xsl:text>
                                      <table cellSpacing="0" cellPadding="0" width="126" border="0">
                                        <tbody>
                                          <tr>
                                            <td align="middle">
                                              <xsl:text disable-output-escaping="yes">&#60;</xsl:text>input type="hidden" value="1" name="exchange.<xsl:value-of disable-output-escaping="yes" select="ExchangeId"/>.<xsl:value-of disable-output-escaping="yes" select="../../Asin"/>.<xsl:value-of disable-output-escaping="yes" select="SellerId"/>"/<xsl:text disable-output-escaping="yes">&#62;</xsl:text>
                                              <input type="image" alt="Buy from Amazon.com" src="http://g-images.amazon.com/images/G/01/buttons/add-to-cart-yellow-short.gif" value="Buy from Amazon.com" border="0" name="submit.add-to-cart"/></td>
                                          </tr>
                                          <tr>
                                            <td align="middle">
                                              </td>
                                          </tr>
                                          <tr>
                                            <td align="middle">
                                              <span class="tiny">
                                              </span></td>
                                          </tr>
                                        </tbody>
                                      </table>
                                    <xsl:text disable-output-escaping="yes">&#60;</xsl:text>/form<xsl:text disable-output-escaping="yes">&#62;</xsl:text>
</td>
                                </tr>
                                </xsl:for-each>
                                <!--end XSL Transform -->
                                
                              </tbody>
                            </table></td>
                        </tr>
                      </tbody>
                    </table></td>
                </tr>
              </tbody>
            </table></td>
          <td bgColor="#666666">
            <img height="1" src="http://g-images.amazon.com/images/G/01/baby/nav/spacer1x1.gif" width="1" border="0"/></td>
        </tr>
      </tbody>
    </table>
    <table cellSpacing="0" cellPadding="0" width="100%" border="0">
      <tbody>
        <tr>
          <td class="small" width="100%">
            <table cellSpacing="0" cellPadding="0" width="100%" border="0">
              <tbody>
                <tr vAlign="top">
                  <td rowSpan="4">
                    <img height="23" alt="Browse by type:" src="http://g-images.amazon.com/images/G/01/offerings/browse-by-type-u.gif" width="106" border="0"/></td>
                   <td rowSpan="4">
                    <nobr>
                       <xsl:text disable-output-escaping="yes">&#60;</xsl:text>a href="http://www.amazon.com/o/dt/assoc/tg/stores/offering/list/-/<xsl:value-of select="ProductInfo/Details/Asin"/>/all"<xsl:text disable-output-escaping="yes">&#62;</xsl:text><img height="23" alt="all" src="http://g-images.amazon.com/images/G/01/offerings/mtt-all.gif" border="0"/>
<xsl:text disable-output-escaping="yes">&#60;</xsl:text>/a<xsl:text disable-output-escaping="yes">&#62;</xsl:text>
                      <xsl:text disable-output-escaping="yes">&#60;</xsl:text>a href="http://www.amazon.com/o/dt/assoc/tg/stores/offering/list/-/<xsl:value-of select="ProductInfo/Details/Asin"/>/new"<xsl:text disable-output-escaping="yes">&#62;</xsl:text><img height="23" alt="all" src="http://g-images.amazon.com/images/G/01/offerings/mtt-new.gif" border="0"/>
<xsl:text disable-output-escaping="yes">&#60;</xsl:text>/a<xsl:text disable-output-escaping="yes">&#62;</xsl:text>
<xsl:text disable-output-escaping="yes">&#60;</xsl:text>a href="http://www.amazon.com/o/dt/assoc/tg/stores/offering/list/-/<xsl:value-of select="ProductInfo/Details/Asin"/>/used"<xsl:text disable-output-escaping="yes">&#62;</xsl:text><img height="23" alt="all" src="http://g-images.amazon.com/images/G/01/offerings/mtt-used.gif" border="0"/>
<xsl:text disable-output-escaping="yes">&#60;</xsl:text>/a<xsl:text disable-output-escaping="yes">&#62;</xsl:text>
<xsl:text disable-output-escaping="yes">&#60;</xsl:text>a href="http://www.amazon.com/o/dt/assoc/tg/stores/offering/list/-/<xsl:value-of select="ProductInfo/Details/Asin"/>/collectible"<xsl:text disable-output-escaping="yes">&#62;</xsl:text><img height="23" alt="all" src="http://g-images.amazon.com/images/G/01/offerings/mtt-collectible.gif"  border="0"/>
<xsl:text disable-output-escaping="yes">&#60;</xsl:text>/a<xsl:text disable-output-escaping="yes">&#62;</xsl:text>
<xsl:text disable-output-escaping="yes">&#60;</xsl:text>a href="http://www.amazon.com/o/dt/assoc/tg/stores/offering/list/-/<xsl:value-of select="ProductInfo/Details/Asin"/>/sell-yours-here"<xsl:text disable-output-escaping="yes">&#62;</xsl:text><img height="23" alt="all" src="http://g-images.amazon.com/images/G/01/offerings/mtt-sell-yours-here.gif" border="0"/>
<xsl:text disable-output-escaping="yes">&#60;</xsl:text>/a<xsl:text disable-output-escaping="yes">&#62;</xsl:text>
                    </nobr></td>
                  <td width="100%" bgColor="#666666" height="1">
                    <img height="1" src="http://g-images.amazon.com/images/G/01/baby/nav/spacer1x1.gif" width="1"/></td>
                  <td rowSpan="4">
                    <img height="23" src="http://g-images.amazon.com/images/G/01/nav/3rd-level/right-corner-cap-u.gif" width="6" border="0"/></td>
                </tr>
                <tr>
                  <td bgColor="#cccccc" height="18">
                    <img height="1" src="http://g-images.amazon.com/images/G/01/baby/nav/spacer1x1.gif" width="1"/></td>
                </tr>
                <tr>
                  <td bgColor="#999999" height="1">
                    <img height="1" src="http://g-images.amazon.com/images/G/01/baby/nav/spacer1x1.gif" width="1"/></td>
                </tr>
                <tr>
                  <td bgColor="#ffffff" height="3">
                    <img height="1" src="http://g-images.amazon.com/images/G/01/baby/nav/spacer1x1.gif" width="1"/></td>
                </tr>
              </tbody>
            </table></td>
        </tr>
      </tbody>
    </table>
  </BODY>
</html>
</xsl:template>
</xsl:stylesheet>


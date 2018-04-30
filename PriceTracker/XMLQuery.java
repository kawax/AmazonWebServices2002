/*****************************************************************************
      List of Classes:
        XMLQuery - handle XML requests and response for any kind of search
*****************************************************************************/

import java.lang.*;
import java.net.*;
import java.io.*;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class XMLQuery extends Query {

/*****************************************************************************
      Method name: queryGenerator
      Function: generate the URI (query) for XML request
      Input Parameters: N/A
      Return Values: the URI string (String)
*****************************************************************************/
	public String queryGenerator() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(serverURL);
		buffer.append("?");
		buffer.append("t="); buffer.append(associatesID); buffer.append("&");
		buffer.append("dev-t="); buffer.append(token); buffer.append("&");
		buffer.append(searchType); buffer.append("="); buffer.append(generateMultipleSearchString(searchType, searchValues)); buffer.append("&");
		//buffer.append("mode="); buffer.append(mode); buffer.append("&");
		buffer.append("type="); buffer.append(type); buffer.append("&");
		buffer.append("page="); buffer.append(page); buffer.append("&");
		buffer.append("offer="); buffer.append(offer); buffer.append("&");
		buffer.append("f=xml");

		return new String(buffer);
	}

/*****************************************************************************
      Method name: sendRequest
      Function: send the request to server (both AsinSearch and Exchange
                use this method)
      Input Parameters: the URI string (String)
      Return Values: the response string from server (String)
*****************************************************************************/
	public String sendRequest(String string) throws Exception {
		// send the request through URL
		URL url = new URL(string);

		// receive the response
		URLConnection urlConnection = url.openConnection();
		urlConnection.connect();
    DataInputStream in = new DataInputStream(urlConnection.getInputStream());
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		byte[] buffer = new byte[100];
		do {
			int available = in.read(buffer);
			if(available == -1) break;
			bo.write(buffer, 0, available);
		} while(true);
		return bo.toString();
	}

/*****************************************************************************
      Method name: getExchangeIds
      Function: parse the XML response (from AsinSearch) and collect all
                the exchange ids
      Input Parameters: XML response (String)
      Return Values: collections of exchange ids (String[])
*****************************************************************************/
	public String[] getExchangeIds(String _xml) throws ParserConfigurationException, SAXException, IOException {
		String[] exchangeIds;
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(_xml.getBytes()));
		NodeList nodeList = document.getElementsByTagName("ExchangeId");
		exchangeIds = new String[nodeList.getLength()];
		for(int i = 0; i < nodeList.getLength(); i++) {
			exchangeIds[i] = nodeList.item(i).getFirstChild().getNodeValue();
		}			
		return exchangeIds;
	}

/*****************************************************************************
      Method name: getExchanges
      Function: parse the XML response (from ExchangeSearch) and extract all
                exchange records
      Input Parameters: XML response (String)
      Return Values: exchange records (Exchange[])
*****************************************************************************/
	public Exchange[] getExchanges(String exchangeSearchResult) throws ParserConfigurationException, SAXException, IOException {
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(exchangeSearchResult.getBytes()));
		NodeList nodeList = document.getElementsByTagName("ListingProductDetails");
		// make Exchange object(s)
		Exchange[] exchanges = new Exchange[nodeList.getLength()];
		for(int i = 0; i < nodeList.getLength(); i++) {
			exchanges[i] = new Exchange();
			NodeList tagNodeList = nodeList.item(i).getChildNodes();
			for(int j = 0; j < tagNodeList.getLength(); j++) {
				if(tagNodeList.item(j).getNodeName().equals("ExchangeId"))
					exchanges[i].exchangeId                = tagNodeList.item(j).getChildNodes().item(0).getNodeValue();
				if(tagNodeList.item(j).getNodeName().equals("ListingId"))
					exchanges[i].listingId                 = tagNodeList.item(j).getChildNodes().item(0).getNodeValue();
				if(tagNodeList.item(j).getNodeName().equals("ExchangeTitle"))
					exchanges[i].exchangeTitle             = tagNodeList.item(j).getChildNodes().item(0).getNodeValue();
				if(tagNodeList.item(j).getNodeName().equals("ExchangePrice"))
					exchanges[i].exchangePrice             = tagNodeList.item(j).getChildNodes().item(0).getNodeValue();
				if(tagNodeList.item(j).getNodeName().equals("ExchangeAsin"))
					exchanges[i].exchangeAsin              = tagNodeList.item(j).getChildNodes().item(0).getNodeValue();
				if(tagNodeList.item(j).getNodeName().equals("ExchangeEndDate"))
					exchanges[i].exchangeEndDate           = tagNodeList.item(j).getChildNodes().item(0).getNodeValue();
				if(tagNodeList.item(j).getNodeName().equals("ExchangeSellerId"))
					exchanges[i].exchangeSellerId          = tagNodeList.item(j).getChildNodes().item(0).getNodeValue();
				if(tagNodeList.item(j).getNodeName().equals("ExchangeSellerNickname"))
					exchanges[i].exchangeSellerNickname    = tagNodeList.item(j).getChildNodes().item(0).getNodeValue();
				if(tagNodeList.item(j).getNodeName().equals("ExchangeStartDate"))
					exchanges[i].exchangeStartDate         = tagNodeList.item(j).getChildNodes().item(0).getNodeValue();
				if(tagNodeList.item(j).getNodeName().equals("ExchangeStatus"))
					exchanges[i].exchangeStatus            = tagNodeList.item(j).getChildNodes().item(0).getNodeValue();
				if(tagNodeList.item(j).getNodeName().equals("ExchangeQuantity"))
					exchanges[i].exchangeQuantity          = tagNodeList.item(j).getChildNodes().item(0).getNodeValue();
				if(tagNodeList.item(j).getNodeName().equals("ExchangeQuantityAllocated"))
					exchanges[i].exchangeQuantityAllocated = tagNodeList.item(j).getChildNodes().item(0).getNodeValue();
				if(tagNodeList.item(j).getNodeName().equals("ExchangeFeaturedCategory"))
					exchanges[i].exchangeFeaturedCategory  = tagNodeList.item(j).getChildNodes().item(0).getNodeValue();
				if(tagNodeList.item(j).getNodeName().equals("ExchangeCondition"))
					exchanges[i].exchangeCondition         = tagNodeList.item(j).getChildNodes().item(0).getNodeValue();
				if(tagNodeList.item(j).getNodeName().equals("ExchangeConditionType"))
					exchanges[i].exchangeConditionType     = tagNodeList.item(j).getChildNodes().item(0).getNodeValue();
				if(tagNodeList.item(j).getNodeName().equals("ExchangeAvailability"))
					exchanges[i].exchangeAvailability      = tagNodeList.item(j).getChildNodes().item(0).getNodeValue();
				if(tagNodeList.item(j).getNodeName().equals("ExchangeOfferingType"))
					exchanges[i].exchangeOfferingType      = tagNodeList.item(j).getChildNodes().item(0).getNodeValue();
				if(tagNodeList.item(j).getNodeName().equals("ExchangeSellerState"))
					exchanges[i].exchangeSellerState       = tagNodeList.item(j).getChildNodes().item(0).getNodeValue();
				if(tagNodeList.item(j).getNodeName().equals("ExchangeSellerCountry"))
					exchanges[i].exchangeSellerCountry     = tagNodeList.item(j).getChildNodes().item(0).getNodeValue();
				if(tagNodeList.item(j).getNodeName().equals("ExchangeSellerRating"))
					exchanges[i].exchangeSellerRating      = tagNodeList.item(j).getChildNodes().item(0).getNodeValue();
			}
		}
		return exchanges;

	}

}
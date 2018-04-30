/*****************************************************************************
      List of Classes:
        SoapQuery - handle soap requests and response for any kind of search
*****************************************************************************/

import java.net.URL;
import java.net.MalformedURLException; 
import javax.xml.rpc.ServiceException; 
import java.rmi.RemoteException;


public class SoapQuery extends Query
{
 	public SoapQuery()
 	{
 	}

/*****************************************************************************
      Method name: sendAsinRequest
      Function: send the Asin Soap request to server
      Input Parameters: N/A
      Return Values: the response object in ListingProductDetails (Object)
*****************************************************************************/
 	public Object sendAsinRequest() throws MalformedURLException, ServiceException, RemoteException
 	{
 		com.amazon.soap.axis.AmazonSearchService service = new com.amazon.soap.axis.AmazonSearchServiceLocator();
		com.amazon.soap.axis.AmazonSearchPort port = service.getAmazonSearchPort(new URL(serverURL));
		com.amazon.soap.axis.AsinRequest request = new com.amazon.soap.axis.AsinRequest();

		request.setAsin(generateMultipleSearchString(searchType, searchValues));
		request.setTag(associatesID);
 		request.setType(type);
 		request.setDevtag(token);
		request.setOffer(offer);
		request.setOfferpage(page);

 		return port.asinSearchRequest(request);
 	}

/*****************************************************************************
      Method name: sendExchangeRequest
      Function: send the Exchange Soap request to server
      Input Parameters: N/A
      Return Values: the response object (Object)
*****************************************************************************/
 	public Object sendExchangeRequest(String exchangeId) throws MalformedURLException, ServiceException, RemoteException
 	{
 		com.amazon.soap.axis.AmazonSearchService service = new com.amazon.soap.axis.AmazonSearchServiceLocator();
		com.amazon.soap.axis.AmazonSearchPort port = service.getAmazonSearchPort(new URL(serverURL));
		com.amazon.soap.axis.ExchangeRequest request = new com.amazon.soap.axis.ExchangeRequest();

		request.setExchange_Id(exchangeId);
		request.setTag(associatesID);
 		request.setType(type);
 		request.setDevtag(token);
 		return port.exchangeSearchRequest(request);
 	}

/*****************************************************************************
      Method name: getExchanges
      Function: return all the exchange records in the object array
                (The object array is come from the Asin Soap response)
      Input Parameters: the Asin Soap response (Object[])
      Return Values: the exchange records (Exchange[])
*****************************************************************************/
	public Exchange[] getExchanges(Object[] listingProductDetails) {
		Exchange[] exchanges = new Exchange[listingProductDetails.length];
		for(int i = 0; i < listingProductDetails.length; i++) {
			exchanges[i] = new Exchange();
			exchanges[i].exchangeId = ((com.amazon.soap.axis.ListingProductDetails)listingProductDetails[i]).getExchangeId();
			exchanges[i].listingId = ((com.amazon.soap.axis.ListingProductDetails)listingProductDetails[i]).getListingId();
			exchanges[i].exchangeTitle = ((com.amazon.soap.axis.ListingProductDetails)listingProductDetails[i]).getExchangeTitle();
			exchanges[i].exchangePrice = ((com.amazon.soap.axis.ListingProductDetails)listingProductDetails[i]).getExchangePrice();
			exchanges[i].exchangeAsin = ((com.amazon.soap.axis.ListingProductDetails)listingProductDetails[i]).getExchangeAsin();
			exchanges[i].exchangeEndDate = ((com.amazon.soap.axis.ListingProductDetails)listingProductDetails[i]).getExchangeEndDate();
			exchanges[i].exchangeSellerId = ((com.amazon.soap.axis.ListingProductDetails)listingProductDetails[i]).getExchangeSellerId();
			exchanges[i].exchangeSellerNickname = ((com.amazon.soap.axis.ListingProductDetails)listingProductDetails[i]).getExchangeSellerNickname();
			exchanges[i].exchangeStartDate = ((com.amazon.soap.axis.ListingProductDetails)listingProductDetails[i]).getExchangeStartDate();
			exchanges[i].exchangeStatus = ((com.amazon.soap.axis.ListingProductDetails)listingProductDetails[i]).getExchangeStatus();
			exchanges[i].exchangeQuantity = ((com.amazon.soap.axis.ListingProductDetails)listingProductDetails[i]).getExchangeQuantity();
			exchanges[i].exchangeQuantityAllocated = ((com.amazon.soap.axis.ListingProductDetails)listingProductDetails[i]).getExchangeQuantityAllocated();
			exchanges[i].exchangeFeaturedCategory = ((com.amazon.soap.axis.ListingProductDetails)listingProductDetails[i]).getExchangeFeaturedCategory();
			exchanges[i].exchangeCondition = ((com.amazon.soap.axis.ListingProductDetails)listingProductDetails[i]).getExchangeCondition();
			exchanges[i].exchangeConditionType = ((com.amazon.soap.axis.ListingProductDetails)listingProductDetails[i]).getExchangeConditionType();
			exchanges[i].exchangeAvailability = ((com.amazon.soap.axis.ListingProductDetails)listingProductDetails[i]).getExchangeAvailability();
			exchanges[i].exchangeOfferingType = ((com.amazon.soap.axis.ListingProductDetails)listingProductDetails[i]).getExchangeOfferingType();
			exchanges[i].exchangeSellerState = ((com.amazon.soap.axis.ListingProductDetails)listingProductDetails[i]).getExchangeSellerState();
			exchanges[i].exchangeSellerCountry = ((com.amazon.soap.axis.ListingProductDetails)listingProductDetails[i]).getExchangeSellerCountry();
			exchanges[i].exchangeSellerRating = ((com.amazon.soap.axis.ListingProductDetails)listingProductDetails[i]).getExchangeSellerRating();
		}
		return exchanges;
	}
}

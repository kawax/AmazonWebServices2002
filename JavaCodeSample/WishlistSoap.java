import java.net.URL;
import java.net.MalformedURLException; 
import javax.xml.rpc.ServiceException; 
import java.rmi.RemoteException; 

public class WishlistSoap extends AbstractSoapQuery
{
 	public WishlistSoap()
 	{
 		super();
		this.parameters.put("Host","http://soap.amazon.com/onca/soap");
 		this.parameters.put("WishListId","");
 		this.parameters.put("Page","");
 		this.parameters.put("Tag","");
 		this.parameters.put("Type","lite");
 		this.parameters.put("Dev-Tag","");
 		//this.parameters.put("Version","");
 	}

 	public Object issueRequest() throws MalformedURLException, ServiceException, RemoteException
 	{
		com.amazon.soap.axis.AmazonSearchService service = new com.amazon.soap.axis.AmazonSearchServiceLocator();
		com.amazon.soap.axis.AmazonSearchPort port = service.getAmazonSearchPort(new URL((String)this.parameters.get("Host")));
		com.amazon.soap.axis.WishlistRequest request = new com.amazon.soap.axis.WishlistRequest();


 		request.setWishlist_Id((String)this.parameters.get("WishListId"));
 		request.setPage((String)this.parameters.get("Page"));
		request.setTag((String)this.parameters.get("Tag"));
 		request.setType((String)this.parameters.get("Type"));
 		request.setDevtag((String)this.parameters.get("Dev-Tag"));
 		//request.setVersion((String)this.parameters.get("Version"));
 		this.result = port.wishlistSearchRequest(request);
 		return this.result;
 	}
	
}

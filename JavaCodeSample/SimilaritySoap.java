import java.net.URL;
import java.net.MalformedURLException; 
import javax.xml.rpc.ServiceException; 
import java.rmi.RemoteException; 

public class SimilaritySoap extends AbstractSoapQuery
{
 	public SimilaritySoap()
 	{
 		super();
		this.parameters.put("Host","http://soap.amazon.com/onca/soap");
 		this.parameters.put("ASIN","");
 		this.parameters.put("Page","");
 		this.parameters.put("Mode","");
 		this.parameters.put("Tag","");
 		this.parameters.put("Type","lite");
 		this.parameters.put("Dev-Tag","");
 		//this.parameters.put("Version","");
 	}

 	public Object issueRequest() throws MalformedURLException, ServiceException, RemoteException
 	{
		com.amazon.soap.axis.AmazonSearchService service = new com.amazon.soap.axis.AmazonSearchServiceLocator();
		com.amazon.soap.axis.AmazonSearchPort port = service.getAmazonSearchPort(new URL((String)this.parameters.get("Host")));
		com.amazon.soap.axis.SimilarityRequest request = new com.amazon.soap.axis.SimilarityRequest();


 		request.setAsin((String)this.parameters.get("ASIN"));
 		//request.setMode((String)this.parameters.get("Mode"));
		request.setTag((String)this.parameters.get("Tag"));
 		request.setType((String)this.parameters.get("Type"));
 		request.setDevtag((String)this.parameters.get("Dev-Tag"));
 		//request.setVersion((String)this.parameters.get("Version"));

 		this.result = port.similaritySearchRequest(request);
 		return this.result;
 	}
	
}

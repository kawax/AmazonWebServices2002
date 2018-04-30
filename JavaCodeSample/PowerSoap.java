import java.net.URL;
import java.net.MalformedURLException; 
import javax.xml.rpc.ServiceException; 
import java.rmi.RemoteException; 

public class PowerSoap extends AbstractSoapQuery
{
 	public PowerSoap()
 	{
 		super();
		this.parameters.put("Host","http://soap.amazon.com/onca/soap");
 		this.parameters.put("Author","");
 		this.parameters.put("Pub Date","");
 		this.parameters.put("Subject","");
 		this.parameters.put("Mode","");
 		this.parameters.put("Type","lite");
 		this.parameters.put("Dev-Tag","");
 		this.parameters.put("Tag","");
 		//this.parameters.put("Version","");
 	}

 	public Object issueRequest() throws MalformedURLException, ServiceException, RemoteException
 	{
 		com.amazon.soap.axis.AmazonSearchService service = new com.amazon.soap.axis.AmazonSearchServiceLocator();
		com.amazon.soap.axis.AmazonSearchPort port = service.getAmazonSearchPort(new URL((String)this.parameters.get("Host")));
 		com.amazon.soap.axis.PowerRequest request = new com.amazon.soap.axis.PowerRequest();

		request.setPower(getPowerString());
		request.setTag((String)this.parameters.get("Tag"));
 		request.setType((String)this.parameters.get("Type"));
 		request.setDevtag((String)this.parameters.get("Dev-Tag"));
 		request.setMode((String)this.parameters.get("Mode"));
 		//request.setVersion((String)this.parameters.get("Version"));

 		this.result = port.powerSearchRequest(request);
 		return this.result;
 	}

	private String getPowerString()
	{
		StringBuffer powerString = new StringBuffer();
		String author = (String)this.parameters.get("Author");
		String pubdate = (String)this.parameters.get("Pub Date");
		String subject = (String)this.parameters.get("Subject");

		if(!author.equals(""))
			powerString.append("author:" + author);

		if(!author.equals("") && !pubdate.equals(""))
			powerString.append(" and " + "pubdate:" + pubdate);
		else if(author.equals("") && !pubdate.equals(""))
			powerString.append("pubdate:" + pubdate);

		if((!author.equals("") || !pubdate.equals("")) && !subject.equals(""))
			powerString.append(" and " + "subject:" + subject);
		else if(author.equals("") && pubdate.equals("") && !subject.equals(""))
			powerString.append("subject:" + subject);

		return new String(powerString);
	}
	
}
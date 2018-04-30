import java.awt.Component;

import java.net.MalformedURLException; 
import javax.xml.rpc.ServiceException; 
import java.rmi.RemoteException; 

public interface SoapQuery
{
	/**
	 *Issues the SOAP request and gets the response.  Returns the result.
	 **/
	public Object issueRequest() throws MalformedURLException, ServiceException, RemoteException;

	/**
	 *Get the result from the last invocation.
	 **/
	public Object getResult();
	
	public Parameter[] getParameters();
	public void setParameter(String name_, String value_);
	public void setParameters(Parameter[] parameters_);

	/**
	 * Tells the XmlQuery to query the user for inputs
	 **/
	public Component createWidget(SoapQuery.Listener listener_);

	public interface Listener
	{
		/**
		 * Called by SoapQuery when its Widgets are done
		 **/
		public void finished(SoapQuery query_);
	}

	
	/**
	 * Encapsulates a parameter and it's value. Much like Map.Entry.
	 **/
	public class Parameter
	{
		protected String value;
		protected String name;

		public Parameter(String name_, String value_)
		{
			this.name = name_;
			this.value = value_;
		}

		public String getName()
		{
			return this.name;
		}
		public String getValue()
		{
			return this.value;
		}
	}
}

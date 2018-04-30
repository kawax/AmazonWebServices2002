import java.awt.Component;

import java.io.IOException;

/**
 * Interface to be implemented by classes that represent a query to a server.
 * It provides a user interface with which it acquires user input, and it
 * generates a query with the user input.
 **/

public interface XmlQuery
{
	/**
	 * Retrieves the title (short description) about the query
	 **/
	public String getTitle();

	/**
	 * Retrieves the description about the query
	 **/
	public String getDescription();

	/**
	 * Retrieves the data for this query
	 **/
	public String getHttpRequest();

	/**
	 * Retrieves the response that this query got from the server
	 **/
	public String getHttpResponse();

	/**
	 * Retrieves the body of the response (without http header)that this query got from the server
	 **/
	public String getHttpBody();

	/**
	 * Sends the request to the server and blocks until the response is retrieved
	 **/
	public void issueRequest() throws IOException, SecurityException;

	/**
	 * Tells the XmlQuery to query the user for inputs
	 **/
	public Component createWidget(Listener listener_);
	
	public interface Listener
	{
		/**
		 * Called by XmlQuery when its Widgets are done
		 **/
		public void finished(XmlQuery query_);
	}
}

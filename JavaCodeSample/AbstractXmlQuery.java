
import java.awt.Container;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import java.net.Socket;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

/**
 * Interface to be implemented by classes that represent a query to a server.
 * It provides a user interface with which it acquires user input, and it
 * generates a query with the user input.
 **/

public abstract class AbstractXmlQuery implements XmlQuery
{
	public static String HTTPHEADER = " HTTP/1.0\nHost: ";
	public static String HTTPHEADER2= "\nAccept: text/html, text/plain, text/sgml, text/xml\nUser-Agent: Amazon Java Xml Client/1\n\n";

	protected String response="";

	protected String host="xml.amazon.com:80"; //host:port
	//protected String path="/onca/xml";
	//protected String host="angkao.desktop:3040"; //host:port
	//protected String host="raji.desktop:2000"; //host:port
	protected String path="/onca/xml";

	protected String version="1.0";
	protected String tag="webservices-20";

	protected String type="lite";
	protected String format="xml";
	protected String devtoken="";
	
	
	public String getHttpRequest()
	{
		return getGetString()+HTTPHEADER+host+HTTPHEADER2;
	}

	public String convertWhiteSpace(String oldString) {
		return oldString.replaceAll(" ", "%20");
	}

	public String getHttpResponse()
	{
		return this.response;
	}

	public String getHttpBody()
	{
		//Tries to find the double line returns that exists between the header and body
		int index = this.response.indexOf("\n\n")+2;
		if(index==1)
			index = this.response.indexOf("\n\r\n\r")+4;
		else
		{
			int temp = this.response.indexOf("\n\r\n\r")+4;
			index = (temp < index) ? temp : index;
		}
		if(index==3)
			index = this.response.indexOf("\r\n\r\n")+4;
		else
		{
			int temp = this.response.indexOf("\r\n\r\n")+4;
			index = (temp < index) ? temp : index;
		}
		if(index==3)
			return "";
		//Chops off the header and returns the body
		return this.response.substring(index);
	}

	protected String getGetString()
	{
		return "GET "+path+"?t="+tag+"&dev-t="+devtoken+"&type="+type+"&f="+format;
	}


	public void issueRequest() throws IOException, SecurityException
	{
		System.out.println("Opening connection");
		//Support host:port
		String[] hostPort = this.host.split(":",2);
		Socket socket;
		try
		{
			socket = new Socket(hostPort[0], (hostPort.length > 1) ? Integer.parseInt(hostPort[1]) : 80);
		}
		catch(NumberFormatException e)
		{
			System.out.println("Illegal Port Number");
			socket = new Socket(hostPort[0], 80);
		}
		
		//Write all the data (request)
		OutputStream output=socket.getOutputStream();
		System.out.println("Sending...");
		output.write(getHttpRequest().getBytes());
		InputStream input=socket.getInputStream();

		//Read the response
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		System.out.println("Receiving...");
		byte[] buffer = new byte[1000];
		int amount=0;
		while(amount != -1)
		{
			result.write(buffer, 0, amount);
			amount = input.read(buffer);
		}
		this.response =  result.toString();
	}
	
	public Component createWidget(XmlQuery.Listener listener_)
	{
		return new DataInput(listener_);
	}

	protected class DataInput extends Container
	{
		protected JTextField version;
		protected JTextField tag;
		protected JTextField host;
		protected JTextField path;
		protected JTextField format;
		protected JTextField devtoken;

		protected JRadioButton liteResult;
		protected JRadioButton heavyResult;
		protected ButtonGroup resultGroup;
		
		protected JButton ok;
		
		protected XmlQuery.Listener listener;
		
		public DataInput(XmlQuery.Listener listener_)
		{
			this.listener = listener_;
			
			JLabel label;
			Container container;

			//Create the widgets for each field
			//host
			this.host = new JTextField();
			this.host.setText(AbstractXmlQuery.this.host);
			label = new JLabel("Host");
			label.setLabelFor(this.host);
			container = new Container();
			container.add(label);
			container.add(this.host);
			container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
			add(container);
			//path
			this.path = new JTextField();
			this.path.setText(AbstractXmlQuery.this.path);
			label = new JLabel("Path");
			label.setLabelFor(this.path);
			container = new Container();
			container.add(label);
			container.add(this.path);
			container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
			add(container);
			//version
			this.version = new JTextField();
			this.version.setText(AbstractXmlQuery.this.version);
			label = new JLabel("Version");
			label.setLabelFor(this.version);
			container = new Container();
			container.add(label);
			container.add(this.version);
			container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
			add(container);
			//tag
			this.tag = new JTextField();
			this.tag.setText(AbstractXmlQuery.this.tag);
			label = new JLabel("Tag");
			label.setLabelFor(this.tag);
			container = new Container();
			container.add(label);
			container.add(this.tag);
			container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
			add(container);
			//format
			this.format = new JTextField();
			this.format.setText(AbstractXmlQuery.this.format);
			label = new JLabel("Format");
			label.setLabelFor(this.format);
			container = new Container();
			container.add(label);
			container.add(this.format);
			container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
			add(container);
			//devtoken
			this.devtoken = new JTextField();
			this.devtoken.setText(AbstractXmlQuery.this.devtoken);
			label = new JLabel("Developer Token");
			label.setLabelFor(this.devtoken);
			container = new Container();
			container.add(label);
			container.add(this.devtoken);
			container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
			add(container);


			//Type
			this.liteResult = new JRadioButton("Lite");
			this.heavyResult = new JRadioButton("Heavy");
			this.resultGroup = new ButtonGroup();			
			this.resultGroup.add(this.liteResult);
			this.resultGroup.add(this.heavyResult);
			add(this.liteResult);
			add(this.heavyResult);
			if(AbstractXmlQuery.this.type.equalsIgnoreCase("lite"))
			{
				this.liteResult.setSelected(true);
			}
			else
			{
				this.heavyResult.setSelected(true);
			}

			this.ok = new JButton("Send");
			this.ok.setActionCommand("Send");
			this.ok.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						if(e.getActionCommand().equals("Send"))
						{
							//Sets the fields when the send button is clicked.
							AbstractXmlQuery.this.host=AbstractXmlQuery.DataInput.this.host.getText();
							AbstractXmlQuery.this.path=AbstractXmlQuery.DataInput.this.path.getText();
							
							AbstractXmlQuery.this.version=AbstractXmlQuery.DataInput.this.version.getText();
							AbstractXmlQuery.this.tag=AbstractXmlQuery.DataInput.this.tag.getText();
							
							AbstractXmlQuery.this.type=(AbstractXmlQuery.DataInput.this.liteResult.isSelected()) ? "lite" : "heavy";
							AbstractXmlQuery.this.format=AbstractXmlQuery.DataInput.this.format.getText();
							AbstractXmlQuery.this.devtoken=AbstractXmlQuery.DataInput.this.devtoken.getText();
							
							AbstractXmlQuery.DataInput.this.dialogFinished();
						}
					}
				});
			add(this.ok);
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		}
		
		protected void dialogFinished()
		{
			AbstractXmlQuery.DataInput.this.listener.finished(AbstractXmlQuery.this);
		}
	}
}

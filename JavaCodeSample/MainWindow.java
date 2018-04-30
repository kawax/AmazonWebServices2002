import java.awt.Container;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;


import java.util.HashMap;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;




/**
 * Main class for the application.  Responsible for setting up the layout and
 * managing the functionality.
 **/
public class MainWindow extends JFrame
{

	Container soapPane;
	Container soapQueryContainer; /**Container that has the widgets for a query*/
	JComboBox soapQuerySelect;
	Container soapInstruction;
	Container soapResponse;
	HashMap soapQueries;

	Container xmlPane;
	Container xmlQueryContainer; /**Container that has the widgets for a query*/
	JComboBox xmlQuerySelect; /**Drop-down that contains the list of possible queries*/
	Container xmlInstruction; /**Container that holds instructions about how to make a query*/
	Container xmlRequest; /**Not used*/
	Container xmlResponse; /**Container that holds the tree views of the response*/
	HashMap xmlQueries;
	
	public MainWindow()
	{
		init();
	}
	
	private void init()
	{
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		//Tabbed Panes2
		JTabbedPane tabbedPane = new JTabbedPane();
		getContentPane().add(tabbedPane);
		
		//XML Pane
		this.xmlPane = new Container();
		this.xmlPane.setLayout(new BoxLayout(this.xmlPane, BoxLayout.Y_AXIS));
		tabbedPane.addTab("XML", null, this.xmlPane, "Send XML Requests");
		//Query List drop-down
		this.xmlQuerySelect = new JComboBox(new String[]{"Instruction", "Browse Node", "Keyword Search", "Power Search", "ASIN / UPC Search", "Exchange Search", "Wishlist Search", "ListMania Search", "Seller Search", "Seller Profile Search", "Real Power Search"});
		this.xmlQuerySelect.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					MainWindow.this.xmlQueryContainer.removeAll();
					MainWindow.this.xmlQueryContainer.add((Component)MainWindow.this.xmlQueries.get(MainWindow.this.xmlQuerySelect.getSelectedItem()));
					MainWindow.this.xmlPane.doLayout();
					MainWindow.this.xmlQueryContainer.repaint();
				}
			});
		this.xmlPane.add(this.xmlQuerySelect);

		this.xmlQueryContainer = new Container();
		this.xmlQueryContainer.setLayout(new BoxLayout(this.xmlQueryContainer, BoxLayout.Y_AXIS));
		this.xmlPane.add(this.xmlQueryContainer);

//		this.xmlRequest = new Container();
//		this.xmlRequest.setLayout(new BoxLayout(this.xmlRequest, BoxLayout.Y_AXIS));
//		this.xmlPane.add(this.xmlRequest);

		this.xmlResponse = new Container();
		this.xmlResponse.add(new JScrollPane(new JTree(new DefaultMutableTreeNode("result"))));
		this.xmlResponse.setLayout(new BoxLayout(this.xmlResponse, BoxLayout.Y_AXIS));
		this.xmlPane.add(this.xmlResponse);
		//Instructioins for how to use the system*/
		this.xmlInstruction = new Container();
		this.xmlInstruction.setLayout(new BoxLayout(this.xmlInstruction, BoxLayout.Y_AXIS));
		this.xmlInstruction.add(new JTextField("Select an XML query from the drop down list"));
		this.xmlQueryContainer.add(this.xmlInstruction);
		//Associate query names to the widgets that manage them
		this.xmlQueries = new HashMap();
		this.xmlQueries.put("Instruction", this.xmlInstruction); //This is why this chunk is down here.
		this.xmlQueries.put("Browse Node", new BrowseNodeSearch().createWidget(new QueryListener()));
		this.xmlQueries.put("Keyword Search", new KeywordSearch().createWidget(new QueryListener()));
		this.xmlQueries.put("Power Search", new PowerSearch().createWidget(new QueryListener()));
		this.xmlQueries.put("ASIN / UPC Search", new AsinSearch().createWidget(new QueryListener()));
		this.xmlQueries.put("Exchange Search", new ExchangeSearch().createWidget(new QueryListener()));
		this.xmlQueries.put("Wishlist Search", new WishlistSearch().createWidget(new QueryListener()));
		this.xmlQueries.put("ListMania Search", new ListManiaSearch().createWidget(new QueryListener()));
		this.xmlQueries.put("Seller Search", new SellerSearch().createWidget(new QueryListener()));
		this.xmlQueries.put("Seller Profile Search", new SellerProfileSearch().createWidget(new QueryListener()));
		this.xmlQueries.put("Real Power Search", new RealPowerSearch().createWidget(new QueryListener()));

		//SOAP Pane
		this.soapPane = new Container();
		this.soapPane.setLayout(new BoxLayout(this.soapPane, BoxLayout.Y_AXIS));
		tabbedPane.addTab("SOAP", null, this.soapPane, "Send SOAP Requests");

		this.soapQuerySelect = new JComboBox(new String[]{"Instruction", "Browse Node Search", "Keyword Search", "List Search", "ASIN Search", "UPC Search", 
																											"Artist Search", "Author Search", "Actor Search", "Director Search", "Similarity Search", "Manufacturer Search", "Wishlist Search", "Exchange Search", "Seller Profile Search", "Seller Search", "List Mania Search", "Real Power Search"});
		this.soapQuerySelect.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					MainWindow.this.soapQueryContainer.removeAll();
 					MainWindow.this.soapQueryContainer.add((Component)MainWindow.this.soapQueries.get(MainWindow.this.soapQuerySelect.getSelectedItem()));
					MainWindow.this.soapPane.doLayout();
					MainWindow.this.soapQueryContainer.repaint();
				}
			});
		this.soapPane.add(this.soapQuerySelect);

		this.soapQueryContainer = new Container();
		this.soapQueryContainer.setLayout(new BoxLayout(this.soapQueryContainer, BoxLayout.Y_AXIS));
		this.soapPane.add(this.soapQueryContainer);
		
		this.soapResponse = new Container();
		this.soapResponse.add(new JScrollPane(new JTree(new DefaultMutableTreeNode("result"))));
		this.soapResponse.setLayout(new BoxLayout(this.soapResponse, BoxLayout.Y_AXIS));
		this.soapPane.add(this.soapResponse);

		//Instructions for how to use the system*/
		this.soapInstruction = new Container();
		this.soapInstruction.setLayout(new BoxLayout(this.soapInstruction, BoxLayout.Y_AXIS));
		this.soapInstruction.add(new JTextField("Select a SOAP query from the drop down list"));
		this.soapQueryContainer.add(soapInstruction);

		this.soapQueries = new HashMap();
		this.soapQueries.put("Instruction", this.soapInstruction);
		this.soapQueries.put("Browse Node Search", new BrowseNodeSoap().createWidget(new SoapListener()));
		this.soapQueries.put("Keyword Search", new KeywordSearchSoap().createWidget(new SoapListener()));
		this.soapQueries.put("List Search", new ListSoap().createWidget(new SoapListener()));
		this.soapQueries.put("ASIN Search", new AsinSoap().createWidget(new SoapListener()));
		this.soapQueries.put("UPC Search", new UpcSoap().createWidget(new SoapListener()));
		this.soapQueries.put("Artist Search", new ArtistSoap().createWidget(new SoapListener()));
		this.soapQueries.put("Author Search", new AuthorSoap().createWidget(new SoapListener()));
		this.soapQueries.put("Actor Search", new ActorSoap().createWidget(new SoapListener()));
		this.soapQueries.put("Director Search", new DirectorSoap().createWidget(new SoapListener()));
		this.soapQueries.put("Similarity Search", new SimilaritySoap().createWidget(new SoapListener()));
		this.soapQueries.put("Manufacturer Search", new ManufacturerSoap().createWidget(new SoapListener()));
		this.soapQueries.put("Wishlist Search", new WishlistSoap().createWidget(new SoapListener()));
		this.soapQueries.put("Exchange Search", new ExchangeSoap().createWidget(new SoapListener()));
		this.soapQueries.put("Seller Profile Search", new SellerProfileSoap().createWidget(new SoapListener()));
		this.soapQueries.put("Seller Search", new SellerSoap().createWidget(new SoapListener()));
		this.soapQueries.put("List Mania Search", new ListManiaSoap().createWidget(new SoapListener()));
		this.soapQueries.put("Real Power Search", new PowerSoap().createWidget(new SoapListener()));
		setSize(350, 600);
		show();
	}


	/**
	 * This class is passed on to XmlQuery's.
	 **/
	private class QueryListener implements XmlQuery.Listener
	{
		public void finished(XmlQuery query_)
		{
			try
			{
				//Does the following when the user clicks "Send" on the dialog.
				System.out.println(query_.getHttpRequest());
				query_.issueRequest();
				System.out.println(query_.getHttpBody());
				MainWindow.this.xmlResponse.removeAll();
				MainWindow.this.xmlResponse.add(new JScrollPane(XmlDisplay.createTreeView(query_.getHttpBody())));
				MainWindow.this.xmlPane.doLayout();
				MainWindow.this.xmlResponse.repaint();
			}
			catch(IOException e)
			{
				System.out.println("Experienced IOException");
				System.out.println(e);
			}
			catch(SecurityException e)
			{
				System.out.println("Experienced SecurityException");
				System.out.println(e);
			}
			catch(ParserConfigurationException e)
			{
				System.out.println("Experienced ParserConfigurationException");
				System.out.println(e);
			}
			catch(SAXException e)
			{
				System.out.println("Experienced SAXException");
				System.out.println(e);
			}
		}
	}
	

	private class SoapListener implements SoapQuery.Listener
	{
		public void finished(SoapQuery query_)
		{
			try
			{
				query_.issueRequest();
				MainWindow.this.soapResponse.removeAll();
				MainWindow.this.soapResponse.add(new JScrollPane(ReflectDisplay.createTreeView("Soap Response",query_.getResult())));
				MainWindow.this.soapPane.doLayout();
				MainWindow.this.soapResponse.repaint();
			}
			catch(java.net.MalformedURLException e)
			{
				System.out.println("Experienced MalformedURLException");
				System.out.println(e);
			}
			catch(javax.xml.rpc.ServiceException e)
			{
				System.out.println("Experienced ServiceException");
				System.out.println(e);
			}
			catch(java.rmi.RemoteException e)
			{
				System.out.println("Experienced RemoteException");
				System.out.println(e);
			}
		}
	}
}


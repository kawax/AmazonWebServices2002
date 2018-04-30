/*****************************************************************************
      List of Classes:
        XmlSoapGUI - the main body of application which handles the GUI
*****************************************************************************/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.tree.*;
import javax.swing.event.*;
import java.util.*;

public class XmlSoapGUI extends JFrame {

	final String[] SEARCH_METHODS = {"AsinSearch"};
	final String[] TYPES = {"heavy"};
	final String[] OFFER = {"Used", "Collectible", "ThirdPartyNew", "Refurbished"};
	final String[] ASIN_TABLE_HEADERS = {"Product", "ASIN Number"};

	final String FILE_LOCATION = "asinRecord.dat";
	final String CSV_FILE_LOCATION = "asinRecord.csv";

	JPanel vendorPanel;
  JLabel vendorLabel, associateIDLabel, tokenLabel,	sellerIdLabel; // labels for user's info
	JTextField vendorText, associateIDText, tokenText, sellerIdText;

	JPanel searchPanel;
	JLabel serverURLLabel, searchLabel, typeLabel, offerLabel, pageLabel;	// label for search parameters
	JComboBox searchCombo, typeCombo, offerCombo;
	JTextField serverURLText, pageText;

	JTable asinTable;
	JLabel asinCountLabel;
	JButton asinCountButton;
	JTextField asinCountText;
	ASINTableModel asinTableModel;
	JScrollPane asinScroll;

	JPanel sendPanel;
	JLabel sendLabel;
	JButton xmlButton, soapButton;

	JPanel resultPanel;
	JTextArea resultTextArea;
	JScrollPane resultPane;

	AsinRecordTable asinRecordTable;



  Color foreColor, backColor, back2Color;

	public XmlSoapGUI() {
		super("AMAZON.COM WebServices Demo in Java");
		init();
	}

	void init() {
	  Container c = getContentPane();
	  c.setLayout(new GridBagLayout());
	  GridBagConstraints gbc = new GridBagConstraints();
	  gbc.fill = GridBagConstraints.BOTH;
	  gbc.insets = new Insets(3,3,3,3);
	
	  foreColor = new Color(248,238,126);
	  backColor = new Color(89,138,174);
	  back2Color = new Color(69,111,141);


		// Vendor Info
		vendorPanel = new JPanel();
		vendorPanel.setLayout(new GridBagLayout());
		vendorPanel.setBackground(backColor);
		vendorPanel.setBorder(BorderFactory.createTitledBorder("Vendor Info"));
    GridBagConstraints vendorGbc = new GridBagConstraints();
    vendorGbc.insets = new Insets(3,3,3,3);

		vendorLabel = new JLabel("Vendor name:");
    vendorLabel.setForeground(foreColor);
    vendorLabel.setHorizontalAlignment(SwingConstants.CENTER);
    vendorGbc.gridx = 0; vendorGbc.gridy = 0;
    vendorPanel.add(vendorLabel, vendorGbc);

		vendorText = new JTextField(20);
		vendorText.setBackground(back2Color);
		vendorText.setForeground(Color.yellow);
		vendorGbc.gridx = 1; vendorGbc.gridy = 0; vendorGbc.weightx = 1.0; vendorGbc.fill = GridBagConstraints.HORIZONTAL;
		vendorPanel.add(vendorText, vendorGbc);	

		associateIDLabel = new JLabel("Associate ID:");
    associateIDLabel.setForeground(foreColor);
    associateIDLabel.setHorizontalAlignment(SwingConstants.CENTER);
    vendorGbc.gridx = 0; vendorGbc.gridy = 1;
    vendorPanel.add(associateIDLabel, vendorGbc);

		associateIDText = new JTextField(20);
		associateIDText.setBackground(back2Color);
		associateIDText.setForeground(Color.yellow);
		vendorGbc.gridx = 1; vendorGbc.gridy = 1; vendorGbc.weightx = 1.0; vendorGbc.fill = GridBagConstraints.HORIZONTAL;
		vendorPanel.add(associateIDText, vendorGbc);	

		tokenLabel = new JLabel("Token:");
    tokenLabel.setForeground(foreColor);
    tokenLabel.setHorizontalAlignment(SwingConstants.CENTER);
    vendorGbc.gridx = 0; vendorGbc.gridy = 2;
    vendorPanel.add(tokenLabel, vendorGbc);

		tokenText = new JTextField(20);
		tokenText.setBackground(back2Color);
		tokenText.setForeground(Color.yellow);
		vendorGbc.gridx = 1; vendorGbc.gridy = 2; vendorGbc.weightx = 1.0; vendorGbc.fill = GridBagConstraints.HORIZONTAL;
		vendorPanel.add(tokenText, vendorGbc);	

		sellerIdLabel = new JLabel("Seller Id:");
    sellerIdLabel.setForeground(foreColor);
    sellerIdLabel.setHorizontalAlignment(SwingConstants.CENTER);
    vendorGbc.gridx = 0; vendorGbc.gridy = 3;
    vendorPanel.add(sellerIdLabel, vendorGbc);

		sellerIdText = new JTextField(20);
		sellerIdText.setBackground(back2Color);
		sellerIdText.setForeground(Color.yellow);
		vendorGbc.gridx = 1; vendorGbc.gridy = 3; vendorGbc.weightx = 1.0; vendorGbc.fill = GridBagConstraints.HORIZONTAL;
		vendorPanel.add(sellerIdText, vendorGbc);	

		// Search Info
		searchPanel = new JPanel();
		searchPanel.setLayout(new GridBagLayout());
		searchPanel.setBackground(backColor);
		searchPanel.setBorder(BorderFactory.createTitledBorder("Search Parameters"));
    GridBagConstraints searchGbc = new GridBagConstraints();
	  searchGbc.fill = GridBagConstraints.BOTH;
    searchGbc.insets = new Insets(3,3,3,3);

		serverURLLabel = new JLabel("Server URL:");
    serverURLLabel.setForeground(foreColor);
    serverURLLabel.setHorizontalAlignment(SwingConstants.CENTER);
    searchGbc.gridx = 0; searchGbc.gridy = 0; searchGbc.gridwidth = 1;
    searchPanel.add(serverURLLabel, searchGbc);

		serverURLText = new JTextField(20);
		serverURLText.setBackground(back2Color);
		serverURLText.setForeground(Color.yellow);
		searchGbc.gridx = 1; searchGbc.gridy = 0; searchGbc.gridwidth = 3;
		searchPanel.add(serverURLText, searchGbc);

		searchLabel = new JLabel("Search Method:");
    searchLabel.setForeground(foreColor);
    searchLabel.setHorizontalAlignment(SwingConstants.CENTER);
    searchGbc.gridx = 0; searchGbc.gridy = 1; searchGbc.gridwidth = 1;
    searchPanel.add(searchLabel, searchGbc);

		searchCombo = new JComboBox(SEARCH_METHODS);
		searchCombo.setBackground(back2Color);
		searchCombo.setForeground(Color.yellow);
    searchGbc.gridx = 1; searchGbc.gridy = 1; searchGbc.gridwidth = 3;
    searchPanel.add(searchCombo, searchGbc);

		asinCountLabel = new JLabel("Number of ASIN Entered:");
    asinCountLabel.setForeground(foreColor);
    asinCountLabel.setHorizontalAlignment(SwingConstants.CENTER);
    searchGbc.gridx = 0; searchGbc.gridy = 2; searchGbc.gridwidth = 1;
    searchPanel.add(asinCountLabel, searchGbc);

		asinCountText = new JTextField(5);
		asinCountText.setBackground(back2Color);
		asinCountText.setForeground(Color.yellow);
    searchGbc.gridx = 1; searchGbc.gridy = 2; searchGbc.gridwidth = 2;
    searchPanel.add(asinCountText, searchGbc);

		asinCountButton = new JButton("Click to Enter Asin");
    asinCountButton.setBackground(back2Color);
    asinCountButton.setForeground(foreColor);
    searchGbc.gridx = 3; searchGbc.gridy = 2; searchGbc.gridwidth = 1;
    searchPanel.add(asinCountButton, searchGbc);

		asinTableModel = new ASINTableModel(ASIN_TABLE_HEADERS, 0);
		asinTableModel.setNumRows(1);
		asinTable = new JTable(asinTableModel); 
    asinTable.setBackground(back2Color);
    asinTable.setForeground(foreColor);
    asinTable.setGridColor(backColor);
		asinTable.setPreferredScrollableViewportSize(new Dimension(100, 150));
    asinTable.getTableHeader().setReorderingAllowed(false);
    asinScroll = new JScrollPane(asinTable);
    asinScroll.setBackground(back2Color);
    asinScroll.setForeground(foreColor);
    searchGbc.gridx = 0; searchGbc.gridy = 3; searchGbc.gridheight = 10; searchGbc.weighty = 1; searchGbc.gridwidth = GridBagConstraints.REMAINDER;
    searchPanel.add(asinScroll, searchGbc);

		typeLabel = new JLabel("Type:");
    typeLabel.setForeground(foreColor);
    typeLabel.setHorizontalAlignment(SwingConstants.CENTER);
    searchGbc.gridx = 0; searchGbc.gridy = 14; searchGbc.gridwidth = 1; searchGbc.gridheight = 1; searchGbc.weighty = 0;
    searchPanel.add(typeLabel, searchGbc);

		typeCombo = new JComboBox(TYPES);
		typeCombo.setBackground(back2Color);
		typeCombo.setForeground(Color.yellow);
    searchGbc.gridx = 1; searchGbc.gridy = 14; searchGbc.gridwidth = 3; searchGbc.fill = GridBagConstraints.HORIZONTAL;
    searchPanel.add(typeCombo, searchGbc);

		offerLabel = new JLabel("Offer:");
    offerLabel.setForeground(foreColor);
    offerLabel.setHorizontalAlignment(SwingConstants.CENTER);
    searchGbc.gridx = 0; searchGbc.gridy = 15; searchGbc.gridwidth = 1;
    searchPanel.add(offerLabel, searchGbc);

		offerCombo = new JComboBox(OFFER);
		offerCombo.setBackground(back2Color);
		offerCombo.setForeground(Color.yellow);
    searchGbc.gridx = 1; searchGbc.gridy = 15; searchGbc.gridwidth = 3;
    searchPanel.add(offerCombo, searchGbc);

		pageLabel = new JLabel("Page (for Exchange):");
    pageLabel.setForeground(foreColor);
    pageLabel.setHorizontalAlignment(SwingConstants.CENTER);
    searchGbc.gridx = 0; searchGbc.gridy = 16; searchGbc.gridwidth = 1;
    searchPanel.add(pageLabel, searchGbc);

		pageText = new JTextField(20);
		pageText.setBackground(back2Color);
		pageText.setForeground(Color.yellow);
		searchGbc.gridx = 1; searchGbc.gridy = 16; searchGbc.gridwidth = 3; searchGbc.weightx = 1.0; searchGbc.fill = GridBagConstraints.HORIZONTAL;
		searchPanel.add(pageText, searchGbc);


		// Send Buttons
		sendPanel = new JPanel();
		sendPanel.setLayout(new GridBagLayout());
		sendPanel.setBackground(backColor);
		sendPanel.setBorder(BorderFactory.createTitledBorder("Send Methods"));
    GridBagConstraints sendGbc = new GridBagConstraints();
		sendGbc.insets = new Insets(3,3,3,3);

		sendLabel = new JLabel("Send Method:");
    sendLabel.setForeground(foreColor);
    sendLabel.setHorizontalAlignment(SwingConstants.CENTER);
    sendGbc.gridx = 0; sendGbc.gridy = 0;
    sendPanel.add(sendLabel, sendGbc);

		xmlButton = new JButton("XML");
    xmlButton.setBackground(back2Color);
    xmlButton.setForeground(foreColor);
    sendGbc.gridx = 1; sendGbc.gridy = 0;
    sendPanel.add(xmlButton, sendGbc);

		soapButton = new JButton("SOAP");
    soapButton.setBackground(back2Color);
    soapButton.setForeground(foreColor);
    sendGbc.gridx = 2; sendGbc.gridy = 0;
    sendPanel.add(soapButton, sendGbc);

		// Result Panel
		resultPanel = new JPanel();
		resultPanel.setLayout(new GridBagLayout());
		resultPanel.setBackground(backColor);
		resultPanel.setBorder(BorderFactory.createTitledBorder("Result"));
    GridBagConstraints resultGbc = new GridBagConstraints();
    resultGbc.insets = new Insets(3,3,3,3);

		resultTextArea = new JTextArea(20, 20);
		resultPane = new JScrollPane(resultTextArea);
		resultPane.setPreferredSize(new Dimension(300,500));
		resultPane.setMinimumSize(new Dimension(300,500));
		resultGbc.gridx = 0; resultGbc.gridy = 0; resultGbc.gridheight = 2;
		resultGbc.gridx = 0; resultGbc.gridy = 0;
		resultPanel.add(resultPane, resultGbc);

		// Overall Outlook
    gbc.gridx = 0; gbc.gridy = 0;
    c.add(vendorPanel, gbc);

    gbc.gridx = 0; gbc.gridy = 1;
    c.add(searchPanel, gbc);

    gbc.gridx = 0; gbc.gridy = 2;
    c.add(sendPanel, gbc);

		gbc.gridx = 1; gbc.gridy = 0; gbc.gridheight = 3; // gbc.weightx = 100; gbc.gridwidth = 10;
		c.add(resultPanel, gbc);

    c.setBackground(backColor);
    setSize(600,700);
		pack();
    centerWindow();

		// Adding Listener
    ButtonHandler handler = new ButtonHandler();
    asinCountButton.addActionListener(handler);
    xmlButton.addActionListener(handler);
    soapButton.addActionListener(handler);

    show();
	}

  private void centerWindow() {
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension window = getSize();
    if (window.width==0)
      return;
    int left = screen.width/2-window.width/2;
    int top = 0;
    if (top<0) top = 0;
    if (left<0) left = 0;
    setLocation(left, top);
  }

	private class ButtonHandler implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			if (e.getSource() == asinCountButton) {
				try {
					int asinCount = Integer.parseInt(asinCountText.getText());
					// expand the rows
					asinTableModel.setNumRows(asinCount);
					for(int i = 0; i < asinCount; i++) {
						asinTableModel.setValueAt((new Integer(i + 1)).toString(), i, 0);
					}
				}
				catch(NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null, "Please enter an integer into the field");
				}
			}
			else if (e.getSource() == xmlButton || e.getSource() == soapButton) {
				Vector asinVector = new Vector();
				// grab all Asin numbers to do AsinSearch
				asinTableModel.fireTableCellUpdated(asinTableModel.getRowCount() - 1, 1);
				for (int i = 0; i < asinTable.getRowCount(); i++) {
					asinVector.addElement(((Vector)asinTableModel.getDataVector().elementAt(i)).elementAt(1));
				}
				// check if the data is properly entered
				if(asinTableModel.getValueAt(asinTableModel.getRowCount() - 1, 1) == null) {
					JOptionPane.showMessageDialog(null, "Please select the table properly");
				}

				try {
					asinRecordTable = new AsinRecordTable();
					asinRecordTable.loadCSV(CSV_FILE_LOCATION);
					asinRecordTable.updateOwnSellerId(sellerIdText.getText());

					if(e.getSource() == xmlButton) {

						XMLQuery xml = new XMLQuery();
		
						xml.setServerURL(serverURLText.getText());
						xml.setAssociatesID(associateIDText.getText());
						xml.setToken(tokenText.getText());
						xml.setSearchType(searchCombo.getSelectedItem().toString());
						xml.setSearchValues(asinVector);
						xml.setType(typeCombo.getSelectedItem().toString());
						xml.setPage(pageText.getText());
						xml.setOffer(offerCombo.getSelectedItem().toString());
		
						String response = xml.sendRequest(xml.queryGenerator());
						// display result
						resultTextArea.setText(response);
						String[] exchangeIds = xml.getExchangeIds(response);
						for(int i = 0; i < exchangeIds.length; i++) {
							Vector exchangeVector = new Vector();
							exchangeVector.add(exchangeIds[i]);
							xml.setSearchType("ExchangeSearch");
							xml.setSearchValues(exchangeVector);
		
							String exchangeResponse = xml.sendRequest(xml.queryGenerator());
							Exchange[] exchanges = xml.getExchanges(exchangeResponse);
							asinRecordTable.update(exchanges);
						}
					}
					else if(e.getSource() == soapButton) {
						SoapQuery soap = new SoapQuery();
						soap.setServerURL(serverURLText.getText());
						soap.setAssociatesID(associateIDText.getText());
						soap.setToken(tokenText.getText());
						soap.setSearchType(searchCombo.getSelectedItem().toString());

						soap.setSearchValues(asinVector);
						soap.setType(typeCombo.getSelectedItem().toString());
						soap.setPage(pageText.getText());
						soap.setOffer(offerCombo.getSelectedItem().toString());
	
						Object productInfo = soap.sendAsinRequest();
						Vector exchangeVector = new Vector();
						try {
							com.amazon.soap.axis.Details[] details = ((com.amazon.soap.axis.ProductInfo)productInfo).getDetails();
							for(int i = 0; i < details.length; i++) {
								com.amazon.soap.axis.ThirdPartyProductInfo thirdPartyProductInfo = details[i].getThirdPartyProductInfo();
								if(thirdPartyProductInfo != null) {
									com.amazon.soap.axis.ThirdPartyProductDetails[] thirdPartyProductDetails = thirdPartyProductInfo.getThirdPartyProductDetails();
									for(int j = 0; j < thirdPartyProductDetails.length; j++) {
										exchangeVector.add(thirdPartyProductDetails[j].getExchangeId());
									}
								}
							}
							// do exchange search
							Object[] listingProductDetails = new Object[exchangeVector.size()];
							for(int i = 0; i < exchangeVector.size(); i++) {
								listingProductDetails[i] = soap.sendExchangeRequest((String)exchangeVector.elementAt(i));
							}
							Exchange[] exchanges = soap.getExchanges(listingProductDetails);
							asinRecordTable.update(exchanges);
						}
						catch(NullPointerException npe) {
							JOptionPane.showMessageDialog(null, "No item returns for your query");
						}
					}
					// for both XML and Soap
					// calculate recommend price
					for(int i = 0; i < asinVector.size(); i++) {
						asinRecordTable.calculateRecommendPrice((String)asinVector.elementAt(i));
					}

					ResultFrame frame = new ResultFrame(asinVector);
					asinRecordTable.saveCSV(CSV_FILE_LOCATION);
				}
				catch(org.xml.sax.SAXParseException ex) {
					JOptionPane.showMessageDialog(null, "Please check the server URL");
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Please check the input parameters");
				}
			}
		}
	}

  private class ASINTableModel extends DefaultTableModel {
		public ASINTableModel(Object[] columnNames, int rowCount) {
			super(columnNames, rowCount);

		}

    public boolean isCellEditable(int rowIndex, int ColumnIndex)
    {
      return true;
    }
  }

	private class ResultFrame extends JFrame {

		Color foreColor, backColor, back2Color;
		final String ROOT_NAME = "Results for each Asin";

		JTree tree;
		JScrollPane treeScrollPane;
		JScrollPane tablePane;
		JSplitPane splitPane;

		public ResultFrame(Vector asinVector) {
			super("Results");
			init(asinVector);

	    getContentPane().setBackground(backColor);
	    setSize(900,400);
	    centerWindow();
			show();
		}

		private void init(Vector asinVector) {
		  foreColor = new Color(248,238,126);
		  backColor = new Color(89,138,174);
		  back2Color = new Color(69,111,141);

			DefaultMutableTreeNode top = new DefaultMutableTreeNode(ROOT_NAME);

			for(int i = 0; i < asinVector.size(); i++) {
				top.add(new DefaultMutableTreeNode((String)asinVector.elementAt(i)));
			}
			tree = new JTree(top);

			tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
			tree.addTreeSelectionListener(new TreeHandler());

			treeScrollPane = new JScrollPane(tree);
			treeScrollPane.setMinimumSize(new Dimension(400,200));
			treeScrollPane.setForeground(foreColor);
			treeScrollPane.setBackground(back2Color);
			tablePane = new JScrollPane();
			tablePane.setForeground(foreColor);
			tablePane.setBackground(back2Color);
			
			splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
			splitPane.setTopComponent(treeScrollPane);
			splitPane.setBottomComponent(tablePane);
			splitPane.setForeground(foreColor);
			splitPane.setBackground(back2Color);

			getContentPane().add(splitPane);
		}

		private class TreeHandler implements TreeSelectionListener {
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)((JTree)e.getSource()).getLastSelectedPathComponent();

				
				if(asinRecordTable.containsKey(node.toString())) {
					JTable resultTable = asinRecordTable.makeTable(node.toString());
					if(resultTable != null) {
						resultTable.setForeground(foreColor);
						resultTable.setBackground(back2Color);
						resultTable.setGridColor(backColor);
		
						tablePane.setViewportView(resultTable);
						tablePane.doLayout();
						tablePane.revalidate();
						tablePane.repaint();
		
						splitPane.doLayout();
						splitPane.revalidate();
						splitPane.repaint();
						ResultFrame.this.doLayout();
						ResultFrame.this.repaint();
					}
					else {
						JOptionPane.showMessageDialog(null, "There is no exchange record for this Asin");
						tablePane.setViewportView(null);
						tablePane.doLayout();
						tablePane.repaint();
					}
				}
				else if(!node.toString().equals(ROOT_NAME)) {
					JOptionPane.showMessageDialog(null, "There is no exchange record for this Asin");
					tablePane.setViewportView(null);
					tablePane.doLayout();
					tablePane.repaint();
				}
			}
		}
	}
}
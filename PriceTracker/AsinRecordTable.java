/*****************************************************************************
      List of Classes:
        AsinRecordTable - storage of all the records
        ExchangeIdRecord - record of each exchange id
*****************************************************************************/

import java.lang.*;
import java.io.*;
import java.util.*;
import java.text.*;

import javax.swing.*;
import javax.swing.table.*;

public class AsinRecordTable implements Serializable {
	Hashtable asinHashtable;
	String mySellerId;
	DateFormat df;

	final String[] TABLE_HEADERS = {"Exchange Id", "Offer Type", "Last Price", "Current Price", "Tracking Start Date", "Condition Type", "Own?", "Recommend Price"};
	final String[] CSV_HEADERS = {"Asin", "Exchange Id", "Offer Type", "Last Price", "Current Price", "Tracking Start Date", "Condition Type"};

/*****************************************************************************
      Method name: AsinRecordTable
      Function: Constructor
      Input Parameters: N/A
      Return Values: N/A
*****************************************************************************/
	AsinRecordTable() {
		asinHashtable = new Hashtable();
		df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM);
	}

/*****************************************************************************
      Method name: updateOwnSellerId
      Function: update the seller id in the record
      Input Parameters: seller id (String)
      Return Values: N/A
*****************************************************************************/
	public void updateOwnSellerId(String sellerId) {
		mySellerId = sellerId;
	}

	public String getOwnSellerId() {
		return mySellerId;
	}

/*****************************************************************************
      Method name: update
      Function: update all the exchange records into different Asin records
      Input Parameters: exchange records (Exchange[])
      Return Values: N/A
*****************************************************************************/
	public void update(Exchange[] exchanges) {
		for(int i = 0; i < exchanges.length; i++) {
			if(asinHashtable.containsKey(exchanges[i].exchangeAsin)) {
				Hashtable oldExchangeIdHashtable = (Hashtable)asinHashtable.get(exchanges[i].exchangeAsin);

				if(oldExchangeIdHashtable.containsKey(exchanges[i].exchangeId)) {
					ExchangeIdRecord oldExchangeIdRecord = (ExchangeIdRecord)oldExchangeIdHashtable.get(exchanges[i].exchangeId);
					oldExchangeIdRecord.update(exchanges[i], mySellerId);
	
					oldExchangeIdHashtable.put(exchanges[i].exchangeId, oldExchangeIdRecord);
				}
				else {
					ExchangeIdRecord newExchangeIdRecord = new ExchangeIdRecord(exchanges[i], mySellerId);
					oldExchangeIdHashtable.put(exchanges[i].exchangeId, newExchangeIdRecord);
				}
				asinHashtable.put(exchanges[i].exchangeAsin, oldExchangeIdHashtable);
			}
			else {
				Hashtable newExchangeIdHashtable = new Hashtable();
				ExchangeIdRecord newExchangeIdRecord = new ExchangeIdRecord(exchanges[i], mySellerId);
				newExchangeIdHashtable.put(exchanges[i].exchangeId, newExchangeIdRecord);
				asinHashtable.put(exchanges[i].exchangeAsin, newExchangeIdHashtable);
			}
		}
	}

/*****************************************************************************
      Method name: calculateRecommendPrice
      Function: extract the Hashtable of a particular Asin, then update all
                the recommend price fields in all its exchanges records
      Input Parameters: Asin number (String)
      Return Values: N/A
*****************************************************************************/
	public void calculateRecommendPrice(String asin) {
		if(asinHashtable.containsKey(asin)) {
			Hashtable exchangeIdHashtable = (Hashtable)(asinHashtable.get(asin));
			updateRecommendPrice(exchangeIdHashtable);
		}
	}

/*****************************************************************************
      Method name: updateRecommendPrice
      Function: called exclusively from the calculateRecommendPrice method,
                update all the recommend price fields
      Input Parameters: a hashtable with all exchange records for the
                        same Asin (Hashtable)
      Return Values: a new hashtable with the updated recommended price fields
*****************************************************************************/
	private Hashtable updateRecommendPrice(Hashtable table) {
		Enumeration exKeys = table.keys();
		// calculate the average price for each of self-own product
		while(exKeys.hasMoreElements()) {
			ExchangeIdRecord ownRecord = (ExchangeIdRecord)table.get(exKeys.nextElement());
			if(ownRecord.getIsMyOwnProduct()) {
				// do another loop to calculate the average price
				Enumeration exKeys2 = table.keys();
				float total = 0;
				int count = 0;
				while(exKeys2.hasMoreElements()) {
					// ignore own products in the calculation
					ExchangeIdRecord eRecord = (ExchangeIdRecord)table.get(exKeys2.nextElement());
					// ignore own products in the calculation
					if(!eRecord.getIsMyOwnProduct() && eRecord.getConditionType().equals(ownRecord.getConditionType())) {
						total+=eRecord.getCurrentPrice();
						count++;
					}
				}
				if(count != 0)
					ownRecord.setRecommendPrice((float)total/count);
				else
					ownRecord.setRecommendPrice(0);
				table.put(ownRecord.getExchangeId(), ownRecord);
			}
		}
		return table;
	}

/*****************************************************************************
      Method name: load
      Function: load the records from a file location
      Input Parameters: location of the file and the file name (String)
      Return Values: N/A
*****************************************************************************/
	public void load(String location) {
		try {
			File loadingFile = new File(location);
			if(!loadingFile.exists()) {
				loadingFile.createNewFile();
			}
			else {
				FileInputStream fis = new FileInputStream(location);
				ObjectInputStream ois = new ObjectInputStream(fis);
				asinHashtable = (Hashtable)ois.readObject();
				ois.close();
				fis.close();
			}
		}
		catch(Exception e) {
			try {
				System.err.println("The existing file can't be used:" + e.toString());
				// create a new file if the old one doesn't work
				File loadingFile = new File(location);
				loadingFile.createNewFile();
				System.out.println("The file is replaced");
			}
			catch(Exception ex) {
				System.err.println(e.toString());
			}
		}
	}

/*****************************************************************************
      Method name: loadCSV
      Function: load the records (in comma separated variable format) from a file location
      Input Parameters: location of the file and the file name (String)
      Return Values: N/A
*****************************************************************************/
	public void loadCSV(String location) {
		try {
			File loadingFile = new File(location);
			if(!loadingFile.exists()) {
				System.out.println("create file");
				loadingFile.createNewFile();
			}
			else {
				FileInputStream fis = new FileInputStream(location);
				BufferedReader d = new BufferedReader(new InputStreamReader(fis));
				String str;
				while((str = d.readLine()) != null) {
					String[] tokens = str.split(",");
					if(tokens.length == CSV_HEADERS.length) {
						if(tokens[0].trim().equals(CSV_HEADERS[0])) {
							// ignore the first line (header)
							continue;
						}
						ExchangeIdRecord eRecord = new ExchangeIdRecord();
						eRecord.setExchangeId(tokens[1].trim());
						eRecord.setOfferType(tokens[2].trim());
						eRecord.setLastPrice(Float.parseFloat(tokens[3].trim()));
						eRecord.setCurrentPrice(Float.parseFloat(tokens[4].trim()));
						eRecord.setTrackingStartDate(df.parse(tokens[5].trim()));
						eRecord.setConditionType(tokens[6].trim());

						Hashtable eHash;
						if(asinHashtable.containsKey(tokens[0].trim())) {
							eHash = (Hashtable)asinHashtable.get(tokens[0].trim());
						}
						else {
							eHash = new Hashtable();
						}
						eHash.put(eRecord.getExchangeId(), eRecord);
						asinHashtable.put(tokens[0].trim(), eHash);
					}
				}
				d.close();
				fis.close();
			}
		}
		catch(Exception e) {
			try {
				System.err.println("The existing file can't be used:" + e.toString());
				// create a new file if the old one doesn't work
				File loadingFile = new File(location);
				loadingFile.createNewFile();
				System.out.println("The file is replaced");
			}
			catch(Exception ex) {
				System.err.println(e.toString());
			}
		}
	}

/*****************************************************************************
      Method name: save
      Function: save the records into the specified location
      Input Parameters: location of the file and the file name (String)
      Return Values: N/A
*****************************************************************************/
	public void save(String location) throws Exception {
		FileOutputStream fos = new FileOutputStream(location);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(asinHashtable);
		oos.close();
		fos.close();
	}

/*****************************************************************************
      Method name: saveCSV
      Function: save the records (in comma separated variable format) from a file location
      Input Parameters: location of the file and the file name (String)
      Return Values: N/A
*****************************************************************************/
	public void saveCSV(String location) throws Exception {
		FileOutputStream fos = new FileOutputStream(location);
		PrintStream ps = new PrintStream(fos);

		// put header into the file
		StringBuffer header = new StringBuffer();
		for(int i = 0; i < CSV_HEADERS.length; i++) {
			header.append(CSV_HEADERS[i]);
			if(i != CSV_HEADERS.length) {
				header.append(",");
			}
		}
		ps.println(new String(header));

		Enumeration exKeys = asinHashtable.keys();
		while(exKeys.hasMoreElements()) {
			String asin = (String)exKeys.nextElement();
			Hashtable eHashtable = (Hashtable)asinHashtable.get(asin);
			Enumeration exKeys2 = eHashtable.keys();
			while(exKeys2.hasMoreElements()) {
				ExchangeIdRecord eRecord = (ExchangeIdRecord)eHashtable.get(exKeys2.nextElement());
				StringBuffer eString = new StringBuffer();
				eString.append(asin); eString.append(",");
				eString.append(eRecord.getExchangeId()); eString.append(",");
				eString.append(eRecord.getOfferType()); eString.append(",");
				eString.append(new Float(eRecord.getLastPrice()).toString()); eString.append(",");
				eString.append(new Float(eRecord.getCurrentPrice()).toString()); eString.append(",");
				eString.append(df.format(eRecord.getTrackingStartDate())); eString.append(",");
				eString.append(eRecord.getConditionType());
				ps.println(new String(eString));
			}
		}
		ps.close();
		fos.close();
	}

/*****************************************************************************
      Method name: makeTable
      Function: make a table which contains all the exchange records
      Input Parameters: Asin number
      Return Values: a table of all exchange records with the same Asin (JTable)
*****************************************************************************/
	public JTable makeTable(String asin) {
		if(asinHashtable.containsKey(asin)) {
			return makeTable((Hashtable)asinHashtable.get(asin));
		}
		else {
			return null;
		}
	}

/*****************************************************************************
      Method name: makeTable
      Function: save the records into the specified location
      Input Parameters: location of the file and the file name (String)
      Return Values: a table of all exchange records with the same Asin (JTable)
*****************************************************************************/
	private JTable makeTable(Hashtable exchangeIdTable) {
		ASINTableModel asinTableModel = new ASINTableModel(TABLE_HEADERS, 0);
		JTable asinTable = new JTable(asinTableModel);
    asinTable.setCellSelectionEnabled(false);
    asinTable.setColumnSelectionAllowed(false);
    asinTable.setRowSelectionAllowed(false);
		asinTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		for(int i = 0; i < asinTable.getColumnCount(); i++) {
			asinTable.getColumnModel().getColumn(i).sizeWidthToFit();
		}
    asinTable.getColumnModel().getColumn(0).setPreferredWidth(130);
    asinTable.getColumnModel().getColumn(1).setPreferredWidth(45);
    asinTable.getColumnModel().getColumn(2).setPreferredWidth(45);
    asinTable.getColumnModel().getColumn(3).setPreferredWidth(50);
    asinTable.getColumnModel().getColumn(4).setPreferredWidth(170);
    asinTable.getColumnModel().getColumn(5).setPreferredWidth(60);
    asinTable.getColumnModel().getColumn(6).setPreferredWidth(20);
    asinTable.getColumnModel().getColumn(7).setPreferredWidth(95);
    asinTable.getTableHeader().setReorderingAllowed(false);
		Enumeration exKeys = exchangeIdTable.keys();
		while(exKeys.hasMoreElements()) {
			ExchangeIdRecord displayExchangeIdRecord = (ExchangeIdRecord)exchangeIdTable.get(exKeys.nextElement());
			asinTableModel.addRow(displayExchangeIdRecord.getVector());
		}
		return asinTable;

	}

/*****************************************************************************
      Method name: containsKey
      Function: check if this Asin exists in the database
      Input Parameters: Asin number (String)
      Return Values: boolean
*****************************************************************************/
	public boolean containsKey(String asin) {
		if(asinHashtable.containsKey(asin))
			return true;
		else
			return false;
	}

  private class ASINTableModel extends DefaultTableModel {
		public ASINTableModel(Object[] columnNames, int rowCount) {
			super(columnNames, rowCount);

		}

/*****************************************************************************
      Method name: isCellEditable
      Function: required by the AbstractTableModel
      Input Parameters: row, column
      Return Values: boolean
*****************************************************************************/
    public boolean isCellEditable(int rowIndex, int ColumnIndex)
    {
      return true;
    } // method isCellEditable
  } // class ASINTableModel

/*****************************************************************************
      Method name: print
      Function: for debugging purpose only, print out all the exchange ids
      Input Parameters: N/A
      Return Values: N/A
*****************************************************************************/
	public void print() {
		Enumeration exKeys = asinHashtable.keys();
		while(exKeys.hasMoreElements()) {
			String key = (String)exKeys.nextElement();
			System.out.println("Asin :" + key);
			Hashtable exchangeIdHashtable = (Hashtable)asinHashtable.get(key);
			Enumeration exKeys2 = exchangeIdHashtable.keys();
			while(exKeys2.hasMoreElements()) {
				ExchangeIdRecord displayExchangeIdRecord = (ExchangeIdRecord)exchangeIdHashtable.get(exKeys2.nextElement());
				System.out.println("ExchangeId is" + displayExchangeIdRecord.getExchangeId());
				System.out.println("Last Price is " + displayExchangeIdRecord.getLastPrice());
			}
		}
	}
	
	private class ExchangeIdRecord implements Serializable {
		String exchangeId;
		String offerType;
		float lastPrice;
		float currentPrice;
		Date trackingStartDate;
		float recommendPrice;
		String conditionType;
		boolean isMyOwnProduct;

		public ExchangeIdRecord() {
		}

		public ExchangeIdRecord(Exchange exchange, String ownerId) {

			exchangeId = exchange.exchangeId;

			offerType = exchange.exchangeOfferingType;

			// eliminate the "$"
			if(exchange.exchangePrice.charAt(0) == '$')
				currentPrice = Float.parseFloat(exchange.exchangePrice.substring(1));
			else
				currentPrice = Float.parseFloat(exchange.exchangePrice);

			trackingStartDate = new Date();

			if(exchange.exchangeSellerId.equals(ownerId))
				isMyOwnProduct = true;
			else
				isMyOwnProduct = false;

			conditionType = exchange.exchangeConditionType;
		}

/*****************************************************************************
      Method name: getVector
      Function: wrap the ExchangeIdRecord object into Vector object
                (for display)
      Input Parameters: N/A
      Return Values: Vector
*****************************************************************************/
		public Vector getVector() {
			Vector v = new Vector();
			v.add(exchangeId);
			v.add(offerType);
			v.add(new Float(lastPrice));
			v.add(new Float(currentPrice));
			v.add((Date)trackingStartDate);
			v.add(new String(conditionType));
			v.add(new Boolean(isMyOwnProduct));
			if(isMyOwnProduct)
				if(recommendPrice != 0)
					v.add(new Float(recommendPrice));
				else
					v.add(new String("No Comment"));
			else // not own product
				v.add(new String("Not Applicable"));
			return v;
		}

/*****************************************************************************
      Method name: update
      Function: update each exchangeIdRecord (wrap an Exchange object into
                ExchangeIdRecord object)
      Input Parameters: exchange (Exchange)
      Return Values: N/A
*****************************************************************************/
		public void update(Exchange exchange, String ownerId) {

			// get the old info first, then update
			setLastPrice(getCurrentPrice());

			offerType = exchange.exchangeOfferingType;

			if(exchange.exchangePrice.charAt(0) == '$')
				setCurrentPrice(Float.parseFloat(exchange.exchangePrice.substring(1)));
			else
				setCurrentPrice(Float.parseFloat(exchange.exchangePrice));

			if(exchange.exchangeSellerId.equals(ownerId))
				setIsMyOwnProduct(true);
			else
				setIsMyOwnProduct(false);

			setConditionType(exchange.exchangeConditionType);
		}

		public String getExchangeId() {
			return exchangeId;
		}
		public String getOfferType() {
			return offerType;
		}
		public float getLastPrice() {
			return lastPrice;
		}
		public float getCurrentPrice() {
			return currentPrice;
		}
		public Date getTrackingStartDate() {
			return trackingStartDate;
		}
		public float getRecommendPrice() {
			return recommendPrice;
		}
		public boolean getIsMyOwnProduct() {
			return isMyOwnProduct;
		}
		public String getConditionType() {
			return conditionType;
		}

		public void setExchangeId(String _exchangeId) {
			exchangeId = _exchangeId;
		}
		public void setOfferType(String _offerType) {
			offerType = _offerType;
		}
		public void setLastPrice(float _lastPrice) {
			lastPrice = _lastPrice;
		}
		public void setCurrentPrice(float _currentPrice) {
			currentPrice = _currentPrice;
		}
		public void setTrackingStartDate(Date _trackingStartDate) {
			trackingStartDate = _trackingStartDate;
		}
		public void setRecommendPrice(float _recommendPrice) {
			recommendPrice = _recommendPrice;
		}
		public void setIsMyOwnProduct(boolean _isMyOwnProduct) {
			isMyOwnProduct = _isMyOwnProduct;
		}

		public void setConditionType(String _conditionType) {
			conditionType = _conditionType;
		}
	}
}
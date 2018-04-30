import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Container;
import java.awt.Component;
import javax.swing.ButtonGroup;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


/**
 * Interface to be implemented by classes that represent a query to a server.
 * It provides a user interface with which it acquires user input, and it
 * generates a query with the user input.
 **/

public class SellerSearch extends AbstractXmlQuery
{

	protected String seller;
	
	public SellerSearch()
	{
	}
	
	public String getTitle() 
	{
		return "Seller";
	}
	
	public String getDescription()
	{
		return "Retrieves information for an seller record";
	}
	
	protected String getGetString()
	{
		return super.getGetString()+"&SellerSearch="+seller;
	}
	
	public Component createWidget(XmlQuery.Listener listener_)
	{
		return new DataInput(listener_);
	}

	protected class DataInput extends AbstractXmlQuery.DataInput
	{
		public JTextField seller;
		
		public DataInput(XmlQuery.Listener listener_)
		{
			super(listener_);

			JLabel label;
			Container container;

			//seller
			this.seller = new JTextField();
			this.seller.setText(SellerSearch.this.seller);
			label = new JLabel("Seller");
			label.setLabelFor(this.seller);
			container = new Container();
			container.add(label);
			container.add(this.seller);
			container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
			add(container);
		}

		protected void dialogFinished()
		{
			
			SellerSearch.this.seller=SellerSearch.DataInput.this.seller.getText();

			SellerSearch.DataInput.this.listener.finished(SellerSearch.this);
		}
	}
}

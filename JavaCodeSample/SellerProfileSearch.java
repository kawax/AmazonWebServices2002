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

public class SellerProfileSearch extends AbstractXmlQuery
{

	protected String sellerProfile;
	
	public SellerProfileSearch()
	{
	}
	
	public String getTitle() 
	{
		return "Seller Profile";
	}
	
	public String getDescription()
	{
		return "Retrieves information for an Seller Profile record";
	}
	
	protected String getGetString()
	{
		return super.getGetString()+"&SellerProfile="+sellerProfile;
	}
	
	public Component createWidget(XmlQuery.Listener listener_)
	{
		return new DataInput(listener_);
	}

	protected class DataInput extends AbstractXmlQuery.DataInput
	{
		public JTextField sellerProfile;
		
		public DataInput(XmlQuery.Listener listener_)
		{
			super(listener_);

			JLabel label;
			Container container;

			//sellerProfile
			this.sellerProfile = new JTextField();
			this.sellerProfile.setText(SellerProfileSearch.this.sellerProfile);
			label = new JLabel("Seller Profile");
			label.setLabelFor(this.sellerProfile);
			container = new Container();
			container.add(label);
			container.add(this.sellerProfile);
			container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
			add(container);
		}

		protected void dialogFinished()
		{
			
			SellerProfileSearch.this.sellerProfile=SellerProfileSearch.DataInput.this.sellerProfile.getText();

			SellerProfileSearch.DataInput.this.listener.finished(SellerProfileSearch.this);
		}
	}
}

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

public class WishlistSearch extends AbstractXmlQuery
{

	protected String wishlist;
	
	public WishlistSearch()
	{
	}
	
	public String getTitle() 
	{
		return "Wishlist";
	}
	
	public String getDescription()
	{
		return "Retrieves information for an wishlist record";
	}
	
	protected String getGetString()
	{
		return super.getGetString()+"&WishlistSearch="+wishlist;
	}
	
	public Component createWidget(XmlQuery.Listener listener_)
	{
		return new DataInput(listener_);
	}

	protected class DataInput extends AbstractXmlQuery.DataInput
	{
		public JTextField wishlist;
		
		public DataInput(XmlQuery.Listener listener_)
		{
			super(listener_);

			JLabel label;
			Container container;

			//wishlist
			this.wishlist = new JTextField();
			this.wishlist.setText(WishlistSearch.this.wishlist);
			label = new JLabel("Wishlist");
			label.setLabelFor(this.wishlist);
			container = new Container();
			container.add(label);
			container.add(this.wishlist);
			container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
			add(container);
		}

		protected void dialogFinished()
		{
			
			WishlistSearch.this.wishlist=WishlistSearch.DataInput.this.wishlist.getText();

			WishlistSearch.DataInput.this.listener.finished(WishlistSearch.this);
		}
	}
}

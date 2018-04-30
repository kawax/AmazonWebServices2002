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

public class BrowseNodeSearch extends AbstractXmlQuery
{

	protected String node;
	protected String mode;
	
	public BrowseNodeSearch()
	{
	}
	
	public String getTitle() 
	{
		return "Browse Node";
	}
	
	public String getDescription()
	{
		return "Retrieves information for a node";
	}
	
	protected String getGetString()
	{
		return super.getGetString()+"&BrowseNodeSearch="+node+"&mode="+mode;
	}
	
	public Component createWidget(XmlQuery.Listener listener_)
	{
		return new DataInput(listener_);
	}

	protected class DataInput extends AbstractXmlQuery.DataInput
	{
		public JTextField node;
		public JTextField mode;
		
		public DataInput(XmlQuery.Listener listener_)
		{
			super(listener_);

			JLabel label;
			Container container;

			//node
			this.node = new JTextField();
			this.node.setText(BrowseNodeSearch.this.node);
			label = new JLabel("Node");
			label.setLabelFor(this.node);
			container = new Container();
			container.add(label);
			container.add(this.node);
			container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
			add(container);
			//mode
			this.mode = new JTextField();
			this.mode.setText(BrowseNodeSearch.this.mode);
			label = new JLabel("Mode");
			label.setLabelFor(this.mode);
			container = new Container();
			container.add(label);
			container.add(this.mode);
			container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
			add(container);
		}

		protected void dialogFinished()
		{
			
			BrowseNodeSearch.this.node=BrowseNodeSearch.DataInput.this.node.getText();
			BrowseNodeSearch.this.mode=BrowseNodeSearch.DataInput.this.mode.getText();

			BrowseNodeSearch.DataInput.this.listener.finished(BrowseNodeSearch.this);
		}
	}
}

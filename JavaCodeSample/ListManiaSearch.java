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

public class ListManiaSearch extends AbstractXmlQuery
{

	protected String listMania;
	
	public ListManiaSearch()
	{
	}
	
	public String getTitle() 
	{
		return "List Mania";
	}
	
	public String getDescription()
	{
		return "Retrieves information for an list Mania record";
	}
	
	protected String getGetString()
	{
		return super.getGetString()+"&ListManiaSearch="+listMania;
	}
	
	public Component createWidget(XmlQuery.Listener listener_)
	{
		return new DataInput(listener_);
	}

	protected class DataInput extends AbstractXmlQuery.DataInput
	{
		public JTextField listMania;
		
		public DataInput(XmlQuery.Listener listener_)
		{
			super(listener_);

			JLabel label;
			Container container;

			//listMania
			this.listMania = new JTextField();
			this.listMania.setText(ListManiaSearch.this.listMania);
			label = new JLabel("List Mania");
			label.setLabelFor(this.listMania);
			container = new Container();
			container.add(label);
			container.add(this.listMania);
			container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
			add(container);
		}

		protected void dialogFinished()
		{
			
			ListManiaSearch.this.listMania=ListManiaSearch.DataInput.this.listMania.getText();

			ListManiaSearch.DataInput.this.listener.finished(ListManiaSearch.this);
		}
	}
}

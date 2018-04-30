import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Container;
import java.awt.Component;
import javax.swing.ButtonGroup;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


/**
 * Interface to be implemented by classes that represent a query to a server.
 * It provides a user interface with which it acquires user input, and it
 * generates a query with the user input.
 **/

public class PowerSearch extends AbstractXmlQuery
{
	protected String searchType;	
	protected String searchWord;
	protected String mode;
	
	public PowerSearch()
	{
	}
	
	public String getTitle() 
	{
		return "Power Search";
	}
	
	public String getDescription()
	{
		return "Searches based on Author / Actor / Director / Manufacturer";
	}
	
	protected String getGetString()
	{
		return super.getGetString()+"&"+this.searchType+"="+super.convertWhiteSpace(this.searchWord)+"&mode="+this.mode;
	}
	
	public Component createWidget(XmlQuery.Listener listener_)
	{
		return new DataInput(listener_);
	}

	protected class DataInput extends AbstractXmlQuery.DataInput
	{
		protected JTextField searchWord;
		protected JComboBox searchType;
		protected JTextField mode;
		
		public DataInput(XmlQuery.Listener listener_)
		{
			super(listener_);

			JLabel label;
			Container container;

			//search word and type
			this.searchWord = new JTextField();
			this.searchWord.setText(PowerSearch.this.searchWord);
			this.searchType = new JComboBox(new String[]{"AuthorSearch", "ActorSearch", "DirectorSearch", "ManufacturerSearch"});
			container = new Container();
			container.add(this.searchType);
			container.add(this.searchWord);
			container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
			add(container);

			//mode
			this.mode = new JTextField();
			this.mode.setText(PowerSearch.this.mode);
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
			PowerSearch.this.searchType=(String)PowerSearch.DataInput.this.searchType.getSelectedItem();
			PowerSearch.this.searchWord=PowerSearch.DataInput.this.searchWord.getText();
			PowerSearch.this.mode=PowerSearch.DataInput.this.mode.getText();

			PowerSearch.DataInput.this.listener.finished(PowerSearch.this);
		}
	}
}

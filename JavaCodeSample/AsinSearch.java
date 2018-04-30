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

public class AsinSearch extends AbstractXmlQuery
{
	protected String searchType;	
	protected String searchWord;
	protected String mode;
	
	public AsinSearch()
	{
	}
	
	public String getTitle() 
	{
		return "Asin Search";
	}
	
	public String getDescription()
	{
		return "Searches based on Asin / ISBN / UPC";
	}
	
	protected String getGetString()
	{
		return super.getGetString()+"&"+this.searchType+"="+this.searchWord+"&mode="+this.mode;
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
			this.searchWord.setText(AsinSearch.this.searchWord);
			this.searchType = new JComboBox(new String[]{"AsinSearch", "UpcSearch"});
			container = new Container();
			container.add(this.searchType);
			container.add(this.searchWord);
			container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
			add(container);

			//mode
			this.mode = new JTextField();
			this.mode.setText(AsinSearch.this.mode);
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
			AsinSearch.this.searchType=(String)AsinSearch.DataInput.this.searchType.getSelectedItem();
			AsinSearch.this.searchWord=AsinSearch.DataInput.this.searchWord.getText();
			AsinSearch.this.mode=AsinSearch.DataInput.this.mode.getText();

			AsinSearch.DataInput.this.listener.finished(AsinSearch.this);
		}
	}
}

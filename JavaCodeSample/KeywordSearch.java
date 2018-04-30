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

public class KeywordSearch extends AbstractXmlQuery
{

	protected String keyword;
	protected String mode;
	
	public KeywordSearch()
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
		return super.getGetString()+"&KeywordSearch="+super.convertWhiteSpace(keyword)+"&mode="+mode;
	}
	
	public Component createWidget(XmlQuery.Listener listener_)
	{
		return new DataInput(listener_);
	}

	protected class DataInput extends AbstractXmlQuery.DataInput
	{
		public JTextField keyword;
		public JTextField mode;
		
		public DataInput(XmlQuery.Listener listener_)
		{
			super(listener_);

			JLabel label;
			Container container;

			//keyword
			this.keyword = new JTextField();
			this.keyword.setText(KeywordSearch.this.keyword);
			label = new JLabel("Keyword");
			label.setLabelFor(this.keyword);
			container = new Container();
			container.add(label);
			container.add(this.keyword);
			container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
			add(container);
			//mode
			this.mode = new JTextField();
			this.mode.setText(KeywordSearch.this.mode);
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
			
			KeywordSearch.this.keyword=KeywordSearch.DataInput.this.keyword.getText();
			KeywordSearch.this.mode=KeywordSearch.DataInput.this.mode.getText();

			KeywordSearch.DataInput.this.listener.finished(KeywordSearch.this);
		}
	}
}

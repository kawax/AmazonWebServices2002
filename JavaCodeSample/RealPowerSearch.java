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

public class RealPowerSearch extends AbstractXmlQuery
{

	protected String author;
	protected String pubdate;
	protected String subject;
	protected String mode;
	
	public RealPowerSearch()
	{
	}
	
	public String getTitle() 
	{
		return "Power Search";
	}
	
	public String getDescription()
	{
		return "Retrieves information for a node";
	}
	
	protected String getGetString()
	{
		return super.getGetString()+"&PowerSearch="+super.convertWhiteSpace(getPowerString())+"&mode="+mode;
	}

	private String getPowerString()
	{
		StringBuffer powerString = new StringBuffer();
		if(!author.equals(""))
			powerString.append("author:" + author);

		if(!author.equals("") && !pubdate.equals(""))
			powerString.append(" and " + "pubdate:" + pubdate);
		else if(author.equals("") && !pubdate.equals(""))
			powerString.append("pubdate:" + pubdate);

		if((!author.equals("") || !pubdate.equals("")) && !subject.equals(""))
			powerString.append(" and " + "subject:" + subject);
		else if(author.equals("") && pubdate.equals("") && !subject.equals(""))
			powerString.append("subject:" + subject);

		return new String(powerString);
	}
	
	public Component createWidget(XmlQuery.Listener listener_)
	{
		return new DataInput(listener_);
	}

	protected class DataInput extends AbstractXmlQuery.DataInput
	{
		public JTextField author;
		public JTextField pubdate;
		public JTextField subject;
		public JTextField mode;
		
		public DataInput(XmlQuery.Listener listener_)
		{
			super(listener_);

			JLabel label;
			Container container;

			//author
			this.author = new JTextField();
			this.author.setText(RealPowerSearch.this.author);
			label = new JLabel("Author");
			label.setLabelFor(this.author);
			container = new Container();
			container.add(label);
			container.add(this.author);
			container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
			add(container);

			//pubdate
			this.pubdate = new JTextField();
			this.pubdate.setText(RealPowerSearch.this.pubdate);
			label = new JLabel("Publish Date");
			label.setLabelFor(this.pubdate);
			container = new Container();
			container.add(label);
			container.add(this.pubdate);
			container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
			add(container);

			//subject
			this.subject = new JTextField();
			this.subject.setText(RealPowerSearch.this.subject);
			label = new JLabel("Subject");
			label.setLabelFor(this.subject);
			container = new Container();
			container.add(label);
			container.add(this.subject);
			container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
			add(container);

			//mode
			this.mode = new JTextField();
			this.mode.setText(RealPowerSearch.this.mode);
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
			
			RealPowerSearch.this.author=RealPowerSearch.DataInput.this.author.getText();
			RealPowerSearch.this.pubdate=RealPowerSearch.DataInput.this.pubdate.getText();
			RealPowerSearch.this.subject=RealPowerSearch.DataInput.this.subject.getText();
			RealPowerSearch.this.mode=RealPowerSearch.DataInput.this.mode.getText();

			RealPowerSearch.DataInput.this.listener.finished(RealPowerSearch.this);
		}
	}
}

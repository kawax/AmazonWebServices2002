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

public class ExchangeSearch extends AbstractXmlQuery
{

	protected String exchange;
	
	public ExchangeSearch()
	{
	}
	
	public String getTitle() 
	{
		return "Exchange";
	}
	
	public String getDescription()
	{
		return "Retrieves information for an exchange record";
	}
	
	protected String getGetString()
	{
		return super.getGetString()+"&ExchangeSearch="+exchange;
	}
	
	public Component createWidget(XmlQuery.Listener listener_)
	{
		return new DataInput(listener_);
	}

	protected class DataInput extends AbstractXmlQuery.DataInput
	{
		public JTextField exchange;
		
		public DataInput(XmlQuery.Listener listener_)
		{
			super(listener_);

			JLabel label;
			Container container;

			//exchange
			this.exchange = new JTextField();
			this.exchange.setText(ExchangeSearch.this.exchange);
			label = new JLabel("Exchange");
			label.setLabelFor(this.exchange);
			container = new Container();
			container.add(label);
			container.add(this.exchange);
			container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
			add(container);
		}

		protected void dialogFinished()
		{
			
			ExchangeSearch.this.exchange=ExchangeSearch.DataInput.this.exchange.getText();

			ExchangeSearch.DataInput.this.listener.finished(ExchangeSearch.this);
		}
	}
}

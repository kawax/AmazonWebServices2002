import java.awt.Container;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;


public abstract class AbstractSoapQuery implements SoapQuery
{
	protected Object result;
	protected HashMap parameters; //parameter name->value
	
	public AbstractSoapQuery()
	{
		result=null;
		parameters = new HashMap();
	}
	
	public Object getResult()
	{
		return this.result;
	}

	public Parameter[] getParameters()
	{
		Parameter[] result=new Parameter[this.parameters.size()];
		Iterator it=this.parameters.entrySet().iterator();
		int i=0;
		while(it.hasNext())
		{
			Map.Entry entry = (Map.Entry)it.next();
			result[i]=new Parameter((String)entry.getKey(), (String)entry.getValue());
			i++;
		}
		return result;
	}
	
	public void setParameter(String name_, String value_)
	{
		if (this.parameters.containsKey(name_))
		{
			this.parameters.put(name_,value_);
		}
	}
	
	public void setParameters(Parameter[] parameters_)
	{
		for(int i=0;i<parameters_.length;++i)
		{
			setParameter(parameters_[i].getName(),parameters_[i].getValue());
		}
	}
	
	public static Object createField(String name_, String value_, Container container_)
	{
		if(name_.equals("Type"))
		{
			JRadioButton liteResult = new JRadioButton("Lite");
			liteResult.getModel().setActionCommand("lite");
			JRadioButton heavyResult = new JRadioButton("Heavy");
			heavyResult.getModel().setActionCommand("heavy");
			ButtonGroup resultGroup = new ButtonGroup();			
			resultGroup.add(liteResult);
			resultGroup.add(heavyResult);
			container_.add(liteResult);
			container_.add(heavyResult);
			if(value_.equalsIgnoreCase("lite"))
			{
				liteResult.setSelected(true);
			}
			else
			{
				heavyResult.setSelected(true);
			}
			return resultGroup;
			}
		else
		{
			JTextField text = new JTextField();
			text.setText(value_);
			JLabel label = new JLabel(name_);
			label.setLabelFor(text);
			Container container = new Container();
			container.add(label);
			container.add(text);
			container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
			container_.add(container);
			return text;
		}
		
	}

	public Component createWidget(SoapQuery.Listener listener_)
	{
		return new DataInput(listener_);
	}

	protected class DataInput extends Container
	{
		protected Vector parameters; //has Payload

		protected JButton ok;

		protected SoapQuery.Listener listener;
		
		public class Payload
		{
			String name;
			Object payload;
			public Payload(String name_, Object payload_)
			{
				this.name=name_;
				this.payload=payload_;
			}
			public String getName()
			{
				return this.name;
			}
			
			public Object getPayload()
			{
				return this.payload;
			}
		}
		

		public DataInput(SoapQuery.Listener listener_)
		{
			this.listener = listener_;

			JLabel label;
			Container container;

			//Create the widgets for each field
			this.parameters = new Vector();
			Parameter[] paramArray = AbstractSoapQuery.this.getParameters();
			for(int i=0;i<paramArray.length;++i)
			{
				this.parameters.add(new Payload(paramArray[i].getName(),createField(paramArray[i].getName(), paramArray[i].getValue(), this)));
			}
			

			//Type
			this.ok = new JButton("Send");
			this.ok.setActionCommand("Send");
			this.ok.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						if(e.getActionCommand().equals("Send"))
						{
							for(int i=0;i<parameters.size();++i)
							{
								if(((Payload)parameters.elementAt(i)).getName().equals("Type"))
								{
									AbstractSoapQuery.this.setParameter("Type",((ButtonGroup)((Payload)parameters.elementAt(i)).getPayload()).getSelection().getActionCommand());
								}
								else
								{
									AbstractSoapQuery.this.setParameter(((Payload)parameters.elementAt(i)).getName(),((JTextField)((Payload)parameters.elementAt(i)).getPayload()).getText());
								}
							}
							AbstractSoapQuery.DataInput.this.dialogFinished();
						}
					}
				});
			add(this.ok);
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		}
			
		
		protected void dialogFinished()
		{
			AbstractSoapQuery.DataInput.this.listener.finished(AbstractSoapQuery.this);
		}
	}


}



import java.awt.Component;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import java.io.ByteArrayInputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;


/**
 * Creates a JTree out of the data contained within a class that is returned
 * from a SOAP call.  This class contains only one publically accessible method:
 * createTreeView().  This method generates a JTree based upon the data
 * contained in the specified class.
 **/
public class XmlDisplay
{
	private static DefaultMutableTreeNode createView(Node node_)
	{
		DefaultMutableTreeNode topNode = new DefaultMutableTreeNode();
		String value=new String();


		
		NodeList children = node_.getChildNodes();
		for(int i=0; i<children.getLength();++i)
		{
			if(children.item(i).getNodeName().equals("#text"))
			{
				if(!children.item(i).getNodeValue().trim().equals(""))
					value+=children.item(i).getNodeValue().trim()+",";
			}
			else
			{
				topNode.add(createView(children.item(i)));
			}
		}
		
		if(!value.equals(""))
		{
			topNode.setUserObject(node_.getNodeName()+"="+value.substring(0,value.length()-1));
		}
		else
		{
			topNode.setUserObject(node_.getNodeName());
		}
		
		return topNode;
	}
	
	/**
	 * This method goes through the given Object obj_, and finds all the data
	 * contained within it - going through any fields that contain "getter"
	 * methods -- methods that return data without taking an argument.  It creates
	 * leaves for fields that are either null or are a string.  The String name_,
	 * is the name given to the root node.  This is intended for use with a the
	 * returned data from a SOAP call, where all the fundamental types are strings
	 * -- or other data structures which follow the same restrictions.
	 **/
	public static JTree createTreeView(String xml_) throws ParserConfigurationException, SAXException, IOException
	{
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(xml_.getBytes()));
		return new JTree(createView(document));
	}


	/**
	 * For testing purposes only.
	 **/
	public static void main(String[] args) throws Exception
	{
		XmlDisplay.createTreeView("test");
	}
}

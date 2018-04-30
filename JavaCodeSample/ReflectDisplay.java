import java.awt.Component;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;


/**
 * Creates a JTree out of the data contained within a class that is returned
 * from a SOAP call.  This class contains only one publically accessible method:
 * createTreeView().  This method generates a JTree based upon the data
 * contained in the specified class.
 **/
public class ReflectDisplay
{
	/**
	 * Create part of a tree with Object array_ at the root -- and the array_ is
	 * in fact an array, not just a simple object.
	 **/
	private static DefaultMutableTreeNode createViewArray(String name_, Object array_)
	{
		if(array_ != null)
		{
			DefaultMutableTreeNode topNode = new DefaultMutableTreeNode(name_);
			
			for(int i=0; i<Array.getLength(array_); ++i)
			{
				if((Array.get(array_,i) == null) ||
					 (!Array.get(array_,i).getClass().isArray()))
				{
					topNode.add(createView(name_+"["+i+"]",Array.get(array_, i)));
				}
				else if(Array.get(array_, i).getClass().isArray())
				{
					topNode.add(createViewArray(name_+"["+i+"]",Array.get(array_, i)));
				}
			}
			return topNode;
		}
		else
		{
			return new DefaultMutableTreeNode(name_ +"= null");
		}
	}
	
	/**
	 * Create part of tree with the Object obj_ at the root.
	 **/
	private static DefaultMutableTreeNode createView(String name_, Object obj_)
	{
		if(obj_ != null)
		{
			Class stringClass = null;
			Package soapPackage = Package.getPackage("com.amazon.soap.axis");
			Package soapPackage2 = Package.getPackage("com.amazon.soap.jdk");
			try
			{
				stringClass = Class.forName("java.lang.String");
			}
			catch(ClassNotFoundException e)
			{
				System.out.println("Can't find java.lang.String class");
			}

			DefaultMutableTreeNode topNode = new DefaultMutableTreeNode(name_);
			if(obj_.getClass().equals(stringClass))
			{
				return new DefaultMutableTreeNode(name_+" = "+obj_);
			}
			
			Method[] methods = obj_.getClass().getMethods();
			for(int i = 0; i < methods.length; ++i)
			{

				try
				{
					if((methods[i].getParameterTypes().length == 0))
					{
						if(methods[i].getReturnType().isArray())
						{
							topNode.add(createViewArray(methods[i].getName(), methods[i].invoke(obj_, new Object[0])));
						}
						else if((methods[i].getReturnType().getPackage() != null) &&
										((methods[i].getReturnType().getPackage().equals(soapPackage)) ||
										 (methods[i].getReturnType().getPackage().equals(soapPackage2)) ||
										 (methods[i].getReturnType().equals(stringClass))))
						{
							topNode.add(createView(methods[i].getName(), methods[i].invoke(obj_, new Object[0])));
						}
					}
				}
				catch(IllegalAccessException e)
				{
					System.out.println("Can't access method.");
				}
				catch(InvocationTargetException e)
				{
					System.out.println("Can't access method.");
				}

			}
			return topNode;
		}
		else
		{
			return new DefaultMutableTreeNode(name_ + "= null");
		}
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
	public static JTree createTreeView(String name_, Object obj_)
	{
		return new JTree(createView(name_, obj_));
	}


	/**
	 * For testing purposes only.
	 **/
	public static void main(String[] args)	
	{
//		Object test = new com.amazon.soap.AmazonSearchPort_KeywordSearchRequest_RequestStruct();
		ReflectDisplay.createView("test", "whatever");
	}
}

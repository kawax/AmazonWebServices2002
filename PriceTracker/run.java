/*****************************************************************************
      List of Classes:
        Run - entry point of the application
*****************************************************************************/

import java.awt.event.*;

public class run
{
	public static void main(String[] args_)
	{
		System.out.println("Starting Application...");
		//Creates the Main Window and finishes
		XmlSoapGUI gui = new XmlSoapGUI();
		gui.addWindowListener(
			new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			}
		);
	}
}
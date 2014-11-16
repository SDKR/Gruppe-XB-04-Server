package GUILogic;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.*;

public class Logic {
	private ContainerPanel CP;
	public Logic()
	{
		CP = new ContainerPanel();
		initializeListeners();
	}
	public void startApp()
	{
		CP.show(ContainerPanel.loginScreen);
		CP.setVisible(true);
	}
	private class WelcomeLoginBtn implements ActionListener {
		//When button pushed, show login screen
		public void actionPerformed(ActionEvent e) {
			CP.show(ContainerPanel.mainMenu);
		}
	}

}

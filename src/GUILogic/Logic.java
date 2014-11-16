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
	private class LogOut implements ActionListener {
		//When button pushed, show login screen
		public void actionPerformed(ActionEvent e) {
			String UserMenuController = e.getActionCommand();
			switch (UserMenuController) {
			case "LogOut":
				CP.show(ContainerPanel.loginScreen);
				break;

			case "UserList":
				CP.show(ContainerPanel.userView);
				break;

			case "EventList":
				CP.show(ContainerPanel.eventView);
				break;

			case "NoteList":
				CP.show(ContainerPanel.noteView);
				break;
		}
	}
		}
	private void initializeListeners() {
		CP.getLI().addActionListenerWelcomeScreen(new WelcomeLoginBtn());
		CP.getMM().addActionListenerMainMenu(new LogOut());
	}
}

package GUILogic;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import DatabaseLogic.DatabaseConnection;
import GUI.*;

public class Logic {
	DatabaseConnection DC = new DatabaseConnection();
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
	private class loginBtn implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String emailInput = CP.getLI().getTextFieldUsername().getText();
			String passwordInput = CP.getLI().getTextFieldPassword().getText();
			if(DC.checkPassword(emailInput, passwordInput)==true)
			{
				if(DC.checkIfAdmin(emailInput)==true)
				{
					JOptionPane.showMessageDialog( CP, "Login succesfull!");
					CP.show(ContainerPanel.mainMenu);
				}
				else
				{
					JOptionPane.showMessageDialog( CP, "You do not have sufficient access to login");
				}
				
			}
			else
			{
				JOptionPane.showMessageDialog( CP, "The entered password was incorrect");
			}
			
		}
	}
	
	private class btnToMainMenu implements ActionListener {
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
		CP.getLI().addActionListenerWelcomeScreen(new loginBtn());
		CP.getMM().addActionListenerMainMenu(new LogOut());
		CP.getUI().goToMainMenu(new btnToMainMenu());
		CP.geteList().goToMainMenu(new btnToMainMenu());
		CP.getNL().goToMainMenu(new btnToMainMenu());
	}
}

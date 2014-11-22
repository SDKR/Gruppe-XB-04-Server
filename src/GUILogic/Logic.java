package GUILogic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import DatabaseLogic.DatabaseConnection;
import GUI.*;

public class Logic {
	DatabaseConnection DC = new DatabaseConnection();
	private ContainerPanel CP;
	UserList UL = new UserList();

	public Logic() throws SQLException {
		CP = new ContainerPanel();
		initializeListeners();
	}

	public void startApp() {
		
		DC.keyImporter();
		CP.show(ContainerPanel.loginScreen);
		CP.setVisible(true);
	}

	private class loginBtn implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String emailInput = CP.getLI().getTextFieldUsername().getText();
			String passwordInput = CP.getLI().getTextFieldPassword().getText();
			if (!emailInput.equals("") || !passwordInput.equals("")) {
				if (DC.checkPassword(emailInput, passwordInput) == true) {
					if (DC.checkIfAdmin(emailInput) == true) {
						JOptionPane.showMessageDialog(CP, "Login succesfull!");
						CP.show(ContainerPanel.mainMenu);
						CP.getLI().getTextFieldUsername().setText("");
						CP.getLI().getTextFieldPassword().setText("");
					} else {
						JOptionPane.showMessageDialog(CP,
								"You do not have sufficient access to login");
					}
				} else {
					JOptionPane.showMessageDialog(CP,
							"The entered password was incorrect");
				}
			} else {
				JOptionPane.showMessageDialog(CP,
						"You have to enter both an username and password!");
			}
		}
	}

	private class btnToMainMenu implements ActionListener {
		// When button pushed, show login screen
		public void actionPerformed(ActionEvent e) {
			CP.show(ContainerPanel.mainMenu);
		}
	}
	
	public void viewUser() {
		// Creates an object of the class databaseconnection
		DatabaseConnection DC = new DatabaseConnection();
		// Get the size of an arraylist which a method from databaseConnection
		// returns, and sets a int equals that
		String[][] test = DC.arrayID();
		int arrayCounter = test[0].length;
		// Creates an int equals to 0
		int arrayChecker = 0;
		
		for( int reset = 1; reset<arrayCounter; reset++)
		{
			//Sets every field in a Jtable equals nothing
			CP.getUI().getTable().setValueAt(null, reset, 0);
			CP.getUI().getTable().setValueAt(null, reset, 1);
			CP.getUI().getTable().setValueAt(null, reset, 2);
			CP.getUI().getTable().setValueAt(null, reset, 3);
			CP.getUI().getTable().setValueAt(null, reset, 4);
			CP.getUI().getTable().setValueAt(null, reset, 5);
		}
		// As long as there is something in the arraylists, add it to the Jtable
		while (arrayChecker <= arrayCounter-2) {
			CP.getUI().getTable().setValueAt(test[0][arrayChecker], arrayChecker, 0);
			CP.getUI().getTable().setValueAt(test[1][arrayChecker], arrayChecker, 1);
			CP.getUI().getTable().setValueAt(test[2][arrayChecker], arrayChecker, 2);
			CP.getUI().getTable().setValueAt(test[3][arrayChecker], arrayChecker, 3);
			CP.getUI().getTable().setValueAt(test[4][arrayChecker], arrayChecker, 4);
			CP.getUI().getTable().setValueAt(test[5][arrayChecker], arrayChecker, 5);
	}}

	private class LogOut implements ActionListener {
		// When button pushed, show login screen
		public void actionPerformed(ActionEvent e) {
			String UserMenuController = e.getActionCommand();
			switch (UserMenuController) {
			case "LogOut":
				CP.show(ContainerPanel.loginScreen);
				break;

			case "UserList":
				viewUser();
				CP.show(ContainerPanel.userView);
				break;

			case "EventList":
				CP.show(ContainerPanel.eventView);
				break;

			case "NoteList":
				CP.show(ContainerPanel.noteView);
				break;
				
			case "QAWList":
				CP.show(ContainerPanel.quoteAndWeather);
				break;
			}
		}
	}

	private class QuoteAndWeather implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			CP.show(ContainerPanel.mainMenu);
		}
	}

	
	


	private void initializeListeners() {
		CP.getLI().addActionListenerWelcomeScreen(new loginBtn());
		CP.getMM().addActionListenerMainMenu(new LogOut());
		CP.getUI().goToMainMenu(new btnToMainMenu());
		CP.geteList().goToMainMenu(new btnToMainMenu());
		CP.getNL().goToMainMenu(new btnToMainMenu());
		CP.getQAW().goToMainMenu(new QuoteAndWeather());
	}
}

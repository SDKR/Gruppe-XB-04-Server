package GUILogic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.Forecast.ForecastModel;
import model.Forecast.ForecastTest;
import model.QOTD.QOTDModel;
import DatabaseLogic.DatabaseConnection;
import GUI.*;

public class Logic {
	DatabaseConnection DC = new DatabaseConnection();
	private ContainerPanel CP;
	ForecastModel FM = new ForecastModel();
	QOTDModel QModel = new QOTDModel();


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
		DC.keyImporter();
		// Get the size of an arraylist which a method from databaseConnection
		// returns, and sets a int equals that
		System.out.println("Vi er inde i viewuser");
		String[][] test = DC.arrayID();
		int arrayCounter = test[0].length;
		// Creates an dint equals to 0
		int arrayChecker = 0;

		for (int reset = 1; reset < arrayCounter; reset++) {
			System.out.println("Vi er inde i for-loop " + reset + ". gang");
			// Sets every field in a Jtable equals nothing
			CP.getUI().getTable().setValueAt(null, reset, 0);
			CP.getUI().getTable().setValueAt(null, reset, 1);
			CP.getUI().getTable().setValueAt(null, reset, 2);
			CP.getUI().getTable().setValueAt(null, reset, 3);
			CP.getUI().getTable().setValueAt(null, reset, 4);
			CP.getUI().getTable().setValueAt(null, reset, 5);
		}
		// As long as there is something in the arraylists, add it to the Jtable
		while (arrayChecker < arrayCounter) {
			System.out.println("Vi er inde i while-loop " + arrayChecker
					+ ". gang");
			CP.getUI().getTable()
					.setValueAt(test[0][arrayChecker], arrayChecker, 0);
			CP.getUI().getTable()
					.setValueAt(test[1][arrayChecker], arrayChecker, 1);
			CP.getUI().getTable()
					.setValueAt(test[2][arrayChecker], arrayChecker, 2);
			CP.getUI().getTable()
					.setValueAt(test[3][arrayChecker], arrayChecker, 3);
			CP.getUI().getTable()
					.setValueAt(test[4][arrayChecker], arrayChecker, 4);
			CP.getUI().getTable()
					.setValueAt(test[5][arrayChecker], arrayChecker, 5);
			arrayChecker++;
		}
	}

	public void viewEvents() {
		// Creates an object of the class databaseconnection
		DatabaseConnection DC = new DatabaseConnection();
		DC.keyImporter();
		// Get the size of an arraylist which a method from databaseConnection
		// returns, and sets a int equals that
		System.out.println("Vi er inde i viewuser");
		String[][] test = DC.eventID();
		int arrayCounter = test[0].length;
		// Creates an dint equals to 0
		int arrayChecker = 0;

		for (int reset = 1; reset < arrayCounter; reset++) {
			System.out.println("Vi er inde i for-loop " + reset + ". gang");
			// Sets every field in a Jtable equals nothing
			CP.getUI().getTable().setValueAt(null, reset, 0);
			CP.getUI().getTable().setValueAt(null, reset, 1);
			CP.getUI().getTable().setValueAt(null, reset, 2);
			CP.getUI().getTable().setValueAt(null, reset, 3);
			CP.getUI().getTable().setValueAt(null, reset, 4);
			CP.getUI().getTable().setValueAt(null, reset, 5);
		}
		// As long as there is something in the arraylists, add it to the Jtable
		while (arrayChecker < arrayCounter) {
			System.out.println("Vi er inde i while-loop " + arrayChecker
					+ ". gang");
			CP.getUI().getTable()
					.setValueAt(test[0][arrayChecker], arrayChecker, 0);
			CP.getUI().getTable()
					.setValueAt(test[1][arrayChecker], arrayChecker, 1);
			CP.getUI().getTable()
					.setValueAt(test[2][arrayChecker], arrayChecker, 2);
			CP.getUI().getTable()
					.setValueAt(test[3][arrayChecker], arrayChecker, 3);
			CP.getUI().getTable()
					.setValueAt(test[4][arrayChecker], arrayChecker, 4);
			CP.getUI().getTable()
					.setValueAt(test[5][arrayChecker], arrayChecker, 5);
			arrayChecker++;
		}
	}

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
				displayWeather();
				displayQuote();
				CP.show(ContainerPanel.quoteAndWeather);
				break;
			case "CalendarList":
				CP.show(ContainerPanel.calendarList);
				break;
			}
		}
	}

	private class QuoteAndWeather implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			CP.show(ContainerPanel.mainMenu);
		}
	}

	private class backToEventList implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			CP.show(ContainerPanel.eventView);
		}
	}

	private class goToCreateEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			CP.show(ContainerPanel.mainMenu);
			CP.show(ContainerPanel.createEvent);
			setComboDates();
		}

	}

	private class UserCreation implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			CP.show(ContainerPanel.mainMenu);
		}
	}

	private class CreateUser implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			CP.show(ContainerPanel.mainMenu);
			DC.keyImporter();
			CP.getUC().getEmailText().getText();
			CP.getUC().getPass().getText();
			CP.getUC().getRepeatPass().getText();
		}
	}

	public long checkDate(String year, String month, String day, String hour,
			String minute) throws ParseException {
		long longToBeReturned = 0;
		String dateToCheck = year + "/" + month + "/" + day + " " + hour + ":"
				+ minute + ":00";
		Date dating = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss",
				Locale.TRADITIONAL_CHINESE).parse(dateToCheck);
		longToBeReturned = dating.getTime();

		return longToBeReturned;
	}

	private class createNewEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String eventName = CP.getAE().getNameField().getText();
			String type = CP.getAE().getTypeCombo().getSelectedItem()
					.toString();
			String locationC = CP.getAE().getLocationCombo().getSelectedItem()
					.toString();
			String locationF = CP.getAE().getLocationField().getText();

			String location = locationC + "" + locationF;
			String startYear = CP.getAE().getStartYear().getSelectedItem()
					.toString();
			String startMonth = CP.getAE().getStartMonth().getSelectedItem()
					.toString();
			String startDay = CP.getAE().getStartDay().getSelectedItem()
					.toString();
			String startHour = CP.getAE().getStartHour().getSelectedItem()
					.toString();
			String startMinute = CP.getAE().getStartMinute().getSelectedItem()
					.toString();
			String endYear = CP.getAE().getEndYear().getSelectedItem()
					.toString();
			String endMonth = CP.getAE().getEndMonth().getSelectedItem()
					.toString();
			String endDay = CP.getAE().getEndDay().getSelectedItem().toString();
			String endHour = CP.getAE().getEndhour().getSelectedItem()
					.toString();
			String endMinute = CP.getAE().getEndMinute().getSelectedItem()
					.toString();
			String Calendar = CP.getAE().getCalendarCombo().getSelectedItem()
					.toString();
			String infoText = CP.getAE().getInfoBox().getText();
			String endTime = endYear + "-" + endMonth + "-" + endDay + " "
					+ endHour + ":" + endMinute + ":00";
			String startTime = startYear + "-" + startMonth + "-" + startDay
					+ " " + startHour + ":" + startMinute + ":00";

			if (!eventName.equals("Enter Event Name") || !eventName.equals("")) {
				if (!type.equals("Choose Type")) {
					try {
						long checkStartTime = checkDate(startYear, startMonth,
								startDay, startHour, startMinute);
						long checkEndTime = checkDate(endYear, endMonth,
								endDay, endHour, endMinute);

						if (checkStartTime < checkEndTime) {
							Date date = new Date();
							if (checkStartTime > date.getTime()) {
								if (!Calendar.equals("Choose Calendar")) {
									if (!infoText
											.equals("If any, enter further information here...")) {
										DC.createNewEvent(type, location,
												startTime, endTime, eventName,
												infoText, Calendar);
									} else {
										JOptionPane
												.showMessageDialog(null,
														"The info text cannot be the default one.");
									}
								} else {
									JOptionPane
											.showMessageDialog(null,
													"You have to select a calendar to which the event belongs");
								}
							} else {
								JOptionPane
										.showMessageDialog(null,
												"You cannot create an event in the past.");
							}
						} else {
							JOptionPane
									.showMessageDialog(null,
											"You cannot have an end-time earlier than start time.");
						}

					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"You have to choose a type for the event.");
				}
			} else {
				JOptionPane.showMessageDialog(null,
						"You have to enter an eventname");
			}

		}
	}

	public void setComboDates() {
		for (int yCount = 2014; yCount < 2038; yCount++) {

			CP.getAE().getStartYear().addItem(yCount);
			CP.getAE().getEndYear().addItem(yCount);
		}
		for (int moCount = 0; moCount < 13; moCount++) {
			CP.getAE().getStartMonth().addItem(moCount);
			CP.getAE().getEndMonth().addItem(moCount);
		}
		for (int dCount = 1; dCount < 32; dCount++) {
			CP.getAE().getStartDay().addItem(dCount);
			CP.getAE().getEndDay().addItem(dCount);
		}
		for (int hCount = 1; hCount < 25; hCount++) {
			CP.getAE().getStartHour().addItem(hCount);
			CP.getAE().getEndhour().addItem(hCount);
		}
		for (int miCount = 1; miCount < 60; miCount++) {
			CP.getAE().getStartMinute().addItem(miCount);
			CP.getAE().getEndMinute().addItem(miCount);
		}
	}

	public static void main(String[] args) throws SQLException, ParseException {
		DatabaseConnection DC = new DatabaseConnection();
		Logic L = new Logic();
		L.checkDate("2014", "9", "5", "6", "9");
		L.checkDate("2014", "9", "6", "6", "9");
		L.checkDate("2014", "12", "4", "6", "9");
	}

	// Create new user
	private class activeChecker implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			// TODO Auto-generated method stub
			String Email = CP.getUC().getEmailText().getText();
			String pass1 = CP.getUC().getPass().getText();
			String pass2 = CP.getUC().getRepeatPass().getText();
			int checkIfActive;
			int checkIfAdmin;
			boolean activeCheck = CP.getUC().getChckbxActive().isSelected();
			boolean adminCheck = CP.getUC().getChckbxAdministrator()
					.isSelected();

			if (pass1.equals(pass2) && !Email.isEmpty() && !pass1.isEmpty()
					&& !pass2.isEmpty()) {

				if (activeCheck == true) {

					checkIfActive = 1;
				} else {
					checkIfActive = 2;

				}
				if (adminCheck == true) {
					checkIfAdmin = 1;
				} else {
					checkIfAdmin = 2;
				}
				DC.CreatedUser(Email, pass1, checkIfActive, checkIfAdmin);

			} else {
				JOptionPane
						.showMessageDialog(
								null,
								"Et felt er tomt eller password indtastningen er forkert",
								"Information", JOptionPane.INFORMATION_MESSAGE);
				System.out.println("Passwords Do not Match");
			}
		}
	}

	// Delete user
	private class deleteUser implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFrame frame = new JFrame("InputDialog");
			String killUser = JOptionPane.showInputDialog(frame,
					"Input Email of user to delete");

			if (killUser == null) {
				JOptionPane.showMessageDialog(null,
						"No Email address detected", "Information",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				DC.deletesRow(killUser, "users", "email");
				viewUser();
			}
		}
	}
	
	private class calendarInactive implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			JFrame frame = new JFrame("InputDialog");
			String killCalendar = JOptionPane.showInputDialog(frame,
					"Input name of calendar to delete");

			if (killCalendar == null) {
				JOptionPane.showMessageDialog(null,
						"No Email address detected", "Information",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				DC.deletesRow(killCalendar, "calendars", "Name");
			}
		}
	}

	private class deleteEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFrame frame = new JFrame("InputDialog");
			String killEvent = JOptionPane.showInputDialog(frame,
					"Input ID of the event to be deleted");

			if (killEvent == null) {
				JOptionPane.showMessageDialog(null, "No EventID detected","Information",
						JOptionPane.INFORMATION_MESSAGE);

			} else {
				DC.deletesRow(killEvent, "events", "eventid");
			}
		}
	}

	
//	activate user
	private class activateUse implements ActionListener {
		public void actionPerformed(ActionEvent e){
			JFrame frame = new JFrame("InputDialog");
			String reActivate = JOptionPane.showInputDialog(frame, "Input Email of user activate");
			
			if (reActivate == null){
				JOptionPane.showMessageDialog (null, "No Email address detected", "Information", JOptionPane.INFORMATION_MESSAGE);
			}
			else{
				DC.activateUse(reActivate, "users", "email");
				viewUser();
			}
		}
	}
	
	private class activateCalendar implements ActionListener {
		public void actionPerformed(ActionEvent e){
			JFrame frame = new JFrame("InputDialog");
			String reActivate = JOptionPane.showInputDialog(frame, "Input Email of user activate");
			
			if (reActivate == null){
				JOptionPane.showMessageDialog (null, "No Email address detected", "Information", JOptionPane.INFORMATION_MESSAGE);
			}
			else{
				DC.activateUse(reActivate, "calendars", "Name");
				viewUser();
			}
		}
	}
	
	private class createCalendar implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int pp = 0;
			int act = 0;
			String eventName = CP.getCL().getNameField().getText();
			String publicPrivate = CP.getCL().getPPCombo().getSelectedItem().toString();
			String active = CP.getCL().getActiveCombo().getSelectedItem().toString();
			if(publicPrivate.equals("Public"))
			{
				pp = 1;
			}
			else
			{
				pp=2;
			}
			if(active.equals("Active"))
			{
				act=1;
			}
			else
			{
				act=2;
			}
			if(DC.checkCalendarName(eventName) == true)
			{
				if(DC.createNewCalender(eventName, pp, act)== true)
				{
					JOptionPane.showMessageDialog (null, "The Calendar has been created!", "Information", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog (null, "The calendar could not be created.", "Information", JOptionPane.INFORMATION_MESSAGE);
				}
				}
				
			else
			{
				JOptionPane.showMessageDialog (null, "Invalid calendarname.", "Information", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	//Activate Event '
	private class activateEvent implements ActionListener {
		public void actionPerformed(ActionEvent e){
			JFrame frame = new JFrame("InputDialog");
			String reActivate = JOptionPane.showInputDialog(frame, "Input EventID of the Event to activate");
			
			if (reActivate == null){
				JOptionPane.showMessageDialog (null, "No EventID detected", "Information", JOptionPane.INFORMATION_MESSAGE);
			}
			else{
				DC.activatesEvent(reActivate);
			}
		}
	}
	
	public void displayWeather(){
		/*String arrayText = "";
	

		int lenght = FM.requestForecast().size();
		for(int i = 0; i < lenght; i++) {
			
			arrayText = arrayText + FM.requestForecast().get(i).getCelsius() + "\n";
			arrayText = arrayText + FM.requestForecast().get(i).getDate() + "\n";
			arrayText = arrayText + FM.requestForecast().get(i).getDesc() + "\n";
		
			
			   CP.getQAW().getWeatherTextArea().setText(arrayText);

	}*/}
	
	public void displayQuote(){
		QModel.updateQuote();
		String stringQText = QModel.getQuote();
		System.out.println(stringQText);
		CP.getQAW().getqTextArea().setText(stringQText);
		
	
		}
	
	private class goToUserCreation implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			CP.show(ContainerPanel.UserCreation);
		}
	}

	private void initializeListeners() {
		CP.getLI().addActionListenerWelcomeScreen(new loginBtn());
		CP.getUI().goToAddUser(new goToUserCreation());
		CP.getMM().addActionListenerMainMenu(new LogOut());
		CP.getUI().goToMainMenu(new btnToMainMenu());
		CP.geteList().goToMainMenu(new btnToMainMenu());
		CP.getNL().goToMainMenu(new btnToMainMenu());
		CP.getQAW().goToMainMenu(new QuoteAndWeather());
		CP.getAE().backListener(new backToEventList());
		CP.getUC().goToMainMenu(new btnToMainMenu());
		CP.geteList().goToAddEvent(new goToCreateEvent());
		CP.getUC().createUser(new activeChecker());
		CP.getUI().deluser(new deleteUser());
		CP.getAE().createEventListener(new createNewEvent());
		CP.getUI().activateUser(new activateUse());
		CP.geteList().deleteEvent(new deleteEvent());
		CP.geteList().setActive(new activateEvent());
		CP.getCL().addCalendarListener(new createCalendar());
		CP.getCL().deleteCalendarListener(new calendarInactive());
		CP.getCL().reActivateListener(new activateCalendar());
		}
}
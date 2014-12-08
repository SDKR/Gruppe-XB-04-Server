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
import model.user.encryptionAES;
import DatabaseLogic.DatabaseConnection;
import GUI.*;

public class Logic {
	DatabaseConnection DC = new DatabaseConnection();
	private ContainerPanel CP;
	private String allKnowingName;
	private int adminID;
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

//	Login button action
//	Checks if login data is correct and shows error message if it's not. 
	private class loginBtn implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String emailInput = CP.getLI().getTextFieldUsername().getText();
			String passwordInput = CP.getLI().getTextFieldPassword().getText();
//			Checking Login detalis
			if (!emailInput.equals("") || !passwordInput.equals("")) {
				if (DC.checkPassword(emailInput, passwordInput) == true) {
					if (DC.checkIfAdmin(emailInput) == true) {
						JOptionPane.showMessageDialog(CP, "Login succesfull!");
						setAllKnowingName(emailInput);
						CP.show(ContainerPanel.mainMenu);
						saveWeather();
						displayWeather();
						displayQuote();
						CP.getLI().getTextFieldUsername().setText("");
						CP.getLI().getTextFieldPassword().setText("");
//			Showing suitable error messages, depending on which if-statement failed
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

//	View user function for GUI
	public void viewUser() {
		// Creates an object of the class databaseconnection
		DatabaseConnection DC = new DatabaseConnection();
		DC.keyImporter();
		// Get the size of an arraylist which a method from databaseConnection
		// returns, and sets a int equals that
		String[][] test = DC.calendarData();
		int arrayCounter = test[0].length;
		// Creates an dint equals to 0
		int arrayChecker = 0;

		for (int reset = 1; reset < arrayCounter; reset++) {
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
	
//	View calendar function for GUI
	public void viewCalendar() {
		// Creates an object of the class databaseconnection
		DatabaseConnection DC = new DatabaseConnection();
		DC.keyImporter();
		// Get the size of an arraylist which a method from databaseConnection
		// returns, and sets a int equals that
		String[][] test = DC.calendarData();
		int arrayCounter = test[0].length;
		// Creates an dint equals to 0
		int arrayChecker = 0;

		for (int reset = 1; reset < arrayCounter; reset++) {
			System.out.println("Vi er inde i for-loop " + reset + ". gang");
			// Sets every field in a Jtable equals nothing
			CP.getCL().getCalendarTable().setValueAt(null, reset, 0);
			CP.getCL().getCalendarTable().setValueAt(null, reset, 1);
			CP.getCL().getCalendarTable().setValueAt(null, reset, 2);
			CP.getCL().getCalendarTable().setValueAt(null, reset, 3);
			CP.getCL().getCalendarTable().setValueAt(null, reset, 4);
		}
		// As long as there is something in the arraylists, add it to the Jtable
		while (arrayChecker < arrayCounter) {
			CP.getCL().getCalendarTable().setValueAt(test[0][arrayChecker], arrayChecker, 0);
			CP.getCL().getCalendarTable().setValueAt(test[1][arrayChecker], arrayChecker, 1);
			CP.getCL().getCalendarTable().setValueAt(test[2][arrayChecker], arrayChecker, 2);
			CP.getCL().getCalendarTable().setValueAt(test[3][arrayChecker], arrayChecker, 3);
			CP.getCL().getCalendarTable().setValueAt(test[4][arrayChecker], arrayChecker, 4);
			arrayChecker++;
		}
	}
	
//	View notes function for GUI
	public void viewNotes() {
		// Creates an object of the class databaseconnection
		DatabaseConnection DC = new DatabaseConnection();
		DC.keyImporter();
		// Get the size of an arraylist which a method from databaseConnection
		// returns, and sets a int equals that
		String[][] test = DC.noteID();
		int arrayCounter = test[0].length;
		// Creates an dint equals to 0
		int arrayChecker = 0;

		for (int reset = 1; reset < arrayCounter; reset++) {
			// Sets every field in a Jtable equals nothing
			
			CP.getNL().getNoteTable().setValueAt(null, reset, 0);
			CP.getNL().getNoteTable().setValueAt(null, reset, 1);
			CP.getNL().getNoteTable().setValueAt(null, reset, 2);
			CP.getNL().getNoteTable().setValueAt(null, reset, 3);
			CP.getNL().getNoteTable().setValueAt(null, reset, 4);
		}
		// As long as there is something in the arraylists, add it to the Jtable
		while (arrayChecker < arrayCounter) {
			CP.getNL().getNoteTable().setValueAt(test[0][arrayChecker], arrayChecker, 0);
			CP.getNL().getNoteTable().setValueAt(test[1][arrayChecker], arrayChecker, 1);
			CP.getNL().getNoteTable().setValueAt(test[2][arrayChecker], arrayChecker, 2);
			CP.getNL().getNoteTable().setValueAt(test[3][arrayChecker], arrayChecker, 3);
			CP.getNL().getNoteTable().setValueAt(test[4][arrayChecker], arrayChecker, 4);
			arrayChecker++;
		}
	}
	
	
//	View events function for GUI
	public void viewEvents() {
		// Creates an object of the class databaseconnection
		DatabaseConnection DC = new DatabaseConnection();
		DC.keyImporter();
		// Get the size of an arraylist which a method from databaseConnection
		// returns, and sets a int equals that
		String[][] test = DC.eventID();
		int arrayCounter = test[0].length;
		// Creates an dint equals to 0
		int arrayChecker = 0;

		for (int reset = 1; reset < arrayCounter; reset++) {
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
	
//	#
	private class manipulateNotes implements ActionListener{
		public void actionPerformed (ActionEvent e)
		{
			String btnClicked = e.getActionCommand();
			switch (btnClicked){
			case "editNote":
				System.out.println("editNote");
				break;
			case "addNote":
				System.out.println("addNote");
				break;
			case "deleteNote":
				System.out.println("deleteNote");
				deleteNote();
				break;
			default:
				break;
			}
		}
	}
	
//	#
	private void addNote()
	{
		String eventID = JOptionPane.showInputDialog("Enter EventID of event to commit note:");
		JFrame addFrame = new JFrame();
		addFrame.setBounds(0, 0, 300, 300);
		
		if(!eventID.equals(""))
		{
//			String newNote = JOptionPane.
		}
		else
		{
			JOptionPane.showMessageDialog(CP, "You have to enter an eventID");
		}
	}
	
//	Recives input from user, checks if it is empty, and then sends it to the DatabaseConnection class
	private void deleteNote()
	{
		String eventID = JOptionPane.showInputDialog("Enter EventID of note to delete");
		if(!eventID.equals(""))
		{
			String stringToBeReturned = DC.deleteNote(eventID, allKnowingName);
			JOptionPane.showMessageDialog(CP, stringToBeReturned);
			
		}
		else
		{
			JOptionPane.showMessageDialog(CP, "No Note has been deleted");
		}
	}
//	#
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
				viewNotes();
				break;

			case "QAWList":
				CP.show(ContainerPanel.quoteAndWeather);
				break;
			case "CalendarList":
				CP.show(ContainerPanel.calendarList);
				viewCalendar();
				break;
			}
		}
	}

//	On button push show event view panel
	private class backToEventList implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			CP.show(ContainerPanel.eventView);
		}
	}

//	On button push show createEvent panel
	private class goToCreateEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
		
			CP.show(ContainerPanel.createEvent);
			setComboDates();
		}

	}

//	This formats the date so it will fit the database correctly
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

//	Create new event button. This takes all the inputs from the 
//	user and puts them into a string
	private class createNewEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String eventName = CP.getAE().getNameField().getText();
			String type = CP.getAE().getTypeCombo().getSelectedItem()
					.toString();
			String locationC = CP.getAE().getLocationField().getText();
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

//		Here the inputs are checked to see if they are correct
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
											.equals("Enter further information here...")) {
										DC.createNewEvent(type, location,
												startTime, endTime, eventName,
												infoText, Calendar);
//		If the inputs are incorrect, an error message is shown
									} else {
										JOptionPane
												.showMessageDialog(null,
														"You must elaborate on what the event is about");
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

//	Logic for the combo buttons in create event.
	public void setComboDates() {
		for (int yCount = 2014; yCount < 2038; yCount++) {

			CP.getAE().getStartYear().addItem(yCount);
			CP.getAE().getEndYear().addItem(yCount);
		}
		for (int moCount = 1; moCount < 13; moCount++) {
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

	// Create new user button, 
	private class createUser implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			String Email = CP.getUC().getEmailText().getText();
			String pass1 = CP.getUC().getPass().getText();
			String pass2 = CP.getUC().getRepeatPass().getText();
			int checkIfActive;
			int checkIfAdmin;
			boolean activeCheck = CP.getUC().getChckbxActive().isSelected();
			boolean adminCheck = CP.getUC().getChckbxAdministrator()
					.isSelected();
			
//	Checks if the inputs are correct, and passwords matching
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
				try {
					DC.CreatedUser(encryptionAES.encrypt(Email), pass1, checkIfActive, checkIfAdmin);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				DC.CreatedUser(Email, pass1, checkIfActive, checkIfAdmin);
//	Clears old input
				CP.getUC().getEmailText().setText("");
				CP.getUC().getPass().setText("");
				CP.getUC().getRepeatPass().setText("");
				
//	If someting is incorrect show error message.
			} else {
				JOptionPane
						.showMessageDialog(
								null,
								"One of the inputs are empty or the password is incorrect",
								"Information", JOptionPane.INFORMATION_MESSAGE);
				System.out.println("Passwords Do not Match");
			}
		}
	}

	// Delete user function
//	Uses input dialogs to receive email input 
	private class deleteUser implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFrame frame = new JFrame("InputDialog");
			String killUser = JOptionPane.showInputDialog(frame,
					"Input Email of user to delete");
//	Checks if input equals null, if not delete/deactivate user
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
	
//	Deactivate calendar
//	Takes input from user 
	private class calendarInactive implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			JFrame frame = new JFrame("InputDialog");
			String killCalendar = JOptionPane.showInputDialog(frame,
					"Input name of calendar to delete");
//	If input equals null show error
			if (killCalendar == null) {
				JOptionPane.showMessageDialog(null,
						"No Email address detected", "Information",
						JOptionPane.INFORMATION_MESSAGE);
//	Else deactivate from calendar		
			} else {
				DC.deletesRow(killCalendar, "calendar", "Name");
			}
		}
	}

//	Delte event
//	Takes input from user i dialog
	private class deleteEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFrame frame = new JFrame("InputDialog");
			String killEvent = JOptionPane.showInputDialog(frame,
					"Input ID of the event to be deleted");
//	if input is empty show error
			if (killEvent == null) {
				JOptionPane.showMessageDialog(null, "No EventID detected","Information",
						JOptionPane.INFORMATION_MESSAGE);
//	Else delete event from database
			} else {
				DC.deletesRow(killEvent, "events", "eventid");
			}
		}
	}

	
//	activate user
//	Takes input from user i dialog
	private class activateUse implements ActionListener {
		public void actionPerformed(ActionEvent e){
			JFrame frame = new JFrame("InputDialog");
			String reActivate = JOptionPane.showInputDialog(frame, "Input Email of user activate");
//	if input is empty show error
			if (reActivate == null){
				JOptionPane.showMessageDialog (null, "No Email address detected", "Information", JOptionPane.INFORMATION_MESSAGE);
			}
//	Else activate user in database
			else{
				DC.activateUse(reActivate, "users", "email");
				viewUser();
			}
		}
	}
	
//	activate calendar
//	Takes input from user i dialog
	private class activateCalendar implements ActionListener {
		public void actionPerformed(ActionEvent e){
			JFrame frame = new JFrame("InputDialog");
			String reActivate = JOptionPane.showInputDialog(frame, "Input Email of user activate");
//	if input is empty show error
			if (reActivate == null){
				JOptionPane.showMessageDialog (null, "No Email address detected", "Information", JOptionPane.INFORMATION_MESSAGE);
			}
//	Else activate calendar i databse.
			else{
				DC.activateUse(reActivate, "calendar", "Name");
				viewUser();
			}
		}
	}
	
//	Create calendar
//	Takes inputs from textboxes 
	private class createCalendar implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int pp = 0;
			int act = 0;
			String calendarName = CP.getCL().getNameField().getText();
			String publicPrivate = CP.getCL().getPPCombo().getSelectedItem().toString();
			String active = CP.getCL().getActiveCombo().getSelectedItem().toString();
			
//	Formats inputs from GUI to usable inputs in database 
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
			
//	Checks if inputs are correct and sends inforation to database
			if(!calendarName.equals("Enter Name here...") && !calendarName.equals(""))
			{
			if(DC.checkCalendarName(calendarName) == true)
			{
				if(DC.createNewCalender(calendarName, pp, act)== true)
				{
					JOptionPane.showMessageDialog (null, "The Calendar has been created!", "Information", JOptionPane.INFORMATION_MESSAGE);
					CP.getCL().getNameField().setText("");
					viewCalendar();
					
				}
//	If inputs are incorrect display errormessage 
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
			else
			{
				JOptionPane.showMessageDialog (null, "You have to enter a Calendarname", "Information", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	//Activate Event '
//	Takes input from user via dialog
	private class activateEvent implements ActionListener {
		public void actionPerformed(ActionEvent e){
			JFrame frame = new JFrame("InputDialog");
			String reActivate = JOptionPane.showInputDialog(frame, "Input EventID of the Event to activate");
			
//	Checks if the input is equal to zero, and if displays error
			if (reActivate == null){
				JOptionPane.showMessageDialog (null, "No EventID detected", "Information", JOptionPane.INFORMATION_MESSAGE);
			}
//	If input is accepted, it is sent to database 
			else{
				DC.activatesEvent(reActivate);
			}
		}
	}
	
//	Takes the weather input from forecast model class
//	and sends it to database
	public void saveWeather(){
		int lenght = FM.requestForecast().size();
	
		for(int i = 0; i < lenght; i++) {
			
			DC.weatherToDB(FM.requestForecast().get(i).getCelsius(), FM.requestForecast().get(i).getDate(), FM.requestForecast().get(i).getDesc());
		}
	}
	
//	Display quote
//	Gets qoute from QModel class and writes it to the textarea in the GUI
	public void displayQuote(){
		QModel.saveQuote();
		QModel.updateQuote();
		String stringQText = QModel.getQuote();
		System.out.println(stringQText);
		CP.getQAW().getqTextArea().setText(stringQText);
		}
	
//	Display weather
//	Gets the weahter and writes it to the textarea
	public void displayWeather(){
		int count = DC.weatherArray().size();
		for(int i = 0; i < count; i ++){
		String weatherString = CP.getQAW().getWeatherTextArea().getText();
		CP.getQAW().getWeatherTextArea().setText(weatherString + DC.weatherArray().get(i));
		}
	}
	
//	Button to shhow the user creation panel
	private class goToUserCreation implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			CP.show(ContainerPanel.UserCreation);
		}
	}
	
//	Button to show the userView panel, also updates the Table
	private class Back implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			CP.show(ContainerPanel.userView);
			viewUser();
		}
	}
	
//	list of initialized listeners 
	private void initializeListeners() {
		CP.getLI().addActionListenerWelcomeScreen(new loginBtn());
		CP.getUI().goToAddUser(new goToUserCreation());
		CP.getMM().addActionListenerMainMenu(new LogOut());
		CP.getUI().goToMainMenu(new btnToMainMenu());
		CP.geteList().goToMainMenu(new btnToMainMenu());
		CP.getNL().goToMainMenu(new btnToMainMenu());
		CP.getQAW().goToMainMenu(new btnToMainMenu());
		CP.getAE().backListener(new backToEventList());
		CP.getUC().goToMainMenu(new Back());
		CP.geteList().goToAddEvent(new goToCreateEvent());
		CP.getUC().createUser(new createUser());
		CP.getUI().deluser(new deleteUser());
		CP.getAE().createEventListener(new createNewEvent());
		CP.getUI().activateUser(new activateUse());
		CP.geteList().deleteEvent(new deleteEvent());
		CP.geteList().setActive(new activateEvent());
		CP.getCL().addCalendarListener(new createCalendar());
		CP.getCL().deleteCalendarListener(new calendarInactive());
		CP.getCL().reActivateListener(new activateCalendar());
		CP.getCL().goToMainMenu(new btnToMainMenu());
		}

//	Sets the allknowingName, this keeps track of who is logged in
	public void setAllKnowingName(String allKnowingName) {
		this.allKnowingName = allKnowingName;
	}
	
}
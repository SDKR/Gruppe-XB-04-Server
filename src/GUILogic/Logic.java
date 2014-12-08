package GUILogic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.Forecast.ForecastModel;
import model.Forecast.ForecastTest;
import model.QOTD.QOTDModel;
import model.calendar.GetCalendarData;
import model.user.encryptionAES;
import DatabaseLogic.DatabaseConnection;
import GUI.*;

public class Logic {
	DatabaseConnection DC = new DatabaseConnection();
	private ContainerPanel CP;
	private String allKnowingName;
	ForecastModel FM = new ForecastModel();
	QOTDModel QModel = new QOTDModel();
	GetCalendarData GCD = new GetCalendarData();


	public Logic() throws SQLException {
		CP = new ContainerPanel();
		initializeListeners();
	}

	public void startApp() {
		DC.keyImporter();
		CP.show(ContainerPanel.loginScreen);
		CP.setVisible(true);
		Timer t = new Timer();
	     MyTask mTask = new MyTask();
	     // This task is scheduled to run every 10 seconds

	     t.scheduleAtFixedRate(mTask, 0, 180000);
	     //3600000
	}

	//Fuction to test the login of a user in the backend
	private class loginBtn implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//Gets the user input and sets equal a string
			String emailInput = CP.getLI().getTextFieldUsername().getText();
			String passwordInput = CP.getLI().getTextFieldPassword().getText();
			//Checks if fields are empty
			if (!emailInput.equals("") || !passwordInput.equals("")) {
				//If password is true
				if (DC.checkPassword(emailInput, passwordInput) == true) {
					//checks if user is admin
					if (DC.checkIfAdmin(emailInput) == true) {
						JOptionPane.showMessageDialog(CP, "Login succesfull!");
						setAllKnowingName(emailInput);
						CP.show(ContainerPanel.mainMenu);
						//Runs weather and quote functions, and sets input fields equal nothing
						
						CP.getLI().getTextFieldUsername().setText("");
						CP.getLI().getTextFieldPassword().setText("");
					}
					//if something goes wrong, print error message in pop-up
					else {
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

	//Private class which implements Action listner.
	private class btnToMainMenu implements ActionListener {
		// When button pushed, show login screen
		public void actionPerformed(ActionEvent e) {
			CP.show(ContainerPanel.mainMenu);
		}
	}

	//View all user function
	public void viewUser() {
		// Creates an object of the class databaseconnection
		DatabaseConnection DC = new DatabaseConnection();
		DC.keyImporter();
		// Get the size of an arraylist which a method from databaseConnection
		// returns, and sets a int equals that
		String[][] test = DC.userData();
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
	
	public void viewCalendar() {
		// Creates an object of the class databaseconnection
		DatabaseConnection DC = new DatabaseConnection();
		DC.keyImporter();
		// Get the size of an arraylist which a method from databaseConnection
		// returns, and sets a int equals that
		System.out.println("Vi er inde i viewuser");
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

		for (int reset = 0; reset < arrayCounter; reset++) {
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
	private class manipulateNotes implements ActionListener{
		public void actionPerformed (ActionEvent e)
		{
			String btnClicked = e.getActionCommand();
			switch (btnClicked){
			case "editNote":
				editNote();
				viewNotes();
				break;
			case "addNote":
				addNote();
				viewNotes();
				break;
			case "deleteNote":
				deleteNote();
				viewNotes();
				break;
			default:
				break;
			}
		}
	}
	private void editNote()
	{
		String eventID = JOptionPane.showInputDialog("Enter EventID of event to edit note:");
		try
		{
			if(!eventID.equals(""))
			{
				String noteChecker = DC.getExistingNote(eventID);;
				String newNote = "";
				if(noteChecker.equals("There is no note added to this eventid"))
				{
					JOptionPane.showMessageDialog(CP, DC.getExistingNote(eventID));
				}
				else
				{
					newNote = JOptionPane.showInputDialog(DC.getExistingNote(eventID));
					JOptionPane.showMessageDialog(CP, DC.editNote(eventID, newNote, allKnowingName));
				}
						
			}
			else
			{
				JOptionPane.showMessageDialog(CP, "You have to enter an eventid");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(CP, "Something went wrong, please try again");
		}
	}
	private void addNote()
	{
		String eventID = JOptionPane.showInputDialog("Enter EventID of event to commit note:");
		try
		{
			if(!eventID.equals(""))
			{
				String newNote = JOptionPane.showInputDialog("Enter new note text:");
				JOptionPane.showMessageDialog(CP, DC.addNewNote(eventID, newNote, allKnowingName));
			}
			else
			{
				JOptionPane.showMessageDialog(CP, "You have to enter an eventID");
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(CP, "No note has been created");
			e.printStackTrace();
		}
	}
	private void deleteNote()
	{
		String eventID = JOptionPane.showInputDialog("Enter EventID of note to delete");
		try
		{
			if(!eventID.isEmpty())
			{
				String stringToBeReturned = DC.deleteNote(eventID, allKnowingName);
				JOptionPane.showMessageDialog(CP, stringToBeReturned);
				
			}
			else
			{
				JOptionPane.showMessageDialog(CP, "No Note has been deleted");
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(CP, "No Note has been deleted");
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

	private class backToEventList implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			CP.show(ContainerPanel.eventView);
		}
	}

	private class goToCreateEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
		
			CP.show(ContainerPanel.createEvent);
			setComboDates();
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
										clearFieldsAddEventMethod();
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
	private void clearFieldsAddEventMethod ()
	{
		CP.getAE().getNameField().setText("");
		CP.getAE().getTypeCombo().setSelectedIndex(0);
		CP.getAE().getLocationField().setText("");
		CP.getAE().getStartYear().setSelectedIndex(0);
		CP.getAE().getStartMonth().setSelectedIndex(0);
		CP.getAE().getStartDay().setSelectedIndex(0);
		CP.getAE().getStartHour().setSelectedIndex(0);
		CP.getAE().getStartMinute().setSelectedIndex(0);
		CP.getAE().getEndYear().setSelectedIndex(0);
		CP.getAE().getEndMonth().setSelectedIndex(0);
		CP.getAE().getEndDay().setSelectedIndex(0);
		CP.getAE().getEndhour().setSelectedIndex(0);
		CP.getAE().getEndMinute().setSelectedIndex(0);
		CP.getAE().getCalendarCombo().setSelectedIndex(0);
		CP.getAE().getInfoBox().setText("Enter further information here...");
	}
	//Clear fields at addEvent
	private class clearFieldsAddEvent implements ActionListener{
		public void actionPerformed (ActionEvent e)
		{
			clearFieldsAddEventMethod();
		}
	}

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

	// Create new user
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
//				Clear fields email, pass, pass2
				CP.getUC().getEmailText().setText("");
				CP.getUC().getPass().setText("");
				CP.getUC().getRepeatPass().setText("");
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
				JOptionPane.showMessageDialog(CP, DC.deletesRowCalendar(killCalendar, "calendar", "Name"));
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
				JOptionPane.showMessageDialog(CP, "The event "+killEvent+" has been set to inactive");
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
				DC.activateUse(reActivate, "calendar", "Name");
				viewUser();
			}
		}
	}
	
	private class createCalendar implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int pp = 0;
			int act = 0;
			String calendarName = CP.getCL().getNameField().getText();
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
	public void saveWeather(){
		int lenght = FM.requestForecast().size();
	
		for(int i = 0; i < lenght; i++) {
			
			DC.weatherToDB(FM.requestForecast().get(i).getCelsius(), FM.requestForecast().get(i).getDate(), FM.requestForecast().get(i).getDesc());
		}
	}
	
	public void displayQuote(){
		QModel.saveQuote();
		QModel.updateQuote();
		String stringQText = QModel.getQuote();
		System.out.println(stringQText);
		CP.getQAW().getqTextArea().setText(stringQText);
		}
	public void displayWeather(){
		int count = DC.weatherArray().size();
		for(int i = 0; i < count; i ++){
		String weatherString = CP.getQAW().getWeatherTextArea().getText();
		CP.getQAW().getWeatherTextArea().setText(weatherString + DC.weatherArray().get(i));
		}
	}
	
	private class goToUserCreation implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			CP.show(ContainerPanel.UserCreation);
		}
	}
	
	private class Back implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			CP.show(ContainerPanel.userView);
			viewUser();
		}
	}
	


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
		CP.getAE().clearFieldListener(new clearFieldsAddEvent());
		CP.getNL().noteListener(new manipulateNotes());
		}

	public void setAllKnowingName(String allKnowingName) {
		this.allKnowingName = allKnowingName;
	}
	
}
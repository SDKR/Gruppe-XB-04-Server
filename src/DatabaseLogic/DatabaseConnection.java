package DatabaseLogic;

import java.io.PrintStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JOptionPane;

import GUI.UserCreation;
import GUILogic.Logic;
import model.QueryBuild.QueryBuilder;
import keyKeeper.*;

public class DatabaseConnection {

	keyKeeper.KeyGetter GK = new keyKeeper.KeyGetter();
	KeyChest KC = new KeyChest();
	QueryBuilder QB = new QueryBuilder();
	UserCreation UC = new UserCreation();

	// Creates the needed information to connect to the database
	// Brug til manuel indtastning af connect info.
	// private String sqlUrl = "jdbc:mysql://localhost:3306/";
	// private String sqlUser = "Asger";
	// private String sqlPasswd = "1darkeldar";

	private String sqlUrl = "jdbc:mysql://localhost:3306/";
	private String sqlUser = "root";
	private String sqlPasswd = "";

	// Creates a statement, resultest and connection
	private java.sql.Statement stmt;
	private ResultSet rs;
	private Connection conn = null;

	// Imports login info keys
	public void keyImporter() {
//		KC.keyImporter();
//		setSqlUrl(KC.getSqlUrl());
//		setSqlUser(KC.getSqlUser());
//		setSqlPasswd(KC.getSqlPasswd());
	}

	public void clearOldCBSData() {
		try {
			QB.deleteHardFrom("events").where("customevent", "=", "1")
					.Execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addingCBSCalendarToDB(String type, String location,
			String start, String end, String name, String text)
			throws SQLException {
		// Her skal v�re 2 switches til at bestemme Hvilken calendar event
		// tilh�rer, og hvilken lokation.
		int locationID = determineLocationID(location);
		int calendarID = determineCalendarID(name);
		int typeID = determineTypeID(type);
		if (!start.contains("9-31") && !end.contains("9-31")) {
			try {
				getConnection();
				doUpdate("insert into cbscalendar.events (type, location, createdBy, start, end, name, text, customevent, CalenderID) values ('"
						+ typeID
						+ "', '"
						+ locationID
						+ "', '1','"
						+ start
						+ "', '"
						+ end
						+ "', '"
						+ name
						+ "', '"
						+ text
						+ "', '1', '" + calendarID + "');");
				conn.close();
				stmt.close();
			}

			catch (SQLException e) {
				e.printStackTrace();
				if (e.getErrorCode() == 1292) {
					throw new SQLException("Date does not exists");
				}
				// Else throw normal exception
				else {
					throw e;
				}
			}
		} else {
			System.out.println("This date does not exists");
		}
	}
	
	public boolean checkIfActiveOrNot(String eventID, String userID){
		if(eventID.equals("1") && userID.equals("1")){
		}	
		return false;
	}

	private int determineTypeID(String type) {
		int intToBeReturned = 0;
		switch (type) {
		case "Lecture":
			intToBeReturned = 1;
			break;
		case "Exercise":
			intToBeReturned = 2;
			break;
		default:
			intToBeReturned = 3;
			break;
		}
		return intToBeReturned;
	}

	private int determineCalendarID(String course) {
		int intToBeReturned = 0;
		switch (course) {
		case "Distribuerede systemer (LA)":
			intToBeReturned = 11;
			break;
		case "Ledelse af IS - forandring, innovation og viden (XB)":
			intToBeReturned = 12;
			break;
		case "Ledelse af IS - forandring, innovation og viden (LA)":
			intToBeReturned = 14;
			break;
		case "Virksomhedens �konomiske styring (3)":
			intToBeReturned = 15;
			break;
		case "Makro�konomi (XB)":
			intToBeReturned = 9;
			break;
		case "Makro�konomi (XA)":
			intToBeReturned = 8;
			break;
		case "Ledelse af IS - forandring, innovation og viden (XA)":
			intToBeReturned = 13;
			break;
		case "Makro�konomi (LA)":
			intToBeReturned = 10;
			break;
		default:
			intToBeReturned = 1;
		}
		return intToBeReturned;
	}

	private int determineLocationID(String location) {
		int intToBeReturned = 0;
		char position1 = location.charAt(0);
		char position2 = location.charAt(1);
		String locationID = position1 + "" + position2;
		if (locationID.equals("Ks")) {
			intToBeReturned = 7;
		} else if (locationID.equals("FH")) {
			intToBeReturned = 3;
		} else if (locationID.equals("SP")) {
			intToBeReturned = 4;
		} else if (locationID.equals("HO")) {
			intToBeReturned = 5;
		} else if (locationID.equals("PH")) {
			intToBeReturned = 6;
		} else if (locationID.startsWith("D")) {
			intToBeReturned = 8;
		} else if (locationID.startsWith("Fa")) {
			intToBeReturned = 9;
		} else {
			intToBeReturned = 10;
		}
		return intToBeReturned;
	}

	public void createNewEvent(String type, String location, String start,
			String end, String name, String text, String calendarString) {
		int calendarID = determineCalendarID(calendarString);
		int locationID = determineLocationID(location);
		int typeID = determineTypeID(type);
		try {
			doUpdate("insert into cbscalendar.events (type, location, createdby, start, end, name, text, customevent, CalendarID) VALUES ('"
					+ typeID
					+ "', '"
					+ locationID
					+ "', '1', '"
					+ start
					+ "', '"
					+ end
					+ "', '"
					+ name
					+ "', '"
					+ text
					+ "', '2', '" + calendarID + "');");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Method to test connection which returns false
	public boolean TestConnection() {
		try {
			// Get Connection
			getConnection();

			// If connection established before 5000 miliseconds - Succes!
			if (conn.isValid(5000)) {
				JOptionPane.showMessageDialog(null,
						"You have succesfully connected to database!");
			}
			// If error, print exception in dialogbox
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Could not connect to database due to: " + e.getMessage());
		}
		return false;
	}

	public boolean userAuthenticating(String userName, String password,
			String isActive) {
		boolean booleanToBeReturned = false;
		try {

			getConnection();
			stmt = conn.createStatement();
			rs = stmt
					.executeQuery("select password, active from cbscalendar.users where email = '"
							+ userName + "';");
			while (rs.next()) {
				booleanToBeReturned = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return booleanToBeReturned;
	}

	public boolean userIsActive(String userName, String isActive) {
		boolean activeChecker = false;
		try {
			getConnection();
			stmt = conn.createStatement();
			rs = stmt
					.executeQuery("select active from cbscalendar.users where email = '"
							+ userName + "';");
			while (rs.next()) {
				String isAccountActive = rs.getString("Active");
				if (isAccountActive.equals("1")) {
					activeChecker = true;
				} else {
					System.out.println("Din bruger virker ikke fuckhovede!");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return activeChecker;
	}

	public boolean userPasswordCheck(String userName, String password) {
		boolean passwordChecker = false;
		try {
			getConnection();
			stmt = conn.createStatement();
			rs = stmt
					.executeQuery("select password from cbscalendar.users where email = '"
							+ userName + "';");
			while (rs.next()) {
				String accountPassword = rs.getString("password");
				if (accountPassword.equals(password)) {
					passwordChecker = true;
				} else {
					System.out.println("Password'et var forkert tard!");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return passwordChecker;
	}

	/*************************
	 * Arraylist to bugtable *
	 ************************/
	// Creates a method returning a array list, and receives nothing
	public String[][] arrayID() {
		String[] headerNames = { "userid", "email", "active", "created",
				"password", "Admin" };
		int rowCounter = 0;
		try {
			getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT userid FROM cbscalendar.users;");
			while (rs.next()) {
				rowCounter++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(rowCounter);
		String[][] doubleArray = new String[6][rowCounter];
		System.out.println("Lige efter String array er blevet oprettet");
		for (int headerCounter = 0; headerCounter < 6; headerCounter++) {
			System.out.println("inde I starten af for loopet " + headerCounter
					+ ". gang");
			ArrayList<Object> resultArray = new ArrayList<Object>();
			try {
				int otherCounter = 0;
				getConnection();
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select " + headerNames[headerCounter]
						+ " from cbscalendar.users");
				while (rs.next()) {
					doubleArray[headerCounter][otherCounter] = rs
							.getString(headerNames[headerCounter]);
					otherCounter++;
				}
				closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("inde I slutningen af for loopet "
					+ headerCounter + ". gang");
		}
		return doubleArray;
	}

	public String[][] eventID() {
		String[] headerNames = { "eventid", "type", "location", "createdby",
				"start", "end", "name", "text", "customevent", "CalenderID" };
		int rowCounter = 0;
		try {
			getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT eventid FROM cbscalendar.events;");
			while (rs.next()) {
				rowCounter++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(rowCounter);
		String[][] doubleArray = new String[10][rowCounter];
		System.out.println("Lige efter String array er blevet oprettet");
		for (int headerCounter = 0; headerCounter < 10; headerCounter++) {
			System.out.println("inde I starten af for loopet " + headerCounter
					+ ". gang");
			try {
				int otherCounter = 0;
				getConnection();
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select " + headerNames[headerCounter]
						+ " from cbscalendar.events");
				while (rs.next()) {
					doubleArray[headerCounter][otherCounter] = rs
							.getString(headerNames[headerCounter]);
					otherCounter++;
				}
				closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("inde I slutningen af for loopet "
					+ headerCounter + ". gang");
		}
		return doubleArray;
	}

	// Creates a method which returns a int, and recieves one input
	public int doUpdate(String Update) throws SQLException {
		// Establish connection to database
		getConnection();
		// Creates an int, and sets to 0
		int temp = 0;

		try {
			// Creates a method for executing updating statements, which
			// recieves the input
			stmt = conn.createStatement();
			temp = stmt.executeUpdate(Update);
		} catch (SQLException ex) {
			ex.printStackTrace();
			// If errorcode equals 1062, it is duplicate entry
			if (ex.getErrorCode() == 1062) {
				throw new SQLException("Duplicate entry");
			}
			// Else throw normal exception
			else {
				throw ex;
			}
		}
		// If stamement is not equals null, close stament.
		finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
					stmt = null;
					sqlEx.printStackTrace();
				}
			}
		}
		// Returns int
		return temp;
	}

	// Establish connection to database
	private void getConnection() throws SQLException {
		conn = DriverManager.getConnection(sqlUrl, sqlUser, sqlPasswd);
	}

	// Method to close connection, resultset and statement
	private void closeConnection() throws SQLException {
		conn.close();
		rs.close();
		stmt.close();
	}

	public boolean checkPassword(String emailInput, String passwordInput) {
		boolean passwordChecker = false;
		try {
			getConnection();
			stmt = conn.createStatement();
			rs = stmt
					.executeQuery("select password from cbscalendar.users where email = '"
							+ emailInput + "';");
			while (rs.next()) {
				String accountPassword = rs.getString("password");
				if (accountPassword.equals(passwordInput) && checkIfInactive(emailInput).equals("1") ) {
					passwordChecker = true;
				} else {
					System.out.println("Wrong Password/User Inactive!");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return passwordChecker;
	}
	
	public String checkIfInactive(String emailInput){
		String activeMaybe = "";
		try{
			getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select active from cbscalendar.users where email = '"
							+ emailInput + "';");
while(rs.next()){
	activeMaybe = rs.getString("active");
	
}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return activeMaybe;
	}

	public boolean checkIfAdmin(String emailInput) {
		boolean isAdmin = false;
		String adminID = "";
		try {
			getConnection();
			stmt = conn.createStatement();
			rs = stmt
					.executeQuery("select Admin from cbscalendar.users where email = '"
							+ emailInput + "';");
			while (rs.next()) {
				adminID = rs.getString("Admin");
				System.out.println(adminID);
			}
			if (adminID.equals("1")) {
				isAdmin = true;
			} else {
				isAdmin = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isAdmin;
	}

	// Create user
	public String CreatedUser(String EmailText, String pass, int checkIfActive,
			int checkIfAdmin) {
		String stringToBeReturned = "";
		String stringResultChecker = "";

		try {
			getConnection();
			stmt = conn.createStatement();
			rs = stmt
					.executeQuery("select * from cbscalendar.users where email = '"
							+ EmailText + "';");
			while (rs.next()) {

				stringResultChecker = rs.getString("Email");

			}
			if (!stringResultChecker.equals("")) {
				stringToBeReturned = "Fejl";
				JOptionPane.showMessageDialog(null, "The Email already exists",
						"Error", JOptionPane.ERROR_MESSAGE);
			} else {
				doUpdate("insert into cbscalendar.users(email, active, password, admin) values('"
						+ EmailText
						+ "', '"
						+ checkIfActive
						+ "', '"
						+ pass
						+ "', '" + checkIfAdmin + "');");
				JOptionPane.showMessageDialog(null,
						"The user has been created", "Success",
						JOptionPane.INFORMATION_MESSAGE);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return stringToBeReturned;
	}

	// Delete user (soft)
	public String deletesRow(String killRow, String table, String columnName) {
		String stringResultChecker = "";
		String stringIsAllreadyOff = "";
		String stringToBeReturned = "";

		try {

			getConnection();
			stmt = conn.createStatement();
			rs = stmt

			.executeQuery("select * from cbscalendar."+table+" where "+columnName+" = '"
					+ killRow + "';");
			while (rs.next()) {

				stringResultChecker = rs.getString(columnName);
				stringIsAllreadyOff = rs.getString("active");

			}

			if (stringResultChecker.equals("")) {

				JOptionPane.showMessageDialog(null, "The "+columnName+" doesn't exist",
						"Error", JOptionPane.ERROR_MESSAGE);

			} else if (stringIsAllreadyOff.equals("2")) {
				JOptionPane.showMessageDialog(null,
						"The "+columnName+" is already inactive", "Error",
						JOptionPane.ERROR_MESSAGE);
			}

			else {

				doUpdate("update cbscalendar."+table+" set active='2' where "+columnName+"='"
						+ killRow + "';");
				JOptionPane.showMessageDialog(null, "The "+killRow+" is now inactive",
						"Error", JOptionPane.INFORMATION_MESSAGE);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return stringToBeReturned;
	}
	
//	Activate user
	public String activateUse(String reActivate, String table, String column){

	
		String stringResultChecker = "";
		String stringIsAllreadyOn = "";
		String stringToBeReturned = "";

		try {

			getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from cbscalendar."+table+" where "+column+" = '"
					+ reActivate + "';");
	while (rs.next()) {

		stringResultChecker = rs.getString(column);
		stringIsAllreadyOn = rs.getString("active");
		
	}
	
	if (stringResultChecker.equals("")) {

	JOptionPane.showMessageDialog (null, "The "+column+" doesn't exist", "Error", JOptionPane.ERROR_MESSAGE);
				
			}	
	else if(stringIsAllreadyOn.equals("1")){
		JOptionPane.showMessageDialog (null, "The "+column+" is already active", "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	else{
			
			doUpdate("update cbscalendar."+table+" set active='1' where "+column+"='"+reActivate+"';");
			JOptionPane.showMessageDialog (null, "The "+column+" is now active", "Error", JOptionPane.INFORMATION_MESSAGE);
			
	}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
return stringToBeReturned;
		
	}
	
	public String activatesEvent(String reActivate){
		String stringResultChecker = "";
		String stringIsAllreadyOn = "";
		String stringToBeReturned = "";

		try {

			getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from cbscalendar.events where EventID = '"
					+ reActivate + "';");
	while (rs.next()) {

		stringResultChecker = rs.getString("EventID");
		stringIsAllreadyOn = rs.getString("Active");
		
	}
	
	if (stringResultChecker.equals("")) {

	JOptionPane.showMessageDialog (null, "The EventID doesn't exist", "Error", JOptionPane.ERROR_MESSAGE);
				
			}	
	else if(stringIsAllreadyOn.equals("1")){
		JOptionPane.showMessageDialog (null, "The Event is already active", "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	else{
			
			doUpdate("update cbscalendar.event set active='1' where eventID='"+reActivate+"';");
			JOptionPane.showMessageDialog (null, "The Event is now active", "Error", JOptionPane.INFORMATION_MESSAGE);
			
	}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return stringToBeReturned;
		
	}

	public boolean checkCalendarName(String eventName) {
		boolean booleanToBeReturned = false;
		String resultSetHolder ="";
		try{
			getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from cbscalendar.calendars where Name = '"
						+ eventName + "';");
				while (rs.next()) {
					resultSetHolder = rs.getString("Name");
				}
		if(resultSetHolder.equals(""))
		{
			booleanToBeReturned = true;
		}
		else
		{
			//Do nothing if name already exists
		}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return booleanToBeReturned;
	}
	
	public void setSqlUrl(String sqlUrl) {
		this.sqlUrl = sqlUrl;
	}

	public void setSqlUser(String sqlUser) {
		this.sqlUser = sqlUser;
	}

	public void setSqlPasswd(String sqlPasswd) {
		this.sqlPasswd = sqlPasswd;
	}

	public boolean createNewCalender(String eventName, int publicPrivate,
			int active) {
		boolean booleanToBeReturned = false;
		try {
			doUpdate("insert into cbscalendar.calendars (Name, Active, CreatedBy, PrivatePublic) Values ('"+eventName+"', '"+active+"', '1', '"+publicPrivate+"');");
			booleanToBeReturned = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return booleanToBeReturned;
	}
}

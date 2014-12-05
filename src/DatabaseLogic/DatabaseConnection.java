package DatabaseLogic;

import java.io.PrintStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JOptionPane;

import GUI.UserCreation;
import GUILogic.Logic;
import SwitchLogic.Methods.Weather;
import model.Model;
import model.Forecast.ForecastModel;
import model.QueryBuild.QueryBuilder;
import model.user.encryptionAES;
import keyKeeper.*;

public class DatabaseConnection extends Model {

	keyKeeper.KeyGetter GK = new keyKeeper.KeyGetter();
	KeyChest KC = new KeyChest();
	QueryBuilder QB = new QueryBuilder();
	UserCreation UC = new UserCreation();
	ForecastModel FM = new ForecastModel();
	

	// Creates the needed information to connect to the database
	// Brug til manuel indtastning af connect info.
	// private String sqlUrl = "jdbc:mysql://localhost:3306/";
	// private String sqlUser = "Asger";
	// private String sqlPasswd = "1darkeldar";

	private String sqlUrl = "";
	private String sqlUser = "";
	private String sqlPasswd = "";

	// Creates a statement, resultest and connection
	private java.sql.Statement stmt;
	private ResultSet rs;
	private Connection conn = null;

	public DatabaseConnection(){
		KC.keyImporter();
		setSqlUrl(KC.getSqlUrl());
		setSqlUser(KC.getSqlUser());
		setSqlPasswd(KC.getSqlPasswd());
	}
	// Imports login info keys
	public void keyImporter() {
		KC.keyImporter();
		setSqlUrl(KC.getSqlUrl());
		setSqlUser(KC.getSqlUser());
		setSqlPasswd(KC.getSqlPasswd());
	}

	public void clearOldCBSData() {
		try {
			QB.deleteHardFrom("events").where("customevent", "=", "1").Execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addingCBSCalendarToDB(String activityId, String eventid, String type, String location,
			String start, String end, String name, String text)
			throws SQLException {
		// Her skal v�re 2 switches til at bestemme Hvilken calendar event
		// tilh�rer, og hvilken lokation.
		int locationID = determineLocationID(location);
		int calendarID = determineCalendarID(activityId);
		int typeID = determineTypeID(type);
		String typeIDS = Integer.toString(typeID);
		String locationIDS = Integer.toString(locationID);
		String calendarIDS = Integer.toString(calendarID);
		if (!start.contains("9-31") && !end.contains("9-31")) {
			try {
				getConnection();
				String[] fields = {"activityid", "cbsEventId", "type", "location", "locationName", "createdby", "start", "end", "name", "text", "customevent", "CalendarID"};
				String[] values = {activityId, eventid, typeIDS, locationIDS, location, "1", start, end, name, text, "1", calendarIDS};
				QB.insertInto("events", fields).values(values).Execute();		
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
	
	private boolean checkIfActiveOrNot(String userID){
		if(userID.equals("1")){
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
		case "BINTO1035U_XA_E14":
			intToBeReturned = 1;
			break;
		case "BINTO1035U_XB_E14":
			intToBeReturned = 2;
			break;
		case "BINTO1035U_LA_E14":
			intToBeReturned = 3;
			break;
		case "BINTO1067U_LA_E14":
			intToBeReturned = 4;
			break;
		case "BINTO1056U_XB_E14":
			intToBeReturned = 5;
			break;
		case "BINTO1056U_XA_E14":
			intToBeReturned = 6;
			break;
		case "BINTO1056U_LA_E14":
			intToBeReturned = 7;
			break;
		case "BINTO1051U_LA_E14":
			intToBeReturned = 8;
			break;
		default:
			intToBeReturned = 9;
		}
		return intToBeReturned;
	}

	private int determineLocationID(String location) {
		int intToBeReturned = 0;
		char position1 = location.charAt(0);
		char position2 = location.charAt(1);
		String locationID = position1 + "" + position2;
		if (locationID.equals("Ks")) {
			intToBeReturned = 5;
		} else if (locationID.equals("FH")) {
			intToBeReturned = 1;
		} else if (locationID.equals("SP")) {
			intToBeReturned = 2;
		} else if (locationID.equals("HO")) {
			intToBeReturned = 3;
		} else if (locationID.equals("PH")) {
			intToBeReturned = 4;
		} else if (locationID.startsWith("D")) {
			intToBeReturned = 6;
		} else if (locationID.startsWith("Fa")) {
			intToBeReturned = 7;
		} else {
			intToBeReturned = 8;
		}
		return intToBeReturned;
	}

	public void createNewEvent(String type, String location, String start,
			String end, String name, String text, String calendarString){
		System.out.println("flute");
		int calendarID = determineCalendarID(calendarString);
		int locationID = determineLocationID(location);
		int typeID = determineTypeID(type);
		System.out.println("Dillermand");
		try {
			doUpdate("insert into cbscalendar.events (type, location, locationName, createdby, start, end, name, text, customevent, CalendarID) VALUES ('"
					+ typeID
					+ "', '"
					+ locationID
					+ "', '"+location+"', '1', '"
					+ start
					+ "', '"
					+ end
					+ "', '"
					+ name
					+ "', '"
					+ text
					+ "', '2', '" + calendarID + "');");
		} catch (SQLException e) {
			e.printStackTrace();
		}
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

	// Creates a method returning a array list, and receives nothing
	public String[][] calendarData() {
		String[] headerNames = { "CalendarID", "Name", "Active", "CreatedBy",
				"PrivatePublic"};
		int rowCounter = 0;
		String[] useridValue = {"CalendarID"};
		try {
			resultSet = QB.selectFrom(useridValue, "calendar").all().ExecuteQuery();
			while (resultSet.next()) {
				rowCounter++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(rowCounter);
		String[][] doubleArray = new String[5][rowCounter];
		System.out.println("Lige efter String array er blevet oprettet");
		for (int headerCounter = 0; headerCounter < 6; headerCounter++) {
			System.out.println("inde I starten af for loopet " + headerCounter
					+ ". gang");
			try {
				int otherCounter = 0;
				getConnection();
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select " + headerNames[headerCounter] + " from cbscalendar.calendar");
				while (rs.next()) {
					doubleArray[headerCounter][otherCounter] = rs.getString(headerNames[headerCounter]);
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
			String[] passwordValues = {"password"};
			resultSet = QB.selectFrom(passwordValues, "users").where("email", "=", emailInput).ExecuteQuery();
			while (resultSet.next()) {
				String accountPassword = resultSet.getString("password");
				if (encryptionAES.encrypt(passwordInput).equals(accountPassword) && checkIfInactive(emailInput).equals("1") ) {
					passwordChecker = true;
				} else {
					System.out.println("Wrong Password/User Inactive!");
				}
			}
		} catch (Exception e ) {
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
			rs = stmt.executeQuery("select * from cbscalendar.calendar where Name = '"
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

	public boolean createNewCalender(String eventName, int publicPrivate,
			int active) {
		boolean booleanToBeReturned = false;
		try {
			doUpdate("insert into cbscalendar.calendar (Name, Active, CreatedBy, PrivatePublic) Values ('"+eventName+"', '"+active+"', '1', '"+publicPrivate+"');");
			booleanToBeReturned = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return booleanToBeReturned;
	}
	
	public ArrayList<String> weatherArray(){
		ArrayList<String> arrayToBeReturned = new ArrayList<String>();
		String resultSetHolder = "";
		try{
		getConnection();
		stmt = conn.createStatement();
		rs = stmt.executeQuery("select * from cbscalendar.weathertable;");
			while (rs.next()) {
				resultSetHolder = "WeatherID: "+rs.getString("weatherid") +" WeatherDate: "+rs.getString("weatherdate")+" Weatherdesc: "+rs.getString("weatherdesc")+" WeatherDegrees: "+rs.getString("weatherDegrees");
				arrayToBeReturned.add(resultSetHolder);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return arrayToBeReturned;
	}
	



	public void weatherToDB(String celsius, String date, String desc) {
		try {
			doUpdate("insert into cbscalendar.weathertable (weatherdate, weatherdegrees, weatherdesc) values ('"+date+"', '"+celsius+"','"+desc+"' );");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}

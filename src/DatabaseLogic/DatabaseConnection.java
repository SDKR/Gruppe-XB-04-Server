package DatabaseLogic;

import java.io.PrintStream;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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

	 private String sqlUrl = "";
	 private String sqlUser = "";
	 private String sqlPasswd = "";
	 private String dbNAme = "cbscalendar";

//	private String sqlUrl = "";
//	private String sqlUser = "";
//	private String sqlPasswd = "";

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

//	#
	public void clearOldCBSData() {
		try {
			QB.deleteSoftFrom("events").where("customevent", "=", "1").Execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
//	Takes variables processed in logic and writes them to database
//	Thus removing user from calendar
	public boolean deleteFromCalendar(String resultSetStringCalendarID, String resultSetStringEmailID){
		boolean booleanToBeReturned = false;
		try{
			getConnection();
			doUpdate("delete from "+getDbNAme()+".userevents where userid='"+resultSetStringEmailID+"' and CalendarID='"+resultSetStringCalendarID+"';");
			booleanToBeReturned = true;
		}
			catch (SQLException e) {
				e.printStackTrace();
		}
		return booleanToBeReturned;
}

//	Takes variables processed in logic and writes them to database
//	Here a calendar is added to the database.
	public void addingCBSCalendarToDB(String activityId, String type, String location,
			String start, String end, String name)
			throws SQLException {
		// Her skal v�re 2 switches til at bestemme Hvilken calendar event
		// tilh�rer, og hvilken lokation.
		int calendarID = determineCalendarID(activityId);
		int typeID = determineTypeID(type);
		String typeIDS = Integer.toString(typeID);
		String calendarIDS = Integer.toString(calendarID);
		if (!start.contains("9-31") && !end.contains("9-31")) {
//	If true try writing to database
			try {
				getConnection();
				String[] fields = {"activityid", "type", "location", "createdby", "start", "end", "name", "customevent", "CalendarID"};
				String[] values = {activityId, typeIDS, location, "1", start, end, name, "1", calendarIDS};
				QB.insertInto("events", fields).values(values).Execute();
				
			}
//	If error found throw 
			catch (SQLException e) {
				e.printStackTrace();
				if (e.getErrorCode() == 1292) {
					throw new SQLException("Date does not exist");
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
	
//	#
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

//	Sets cbs calendar id's for database.
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

//	Sets location id's for database 
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

//	Takes event variables processed in logic and writes them to database
	public void createNewEvent(String type, String location, String start,
			String end, String name, String text, String calendarString){
		String calendarID = "";
		String[] idValues = {"CalendarID"};
		try {
			resultSet = QB.selectFrom(idValues, "calendar").where("name", "=", calendarString).ExecuteQuery();
			while(resultSet.next())
			{
				calendarID = resultSet.getString("CalendarID");
				
			}
		} catch (SQLException e1) {
		}	
		int typeID = determineTypeID(type);
		System.out.println(calendarString);
		System.out.println(calendarID);
		try {
			doUpdate("insert into "+getDbNAme()+".events (type, location, createdby, start, end, name, customevent, CalendarID) VALUES ('"
					+ typeID
					+ "', '"+location+"', '1', '"
					+ start
					+ "', '"
					+ end
					+ "', '"
					+ name
					+ "', '2', '" + calendarID + "');");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//	Takes the user authenticate variables processed in logic and writes them to database
	public boolean userAuthenticating(String userName, String password,
			String isActive) {
		boolean booleanToBeReturned = false;
		try {

			getConnection();
			stmt = conn.createStatement();
			rs = stmt
					.executeQuery("select password, active from "+getDbNAme()+".users where email = '"
							+ userName + "';");
			while (rs.next()) {
				booleanToBeReturned = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return booleanToBeReturned;
	}

//	Takes user is active variables processed in logic and writes them to database 
	public boolean userIsActive(String userName, String isActive) {
		boolean activeChecker = false;
		try {
			getConnection();
			stmt = conn.createStatement();
			rs = stmt
					.executeQuery("select active from "+getDbNAme()+".users where email = '"
							+ userName + "';");
			while (rs.next()) {
				String isAccountActive = rs.getString("Active");
				if (isAccountActive.equals("1")) {
					activeChecker = true;
				} else {
					System.out.println("The user is not on");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return activeChecker;
	}

//	After the input is checked in GUI.Logic the login information in matched against the database
//	Errors are printed if it fails
	public boolean userPasswordCheck(String userName, String password) throws Exception {
		boolean passwordChecker = false;
		try {
			getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select password from "+getDbNAme()+".users where email = '"+ userName + "';");
			while (rs.next()) {
				String accountPassword = rs.getString("password");
				if (accountPassword.equals(encryptionAES.encrypt(password))) {
					passwordChecker = true;
				} else {
					System.out.println("The password is incorrect");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return passwordChecker;
	}
	
//	Gets all the calendar data from the database and returns it as doubleArray#
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
		for (int headerCounter = 0; headerCounter < 5; headerCounter++) {
			System.out.println("inde I starten af for loopet " + headerCounter
					+ ". gang");
			try {
				int otherCounter = 0;
				getConnection();
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select " + headerNames[headerCounter] + " from "+getDbNAme()+".calendar");
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
	

//	Gets all the events from the database and returns it as doubleArray
	public String[][] userData() {
		String[] headerNames = { "userid", "email", "active", "created",
				"password", "admin"};
		int rowCounter = 0;
		String[] useridValue = {"userid"};
		try {
			resultSet = QB.selectFrom(useridValue, "users").all().ExecuteQuery();
			while (resultSet.next()) {
				rowCounter++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String[][] doubleArray = new String[6][rowCounter];
		for (int headerCounter = 0; headerCounter < 6; headerCounter++) {
			try {
				int otherCounter = 0;
				getConnection();
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select " + headerNames[headerCounter] + " from "+getDbNAme()+".users");
				while (rs.next()) {
					doubleArray[headerCounter][otherCounter] = rs.getString(headerNames[headerCounter]);
					otherCounter++;
				}
				closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return doubleArray;
	}

	public String[][] eventID() {
		String[] headerNames = { "eventid", "type", "location", "createdby",
				"start", "end", "name", "customevent", "CalendarID", "active" };
		int rowCounter = 0;
		try {
			resultSet = QB.selectFrom("events").where("active", "=", "1").ExecuteQuery();	
			while (resultSet.next()) {
				rowCounter++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(rowCounter);
		String[][] doubleArray = new String[9][rowCounter];
		for (int headerCounter = 0; headerCounter < 9; headerCounter++) {
			try {
				int otherCounter = 0;
				getConnection();
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select " + headerNames[headerCounter]+ " from "+getDbNAme()+".events where active = '1'");
				while (rs.next()) {
					doubleArray[headerCounter][otherCounter] = rs.getString(headerNames[headerCounter]);
					otherCounter++;
				}
				closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return doubleArray;
	}
	
//	Gets all the notes from the database and returns it as doubleArray
	public String[][] noteID() {
		String[] headerNames = { "noteid", "createdBy", "text", "dateTime",
				"eventid"};
		int rowCounter = 0;
		try {
			resultSet = QB.selectFrom("notes").all().ExecuteQuery();
			while (resultSet.next()) {
				rowCounter++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String[][] doubleArray = new String[5][rowCounter];
		for (int headerCounter = 0; headerCounter < 5; headerCounter++) {
			try {
				int otherCounter = 0;
				getConnection();
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select " + headerNames[headerCounter]+ " from "+getDbNAme()+".notes order by noteid desc;");
				while (rs.next()) {
					doubleArray[headerCounter][otherCounter] = rs.getString(headerNames[headerCounter]);
					
					System.out.println(doubleArray[headerCounter][otherCounter]+" " + headerCounter+" "+otherCounter);
					
					otherCounter++;
					
				}
				closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return doubleArray;
	}

//	#
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

//	Checks if password is correct
//	Pulls password from database where input email is found, 
//	and matches it with user input. Error is printed if it dosen't match.
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

//	Checks if user is set to inactive in database by looking at the active number (1 or 2)
//	Active = 1, Inactive = 2.
	public String checkIfInactive(String emailInput){
		String activeMaybe = "";
		try{
			getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select active from "+getDbNAme()+".users where email = '"
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

//	Checks if a user is admin by looking at the admin number (1 or 2)
//	Admin = 1, User = 2.
	public boolean checkIfAdmin(String emailInput) {
		boolean isAdmin = false;
		String adminID = "";
		try {
			getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select Admin from "+getDbNAme()+".users where email = '"	+ emailInput + "';");
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
//	Takes processed input from logic and inserts it into database.users
//	Also checks if input is empty and returns error/information message
	public String CreatedUser(String EmailText, String pass, int checkIfActive,
			int checkIfAdmin) {
		String stringToBeReturned = "";
		String stringResultChecker = "";

		try {
			getConnection();
			stmt = conn.createStatement();
			rs = stmt
					.executeQuery("select * from "+getDbNAme()+".users where email = '"
							+ EmailText + "';");
			while (rs.next()) {

				stringResultChecker = rs.getString("Email");

			}
			if (!stringResultChecker.equals("")) {
				stringToBeReturned = "Fejl";
				JOptionPane.showMessageDialog(null, "The Email already exists",
						"Error", JOptionPane.ERROR_MESSAGE);
			} else {
				doUpdate("insert into "+getDbNAme()+".users(email, active, password, admin) values('"
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
	
	public String deletesRowCalendar(String killRow, String table, String columnName) {
		String stringResultChecker = "";
		String stringIsAllreadyOff = "";
		String stringToBeReturned = "";
		String customID = "";

		try {
			String[] calendarValues = {"Name", "active","customid"};
			resultSet = QB.selectFrom(calendarValues, "calendar").where("Name", "=", killRow).ExecuteQuery();
			while (resultSet.next()) {

				stringResultChecker = resultSet.getString(columnName);
				stringIsAllreadyOff = resultSet.getString("active");
				customID = resultSet.getString("customid");
				System.out.println(customID);
			}

			if (stringResultChecker.equals("")) {

				stringToBeReturned = "The "+columnName+" doesn't exist";
				
			} else if (stringIsAllreadyOff.equals("2")) {
				
				stringToBeReturned = "The "+columnName+" is already inactive";
			}
			else if(customID.equals("1"))
			{
				stringToBeReturned = "You cannot delete calendars imported from cbs!";
			}
			else {

				doUpdate("update "+getDbNAme()+"."+table+" set active='2' where "+columnName+"='"
						+ killRow + "';");
				stringToBeReturned = "The "+killRow+" is now inactive";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stringToBeReturned;
	}

	// Delete user (soft)
	// Soft deletes a user by input from admin.
	// Checks if input eqals anything and if user is already deactivated.
	// Returns error or information message
	public String deletesRow(String killRow, String table, String columnName) {
		String stringResultChecker = "";
		String stringIsAllreadyOff = "";
		String stringToBeReturned = "";

		try {

			getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from "+getDbNAme()+"."+table+" where "+columnName+" = '"
					+ killRow + "';");
			while (rs.next()) {

				stringResultChecker = rs.getString(columnName);
				stringIsAllreadyOff = rs.getString("active");
				
			}

			if (stringResultChecker.equals("")) {

				stringToBeReturned = "The "+columnName+" doesn't exist";
				
			} else if (stringIsAllreadyOff.equals("2")) {
				
				stringToBeReturned = "The "+columnName+" is already inactive";
			}

			else {

				doUpdate("update "+getDbNAme()+"."+table+" set active='2' where "+columnName+"='"
						+ killRow + "';");
				stringToBeReturned = "The "+killRow+" is now inactive";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stringToBeReturned;
	}
	
//	Activate user
	// Activates a user by input from admin.
	// Checks if input eqals anything and if user is already activated.
	// Returns error or information message
	public String activateUse(String reActivate, String table, String column){

	
		String stringResultChecker = "";
		String stringIsAllreadyOn = "";
		String stringToBeReturned = "";

		try {

			getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from "+getDbNAme()+"."+table+" where "+column+" = '"
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
			
			doUpdate("update "+getDbNAme()+"."+table+" set active='1' where "+column+"='"+reActivate+"';");
			JOptionPane.showMessageDialog (null, "The "+column+" is now active", "Error", JOptionPane.INFORMATION_MESSAGE);
			
	}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
return stringToBeReturned;
		
	}
	
//	Activate event
	// Activates an event by input.
	// Checks if input eqals anything and if event is already activated.
	// Returns error or information message	
	public String activatesEvent(String reActivate){
		String stringResultChecker = "";
		String stringIsAllreadyOn = "";
		String stringToBeReturned = "";

		try {

			getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from "+getDbNAme()+".events where EventID = '"
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
			
			doUpdate("update "+getDbNAme()+".event set active='1' where eventID='"+reActivate+"';");
			JOptionPane.showMessageDialog (null, "The Event is now active", "Error", JOptionPane.INFORMATION_MESSAGE);
			
	}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return stringToBeReturned;
		
	}

//	Cheks if a calendar name is already used.
//	If input equals zero nothing is done 
	public boolean checkCalendarName(String eventName) {
		boolean booleanToBeReturned = false;
		String resultSetHolder ="";
		try{
			getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from "+getDbNAme()+".calendar where Name = '"
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

//		Creates new calendar 
//		# gutter hardcoder vi ikke hvem kalendereen er created af?
//		Takes userinput and inputs new calendar variables into the database
	public boolean createNewCalender(String eventName, int publicPrivate,
			int active) {
		boolean booleanToBeReturned = false;
		try {
			doUpdate("insert into "+getDbNAme()+".calendar (Name, Active, CreatedBy, PrivatePublic) Values ('"+eventName+"', '"+active+"', '1', '"+publicPrivate+"');");
			booleanToBeReturned = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return booleanToBeReturned;
	}
	
//	Gets all information in the database regarding weather and returns it.
	public ArrayList<String> weatherArray(){
		ArrayList<String> arrayToBeReturned = new ArrayList<String>();
		String resultSetHolder = "";
		try{
		getConnection();
		stmt = conn.createStatement();
		rs = stmt.executeQuery("select * from "+getDbNAme()+".weathertable;");
			while (rs.next()) {
				resultSetHolder = "WeatherID: "+rs.getString("weatherid") +" WeatherDate: "+rs.getString("weatherdate")+" Weatherdesc: "+rs.getString("weatherdesc")+" WeatherDegrees: "+rs.getString("weatherDegrees")+"\n";
				arrayToBeReturned.add(resultSetHolder);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return arrayToBeReturned;
	}
	
//	Gets the weather from logic class, which in turn comes from the online service, and writes it to the database 
	public void weatherToDB(String celsius, String date, String desc) {
		try {
			doUpdate("insert into "+getDbNAme()+".weathertable (weatherdate, weatherdegrees, weatherdesc) values ('"+date+"', '"+celsius+"','"+desc+"' );");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
//	Gets the time from built in java function
	private String getTime() {
		String timeStamp = "";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		timeStamp = dateFormat.format(date);
		return timeStamp;
	}
	
//	delete note 
//	Takes userinput and deletes relevant event if it existis
//	Returns appropriate string depending on result
	public String deleteNote(String eventID, String allKnowingName) {
		String stringToBeReturned = "";
		String[] deleteValues = {allKnowingName, "There is no note added to this event", getTime()};
		String[] deleteFields = {"createdBy", "text", "dateTime"};
		try {
			QB.update("notes", deleteFields, deleteValues).where("eventid", "=", eventID).Execute();
			stringToBeReturned = "Note succesfully deleted";
		} catch (SQLException e) {
			stringToBeReturned = "There is no event with this ID";
			e.printStackTrace();
		}
		return stringToBeReturned;
	}
	public String addNewNote(String eventID, String newNote,
		String allKnowingName) {
		String stringToBeReturned = "";
		String eventIDFromDB = "";
		String[] newNoteFields = {"createdBy", "text", "dateTime", "eventid"};
		String[] newNoteValues = {allKnowingName, newNote, getTime(), eventID};
		String[] checkIfNoteExists = {"eventid"};
		try {
			resultSet = QB.selectFrom(checkIfNoteExists, "notes").where("eventid", "=", eventID).ExecuteQuery();
			while(resultSet.next())
			{
				eventIDFromDB = resultSet.getString("eventID");
			}
			if(eventIDFromDB.equals(""))
			{
				QB.insertInto("notes", newNoteFields).values(newNoteValues).Execute();
				stringToBeReturned = "NotSe has been updated";
			}
			else
			{
				stringToBeReturned = "Note does already exists. please use edit note insted.";
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			stringToBeReturned = "Note has not been created, because something went wrong. Please try again";
		}
		return stringToBeReturned;
	}
	public String getExistingNote(String eventID) {
		String stringToBeReturned = "";
		String existingNote = "";
		String[] noteValues = {"text"};
		try {
			resultSet = QB.selectFrom(noteValues, "notes").where("eventid", "=", eventID).ExecuteQuery();
		
		while(resultSet.next())
		{
			existingNote = resultSet.getString("text");
		}
		if(existingNote.equals(""))
		{
			stringToBeReturned = "There is no note added to this eventid";
		}
		else
		{
			stringToBeReturned = "Existing note of "+eventID+" is:\n"+existingNote;
		}
		
		} catch (SQLException e) {
			e.printStackTrace();
			stringToBeReturned = "There is no note added to this eventid";
		}
		return stringToBeReturned;
	}
	public String editNote(String eventID, String newNote, String allKnowingName) {
		String stringToBeReturned = "";
		String[] updateValues = {allKnowingName, newNote, getTime()};
		String[] updateFields = {"createdBy", "text", "dateTime"};
		try {
			QB.update("notes", updateFields, updateValues).where("eventid", "=", eventID).Execute();
			stringToBeReturned = "Note has succesfully been updated";
		} catch (SQLException e) {
			e.printStackTrace();
			stringToBeReturned = "There is no note with this eventid";
		}
		return stringToBeReturned;
	}
	public void clearWeatherQuote() {
		try {
			doUpdate("truncate "+getDbNAme()+".qotd;");
			doUpdate("truncate "+getDbNAme()+".weathertable;");
		} catch (SQLException e) {
			System.out.println("Weather could not be updated due to unstable weather API");
			e.printStackTrace();
		}
		
	}
	public String getDbNAme() {
		return dbNAme;
	}
	public String getCalendarID(String calendar) {
		String stringToBeReturned = "";
		String[] calendarIDValue = {"CalendarID"};
		try {
			resultSet = QB.selectFrom(calendarIDValue, "calendar").where("Name", "=", calendar).ExecuteQuery();
			while(resultSet.next())
			{
				stringToBeReturned = resultSet.getString("CalendarID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stringToBeReturned;
	}
}
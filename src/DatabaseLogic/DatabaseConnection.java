package DatabaseLogic;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JOptionPane;

import keyKeeper.*;

public class DatabaseConnection {
	
	keyKeeper.KeyGetter GK = new keyKeeper.KeyGetter();
	KeyChest KC = new KeyChest();

	//Creates the needed information to connect to the database
	
	//Creates the needed information to connect to the database
//	Brug til manuel indtastning af connect info.
//	private String sqlUrl = "jdbc:mysql://localhost:3306/";
//	private String sqlUser = "Asger";
//	private String sqlPasswd = "1darkeldar";
	private String sqlUrl = "";
	private String sqlUser = "";
	private String sqlPasswd = "";

	//Creates a statement, resultest and connection
	private java.sql.Statement stmt;
	private ResultSet rs;
	private Connection conn = null;
	
//	Imports login info keys
	public void keyImporter()
	{
		KC.keyImporter();

		setSqlUrl(KC.getSqlUrl());
		setSqlUser(KC.getSqlUser());
		setSqlPasswd(KC.getSqlPasswd());
	}

	public void addingCBSCalendarToDB(int type, String location, String start, String end, String name, String text, int customevent, String description)
	{
		//Her skal være 2 switches til at bestemme Hvilken calendar event tilhører, og hvilken lokation.
		int locationID = determineLocation(location);
		int calendarID = determineCalendarID(description);
		try
		{
			getConnection();
			doUpdate("insert into events (type, location, createdBy, start, end, name, text, customevent, CalendarID) values ('"+type+"', '"+locationID+"', 'admin@admin.dk','"+start+"', '"+end+"', '"+name+"', '"+text+"', '"+customevent+"', '"+calendarID+"'");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	private int determineCalendarID(String course) {
		int intToBeReturned = 0;
		switch (course)
		{
			case "Distribuerede systemer (LA)": intToBeReturned = 11;
			break;
			case "Ledelse af IS - forandring, innovation og viden (XB)": intToBeReturned = 12;
			break;
			case "Ledelse af IS - forandring, innovation og viden (LA)": intToBeReturned = 14;
			break;
			case "Virksomhedens økonomiske styring (3)": intToBeReturned = 15;
			break;
			case "Makroøkonomi (XB)": intToBeReturned = 9;
			break;
			case "Makroøkonomi (XA)": intToBeReturned = 8;
			break;
			case "Ledelse af IS - forandring, innovation og viden (XA)": intToBeReturned = 13;
			break;
			case "Makroøkonomi (LA)": intToBeReturned = 10;
			break;
			default: intToBeReturned = 1;
		}
		return intToBeReturned;
	}

	private int determineLocation(String location) {
		int intToBeReturned = 0;
		char position1 = location.charAt(0);
		char position2 = location.charAt(1);
		String locationID = position1 +""+ position2;
		switch (locationID)
		{
			case "KS": intToBeReturned = 7;
			break;
			case "FH": intToBeReturned = 3;
			break;
			case "SP": intToBeReturned = 4;
			break;
			case "HV": intToBeReturned = 5;
			break;
			case "PH": intToBeReturned = 6;
			break;
			case "DH": intToBeReturned = 8;
			break;
			default: intToBeReturned = 1;
		}
		return intToBeReturned;
	}

	//Method to test connection which returns false
	public boolean TestConnection() {
		try {
			//Get Connection
			getConnection();

			//If connection established before 5000 miliseconds - Succes!
			if (conn.isValid(5000)) {
				JOptionPane.showMessageDialog(null,
						"You have succesfully connected to database!");
			}
			//If error, print exception in dialogbox
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Could not connect to database due to: "+e.getMessage());
		}
		return false;
	}
	
	public boolean userAuthenticating(String userName, String password, String isActive)
	{
		boolean booleanToBeReturned = false;
		try
		{
			System.out.println("Intet virker, og derudaf!");
			getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select password, active from cbscalendar.users where email = '"+userName+"';");
			while(rs.next())
			{
				booleanToBeReturned = true;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return booleanToBeReturned;
	}
	
	public boolean userIsActive(String userName, String isActive) {
		boolean activeChecker = false;
		try {
			getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select active from cbscalendar.users where email = '"+userName+"';");
			while(rs.next())
			{
				String isAccountActive = rs.getString("Active");
				if(isAccountActive.equals("1"))
				{
					activeChecker = true;
				}
				else
				{
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
			rs = stmt.executeQuery("select password from cbscalendar.users where email = '"+userName+"';");
			while(rs.next())
			{
				String accountPassword = rs.getString("password");
				if(accountPassword.equals(password))
				{
					passwordChecker = true;
				}
				else
				{
					System.out.println("Password'et var forkert tard!");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return passwordChecker;
	}
	//Creates a public String, which recieves 1 String input
	public String getAdminID(String username) {
		//Create a String an sets to nothing
		String AdminID = "";
		try {
			//Get connection to database
			getConnection();
			//Creates a method, which allow us to use resultset, and recieve data from the database
			stmt = conn.createStatement();
			//Executes a query to recieve data
			rs = stmt.executeQuery("select AdminID from BtcDatabase.Login_Information where CBSMail='"
					+ username + "';");
			//Sets the string equals the resultset
			while (rs.next()) {
				AdminID = rs.getString("AdminID");
			}
			//Close the connection to the database
			closeConnection();
			//If error, print exception in a dialogbox
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					e.getMessage());
		}
		//returns the string AdminID to be used later
		return AdminID;
	}

	
	
	//Creates a public method, which revieves 2 inputs, and returns a string
	public String authenticate(String userName, String password) {
		//Creates an empty String
		String ID = "";
		try {
			//Connects to database
			getConnection();
			//Execute query
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from BtcDatabase.Login_Information where CBSMail='"
					+ userName + "' and Password='" + password + "';");
			//Sets empty string equals resultset
			while (rs.next()) {
				ID = rs.getString("AdminID");
			}
			//Close connection to database
			closeConnection();
			//Prints Exception in a dialogbox
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					e.getMessage());
		}
		//returns the empty string which now contains the resultset given, by the query
		return ID;
		
	}
	//Creates a public method which returns a boolean, and recieves 3 input
	public boolean creatingNewUserLogin(String userName, String newPassword,
			String repeatPassword) {
		//Sets boolean which will be returned to false
		boolean userChecker = false;
		try {
			//Connects to database
			getConnection();
			//Creates an empty string
			String testBruger = "";
			//Executes a query which gives us all usernames in a string seperated by space
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from BtcDatabase.Login_Information");
			while (rs.next()) {
				testBruger += rs.getString("CBSMail");
				testBruger += " ";
			}
			//If the entered passwords are not the same, print it, and return false 
			if (!newPassword.equals(repeatPassword)) {
				JOptionPane.showMessageDialog(null,
						"The passwords are not the same", "alert",
						JOptionPane.ERROR_MESSAGE);
				return false;
				//If the user which is trying to be created allready exists, return false
			} else if (testBruger.contains(userName + " ")) {
				JOptionPane.showMessageDialog(null,
						"The Username does already exists", "alert",
						JOptionPane.ERROR_MESSAGE);
				return false;
				//Else create user and return true
			} else {
				doUpdate("Insert Into BtcDatabase.Login_Information (CBSMail, Password, Balance, AdminID) VALUES ('"
						+ userName
						+ "', '"
						+ newPassword
						+ "', '"
						+ 00.00
						+ "', '" + 2 + "');");
				JOptionPane.showMessageDialog(null, "The user " + userName
						+ " has been created!", "Succes!",
						JOptionPane.INFORMATION_MESSAGE);
				userChecker = true;
			}
			//Close connection to database
			closeConnection();
			//Prints exception in dialogbox
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					e.getMessage());
		}
		return userChecker;
	}

	//Creates method which returns a boolean, and recieves one input
	public void deleteUser(String username) {
		//boolean userChecker = false;
		int userChecker = 0;
		try {
			//Establish connection, and executes query
			getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from BtcDatabase.Login_Information where CBSMail = '"
					+ username + "';");
			//If query returns anything set boolean equals true
			while(rs.next())
			{
				userChecker++;
				//userChecker = true;
			}
			//If boolean equals true delete user from database
			if(userChecker > 0)
			{
			doUpdate("DELETE FROM BtcDatabase.Login_Information where CBSMail='"
					+ username + "';");
			JOptionPane.showMessageDialog(null, "The user '" + username
					+ "'has been deleted!", "alert", JOptionPane.INFORMATION_MESSAGE);
			}
			//Else user cant be created, since it does not exists
			else
			{
				JOptionPane.showMessageDialog(null, "The user '" + username
						+ "' does not exist!", "alert", JOptionPane.ERROR_MESSAGE);
			}
			//Prints exception in dialogbox
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					e.getMessage());
		}
	}
	

	//Creates a method which returns a boolean, and recieves 3 inputs
	public boolean authenticatePassword (String username, String newPassword, String oldPassword)
	{
		//Creates a boolean and sets to false
		boolean authenticatePassword = false;
		//Creates an empty string
		String passwordCheck = "";
		try
		{
		//Establish connection and executes query
		getConnection();
		stmt = conn.createStatement();
		rs = stmt.executeQuery("select * from btcDatabase.Login_Information where CBSMail = '"
				+username+ "' and password = '"+oldPassword+"'");
		//Sets the empty string to the resultset, and set boolean equals true, if the resultset returns anything
		while(rs.next())
		{
			passwordCheck = rs.getString("balance");
			authenticatePassword = true;
		}
		//If boolean equals true, do the database update
		if(authenticatePassword = true)
		{
			doUpdate("UPDATE btcDatabase.Login_Information SET password = '"
						+newPassword+"' WHERE CBSMail = '"
						+username+"'");
		}
		//Else print the original password is incorrect
		else
		{
			JOptionPane.showMessageDialog(null, "The original password is incorrect",
					"alert", JOptionPane.ERROR_MESSAGE);
		}
		
		}
		//Prints exception in a dialogbox
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null,
					e.getMessage());
		}
		//return the boolean
		return authenticatePassword;
	}
	/*************************
	 * FOR LOOP FOR JTABLE *
	 ************************/
	
	
	/*************************
	 * Arraylist to bugtable *
	 ************************/
	//Creates a method returning a array list, and receives nothing
	public ArrayList<String> arrayUserEmail()
	{
		//Creates an empty arraylist
		ArrayList<String> userEmail = new ArrayList<String>();
		try {
			//Establish connection to database
			getConnection();
			//Executes query
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select email from cbscalendar.users");
			//Adds every object from the resultset to the empty arraylist
			while (rs.next()) {
				userEmail.add(rs.getString("email"));
				
			}
			//Close connection
			closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//Returns the arraylist
		return userEmail;
	}
	
	public ArrayList<String> arrayBugText()
	{
		ArrayList<String> bugText = new ArrayList<String>();
		try {
			getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select bugText from btcdatabase.bugTable");
			while (rs.next()) {
				bugText.add(rs.getString("bugText"));
				
			}
			closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bugText;
	}
	
	public String[][] arrayID() {
		String[] headerNames = {"userid", "email", "active", "created", "password", "Admin"};  
		int rowCounter = 0;
		try{
			getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT userid FROM cbscalendar.users;");
			while(rs.next())
			{
				rowCounter++;
			}
		}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		System.out.println(rowCounter);
		String[ ][ ] doubleArray = new String[6][rowCounter];
		System.out.println("Lige efter String array er blevet oprettet");
		for(int headerCounter = 0 ; headerCounter < 6 ; headerCounter++)
		{
			System.out.println("inde I starten af for loopet "+headerCounter+". gang");
			ArrayList<Object> resultArray = new ArrayList<Object>();
		try {
			int otherCounter = 0;
			getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select "+headerNames[headerCounter]+" from cbscalendar.users");
			while (rs.next()) {
				doubleArray[headerCounter][otherCounter]=rs.getString(headerNames[headerCounter]);
				otherCounter++;
			}
			closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("inde I slutningen af for loopet "+headerCounter+". gang");
		}
		return doubleArray;
	}

	public ArrayList<String> AdminID() {
		
		ArrayList<String> AdminID = new ArrayList<String>();
		try {
			getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select AdminID from btcdatabase.login_information");
			while (rs.next()) {
				AdminID.add(rs.getString("adminID"));
			}
			closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return AdminID;
	}

	public ArrayList<String> password() {
		ArrayList<String> password = new ArrayList<String>();
		try {
			getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select password from btcdatabase.login_information");
			while (rs.next()) {
				password.add(rs.getString("password"));
			}          
			closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return password;
	}
	
	/****************************************************
	 * Methods which return all info about all transfers*
	 ***************************************************/
	
	public ArrayList<String> TransferID() {
		ArrayList<String> TransferID = new ArrayList<String>();
		try {
			getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select transferID from btcdatabase.Transfer_information");
			while (rs.next()) {
				TransferID.add(rs.getString("transferID"));
			}
			closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TransferID;
	}
	
	public ArrayList<String> Date() {
		ArrayList<String> Date = new ArrayList<String>();
		try {
			getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select date from btcdatabase.Transfer_information");
			while (rs.next()) {
				Date.add(rs.getString("date"));
			}
			closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Date;
	}
	
	public ArrayList<String> outgoingID() {
		ArrayList<String> outgoingID = new ArrayList<String>();
		try {
			getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select outgoing from btcdatabase.Transfer_information");
			while (rs.next()) {
				outgoingID.add(rs.getString("outgoing"));
			}
			closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outgoingID;
	}
	
	public ArrayList<String> ingoingID() {
		ArrayList<String> ingoing = new ArrayList<String>();
		try {
			getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select ingoing from btcdatabase.Transfer_information");
			while (rs.next()) {
				ingoing.add(rs.getString("ingoing"));
			}
			closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ingoing;
	}
	
	public ArrayList<String> amount() {
		ArrayList<String> amount = new ArrayList<String>();
		try {
			getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select amount from btcdatabase.Transfer_information");
			while (rs.next()) {
				amount.add(rs.getString("amount"));
			}
			closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return amount;
	}
	
	public ArrayList<String> tUserID() {
		ArrayList<String> tUserID = new ArrayList<String>();
		try {
			getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select UserID from btcdatabase.Transfer_information");
			while (rs.next()) {
				tUserID.add(rs.getString("UserID"));
			}
			closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tUserID;
	}
	
	/****************************************************
	 * Methods which return all info about user transfers*
	 ***************************************************/
	
	public ArrayList<String> uTransferID(String username) {
		ArrayList<String> uTransferID = new ArrayList<String>();
		try {
			getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select transferID from btcdatabase.Transfer_information where ingoing = '"+username+"' or outgoing = '"+username+"'");
			while (rs.next()) {
				uTransferID.add(rs.getString("transferID"));
			}
			closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uTransferID;
	}
	
	public ArrayList<String> uDate(String username) {
		ArrayList<String> uDate = new ArrayList<String>();
		try {
			getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select Date from btcdatabase.Transfer_information where ingoing = '"+username+"' or outgoing = '"+username+"'");
			while (rs.next()) {
				uDate.add(rs.getString("Date"));
			}
			closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uDate;
	}
	
	public ArrayList<String> uOutgoing(String username) {
		ArrayList<String> uOutgoing = new ArrayList<String>();
		try {
			getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select outgoing from btcdatabase.Transfer_information where ingoing = '"+username+"' or outgoing = '"+username+"'");
			while (rs.next()) {
				uOutgoing.add(rs.getString("outgoing"));
			}
			closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uOutgoing;
	}
	
	public ArrayList<String> uIngoing(String username) {
		ArrayList<String> uIngoing = new ArrayList<String>();
		try {
			getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select Ingoing from btcdatabase.Transfer_information where ingoing = '"+username+"' or outgoing = '"+username+"'");
			while (rs.next()) {
				uIngoing.add(rs.getString("Ingoing"));
			}
			closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uIngoing;
	}
	
	public ArrayList<String> uAmount(String username) {
		ArrayList<String> uAmount = new ArrayList<String>();
		try {
			getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select Amount from btcdatabase.Transfer_information where ingoing = '"+username+"' or outgoing = '"+username+"'");
			while (rs.next()) {
				uAmount.add(rs.getString("Amount"));
			}
			closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uAmount;
	}
	
	public ArrayList<String> uID(String username) {
		ArrayList<String> uID = new ArrayList<String>();
		try {
			getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select UserID from btcdatabase.Transfer_information where ingoing = '"+username+"' or outgoing = '"+username+"'");
			while (rs.next()) {
				uID.add(rs.getString("UserID"));
			}
			closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return uID;
	}
	//Creates a method which returns a int, and recieves one input
	public int doUpdate(String Update) throws SQLException {
		//Establish connection to database
		getConnection();
		//Creates an int, and sets to 0
		int temp = 0;

		try {
			//Creates a method for executing updating statements, which recieves the input
			stmt = conn.createStatement();
			temp = stmt.executeUpdate(Update);
		} catch (SQLException ex) {
			ex.printStackTrace();
			//If errorcode equals 1062, it is duplicate entry
			if (ex.getErrorCode() == 1062) {
				throw new SQLException("Duplicate entry");
			}
			//Else throw normal exception
			else {
				throw ex;
			}
		}
		//If stamement is not equals null, close stament.
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
		//Returns int
		return temp;
	}

	//Establish connection to database
	private void getConnection() throws SQLException {
			conn = DriverManager.getConnection(sqlUrl, sqlUser, sqlPasswd);
}
	//Method to close connection, resultset and statement
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
			rs = stmt.executeQuery("select password from cbscalendar.users where email = '"+emailInput+"';");
			while(rs.next())
			{
				String accountPassword = rs.getString("password");
				if(accountPassword.equals(passwordInput))
				{
					passwordChecker = true;
				}
				else
				{
					System.out.println("Password'et var forkert tard!");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return passwordChecker;
	}

	public boolean checkIfAdmin(String emailInput) {
		boolean isAdmin = false;
		String adminID = "";
		try
		{
			getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select Admin from cbscalendar.users where email = '"+emailInput+"';");
			while(rs.next())
			{
				adminID = rs.getString("Admin");
				System.out.println(adminID);
			}
			if(adminID.equals("1"))
			{
				isAdmin = true;
			}
			else
			{
				isAdmin = false;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return isAdmin;
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

	

	
	

}

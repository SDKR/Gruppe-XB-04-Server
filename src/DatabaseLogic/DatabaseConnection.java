package DatabaseLogic;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JOptionPane;

import model.QueryBuild.QueryBuilder;
import keyKeeper.*;

public class DatabaseConnection {
	
	keyKeeper.KeyGetter GK = new keyKeeper.KeyGetter();
	KeyChest KC = new KeyChest();

	QueryBuilder QB = new QueryBuilder();

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
	public void clearOldCBSData()
	{
		try {
			doUpdate("Delete from cbscalendar.events where customevent = 1");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void addingCBSCalendarToDB(String type, String location, String start, String end, String name, String text) throws SQLException
	{
		//Her skal være 2 switches til at bestemme Hvilken calendar event tilhører, og hvilken lokation.
		int locationID = determineLocationID(location);
		int calendarID = determineCalendarID(type);
		int typeID = determineTypeID(type);
		if(!start.contains("9-31") && !end.contains("9-31") )
		{
		try
		{
			
			getConnection();
			doUpdate("insert into cbscalendar.events (type, location, createdBy, start, end, name, text, customevent, CalenderID) values ('"+typeID+"', '"+locationID+"', '1','"+start+"', '"+end+"', '"+name+"', '"+text+"', '1', '"+calendarID+"');");
			conn.close();
			stmt.close();
		}
		
		catch(SQLException e)
		{
			e.printStackTrace();
			if(e.getErrorCode()== 1292)
			{
				throw new SQLException("Date does not exists");
			}
			//Else throw normal exception
			else {
				throw e;
			}
		}}
		else
		{
			System.out.println("This date does not exists");
		}
	}
	private int determineTypeID(String type) {
		int intToBeReturned = 0;
		switch (type)
		{
		case "Lecture": intToBeReturned = 1;
		break;
		case "Exercise": intToBeReturned = 2;
		break;
		default: intToBeReturned = 3;
		break;
		}
		return intToBeReturned;
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

	private int determineLocationID(String location) {
		int intToBeReturned = 0;
		char position1 = location.charAt(0);
		char position2 = location.charAt(1);
		String locationID = position1+""+position2;
		if(locationID.equals("Ks"))
		{
			intToBeReturned = 7;
		}
		else if(locationID.equals("FH"))
		{
			intToBeReturned = 3;
		}
		else if(locationID.equals("SP"))
		{
			intToBeReturned = 4;
		}
		else if(locationID.equals("HO"))
		{
			intToBeReturned = 5;
		}
		else if(locationID.equals("PH"))
		{
			intToBeReturned = 6;
		}
		else if(locationID.startsWith("D"))
		{
			intToBeReturned = 8;
		}
		else if(locationID.startsWith("Fa"))
		{
			intToBeReturned = 9;
		}
		else
		{
			intToBeReturned = 10;
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
	
	/*************************
	 * Arraylist to bugtable *
	 ************************/
	//Creates a method returning a array list, and receives nothing
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
	
	public String[][] eventID() {
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

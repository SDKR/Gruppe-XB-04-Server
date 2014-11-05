package DatabaseLogic;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class DatabaseConnection {

	//Creates the needed information to connect to the database
	private static String sqlUrl = "jdbc:mysql://localhost:3306/";
	private static String sqlUser = "Asger";
	private static String sqlPasswd = "1darkeldar";

	//Creates a statement, resultest and connection
	private java.sql.Statement stmt;
	private ResultSet rs;
	private Connection conn = null;

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

	//Creates public method which returns a string, and recieves a string
	public String getBGColor(String username)
	{
		//Creates empty string
		String bgColor = "";
		try
		{
			//Establish connection and executes query
			getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select backgroundColor from BtcDatabase.Login_Information where CBSMail='"
					+ username + "';");
			//Sets the resultset equals empty string
			while(rs.next())
			{
				bgColor = rs.getString("backgroundColor");
			}
		}
		//Prints exception in dialogbox
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,
					e.getMessage());
		}
		//returns string which contains the resultset
		return bgColor;
	}
	
	//Creates a method which returns nothing, and recieves 2 strings
	public void addBug(String userID, String bugText)
	{
		//do an update in database, with the data recieved
		try
		{
			doUpdate("Insert Into BtcDatabase.bugTable (bugText, USERID) VALUES ('"
					+ bugText
					+ "', '"
					+ userID
					+ "');");
		}
		//prints exception in dialogbox
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,
					e.getMessage());
		}
	}
	
	//Creates a method which returns nothing, but recieves 2 strings
	public void updateBGColor(String newColor, String username)
	{
		try
		{
		//Do update in database with the 2 inputs
		doUpdate("UPDATE BtcDatabase.login_information SET backgroundColor = '"
					+newColor+ "' WHERE CBSMail='"+username+"'");
		}
		//Prints exception in dialogbox
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null,
					e.getMessage());
		}
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
	//Creates a method which returns a string, which is given one input
	public String viewBalance(String userName) {
		//Sets a string to empty
		String tempUserBalance = "";
		try {
			//Establish connection
			getConnection();
			//Executes query
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select Balance from BtcDatabase.Login_Information where CBSMail = '"
					+ userName + "';");
			//Sets empty string equals the resultset
			while (rs.next()) {
				tempUserBalance += rs.getString("Balance");
			}
			//Close connection to database
			closeConnection();
			//Prints an error has occured and exception in a dialog box
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "An error has occured",
					"Error", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,
					e.getMessage());}
			//returns the resultset in a string
		return tempUserBalance;
	}

	//Creates a method which returns a string, but recieve no input
	public String viewEXR() {
		//Creates an empty string
		String tempEXRString = "";
		try {
			//Connects to database, and execute query
			getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select exchangeRateDecimal from BtcDatabase.exchangeRate where exchangeRateText = 'EXR'");
			//Sets the empty string equals the resultset
			while (rs.next()) {
				tempEXRString = rs.getString("exchangeRateDecimal");
			}
			//Close connection
			closeConnection();
			//Prints the exception in a dialogbox
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					e.getMessage());
		}
		//Returns the string containing the resultset
		return tempEXRString;
	}
	
	//Creates a method which returns nothing, but recieves one input
	public void setEXR(String newEXR) {
		try {
			//Do an update in the database, which changes the exchangerate
			doUpdate("UPDATE BtcDatabase.exchangeRate SET exchangeRateDecimal = '"
					+ newEXR + "' WHERE exchangeRateText='EXR'");
		}//If exception occurs, print out there is an error, and the exception
		catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"You have to enter a number", "alert",
					JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,
					e.getMessage());
			e.printStackTrace();
		}
	}
	//Creates a method which returns a string, and recieves one input
	public String postAmountTB(String username) {
		//Creates an empty string
		String tempAmount = "";
		try {
			//Connects to database, and executes query
			getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select Balance from btcDatabase.Login_Information where CBSMail = '"
					+ username + "'");
			//While the resultset returns anything, set the empty string equals the resultset
			while (rs.next()) {
				tempAmount = rs.getString("Balance");
			}
			//Prints exception in a dialogbox
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					e.getMessage());
		}
		//Returns the string which contains the resultset
		return tempAmount;
	}

	//Creates a method which returns nothing, but recieves 2 string inputs and 1 double
	public void transferTo(String fromUsername, String toUsername,
			double transferAmount, String date) {
		//Creates 3 empty strings
		String toTempTransferAmountString = "";
		String fromTempTransferAmountString = "";
		String fromUsernameID = "";
		try {
			//Connects to database and exequtes the first query
			getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select balance from btcDatabase.Login_Information where CBSMail = '"
					+ fromUsername + "'");
			//Sets one of the empty strings equals the first resultset
			while (rs.next()) {
				
				fromTempTransferAmountString = rs.getString("Balance");
			}
			
			//Parse the above string to a double, so we can use it to calculate
			double fromTempTransferAmount = Double
					.parseDouble(fromTempTransferAmountString);
			//calculate the user who is going to transfer new balance
			fromTempTransferAmount = fromTempTransferAmount - transferAmount;

			//Execute the second query
			rs = stmt.executeQuery("select balance from btcDatabase.Login_Information where CBSMail = '"
					+ toUsername + "'");
			//Sets the second empty string equals the resultset, which is the balance of the user who is getting a new amount
			while (rs.next()) {
				toTempTransferAmountString = rs.getString("Balance");
			}
			//Parse it to a double, and calculate the new balance
			double toTempTransferAmount = Double
					.parseDouble(toTempTransferAmountString);
			toTempTransferAmount = toTempTransferAmount + transferAmount;

			//If the user who is trying to transfer money is able to do it, do it, and update the database.
			if (fromTempTransferAmount > 0 || fromTempTransferAmount == 0) {
				doUpdate("UPDATE btcDatabase.Login_Information SET balance = '"
						+ fromTempTransferAmount + "' WHERE CBSMail = '"
						+ fromUsername + "'");
				doUpdate("UPDATE btcDatabase.Login_Information SET balance = '"
						+ toTempTransferAmount + "' WHERE CBSMail = '"
						+ toUsername + "'");
				//Execute the third query, and sets the last empty string equals the resultset
				stmt = conn.createStatement();
				rs = stmt.executeQuery("Select USERID from btcdatabase.login_information where CBSMail = '"
						+ fromUsername + "'");
				while (rs.next()) {
					fromUsernameID = rs.getString("USERID");
				}
				//Update the database, with a row containing information regarding the transaktion
				doUpdate("INSERT INTO btcdatabase.Transfer_Information (Date, Outgoing, ingoing, amount, USERID) VALUES ('"+date+"', '"
						+ fromUsername
						+ "', '"
						+ toUsername
						+ "', '"
						+ transferAmount + "', '" + fromUsernameID + "')");
				//Print a dialogbox
				JOptionPane.showMessageDialog(null, "Transfer completed!",
						"Succes!", JOptionPane.INFORMATION_MESSAGE);
			}
			//If the user is unable to transfer, due to lack of balance, print dialogbox
			else if(toTempTransferAmount < 0)
			{
				JOptionPane.showMessageDialog(null,
						"Not enough Bitcoins",
						"alert", JOptionPane.ERROR_MESSAGE);
			}
			//Else print a message saying you have to enter a digit and valid username
			else {
				JOptionPane.showMessageDialog(null,
						"You have to enter a digit, and valid username",
						"alert", JOptionPane.ERROR_MESSAGE);
			}
			//If an exception is thrown, print a dialogbox containing the exception
			} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "User does not exist, or it is not a transferable amount",
					"alert", JOptionPane.ERROR_MESSAGE);
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
	
	//Creates a method which returns nothing, and recieves two input
	public void doDeposit(String username, double amount){
		try {
			//Connects to database
			getConnection();
			//Creates an empty String
			String tempAmountString = "";
			//Executes a query
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select balance from btcdatabase.login_information where CBSMail = '"
					+ username + "'");
			//Sets the empty string equals the resultset
			while (rs.next()) {
				tempAmountString = rs.getString("Balance");
			}
			//Parse the string to a double and calculate
			double tempAmountDouble = Double.parseDouble(tempAmountString);
			tempAmountDouble = tempAmountDouble + amount;
			//do the update and print succes
			doUpdate("UPDATE btcDatabase.Login_Information SET balance = '"
					+ tempAmountDouble + "' WHERE CBSMail = '" + username + "'");
			JOptionPane.showMessageDialog(null, "Deposit completed!",
					"Succes!", JOptionPane.INFORMATION_MESSAGE);
			//Close connection
		closeConnection();
		//Prints the exception in a dialogbox
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					e.getMessage());
		}
	}

	//Creates a method returning a string, and recieve one input
	public String vbCurrentBalance(String username) {
		//Creates an empty string
		String tempCurrentBalance = "";
		try {
			//Establish connection
			getConnection();
			//Execute query
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select balance from BtcDatabase.login_information where CBSMail = '"
					+ username + "'");
			//Sets empty string equals resultset
			while (rs.next()) {
				tempCurrentBalance = rs.getString("Balance");
			}
			//Close connection
			closeConnection();
			//print exception
		} catch (Exception e) {
			e.printStackTrace();
		}
		//returns string containing resultset
		return tempCurrentBalance;
	}
	
	/**
	 * The next 15 methods are mostly using the same syntax. The only exception is which information the resultset gets
	 * Therefor will only the first one be commented**/
	
	/*************************
	 * Arraylist to bugtable *
	 ************************/
	//Creates a method returning a array list, and receives nothing
	public ArrayList<String> arrayBugID()
	{
		//Creates an empty arraylist
		ArrayList<String> userID = new ArrayList<String>();
		try {
			//Establish connection to database
			getConnection();
			//Executes query
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select bugID from btcdatabase.bugTable");
			//Adds every object from the resultset to the empty arraylist
			while (rs.next()) {
				userID.add(rs.getString("bugID"));
				
			}
			//Close connection
			closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//Returns the arraylist
		return userID;
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
	
	/********************************************
	 * Methods which return all info about users*
	 *******************************************/
	public ArrayList<String> arrayUsername() {
		ArrayList<String> userID = new ArrayList<String>();
		try {
			getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select CBSMail from btcdatabase.login_information");
			while (rs.next()) {
				userID.add(rs.getString("CBSMail"));
				
			}
			closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userID;
	}

	public ArrayList<String> viewBalance() {
		ArrayList<String> viewBalance = new ArrayList<String>();
		try {
			getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select Balance from btcdatabase.login_information");
			while (rs.next()) {
				viewBalance.add(rs.getString("Balance"));
				}
			closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return viewBalance;
	}

	public ArrayList<String> arrayID() {
		ArrayList<String> userID = new ArrayList<String>();
		try {
			getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select USERID from btcdatabase.login_information");
			while (rs.next()) {
				userID.add(rs.getString("USERID"));
			}
			closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userID;
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

	

	
	

}

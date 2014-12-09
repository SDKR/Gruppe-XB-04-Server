package SwitchLogic.Methods;

import DatabaseLogic.DatabaseConnection;

public class UserLogin {
	
	DatabaseConnection DBC = new DatabaseConnection();
	public String authenticateUser(String userName, String password, String isActive) throws Exception {
		String stringToBeReturned = "";
		if(DBC.userAuthenticating(userName, password, isActive))
		{
			stringToBeReturned = "Brugeren eksisterer";
			if(DBC.userIsActive(userName, isActive))
			{
				if(DBC.userPasswordCheck(userName, password))
				{
					stringToBeReturned = "1";
				}
				else
				{
					stringToBeReturned = "Password is incorrect.";
				}
			}
			else
			{
				stringToBeReturned = "The user is inactive. Contact administrator for further information";
			}
		}
		else
		{
			stringToBeReturned = "The user does not exists!";
		}
		return stringToBeReturned;
	}

}

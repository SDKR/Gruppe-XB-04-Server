package SwitchLogic.Methods;

import java.sql.SQLException;

import DatabaseLogic.DatabaseConnection;
import model.Model;
import model.QueryBuild.QueryBuilder;
import model.QueryBuild.Where;

public class unsubFromCalendar extends Model {
	QueryBuilder QB = new QueryBuilder();
	DatabaseConnection DBC = new DatabaseConnection();
	
	public String removeUserFromCalendar(String email, String calendarName) throws SQLException {
		String stringToBeReturned ="";
		String resultSetStringCalendarID = "";
		String resultSetStringEmailID = "";
		String resultSetStringCalendarCreator = "";
		String resultSetStringPP = "";
		String testingIfAlreadySubscribedC = "";
		String testingIfAlreadySubscribedArray = "";
		String[] fields = {"userid", "CalendarID"};
		String[] otherValues = {"CalendarID", "CreatedBy", "PrivatePublic"};
		
		System.out.println("Email");
		System.out.println(email);
		System.out.println(calendarName);
		
		resultSet = QB.selectFrom(otherValues, "calendar").where("Name", "=", calendarName).ExecuteQuery();
		while(resultSet.next())
		{
			resultSetStringCalendarID = resultSet.getString("CalendarID");
			resultSetStringCalendarCreator = resultSet.getString("CreatedBy");
			resultSetStringPP = resultSet.getString("PrivatePublic");
			System.out.println("2");
			
		}
		System.out.println("Første IF");
		System.out.println(resultSetStringCalendarID);
		if(!resultSetStringCalendarID.equals(""))
		{

				resultSet = QB.selectFrom("users").where("email", "=", email).ExecuteQuery();
				while(resultSet.next())
				{
					System.out.println("while");
					resultSetStringEmailID = resultSet.getString("userid");
					System.out.println("3");
				}
				String[] values = {resultSetStringEmailID, resultSetStringCalendarID};
				String[] someValues = {"userid"};
				resultSet = QB.selectFrom(someValues, "userevents").where("CalendarID", "=", resultSetStringCalendarID).ExecuteQuery();
				while(resultSet.next())
				{
					testingIfAlreadySubscribedArray = testingIfAlreadySubscribedArray+" "+resultSet.getString("userid")+", ";
					System.out.println("34");
					
				}
				System.out.println("4");
				System.out.println(resultSetStringEmailID);
				System.out.println(testingIfAlreadySubscribedArray);
				if(testingIfAlreadySubscribedArray.contains(" "+resultSetStringEmailID+","))
				{
					DBC.deleteFromCalendar(resultSetStringCalendarID,resultSetStringEmailID);
//					QB.deleteHardFrom("userevents", fields).where("userid", "=", values).Execute();
					stringToBeReturned = "User succesfully unsubscribed from calendar";
				}
				else
				{
					System.out.println("5");
					stringToBeReturned = "User was not subscribed to begin with!";
				}
			
		}
		return stringToBeReturned;
	}
	
}

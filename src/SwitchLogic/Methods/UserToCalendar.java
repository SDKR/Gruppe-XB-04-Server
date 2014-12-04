package SwitchLogic.Methods;

import java.sql.SQLException;

import org.bouncycastle.jcajce.provider.asymmetric.RSA;

import model.Model;
import model.QueryBuild.QueryBuilder;
import model.QueryBuild.Where;

public class UserToCalendar extends Model{
	QueryBuilder QB = new QueryBuilder();

	public String addUserToCalendar(String email, String calendarName) throws SQLException {
		String stringToBeReturned ="";
		String resultSetStringCalendarID = "";
		String resultSetStringEmailID = "";
		String resultSetStringCalendarCreator = "";
		String resultSetStringPP = "";
		String testingIfAlreadySubscribedC = "";
		String testingIfAlreadySubscribedArray = "";
		String[] fields = {"userid", "CalendarID"};
		String[] otherValues = {"CalendarID", "CreatedBy", "PrivatePublic"};
		System.out.println("1");
		
		resultSet = QB.selectFrom(otherValues, "calendar").where("Name", "=", calendarName).ExecuteQuery();
		while(resultSet.next())
		{
			resultSetStringCalendarID = resultSet.getString("CalendarID");
			resultSetStringCalendarCreator = resultSet.getString("CreatedBy");
			resultSetStringPP = resultSet.getString("PrivatePublic");
			System.out.println("2");
			
		}
		
		if(!resultSetStringCalendarID.equals(""))
		{
			System.out.println("3");
			if(resultSetStringPP.equals("1")){
				resultSet = QB.selectFrom("users").where("email", "=", email).ExecuteQuery();
				while(resultSet.next())
				{
					resultSetStringEmailID = resultSet.getString("userid");
				}
				String[] values = {resultSetStringEmailID, resultSetStringCalendarID};
				String[] someValues = {"userid"};
				resultSet = QB.selectFrom(someValues, "userevents").where("CalendarID", "=", resultSetStringCalendarID).ExecuteQuery();
				while(resultSet.next())
				{
					testingIfAlreadySubscribedArray = testingIfAlreadySubscribedArray+" "+resultSet.getString("userid")+", ";
					
				}
				System.out.println("4");
				System.out.println(resultSetStringEmailID);
				System.out.println(testingIfAlreadySubscribedArray);
				if(!testingIfAlreadySubscribedArray.contains(" "+resultSetStringEmailID+","))
				{
					QB.insertInto("userevents", fields).values(values).Execute();
					stringToBeReturned = "User succesfully added to calendar";
				}
				else
				{
					System.out.println("5");
					stringToBeReturned = "You have already subscribed to this calendar!";
				}
			}
			else
			{
				if(resultSetStringCalendarCreator.equals(email))
				{
					resultSet = QB.selectFrom("users").where("email", "=", email).ExecuteQuery();
					while(resultSet.next())
					{
						resultSetStringEmailID = resultSet.getString("userid");
					}
					String[] values = {resultSetStringEmailID, resultSetStringCalendarID};
					String[] someValues = {"CalendarID"};
					resultSet = QB.selectFrom(someValues, "userevents").where("userid", "=", resultSetStringEmailID).ExecuteQuery();
					while(resultSet.next())
					{
						testingIfAlreadySubscribedC = resultSet.getString("CalendarID");
					}
					if(testingIfAlreadySubscribedC.equals(""))
					{
						QB.insertInto("userevents", fields).values(values).Execute();
						stringToBeReturned = "User succesfully added to calendar";
					}
					else
					{
						stringToBeReturned = "You have already subscribed to this calendar!";
					}
				}
				else
				{
					stringToBeReturned = "You cant subscribe to private calendars you are not the creator of.";
				}
			}
			
		}
		else
		{
			stringToBeReturned = "The calendar does not exists";
		}
		return stringToBeReturned;
	}
}
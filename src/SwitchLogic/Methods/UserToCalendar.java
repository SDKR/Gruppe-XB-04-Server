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
		String testingIfAlreadySubscribed = "";
		String[] fields = {"userid", "CalendarID"};
		
		resultSet = QB.selectFrom("calendar").where("Name", "=", calendarName).ExecuteQuery();
		while(resultSet.next())
		{
			resultSetStringCalendarID = resultSet.getString("CalendarID");
			resultSetStringCalendarCreator = resultSet.getString("CreatedBy");
			resultSetStringPP = resultSet.getString("PrivatePublic");
			
		}
		
		if(!resultSetStringCalendarID.equals(""))
		{
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
					testingIfAlreadySubscribed = resultSet.getString("CalendarID");
				}
				if(testingIfAlreadySubscribed.equals(""))
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
						testingIfAlreadySubscribed = resultSet.getString("CalendarID");
					}
					if(testingIfAlreadySubscribed.equals(""))
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
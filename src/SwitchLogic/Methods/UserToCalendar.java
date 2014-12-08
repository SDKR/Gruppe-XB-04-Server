package SwitchLogic.Methods;

import java.sql.SQLException;
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

	public String addOtherUserToCalender(String subscriber, String username, String calendarName) {
		String stringToBeReturned = "";
		String subscriberString = "";
		String subscribedID = "";
		String calendarID = "";
		String usernameID = "";
		String[] creatorValue = {"CalendarID", "CreatedBy"};
		String[] userEventsFields = {"userid", "CalendarID"};
		String[] userEventsValues = {usernameID, calendarID};
		String[] subscriptionValues = {"userid"};
		try {
			resultSet = QB.selectFrom(creatorValue, "calendar").where("Name", "=", calendarName).ExecuteQuery();
			while(resultSet.next())
			{
				calendarID = resultSet.getString("CalendarID");
				subscriberString = resultSet.getString("CreatedBy");
			}
			if(!calendarID.equals(""))
			{
			if(subscriberString.equals(subscriber))
			{
				resultSet = QB.selectFrom(subscriptionValues, "users").where("email", "=", username).ExecuteQuery();
				while(resultSet.next())
				{
					usernameID = resultSet.getString("userid");
				}
				resultSet = QB.selectFrom(subscriptionValues, "userevents"). where("CalendarID", "=", calendarID).ExecuteQuery();
				while(resultSet.next())
				{
					subscribedID = subscribedID+" "+resultSet.getString("userid")+", ";
				}
				
				if(!subscribedID.contains(" "+usernameID+","))
				{
					userEventsValues[0]=usernameID;
					userEventsValues[1]=calendarID;
					QB.insertInto("userevents", userEventsFields).values(userEventsValues).Execute();
					stringToBeReturned = "You have succesfully subscribed an user to your calendar!";
				}
				else
				{
					stringToBeReturned = "The user is already subscribed to this calendar";
				}
			}
			else
			{
				stringToBeReturned = "You cannot subscribe other people to calendars you do not own";
			}
			}
			else
			{
				stringToBeReturned = "The calendar does not exists";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stringToBeReturned;
	}
}
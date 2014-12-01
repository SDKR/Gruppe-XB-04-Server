package SwitchLogic.Methods;

import java.sql.SQLException;

import org.bouncycastle.jcajce.provider.asymmetric.RSA;

import model.Model;
import model.QueryBuild.QueryBuilder;

public class UserToCalendar extends Model{
	QueryBuilder QB = new QueryBuilder();

	public String addUserToCalendar(String email, String calendarName) throws SQLException {
		String stringToBeReturned ="";
		String resultSetStringCalendarID = "";
		String resultSetStringEmailID = "";
		String[] fields = {"userid", "CalendarID"};
		
		resultSet = QB.selectFrom("calendar").where("Name", "=", calendarName).ExecuteQuery();
		while(resultSet.next())
		{
			resultSetStringCalendarID = resultSet.getString("CalendarID");
		}
		if(!resultSetStringCalendarID.equals(""))
		{
			resultSet = QB.selectFrom("users").where("email", "=", email).ExecuteQuery();
			while(resultSet.next())
			{
				resultSetStringEmailID = resultSet.getString("userid");
			}
			String[] values = {resultSetStringEmailID, resultSetStringCalendarID};
			QB.insertInto("userevents", fields).values(values).Execute();
			stringToBeReturned = "User succesfully added to calendar";
		}
		else
		{
			stringToBeReturned = "The calendar does not exists";
		}
		return stringToBeReturned;
	}
}
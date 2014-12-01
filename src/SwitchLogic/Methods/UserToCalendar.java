package SwitchLogic.Methods;

import java.sql.SQLException;

import org.bouncycastle.jcajce.provider.asymmetric.RSA;

import model.Model;
import model.QueryBuild.QueryBuilder;

public class UserToCalendar extends Model{
	QueryBuilder QB = new QueryBuilder();

	public String addUserToCalendar(String email, String calendarName) throws SQLException {
		String stringToBeReturned ="";
		String resultSetString = "";
		String[] fields = {"userid", "CalendarID"};
		
		resultSet = QB.selectFrom("calendar").where("Name", "=", calendarName).ExecuteQuery();
		while(resultSet.next())
		{
			resultSetString = resultSet.getString("CalendarID");
		}
		if(!resultSetString.equals(""))
		{
			String[] values = {email, resultSetString};
			QB.insertInto("userevents", fields).values(values);
			stringToBeReturned = "User succesfully added to calendar";
		}
		else
		{
			stringToBeReturned = "The calendar does not exists";
		}
		return stringToBeReturned;
	}
}
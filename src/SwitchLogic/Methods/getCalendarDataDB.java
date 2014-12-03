package SwitchLogic.Methods;

import java.sql.SQLException;

import com.google.gson.Gson;

import model.Model;
import model.QueryBuild.QueryBuilder;

public class getCalendarDataDB extends Model {
	public String getCalendarData()
	{
		QueryBuilder QB = new QueryBuilder();
		Gson gson = new Gson();
		String stringToBeReturned = "";
		int rowCounterSQL  = 0;
		try {
			resultSet = QB.selectFrom("calendar").all().ExecuteQuery();
			while(resultSet.next())
			{
				rowCounterSQL++;
			}
			String[][] calendarData = new String[5][rowCounterSQL];
			resultSet = QB.selectFrom("calendar").all().ExecuteQuery();
			int stringArrayCounter = 0;
			while(resultSet.next())
			{
				calendarData[0][stringArrayCounter]  = resultSet.getString("CalendarID");
				calendarData[1][stringArrayCounter]  = resultSet.getString("Name");
				calendarData[2][stringArrayCounter]  = resultSet.getString("Active");
				calendarData[3][stringArrayCounter]  = resultSet.getString("CreatedBy");
				calendarData[4][stringArrayCounter]  = resultSet.getString("PrivatePublic");
			stringArrayCounter++;	
			}
			stringToBeReturned = gson.toJson(calendarData);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(stringToBeReturned);
		return stringToBeReturned;
	}

}

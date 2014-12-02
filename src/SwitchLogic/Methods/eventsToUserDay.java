package SwitchLogic.Methods;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import com.google.gson.Gson;

import model.Model;
import model.QueryBuild.QueryBuilder;

public class eventsToUserDay extends Model {
	
	QueryBuilder QB = new QueryBuilder();
	Gson gson = new Gson();
	public String getEvents(String authUserEmail) {
		
		String stringToBeReturned = "";
		String userID = "";
		int arrayCounter = 0;
		int rowCounter = 0;
		ArrayList<String> calendarIDs = new ArrayList<String>();
		String[] values1 = {"userid"};
		String[] values2 = {"CalendarID"};
		String[] eventValues = {"type", "locationName", "start", "end", "name"};
		try {
			resultSet = QB.selectFrom(values1, "users").where("email", "=", authUserEmail).ExecuteQuery();
			while(resultSet.next())
			{
				userID = resultSet.getString("userid");
			}
			resultSet = QB.selectFrom(values2, "userevents").where("userid", "=", userID).ExecuteQuery();
			while(resultSet.next())
			{
				calendarIDs.add(resultSet.getString("CalendarID"));
			}
			for(int i = 0 ; i < calendarIDs.size(); i++)
			{
				resultSet = QB.selectFrom("events").where("CalendarID", "=", calendarIDs.get(i)).ExecuteQuery();
				while(resultSet.next())
				{
					rowCounter++;
				}
				
			}
			String[][] eventsToJson = new String[5][rowCounter];
			for(int i = 0 ; i < calendarIDs.size(); i++)
			{
				resultSet = QB.selectFrom(eventValues, "events").where("CalendarID", "=", calendarIDs.get(i)).ExecuteQuery();
				while(resultSet.next())
				{
					if(checkDate(resultSet.getString("start"))== true)
					{
						eventsToJson[0][arrayCounter] = resultSet.getString("type");
						eventsToJson[1][arrayCounter] = resultSet.getString("locationName");
						eventsToJson[2][arrayCounter] = resultSet.getString("start");
						eventsToJson[3][arrayCounter] = resultSet.getString("end");
						eventsToJson[4][arrayCounter] = resultSet.getString("name");
					}
					else
					{
						System.out.println("Its not the correct date");
					}
					arrayCounter++;
				}
			}
			stringToBeReturned = gson.toJson(eventsToJson);
			System.out.println(stringToBeReturned);
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}
		return stringToBeReturned;
	}
	
	public boolean checkDate(String dateStamp) throws ParseException {
		boolean booleanToBeReturned = false;
		DateFormat formatter;
		Date date;
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		date = (Date) formatter.parse(dateStamp);
	
		Date dateToday = new Date();
		int todaysDate = dateToday.getDate();
		int todaysYear = dateToday.getYear();
		int todaysMonth = dateToday.getMonth();
		int courseDate = date.getDate();
		System.out.println(courseDate);
		int courseMonth = date.getMonth()+1;
		System.out.println(courseMonth);
		int courseYear = date.getYear();
		System.out.println(courseYear);
		System.out.println(todaysYear);
		if(courseYear == todaysYear)
		{
			if(courseMonth == todaysMonth)
			{
				if(courseDate == todaysDate)
				{
					System.out.println("Succes");
					booleanToBeReturned = true;
				}
				else
				{
					booleanToBeReturned = false;
					System.out.println("Its not the same day");
				}
			}
			else
			{
				booleanToBeReturned = false;
				System.out.println("Its not the same month");
			}
		}
		else
		{
			System.out.println("Its not the same year");
			booleanToBeReturned = false;
		}
		
		
		

		return booleanToBeReturned;
	}
	public static void main (String [] args) throws ParseException
	{
		eventsToUserDay ETU = new eventsToUserDay();
		ETU.getEvents("aa");
	}

}

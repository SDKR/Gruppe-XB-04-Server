package SwitchLogic.Methods;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JOptionPane;

import DatabaseLogic.DatabaseConnection;
import GUILogic.Logic;
import model.Model;
import model.QueryBuild.QueryBuilder;

public class Events extends Model{
	
	QueryBuilder qb = new QueryBuilder();
	DatabaseConnection DBC = new DatabaseConnection();
	
	public long checkDate(String year, String month, String day, String hour,
			String minute) throws ParseException {
		long longToBeReturned = 0;
		String dateToCheck = year + "/" + month + "/" + day + " " + hour + ":"
				+ minute + ":00";
		Date dating = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss",
				Locale.TRADITIONAL_CHINESE).parse(dateToCheck);
		longToBeReturned = dating.getTime();

		return longToBeReturned;
	}
	
	
	public String checkEvent(String eventid, String cbsEventId, String type, String location, String locationName, String createdby, String start, String end, String name, String text, String customevent, String CalendarID, String startYear, String startMonth, String startDay, String startHour, String startMinute, String endYear, String endMonth, String endDay, String endHour, String endMinute) throws SQLException{
		
		String endTime = endYear + "-" + endMonth + "-" + endDay + " "
				+ endHour + ":" + endMinute + ":00";
		String startTime = startYear + "-" + startMonth + "-" + startDay
				+ " " + startHour + ":" + startMinute + ":00";
		
		String stringToBeReturned = "";
		testConnection();
		
		if (!name.equals("Enter Event Name") || !name.equals("")) {
			if (!type.equals("Choose Type")) {
				try {
					long checkStartTime = checkDate(startYear, startMonth,
							startDay, startHour, startMinute);
					long checkEndTime = checkDate(endYear, endMonth,
							endDay, endHour, endMinute);

					if (checkStartTime < checkEndTime) {
						Date date = new Date();
						if (checkStartTime > date.getTime()) {
							if (!CalendarID.equals("0")) {
								if (!text
										.equals("If any, enter further information here...")) {
									DBC.createNewEvent(type, location,
											startTime, endTime, name,
											text, CalendarID);
									stringToBeReturned = "Success";
								} else {
									stringToBeReturned = "The info text cannot be the default one.";
								}
							} else {
								
												stringToBeReturned = "You have to select a calendar to which the event belongs";
							}
						} else {
							
											 stringToBeReturned = "You cannot create an event in the past.";
						}
					} else {
						
										stringToBeReturned = "You cannot have an end-time earlier than start time.";
					}

				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
			stringToBeReturned = "You have to choose a type for the event.";
			}
		} else {
			stringToBeReturned = "You have to enter an eventname";
		}
	

		
		return stringToBeReturned;
	}
	
	public void createNewEvent(int eventid, String cbsEventId, String type, String location, String locationName, String createdby, String startTime, String endTime, String name, String text, String customevent, String CalendarID) throws SQLException{
	
		String [] fields = {
			"type", "location", "locationName", "createdby", "start", "end", "name", "text", "customevent", "CalendarID"};
		String [] values = {type, location, locationName, createdby, startTime, endTime, name, text, customevent, CalendarID};
		qb.insertInto("Events", fields).values(values).Execute();
		
		
	}

}

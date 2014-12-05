package SwitchLogic.Methods;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import JsonClasses.NoteJson;
import model.Model;
import model.QueryBuild.QueryBuilder;

public class NoteFunctions extends Model{
	QueryBuilder QB = new QueryBuilder();
	NoteJson NJR = new NoteJson();
	Gson gson = new GsonBuilder().create();

	public String getNote(String eventID) {
		String stringToBeReturned = "";
		String[] noteValues = {"createdBy", "text", "dateTime", "eventid"};
		String[] returnValues = new String[4];
		String contentChecker = "";
		try {
			System.out.println("NU er vi her");
			resultSet = QB.selectFrom("notes").where("eventid", "=", eventID).ExecuteQuery();
			while(resultSet.next())
			{
				contentChecker = resultSet.getString("text");
			}
			if(!contentChecker.equals(""))
			{
			resultSet = QB.selectFrom(noteValues, "notes").where("eventid", "=", eventID).ExecuteQuery();
			while(resultSet.next())
			{
				returnValues[0] = resultSet.getString("createdBy");
				returnValues[1] = resultSet.getString("text");
				returnValues[2] = resultSet.getString("dateTime");
				returnValues[3] = resultSet.getString("eventid");
			}
				NJR.setCreatedBy(returnValues[0]);
				NJR.setNote(returnValues[1]);
				NJR.setDateTime(returnValues[2]);
				NJR.setEventID(returnValues[3]);
				stringToBeReturned = gson.toJson(NJR);
			}
			else
			{
				stringToBeReturned = "There is no note added to this event.";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stringToBeReturned;
	}

	public String updateNote(String eventID, String note, String createdBy) {
		String stringToBeReturned = "";
		String existingChecker = "";
		String time = getTime();
		String[] insertFields = {"createdBy", "text", "dateTime", "eventid"};
		String[] insertValues = {createdBy, note, time, eventID};
		String[] updateValues = {createdBy, note, time};
		String[] updateFields = {"createdBy", "text", "dateTime"};
		try {
			resultSet = QB.selectFrom("notes").where("eventid", "=", eventID).ExecuteQuery();
			while(resultSet.next())
			{
				existingChecker = resultSet.getString("eventid");
			}
			if(!existingChecker.equals(""))
			{
				QB.update("notes", updateFields, updateValues).where("eventid", "=", eventID).Execute();
				stringToBeReturned = "Note has been updated";
			}
			else
			{
				QB.insertInto("notes", insertFields).values(insertValues).Execute();
				stringToBeReturned = "Note has been created";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stringToBeReturned;
	}

	private String getTime() {
		String timeStamp = "";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		timeStamp = dateFormat.format(date);
		return timeStamp;
	}

}

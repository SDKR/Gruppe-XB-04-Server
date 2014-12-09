package SwitchLogic.Methods;
import java.sql.SQLException;
import model.Model;
import model.QueryBuild.QueryBuilder;

public class DeleteEvent extends Model {

	public String deleteEvent(String calendarID, String userName) {
		QueryBuilder QB = new QueryBuilder();
		String stringToBeReturned ="";
		String checkIfExists = "";
		String checkIfActive = "";
		String checkIfCbs = "";
		String userID = "";
		String[] updateValues = {"0"};
		String[] updateFields = {"active"};
		String[] checkUserValues = {"userid"};
		String[] checkIfExistsValues = {"eventid", "CreatedBy", "customevent", "active"};
		try {
			System.out.println(calendarID+userName);
			resultSet = QB.selectFrom(checkIfExistsValues, "events").where("eventid", "=", calendarID).ExecuteQuery();
			while(resultSet.next())
			{
				checkIfExists = resultSet.getString("CreatedBy");
				checkIfActive = resultSet.getString("active");
				checkIfCbs = resultSet.getString("customevent");
			}
			resultSet = QB.selectFrom(checkUserValues, "users").where("email", "=", userName).ExecuteQuery();
			while(resultSet.next())
			{
				userID = resultSet.getString("userid");
			}
			System.out.println(checkIfExists);
			if(!checkIfExists.equals(""))
			{
				if(checkIfExists.equals(userID))
				{
					if(!checkIfActive.equals("0"))
					{
						if(!checkIfCbs.equals("1"))
						{
							//QB.update(tableName, fields, values)
							QB.update("events", updateFields, updateValues).where("eventid", "=", calendarID).Execute();
							stringToBeReturned = "Event succesfully deleted!";
						}
						else
						{
							stringToBeReturned = "You cannot delete events from CBS";
						}
					}
					else
					{
						stringToBeReturned = "The event is already inactive";
					}
				}
				else
				{
					stringToBeReturned = "You cannot delete events you are not the creator of";
				}
			}
			else
			{
				stringToBeReturned = "The event does not exists";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stringToBeReturned;
	}
}
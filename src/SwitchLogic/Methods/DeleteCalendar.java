package SwitchLogic.Methods;

import java.sql.SQLException;
import model.Model;
import model.QOTD.QOTDModel;
import model.QueryBuild.QueryBuilder;

public class DeleteCalendar extends Model {
	QueryBuilder qb = new QueryBuilder();
	QOTDModel qm = new QOTDModel();
	public String deleteCalender (String userName, String calenderName) throws SQLException
	{
		String stringToBeReturned ="";
		testConnection();
		stringToBeReturned = removeCalender(userName, calenderName);

		return stringToBeReturned;
	}
	
	public String removeCalender (String userName, String calenderName) throws SQLException
	{
		String stringToBeReturend = "";
		String usernameOfCreator ="";
		String calenderExists = "";
		resultSet = qb.selectFrom("calendar").where("Name", "=", calenderName).ExecuteQuery();
				
//				("select * from calender where Name = '"+calenderName+"';");
		while(resultSet.next())
		{
			calenderExists = resultSet.toString();
			System.out.println(calenderExists + " Det er her det går galt.");
		}
		if(!calenderExists.equals(""))
		{
			String [] value = {"CreatedBy"};
			resultSet = qb.selectFrom(value, "Calendar").where("Name", "=", calenderName).ExecuteQuery();
			while(resultSet.next())
			{
				usernameOfCreator = resultSet.getString("CreatedBy");
				System.out.println(usernameOfCreator + "Her går username galt");
			}
			if(!usernameOfCreator.equals(userName))
			{
				stringToBeReturend = "Only the creator of the calender is able to delete it.";
			}
			else
			{
				String [] keys = {"Active"};
				String [] values = {"2"};
				qb.update("Calendar", keys, values).where("Name", "=", calenderName).Execute();
				stringToBeReturend = "Calendar has been set inactive";
			}
		}
		else
		{
			stringToBeReturend = "The calender you are trying to delete, does not exists.";
		}
		return stringToBeReturend;
	}

}

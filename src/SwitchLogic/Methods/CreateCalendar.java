package SwitchLogic.Methods;

import java.sql.SQLException;

import model.Model;
import model.QOTD.QOTDModel;
import model.QueryBuild.QueryBuilder;
import DatabaseLogic.DatabaseConnection;

public class CreateCalendar extends Model {
	DatabaseConnection DBC = new DatabaseConnection();
	QueryBuilder qb = new QueryBuilder();
	QOTDModel qm = new QOTDModel();
	public String createNewCalender (String userName, String calenderName, int privatePublic) throws SQLException
	{
		System.out.println("Vi er inde i klassen uden fejl");
		String stringToBeReturned ="";
		testConnection();
		System.out.println("Vi har testet forbindelsen uden fejl");
		if(authenticateNewCalender(calenderName) == false)
		{
			System.out.println("Vi prøver at authenticate uden fejl");
			addNewCalender(calenderName, userName, privatePublic);
			stringToBeReturned = "The new calender has been created!";
			System.out.println(stringToBeReturned);
			System.out.println("Vi har authenticated uden fejl");
		}
		else
		{
			System.out.println("Kalenderen eksisterer allerede");
			stringToBeReturned = "The new calender has not been created!";
		}
		System.out.println("Metoden burde umiddelbart virke?");
		return stringToBeReturned;
	}
	
	public boolean authenticateNewCalender(String newCalenderName) throws SQLException
	{
		getConn();
		boolean authenticate = false;
		
		resultSet= qb.selectFrom("calendar").where("name", "=", newCalenderName).ExecuteQuery();
				
				//("select * from test.calender where Name = '"+newCalenderName+"';");
		while(resultSet.next())
		{
			authenticate = true;
		}
		return authenticate;
	}
	
	public void addNewCalender (String newCalenderName, String userName, int publicOrPrivate) throws SQLException
	{
		System.out.println("Vi er ny i addNewCalendar uden fejl");
		String [] keys = {"Name","Active","CreatedBy","PrivatePublic"};
		System.out.println(keys[0] + keys[1]+ keys[2]+ keys[3]);
		System.out.println("Vi har nu smidt noget data ind i keys uden fejl");
		String [] values = {newCalenderName,"1",userName, Integer.toString(publicOrPrivate)};
		System.out.println(values[0] + values[1]+ values[2]+ values[3]);
		System.out.println("Vi har nu smidt noget data ind i values uden fejl");
		qb.insertInto("calendar", keys).values(values).Execute();
		System.out.println("Vi burde have oprettet den nye kalender uden fejl");
		
//		doUpdate("insert into test.calender (Name, Active, CreatedBy, PrivatePublic) VALUES ('"+newCalenderName+"', '1', '"+userName+"', '"+publicOrPrivate+"');");
	}

}

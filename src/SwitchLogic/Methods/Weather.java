package SwitchLogic.Methods;
import model.Model;
import model.QOTD.*;

import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.QueryBuild.QueryBuilder;
import DatabaseLogic.DatabaseConnection;
import JsonClasses.WeatherJson;

public class Weather extends Model {

	DatabaseConnection DBC = new DatabaseConnection();
	QueryBuilder qb = new QueryBuilder();
	WeatherJson WJ = new WeatherJson();
	Gson gson = new GsonBuilder().create();

	public String weatherCheck()
			throws SQLException {
		String stringToBeReturned = "";
		testConnection();
		
		resultSet = qb.selectFrom("weathertable").all().ExecuteQuery();
		System.out.println("Vi kører dette her");
		int counter = 1;
		while(resultSet.next() && counter < 2){
			WJ.setDegrees(resultSet.getString("weatherDegrees"));
			WJ.setDesc(resultSet.getString("weatherDesc"));
			WJ.setWeather(resultSet.getString("weatherDate"));
			System.out.println("Vi kører RS");
			counter++;
			
		}
		stringToBeReturned = gson.toJson(WJ);
		System.out.println(stringToBeReturned);
		
		
		

		return stringToBeReturned;
	}

}

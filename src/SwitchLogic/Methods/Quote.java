package SwitchLogic.Methods;
import model.Model;
import model.QOTD.*;

import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.QueryBuild.QueryBuilder;
import DatabaseLogic.DatabaseConnection;
import JsonClasses.QuoteJson;



public class Quote extends Model {

		DatabaseConnection DBC = new DatabaseConnection();
		QueryBuilder qb = new QueryBuilder();
		QuoteJson QJ = new QuoteJson();
		Gson gson = new GsonBuilder().create();

		public String quoteCheck() throws SQLException {
			QuoteJson QJ2 = new QuoteJson();
			String stringToBeReturned = "";
			testConnection();
			
			resultSet = qb.selectFrom("qotd").all().ExecuteQuery();
			System.out.println("Vi kører dette her");
			int counter = 1;
			while(resultSet.next() && counter < 2){
				QJ2.setAuthor(resultSet.getString("author"));
				QJ2.setQuote(resultSet.getString("Quote"));
				System.out.println("Vi kører RS");
				counter++;
				
			}
			stringToBeReturned = gson.toJson(QJ2);
			System.out.println(stringToBeReturned);
			
			
			

			return stringToBeReturned;
		}

	}




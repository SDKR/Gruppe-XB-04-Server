package GUILogic;

import java.util.TimerTask;

import DatabaseLogic.DatabaseConnection;
import model.calendar.GetCalendarData;

public class MyTask extends TimerTask{
	GetCalendarData GCD = new GetCalendarData();
	DatabaseConnection DC = new DatabaseConnection();
	
	public MyTask(){
	     
	   }

	   @Override
	   public void run() {
	     System.out.println("Hi see you after 10 seconds");
	     try {
	    	 Logic l = new Logic();
			GCD.getDataFromCalendar();
			DC.clearWeatherQuote();
			l.saveWeather();
			l.displayWeather();
			l.displayQuote();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	   }

}

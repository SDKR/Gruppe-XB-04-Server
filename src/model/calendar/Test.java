package model.calendar;

public class Test {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Events etest = new Events();
		GetCalendarData GCD = new GetCalendarData();
		try {
			GCD.getDataFromCalendar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		etest.getEvents();
	}

}

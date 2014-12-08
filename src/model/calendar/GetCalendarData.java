package model.calendar;

import DatabaseLogic.DatabaseConnection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jesperbruun on 13/10/14.
 */
public class GetCalendarData {
	
	EncryptUserID e = new EncryptUserID();
	private long cbsTimeStamp;

	//henter data fra URL og l??er ind til en string
    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }
    //Nu har vi alle data liggende i en string (JSON). 
    //Saa bruger vi Google's udviklede library Json string. den kan lave det om til java objekter
    //Events laver en arraylist af Event
    
    /**
     * Allows client to retrieve CBS's calendar and then access it.
     * @throws Exception
     */
    private static int arrayRows; 
    
    public void checkTimeSinceLastUpdate()
    {
    	Calendar dateCal = Calendar.getInstance();
    	// make it tomorrow
    	dateCal.add(Calendar.DAY_OF_YEAR, 1);
    	long lastUpdate = getCbsTimeStamp();
    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	Date date = new Date();
    	long todayDateStamp = date.getTime();
    	System.out.println(todayDateStamp);
    	System.out.println(getCbsTimeStamp());
    	System.out.println((todayDateStamp-getCbsTimeStamp()));
    	if((todayDateStamp-getCbsTimeStamp())>3600)
    	{
    		try {
				getDataFromCalendar();
			} catch (Exception e) {
				e.printStackTrace();
			}
    		setCbsTimeStamp(todayDateStamp);
    		System.out.println(todayDateStamp);
    		System.out.println(getCbsTimeStamp());
    	}
    	else
    	{
    		System.out.println("Well.. we'll wait a little bit more.");
    	}
    }
 
    public void getDataFromCalendar() throws Exception {
    	
    	Gson gson1 = new GsonBuilder().create();
    	DatabaseConnection DC = new DatabaseConnection();
    	DC.keyImporter();
        /**
         * Get URL From calendar.cbs.dk -> Subscribe -> change URL to end with .json
         * Encrypt hash from
         */
    	String userID = "asth13ac";
        String json = readUrl("http://calendar.cbs.dk/events.php/"+userID+"/"+"92a62decac8a6524ec6c382a0ca27419"+".json");
    	Events event = gson1.fromJson(json, Events.class);
    
    	int rCount = 0;
        DC.clearOldCBSData();
        setArrayRows(120);
        while(rCount < arrayRows) 
        {
        	String startTime = startTimeToString(event.getEvents().get(rCount).getStart());
        	String endTime = endTimeToString(event.getEvents().get(rCount).getEnd());
        	DC.addingCBSCalendarToDB(event.getEvents().get(rCount).getActivityid(), event.getEvents().get(rCount).getType(), event.getEvents().get(rCount).getLocation(), startTime, endTime, event.getEvents().get(rCount).getDescription());
        	rCount++;
        	System.out.println(arrayRows);
        }
    }
    private String endTimeToString(ArrayList<String> end) {
		String stringToBeReturned = (end.get(0)+"-"+end.get(1)+"-"+end.get(2)+" "+end.get(3)+":"+end.get(4)+":00");
		return stringToBeReturned;
	}
	private String startTimeToString(ArrayList<String> arrayList) {
		String stringToBeReturned = (arrayList.get(0)+"-"+arrayList.get(1)+"-"+arrayList.get(2)+" "+arrayList.get(3)+":"+arrayList.get(4)+":00");
		return stringToBeReturned;
}

	public static void setArrayRows(int arrayRows) {
		GetCalendarData.arrayRows = arrayRows;
	}
	public void setCbsTimeStamp(long cbsTimeStamp) {
		this.cbsTimeStamp = cbsTimeStamp;
	}
	public long getCbsTimeStamp() {
		return cbsTimeStamp;
	}
	}

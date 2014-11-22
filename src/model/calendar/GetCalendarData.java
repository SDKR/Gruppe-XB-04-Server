package model.calendar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by jesperbruun on 13/10/14.
 */
public class GetCalendarData {
	
	EncryptUserID e = new EncryptUserID();

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
    public void getDataFromCalendar() throws Exception {

    	Gson gson1 = new GsonBuilder().create();
        /**
         * Get URL From calendar.cbs.dk -> Subscribe -> change URL to end with .json
         * Encrypt hash from
         */
    	String userID = "asth13ac";
        String json = readUrl("http://calendar.cbs.dk/events.php/"+userID+"/"+"92a62decac8a6524ec6c382a0ca27419"+".json");
    	Events event = gson1.fromJson(json, Events.class);
    	
    	System.out.println(event);
        
        System.out.println(json);
        System.out.println(event.getEvents().get(1).getActivityid());
        System.out.println(event.getEvents().get(1).getCreatedby());
        System.out.println(event.getEvents().get(1).getDescription());
        System.out.println(event.getEvents().get(1).getStart().get(0));
        System.out.println(event.getEvents().size());
        
        for(int rCount = 0 ; rCount < event.getEvents().size() ; rCount++)
        {
        	System.out.println(event.getEvents().get(rCount).getActivityid());
        }
    }
}

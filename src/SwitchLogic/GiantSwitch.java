package SwitchLogic;
import model.QOTD.QOTDModel;
import model.note.Note;
import JsonClasses.AddNoteJson;
import JsonClasses.AuthUserJson;
import JsonClasses.CreateCalendarJson;
import JsonClasses.DeleteCalendarJson;
import JsonClasses.EventsJson;
import JsonClasses.GetAllCalendar;
import JsonClasses.NoteJson;
import JsonClasses.QuoteJson;
import JsonClasses.WeatherJson;
import JsonClasses.EventsDayJson;
import JsonClasses.EventsWeekJson;
import JsonClasses.subscribeUserJson;
import JsonClasses.userToCalendarJson;
import SwitchLogic.Methods.*;

import com.google.gson.*;

public class GiantSwitch {
	public String GiantSwitchMethod(String jsonString) throws Exception {
		Weather WH = new Weather();

		//Events eventsKlasse = new Events(0, 0, 0, jsonString, jsonString, jsonString, jsonString, jsonString);

		Note noteKlasse = new Note();
		//ForecastModel forecastKlasse = new ForecastModel();
		QOTDModel QOTDKlasse = new QOTDModel();
		Gson gson = new GsonBuilder().create();
		CreateCalendar CC = new CreateCalendar();
		DeleteCalendar DC = new DeleteCalendar();
		UserLogin UL = new UserLogin();
		getCalendarDataDB GCDDB = new getCalendarDataDB();
		NoteFunctions NF = new NoteFunctions();

		Quote quote = new Quote();
		Events eve = new Events();

		UserToCalendar UTC = new UserToCalendar();
		eventsToUserDay ETUD = new eventsToUserDay();
		eventsToUserWeek ETUW = new eventsToUserWeek();

		String answer = "";	
		//Creates a switch which determines which method should be used. Methods will be applied later on
		switch (Determine(jsonString)) {
		//If the Json String contains one of the keywords below, run the relevant method.

		/************
		 ** SUBSCRIBE **
		 ************/

		case "addUserToCalendar":
			userToCalendarJson UTCJ = gson.fromJson(jsonString, userToCalendarJson.class);
			System.out.println("Vi er inde i switchen");
			answer = UTC.addUserToCalendar(UTCJ.getEmail(), UTCJ.getCalendarName());
			break;
			
		case "addOtherUserToCalendar":
			subscribeUserJson SUJ = gson.fromJson(jsonString, subscribeUserJson.class);
			System.out.println("Vi prøver at subribe en anden bruger");
			answer = UTC.addOtherUserToCalender(SUJ.getSubscriber(), SUJ.getUsername(), SUJ.getCalendarName());
			break;

		/**********
		 ** LOGIN **
		 **********/
		case "logIn":		
			AuthUserJson AU = gson.fromJson(jsonString, AuthUserJson.class);
			System.out.println("Recieved logIn");
			System.out.println("Vi kan kï¿½re det!");
			answer = UL.authenticateUser(AU.getAuthUserEmail(), AU.getAuthUserPassword(), AU.getAuthUserIsActive());
			break;

		/*************
		 ** CALENDAR **
		 *************/
		case "createCalendar":
			CreateCalendarJson CCJ = gson.fromJson(jsonString, CreateCalendarJson.class);
			System.out.println(CCJ.getCalenderName()+ " Den har lagt det nye ind i klassen");
			answer = CC.createNewCalender(CCJ.getUserName(), CCJ.getCalenderName(), CCJ.getPublicOrPrivate());
			System.out.println(answer);
			break;
		
		case "deleteCalendar":
			DeleteCalendarJson DCJ = gson.fromJson(jsonString, DeleteCalendarJson.class);
			System.out.println(DCJ.getCalenderName()+ "Deleted");
			answer = DC.deleteCalender(DCJ.getUserName(), DCJ.getCalenderName());
			break;

		case "createEvent":
			System.out.println("Recieved createEvent");
			EventsJson eventsJ = gson.fromJson(jsonString, EventsJson.class);
			System.out.println(eventsJ.getCbsEventId() + eventsJ.getType() + "Added");
			answer = eve.checkEvent(eventsJ.getEventid(), eventsJ.getCbsEventId(), eventsJ.getType(), eventsJ.getLocationName(), eventsJ.getLocationName(), eventsJ.getCreatedBy(), eventsJ.getStart(), eventsJ.getEnd(), eventsJ.getName(), eventsJ.getText(), eventsJ.getCustomevent(), eventsJ.getCalendarID(), eventsJ.getStartYear(), eventsJ.getStartMonth(), eventsJ.getStartDay(), eventsJ.getStartHour(), eventsJ.getStartMinute(), eventsJ.getEndYear(), eventsJ.getEndMonth(), eventsJ.getEndDay(), eventsJ.getEndHour(), eventsJ.getEndMinute());
			System.out.println(answer);

		case "getEventsDay":
			EventsDayJson EDJ = gson.fromJson(jsonString, EventsDayJson.class);
			answer = ETUD.getEvents(EDJ.getCreatedby());

			break;

		case "getEventsWeek":
			EventsWeekJson EWJ = gson.fromJson(jsonString, EventsWeekJson.class);
			answer = ETUW.getEvents(EWJ.getCreatedby());
			System.out.println("Recieved saveEvent");
			break;

		case "getAllCalendars":
			GetAllCalendar GAC = gson.fromJson(jsonString, GetAllCalendar.class);
			answer = GCDDB.getCalendarData();
			System.out.println("Recieved getAllCalendars");
			break;
			
		case "deleteEvent":
			System.out.println("Recieved deleteEvent");
		
		case "saveNote":
			System.out.println("Recieved saveNote");
			break;

		case "getThemNotesPlaya":
			NoteJson NJ = gson.fromJson(jsonString, NoteJson.class);
			answer = NF.getNote(NJ.getEventID());
			break;
			
		case "addThemNotesPlaya":
			AddNoteJson ANJ = gson.fromJson(jsonString, AddNoteJson.class);
			answer = NF.updateNote(ANJ.getEventID(), ANJ.getNote(), ANJ.getCreatedBy());
			break;

		/**********
		 ** QUOTE **
		 **********/
		case "getQuote":
			System.out.println("Recived getQuote");
			System.out.println(jsonString);
		    answer = quote.quoteCheck();
			System.out.println(answer);
			
			break;

		/*************
		 ** WEATHER **
		 ************/

		case "getClientForecast":
			System.out.println("Recieved getClientForecast");
			System.out.println(jsonString);
			WeatherJson WJ = gson.fromJson(jsonString, WeatherJson.class);
			answer = WH.weatherCheck();
			System.out.println(answer);
			break;
			
		
		default:
			System.out.println("Error");
			break;
		}
		return answer;
		
	}

	//Creates a loooong else if statement, which checks the JSon string which keyword it contains, and returns the following 
	//keyword if
	public String Determine(String ID) {

		if (ID.contains("getEventsDay")) {
			return "getEventsDay";
		}if (ID.contains("getEventsWeek")) {
			return "getEventsWeek";
		}else if (ID.contains("getEventInfo")) {
			return "getEventInfo";
		} else if (ID.contains("saveNote")) {
			return "saveNote";
		} else if (ID.contains("getThemNotesPlaya")) {
			return "getThemNotesPlaya";
		} else if (ID.contains("addThemNotesPlaya")){
			return "addThemNotesPlaya";
		}else if  (ID.contains("deleteCalendar")){
			return "deleteCalendar";
		} else if (ID.contains("getClientForecast")) {
			return "getClientForecast";
		} else if (ID.contains("getAllCalendars")) {
			return "getAllCalendars";
		}else if (ID.contains("addUserToCalendar")) {
			return "addUserToCalendar";
		} else if (ID.contains("exportCourse")) {
			return "exportCourse";
		} else if (ID.contains("getQuote")) {
			return "getQuote";
		} else if (ID.contains("logIn")) {
			return "logIn";
		} else if (ID.contains("logOut")) {
			return "logOut";
		} else if (ID.contains("getCalender")) {
			return "getCalender";
		} else if (ID.contains("createEvent")) {
			return "createEvent";
		} else if (ID.contains("deleteEvent")) {
			return "deleteEvent"; 
		} else if (ID.contains("createCalendar")) {
			return "createCalendar";
		} else if (ID.contains("getEvents")) {
			return "getEvents";
		}

		else
			return "error";
	}
}
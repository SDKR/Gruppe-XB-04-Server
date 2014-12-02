package SwitchLogic;
import model.QOTD.QOTDModel;
import model.note.Note;
import JsonClasses.AuthUserJson;
import JsonClasses.CreateCalendarJson;
import JsonClasses.DeleteCalendarJson;
import JsonClasses.EventsDayJson;
import JsonClasses.EventsWeekJson;
import JsonClasses.userToCalendarJson;
import SwitchLogic.Methods.*;

import com.google.gson.*;

public class GiantSwitch {
	public String GiantSwitchMethod(String jsonString) throws Exception {

		//Events eventsKlasse = new Events(0, 0, 0, jsonString, jsonString, jsonString, jsonString, jsonString);

		Note noteKlasse = new Note();
		//ForecastModel forecastKlasse = new ForecastModel();
		QOTDModel QOTDKlasse = new QOTDModel();
		Gson gson = new GsonBuilder().create();
		CreateCalendar CC = new CreateCalendar();
		DeleteCalendar DC = new DeleteCalendar();
		UserLogin UL = new UserLogin();
		UserToCalendar UTC = new UserToCalendar();
		eventsToUserDay ETUD = new eventsToUserDay();
		eventsToUserWeek ETUW = new eventsToUserWeek();
		String answer = "";	
		//Creates a switch which determines which method should be used. Methods will be applied later on
		switch (Determine(jsonString)) {
		//If the Json String contains one of the keywords below, run the relevant method.

		/************
		 ** COURSES **
		 ************/

		case "addUserToCalendar":
			userToCalendarJson UTCJ = gson.fromJson(jsonString, userToCalendarJson.class);
			System.out.println("Vi er inde i switchen");
			answer = UTC.addUserToCalendar(UTCJ.getEmail(), UTCJ.getCalendarName());
			break;

		/**********
		 ** LOGIN **
		 **********/
		case "logIn":		
			AuthUserJson AU = gson.fromJson(jsonString, AuthUserJson.class);
			System.out.println("Recieved logIn");
			System.out.println("Vi kan køre det!");
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
			System.out.println(DCJ.getCalenderName()+ "Den har lagt det nye ind i klassen");
			answer = DC.deleteCalender(DCJ.getUserName(), DCJ.getCalenderName());
			break;

		case "getEventsDay":
			EventsDayJson EDJ = gson.fromJson(jsonString, EventsDayJson.class);
			answer = ETUD.getEvents(EDJ.getCreatedby());
			break;

		case "getEventsWeek":
			EventsWeekJson EWJ = gson.fromJson(jsonString, EventsWeekJson.class);
			answer = ETUW.getEvents(EWJ.getCreatedby());
			System.out.println("Recieved saveEvent");
			break;

		case "getEventInfo":
			System.out.println("Recieved getEventInfo");
			break;
			
		case "deleteEvent":
			System.out.println("Recieved deleteEvent");
		
		case "saveNote":
			System.out.println("Recieved saveNote");
			break;

		case "getNote":
			System.out.println("Recieved getNote");
			break;
			
		case "deleteNote":
			System.out.println("Recieved deleteNote");
			break;

		/**********
		 ** QUOTE **
		 **********/
		case "getQuote":

		answer = QOTDKlasse.getQuote();
			System.out.println(answer);
			
			break;

		/************
		 ** WEATHER **
		 ************/

		case "getClientForecast":
			System.out.println("Recieved getClientForecast");
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
		} else if (ID.contains("getNote")) {
			return "getNote";
		} else if (ID.contains("deleteNote")){
			return "deleteNote";
		}else if  (ID.contains("deleteCalendar")){
			return "deleteCalendar";
		} else if (ID.contains("getClientForecast")) {
			return "getClientForecast";
		} else if (ID.contains("saveImportedCalender")) {
			return "saveImportedCalender";
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
		}
		else
			return "error";
	}
}
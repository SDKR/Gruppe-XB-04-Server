package JsonClasses;

public class UnSubscribeCalendarJson implements java.io.Serializable
{
	private final long serialVersionUID = 1L;
	private String overallID = "unSubFromCalendar";
	private String email;
	private String calendarName;
	
	//Getters and setters
	public String getOverallID() {
		return overallID;
	}
	public void setOverallID(String overallID) {
		this.overallID = overallID;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCalendarName() {
		return calendarName;
	}
	public void setCalendarName(String calendarName) {
		this.calendarName = calendarName;
	}
}
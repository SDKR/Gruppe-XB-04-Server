package JsonClasses;

public class deleteEventJson implements java.io.Serializable
{	
	private final long serialVersionUID = 1L;
	private String overallID = "deleteEvent";
	private String calendarID;
	private String userName;
	
	public String getCalendarID() {
		return calendarID;
	}
	public void setCalendarID(String calendarID) {
		this.calendarID = calendarID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}

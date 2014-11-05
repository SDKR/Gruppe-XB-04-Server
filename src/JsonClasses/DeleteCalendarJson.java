package JsonClasses;

public class DeleteCalendarJson implements java.io.Serializable 
{
	private  final long serialVersionUID = 1L;
	private String overallID = "deleteCalendar";
	private String calendarName;
	private String userName;
	
	//Getters and setters for everything, bitch
	public String getOverallID() {
		return overallID;
	}
	public void setOverallID(String overallID) {
		this.overallID = overallID;
	}
	public String getCalenderName() {
		return calendarName;
	}
	public void setCalenderName(String calenderName) {
		this.calendarName = calenderName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
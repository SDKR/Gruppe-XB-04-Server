package JsonClasses;

public class EventsWeekJson implements java.io.Serializable
{
	private  final long serialVersionUID = 1L;
	private String overallID = "getEventsWeek";
	private String eventid;
	private String cbsEventId;
	private String type;
	private String location;
	private String locationName;
	private String createdby;
	private String start;
	private String end;
	private String name;
	private String text;
	private String customevent;
	private String CalendarID;
	
	public String getOverallID() {
		return overallID;
	}
	public void setOverallID(String overallID) {
		this.overallID = overallID;
	}
	public String getEventid() {
		return eventid;
	}
	public void setEventid(String eventid) {
		this.eventid = eventid;
	}
	public String getCbsEventId() {
		return cbsEventId;
	}
	public void setCbsEventId(String cbsEventId) {
		this.cbsEventId = cbsEventId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getCustomevent() {
		return customevent;
	}
	public void setCustomevent(String customevent) {
		this.customevent = customevent;
	}
	public String getCalendarID() {
		return CalendarID;
	}
	public void setCalendarID(String calendarID) {
		CalendarID = calendarID;
	}
}
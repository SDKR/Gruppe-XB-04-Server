package JsonClasses;

public class subscribeUserJson implements java.io.Serializable{	
		private  final long serialVersionUID = 1L;
		private String overallID = "addOtherUserToCalendar";
		private String username;
		private String calendarName;
		private String subscriber;
		
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getCalendarName() {
			return calendarName;
		}
		public void setCalendarName(String calendarName) {
			this.calendarName = calendarName;
		}
		public String getSubscriber() {
			return subscriber;
		}
		public void setSubscriber(String subscriber) {
			this.subscriber = subscriber;
		}
}
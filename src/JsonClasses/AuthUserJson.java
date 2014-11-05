package JsonClasses;

public class AuthUserJson implements java.io.Serializable
{
	private  final long serialVersionUID = 2L;
	private String overallID = "logIn";
	private String email;
	private String password;
	private String isActive;
	
	//Getters and setters for everything, bitch
	public String getOverallID() {
		return overallID;
	}
	public void setOverallID(String overallID) {
		this.overallID = overallID;
	}
	public String getAuthUserEmail() {
		return email;
	}
	public void setAuthUserEmail(String email) {
		this.email = email;
	}
	public String getAuthUserPassword() {
		return password;
	}
	public void setAuthUserPassword(String userName) {
		this.password = password;
	}
	public String getAuthUserIsActive() {
		return isActive;
	}
	public void setAuthUserIsActive(String isActive) {
		this.isActive = isActive;
	}
}
import java.sql.SQLException;

import GUILogic.Logic;
public class AdminWorker implements Runnable{
	public void run(){
		Logic GUILogic;
		try {
			GUILogic = new Logic();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GUILogic.startApp();
	}
}

import GUILogic.Logic;
public class AdminWorker implements Runnable{
	public void run(){
		Logic GUILogic = new Logic();
		GUILogic.startApp();
	}
}

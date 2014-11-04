import GUI.GUILogic;
public class AdminWorker implements Runnable{
	@Override
	public void run(){
		GUILogic admin = new GUILogic();
		admin.run();
	}
}

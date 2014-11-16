package GUI;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ContainerPanel extends JFrame {
	public static final String loginScreen = "LoginScreen";
	public static final String mainMenu = "MainMenu";
	public static final String userView = "UserView";
	public static final String calendarView = "CalendarView";
	public static final String eventView = "EventView";
	public static final String noteView = "NoteView";
	
	private static JPanel contentPane;
	CardLayout c;
	
	private AddEventGUI AEG;
	private ViewCalendars VC;
	private Login LI;
	private EventList eList;
	private MainMenu MM;
	private NoteList NL;
	private UserInfo UI;
	
	public ContainerPanel()
	{
		setTitle("Doek Calendar System");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 100, 1366, 768);
		setResizable(false);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		c = (CardLayout) getContentPane().getLayout();
		
		LI = new Login();
		contentPane.add(LI,loginScreen);
		AEG = new AddEventGUI();
		contentPane.add(AEG, eventView);
		
		VC = new ViewCalendars();
		contentPane.add(VC, calendarView);
		
		eList = new EventList();
		contentPane.add(eList, eventView);
		
		MM = new MainMenu();
		contentPane.add(MM, mainMenu);
		
		NL = new NoteList();
		contentPane.add(NL, noteView);
		
		UI = new UserInfo();
		contentPane.add(UI, userView);
	}

	public AddEventGUI getAEG() {
		return AEG;
	}

	public ViewCalendars getVC() {
		return VC;
	}

	public EventList geteList() {
		return eList;
	}

	public MainMenu getMM() {
		return MM;
	}

	public NoteList getNL() {
		return NL;
	}

	public UserInfo getUI() {
		return UI;
	}
	
	public Login getLI() {
		return LI;
	}

	public void show(String card){
		c.show(getContentPane(), card);
	}
}

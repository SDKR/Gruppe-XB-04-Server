package GUI;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ContainerPanel extends JFrame {
	public static final String loginScreen = "LoginScreen";
	public static final String mainMenu = "MainMenu";
	public static final String userView = "UserView";
	public static final String eventView = "EventView";
	public static final String noteView = "NoteView";
	public static final String quoteAndWeather = "QuoteAndWeather";
	
	private static JPanel contentPane;
	CardLayout c;
	
	private Login LI;
	private EventList eList;
	private MainMenu MM;
	private NoteList NL;
	private UserList UL;
	private QuoteAndWeather QAW;
	
	
	public ContainerPanel()
	{
		setTitle("Doek Calendar System");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 100, 1366, 799);
		setResizable(false);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		c = (CardLayout) getContentPane().getLayout();
		
		LI = new Login();
		contentPane.add(LI,loginScreen);
		
		eList = new EventList();
		contentPane.add(eList, eventView);
		
		MM = new MainMenu();
		contentPane.add(MM, mainMenu);
		
		NL = new NoteList();
		contentPane.add(NL, noteView);
		
		UL = new UserList();
		contentPane.add(UL, userView);
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

	public UserList getUI() {
		return UL;
	}
	
	public Login getLI() {
		return LI;
	}

	public void show(String card){
		c.show(getContentPane(), card);
	}
}

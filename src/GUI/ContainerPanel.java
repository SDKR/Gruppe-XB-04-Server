package GUI;

import java.awt.CardLayout;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ContainerPanel extends JFrame {
	public static final String loginScreen = "LoginScreen";
	public static final String mainMenu = "MainMenu";
	public static final String userView = "UserView";
	public static final String eventView = "EventView";
	public static final String noteView = "NoteView";
	public static final String quoteAndWeather = "QuoteAndWeather";
	public static final String createEvent = "createEvent";
	public static final String UserCreation = "UserCreation";
	
	private static JPanel contentPane;
	CardLayout c;
	
	private Login LI;
	private EventList eList;
	private MainMenu MM;
	private NoteList NL;
	private UserList UL;
	private QuoteAndWeather QAW;
	private AddEvent AE;
	private UserCreation UC;
	
	public ContainerPanel() throws SQLException
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
		
		AE = new AddEvent();
		contentPane.add(AE, createEvent);
		
		eList = new EventList();
		contentPane.add(eList, eventView);
		
		MM = new MainMenu();
		contentPane.add(MM, mainMenu);
		
		NL = new NoteList();
		contentPane.add(NL, noteView);
		
		UL = new UserList();
		contentPane.add(UL, userView);
		
		QAW = new QuoteAndWeather();
		contentPane.add(QAW, quoteAndWeather);
		
		UC = new UserCreation();
		contentPane.add(UC, UserCreation);
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
	public QuoteAndWeather getQAW(){
		return QAW;
	}
	public AddEvent getAE() {
		return AE;
	}

	public UserCreation getUC(){
		return UC;
	}
	public void show(String card){
		c.show(getContentPane(), card);
	}
}

package GUI;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import java.awt.Dimension;
import javax.swing.SwingConstants;

public class MainMenu extends JPanel {
	private JLabel label;
	private JLabel lblMainMenu;
	private JButton btnLogOut = new JButton("Log Out");
	private JButton btnUserlist = new JButton("View Users");
	private JButton btnEventlist = new JButton("View Events/Calendars");
	private JButton btnNotelist = new JButton("View Notes");
	private JButton btnCalendarList = new JButton("CalendarList");
	private JButton btnQuoteAndWeather = new JButton ("Quote and Weather");
			
	private JLabel lblCBSlogo;
	
	public MainMenu() {
		setSize(new Dimension(1366, 768));
		setLayout(null);
		
		/*
		 * Labels
		 */
		lblMainMenu = new JLabel("Main Menu");
		lblMainMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMainMenu.setForeground(Color.WHITE);
		lblMainMenu.setFont(new Font("Arial", Font.BOLD, 78));
		lblMainMenu.setBounds(458, 62, 449, 90);
		add(lblMainMenu);
		
		lblCBSlogo = new JLabel("");
		lblCBSlogo.setIcon(new ImageIcon(MainMenu.class.getResource("/Images/CBSLogo3.png")));
		lblCBSlogo.setBounds(36, 695, 223, 67);
		add(lblCBSlogo);
		
		/*
		 *Buttons 
		 */
		btnUserlist.setContentAreaFilled(false);
		btnUserlist.setForeground(Color.WHITE);
		btnUserlist.setFont(new Font("Arial", Font.BOLD, 30));
		btnUserlist.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
		btnUserlist.setBackground(Color.WHITE);
		btnUserlist.setBounds(494, 192, 378, 75);
		btnUserlist.setActionCommand("UserList");
		add(btnUserlist);
		
		btnEventlist.setContentAreaFilled(false);
		btnEventlist.setForeground(Color.WHITE);
		btnEventlist.setFont(new Font("Arial", Font.BOLD, 30));
		btnEventlist.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
		btnEventlist.setBackground(Color.WHITE);
		btnEventlist.setBounds(494, 279, 378, 75);
		btnEventlist.setActionCommand("EventList");
		add(btnEventlist);
		
		btnNotelist.setContentAreaFilled(false);
		btnNotelist.setForeground(Color.WHITE);
		btnNotelist.setFont(new Font("Arial", Font.BOLD, 30));
		btnNotelist.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
		btnNotelist.setBackground(Color.WHITE);
		btnNotelist.setBounds(494, 366, 378, 75);
		btnNotelist.setActionCommand("NoteList");
		add(btnNotelist);
		
		btnLogOut.setContentAreaFilled(false);
		btnLogOut.setForeground(Color.WHITE);
		btnLogOut.setFont(new Font("Arial", Font.BOLD, 30));
		btnLogOut.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
		btnLogOut.setBackground(Color.WHITE);
		btnLogOut.setBounds(494, 644, 378, 88);
		btnLogOut.setActionCommand("LogOut");
		add(btnLogOut);
		
		btnQuoteAndWeather = new JButton("Quote and Weather");
		btnQuoteAndWeather.setActionCommand("quoteAndWeather");
		btnQuoteAndWeather.setForeground(Color.WHITE);
		btnQuoteAndWeather.setFont(new Font("Arial", Font.BOLD, 30));
		btnQuoteAndWeather.setContentAreaFilled(false);
		btnQuoteAndWeather.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
		btnQuoteAndWeather.setBackground(Color.WHITE);
		btnQuoteAndWeather.setBounds(494, 460, 378, 75);
		add(btnQuoteAndWeather);
		
		/*
		 * Background
		 */
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(MainMenu.class.getResource("/Images/MetalBackground.jpg")));
		lblBackground.setBounds(0, 0, 1366, 768);
		lblBackground.isForegroundSet();
		
	
		add(lblBackground);
	}
	//Adding actionlisteners
	public void addActionListenerMainMenu(ActionListener l) {
		btnLogOut.addActionListener(l);
		btnEventlist.addActionListener(l);
		btnNotelist.addActionListener(l);
		btnUserlist.addActionListener(l);
		btnCalendarList.addActionListener(l);
		btnQuoteAndWeather.addActionListener(l);
	}
	//Getters
	public JButton getBtnUserlist() {
		return btnUserlist;
	}
	public JButton getBtnEventlist() {
		return btnEventlist;
	}
	public JButton getBtnNotelist() {
		return btnNotelist;
	}
	public JButton getBtnLogOut() {
		return btnLogOut;
	}
	public JButton getBtnQuoteAndWeather(){
		return btnQuoteAndWeather;
		
	}
}
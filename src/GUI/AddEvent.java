package GUI;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;

public class AddEvent extends JPanel {
	private JTextField textField;
	private JButton mainMenuBtn = new JButton("Back");
	private JButton btnCreateEvent = new JButton("Add Event");
	private JButton btnClearFields = new JButton("Clear Fields");

	/**
	 * Create the panel.
	 */
	
	public AddEvent() {
		setLayout(null);
		
		JLabel lblAddEvent = new JLabel("Add Event");
		lblAddEvent.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddEvent.setForeground(Color.WHITE);
		lblAddEvent.setFont(new Font("Arial", Font.BOLD, 78));
		lblAddEvent.setBounds(313, 27, 618, 90);
		add(lblAddEvent);
		
		textField = new JTextField();
		textField.setBounds(560, 146, 266, 22);
		add(textField);
		textField.setColumns(10);
		
		JTextPane txtpnIfAnyEnter = new JTextPane();
		txtpnIfAnyEnter.setText("If any, enter further information here...");
		txtpnIfAnyEnter.setBounds(439, 402, 391, 118);
		add(txtpnIfAnyEnter);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(560, 189, 266, 22);
		add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(560, 224, 266, 22);
		add(comboBox_1);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Arial", Font.BOLD, 18));
		lblName.setBounds(439, 146, 51, 22);
		add(lblName);
		
		JLabel lblType = new JLabel("Type");
		lblType.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblType.setBounds(439, 189, 43, 22);
		add(lblType);
		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setFont(new Font("Arial", Font.BOLD, 18));
		lblLocation.setBounds(439, 224, 75, 22);
		add(lblLocation);
		
		JLabel lblStartDate = new JLabel("Start Date");
		lblStartDate.setFont(new Font("Arial", Font.BOLD, 18));
		lblStartDate.setBounds(438, 263, 86, 22);
		add(lblStartDate);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(795, 259, 31, 22);
		add(comboBox_2);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(560, 259, 79, 22);
		add(comboBox_3);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setBounds(752, 259, 31, 22);
		add(comboBox_4);
		
		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setBounds(694, 259, 31, 22);
		add(comboBox_5);
		
		JComboBox comboBox_6 = new JComboBox();
		comboBox_6.setBounds(655, 259, 31, 22);
		add(comboBox_6);
		
		JLabel lblEndDate = new JLabel("End Date");
		lblEndDate.setFont(new Font("Arial", Font.BOLD, 18));
		lblEndDate.setBounds(438, 294, 86, 22);
		add(lblEndDate);
		
		JLabel lblCalendar = new JLabel("Calendar");
		lblCalendar.setFont(new Font("Arial", Font.BOLD, 18));
		lblCalendar.setBounds(436, 329, 78, 22);
		add(lblCalendar);
		
		JLabel lblInformation = new JLabel("Information");
		lblInformation.setFont(new Font("Arial", Font.BOLD, 18));
		lblInformation.setBounds(436, 367, 100, 22);
		add(lblInformation);
		
		JComboBox comboBox_7 = new JComboBox();
		comboBox_7.setBounds(795, 294, 31, 22);
		add(comboBox_7);
		
		JComboBox comboBox_8 = new JComboBox();
		comboBox_8.setBounds(560, 294, 79, 22);
		add(comboBox_8);
		
		JComboBox comboBox_9 = new JComboBox();
		comboBox_9.setBounds(752, 294, 31, 22);
		add(comboBox_9);
		
		JComboBox comboBox_10 = new JComboBox();
		comboBox_10.setBounds(694, 294, 31, 22);
		add(comboBox_10);
		
		JComboBox comboBox_11 = new JComboBox();
		comboBox_11.setBounds(655, 294, 31, 22);
		add(comboBox_11);
		
		JComboBox comboBox_12 = new JComboBox();
		comboBox_12.setBounds(560, 330, 266, 22);
		add(comboBox_12);
		
		
		mainMenuBtn.setForeground(Color.WHITE);
		mainMenuBtn.setFont(new Font("Arial", Font.BOLD, 30));
		mainMenuBtn.setContentAreaFilled(false);
		mainMenuBtn.setBorder(new CompoundBorder(new BevelBorder(
						BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0,
								0), new Color(255, 255, 255), new Color(0, 0, 0)),
						new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255),
								new Color(0, 0, 0), new Color(255, 255, 255),
								new Color(0, 0, 0))));
		mainMenuBtn.setBounds(543, 647, 163, 43);
		add(mainMenuBtn);
		
		
		btnCreateEvent.setForeground(Color.WHITE);
		btnCreateEvent.setFont(new Font("Arial", Font.BOLD, 30));
		btnCreateEvent.setContentAreaFilled(false);
		btnCreateEvent.setBorder(new CompoundBorder(new BevelBorder(
								BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0,
										0), new Color(255, 255, 255), new Color(0, 0, 0)),
								new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255),
										new Color(0, 0, 0), new Color(255, 255, 255),
										new Color(0, 0, 0))));
		btnCreateEvent.setBounds(436, 576, 179, 43);
		add(btnCreateEvent);
		
		
		btnClearFields.setForeground(Color.WHITE);
		btnClearFields.setFont(new Font("Arial", Font.BOLD, 30));
		btnClearFields.setContentAreaFilled(false);
		btnClearFields.setBorder(new CompoundBorder(new BevelBorder(
								BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0,
										0), new Color(255, 255, 255), new Color(0, 0, 0)),
								new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255),
										new Color(0, 0, 0), new Color(255, 255, 255),
										new Color(0, 0, 0))));
		btnClearFields.setBounds(650, 576, 179, 43);
		add(btnClearFields);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(UserList.class
				.getResource("/Images/CBSLogo3.png")));
		lblNewLabel.setBounds(12, 697, 250, 59);
		add(lblNewLabel);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(EventList.class
				.getResource("/Images/MetalBackground.jpg")));
		label.setBounds(0, 0, 1366, 768);
		add(label);
	}
	public void backListener(ActionListener l) {
		mainMenuBtn.addActionListener(l);
	}
	public void createEventListener (ActionListener l)
	{
		btnCreateEvent.addActionListener(l);
	}
	public void clearFieldListener (ActionListener l)
	{
		btnClearFields.addActionListener(l);
	}
}

package GUI;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class CalendarList extends JPanel {
	private JTable table;
	private JTextField nameField = new JTextField();
	private JComboBox<Object> PPCombo = new JComboBox<Object>();
	private JComboBox<Object> activeCombo = new JComboBox<Object>();
	private JButton btnActivateCalendar = new JButton("Activate Calendar");
	private JButton btnAddCalendar = new JButton("Add Calendar");
	private JButton btnDeleteCalendar = new JButton("Delete Calendar");
	private JButton mainMenuBtn = new JButton("Main Menu");

	/**
	 * Create the panel.
	 */
	public CalendarList() {
		setLayout(null);
		setSize(new Dimension(1366, 768));
		
		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setViewportBorder(new CompoundBorder(new BevelBorder(
						BevelBorder.LOWERED, new Color(0, 0, 205), new Color(255, 255,
								255), new Color(0, 0, 205), new Color(255, 255, 255)),
						null));
		scrollPane.setBorder(new CompoundBorder(new BevelBorder(
						BevelBorder.LOWERED, new Color(0, 0, 205), new Color(255, 255,
								255), new Color(0, 0, 205), new Color(255, 255, 255)),
						new MatteBorder(1, 1, 1, 1, new Color(255, 255, 255))));
		scrollPane.setBounds(372, 219, 591, 361);
		add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"CalendarID", "Name", "Active", "CreatedBy", "Public/Private"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(71);
		scrollPane.setViewportView(table);
		/*
		 * Buttons
		 */
		mainMenuBtn.setForeground(Color.WHITE);
		mainMenuBtn.setFont(new Font("Arial", Font.BOLD, 30));
		mainMenuBtn.setContentAreaFilled(false);
		mainMenuBtn.setBorder(new CompoundBorder(new BevelBorder(
						BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0,
								0), new Color(255, 255, 255), new Color(0, 0, 0)),
						new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255),
								new Color(0, 0, 0), new Color(255, 255, 255),
								new Color(0, 0, 0))));
		mainMenuBtn.setBounds(585, 640, 163, 43);
		add(mainMenuBtn);
		
		btnDeleteCalendar.setForeground(Color.WHITE);
		btnDeleteCalendar.setFont(new Font("Arial", Font.BOLD, 30));
		btnDeleteCalendar.setContentAreaFilled(false);
		btnDeleteCalendar.setBorder(new CompoundBorder(new BevelBorder(
								BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0,
										0), new Color(255, 255, 255), new Color(0, 0, 0)),
								new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255),
										new Color(0, 0, 0), new Color(255, 255, 255),
										new Color(0, 0, 0))));
		btnDeleteCalendar.setBounds(1035, 539, 270, 43);
		add(btnDeleteCalendar);
		
		btnAddCalendar.setForeground(Color.WHITE);
		btnAddCalendar.setFont(new Font("Arial", Font.BOLD, 30));
		btnAddCalendar.setContentAreaFilled(false);
		btnAddCalendar.setBorder(new CompoundBorder(new BevelBorder(
								BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0,
										0), new Color(255, 255, 255), new Color(0, 0, 0)),
								new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255),
										new Color(0, 0, 0), new Color(255, 255, 255),
										new Color(0, 0, 0))));
		btnAddCalendar.setBounds(1035, 346, 270, 43);
		add(btnAddCalendar);
		
		btnActivateCalendar.setForeground(Color.WHITE);
		btnActivateCalendar.setFont(new Font("Arial", Font.BOLD, 30));
		btnActivateCalendar.setContentAreaFilled(false);
		btnActivateCalendar.setBorder(new CompoundBorder(new BevelBorder(
										BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0,
												0), new Color(255, 255, 255), new Color(0, 0, 0)),
										new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255),
												new Color(0, 0, 0), new Color(255, 255, 255),
												new Color(0, 0, 0))));
		btnActivateCalendar.setBounds(1035, 471, 270, 43);
		add(btnActivateCalendar);
		/*
		 * Textfield
		 */
		nameField = new JTextField();
		nameField.setText("Enter Name here...");
		nameField.setColumns(10);
		nameField.setBounds(1169, 220, 134, 22);
		add(nameField);
		
		/*
		 * ComboBox
		 */
		PPCombo.setModel(new DefaultComboBoxModel(new String[] {"Public", "Private"}));
		PPCombo.setBounds(1169, 303, 134, 22);
		add(PPCombo);
		activeCombo.setModel(new DefaultComboBoxModel(new String[] {"Active", "Inactive"}));
		activeCombo.setBounds(1169, 260, 134, 22);
		add(activeCombo);
		
		/*
		 * Labels
		 */
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Arial", Font.BOLD, 18));
		lblName.setBounds(1033, 219, 51, 22);
		add(lblName);
		
		JLabel lblIsActive = new JLabel("Is Active");
		lblIsActive.setFont(new Font("Arial", Font.BOLD, 18));
		lblIsActive.setBounds(1033, 260, 76, 22);
		add(lblIsActive);
		
		JLabel lblPublicPrivate = new JLabel("Public / Private");
		lblPublicPrivate.setFont(new Font("Arial", Font.BOLD, 18));
		lblPublicPrivate.setBounds(1033, 302, 131, 22);
		add(lblPublicPrivate);
		
		JLabel lblCalendarlist = new JLabel("Calendarlist");
		lblCalendarlist.setForeground(Color.WHITE);
		lblCalendarlist.setFont(new Font("Arial", Font.BOLD, 78));
		lblCalendarlist.setBounds(443, 68, 446, 90);
		add(lblCalendarlist);
		/*
		 * Images
		 */
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
	/*
	 * Actionlisteners
	 */
	public void addCalendarListener (ActionListener l)
	{
		btnAddCalendar.addActionListener(l);
	}
	public void deleteCalendarListener (ActionListener l )
	{
		btnDeleteCalendar.addActionListener(l);
	}
	public void reActivateListener (ActionListener l)
	{
		btnActivateCalendar.addActionListener(l);
	}
	
	/*
	 * Getters and setters
	 */
	public JTextField getNameField() {
		return nameField;
	}

	public JComboBox<Object> getPPCombo() {
		return PPCombo;
	}

	public JComboBox<Object> getActiveCombo() {
		return activeCombo;
	}

	public JButton getBtnActivateCalendar() {
		return btnActivateCalendar;
	}

	public JButton getBtnAddCalendar() {
		return btnAddCalendar;
	}

	public JButton getBtnDeleteCalendar() {
		return btnDeleteCalendar;
	}

	public JButton getMainMenuBtn() {
		return mainMenuBtn;
	}
}
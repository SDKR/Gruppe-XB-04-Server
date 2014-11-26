package GUI;

import javax.swing.JPanel;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import java.awt.Font;

import javax.swing.JButton;

import java.awt.Color;

import javax.swing.JScrollPane;

import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import java.awt.event.ActionListener;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class EventList extends JPanel {

	/**
	 * Create the panel.
	 */

	private JButton btnAdd = new JButton("Add");
	private JButton btnDelete;
	private JButton btnLogout;
	private JButton btnMainMenu = new JButton("Main Menu");
	private JComboBox calendarComboBox;
	private JLabel lblChooseCalendar;

	public EventList() {
		setSize(new Dimension(1336, 768));
		setLayout(null);

		JLabel lblEvents = new JLabel("Eventlist");
		lblEvents.setForeground(Color.WHITE);
		lblEvents.setFont(new Font("Arial", Font.BOLD, 78));
		lblEvents.setBounds(521, 90, 325, 91);
		add(lblEvents);

		lblChooseCalendar = new JLabel("Choose Calendar:");
		lblChooseCalendar.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseCalendar.setForeground(Color.WHITE);
		lblChooseCalendar.setFont(new Font("Arial", Font.BOLD, 16));
		lblChooseCalendar.setBounds(990, 331, 174, 19);
		add(lblChooseCalendar);

		calendarComboBox = new JComboBox();
		calendarComboBox.setBounds(990, 336, 174, 82);
		add(calendarComboBox);
		calendarComboBox.addItem("0: Finansiering");
		calendarComboBox.addItem("1: Makro");
		calendarComboBox.addItem("2: DIS");
		calendarComboBox.addItem("3: Ledelse IS");
		calendarComboBox.addItem("0: Finansiering-Ex.");
		calendarComboBox.addItem("1: Makro-Ex");
		calendarComboBox.addItem("2: DIS-Ex");
		calendarComboBox.addItem("3: Ledelse IS-Ex");

		JLabel lblUpcomingEvent = new JLabel("Upcomming Events:");
		lblUpcomingEvent.setFont(new Font("Arial", Font.BOLD, 27));
		lblUpcomingEvent.setForeground(Color.WHITE);
		lblUpcomingEvent.setBounds(51, 140, 309, 33);
		add(lblUpcomingEvent);

		// Laver tabellen inde i Eventlisten.
		String[] columnNames = { "Event", "Date", "Note", "" };

		Object[][] data = {

				{ "D�K Julefrokost", "11.11.2022", "Game on!",
						new Boolean(false) },
				{ "D�K Julefrokost", "11.11.2022", "Game on!",
						new Boolean(true) },
				{ "D�K Julefrokost", "11.11.2022", "Game on!",
						new Boolean(false) },
				{ "D�K Julefrokost", "11.11.2022", "Game on!",
						new Boolean(true) },
				{ "D�K Julefrokost", "11.11.2022", "Game on!",
						new Boolean(false) } };

		final JTable table = new JTable(data, columnNames);
		table.setModel(new DefaultTableModel(
				new Object[][] {
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null }, }, new String[] { "EventId",
						"Type", "Location", "Created By", "Start",
						"End", "Name", "Text", "Custom Event", "Calendar ID" }));
		table.setSurrendersFocusOnKeystroke(true);
		table.setPreferredScrollableViewportSize(new Dimension(500, 100));
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);

		// Create the scroll pane and add the table to it.
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(new CompoundBorder(new BevelBorder(
				BevelBorder.LOWERED, new Color(0, 0, 205), new Color(255, 255,
						255), new Color(0, 0, 205), new Color(255, 255, 255)),
				new MatteBorder(1, 1, 1, 1, new Color(255, 255, 255))));
		scrollPane.setViewportBorder(new CompoundBorder(new BevelBorder(
				BevelBorder.LOWERED, new Color(0, 0, 205), new Color(255, 255,
						255), new Color(0, 0, 205), new Color(255, 255, 255)),
				null));
		scrollPane.setBounds(387, 194, 591, 361);

		// Add the scroll pane to this panel.
		add(scrollPane);

		btnMainMenu.setForeground(Color.WHITE);
		btnMainMenu.setFont(new Font("Arial", Font.BOLD, 30));
		btnMainMenu.setContentAreaFilled(false);
		btnMainMenu.setBorder(new CompoundBorder(new BevelBorder(
				BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0,
						0), new Color(255, 255, 255), new Color(0, 0, 0)),
				new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255),
						new Color(0, 0, 0), new Color(255, 255, 255),
						new Color(0, 0, 0))));
		btnMainMenu.setBounds(601, 612, 163, 43);
		add(btnMainMenu);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setOpaque(true);
		btnDelete.setForeground(new Color(0, 0, 205));
		btnDelete.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 255)));
		btnDelete.setBounds(988, 194, 118, 29);
		add(btnDelete);

		
		btnAdd.setOpaque(true);
		btnAdd.setForeground(new Color(0, 0, 205));
		btnAdd.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 255)));
		btnAdd.setBounds(988, 234, 118, 29);
		add(btnAdd);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(UserList.class
				.getResource("/Images/CBSLogo3.png")));
		lblNewLabel.setBounds(36, 695, 223, 67);
		add(lblNewLabel);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(EventList.class
				.getResource("/Images/MetalBackground.jpg")));
		label.setBounds(0, 0, 1366, 768);
		add(label);

	}
	


	public void deleteEvent(ActionListener l) {
		btnDelete.addActionListener(l);
	}

	public void goToAddEvent(ActionListener l)
	{
		btnAdd.addActionListener(l);
	}
	public void goToMainMenu(ActionListener l) {
		btnMainMenu.addActionListener(l);
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public JButton getBtnLogout() {
		return btnLogout;
	}

	public JButton getBtnMainMenu() {
		return btnMainMenu;
	}
}

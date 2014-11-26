package GUI;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

public class AddCalendar extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public AddCalendar() {
		setLayout(null);
		
		JLabel label_1 = new JLabel("Calendarlist");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Arial", Font.BOLD, 78));
		label_1.setBounds(461, 68, 446, 90);
		add(label_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(UserList.class
				.getResource("/Images/CBSLogo3.png")));
		lblNewLabel.setBounds(12, 693, 250, 59);
		add(lblNewLabel);
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(EventList.class
				.getResource("/Images/MetalBackground.jpg")));
		label.setBounds(0, 0, 1366, 768);
		add(label);
		
		textField = new JTextField();
		textField.setBounds(571, 235, 116, 22);
		add(textField);
		textField.setColumns(10);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("New check box");
		chckbxNewCheckBox.setBounds(596, 295, 113, 25);
		add(chckbxNewCheckBox);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(745, 397, 31, 22);
		add(comboBox);
	}
}

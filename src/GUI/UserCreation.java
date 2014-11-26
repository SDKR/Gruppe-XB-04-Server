package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JCheckBox;

public class UserCreation extends JPanel {
	private JTextField EmailText = new JTextField();
	private JTextField Pass = new JTextField();
	private JTextField RepeatPass = new JTextField();
	private final JTable table = new JTable();
	private JLabel lblCreateUser;
	private JLabel lblUsername_1;
	private JLabel lblPassword;
	private JLabel lblUsername;
	private JButton btnMainMenu;
	private JButton btnCreate;
	private JCheckBox chckbxAdministrator;
	private JCheckBox chckbxActive;
	

	public UserCreation() {
		setLayout(null);
		setSize(new Dimension(1366, 768));
    	setLayout(null);
		
		
		EmailText.setBounds(749, 151, 134, 28);
		add(EmailText);
		EmailText.setColumns(10);
		
		Pass = new JTextField();
		Pass.setBounds(749, 236, 134, 28);
		add(Pass);
		Pass.setColumns(10);
		
		lblUsername_1 = new JLabel("E-mail");
		lblUsername_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername_1.setForeground(Color.WHITE);
		lblUsername_1.setFont(new Font("Arial", Font.BOLD, 25));
		lblUsername_1.setBounds(489, 147, 75, 30);
		add(lblUsername_1);
		
		lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Arial", Font.BOLD, 25));
		lblPassword.setBounds(489, 232, 118, 30);
		add(lblPassword);
		
		lblUsername = new JLabel("Repeat Password");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setFont(new Font("Arial", Font.BOLD, 25));
		lblUsername.setBounds(489, 316, 208, 30);
		add(lblUsername);
		
		lblCreateUser = new JLabel("Create User");
		lblCreateUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateUser.setForeground(Color.WHITE);
		lblCreateUser.setFont(new Font("Arial", Font.BOLD, 50));
		lblCreateUser.setBackground(Color.WHITE);
		lblCreateUser.setBounds(6, 33, 1354, 59);
		add(lblCreateUser);
		
		chckbxActive = new JCheckBox("Active");
		chckbxActive.setSelected(true);
		chckbxActive.setForeground(Color.WHITE);
		chckbxActive.setFont(new Font("Arial", Font.BOLD, 13));
		chckbxActive.setBounds(619, 421, 128, 23);
		add(chckbxActive);
		
		RepeatPass = new JTextField();
		RepeatPass.setBounds(749, 320, 134, 28);
		add(RepeatPass);
		RepeatPass.setColumns(10);
		 
		 btnMainMenu = new JButton("Main Menu");
		 btnMainMenu.setForeground(Color.WHITE);
		 btnMainMenu.setFont(new Font("Arial", Font.BOLD, 30));
		 btnMainMenu.setContentAreaFilled(false);
		 btnMainMenu.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
		 btnMainMenu.setBounds(586, 682, 194, 44);
		 add(btnMainMenu);
		 
		 btnCreate = new JButton("Create");
		 btnCreate.setForeground(Color.WHITE);
		 btnCreate.setFont(new Font("Arial", Font.BOLD, 30));
		 btnCreate.setContentAreaFilled(false);
		 btnCreate.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
		 btnCreate.setBounds(586, 468, 194, 44);
		 add(btnCreate);
		 
		 chckbxAdministrator = new JCheckBox("Administrator");
		 chckbxAdministrator.setForeground(Color.WHITE);
		 chckbxAdministrator.setFont(new Font("Arial", Font.BOLD, 13));
		 chckbxAdministrator.setBounds(619, 373, 128, 23);
		 add(chckbxAdministrator);

		
		 JLabel lblBackground = new JLabel("Background");
	        lblBackground.setIcon(new ImageIcon(UserList.class.getResource("/Images/MetalBackground.jpg")));
	        lblBackground.setBackground(new Color(245, 245, 245));
	        lblBackground.setForeground(new Color(245, 255, 250));
	        lblBackground.setOpaque(true);
	        lblBackground.setBounds(0, 0, 1444, 768);
	        add(lblBackground);
	    }
	   public void goToMainMenu(ActionListener l) {
			btnMainMenu.addActionListener(l);
		}
	   

		public JButton getBtnMainMenu() {
			return btnMainMenu;
		}
		public JTextField getEmailText() {
			return EmailText;
		}
		public JTextField getPass() {
			return Pass;
		}
		public JTextField getRepeatPass() {
			return RepeatPass;
		}
		public JCheckBox getChckbxAdministrator() {
			return chckbxAdministrator;
		}
		public JCheckBox getChckbxActive() {
			return chckbxActive;
		}
		
}

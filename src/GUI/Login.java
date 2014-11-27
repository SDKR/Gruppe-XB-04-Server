package GUI;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;

import java.awt.Color;

import java.awt.Cursor;

import javax.swing.SwingConstants;

import java.awt.Dimension;

import javax.swing.JPasswordField;

public class Login extends JPanel {
	private final JLabel lblWelcome = new JLabel("Welcome");
	private final JLabel lblUsername = new JLabel("Username");
	private final JLabel lblPassword = new JLabel("Password");
	private JButton btnLogIn = new JButton("Log In");
	private final JTextField textFieldUsername = new JTextField();
	private final JLabel lblCBSlogo = new JLabel("");
	private final JLabel lblBackground = new JLabel("");
	private final JPasswordField textFieldPassword = new JPasswordField();
	
	/**
	 * Create the panel.
	 */
	public Login() {
		setLayout(null);
		
		//Login button
		btnLogIn.setContentAreaFilled(false);
		btnLogIn.setFont(new Font("Arial", Font.BOLD, 30));
		btnLogIn.setForeground(new Color(255, 255, 255));
		btnLogIn.setBackground(Color.WHITE);
		btnLogIn.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
		
		lblWelcome.setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
		lblWelcome.setForeground(new Color(255, 255, 255));
		lblWelcome.setFont(new Font("Arial", Font.BOLD, 78));
		lblWelcome.setBounds(510, 90, 346, 107);
		
		add(lblWelcome);
		lblUsername.setForeground(new Color(255, 255, 255));
		lblUsername.setFont(new Font("Arial", Font.BOLD, 26));
		lblUsername.setBounds(499, 335, 125, 30);
		
		add(lblUsername);
		lblPassword.setForeground(new Color(255, 255, 255));
		lblPassword.setFont(new Font("Arial", Font.BOLD, 26));
		lblPassword.setBounds(499, 378, 123, 30);
		add(lblPassword);
		
		btnLogIn.setBounds(572, 449, 222, 51);
		
		add(btnLogIn);
		
		lblCBSlogo.setIcon(new ImageIcon(Login.class.getResource("/Images/CBSLogo3.png")));
		lblCBSlogo.setBounds(36, 695, 223, 67);
		
		add(lblCBSlogo);
		setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		
		/*
		 * Login Fields
		 */
		textFieldPassword.setOpaque(false);
		textFieldPassword.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldPassword.setColumns(10);
		textFieldPassword.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255)));
		textFieldPassword.setBounds(705, 372, 164, 37);
		
		textFieldUsername.setOpaque(false);
		textFieldUsername.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldUsername.setForeground(Color.BLACK);
		textFieldUsername.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		textFieldUsername.setColumns(10);
		textFieldUsername.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255)));
		textFieldUsername.setBounds(705, 334, 164, 37);
		add(textFieldUsername);
		
		add(textFieldPassword);
		lblBackground.setHorizontalAlignment(SwingConstants.CENTER);
		lblBackground.setIcon(new ImageIcon(Login.class.getResource("/Images/MetalBackground.jpg")));
		lblBackground.setSize(new Dimension(1366, 768));
		lblBackground.setBounds(0, 0, 1366, 768);
		
		add(lblBackground);

	}
	public void addActionListenerWelcomeScreen(ActionListener l){
		btnLogIn.addActionListener(l);
	}

	public JTextField getTextField() {
		return textFieldUsername;
	}


	public JButton getBtnLogIn() {
		return btnLogIn;
	}

	public JTextField getTextFieldUsername() {
		return textFieldUsername;
	}

	public JPasswordField getTextFieldPassword() {
		return textFieldPassword;
	}


	}
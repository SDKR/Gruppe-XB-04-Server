package GUI;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
 
public class QuoteAndWeather extends JPanel {
	private JButton btnMainMenu = new JButton("Main Menu");
	private JLabel lblQuoteAndWeather;
	private final JLabel lblNewLabel = new JLabel("Weather");
	private final JLabel lblQuote = new JLabel("Quote");
	private JTextField textField;
	private JTextField textField_1;
	
    public QuoteAndWeather() {
    	/*
    	 * Panel layout
    	 */
    	setSize(new Dimension(1366, 768));
    	setLayout(null);
        
        btnMainMenu.setForeground(Color.WHITE);
        btnMainMenu.setFont(new Font("Arial", Font.BOLD, 30));
        btnMainMenu.setContentAreaFilled(false);
        btnMainMenu.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
        btnMainMenu.setBounds(622, 646, 194, 44);
        add(btnMainMenu);

        /*
         * JTable and Scrollpanel
         */
        String[] columnNames = {"UserID", "IsAdmin", "Email", "Active", "Created datetime", "Password"};
        Object[][] data = {};
        
        lblQuoteAndWeather = new JLabel("Quote and Weather of the Day");
        lblQuoteAndWeather.setForeground(Color.WHITE);
        lblQuoteAndWeather.setHorizontalAlignment(SwingConstants.CENTER);
        lblQuoteAndWeather.setFont(new Font("Arial", Font.BOLD, 50));
        lblQuoteAndWeather.setBackground(Color.WHITE);
        lblQuoteAndWeather.setBounds(6, 102, 1354, 59);
        add(lblQuoteAndWeather);
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 25));
        lblNewLabel.setBounds(330, 258, 112, 69);
        
        add(lblNewLabel);
        lblQuote.setForeground(Color.WHITE);
        lblQuote.setHorizontalAlignment(SwingConstants.CENTER);
        lblQuote.setFont(new Font("Arial", Font.BOLD, 25));
        lblQuote.setBounds(931, 258, 112, 69);
        
        add(lblQuote);
        
        textField = new JTextField();
        textField.setBounds(305, 328, 162, 113);
        add(textField);
        textField.setColumns(10);
        
        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(906, 328, 162, 113);
        add(textField_1);
    
        JLabel lblBackground = new JLabel("Background");
        lblBackground.setIcon(new ImageIcon(UserList.class.getResource("/Images/MetalBackground.jpg")));
        lblBackground.setBackground(new Color(245, 245, 245));
        lblBackground.setForeground(new Color(245, 255, 250));
        lblBackground.setOpaque(true);
        lblBackground.setBounds(0, 0, 1376, 768);
        add(lblBackground);
    }
    
    public void goToMainMenu(ActionListener l) {
		btnMainMenu.addActionListener(l);
	}

	public JButton getBtnMainMenu() {
		return btnMainMenu;
	}
}
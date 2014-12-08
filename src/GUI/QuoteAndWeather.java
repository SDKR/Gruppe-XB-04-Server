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
import java.sql.SQLException;
import javax.swing.border.MatteBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import model.Forecast.ForecastModel;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
 
public class QuoteAndWeather extends JPanel {
	private JButton btnMainMenu = new JButton("Main Menu");
	private JLabel lblQuoteAndWeather;
	private JLabel weatherLabel = new JLabel("Weather");
	private JLabel quoteLabel = new JLabel("Quote");
	private JTextArea weatherTextArea = new JTextArea();
	private JTextArea qTextArea = new JTextArea();
	
    private JLabel lblWeather = new JLabel("Weather");
    


	public QuoteAndWeather() throws SQLException {
    	
    	ForecastModel FM = new ForecastModel();
//    	System.out.println(FM.getForecast());
    	/*
    	 * Panel layout
    	 */
    	setSize(new Dimension(1366, 768));
        setLayout(null);
        btnMainMenu.setBounds(586, 646, 194, 44);
        
        btnMainMenu.setForeground(Color.WHITE);
        btnMainMenu.setFont(new Font("Arial", Font.BOLD, 30));
        btnMainMenu.setContentAreaFilled(false);
        btnMainMenu.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
        add(btnMainMenu);

        /*
         * JTable and Scrollpanel
         */
        String[] columnNames = {"UserID", "IsAdmin", "Email", "Active", "Created datetime", "Password"};
        Object[][] data = {};
        
        lblQuoteAndWeather = new JLabel("Quote and Weather of the Day");
        lblQuoteAndWeather.setBounds(6, 62, 1354, 59);
        lblQuoteAndWeather.setForeground(Color.WHITE);
        lblQuoteAndWeather.setHorizontalAlignment(SwingConstants.CENTER);
        lblQuoteAndWeather.setFont(new Font("Arial", Font.BOLD, 50));
        lblQuoteAndWeather.setBackground(Color.WHITE);
        add(lblQuoteAndWeather);
        weatherLabel.setBounds(627, 384, 112, 69);
        weatherLabel.setForeground(Color.WHITE);
        weatherLabel.setHorizontalAlignment(SwingConstants.CENTER);
        weatherLabel.setFont(new Font("Arial", Font.BOLD, 25));
        
        add(weatherLabel);
        quoteLabel.setBounds(627, 167, 112, 69);
        quoteLabel.setForeground(Color.WHITE);
        quoteLabel.setHorizontalAlignment(SwingConstants.CENTER);
        quoteLabel.setFont(new Font("Arial", Font.BOLD, 25));
        
        add(quoteLabel);
        qTextArea.setFont(new Font("Arial", Font.BOLD, 13));
        qTextArea.setBounds(73, 248, 1219, 139);
        add(qTextArea);
        
        JScrollBar scrollBar = new JScrollBar();
        setVisible(true);
        add(qTextArea);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(71, 466, 1221, 139);
        add(scrollPane);
        scrollPane.setViewportView(weatherTextArea);
        
        JLabel lblWeather = new JLabel("Weather");
        lblWeather.setBounds(538, 134, 483, 16);
        add(lblWeather);
        
        JLabel lblBackground = new JLabel("Background");
        lblBackground.setBounds(0, 0, 1376, 768);
        lblBackground.setIcon(new ImageIcon(UserList.class.getResource("/Images/MetalBackground.jpg")));
        lblBackground.setBackground(new Color(245, 245, 245));
        lblBackground.setForeground(new Color(245, 255, 250));
        lblBackground.setOpaque(true);
        add(lblBackground);
    }
    
    public void goToMainMenu(ActionListener l) {
		btnMainMenu.addActionListener(l);
	}

	public JButton getBtnMainMenu() {
		return btnMainMenu;
	}

	public JTextArea getWeatherTextArea() {
		return weatherTextArea;
	}

	public JTextArea getqTextArea() {
		return qTextArea;
	}

	public void setqTextArea(JTextArea qTextArea) {
		this.qTextArea = qTextArea;
	}
    public JLabel getWeatherLabel() {
		return weatherLabel;
	}
}
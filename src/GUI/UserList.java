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
import javax.swing.table.DefaultTableModel;
 
public class UserList extends JPanel {
	private JButton btnAdd = new JButton("Add");
	private JButton btnDelete = new JButton("Delete");
	private JButton btnMainMenu = new JButton("Main Menu");
	private JTable table = new JTable();
	private String[] headerNames = new String[]{
			"UserID", "Email", "Active", "Created datetime", "Password", "IsAdmin"
	};

    public UserList (){
    	
    	/*
    	 * Panel layout
    	 */
    	setSize(new Dimension(1366, 768));
    	setLayout(null);
    	
    	/*
    	 * Buttons
    	 */
    
    	
    	btnAdd.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 255)));
        btnAdd.setForeground(new Color(0, 0, 205));
        btnAdd.setOpaque(true);
        btnAdd.setBounds(1069, 544, 118, 29);
        add(btnAdd);
        
        btnMainMenu.setForeground(Color.WHITE);
        btnMainMenu.setFont(new Font("Arial", Font.BOLD, 30));
        btnMainMenu.setContentAreaFilled(false);
        btnMainMenu.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
        btnMainMenu.setBounds(586, 646, 194, 44);
        add(btnMainMenu);
        
        btnDelete.setOpaque(true);
        btnDelete.setForeground(new Color(0, 0, 205));
        btnDelete.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 255)));
        btnDelete.setBounds(1069, 502, 118, 29);
        add(btnDelete);

        /*
         * JTable and Scrollpanel
         */
        
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 205), new Color(255, 255, 255), new Color(0, 0, 205), new Color(255, 255, 255)), new MatteBorder(1, 1, 1, 1, new Color(255, 255, 255))));
        scrollPane.setViewportBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 205), new Color(255, 255, 255), new Color(0, 0, 205), new Color(255, 255, 255)), null));
        scrollPane.setBounds(333, 208, 700, 400);
        add(scrollPane);
        scrollPane.setViewportView(table);
        String[] headerNames = new String[]{
        		"UserID", "Email", "Active", "Created datetime", "Password", "IsAdmin"
        };
        table.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        	},
        	headerNames
        		) {
        			boolean[] columnEditables = new boolean[] {
        				false, false, false, false, false
        			};
        			public boolean isCellEditable(int row, int column) {
        				return columnEditables[column];
        			}
        		});
        table.getColumnModel().getColumn(0).setPreferredWidth(57);
        table.getColumnModel().getColumn(0).setResizable(false);
        table.getColumnModel().getColumn(1).setPreferredWidth(184);
        table.getColumnModel().getColumn(1).setResizable(false);
        table.getColumnModel().getColumn(2).setPreferredWidth(48);
        table.getColumnModel().getColumn(2).setResizable(false);
        table.getColumnModel().getColumn(3).setPreferredWidth(125);
        table.getColumnModel().getColumn(3).setResizable(false);
        table.getColumnModel().getColumn(4).setPreferredWidth(66);
        table.getColumnModel().getColumn(5).setResizable(false);
        table.getColumnModel().getColumn(5).setPreferredWidth(59);
        table.setFillsViewportHeight(true);
        table.setRowSelectionAllowed(true);
        
        /*
         * Jlables
         */
        JLabel lblUserlist = new JLabel("Userlist");
        lblUserlist.setForeground(Color.WHITE);
        lblUserlist.setFont(new Font("Arial", Font.BOLD, 78));
        lblUserlist.setBounds(534, 118, 298, 90);
        add(lblUserlist);     
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(UserList.class.getResource("/Images/CBSLogo3.png")));
        lblNewLabel.setBounds(36, 695, 223, 67);
        add(lblNewLabel);
    
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
    
    public void goToAddUser (ActionListener l)
    {
    	btnAdd.addActionListener(l);
    }
    
	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public JButton getBtnMainMenu() {
		return btnMainMenu;
	}
	
}
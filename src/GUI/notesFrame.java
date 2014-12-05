package GUI;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class notesFrame extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void nogetAndetEndMain() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					notesFrame frame = new notesFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public notesFrame() {
		setBounds(100, 100, 450, 300);

	}

}

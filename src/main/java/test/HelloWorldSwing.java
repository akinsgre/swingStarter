package test;

/*
 * HelloWorldSwing.java requires no other files. 
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HelloWorldSwing {
	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("HelloWorldSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add the ubiquitous "Hello World" label.
		BorderLayout layout = new BorderLayout();
		JPanel mainPanel = new JPanel(layout);

		JMenu menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		JMenuItem menuItem = new JMenuItem("New", KeyEvent.VK_N);
		menu.add(menuItem);

		JMenuBar menuBar = new JMenuBar();
		menuBar.add(menu);
		frame.setJMenuBar(menuBar);

		JLabel label = new JLabel("Hello World");
		label.setName("Label");
		label.setForeground(Color.BLACK);
		label.setPreferredSize(new Dimension(50, 100));

		JTextField field = new JTextField();
		field.setText("Done");

		mainPanel.add(label, BorderLayout.PAGE_START);
		mainPanel.add(field, BorderLayout.PAGE_END);

		JPanel leftPanel = new JPanel(new FlowLayout());
		leftPanel.setPreferredSize(new Dimension(100, 200));
		leftPanel.setBackground(Color.BLUE);

		JPanel centerPanel = new JPanel(new FlowLayout());
		centerPanel.setPreferredSize(new Dimension(300, 200));
		centerPanel.setBackground(Color.RED);

		JPanel rightPanel = new JPanel(new FlowLayout());
		rightPanel.setPreferredSize(new Dimension(100, 200));
		rightPanel.setBackground(Color.BLUE);

		mainPanel.add(leftPanel, BorderLayout.LINE_START);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(rightPanel, BorderLayout.LINE_END);
		frame.getContentPane().add(mainPanel);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
		label.requestFocusInWindow();
		label.addFocusListener(new FocusListener(){

			public void focusGained(FocusEvent arg0) {
				System.out.println("Focus Gained");
				KeyboardFocusManager focusMgr = KeyboardFocusManager.getCurrentKeyboardFocusManager();
				Component focusOwner = focusMgr.getFocusOwner();
				System.out.println("FocusOwner is " + (( focusOwner == null) ? "null" : focusOwner.getName()) );		
			}

			public void focusLost(FocusEvent arg0) {
				System.out.println("Focus Lost");
				
			}
			
		});
		
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}

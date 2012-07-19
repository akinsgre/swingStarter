package test;

/*
 * HelloWorldSwing.java requires no other files. 
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.MessageFormat;

import javax.print.DocPrintJob;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.Size2DSyntax;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.print.event.PrintJobAdapter;
import javax.print.event.PrintJobEvent;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class HelloWorldSwing {
	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	static private JTextField field ;
	static private JTextArea area;
	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("HelloWorldSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add the ubiquitous "Hello World" label.
		BorderLayout layout = new BorderLayout();
		JPanel mainPanel = new JPanel(layout);

		JMenu menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);

		JMenuItem fileMenuItem = new JMenuItem("New", KeyEvent.VK_N);
		JMenuItem printMenuItem = new JMenuItem("Print", KeyEvent.VK_P);
		printMenuItem.addActionListener(new AbstractAction() {

			public void actionPerformed(ActionEvent arg0) {
				JTextArea area51 = new JTextArea();
				area51.setLineWrap(true);
				area51.setWrapStyleWord(true);
				area51.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce mi sapien, posuere ut convallis sit amet, venenatis in augue. Suspendisse convallis, arcu convallis euismod interdum, augue nibh ullamcorper arcu, ut placerat velit enim auctor eros. Donec rutrum ligula in augue aliquam eget congue velit vestibulum. Duis tristique, metus sit amet congue tempus, erat justo accumsan nisl, in semper sem nulla eu lectus. Aenean sit amet massa leo, vel iaculis quam. Sed lacinia, odio nec bibendum pellentesque, ante enim dictum diam, in vestibulum tellus risus at purus. Phasellus congue tortor vitae odio faucibus viverra. Cras metus justo, malesuada sed molestie nec, egestas ac urna. Etiam ornare pellentesque faucibus. Morbi pharetra lectus in enim rutrum sed luctus leo accumsan. Nunc euismod velit quis velit cursus ac tristique sapien lacinia. Curabitur odio mauris, iaculis ac faucibus id, hendrerit sit amet dui. Suspendisse varius facilisis vestibulum. Lorem ipsum.");
				print(area51.getPrintable(new MessageFormat("Current Worksheet"), new MessageFormat("Printed Today")), false);
			}
			
		});
		menu.add(fileMenuItem);
		menu.add(printMenuItem);

		JMenuBar menuBar = new JMenuBar();
		menuBar.add(menu);
		frame.setJMenuBar(menuBar);

		JLabel label = new JLabel("Hello World");
		label.setName("Label");
		label.setForeground(Color.BLACK);
		label.setPreferredSize(new Dimension(50, 100));

		field = new JTextField();
		field.setText("Done");
		

		mainPanel.add(label, BorderLayout.PAGE_START);
		mainPanel.add(field, BorderLayout.PAGE_END);

		JPanel leftPanel = new JPanel(new FlowLayout());
		leftPanel.setPreferredSize(new Dimension(100, 200));
		leftPanel.setBackground(Color.BLUE);

		JPanel centerPanel = new JPanel(new BorderLayout());
		centerPanel.setPreferredSize(new Dimension(300, 200));
		centerPanel.setBackground(Color.RED);
		area = new JTextArea("This is a test");
		
		area.setPreferredSize(centerPanel.getSize());
		area.setLineWrap(true);
		area.setWrapStyleWord(true);
		centerPanel.add(area);

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
	static public void print(final Printable printable) {
        print(printable, true);
    }
 
    static public void print(final Printable printable, final boolean portrait) {
        print(printable, portrait, new Insets(10, 10, 10, 10));
    }
	static private PrintRequestAttributeSet attr;
	static public void print(final Printable printable, final boolean portrait, final Insets insets) {
        PrinterJob pjob = PrinterJob.getPrinterJob();

        pjob.setPrintable(printable);
        // create an attribute set to store attributes from the print dialog
        if (attr == null) {
            attr = new HashPrintRequestAttributeSet();
            float leftMargin = insets.left;
            float rightMargin = insets.right;
            float topMargin = insets.top;
            float bottomMargin = insets.bottom;
            if (portrait) {
                attr.add(OrientationRequested.PORTRAIT);
            } else {
                attr.add(OrientationRequested.LANDSCAPE);
                leftMargin = insets.top;
                rightMargin = insets.bottom;
                topMargin = insets.right;
                bottomMargin = insets.left;
            }
            attr.add(MediaSizeName.ISO_A4);
            MediaSize mediaSize = MediaSize.ISO.A4;
            float mediaWidth = mediaSize.getX(Size2DSyntax.MM);
            float mediaHeight = mediaSize.getY(Size2DSyntax.MM);
            attr.add(new MediaPrintableArea(
                    leftMargin, topMargin,
                    (mediaWidth - leftMargin - rightMargin),
                    (mediaHeight - topMargin - bottomMargin), Size2DSyntax.MM));
        }
        if (pjob.printDialog()) {
            try {
                pjob.print(attr);
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        }
    }
}



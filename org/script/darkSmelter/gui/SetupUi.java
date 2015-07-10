package org.script.darkSmelter.gui;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.Context;
import org.script.darkSmelter.gui.swingComponents.MainTabbedPane;
public class SetupUi extends JFrame {
	public MainTabbedPane mainTabbedPane;

	/**
	 * Create the frame.
	 */
	public SetupUi(String title, Context ctx) {
		setTitle(title);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		mainTabbedPane = new MainTabbedPane(ctx);
		getContentPane().add(mainTabbedPane);
		
	}
	
}

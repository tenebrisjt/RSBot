package org.script.darkSmelter.paint;

import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Color;

import org.Context;

import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class SmithingPaint extends JFrame {
	private JPanel contentPane;
	public PaintUpdater<Context> updater;

	boolean minimized = false;
	Button toggleButton;
	public JLabel xpLabel, barsSmeltedLabel, timeLabel, profitLabel;
	JLabel title;
	public SmithingPaint(Context ctx) {
		updater = new PaintUpdater<Context>(this, ctx);
		contentPane = new JPanel();
		toggleButton = new Button("_");
		title = new JLabel("DarkSmither");
		timeLabel = new JLabel("Time Running: ");
		xpLabel = new JLabel("XP Gained: ");
		profitLabel = new JLabel("Profit: ");
		barsSmeltedLabel = new JLabel("Bars Smelted: ");
		
		setBounds(100, 100, 453, 112);
		setUndecorated(true);
		setBackground(Color.CYAN);
		setOpacity(0.5f);
		contentPane.setOpaque(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		title.setFont(new Font("Tahoma", Font.PLAIN, 14));
		title.setBounds(0, 0, 99, 14);
		toggleButton.setBounds(432, 0, 18, 22);
		timeLabel.setBounds(0, 20, 186, 14);
		xpLabel.setBounds(0, 40, 146, 14);
		profitLabel.setBounds(0, 60, 198, 14);
		barsSmeltedLabel.setBounds(0, 80, 133, 14);
		
		contentPane.add(title);
		contentPane.add(toggleButton);
		contentPane.add(timeLabel);
		contentPane.add(xpLabel);
		contentPane.add(profitLabel);
		contentPane.add(barsSmeltedLabel);
		
		toggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setBounds(minimized ? new Rectangle(100, 100, 450, 300) : new Rectangle(100,100,20,20));
				toggleButton.setName(minimized ? "_" : "+");
				toggleButton.setBounds(minimized ? new Rectangle(432, 0, 18, 22) : new Rectangle(0, 0, 20,20));
				minimized = !minimized;
				title.setVisible(!minimized);
			}
		});
		
	}
}

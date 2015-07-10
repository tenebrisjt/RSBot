package org.script.darkSmelter.gui.swingComponents.tabs;

import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.AbstractListModel;

import org.Context;
import org.script.darkSmelter.BankingTask;
import org.script.darkSmelter.SmeltingTask;
import org.script.darkSmelter.SuperHeatingTask;
import org.script.darkSmelter.paint.SmithingPaint;

import javax.swing.JButton;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

public class SmeltingTab extends JPanel{
	JLabel operationsLabel;
	JList<String> operationsList;
	JButton beginButton;
	public HashMap<String, Integer[]> oresToUse;
	@SuppressWarnings({ "unchecked", "serial" })
	public SmeltingTab(Context ctx) {
		oresToUse = new HashMap<String, Integer[]>();
		beginButton = new JButton("Begin");
		operationsLabel = new JLabel("Available Operations");
		operationsList = new JList<String>();
		/*
		 * new Integer[]{primary ore, secondary ore,
		 * 				 amount to withdraw, amount to withdraw(respectively)};
		 */
		int bronzeWithdrawAmount = 14,
			adamantPrimaryWithdraw = 4, adamantSecondaryWithdraw = 24;
		
		if(ctx.smithingData.superHeating){
			bronzeWithdrawAmount = 13;
			adamantPrimaryWithdraw = 3;
			adamantSecondaryWithdraw = 18;
		}
		oresToUse.put("bronze",new Integer[]{436, 438, bronzeWithdrawAmount, bronzeWithdrawAmount} );
		oresToUse.put("adamant", new Integer[]{449, 453, adamantPrimaryWithdraw, adamantSecondaryWithdraw});
		oresToUse.put("iron", new Integer[]{440});
		oresToUse.put("silver", new Integer[]{442});
		oresToUse.put("steel", new Integer[]{440, 453, 9, 18});
		oresToUse.put("gold", new Integer[]{444});
		oresToUse.put("mithril", new Integer[]{447, 453, 5, 20});
		setLayout(null);
		
		beginButton.setBounds(0, 164, 89, 23);
		operationsLabel.setBounds(0, 0, 129, 14);
		operationsList.setBounds(0, 25, 60, 122);
		operationsList.setModel(smeltModel);

		add(operationsList);
		add(operationsLabel);
		add(beginButton);
		
		beginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String value = operationsList.getSelectedValue().toLowerCase();
				ctx.smithingData.setData(oresToUse.get(value), value);
				ctx.setupUi.dispose();
				ctx.setupUi.setVisible(false);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ctx.smithingPaint.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				ctx.operator.submit(new BankingTask(ctx), new SmeltingTask(ctx), new SuperHeatingTask(ctx));
			}
		});
		
		
	}
	AbstractListModel<String> smeltModel = new AbstractListModel<String>(){
		String[] values = new String[] {"Bronze", "Iron", "Silver", "Steel", "Gold", "Mithril", "Adamant"};
		public int getSize() {
			return values.length;
		}
		public String getElementAt(int index) {
			return values[index];
		}
	};
}

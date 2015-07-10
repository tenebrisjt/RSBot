package org.script.darkSmelter.gui.swingComponents.tabs;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.SwingConstants;

import org.Context;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class LocationsTab extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4202910412348964948L;
	public JList<String> locationsList;
	public JLabel availableLocationsLable, warningLabel;
	JLabel superHeatSelectedLable;
	@SuppressWarnings("serial")
	public LocationsTab(Context ctx){
			setLayout(null);
			String heatName = new String("Selecting Superheat allows you to bank at any location");
			superHeatSelectedLable = new JLabel(heatName);
			availableLocationsLable = new JLabel("Available Locations");
			warningLabel = new JLabel("<html>Due to this being an f2p only script, lumbridge is the only \r\navailable location (unless superheating) (lumbridge has the  smallest distance between furnace, anvil, and bank<html>");
			locationsList = new JList<String>();
			locationsList.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent arg0) {
					ctx.smithingData.superHeating = locationsList.getSelectedIndex() == 1;
				}
			});
			
			
			availableLocationsLable.setBounds(0, 0, 112, 14);
			locationsList.setBounds(0, 25, 69, 48);
			superHeatSelectedLable.setBounds(71, 28, 310, 45);
			warningLabel.setVerticalAlignment(SwingConstants.TOP);
			warningLabel.setBounds(71, 73, 369, 56);
			
			locationsList.setModel(new AbstractListModel<String>() {
				String[] values = new String[] {"Lumbridge", "SuperHeat"};
				public int getSize() {
					return values.length;
				}
				public String getElementAt(int index) {
					return values[index];
				}
			});
			
			
			add(availableLocationsLable);
			add(locationsList);
			add(superHeatSelectedLable);
			add(warningLabel);

	}
}


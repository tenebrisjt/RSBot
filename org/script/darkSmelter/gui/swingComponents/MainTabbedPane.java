package org.script.darkSmelter.gui.swingComponents;
import javax.swing.JTabbedPane;

import org.Context;
import org.script.darkSmelter.gui.swingComponents.tabs.*;
public class MainTabbedPane extends JTabbedPane{
	public LocationsTab locationsTab;
	public SmeltingTab smeltingTab;
	public MainTabbedPane(Context ctx){
		super(JTabbedPane.TOP);
		setBounds(0, 0, 434, 264);
		locationsTab = new LocationsTab(ctx);
		smeltingTab = new SmeltingTab(ctx);
		addTab("Location", null, locationsTab, null);
		addTab("Smelting", null, smeltingTab, null);
		
		
	}
}


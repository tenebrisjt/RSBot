package org.util.ui.interfaceUnits;
import java.awt.Graphics;

import org.util.ui.Dimension;
import org.util.ui.UI;
public class Label extends UI {
	private String label;
	
	public Label(Dimension plane, UI container, String label) {
		super(plane, container);
		setLabel(label);
	}

	@Override
	public void draw(Graphics g) {
		if(getPanel() != null){
			getPanel().drawStringOn(label, g, getPlane().getX(), getPlane().getY());
		}
	}
	
	public String setLabel(String label){
		return this.label = label;
	}
	
	public String getLabel(){
		return label;
	}

}

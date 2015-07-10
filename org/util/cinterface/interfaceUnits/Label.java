package org.util.cinterface.interfaceUnits;
import java.awt.Graphics;

import org.util.cinterface.Dimension;
import org.util.cinterface.CUI;
public class Label extends CUI {
	private String label;
	
	public Label(Dimension plane, CUI container, String label) {
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

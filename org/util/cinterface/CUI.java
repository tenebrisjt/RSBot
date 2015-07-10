package org.util.cinterface;
import java.awt.Graphics;
import java.awt.Image;

import org.Context;
import org.powerbot.script.AbstractScript;
import org.util.cinterface.interfaceUnits.transparentBox.Panel;
/**
 * @deprecated  Due to this being a project, 
 * will be using swing for the time being for easier programming on the fly
 */
public abstract class CUI {
	
	protected Dimension plane;
	private CUI container;
	private Panel panel;
	public CUI(Dimension plane){
		this.plane = setPlane(plane);
	}
	
	public CUI(Dimension plane, CUI container){
		this(plane);
		if(container instanceof Panel){
			setPanel((Panel)container);
		}
		setContainer(container);
	}
	
	public Image getImage(String url, AbstractScript<Context> script){
		return script.downloadImage(url);
	}

	public Dimension setPlane(Dimension plane){
		return this.plane = plane;
	}
	
	public Dimension getPlane(){
		return plane;
	}
	
	public CUI setContainer(CUI container){
		return this.container = container;
	}
	
	public CUI getContainer(){
		return container;
	}
	
	public abstract void draw(Graphics g);
	

	public Panel getPanel(){
		if(panel == null){
			if(container != null){
				if(container instanceof Panel){
					return setPanel((Panel)container);
				} else {
					if(container.getContainer() != null){
						getPanel();
					}
				}
			}
		} else {
			return panel;
		}
		return null;
	}
	
	public Panel setPanel(Panel panel){
		return this.panel = panel;
	}
}

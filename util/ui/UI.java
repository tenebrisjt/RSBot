package org.util.ui;
import java.awt.Graphics;
import java.awt.Image;
import org.Context;
import org.powerbot.script.AbstractScript;
import org.util.ui.interfaceUnits.transparentBox.Panel;

public abstract class UI {
	/*
	 * this is only a project, i understand 
	 * that there are already libraries for the same thing
	 * that are most likely better written
	 * 
	 */
	protected Dimension plane;
	private UI container;
	private Panel panel;
	public UI(Dimension plane){
		this.plane = setPlane(plane);
	}
	
	public UI(Dimension plane, UI container){
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
	
	public UI setContainer(UI container){
		return this.container = container;
	}
	
	public UI getContainer(){
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

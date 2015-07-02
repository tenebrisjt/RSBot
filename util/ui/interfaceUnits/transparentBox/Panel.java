package org.util.ui.interfaceUnits.transparentBox;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.util.*;

import org.Context;
import org.powerbot.script.AbstractScript;
import org.powerbot.script.rt6.ClientContext;
import org.util.ui.*;
import org.util.ui.interfaceUnits.button.*;
public class Panel extends UI {
	private ArrayList<UI> components;
	private boolean isShown = true, isLoaded = false;
	private Image image;
	public ClientContext ctx;
	public Panel(Dimension plane, ClientContext ctx){
		super(plane);
		this.ctx = ctx;
		components = new ArrayList<UI>();
		submitComponent(
				new HideButton(new Dimension(this.plane.getWidth()-20,0,20,20), this));
		
	}

	@Override
	public void draw(Graphics g) {
		if(isLoaded){
			for(UI component : components){
				if(component != null){
					if(component instanceof Button){
						component.draw(g);
					} else {
						if(isShown){
							component.draw(g);
						}
					}
				}
			}
			if(isShown){
				if(getPlane().getWidth() == -1){
					g.drawImage(image, getPlane().getX(), getPlane().getY(), null);
				} else {
					g.drawImage(image, getPlane().getX(), getPlane().getY(), getPlane().getWidth(), getPlane().getHeight(), null);
				}
			}
		}
	}

	public void drawImageOn(Image image, Graphics g, int x, int y, int width, int height){
		g.drawImage(image, (getPlane().getX() + x), (getPlane().getY() + y), width, height, null);
	}

	public void drawStringOn(String string, Graphics g, int x, int y){
		int stringWidth;
		if((stringWidth = g.getFontMetrics().stringWidth(string)) >= getPlane().getWidth()){
			getPlane().setWidth(stringWidth + 10);
		}
		g.drawString(string, (getPlane().getX() + x), (getPlane().getY() + y +10));
	}

	public void submitComponent(UI...components){
		Collections.addAll(this.components, components);
	}

	public boolean show(boolean show){
		return isShown = show;
	}

	public boolean isShown(){
		return isShown;
	}

	public void mousePressed(MouseEvent e){
		for(UI component : components){
			if(component instanceof Button){
				((Button)component).mousePressed(e);
			}
		}
	}
	
	public void mouseReleased(){
		for(UI component : components){
			if(component instanceof Button){
				((Button)component).mouseReleased();
			}
		}
	}

	public void load(AbstractScript<Context> script){
		image = script.downloadImage("http://imageshack.us/a/img829/7638/r5ga.png");
		for(UI component : components){
			if(component instanceof Button){
				((Button)component).load(script);
			}
		}
		isLoaded = true;
	}
}

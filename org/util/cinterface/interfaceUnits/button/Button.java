package org.util.cinterface.interfaceUnits.button;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;

import org.Context;
import org.powerbot.script.AbstractScript;
import org.util.cinterface.Dimension;
import org.util.cinterface.CUI;

public abstract class Button extends CUI {
	protected Image image;
	protected boolean isClicked = false;
	
	public abstract void onAction();
	public abstract void load(AbstractScript<Context> script);
	
	public Button(Dimension plane, CUI container) {
		super(plane, container);
	}

	@Override
	public void draw(Graphics g) {
		if(image != null){
			if(getPanel() != null){
				getPanel().drawImageOn(image, g, getPlane().getX(), getPlane().getY(), getPlane().getWidth(), getPlane().getHeight());
			}
		}
	}
	
	public void mousePressed(MouseEvent e){
		int mouseX = e.getX(), 
				mouseY = e.getY();
		if(getPanel() != null){
			if(mouseX > (getPanel().getPlane().getX() + getPlane().getX()) 
					&& mouseX < (getPanel().getPlane().getX() + getPlane().getX() + getPlane().getWidth())
					&& mouseY > (getPanel().getPlane().getY() + getPlane().getY())
					&& mouseY < (getPanel().getPlane().getY() + getPlane().getY() + getPlane().getHeight())){
					onAction();
			}
		}
		isClicked = true;
	}
	
	public void mouseReleased(){
		isClicked = false;
	}
		
	public Image setImage(Image image){
		return this.image = image;
	}
	
	public Image getImage(){
		return image;
	}
}

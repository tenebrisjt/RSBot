package org.script;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import org.Context;
import org.powerbot.script.*;
import org.script.alcher.AlchingTask;
import org.util.TimeUtil;
import org.util.ui.Dimension;
import org.util.ui.interfaceUnits.Label;
@Script.Manifest(description = "MGM", name = "MGM")
public class Alcher extends PollingScript<Context> implements PaintListener, MouseListener{
	public Label titleLbl;
	public Label timeLbl;
	public Alcher(){
		ctx.newPanel(new Dimension(5,5,175,80));
		titleLbl = new Label(new Dimension(5,5), ctx.ui, "Alcher");
		timeLbl = new Label(new Dimension(5,20), ctx.ui, "" + TimeUtil.timeRunning());
		
		ctx.ui.submitComponent(titleLbl, timeLbl);
		ctx.ui.load(this);
	}
	@Override
	public void start(){
		ctx.operator.submit(new AlchingTask(ctx));
	}
	@Override
	public void poll() {
		ctx.operator.operate();
		
	}
	
	
	@Override
	public void repaint(Graphics arg0) {
		if(ctx.isInstantiated){
			ctx.ui.draw(arg0);
			timeLbl.setLabel(TimeUtil.timeRunning());
		}
		
		
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
	}
	
	@Override
	public void mouseExited(MouseEvent arg0) {
	}
	
	@Override
	public void mousePressed(MouseEvent arg0) {
		ctx.ui.mousePressed(arg0);
	}
	
	@Override
	public void mouseReleased(MouseEvent arg0) {
		ctx.ui.mouseReleased();
	}

}

package org.script;
import java.awt.EventQueue;

import javax.swing.SwingUtilities;

import org.Context;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.script.darkSmelter.*;
import org.script.darkSmelter.gui.SetupUi;
@Script.Manifest(description = "Semi-Aio Smelter", name = "DarkSmelter")
public class DarkSmelter extends PollingScript<Context> {
	@Override
	public void start(){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					ctx.setupUi.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	@Override
	public void poll() {
		ctx.operator.operate();
		
	}
	
	@Override
	public void stop(){
		ctx.smithingPaint.updater.running = false;
	}

}

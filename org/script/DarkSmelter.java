package org.script;
import java.awt.EventQueue;

import org.Context;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.script.darkSmelter.*;
import org.script.darkSmelter.gui.SetupUi;
@Script.Manifest(description = "Semi-Aio Smelter", name = "DarkSmelter")
public class DarkSmelter extends PollingScript<Context> {
	@Override
	public void start(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SetupUi frame = new SetupUi("DarkSmither", ctx);
					frame.setVisible(true);
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
		ctx.setupUi.mainTabbedPane.smeltingTab.paint.updater.running = false;
	}

}

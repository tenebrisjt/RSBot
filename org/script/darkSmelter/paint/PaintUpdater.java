package org.script.darkSmelter.paint;
import javax.swing.SwingUtilities;
import org.*;
import org.script.darkSmelter.paint.update.*;
import org.util.TimeUtil;
import org.util.execService.*;
public class PaintUpdater<C extends Context> extends Accessor<C>{
	public boolean running = true;
	Operator operator;
	long lastTimeRan;
	int tick = 500;
	public PaintUpdater(SmithingPaint paint, C ctx){
		super(ctx);
		operator = new Operator(OperatorType.SINGLE_INSTANCE);
		operator.submit(new BarsSmeltedUpdater(ctx), new ProfitUpdater(ctx), new XpUpdater(ctx), new TimeUpdater(ctx));
		
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				while(running){
					if(lastTimeRan + tick < TimeUtil.timeRunning()){
						System.out.println("running");
						operator.operate();
						lastTimeRan = TimeUtil.timeRunning();
					}
				}
				
			}
			
		});
	}
}

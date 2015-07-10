package org.script.darkSmelter.paint.update;

import org.Context;
import org.util.TimeUtil;
import org.util.execService.Operation;

public class TimeUpdater extends Operation<Context>{
	long lastUpdate = 0;
	public TimeUpdater(Context arg0) {
		super(arg0);
	}

	@Override
	public boolean validate() {
		return TimeUtil.timeRunning() != (lastUpdate == 0 ? lastUpdate = TimeUtil.timeRunning() : lastUpdate);
	}

	@Override
	public void operate() {
		System.out.println("timing");
		ctx.smithingPaint.timeLabel.setText("Time Running:" + TimeUtil.formatTimeRunning());
		lastUpdate = TimeUtil.timeRunning();
	}

}

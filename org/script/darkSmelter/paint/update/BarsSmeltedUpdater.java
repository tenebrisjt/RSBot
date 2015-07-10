package org.script.darkSmelter.paint.update;

import org.Context;
import org.script.darkSmelter.data.SmithingData;
import org.util.TimeUtil;
import org.util.execService.Operation;

public class BarsSmeltedUpdater extends Operation<Context>{
	public BarsSmeltedUpdater(Context arg0) {
		super(arg0);
	}

	@Override
	public boolean validate() {
		return ctx.game.loggedIn() &&  (ctx.smithingData.barsSmelted == 0 || 
				(ctx.smithingData.barsSmelted != (int)(ctx.smithingData.xpGained/SmithingData.BAR_XPS.get(ctx.smithingData.barToSmelt))));
	}

	@Override
	public void operate() {
		
			ctx.smithingData.barsSmelted =  (int)(ctx.smithingData.xpGained/SmithingData.BAR_XPS.get(ctx.smithingData.barToSmelt));
			ctx.smithingPaint.barsSmeltedLabel.setText("Bars Smelted(/hr): " + ctx.smithingData.barsSmelted 
					+ " (" + TimeUtil.perHour(ctx.smithingData.barsSmelted) + ")");
		
	}

}

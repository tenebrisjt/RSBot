package org.script.darkSmelter.paint.update;

import org.Context;
import org.powerbot.script.rt6.Constants;
import org.util.TimeUtil;
import org.util.execService.Operation;

public class XpUpdater extends Operation<Context>{
	int startXp = 0, currentXp;
	public XpUpdater(Context arg0) {
		super(arg0);
	}

	@Override
	public boolean validate() {
		return ctx.game.loggedIn() && 
				(currentXp = ctx.skills.experience(Constants.SKILLS_SMITHING)) - startXp != ctx.smithingData.xpGained;
	}

	@Override
	public void operate() {
		if(ctx.smithingData.xpGained == -1){
			if(startXp == 0){
				startXp = currentXp;
			}
			ctx.smithingData.xpGained = currentXp = startXp;
		}
		ctx.smithingPaint.xpLabel.setText("Xp Gained(/hr): " + TimeUtil.figureFormatter.format(ctx.smithingData.xpGained)
				+ " (" + TimeUtil.perHour(ctx.smithingData.xpGained) + ")");
	}

}

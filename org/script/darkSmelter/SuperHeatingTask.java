package org.script.darkSmelter;

import org.Context;
import org.powerbot.script.Condition;
import org.util.execService.Operation;

public class SuperHeatingTask extends Operation<Context>{

	public SuperHeatingTask(Context arg0) {
		super(arg0);
	}

	@Override
	public boolean validate() {
		return ctx.game.loggedIn() && ctx.smithingData.oresToWithdraw != null 
				&& ctx.players.local().animation() == -1
				&& !ctx.backpack.select().id(ctx.smithingData.primaryOre).isEmpty()
				&& (ctx.smithingData.usingSecondaryOre 
						&& !ctx.backpack.select().id(ctx.smithingData.secondaryOre).isEmpty())
				&& ctx.smithingData.superHeating;
	}

	@Override
	public void operate() {
		if(ctx.combatBar.select().poll().select()){
			if(ctx.backpack.select().id(ctx.smithingData.primaryOre).poll().click()){
				while(ctx.players.local().animation() != -1){
					Condition.sleep();
				}
			}
		}
	}

}

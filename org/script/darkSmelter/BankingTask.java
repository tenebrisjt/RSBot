package org.script.darkSmelter;
import java.util.concurrent.Callable;

import org.Context;
import org.powerbot.script.Condition;
import org.powerbot.script.Filter;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.Item;
import org.util.execService.Operation;
public class BankingTask extends Operation<Context>{

	public BankingTask(Context ctx) {
		super(ctx);

	}

	@Override
	public boolean validate() {
		return ctx.smithingData.oresToWithdraw != null && ctx.game.loggedIn() 	
				&& (ctx.backpack.select().id(ctx.smithingData.primaryOre).isEmpty()
						|| (ctx.smithingData.usingSecondaryOre 
								&& ctx.backpack.select().id(ctx.smithingData.secondaryOre).isEmpty()))
								&& !ctx.players.local().inMotion();
	}

	@Override
	public void operate() {
		if(ctx.bank.inViewport()){
			if(ctx.bank.open()){
				if(ctx.backpack.select().select(barFilter).size() > 0)
					ctx.bank.depositInventory();
				if(ctx.backpack.select().id(ctx.smithingData.primaryOre).isEmpty() &&
						ctx.bank.withdraw(ctx.smithingData.primaryOre, 
						ctx.smithingData.usingSecondaryOre ? ctx.smithingData.primaryWithdraw : 
							ctx.smithingData.superHeating ? 27 : 28)){
					while(ctx.backpack.select().id(ctx.smithingData.primaryOre).isEmpty()){
						Condition.sleep();
					}
				}
				if(ctx.smithingData.usingSecondaryOre 
						&& ctx.backpack.select().id(ctx.smithingData.secondaryOre).isEmpty()
						&& ctx.bank.withdraw(ctx.smithingData.secondaryOre, ctx.smithingData.secondaryWithdraw)){
					while(ctx.backpack.select().id(ctx.smithingData.secondaryOre).isEmpty()){
						Condition.sleep();
					}
						
				}
				ctx.bank.close();
			} else {
				ctx.camera.turnTo(ctx.bank.nearest());
			}
		}else if(!ctx.smithingData.superHeating){
			if(ctx.movement.newTilePath(new Tile(3214, 3257, 0)).traverse()){
				Condition.wait(new Callable<Boolean>(){
					@Override
					public Boolean call() throws Exception {
						return ctx.players.local().inMotion();
					}

				});
			}
			ctx.camera.turnTo(ctx.bank.nearest());
		}


	}
	Filter<Item> barFilter = new Filter<Item>(){

		@Override
		public boolean accept(Item arg0) {
			return arg0.name().toLowerCase().startsWith(ctx.smithingData.barToSmelt.toLowerCase());
		}

	};

}

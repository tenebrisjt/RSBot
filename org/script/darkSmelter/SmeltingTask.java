package org.script.darkSmelter;
import java.util.concurrent.Callable;

import org.Context;
import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.Component;
import org.powerbot.script.rt6.GameObject;
import org.powerbot.script.rt6.Widget;
import org.util.execService.Operation;

public class SmeltingTask extends Operation<Context>{
	public SmeltingTask(Context ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean validate() {
		//System.out.println("null");

		return ctx.game.loggedIn() && ctx.smithingData.oresToWithdraw != null 
				&& ctx.players.local().animation() == -1
				&& !ctx.backpack.select().id(ctx.smithingData.primaryOre).isEmpty()
				&& (ctx.smithingData.usingSecondaryOre 
						&& !ctx.backpack.select().id(ctx.smithingData.secondaryOre).isEmpty())
				&&  !ctx.smithingData.superHeating;


	}

	@Override
	public void operate() {
		final GameObject FURNACE = ctx.objects.select().id(2742).poll();

		if(!FURNACE.click()){
			if(ctx.movement.newTilePath(new Tile(3226, 3255, 0)).traverse()){
				Condition.wait(new Callable<Boolean>(){
					@Override
					public Boolean call() throws Exception {
						return ctx.players.local().inMotion();
					}

				});
			}
			
			ctx.camera.turnTo(FURNACE);
		} else {
			final Widget furnaceWidget = ctx.widgets.select().id(1370).poll();
			for(Component component : furnaceWidget.components()){
				if(component.text().toLowerCase().contains(ctx.smithingData.barToSmelt.toLowerCase())){
					if(component.click()){
						if(ctx.widgets.component(1370, 12).click()){
							while( !ctx.backpack.select().id(ctx.smithingData.primaryOre).isEmpty()){
								Condition.sleep();
							}

						}
					}
				}
			}
		}
	}
}

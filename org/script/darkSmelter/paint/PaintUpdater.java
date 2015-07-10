package org.script.darkSmelter.paint;
import javax.swing.SwingUtilities;

import org.Accessor;
import org.Context;
import org.powerbot.script.rt6.Constants;
import org.powerbot.script.rt6.GeItem;
import org.script.SmithingData;
import org.util.TimeUtil;
public class PaintUpdater<C extends Context> extends Accessor<C>{
	SmithingPaint paint;
	Thread updater;
	int startXp = 0, xpGained, barsSmelted = 0, barPrice, costToSmelt, natCost, profit = 0;
	public boolean running = true;
	public PaintUpdater(SmithingPaint paint, C ctx){
		super(ctx);
		this.paint = paint;
		barPrice = getPriceForBar(ctx.smithingData.barToSmelt.toLowerCase());
		costToSmelt = GeItem.price(ctx.smithingData.primaryOre) + (ctx.smithingData.usingSecondaryOre ? 
				GeItem.price(ctx.smithingData.secondaryOre) * 
				ctx.smithingData.secondaryWithdraw / ctx.smithingData.primaryWithdraw : 0);
		if(ctx.smithingData.superHeating){
			natCost = GeItem.price(561);
		}
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				/*while(running){
					update();
				}*/
				
			}
			
		});
	}
	
	public int getPriceForBar(String barName){
		switch(barName){
		case"bronze":
			return GeItem.price(2349);
		case"iron":
			return GeItem.price(2351);
		case"steel":
			return GeItem.price(2353);
		case"silver":
			return GeItem.price(2355);
		case "gold":
			return GeItem.price(2357);
		case "mithril":
			return GeItem.price(2359);
		case "adamant":
			return GeItem.price(2361);
		default:
			return 0;
		}
	}
	
	public void update(){
		paint.timeLabel.setText("Time Running:" + TimeUtil.timeRunning());
		if(ctx.game.loggedIn()){
			int currentXp = ctx.skills.experience(Constants.SKILLS_SMITHING);
			if(startXp == 0){
				startXp = currentXp; 
			}
			
			if(currentXp != startXp){
				xpGained = currentXp - startXp;
				paint.xpLabel.setText("Xp Gained(/hr): " + TimeUtil.figureFormatter.format(xpGained)
													+ " (" + TimeUtil.perHour(xpGained) + ")");
			}

			if(barsSmelted == 0 || barsSmelted != (int)(xpGained/SmithingData.BAR_XPS.get(ctx.smithingData.barToSmelt))){
				barsSmelted =  (int)(xpGained/SmithingData.BAR_XPS.get(ctx.smithingData.barToSmelt));
				paint.barsSmeltedLabel.setText("Bars Smelted(/hr): " + barsSmelted + " (" + TimeUtil.perHour(barsSmelted) + ")");
			}
			if(profit == 0 || profit != (barsSmelted * barPrice) - (barsSmelted * costToSmelt)){
				profit = (barsSmelted * barPrice) - (barsSmelted * costToSmelt);
				paint.profitLabel.setText("Profit(/hr): " + profit + " (" + TimeUtil.perHour(profit) + ")");
			} 
			
			
			
		}
	}
}

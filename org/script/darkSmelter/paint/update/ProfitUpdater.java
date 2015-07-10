package org.script.darkSmelter.paint.update;

import org.Context;
import org.powerbot.script.rt6.GeItem;
import org.util.TimeUtil;
import org.util.execService.Operation;

public class ProfitUpdater extends Operation<Context>{
	int profit = 0, barPrice, costToSmelt, natCost;
	public ProfitUpdater(Context arg0) {
		super(arg0);
		barPrice = getPriceForBar(ctx.smithingData.barToSmelt.toLowerCase());
		if(ctx.smithingData.superHeating){
			natCost = GeItem.price(561);
		}
		costToSmelt = (ctx.smithingData.superHeating ? natCost : 0) +
				GeItem.price(ctx.smithingData.primaryOre) + (ctx.smithingData.usingSecondaryOre ? 
				GeItem.price(ctx.smithingData.secondaryOre) * 
				ctx.smithingData.secondaryWithdraw / ctx.smithingData.primaryWithdraw : 0);
		
	}

	

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return profit == 0 || (profit != (ctx.smithingData.barsSmelted * barPrice) 
				- (ctx.smithingData.barsSmelted * costToSmelt));
	}

	@Override
	public void operate() {
			profit = (ctx.smithingData.barsSmelted * barPrice) - (ctx.smithingData.barsSmelted * costToSmelt);
			ctx.smithingPaint.profitLabel.setText("Profit(/hr): " + profit + " (" + TimeUtil.perHour(profit) + ")");
		
		
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
	
}

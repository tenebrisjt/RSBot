package org.script.alcher;
import org.Context;
import org.powerbot.script.Filter;
import org.powerbot.script.rt6.Item;
import org.util.TimeUtil;
import org.util.execService.Task;
import org.util.ui.Dimension;
import org.util.ui.interfaceUnits.Label;

public class AlchingTask extends Task{
	private int alchsCast = 0, profit = 0, xpGained = 0;
	private Label alchsLbl, profitLbl, xpLbl;
	public AlchingTask(Context ctx) {
		super(ctx);
		alchsLbl = new Label(new Dimension(5,35), ctx.ui, "");
		profitLbl = new Label(new Dimension(5,50), ctx.ui, "");
		xpLbl = new Label(new Dimension(5, 65), ctx.ui, "");
		ctx.ui.submitComponent(alchsLbl, profitLbl, xpLbl);
	}

	@Override
	public boolean validate() {
		return  ctx.game.loggedIn() && ctx.players.local() != null && ctx.players.local().animation() == -1;
	}
	Item alchItem;
	@Override
	public void operate() {
		if(alchItem != null){
			alchsCast++;
			profit = alchsCast * 15;
			xpGained+=65;
			alchsLbl.setLabel("Alchs Cast(/hr): " + alchsCast 
					+ "(" + TimeUtil.figureFormatter.format(TimeUtil.perHour(alchsCast)) + ")");
			profitLbl.setLabel("Profit(/h): " + TimeUtil.figureFormatter.format(profit)
					+ "(" + TimeUtil.figureFormatter.format(TimeUtil.perHour(profit)) + ")");
			xpLbl.setLabel("XP(/h): "  + TimeUtil.figureFormatter.format(xpGained) 
					+ "(" + TimeUtil.figureFormatter.format(TimeUtil.perHour(xpGained)) + ")");
			if(ctx.combatBar.poll().select()){
				alchItem.click();
			}
		} else {
			alchItem = ctx.backpack.select().select(natFilter).poll();
		}
	}
	Filter<Item> natFilter = new Filter<Item>(){


		@Override
		public boolean accept(Item item) {
			return item.id() != 561;
		}
		
	};
}

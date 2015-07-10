package org.script.darkSmelter.data;

import java.util.HashMap;
public class SmithingData{
	public Integer[] oresToWithdraw;
	public int primaryOre, secondaryOre, primaryWithdraw, secondaryWithdraw, xpGained = -1, barsSmelted = 0;
	public boolean usingSecondaryOre, superHeating = false;
	public String barToSmelt;
	public static HashMap<String, Double> BAR_XPS = new HashMap<String, Double>();
	public SmithingData(){
		BAR_XPS.put("bronze", 6.2);
		BAR_XPS.put("iron", 12.5);
		BAR_XPS.put("silver", 13.7);
		BAR_XPS.put("steel", 17.5);
		BAR_XPS.put("gold", 22.5);
		BAR_XPS.put("adamant", 37.5);
		BAR_XPS.put("mithril", 30.0);
	}
	public void setData(Integer[] data, String barToSmelt){
		this.barToSmelt = barToSmelt;
		oresToWithdraw = data;
		usingSecondaryOre = oresToWithdraw.length > 0;
		primaryOre = oresToWithdraw[0];
		if(usingSecondaryOre){
			secondaryOre = oresToWithdraw[1];
			primaryWithdraw = oresToWithdraw[2];
			secondaryWithdraw = oresToWithdraw[3];
		}
	}

}

package org.util;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;
public class TimeUtil{
	public final static long START_TIME = System.currentTimeMillis();
	public static DecimalFormat figureFormatter;
	public TimeUtil() {
		figureFormatter = new DecimalFormat("###,###,###");
	}

	public static String timeFormat(long millis){
		return String.format("%02d:%02d:%02d", 
			    TimeUnit.MILLISECONDS.toHours(millis),
			    TimeUnit.MILLISECONDS.toMinutes(millis) - 
			    TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
			    TimeUnit.MILLISECONDS.toSeconds(millis) - 
			    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
	}
	
	public static String formatTimeRunning(){
		return timeFormat(System.currentTimeMillis()-START_TIME);
	}
	
	public static long timeRunning(){
		return System.currentTimeMillis() - START_TIME;
	}
	
	
	public static int  perHour(int amount){
		return (int) ((amount) * 3600000D / (System.currentTimeMillis() - START_TIME));
	}

	
	
}

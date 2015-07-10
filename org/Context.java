package org;
import org.powerbot.script.rt6.ClientContext;
import org.script.darkSmelter.data.*;
import org.script.darkSmelter.gui.SetupUi;
import org.script.darkSmelter.paint.SmithingPaint;
import org.util.execService.Operator;
import org.util.execService.OperatorType;
public class Context extends ClientContext {
	public SetupUi setupUi;
	public Operator operator;
	public boolean isInstantiated = false;
	public SmithingData smithingData;
	public SmithingPaint smithingPaint;
	public Context(ClientContext arg0) {
		super(arg0);
		operator = new Operator(OperatorType.SINGLE_INSTANCE);
		smithingData = new SmithingData();
		setupUi = new SetupUi("DarkSmither", this);
		
	}
	

}

package org;
import org.powerbot.script.rt6.ClientContext;
import org.util.execService.Operator;
import org.util.execService.OperatorType;
import org.util.ui.Dimension;
import org.util.ui.interfaceUnits.transparentBox.Panel;
public class Context extends ClientContext {
	public Operator operator;
	public Panel ui;
	public boolean isInstantiated = false;
	public Context(ClientContext arg0) {
		super(arg0);
		operator = new Operator(OperatorType.SINGLE_INSTANCE);
	}
	public void newPanel(Dimension plane){
		this.ui = new Panel(plane, this);
		isInstantiated = true;
	}

}

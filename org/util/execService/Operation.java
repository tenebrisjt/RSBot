package org.util.execService;
import org.Accessor;
import org.Context;
public abstract class Operation<C extends Context> extends Accessor<C>{
	public Operation(C arg0) {
		super(arg0);
	}
	public abstract boolean validate();
	public abstract void operate();
}

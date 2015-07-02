package org.util.execService;
import org.Context;
public abstract class Task implements Operation{
	protected Context ctx;
	public Task(Context ctx) {
		this.ctx = ctx;
	}
}

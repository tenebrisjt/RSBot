package org.util.execService;

public class Operator{
	public static int tick;
	public static final int MAX_OPERATIONS = 50;
	protected Operation<?> operation;
	protected Operation<?>[] operations;
	OperatorType type;

	public Operator(OperatorType type){
		this.type = type;
		operations = new Operation[MAX_OPERATIONS];
	}

	public void operate(){
		if(type.equals(OperatorType.SINGLE_INSTANCE) && operation != null && operation.validate()){
			operation.operate();
		} else {
			for(Operation<?> iter : operations){
				if(invoke(iter) && operation.validate()){
					operation.operate();
				}
			}
		}
	}

	public boolean submit(Operation<?> state){
		int slot;
		return (slot = getEmptySlot()) != -1 && (operations[slot] = state) != null;
	}

	public boolean invoke(Operation<?> state){
		return (this.operation = state) != null;
	}

	public void submit(Operation<?>...states){
		for(Operation<?> state : states){
			submit(state);
		}
	}

	private int getEmptySlot(){
		int slot = -1;
		for(int i = 0; i < operations.length; i++){
			if(operations[i] == null){
				slot = i;
				break;
			}
		}
		return slot;
	}
}

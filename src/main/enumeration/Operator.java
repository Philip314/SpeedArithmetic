package main.enumeration;

public enum Operator {
	
	ADD,
	SUBTRACT,
	MULTIPLY,
	DIVIDE;
	
	@Override
	public String toString() {
		switch(this) {
			case ADD:
				return "+";
			case SUBTRACT:
				return "-";
			case MULTIPLY:
				return "*";
			case DIVIDE:
				return "/";
			default:
				throw new IllegalArgumentException();
		}
	}
	
}

package tp4;

class Variable implements Expression {
	@Override
	public double value(double x) {
		return x;
	}
	
	public String toString() {
		return "x";
	}
}

class Constant implements Expression {
	private double val;
	
	public Constant(double value) {
		val = value;
	}
	
	@Override
	public double value(double x) {
		return val;
	}
	
	public String toString() {
		return Double.toString(val);
	}
}
package tp4;

class Addition extends BinaryExpression {

    public Addition(Expression g, Expression d) {
        super(g, d);
    }

    public double value(double x) {
        return left.value(x) + right.value(x);
    }

    public String toString() {
        return "(" + left + " + " + right + ")";
    }
}

class Subtraction extends BinaryExpression {

    public Subtraction(Expression g, Expression d) {
        super(g, d);
    }

    public double value(double x) {
        return left.value(x) - right.value(x);
    }

    public String toString() {
    	return "(" + left + " - " + right + ")";
    }
}

class Division extends BinaryExpression {

    public Division(Expression g, Expression d) {
        super(g, d);
    }

    public double value(double x) {
        return left.value(x) / right.value(x);
    }

    public String toString() {
    	return "(" + left + " / " + right + ")";
    }
}

class Product extends BinaryExpression {

    public Product(Expression g, Expression d) {
        super(g, d);
    }

    public double value(double x) {
        return left.value(x) * right.value(x);
    }

    public String toString() {
    	return "(" + left + " * " + right + ")";
    }
}
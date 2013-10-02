package tp4;

public interface Expression {
    double value(double x);
}

abstract class BinaryExpression implements Expression {
    protected Expression left, right;

    public BinaryExpression(Expression g, Expression d) {
        left = g;
        right = d;
    }
}

abstract class UnaryExpression implements Expression {
    protected Expression argument;

    void setArgument(Expression a) {
        argument = a;
    }
}
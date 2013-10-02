package tp4;

class Sin extends UnaryExpression {

    public double value(double x) {
        return Math.sin(argument.value(x));
    }

    public String toString() {
        return "sin(" + argument + ")";
    }
}

class Cos extends UnaryExpression {

    public double value(double x) {
        return Math.cos(argument.value(x));
    }

    public String toString() {
        return "cos(" + argument + ")";
    }
}

class Log extends UnaryExpression {

    public double value(double x) {
        return Math.log(argument.value(x));
    }

    public String toString() {
        return "log(" + argument + ")";
    }
}

class Exp extends UnaryExpression {

    public double value(double x) {
        return Math.exp(argument.value(x));
    }

    public String toString() {
        return "exp(" + argument + ")";
    }
}
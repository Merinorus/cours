package tp4;

import java.io.*;

public class Analyzer {
	public static void main(String[] args) {
		try {
			Analyzer analyzer = new Analyzer(args[0]);
			Expression expr = analyzer.analyze();
			
			System.out.println("f(x) = " + expr);
			
			for(int i = 1; i < args.length; i++) {
				double x = Double.parseDouble(args[i]);
				System.out.println("f(" + x + ") = " + expr.value(x));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private StreamTokenizer lexical;
	
	public Analyzer(String text) {
		lexical = new StreamTokenizer(new StringReader(text));
		lexical.ordinaryChar('/');
		lexical.ordinaryChar('-');
	}
	
	public Expression analyze() throws IOException, SyntaxErrorException {
		lexical.nextToken();
		Expression result = analyzeExpression();
		if(lexical.ttype != StreamTokenizer.TT_EOF)
			throw new SyntaxErrorException("Unexpected character at end of text.");
		
		return result;
	}
	
	private Expression analyzeExpression() throws IOException, SyntaxErrorException {
		Expression result;
		
		boolean sign = true;
		if(lexical.ttype == '-') {
			sign = false;
			lexical.nextToken();
		}
		result = (sign) ? analyzeTerm() : new Subtraction(new Constant(0), analyzeTerm());
		
		while(lexical.ttype == '+' || lexical.ttype == '-') {
			int symbol = lexical.ttype;
			lexical.nextToken();
			Expression term = analyzeTerm();
			switch(symbol) {
				case '+':
					result = new Addition(result, term);
					break;
				case '-':
					result = new Subtraction(result, term);
					break;
				default:
					throw new SyntaxErrorException("Unknown symbol '" + lexical.ttype + "' in expression");
			}
			
			lexical.nextToken();
		}
		
		return result;
	}
	
	private Expression analyzeTerm() throws IOException, SyntaxErrorException {
		Expression result = analyzeFactor();
		
		while(lexical.ttype == '*' || lexical.ttype == '/') {
			int symbol = lexical.ttype;
			lexical.nextToken();
			Expression fact = analyzeFactor();
			switch(symbol)
			{
				case '*':
					result = new Product(result, fact);
					break;
				case '/':
					result = new Division(result, fact);
					break;
				default:
					throw new SyntaxErrorException("Unknown symbol '" + lexical.ttype + "' in term");
			}
		}
		
		return result;
	}
	
	private Expression analyzeFactor() throws IOException, SyntaxErrorException {
		Expression result;
		switch(lexical.ttype) {
			case StreamTokenizer.TT_NUMBER:
				result = new Constant(lexical.nval);
				break;
			case StreamTokenizer.TT_WORD:
				if(lexical.sval.equalsIgnoreCase("x")) {
					result = new Variable();
				} else if (lexical.sval.equalsIgnoreCase("sin")) {
					lexical.nextToken();
					
					Sin sin = new Sin();
					sin.setArgument(analyzeExpression());
					result = sin;
				} else if (lexical.sval.equalsIgnoreCase("cos")) {
					lexical.nextToken();
					
					Cos cos = new Cos();
					cos.setArgument(analyzeExpression());
					result = cos;
				} else if (lexical.sval.equalsIgnoreCase("log")) {
					lexical.nextToken();
					
					Log log = new Log();
					log.setArgument(analyzeExpression());
					result = log;
				} else if (lexical.sval.equalsIgnoreCase("exp")) {
					lexical.nextToken();
					
					Exp exp = new Exp();
					exp.setArgument(analyzeExpression());
					result = exp;
				} else {
					throw new SyntaxErrorException("Undefined variable '" + lexical.sval + "'");
				}
				break;
			case '(':
				lexical.nextToken();
				result = analyzeExpression();
				lexical.nextToken();
				break;
			case ')':
				throw new SyntaxErrorException("Unmatched closing parenthesis.");
			default:
				throw new SyntaxErrorException("Unknown symbol '" + lexical.ttype + "'in factor");
		}
		
		lexical.nextToken();
		return result;
	}
	
	private void debug() {
		System.out.println("ttype: " + lexical.ttype + "(" + (char)lexical.ttype + ")");
		System.out.println("nval: " + lexical.nval);
		System.out.println("sval: " + lexical.sval);
	}
	
	class SyntaxErrorException extends Exception {
		public SyntaxErrorException(String message) {
			super(message);
			debug();
		}
	}
}
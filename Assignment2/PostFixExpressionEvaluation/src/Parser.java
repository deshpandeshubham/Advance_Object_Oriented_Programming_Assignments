import java.util.Stack;
import java.util.*;

public class Parser {
	
	public PostfixExpression getOperator(String s, PostfixExpression left, PostfixExpression right) {
		if(s.equals("+"))
			return new AddExpression(left, right);
		else if(s.equals("-"))
			return new MinusExpression(left, right);
		else if(s.equals("*"))
			return new MultiplyExpression(left, right);
		else if(s.equals("/"))
			return new DivisionExpression(left, right);
		return null;
	}
	
	public PostfixExpression getSinOrLog(String s, PostfixExpression expr) {
		if(s.equals("lg"))
			return new LogExpression(expr);
		else if(s.equals("sin"))
			return new SinExpression(expr);
		return null;
	}
	
	public boolean isOperator(String s) {
		if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/"))
			return true;
		else
			return false;
	}

	public boolean isSinOrLog(String s) {
		if(s.equals("lg") || s.equals("sin"))
			return true;
		else
			return false;
	}
}
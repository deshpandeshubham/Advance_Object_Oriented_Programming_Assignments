import java.util.Scanner;
import java.util.Stack;

public class EvaluateExpression {

	Parser parse = new Parser();
	Stack<PostfixExpression> stack = new Stack<PostfixExpression>();
	
	public double getResult(String expression) { 
		String[] expressionSymbols = expression.split(" ");
		for (String s : expressionSymbols) {
			if (parse.isOperator(s)) {
				PostfixExpression rightExpression = stack.pop();
				PostfixExpression leftExpression = stack.pop();
				PostfixExpression operator = parse.getOperator(s, leftExpression, rightExpression);
				double result = operator.interpret();
				stack.push(new NumberExpression(result));
			} 
			else if(parse.isSinOrLog(s)) {
				PostfixExpression expr = stack.pop();
				PostfixExpression operator = parse.getSinOrLog(s, expr);
				double result = operator.interpret();
				stack.push(new NumberExpression(result));
			}
			else {
				PostfixExpression i = new NumberExpression(s);
				stack.push(i);
			}
		}
		return (stack.pop()).interpret();
	}
}

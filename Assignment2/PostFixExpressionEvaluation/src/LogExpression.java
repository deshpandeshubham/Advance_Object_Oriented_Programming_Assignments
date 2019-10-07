public class LogExpression implements PostfixExpression {

	PostfixExpression expr;
	
	public LogExpression(PostfixExpression expr) {
		this.expr = expr;
	}

	@Override
	public double interpret() {
		return (Math.log(expr.interpret())) / (Math.log(2));
	}
}

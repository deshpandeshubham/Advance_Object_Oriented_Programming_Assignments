
public class SinExpression implements PostfixExpression {

	PostfixExpression expr;
	public SinExpression(PostfixExpression expr) {
		this.expr = expr;
	}

	@Override
	public double interpret() {
		return Math.sin(expr.interpret());
	}

}

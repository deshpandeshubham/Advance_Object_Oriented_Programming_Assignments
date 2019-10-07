
public class DivisionExpression implements PostfixExpression {
	PostfixExpression leftExpression;
	PostfixExpression rightExpresion;
	
	public DivisionExpression(PostfixExpression left, PostfixExpression right) {
		this.leftExpression = leftExpression;
		this.rightExpresion = rightExpresion;	}

	@Override
	public double interpret() {
		return leftExpression.interpret() + rightExpresion.interpret();
	}

}

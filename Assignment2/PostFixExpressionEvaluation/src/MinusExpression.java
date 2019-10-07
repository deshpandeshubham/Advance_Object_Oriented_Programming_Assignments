public class MinusExpression implements PostfixExpression {

	PostfixExpression leftExpression;
	PostfixExpression rightExpresion;

	public MinusExpression(PostfixExpression leftExpression,
			PostfixExpression rightExpresion) {
		this.leftExpression = leftExpression;
		this.rightExpresion = rightExpresion;
	}

	@Override
	public double interpret() {
		return leftExpression.interpret() - rightExpresion.interpret();
	}

}
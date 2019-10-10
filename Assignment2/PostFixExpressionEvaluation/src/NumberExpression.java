public class NumberExpression implements PostfixExpression {
	double number;

	public NumberExpression(double i) {
		number = i;
	}

	public NumberExpression(String s) {
		try{
			number = Double.parseDouble(s);;
		}
		catch(NumberFormatException exception){}
	}

	@Override
	public double interpret() {
		return number;
	}
}
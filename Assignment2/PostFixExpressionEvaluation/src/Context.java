import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Context {
	EvaluateExpression eval = new EvaluateExpression();
	HashMap<Integer, String> values = new HashMap<Integer, String>();
	HashMap<Integer, String> expressionString = new HashMap<Integer, String>();
	
	public String getValue(int key) {
		return values.get(key);
	}
	
	public void setValue(int key, String value) {
		values.put(key, value);
	}

	public void toggleValueView() {		
		System.out.println("In Value View");
		System.out.println(Client.context.values.keySet());
		for(Integer key : Client.context.values.keySet()) {
			String value = Client.context.getValue(key);
			System.out.println("Cell Value -----> "+value+" at index :"+key);
			Client.context.expressionString.put(key, value);
			if(value.contains(" ")) {
				Client.context.expressionString.put(key, value);
				System.out.println("Value at "+key+" in expression map : "+Client.context.expressionString.get(key));
				value = getValueExpression(key, value);
				System.out.println("Value Expression After Conversion----->"+value);
			}
			Double res = eval.getResult(value.trim());
			System.out.println(res);
			Client.context.values.put(key, res.toString());
			Client.tableModel.setValueAt(res.toString(), 0, key);
		}
	}

	public void toggleEquationView() {
		System.out.println("In Equation View");
		for(Integer key : Client.context.expressionString.keySet()) {
			String value = Client.context.expressionString.get(key);
			System.out.println("Cell Value----->"+value+"at index : "+key);
			Client.tableModel.setValueAt(value, 0, key);
		}
	}
	
	public String getValueExpression(Integer index, String expression) {
		String[] alphabets = expression.split(" ");
		String finalString = "";
		for(String s : alphabets) {
			if(s.matches("[A-I]")) {
				new ObserverPattern().dependencyCheck(index);
				finalString = finalString + " " + Client.context.getValue(((int)(s.charAt(0) - 65)));
			}
			else
				finalString = finalString + " " + s;
		}
		return finalString;
	}
}
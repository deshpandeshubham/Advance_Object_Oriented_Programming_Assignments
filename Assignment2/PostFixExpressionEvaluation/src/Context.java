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
			Client.context.expressionString.put(key, value);
			System.out.println("Cell Value -----> "+value);
			Double res = eval.getResult(value);
			System.out.println(res);
			Client.context.values.put(key, res.toString());
			Client.tableModel.setValueAt(res.toString(), 0, key);
			
		}
	}

	public void toggleEquationView() {
		System.out.println("In Equation View");
		for(Integer key : Client.context.expressionString.keySet()) {
			String value = Client.context.expressionString.get(key);
			System.out.println("Cell Value----->"+value);
			Client.tableModel.setValueAt(value, 0, key);
		}
	}
}
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Context {
	HashMap<Integer, String> values = new HashMap<Integer, String>();
	HashMap<Integer, String> expressionString = new HashMap<Integer, String>();
	public String getValue(int key) {
		return values.get(key);
	}
	
	public void setValue(int key, String value) {
		values.put(key, value);
	}
}
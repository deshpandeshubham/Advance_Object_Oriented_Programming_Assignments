import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.omg.CORBA.ContextList;

public class Context {
	
	HashMap<Integer, String> values = new HashMap<Integer, String>();
	HashMap<Integer, String> expressionString = new HashMap<Integer, String>();
	Set<String> dependentCellsSet = new HashSet<String>();
	Caretaker caretaker = new Caretaker();
    Originator originator = new Originator();    
    int statesCounter = -1;
	
	public String getValue(int key) {
		if(values.get(key) == null) 
			saveCellState();
		return values.get(key);
	}
	
	public void saveCellState() {
		HashMap<String,String> states = new HashMap<String, String>();
        for(Integer key : Client.context.values.keySet()) {
            String currentValue = Client.context.values.get(key);
            if(!Client.isEquationView && !Client.isObserver) {                
                states.put(Integer.toString(key),currentValue);
            }            
        }
        if(!Client.isEquationView){
            Client.context.originator.setData(states);
            Client.context.caretaker.addMemento(Client.context.originator.store());            
            Client.context.statesCounter++;
        }
	}

	public void setValue(int key, String value) {
		if(!Client.isUndoClicked && !Client.isObserver)
			saveCellState();
		values.put(key, value);
	}

	public void restorePreviousState() {
		HashMap<String,String> restoreData = new HashMap<String, String>();
        if(!Client.isEquationView){
            if(Client.context.statesCounter >= 1){
                restoreData = Client.context.originator.restore(Client.context.caretaker.getMemento(Client.context.statesCounter));    
            }
        }
        for(String key : restoreData.keySet()){                
            String previousValue = restoreData.get(key);
            String currentValue = Client.context.values.get(key);
            if(Client.isEquationView && !Client.isObserver && !previousValue.equals(currentValue)) {
                Client.tableModel.setValueAt(previousValue, 0, Integer.valueOf(key));
            }
        }
	}
}
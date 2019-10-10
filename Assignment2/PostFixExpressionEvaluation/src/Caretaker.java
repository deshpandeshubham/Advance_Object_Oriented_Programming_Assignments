import java.util.*;
public class Caretaker{
    
	ArrayList<Memento> mementos = new ArrayList<Memento>();

    public void addMemento(Memento mem){
    	mementos.add(mem);        
    }

    public Memento getMemento(int columnIndex){
        return mementos.get(columnIndex);
    }
}
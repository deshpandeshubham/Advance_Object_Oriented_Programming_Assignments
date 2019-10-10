import java.util.*;
public class Memento{
    
	private HashMap<String,String> data = new HashMap<String,String>();

    public Memento(HashMap<String,String> savedData){
        data = savedData;
    }

    public HashMap<String,String> getSavedData(){
        return data;
    }
}
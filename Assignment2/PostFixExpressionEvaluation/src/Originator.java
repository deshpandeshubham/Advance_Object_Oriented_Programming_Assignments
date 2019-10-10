import java.util.*;

public class Originator {
   
	HashMap<String,String> data = new HashMap<String, String>();

    public void setData(HashMap<String,String> newData){
        data = newData;    
    }

    public Memento store(){
        return new Memento(data);
    }

    public HashMap<String,String> restore(Memento mem){
        HashMap<String,String> savedData = mem.getSavedData();
        return savedData;
    }
}
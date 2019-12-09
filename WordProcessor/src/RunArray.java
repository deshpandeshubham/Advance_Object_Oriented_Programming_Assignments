import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class RunArray {
    private ArrayList<CharacterFont> runList = new ArrayList<CharacterFont>();
	private int endIndex;

    public void appendRun(int run, Font characterFont) {
        int startIndex = endIndex + 1;
        endIndex = (startIndex + run) - 1;
        runList.add(new CharacterFont(startIndex, endIndex, characterFont));
    }
    
    public void addRun(int startIndex, int run, Font characterFont) {
        endIndex = (startIndex + run) - 1;
        runList.add(new CharacterFont(startIndex, endIndex, characterFont));
    }

    public Font getFont(int index) {
        Iterator<CharacterFont> itr = runList.iterator();
        while(itr.hasNext()) {
            CharacterFont charFont = itr.next();
            if(charFont.contains(index)) {
                return charFont.characterFont;
            }
        }
        return null;
    }
}

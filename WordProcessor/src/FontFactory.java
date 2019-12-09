import java.awt.*;
import java.util.HashMap;

public class FontFactory {
	
    private static FontFactory fontFactory;
    private HashMap<String, Font> charFontMap = new HashMap<String, Font>();

    public Font getFont(String fontName, int fontStyle, int fontSize) {
    	if (!charFontMap.containsKey(fontName + fontStyle + fontSize))
            charFontMap.put(fontName + fontStyle + fontSize, new Font(fontName, fontStyle, fontSize));
    	return charFontMap.get(fontName + fontStyle + fontSize);
    }

    public static FontFactory getFontFactoryInstance(){
        if(fontFactory == null)
            fontFactory = new FontFactory();
        return fontFactory;
    }
}





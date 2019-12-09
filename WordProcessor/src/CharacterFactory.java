import java.util.HashMap;

public class CharacterFactory {
	
	private static CharacterFactory charFactory;
	private HashMap<Character, TextCharacter> charMap = new HashMap<Character, TextCharacter>();
    
    public TextCharacter getCharacter(char charUnicode) {
        TextCharacter textChar = charMap.get(charUnicode);
        if(textChar == null) {
            textChar = new TextCharacter(charUnicode);
            charMap.put(charUnicode,textChar);
        }
        return textChar;
    }

    public static CharacterFactory getCharacterFactoryInstance(){
        if(charFactory == null)
            charFactory = new CharacterFactory();
            return charFactory;
    }
}

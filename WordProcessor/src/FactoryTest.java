import static org.junit.Assert.*;
import java.awt.Font;
import org.junit.Test;

public class FactoryTest {

	//Font Factory Test
	@Test
	public void getFontTest() {
		FontFactory fontFactory = FontFactory.getFontFactoryInstance();
        Font fontA = fontFactory.getFont("HELVETICA", Font.ITALIC, 10);
        Font fontB = fontFactory.getFont("ARIAL", Font.BOLD, 12);
        RunArray runArray = new RunArray();
        runArray.addRun(0, 144,fontA);
        runArray.addRun(145, 211, fontB);
        Font actualFont = runArray.getFont(100);
        Font expectedFont = fontA;
        assertEquals(expectedFont, actualFont);
    }
	
	//Character Factory Test
	@Test
    public void getCharacterTest() {
		String text = "CharacterFactoryTest";
        char[] textArray = text.toCharArray();
        CharacterFactory charFactory = CharacterFactory.getCharacterFactoryInstance();
        TextCharacter actualTextChar = charFactory.getCharacter(textArray[2]);
        TextCharacter expectedTextChar = charFactory.getCharacter(textArray[4]);
        assertEquals(actualTextChar, expectedTextChar);
	}
}

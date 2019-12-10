import static org.junit.Assert.*;
import java.awt.Font;
import org.junit.Test;

public class WordProcessorTest {

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
        assertEquals(expectedFont.getFontName(), actualFont.getFontName());
        assertEquals(expectedFont.getSize(), actualFont.getSize());
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
	
	//Memory Utilization Test
	@Test
	public void checkMemoryUsage() {
		String text = "CS 635 Advanced Object-Oriented Design & Programming Fall Semester, 2018"
	    			+ "Doc 17 Mediator, Flyweight, Facade, Demeter, Active Object Nov 19,"
	    			+ "2019 Copyright ©, All rights reserved. 2019 SDSU & Roger Whitney, 5500 Campanile Drive, San Diego, CA 92182-7700 USA."
	    			+ "OpenContent"
	    			+ "(http://www.opencontent.org/opl.shtml) license defines the copyright on this document.";
		char[] textArray = text.toCharArray();
		
		double noOfBytesWithoutFlyWeight = new SizeofUtil() {
	        protected int create() {
            	for(int i = 0; i < textArray.length; i++) {
	                Font charFont = new Font("ARIAL", Font.ITALIC, 10);
	                TextCharacter textChar = new TextCharacter(textArray[i]);
	                textChar.setFont(charFont);
	            }
            	return 1;
        	}
        }.averageBytes();
        
        double noOfBytesWithFlyWeight =  new SizeofUtil() {
	           protected int create() {
	            FontFactory fontFactory = FontFactory.getFontFactoryInstance();
	            Font fontOne = fontFactory.getFont("HELVETICA", Font.ITALIC, 10);
	            Font fontTwo = fontFactory.getFont("ARIAL", Font.BOLD, 12);
	            RunArray runArray = new RunArray();
	            runArray.addRun(0, 145, fontOne); 
	            runArray.addRun(145, 211, fontTwo); 
	            for(int i = 0 ; i < textArray.length; i++) {
	                CharacterFactory characterFactory = CharacterFactory.getCharacterFactoryInstance();
	                TextCharacter textCharacter = characterFactory.getCharacter(textArray[i]); 
	                Font characterFont = runArray.getFont(i);
	                textCharacter.setFont(characterFont);
	            }
	            return 1;
	        }
        }.averageBytes();
        
        assertTrue(noOfBytesWithFlyWeight < noOfBytesWithoutFlyWeight);
	}
}

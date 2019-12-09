import java.awt.*;
import java.util.ArrayList;

public class FlyWeightWordProcessor {

    public static void main(String args[]) {
    	System.out.printf("The average size of FlyWeightWordProcessor is %.1f bytes%n", new SizeofUtil() {
        	String text = "CS 635 Advanced Object-Oriented Design & Programming Fall Semester, 2018"
	        			+ "Doc 17 Mediator, Flyweight, Facade, Demeter, Active Object Nov 19,"
	        			+ "2019 Copyright ©, All rights reserved. 2019 SDSU & Roger Whitney, 5500 Campanile Drive, San Diego, CA 92182-7700 USA."
	        			+ "OpenContent"
	        			+ "(http://www.opencontent.org/opl.shtml) license defines the copyright on this document.";
            ArrayList<TextCharacter> charList = null;
            char[] textArray = text.toCharArray();

            @Override
            protected int create() {
                charList = new ArrayList<TextCharacter>();
                FontFactory fontFactory = FontFactory.getFontFactoryInstance();
                Font fontOne = fontFactory.getFont("HELVETICA", Font.ITALIC, 10);
                Font fontTwo = fontFactory.getFont("ARIAL", Font.BOLD, 12);
                RunArray aRun = new RunArray();
                aRun.addRun(0, 145, fontOne); 
                aRun.addRun(145, 211, fontTwo); 
                for(int i = 0 ; i < textArray.length; i++) {
                    CharacterFactory characterFactory = CharacterFactory.getCharacterFactoryInstance();
                    TextCharacter textCharacter = characterFactory.getCharacter(textArray[i]); 
                    if(!charList.contains(textCharacter))
                        charList.add(textCharacter);
                }
                return 1;
            }
        }.averageBytes());
    }
}

import java.awt.*;

public class Client {

    public static void main(String args[]){
    	String text = "CS 635 Advanced Object-Oriented Design & Programming Fall Semester, 2018"
    			+ "Doc 17 Mediator, Flyweight, Facade, Demeter, Active Object Nov 19,"
    			+ "2019 Copyright ©, All rights reserved. 2019 SDSU & Roger Whitney, 5500 Campanile Drive, San Diego, CA 92182-7700 USA."
    			+ "OpenContent"
    			+ "(http://www.opencontent.org/opl.shtml) license defines the copyright on this document.";
    	char[] textArray = text.toCharArray();
        FontFactory fontFactory = FontFactory.getFontFactoryInstance();
        Font fontOne = fontFactory.getFont("HELVETICA", Font.ITALIC, 10);
        Font fontTwo = fontFactory.getFont("ARIAL", Font.BOLD, 12);
        RunArray runArray = new RunArray();
        runArray.addRun(0, 145, fontOne);
        //runArray.addRun(145, 211, fontTwo);
        runArray.appendRun(211, fontTwo);
        for(int i=0 ; i < text.length(); i++) {
            CharacterFactory charFactory = CharacterFactory.getCharacterFactoryInstance();
            TextCharacter textCharacter = charFactory.getCharacter(textArray[i]); 
            Font characterFont = runArray.getFont(i);
            textCharacter.setFont(characterFont);
        }
    }
}

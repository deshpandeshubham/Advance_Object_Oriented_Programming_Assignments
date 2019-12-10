import java.awt.*;
import java.util.ArrayList;

public class ConventionalWordProcessor {

    public static void main(String args[]) {
        double noOfBytes = new SizeofUtil() {
        	String text = "CS 635 Advanced Object-Oriented Design & Programming Fall Semester,"
	    				+ "2018 Doc 17 Mediator, Flyweight, Facade, Demeter, Active Object "
	    				+ "Nov 19 2019 Copyright ©, All rights reserved. 2019 SDSU & "
	    				+ "Roger Whitney, "
	    				+ "5500 Campanile Drive, San Diego, CA 92182-7700 USA."
	        			+ "OpenContent"
	        			+ "(http://www.opencontent.org/opl.shtml) "
	        			+ "license defines the copyright on this document.";
	        char[] textArray = text.toCharArray();
	        ArrayList<TextCharacter> textCharList = new ArrayList<>();
	        
	        @Override
	        protected int create() {
            	for(int i = 0; i < textArray.length; i++) {
	                Font charFont = new Font("ARIAL", Font.ITALIC, 10);
	                TextCharacter textChar = new TextCharacter(textArray[i]);
	                textCharList.add(textChar);
	                textChar.setFont(charFont);
	            }
            	return 1;
        	}
        }.averageBytes();
        
        System.out.println("Memory : " + noOfBytes);
    }
}



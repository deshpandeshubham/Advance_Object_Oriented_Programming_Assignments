import java.awt.*;
import java.util.ArrayList;

public class ConventionalWordProcessor {

    public static void main(String args[]) {
        System.out.printf("The average size of ConventionalWordProcessor is %.1f bytes%n", new SizeofUtil() {
        	String text = "CS 635 Advanced Object-Oriented Design & Programming Fall Semester, 2018"
        			+ "Doc 17 Mediator, Flyweight, Facade, Demeter, Active Object Nov 19,"
        			+ "2019 Copyright ©, All rights reserved. 2019 SDSU & Roger Whitney, 5500 Campanile Drive, San Diego, CA 92182-7700 USA."
        			+ "OpenContent"
        			+ "(http://www.opencontent.org/opl.shtml) license defines the copyright on this document.";
	        char[] textArray = text.toCharArray();
	        ArrayList<ConventionalTextChar> charList = null;
	        
	        @Override
	        protected int create() {
            	charList = new ArrayList<ConventionalTextChar>();
	            for(int i = 0; i < textArray.length; i++) {
	                Font charFont = new Font("ARIAL", Font.BOLD, 12);
	                ConventionalTextChar textCharacter = new ConventionalTextChar(textArray[i], charFont);
	                charList.add(textCharacter);
            	}
            	return 1;
        	}
        }.averageBytes());
    }

    static class ConventionalTextChar{
    	char uniCode;
        Font font;
        public ConventionalTextChar(char uniCode, Font font){
            this.uniCode = uniCode;
            this.font = font;
        }
    }
}



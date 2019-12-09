import java.awt.Font;

public class TextCharacter implements CharFlyWeight {
	private char charUnicode;
	
	public TextCharacter(char charUniCode) {
		this.charUnicode = charUniCode;
	}

	@Override
	public String setFont(Font font) {
		return "Character : " + this.charUnicode +"	"+"Font : "+font.toString().replace("java.awt.Font", "").replace("[", "").replace("]", "");
	}
}

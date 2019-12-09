import java.awt.Font;

public class CharacterFont {
	private int startIndex;
    private int endIndex;
    Font characterFont;

    public CharacterFont(int startIndex, int endIndex, Font characterFont) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.characterFont = characterFont;
    }

    public boolean contains(int index) {
        return (index >= startIndex && index <= endIndex);
    }
}
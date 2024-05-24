package P98.Exception;

import java.lang.Exception;

public class DeckFullException extends Exception {
    public DeckFullException() {
        super("Deck sudah penuh");
    }
}
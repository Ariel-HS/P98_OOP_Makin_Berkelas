package P98.Exception;

import java.lang.Exception;

public class WrongItemException extends Exception {
    public WrongItemException(String self) {
        super("Hewan/Tumbuhan tersebut milik " + self);
    }
}
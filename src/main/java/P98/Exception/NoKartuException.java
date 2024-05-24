package P98.Exception;

import java.lang.Exception;

public class NoKartuException extends Exception {
    public NoKartuException(String self) {
        super("Tidak ada kartu dengan nama " + self);
    }
}
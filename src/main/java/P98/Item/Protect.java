package P98.Item;

import P98.Exception.*;
import P98.Makhluk.*;
import P98.Player.*;

public class Protect extends Item {
    public Protect() {
        super("Protect");
    }

    public void getEffect(Makhluk m, Player p) throws WrongItemException {
        if (m.getPemilik() == p) {
            m.giveShield();
        } else {
            throw new WrongItemException("pemain lain");
        }
    }
}

package P98.Item;

import P98.Exception.*;
import P98.Makhluk.*;
import P98.Interface.*;
import P98.Player.*;

public class Trap extends Item {
    public Trap() {
        super("Trap");
    }

    public void interact(Makhluk m, Player p) throws WrongItemException {
        if (m.getPemilik() == p) {
            m.giveTrap();
        } else {
            throw new WrongItemException("pemain lain");
        }
    }
}

package P98.Item;

import P98.Exception.*;
import P98.Makhluk.*;
import P98.Interface.*;
import P98.Player.*;

public class Trap extends Item {
    public Trap(Player pem) {
        super("Trap", pem);
    }

    public void interact(Holdable m) throws WrongItemException {
        if (m.getPemilik() == this.getPemilik() && m instanceof Makhluk ma) {
            ma.giveTrap();
        } else {
            throw new WrongItemException("pemain lain");
        }
    }
}

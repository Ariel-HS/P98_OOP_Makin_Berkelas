package P98.Item;

import P98.Exception.*;
import P98.Makhluk.*;
import P98.Interface.*;
import P98.Player.*;


public class Protect extends Item {
    public Protect(Player pem) {
        super("Protect", pem);
    }

    public void interact(Holdable m) throws WrongItemException {
        if (m.getPemilik() == this.getPemilik() && m instanceof Makhluk ma) {
            ma.giveShield();
        } else {
            throw new WrongItemException("pemain lain");
        }
    }
}

package P98.Item;

import P98.Exception.*;
import P98.Makhluk.*;
import P98.Interface.*;
import P98.Player.*;

public class Trap extends Item {
    public Trap() {
        super("Trap");
    }

    public Trap(Player pem) {
        super("Trap", pem);
    }

    public void interact(Holdable m) throws WrongItemException {
        if (m.getPemilik() == this.getPemilik() && m instanceof Makhluk) {
            Makhluk ma = (Makhluk) m;
            ma.addItem(this);
            ma.giveTrap();
        } else {
            throw new WrongItemException("pemain lain");
        }
    }

    public Holdable turnToHoldable(Player p) {
        return new Trap(p);
    }
}

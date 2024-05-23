package P98.Item;

import P98.Exception.*;
import P98.Makhluk.*;
import P98.Makhluk.Tumbuhan.*;
import P98.Player.*;
import P98.Interface.Holdable;

public class Accelerate extends Item {
    public Accelerate() {
        super("Accelerate");
    }

    public void interact(Makhluk m, Player p) throws WrongItemException {
        if (m.getPemilik() == p) {
            if (m instanceof Tumbuhan) {
                m.setUnitPanen(m.getUnitPanen() + 2);
            } else {
                m.setUnitPanen(m.getUnitPanen() + 8);
            }
        } else {
            throw new WrongItemException("pemain lain");
        }
    }

    public Holdable turnToHoldable() {
        return new Accelerate();
    }
}
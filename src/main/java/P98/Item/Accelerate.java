package P98.Item;

import P98.Exception.*;
import P98.Makhluk.*;
import P98.Makhluk.Hewan.Hewan;
import P98.Interface.*;
import P98.Makhluk.Tumbuhan.*;
import P98.Player.*;
import P98.Interface.Holdable;

public class Accelerate extends Item {
    public Accelerate() {
        super("Accelerate");
    }

    public Accelerate(Player pem) {
        super("Accelerate", pem);
    }

    public void interact(Holdable m) throws WrongItemException {
        if (m.getPemilik() == this.getPemilik()) {
            if (m instanceof Tumbuhan) {
                Tumbuhan t = (Tumbuhan) m;
                t.addItem(this);
                t.setUnitPanen(t.getUnitPanen() + 2);
            } else if (m instanceof Hewan) {
                Hewan h = (Hewan) m;
                h.addItem(this);
                h.setUnitPanen(h.getUnitPanen() + 8);
            }
        } else {
            throw new WrongItemException("pemain lain");
        }
    }

    public Holdable turnToHoldable() {
        return new Accelerate(getPemilik());
    }
}
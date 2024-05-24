package P98.Item;

import P98.Exception.*;
import P98.Makhluk.*;
import P98.Makhluk.Hewan.Hewan;
import P98.Interface.*;
import P98.Makhluk.Tumbuhan.*;
import P98.Player.Player;

public class Delay extends Item {
    public Delay(Player pem) {
        super("Delay", pem);
    }

    public void interact(Holdable m) throws WrongItemException {
        if (m.getPemilik() == this.getPemilik()) {
            if (m instanceof Tumbuhan) {
                Tumbuhan t = (Tumbuhan) m;
                t.addItem(this);
                t.setUnitPanen(t.getUnitPanen() - 2);
                if (t.getUnitPanen() < 0 ) t.setUnitPanen(0);
            } else if (m instanceof Hewan) {
                Hewan h = (Hewan) m;
                h.addItem(this);
                h.setUnitPanen(h.getUnitPanen() - 5);
                if (h.getUnitPanen() < 0 ) h.setUnitPanen(0);
            }
        } else {
            throw new WrongItemException("pemain lain");
        }
    }
}

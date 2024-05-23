package P98.Item;

import P98.Exception.*;
import P98.Makhluk.*;
import P98.Interface.*;
import P98.Makhluk.Tumbuhan.*;
// import P98.Player.*;
// import P98.GameManager
import P98.Player.Player;

public class Delay extends Item {
    public Delay() {
        super("Delay");
    }

    public void interact(Makhluk m, Player p) throws WrongItemException {
        if (m.getPemilik() == p) {
            if (m instanceof Tumbuhan) {
                m.setUnitPanen(m.getUnitPanen() - 2);
                if (m.getUnitPanen() < 0 ) m.setUnitPanen(0);
            } else {
                m.setUnitPanen(m.getUnitPanen() - 5);
                if (m.getUnitPanen() < 0 ) m.setUnitPanen(0);
            }
        } else {
            throw new WrongItemException("pemain lain");
        }
    }

    public Holdable turnToHoldable() {
        return new Delay();
    }
}

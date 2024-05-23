package P98.Item;

import P98.Exception.*;
import P98.Makhluk.*;
import P98.Interface.*;
// import P98.Player.*;
// import P98.GameManager

public class Delay extends Item {
    public Delay() {
        super("Delay");
    }

    public void interact(Makhluk m) throws WrongItemException {
        Player p = GameManager.getCurrentPlayer();
        if (m.getPemilik() == p) {
            if (m.istanceof(Tumbuhan)) {
                m.setUnit(m.getUnit() - 2);
                if (m.getUnit() < 0 ) m.setUnit(0);
            } else {
                m.setUnit(m.getUnit() - 5);
                if (m.getUnit() < 0 ) m.setUnit(0);
            }
        } else {
            throw new WrongItemException("pemain lain");
        }
    }

    public Holdable turnToHoldable() {
        return new Delay();
    }
}

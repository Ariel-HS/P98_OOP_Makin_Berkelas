package P98.Item;

import P98.Exception.*;
import P98.Makhluk.*;
import P98.Interface.*;
// import P98.Player.*;
// import P98.GameManager

public class Accelerate extends Item {
    public Accelerate() {
        super("Accelerate");
    }

    public void interact(Makhluk m) throws WrongItemException {
        Player p = GameManager.getCurrentPlayer();
        if (m.getPemilik() == p) {
            if (m.instanceof(Tumbuhan)) {
                m.setUnit(m.getUnit() + 2);
            } else {
                m.setUnit(m.getUnit() + 8);
            }
        } else {
            throw new WrongItemException("pemain lain");
        }
    }

    public Holdable turnToHoldable() {
        return new Accelerate();
    }
}
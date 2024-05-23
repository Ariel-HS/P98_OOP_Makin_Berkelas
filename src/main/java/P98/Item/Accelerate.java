package P98.Item;

import P98.Exception.*;
import P98.Makhluk.*;
import P98.Player.*;
import P98.Tumbuhan.*;;

public class Accelerate extends Item {
    public Accelerate() {
        super("Accelerate");
    }

    public void getEffect(Makhluk m, Player p) throws WrongItemException {
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
}
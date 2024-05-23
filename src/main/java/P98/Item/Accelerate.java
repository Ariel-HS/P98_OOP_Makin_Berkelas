package P98.Item;

import P98.Exception.*;

public class Accelerate extends Item {
    public Accelerate() {
        super("Accelerate");
    }

    public void getEffect(Mahkluk m, Player p) throws WrongItemException {
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
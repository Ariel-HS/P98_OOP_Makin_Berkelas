package P98.Item;

import P98.Exception.*;
import P98.Makhluk.*;
import P98.Player.*;

public class Delay extends Item {
    public Delay() {
        super("Delay");
    }

    public void getEffect(Mahkluk m, Player p) throws WrongItemException {
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
}

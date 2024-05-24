package P98.Item;

import P98.Exception.*;
import P98.Makhluk.Tumbuhan.Tumbuhan;
import P98.Interface.*;
import P98.Player.*;

public class Destroy extends Item {
    public Destroy(Player pem) {
        super("Destroy", pem);
    }

    public void interact(Holdable m) throws WrongItemException {
        if (m.getPemilik() != this.getPemilik()) {
            m = new Tumbuhan();
        } else {
            throw new WrongItemException("Anda");
        }
    }

    public Holdable turnToHoldable() {
        return new Destroy(getPemilik());
    }
}

package P98.Item;

import P98.Exception.*;
import P98.Interface.Holdable;

public class Destroy extends Item {
    public Destroy() {
        super("Destroy");
    }

    public void getEffect(Mahkluk m, Player p) throws WrongItemException {
        if (m.getPemilik() != p) {
            m = null;
        } else {
            throw new WrongItemException("Anda");
        }
    }

    public Holdable turnToHoldable() {
        return new Destroy();
    }
}

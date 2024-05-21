package P98.Item;

import P98.Exception.*;

public class Protect extends Item {
    public Protect() {
        super("Protect");
    }

    public void getEffect(Mahkluk m, Player p) throws WrongItemException {
        if (m.getPemilik() == p) {
            m.giveShield();
        } else {
            throw new WrongItemException("pemain lain");
        }
    }
}

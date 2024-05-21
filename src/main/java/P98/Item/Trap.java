package P98.Item;

import P98.Exception.*;

public class Trap extends Item {
    public Trap() {
        super("Trap");
    }

    public void getEffect(Mahkluk m, Player p) throws WrongItemException {
        if (m.getPemilik() == p) {
            m.giveTrap();
        } else {
            throw new WrongItemException("pemain lain");
        }
    }
}

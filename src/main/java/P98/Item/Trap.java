package P98.Item;

import P98.Exception.*;
import P98.Makhluk.*;
import P98.Interface.*;
// import P98.Player.*;
// import P98.GameManager

public class Trap extends Item {
    public Trap() {
        super("Trap");
    }

    public void interact(Mahkluk m) throws WrongItemException {
        Player p = GameManager.getCurrentPlayer();
        if (m.getPemilik() == p) {
            m.giveTrap();
        } else {
            throw new WrongItemException("pemain lain");
        }
    }

    public Holdable turnToHoldable() {
        return new Trap();
    }
}

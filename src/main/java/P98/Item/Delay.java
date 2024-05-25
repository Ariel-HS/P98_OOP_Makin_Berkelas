package P98.Item;

import P98.Exception.*;
import P98.Makhluk.*;
<<<<<<< HEAD
import P98.Interface.*;
// import P98.Player.*;
// import P98.GameController

public class Delay extends Item {
    public Delay() {
        super("Delay");
    }

    // public void interact(Makhluk m) throws WrongItemException {
    //     Player p = GameController.getCurrentPlayer();
    //     if (m.getPemilik() == p) {
    //         if (m.istanceof(Tumbuhan)) {
    //             m.setUnit(m.getUnit() - 2);
    //             if (m.getUnit() < 0 ) m.setUnit(0);
    //         } else {
    //             m.setUnit(m.getUnit() - 5);
    //             if (m.getUnit() < 0 ) m.setUnit(0);
    //         }
    //     } else {
    //         throw new WrongItemException("pemain lain");
    //     }
    // }

    public Holdable turnToHoldable() {
        return new Delay();
    }

    public void print() {
        System.out.println(this.getNama());
=======
import P98.Makhluk.Hewan.Hewan;
import P98.Interface.*;
import P98.Makhluk.Tumbuhan.*;
import P98.Player.Player;

public class Delay extends Item {
    public Delay(Player pem) {
        super("Delay", pem);
    }

    public void interact(Holdable m) throws WrongItemException {
        if (m.getPemilik() == this.getPemilik()) {
            if (m instanceof Tumbuhan) {
                Tumbuhan t = (Tumbuhan) m;
                t.addItem(this);
                t.setUnitPanen(t.getUnitPanen() - 2);
                if (t.getUnitPanen() < 0 ) t.setUnitPanen(0);
            } else if (m instanceof Hewan) {
                Hewan h = (Hewan) m;
                h.addItem(this);
                h.setUnitPanen(h.getUnitPanen() - 5);
                if (h.getUnitPanen() < 0 ) h.setUnitPanen(0);
            }
        } else {
            throw new WrongItemException("pemain lain");
        }
    }

    public Holdable turnToHoldable() {
        return new Delay(getPemilik());
>>>>>>> refactor-banyak
    }
}

package P98.Item;

import P98.Exception.*;
<<<<<<< HEAD
import P98.Makhluk.*;
import P98.Interface.*;
// import P98.Player.*;
// import P98.GameController

public class Destroy extends Item {
    public Destroy() {
        super("Destroy");
    }

    // public void interact(Makhluk m) throws WrongItemException {
    //     Player p = GameController.getCurrentPlayer();
    //     if (m.getPemilik() != p) {
    //         m = null;
    //     } else {
    //         throw new WrongItemException("Anda");
    //     }
    // }

    public Holdable turnToHoldable() {
        return new Destroy();
    }

    public void print() {
        System.out.println(this.getNama());
=======
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
>>>>>>> refactor-banyak
    }
}

package P98.Item;

import P98.Exception.*;
import P98.Makhluk.*;
import P98.Interface.*;
<<<<<<< HEAD
// import P98.Player.*;
// import P98.GameController

public class Trap extends Item {
    public Trap() {
        super("Trap");
    }

    // public void interact(Mahkluk m) throws WrongItemException {
    //     Player p = GameController.getCurrentPlayer();
    //     if (m.getPemilik() == p) {
    //         m.giveTrap();
    //     } else {
    //         throw new WrongItemException("pemain lain");
    //     }
    // }

    // public Holdable turnToHoldable() {
    //     return new Trap();
    // }

    public Holdable turnToHoldable() {
        return new Trap();
    }

    public void print() {
        System.out.println(this.getNama());
=======
import P98.Player.*;

public class Trap extends Item {
    public Trap(Player pem) {
        super("Trap", pem);
    }

    public void interact(Holdable m) throws WrongItemException {
        if (m.getPemilik() == this.getPemilik() && m instanceof Makhluk) {
            Makhluk ma = (Makhluk) m;
            ma.addItem(this);
            ma.giveTrap();
        } else {
            throw new WrongItemException("pemain lain");
        }
    }

    public Holdable turnToHoldable() {
        return new Trap(getPemilik());
>>>>>>> refactor-banyak
    }
}

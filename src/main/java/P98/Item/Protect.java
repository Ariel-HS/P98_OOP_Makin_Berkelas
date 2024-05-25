package P98.Item;

import P98.Exception.*;
import P98.Makhluk.*;
import P98.Interface.*;
<<<<<<< HEAD
// import P98.Player.*;
// import P98.GameController

public class Protect extends Item {
    public Protect() {
        super("Protect");
    }

    // public void interact(Makhluk m) throws WrongItemException {
    //     Player p = GameController.getCurrentPlayer();
        
    //     if (m.getPemilik() == p) {
    //         m.giveShield();
    //     } else {
    //         throw new WrongItemException("pemain lain");
    //     }
    // }

    public Holdable turnToHoldable() {
        return new Protect();
    }

    public void print() {
        System.out.println(this.getNama());
=======
import P98.Player.*;


public class Protect extends Item {
    public Protect(Player pem) {
        super("Protect", pem);
    }

    public void interact(Holdable m) throws WrongItemException {
        if (m.getPemilik() == this.getPemilik() && m instanceof Makhluk) {
            Makhluk ma = (Makhluk) m;
            ma.addItem(this);
            ma.giveShield();
        } else {
            throw new WrongItemException("pemain lain");
        }
    }

    public Holdable turnToHoldable() {
        return new Protect(getPemilik());
>>>>>>> refactor-banyak
    }
}

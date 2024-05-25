package P98.Item;

import P98.Exception.*;
import P98.Makhluk.*;
<<<<<<< HEAD
import P98.Produk.*;
import P98.Interface.*;
// import P98.Player.*;
// import P98.GameController

public class InstantHarvest extends Item {
    public InstantHarvest() {
        super("Instant Harvest");
    }

    // public void interact(Makhluk m) throws WrongItemException {
    //     if (m.getPemilik() == p) {
    //         Produk pr = m.harvest();
    //         // kasih produk ke pemain
    //     } else {
    //         throw new WrongItemException("pemain lain");
    //     }
    // }

    public Holdable turnToHoldable() {
        return new InstantHarvest();
    }

    public void print() {
        System.out.println(this.getNama());
=======
import P98.Makhluk.Tumbuhan.Tumbuhan;
import P98.Produk.*;
import P98.Interface.*;
import P98.Player.*;
// import P98.GameManager

public class InstantHarvest extends Item {
    public InstantHarvest(Player pem) {
        super("Instant Harvest", pem);
    }

    public void interact(Holdable m) throws WrongItemException {
        if (m.getPemilik() == this.getPemilik()) {
            if (m instanceof Produk) {
                Produk pr = (Produk) m;
                try {
                    this.getPemilik().addToDeck(pr);
                }
                catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                finally {
                    m = new Tumbuhan();
                }
            }
        } else {
            throw new WrongItemException("pemain lain");
        }
    }

    public Holdable turnToHoldable() {
        return new InstantHarvest(getPemilik());
>>>>>>> refactor-banyak
    }
}

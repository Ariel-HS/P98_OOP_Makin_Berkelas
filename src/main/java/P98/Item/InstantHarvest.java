package P98.Item;

import P98.Exception.*;
import P98.Makhluk.*;
import P98.Makhluk.Tumbuhan.Tumbuhan;
import P98.Produk.*;
import P98.Interface.*;
import P98.Player.*;
// import P98.GameManager

public class InstantHarvest extends Item {
    public InstantHarvest() {
        super("Instant Harvest");
    }

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

    public Holdable turnToHoldable(Player p) {
        return new InstantHarvest(p);
    }
}

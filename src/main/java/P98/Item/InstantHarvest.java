package P98.Item;

import P98.Exception.*;
import P98.Makhluk.*;
import P98.Makhluk.Tumbuhan.Tumbuhan;
import P98.Produk.*;
import P98.Interface.*;
import P98.Player.*;
// import P98.GameManager

public class InstantHarvest extends Item {
    public InstantHarvest(Player pem) {
        super("Instant Harvest", pem);
    }

    public void interact(Holdable m) {
        if (m.getPemilik() == this.getPemilik()) {
            if (m instanceof Produk pr) {
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
}

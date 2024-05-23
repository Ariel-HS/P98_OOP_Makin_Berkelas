package P98.Item;

import P98.Exception.*;
import P98.Produk.*;
import P98.Interface.*;
import P98.Player.*;
// import P98.GameManager

public class InstantHarvest extends Item {
    public InstantHarvest() {
        super("Instant Harvest");
    }

    public void interact(Makhluk m, Player p) throws WrongItemException {
        if (m.getPemilik() == p) {
            Produk p = m.harvest();
            // kasih produk ke pemain
        } else {
            throw new WrongItemException("pemain lain");
        }
    }
}

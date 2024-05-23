package P98.Item;

import P98.Exception.*;
import P98.Interface.Holdable;
import P98.Produk.*;

public class InstantHarvest extends Item {
    public InstantHarvest() {
        super("Instant Harvest");
    }

    public void getEffect(Mahkluk m, Player p) throws WrongItemException {
        if (m.getPemilik() == p) {
            Produk pr = m.harvest();
            // kasih produk ke pemain
        } else {
            throw new WrongItemException("pemain lain");
        }
    }

    public Holdable turnToHoldable() {
        return new InstantHarvest();
    }
}

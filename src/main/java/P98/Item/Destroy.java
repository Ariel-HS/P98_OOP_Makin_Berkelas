package P98.Item;

import P98.Exception.*;
import P98.Makhluk.*;
import P98.Interface.*;
import P98.Player.*;

public class Destroy extends Item {
    public Destroy() {
        super("Destroy");
    }

    public void interact(Makhluk m, Player p) throws WrongItemException {
        if (m.getPemilik() != p) {
            // bunuh m
        } else {
            throw new WrongItemException("Anda");
        }
    }
}

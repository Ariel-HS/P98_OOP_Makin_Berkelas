package P98.Interface;

import P98.Exception.WrongItemException;
import P98.Makhluk.*;
import P98.Player.*;

public interface Holdable {
    public Holdable turnToHoldable();

    public void interact(Makhluk m, Player p) throws WrongItemException;

    public String getNama();
}

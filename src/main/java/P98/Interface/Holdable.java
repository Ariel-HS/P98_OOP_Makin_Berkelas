package P98.Interface;

import P98.Exception.WrongItemException;
import P98.Player.*;

public interface Holdable {
    public String getNama();
    public Player getPemilik();
    public void interact(Holdable h) throws WrongItemException;
    public Holdable turnToHoldable();
}

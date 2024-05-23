package P98.Interface;

import P98.Exception.WrongItemException;
import P98.Makhluk.*;

public interface Holdable {
    public Holdable turnToHoldable();

    public void interact(Makhluk m) throws WrongItemException;
}

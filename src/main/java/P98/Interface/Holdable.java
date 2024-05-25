package P98.Interface;

<<<<<<< HEAD
public interface Holdable {
    Holdable turnToHoldable();
    void print();
    public String getNama(); // for image
}
=======
import P98.Exception.WrongItemException;
import P98.Player.*;

public interface Holdable {
    public String getNama();
    public Player getPemilik();
    public void interact(Holdable h) throws WrongItemException;
    public Holdable turnToHoldable();
}
>>>>>>> refactor-banyak

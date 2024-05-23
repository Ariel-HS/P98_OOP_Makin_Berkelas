package P98.Produk;

import P98.Exception.WrongItemException;
import P98.Interface.Holdable;
import P98.Makhluk.*;
// import P98.GameManager.*;
// import P98.Player.*;

public class ProdukHewan extends Produk {
    public ProdukHewan() {
        super();
    }

    public ProdukHewan(String nama, int harga, int bobot) {
        super(nama, harga, bobot);
    }

    public ProdukHewan(ProdukHewan other) {
        this(other.getNama(), other.getHarga(), other.getBobot());
    }

    public boolean tryEat(String s) {
        if (s.equals("Herbivora")) return false;
        return true;
    }

    public void interact(Makhluk m) throws WrongItemException {
        Player p = GameManager.getCurrentPlayer();
        if (m.getPemilik() != p) {
            throw new WrongItemException("pemain lain");
        }
        m.makan(this);
    }

    public Holdable turnToHoldable() {
        return new ProdukHewan(this);
    }

    public Produk turnToProduk() {
        return new ProdukHewan(this);
    }
}
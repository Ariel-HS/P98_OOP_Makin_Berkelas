package P98.Produk;

import P98.Exception.WrongItemException;
import P98.Interface.Holdable;
import P98.Makhluk.*;
import P98.Player.*;

public class ProdukTumbuhan extends Produk {
    public ProdukTumbuhan() {
        super();
    }

    public ProdukTumbuhan(String nama, int harga, int bobot) {
        super(nama, harga, bobot);
    }

    public ProdukTumbuhan(ProdukTumbuhan ohter) {
        this(ohter.getNama(), ohter.getHarga(), ohter.getBobot());
    }

    public boolean tryEat(String s) {
        if (s.equals("Karnivora")) return false;
        return true;
    }

    public Holdable turnToHoldable() {
        return new ProdukTumbuhan(this);
    }

    public void interact(Makhluk m, Player p) throws WrongItemException {
        if (m.getPemilik() != p) {
            throw new WrongItemException("pemain lain");
        }
        m.makan(this);
    }

    public Produk turnToProduk() {
        return new ProdukTumbuhan(this);
    }
}
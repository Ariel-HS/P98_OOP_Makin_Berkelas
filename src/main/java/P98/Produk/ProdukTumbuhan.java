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
        super(nama,harga,bobot);
    }

    public ProdukTumbuhan(String nama, Player pem, int harga, int bobot) {
        super(nama, pem, harga, bobot);
    }

    public ProdukTumbuhan(ProdukTumbuhan ohter) {
        this(ohter.getNama(), ohter.getPemilik(), ohter.getHarga(), ohter.getBobot());
    }

    public boolean tryEat(String s) {
        if (s.equals("Karnivora")) return false;
        return true;
    }

    public void interact(Holdable m) throws WrongItemException {
        if (m.getPemilik() == this.getPemilik() && m instanceof Makhluk) {
            Makhluk ma = (Makhluk) m;
            ma.makan(this);
        }
        throw new WrongItemException("pemain lain");
    }

    public Produk turnToProduk() {
        return new ProdukTumbuhan(this);
    }

    public Holdable turnToHoldable(Player p) {
        return new ProdukTumbuhan(this.getNama(), p, this.getHarga(), this.getBobot());
    }
}
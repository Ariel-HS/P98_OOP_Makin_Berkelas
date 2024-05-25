package P98.Produk;

<<<<<<< HEAD
import P98.Interface.Holdable;
=======
import P98.Exception.WrongItemException;
import P98.Interface.Holdable;
import P98.Makhluk.*;
import P98.Player.*;
>>>>>>> refactor-banyak

public class ProdukTumbuhan extends Produk {
    public ProdukTumbuhan() {
        super();
    }

<<<<<<< HEAD
    public ProdukTumbuhan(String nama, int harga, int bobot) {
        super(nama, harga, bobot);
=======
    public ProdukTumbuhan(String nama, Player pem, int harga, int bobot) {
        super(nama, pem, harga, bobot);
    }

    public ProdukTumbuhan(ProdukTumbuhan ohter) {
        this(ohter.getNama(), ohter.getPemilik(), ohter.getHarga(), ohter.getBobot());
>>>>>>> refactor-banyak
    }

    public boolean tryEat(String s) {
        if (s.equals("Karnivora")) return false;
        return true;
    }

<<<<<<< HEAD
    public Holdable turnToHoldable() {
        return new ProdukTumbuhan(this.getNama(), this.getHarga(), this.getBobot());    
    }

    public void print() {
        System.out.println(this.getNama());
=======
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

    public Holdable turnToHoldable() {
        return new ProdukTumbuhan(this);
>>>>>>> refactor-banyak
    }
}
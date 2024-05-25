package P98.Produk;

<<<<<<< HEAD
import P98.Interface.Holdable;
=======
import P98.Exception.WrongItemException;
import P98.Interface.Holdable;
import P98.Makhluk.*;
import P98.Player.*;
>>>>>>> refactor-banyak

public class ProdukHewan extends Produk {
    public ProdukHewan() {
        super();
    }

<<<<<<< HEAD
    public ProdukHewan(String nama, int harga, int bobot) {
        super(nama, harga, bobot);
=======
    public ProdukHewan(String nama, Player pem, int harga, int bobot) {
        super(nama, pem, harga, bobot);
    }

    public ProdukHewan(ProdukHewan other) {
        this(other.getNama(), other.getPemilik(), other.getHarga(), other.getBobot());
>>>>>>> refactor-banyak
    }

    public boolean tryEat(String s) {
        if (s.equals("Herbivora")) return false;
        return true;
    }

<<<<<<< HEAD
    public Holdable turnToHoldable() {
        return new ProdukHewan(this.getNama(), this.getHarga(), this.getBobot());
    }

    public void print() {
        System.out.println(this.getNama());
    }
}

=======
    public void interact(Holdable m) throws WrongItemException {
        if (m.getPemilik() == this.getPemilik() && m instanceof Makhluk) {
            Makhluk ma = (Makhluk) m;
            ma.makan(this);
        }
        throw new WrongItemException("pemain lain");
    }

    public Holdable turnToHoldable() {
        return new ProdukHewan(this);
    }

    public Produk turnToProduk() {
        return new ProdukHewan(this);
    }
}
>>>>>>> refactor-banyak

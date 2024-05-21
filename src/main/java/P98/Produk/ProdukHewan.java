package P98.Produk;

public class ProdukHewan extends Produk {
    public ProdukHewan() {
        super();
    }

    public ProdukHewan(String nama, int harga, int bobot) {
        super(nama, harga, bobot);
    }

    public boolean tryEat(String s) {
        if (s.equals("Herbivora")) return false;
        return true;
    }
}


package P98.Produk;

import P98.Interface.Holdable;

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

    public Holdable turnToHoldable() {
        return new ProdukHewan(this.getNama(), this.getHarga(), this.getBobot());
    }

    public void print() {
        System.out.println(this.getNama());
    }
}


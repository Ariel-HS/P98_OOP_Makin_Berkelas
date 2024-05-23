package P98.Produk;

import P98.Interface.Holdable;

public class ProdukTumbuhan extends Produk {
    public ProdukTumbuhan() {
        super();
    }

    public ProdukTumbuhan(String nama, int harga, int bobot) {
        super(nama, harga, bobot);
    }

    public boolean tryEat(String s) {
        if (s.equals("Karnivora")) return false;
        return true;
    }

    public Holdable turnToHoldable() {
        return new ProdukTumbuhan(this.getNama(), this.getHarga(), this.getBobot());    
    }

    public void print() {
        System.out.println(this.getNama());
    }
}
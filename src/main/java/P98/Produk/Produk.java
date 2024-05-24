package P98.Produk;

import P98.Interface.Holdable;
import P98.Player.Player;

public abstract class Produk implements Holdable {
    private String nama;
    private Player pemilik;
    private int harga;
    private int bobot;

    public Produk() {
        nama = "";
        pemilik = null;
        harga = -1;
        bobot = -1;
    }

    public Produk(String nama, Player pemilik, int harga, int bobot) {
        this.nama = nama;
        this.pemilik = pemilik;
        this.harga = harga;
        this.bobot = bobot;
    }

    // Getter
    public String getNama() { return nama; }
    public Player getPemilik() { return pemilik; }
    public void setPemilik(Player n) { pemilik = n; }
    public int getHarga() { return harga; }
    public int getBobot() { return bobot; }

    abstract public boolean tryEat(String s);
    abstract public Holdable turnToHoldable();
    abstract public Produk turnToProduk();
}
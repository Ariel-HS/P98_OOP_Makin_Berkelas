package P98.Produk;

import P98.Interface.Holdable;

public abstract class Produk implements Holdable {
    private String nama;
    private int harga;
    private int bobot;

    public Produk() {
        nama = "";
        harga = -1;
        bobot = -1;
    }

    public Produk(String nama, int harga, int bobot) {
        this.nama = nama;
        this.harga = harga;
        this.bobot = bobot;
    }

    // Getter
    public String getNama() { return nama; }
    public int getHarga() { return harga; }
    public int getBobot() { return bobot; }

    abstract boolean tryEat(String s);
}
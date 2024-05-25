package P98.Produk;

<<<<<<< HEAD
import P98.Interface.*;

public abstract class Produk implements Holdable {
    private String nama;
=======
import P98.Interface.Holdable;
import P98.Player.Player;

public abstract class Produk implements Holdable {
    private String nama;
    private Player pemilik;
>>>>>>> refactor-banyak
    private int harga;
    private int bobot;

    public Produk() {
        nama = "";
<<<<<<< HEAD
=======
        pemilik = null;
>>>>>>> refactor-banyak
        harga = -1;
        bobot = -1;
    }

<<<<<<< HEAD
    public Produk(String nama, int harga, int bobot) {
        this.nama = nama;
=======
    public Produk(String nama, Player pemilik, int harga, int bobot) {
        this.nama = nama;
        this.pemilik = pemilik;
>>>>>>> refactor-banyak
        this.harga = harga;
        this.bobot = bobot;
    }

    // Getter
    public String getNama() { return nama; }
<<<<<<< HEAD
    public int getHarga() { return harga; }
    public int getBobot() { return bobot; }

    public abstract boolean tryEat(String s);
=======
    public Player getPemilik() { return pemilik; }
    public int getHarga() { return harga; }
    public int getBobot() { return bobot; }

    abstract public boolean tryEat(String s);
    abstract public Produk turnToProduk();
>>>>>>> refactor-banyak
}
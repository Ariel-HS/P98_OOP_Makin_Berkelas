package P98.Produk;

abstract class Produk {
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

class ProdukHewan extends Produk {
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

class ProdukTumbuhan extends Produk {
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
}
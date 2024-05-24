package tc;

public class Produk implements Holdable {
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
    
    @Override
    public void exampleMethod() {
    	System.out.println("ignore this");
    }

    @Override
    public void interact(Holdable u) {
    	System.out.println("ignore this");
    }
    // Getter
    @Override
    public String getNama() { return nama; }
    public int getHarga() { return harga; }
    public int getBobot() { return bobot; }

    //abstract public boolean tryEat(String s);
    //abstract public Produk turnToProduk();
}

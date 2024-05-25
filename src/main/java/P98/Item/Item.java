package P98.Item;

import P98.Interface.*;
<<<<<<< HEAD

public abstract class Item implements Holdable {
    private String nama;

    public Item(String nama) {
        this.nama = nama;
    }

=======
import P98.Player.Player;

public abstract class Item implements Holdable  {
    private String nama;
    private Player pemilik;

    public Item(String nama, Player pemilik) {
        this.nama = nama;
        this.pemilik = pemilik;
    }

    public Player getPemilik() { return pemilik; }

>>>>>>> refactor-banyak
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
}

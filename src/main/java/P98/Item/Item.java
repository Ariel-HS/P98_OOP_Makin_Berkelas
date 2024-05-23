package P98.Item;

import P98.Interface.*;

public abstract class Item implements Holdable {
    private String nama;

    public Item(String nama) {
        this.nama = nama;
    }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
}

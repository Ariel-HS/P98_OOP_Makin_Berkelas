package P98.Makhluk;

import P98.Interface.*;
import P98.Item.*;
import P98.Produk.*;
import java.util.*;
import java.awt.Point;

public abstract class Makhluk implements Holdable {
    private String name;
    private Point posisi;
    private int unitPanen;
    private int batasPanen;
    private Map<String, Integer> items;
    private Produk produk;
    private Player pemilik;
    private boolean hasTrap;
    private boolean hasProtect;

    public Makhluk() {
        name = "";
        posisi = new Point();
        unitPanen = -1;
        batasPanen = -1;
        items = new HashMap<>();
        hasTrap = false;
        hasProtect = false;
    }

    public Makhluk(String _nama, int x_pos, int y_pos, int _unitPanen, int _batasPanen, Produk p, Player pem) {
        name = _nama;
        posisi = new Point(x_pos, y_pos);
        unitPanen = _unitPanen;
        batasPanen = _batasPanen;
        produk = p;
        items = new HashMap<>();
        hasTrap = false;
        hasProtect = false;
        pemilik = pem;
    }
 
    public String getNama() { return name; }
    public Point getPos() { return posisi; }
    public int getUnitPanen() { return unitPanen; }
    public void setUnitPanen(int unitPanen) { this.unitPanen = unitPanen; }
    public int getBatasPanen() { return batasPanen; }
    public Produk harvest() { return produk; }
    public Player getPemilik() { return pemilik; }
    public boolean isProtected() { return hasProtect; }
    public boolean hasTrap() { return hasTrap; }
    public Map<String, Integer> getItems() { return items; }

    public void setPos(int x_pos, int y_pos) { posisi = new Point(x_pos, y_pos); }
    public void addItem(Item x) {
        items.put(x.getNama(), items.get(x.getNama()) + 1);
    }
    public void giveTrap() { hasProtect = true; }
    public void giveShield() { hasProtect = true; }

    abstract protected void nextTurn();
    abstract public void makan(Produk p);
    abstract public Makhluk turnToMakhluk();
}

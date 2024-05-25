package P98.Makhluk;

import P98.Exception.GaBisaMakanException;
import P98.Interface.*;
import P98.Item.*;
import P98.Player.Player;
import P98.Produk.*;
import java.util.*;
import java.awt.Point;

public abstract class Makhluk implements Holdable {
    private String name;
    private Point posisi;
    private int unitAsli; // Tanpa tambahan item
    private int unitPanen; // Dengan tambahan item
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
        unitAsli = 0;
        batasPanen = -1;
        items = new HashMap<>();
        hasTrap = false;
        hasProtect = false;
    }

    public Makhluk(String _nama, int x_pos, int y_pos, int _unitPanen, int _batasPanen, Produk p) {
        name = _nama;
        posisi = new Point(x_pos, y_pos);
        unitPanen = _unitPanen;
        unitAsli = 0;
        batasPanen = _batasPanen;
        produk = p;
        items = new HashMap<>();
        hasTrap = false;
        hasProtect = false;
    }

    public Makhluk(String _nama, int x_pos, int y_pos, int _unitPanen, int _batasPanen, Produk p, Player pem) {
        name = _nama;
        posisi = new Point(x_pos, y_pos);
        unitPanen = _unitPanen;
        unitAsli = 0;
        batasPanen = _batasPanen;
        produk = p;
        items = new HashMap<>();
        hasTrap = false;
        hasProtect = false;
        pemilik = pem;
    }
 
    public String getNama() { return name; }
    public Point getPos() { return posisi; }
    public int getUnitAsli() { return unitAsli; }
    public void setUnitAsli(int n) { unitAsli = n; }
    public int getUnitPanen() { return unitPanen; }
    public void setUnitPanen(int unitPanen) { this.unitPanen = unitPanen; }
    public int getBatasPanen() { return batasPanen; }
    public Produk harvest() { return produk; }
    public Player getPemilik() { return pemilik; }
    public boolean isProtected() { return hasProtect; }
    public boolean hasTrap() { return hasTrap; }
    public String getItems() { 
        StringBuilder ret = new StringBuilder();
        for (String item : items.keySet()) {
            ret.append(item);
            ret.append("(");
            ret.append(items.get(item));
            ret.append("), ");
        }
        if (!items.isEmpty())
            ret.replace(ret.length()-2, ret.length(), "");

        return ret.toString();
    }

    public void setPos(int x_pos, int y_pos) { posisi = new Point(x_pos, y_pos); }
    public void addItem(Item x) {
        if (items.containsKey(x.getNama())) {
            items.replace(x.getNama(), items.get(x.getNama())+1);
        } else {
            items.put(x.getNama(), 1);
        }
    }
    public void giveTrap() { hasProtect = true; }
    public void giveShield() { hasProtect = true; }

    public boolean siapPanen() { return unitPanen >= batasPanen; }

    abstract public void nextTurn();
    abstract public boolean makan(Produk p);
    abstract public Makhluk turnToMakhluk();
    abstract public Holdable turnToHoldable();
}

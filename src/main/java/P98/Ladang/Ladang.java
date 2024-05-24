package P98.Ladang;

import java.util.ArrayList;
import java.util.List;

import P98.Makhluk.Makhluk;
import P98.Produk.Produk;
import P98.Makhluk.Tumbuhan.*;
import java.awt.Point;

public class Ladang {
    private List<Makhluk> kartu;

    public Ladang() {
        kartu = new ArrayList<Makhluk>();
        for (int i = 0; i < 20; i++) {
            Makhluk elmt = new Tumbuhan();
            kartu.add(elmt);
        }
    }

    private int pointToIndex(Point coor) {
        return (coor.x + coor.y*5);
    }

    public Makhluk getContent(Point coor) {
        return kartu.get(pointToIndex(coor));
    }

    public void addMakhluk(Makhluk elmt, Point coor) {
        kartu.set(pointToIndex(coor), elmt);
    }

    public Produk harvest(Point coor) {
        Makhluk harvested = getContent(coor);
        Produk hasilPanen = harvested.harvest();
        addMakhluk(new Tumbuhan(), coor);
        return hasilPanen;
    }
}

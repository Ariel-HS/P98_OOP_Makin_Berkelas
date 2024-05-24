package P98.Ladang;

import java.util.ArrayList;
import java.util.List;

import P98.Exception.BelumSiapException;
import P98.Interface.Holdable;
import P98.Makhluk.Makhluk;
import P98.Produk.Produk;
import P98.Makhluk.Tumbuhan.*;
import java.awt.Point;

public class Ladang {
    private List<Holdable> kartu;

    public Ladang() {
        kartu = new ArrayList<Holdable>();
        for (int i = 0; i < 20; i++) {
            Holdable elmt = new Tumbuhan();
            kartu.add(elmt);
        }
    }

    private int pointToIndex(Point coor) {
        return (coor.x + coor.y*5);
    }

    public Holdable getContent(Point coor) {
        return kartu.get(pointToIndex(coor));
    }

    public void addMakhluk(Holdable elmt, Point coor) {
        kartu.set(pointToIndex(coor), elmt);
    }

    public Produk harvest(Point coor) throws BelumSiapException {
        Holdable harvested = getContent(coor);
        Produk hasilPanen;
        if (harvested instanceof Makhluk m) {
            if (!m.siapPanen()) throw new BelumSiapException();
            hasilPanen = m.harvest();
        }
        addMakhluk(new Tumbuhan(), coor);
        return hasilPanen;
    }
}

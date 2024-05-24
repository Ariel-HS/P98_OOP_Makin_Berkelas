package P98.Hewan;

import java.util.HashMap;

import P98.Makhluk.Makhluk;
import P98.Produk.Produk;
import P98.Tumbuhan.Tumbuhan;

abstract public class Hewan extends Makhluk {
    public Hewan() {
        super();
    }

    public Hewan(String _nama, int _batasPanen, Produk p) {
        super(_nama, _batasPanen, p);
    }
}

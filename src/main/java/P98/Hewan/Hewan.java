package P98.Hewan;

import P98.Makhluk.Makhluk;
import P98.Produk.Produk;
import P98.Tumbuhan.Tumbuhan;

abstract public class Hewan extends Makhluk {
    public Hewan() {
        super();
    }

    public Hewan(String _nama, int x_pos, int y_pos, int _unitPanen, int _batasPanen, Produk p, Player pem) {
        super(_nama, x_pos, y_pos, _unitPanen, _batasPanen, p, pem);
    }

    public void nextTurn() {
        // do nothing
        return;
    }
}

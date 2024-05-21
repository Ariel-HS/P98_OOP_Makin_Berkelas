package P98.Hewan;

import P98.Makhluk.Makhluk;
import P98.Produk.Produk;
import P98.Tumbuhan.Tumbuhan;

public class Karnivora extends Hewan {
    public Karnivora() {
        super();
    }

    public Karnivora(String _nama, int _batasPanen, Produk p) {
        super(_nama, _batasPanen, p);
    }

    // public Karnivora(String _nama, int x_pos, int y_pos, int _unitPanen, int _batasPanen, Produk p, Player pem) {
    //     super(_nama, x_pos, y_pos, _unitPanen, _batasPanen, p, pem);
    // }

    // public void makan(Produk p) {
    //     if (p.tryEat("Karnivora")) {
    //         setUnitPanen(getUnitPanen() + p.getBobot());
    //     }
    // }

    // public Makhluk turnToMakhluk() {
    //     return new Karnivora(this.getNama(), this.getPos().x, this.getPos().y, this.getUnitPanen(), this.getBatasPanen(), this.harvest(), this.getPemilik())
    // }
}

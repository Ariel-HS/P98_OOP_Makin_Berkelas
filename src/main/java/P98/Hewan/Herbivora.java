package P98.Hewan;

import P98.Makhluk.Makhluk;
import P98.Player.Player;
import P98.Produk.Produk;

public class Herbivora extends Hewan {
    public Herbivora() {
        super();
    }

    public Herbivora(String _nama, int x_pos, int y_pos, int _unitPanen, int _batasPanen, Produk p, Player pem) {
        super(_nama, x_pos, y_pos, _unitPanen, _batasPanen, p, pem);
    }

    public void makan(Produk p) {
        if (p.tryEat("Herbivora")) {
            setUnitPanen(getUnitPanen() + p.getBobot());
        }
    }

    public Makhluk turnToMakhluk() {
        return new Herbivora(this.getNama(), this.getPos().x, this.getPos().y, this.getUnitPanen(), this.getBatasPanen(), this.harvest(), this.getPemilik());
    }
}
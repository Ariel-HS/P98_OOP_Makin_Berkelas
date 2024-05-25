package P98.Makhluk.Tumbuhan;

import P98.Interface.Holdable;
import P98.Makhluk.Makhluk;
import P98.Player.Player;
import P98.Produk.Produk;
public class Tumbuhan extends Makhluk {
    public Tumbuhan() {
        super();
    }

    public Tumbuhan(String _nama, int x_pos, int y_pos, int _unitPanen, int _batasPanen, Produk p, Player pem) {
        super(_nama, x_pos, y_pos, _unitPanen, _batasPanen, p, pem);
    }

    public Tumbuhan(Tumbuhan other) {
        this(other.getNama(), other.getPos().x, other.getPos().y, other.getUnitPanen(),
             other.getBatasPanen(), set.harvest(), other.getPemilik());
    }

    public void nextTurn() {
        // makhluk ditambahin protected void setUnitPanen()
        setUnitAsli(getUnitAsli() + 1);
        setUnitPanen(getUnitPanen() + 1);
    }

    public Makhluk turnToMakhluk() {
        return new Tumbuhan(this.getNama(), this.getPos().x, this.getPos().y, this.getUnitPanen(), this.getBatasPanen(), this.harvest(), this.getPemilik());
    }

    public void makan(Produk p) {
        //
    }

    public void interact(Holdable m) {
        //
    }

    public Holdable turnToHoldable() {
        return new Tumbuhan(this.getNama(), this.getPos().x, this.getPos().y, this.getUnitPanen(), this.getBatasPanen(), this.harvest(), this.getPemilik());
    }
}
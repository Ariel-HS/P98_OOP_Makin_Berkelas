package P98.Makhluk.Tumbuhan;
import P98.Interface.Holdable;
import P98.Makhluk.Makhluk;
import P98.Produk.Produk;
import P98.Produk.ProdukTumbuhan;

public class Tumbuhan extends Makhluk {
    public Tumbuhan() {
        super();
    }

    public Tumbuhan(String _nama, int x_pos, int y_pos, int _unitPanen, int _batasPanen, Produk p, Player pem) {
        super(_nama, x_pos, y_pos, _unitPanen, _batasPanen, p, pem);
    }

    public Tumbuhan(Tumbuhan other) {
        this(other.getNama(), other.getPos().getX(), other.getPos().getY(), other.getUnitPanen(), 
             other.getBatasPanen(), other.harvest(), other.getPemilik());
    }

    public void nextTurn() {
        // makhluk ditambahin protected void setUnitPanen()
        setUnitPanen(getUnitPanen() + 1);
    }

    public Makhluk turnToMakhluk() {
        return new Tumbuhan(this.getNama(), this.getPos().x, this.getPos().y, this.getUnitPanen(), this.getBatasPanen(), this.harvest(), this.getPemilik())
    }

    public void makan(Produk p) {
        //
    }

    public Holdable turnToHoldable() {
        return new Tumbuhan(this);
    }

    public void interact(Makhluk m, Player p) {
        //
    }
}
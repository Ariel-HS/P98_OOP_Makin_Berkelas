package P98.Makhluk.Hewan;

import P98.Interface.Holdable;
import P98.Makhluk.Makhluk;
import P98.Player.Player;
import P98.Produk.Produk;

public class Karnivora extends Hewan {
    public Karnivora() {
        super();
    }

    public Karnivora(String _nama, int x_pos, int y_pos, int _unitPanen, int _batasPanen, Produk p) {
        super(_nama, x_pos, y_pos, _unitPanen, _batasPanen, p);
    }

    public Karnivora(String _nama, int x_pos, int y_pos, int _unitPanen, int _batasPanen, Produk p, Player pem) {
        super(_nama, x_pos, y_pos, _unitPanen, _batasPanen, p, pem);
    }

    public Karnivora(Karnivora other) {
        this(other.getNama(), other.getBatasPanen(), other.getBatasPanen(), other.getUnitPanen(), other.getBatasPanen(), other.harvest(), other.getPemilik());
    }
    
    public boolean makan(Produk p) {
        if (p.tryEat("Karnivora")) {
            setUnitPanen(getUnitPanen() + p.getBobot());
            setUnitAsli(getUnitAsli() + p.getBobot());
            return true;
        }else {
        	return false;
        }
    }

    public Makhluk turnToMakhluk() {
        return new Karnivora(this.getNama(), this.getPos().x, this.getPos().y, this.getUnitPanen(), this.getBatasPanen(), this.harvest(), this.getPemilik());
    }

    public Holdable turnToHoldable(Player p) {
        return new Karnivora(this.getNama(), this.getPos().x, this.getPos().y, this.getUnitPanen(), this.getBatasPanen(), this.harvest(), p);
    }

    public void interact(Holdable m) {
        //
    }
}
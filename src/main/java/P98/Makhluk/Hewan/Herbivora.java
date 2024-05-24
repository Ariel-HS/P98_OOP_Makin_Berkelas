package P98.Makhluk.Hewan;

import P98.Interface.Holdable;
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

    public Herbivora(Herbivora other) {
        this(other.getNama(), other.getBatasPanen(), other.getBatasPanen(), other.getUnitPanen(), other.getBatasPanen(), other.harvest(), other.getPemilik());
    }
    
    public void makan(Produk p) {
        if (p.tryEat("Herbivora")) {
            setUnitPanen(getUnitPanen() + p.getBobot());
            setUnitAsli(getUnitAsli() + p.getBobot());
        }
    }
    
    public Makhluk turnToMakhluk() {
        return new Herbivora(this.getNama(), this.getPos().x, this.getPos().y, this.getUnitPanen(), this.getBatasPanen(), this.harvest(), this.getPemilik());
    }

    public void interact(Holdable m) {
        //
    }

    public Holdable turnToHoldable() {
        return new Herbivora(this.getNama(), this.getPos().x, this.getPos().y, this.getUnitPanen(), this.getBatasPanen(), this.harvest(), this.getPemilik());
    }
}
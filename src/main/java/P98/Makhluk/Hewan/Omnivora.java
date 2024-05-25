package P98.Makhluk.Hewan;

import P98.Interface.Holdable;
import P98.Makhluk.Makhluk;
import P98.Player.Player;
import P98.Produk.Produk;

public class Omnivora extends Hewan {
    public Omnivora() {
        super();
    }

    public Omnivora(String _nama, int x_pos, int y_pos, int _unitPanen, int _batasPanen, Produk p) {
        super(_nama, x_pos, y_pos, _unitPanen, _batasPanen, p);
    }

    public Omnivora(String _nama, int x_pos, int y_pos, int _unitPanen, int _batasPanen, Produk p, Player pem) {
        super(_nama, x_pos, y_pos, _unitPanen, _batasPanen, p, pem);
    }

    public Omnivora(Omnivora other) {
        this(other.getNama(), other.getBatasPanen(), other.getBatasPanen(), other.getUnitPanen(), other.getBatasPanen(), other.harvest(), other.getPemilik());
    }
    
    public void makan(Produk p) {
        if (p.tryEat("Omnivora")) {
            setUnitPanen(getUnitPanen() + p.getBobot());
            setUnitAsli(getUnitAsli() + p.getBobot());
        }
    }

    public Holdable turnToHoldable() {
        return new Omnivora(this.getNama(), this.getPos().x, this.getPos().y, this.getUnitPanen(), this.getBatasPanen(), this.harvest(), this.getPemilik());
    }
    
    public Makhluk turnToMakhluk() {
        return new Omnivora(this.getNama(), this.getPos().x, this.getPos().y, this.getUnitPanen(), this.getBatasPanen(), this.harvest(), this.getPemilik());
    }

    public void interact(Holdable m) {
        //
    }
}
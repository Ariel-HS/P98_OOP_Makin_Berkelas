package P98.Produk;

import P98.Exception.GaBisaMakanException;
import P98.Exception.WrongItemException;
import P98.Interface.Holdable;
import P98.Makhluk.*;
import P98.Player.*;

public class ProdukTumbuhan extends Produk {
    public ProdukTumbuhan() {
        super();
    }

    public ProdukTumbuhan(String nama, Player pem, int harga, int bobot) {
        super(nama, pem, harga, bobot);
    }

    public ProdukTumbuhan(ProdukTumbuhan ohter) {
        this(ohter.getNama(), ohter.getPemilik(), ohter.getHarga(), ohter.getBobot());
    }

    public boolean tryEat(String s) {
        if (s.equals("Karnivora")) return false;
        return true;
    }

    public void interact(Holdable m) throws WrongItemException, GaBisaMakanException {
        if (m.getPemilik() == this.getPemilik() && m instanceof Makhluk) {
            Makhluk ma = (Makhluk) m;
            boolean kesuksesanMakan =ma.makan(this);
            if(kesuksesanMakan == false) {
            	throw new GaBisaMakanException();
            }
        } else {
        	throw new WrongItemException("pemain lain");	
        }
    }

    public Produk turnToProduk() {
        return new ProdukTumbuhan(this);
    }
}
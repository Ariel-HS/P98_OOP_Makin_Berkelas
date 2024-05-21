package P98.Player;
import P98.Produk.*;
import P98.Ladang.*;
import P98.Deck.*;

public class Player {
    private String username;
    private Ladang ladang;
    private Integer gulden;
    private Deck deckAktif;
    private Deck deck;

    public Player() {
        this.gulden = 0;
        this.username = "default";
        this.ladang = new Ladang();
        this.deckAktif = new Deck();
        this.deck = new Deck();
    }

    public Player(String username, Integer gulden) {
        this.username = username;
        this.gulden = gulden;
        this.ladang = new Ladang(); 
        this.deckAktif = new Deck();
        this.deck = new Deck();
    }

    public Boolean jual(Produk p) {
        return false;
    }

    public Boolean beli(Produk p) {
        return false;
    }
}

package P98.Player;
import P98.Produk.*;
import P98.Ladang.*;
import P98.Deck.*;
import P98.Exception.DeckFullException;
import P98.Interface.Holdable;
import P98.Exception.*;

public class Player {
    private Ladang ladang;
    private Integer gulden;
    private Deck deckAktif;
    private Deck deck;

    public Player() {
        this.gulden = 0;
        this.ladang = new Ladang();
        this.deckAktif = new Deck();
        this.deck = new Deck();
    }

    public Player(Integer gulden) {
        this.gulden = gulden;
        this.ladang = new Ladang(); 
        this.deckAktif = new Deck();
        this.deck = new Deck();
    }

    public Player(Player player) {
        this.gulden = player.gulden;
        this.ladang = player.ladang; 
        this.deckAktif = player.deckAktif;
        this.deck = player.deck;
    }

    public Boolean jual(Produk p) {
        return false;
    }

    public Boolean beli(Produk p) {
        return false;
    }

    public void setGulden(Integer gulden) {
        this.gulden = gulden;
    }

    public Integer getGulden() {
        return this.gulden;
    }

    public void addToDeck(Holdable kartu) throws DeckFullException {
        if (this.deck.getJumlahKartu() >= 40) {
            throw new DeckFullException();
        }
        this.deck.addKartu(kartu);
    }

    public void addToDeckAktif(Holdable kartu) throws DeckFullException {
        if (this.deckAktif.getJumlahKartu() >= 6) {
            throw new DeckFullException();
        }

        this.deckAktif.addKartu(kartu);
    }

    public void shuffleDeck() {
        this.deck.shuffleDeck();
    }

    public Deck getDeck() {
        return this.deck;
    }
    
    public Deck getDeckAktif() {
        return this.deckAktif;
    }
}

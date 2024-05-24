package P98.Player;
import P98.Produk.*;
import P98.newComponent.Card;
import P98.Ladang.*;
import P98.Makhluk.Makhluk;

import java.util.ArrayList;

import P98.Deck.*;
import P98.Exception.DeckFullException;
import P98.Interface.Holdable;

public class Player {
    private Ladang ladang;
    private Integer gulden;
    private Deck deckAktif;
    private Deck deck;
	public ArrayList<Integer> previousPositionX = new ArrayList<Integer>();
	public ArrayList<Integer> previousPositionY = new ArrayList<Integer>();
	public ArrayList<Card> kartuAktif = new ArrayList<Card>();

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
    
    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Deck getDeck() {
        return this.deck;
    }
    
    public Deck getDeckAktif() {
        return this.deckAktif;
    }

    public Ladang getLadang() {
        return this.ladang;
    }

    public void nextTurn() {
        for (int i = 0; i < ladang.getKartu().size(); i++) {
            Makhluk m = (Makhluk) ladang.getKartu().get(i);
            if (m.getUnitAsli() != -1) {
                m.nextTurn();
            }
        }
    }
    public ArrayList<Holdable> getTopDeck() {
        Integer numKartu = 4;
        if (this.deck.getJumlahKartu() <= 5) {
            numKartu = 1;
        }
        else if (this.deckAktif.getJumlahKartu() > 2) {
            numKartu = 6-this.deckAktif.getJumlahKartu();
        }

        ArrayList<Holdable> drawnKartu = this.deck.getTopKartu(numKartu);
        // for (Holdable h: drawnKartu) {
        //     h.print();
        // }

        return drawnKartu;
    }

    public void draw() {
        Integer numKartu = 4;
        if (this.deck.getJumlahKartu() <= 5) {
            numKartu = 1;
        }
        else if (this.deckAktif.getJumlahKartu() > 2) {
            numKartu = 6-this.deckAktif.getJumlahKartu();
        }

        ArrayList<Holdable> drawnKartu = this.deck.getTopKartu(numKartu);
        for (Holdable h: drawnKartu) {
            this.deckAktif.addKartu(h);
        }
        this.deck.drawTopKartu(numKartu);
    }

    public Integer getDeckCardCount() {
        return this.deck.getJumlahKartu();
    }

    public Integer getActiveCardCount() {
        return this.deckAktif.getJumlahKartu();
    }
}
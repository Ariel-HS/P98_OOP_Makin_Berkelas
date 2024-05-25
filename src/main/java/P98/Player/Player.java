package P98.Player;
import P98.Produk.*;
import P98.newComponent.Card;
import P98.Ladang.*;
import P98.Deck.*;
import P98.Exception.DeckFullException;
import P98.Exception.UangTidakCukupException;
import P98.Interface.Holdable;
import P98.Makhluk.Makhluk;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class Player {
    private Ladang ladang;
    private Integer gulden;
    private DeckAktif deckAktif;
    private ArrayList<Holdable> deck;

    public ArrayList<Integer> previousPositionX = new ArrayList<>();
	public ArrayList<Integer> previousPositionY = new ArrayList<>();
    public ArrayList<Card> kartuAktif = new ArrayList<>();

    public Player() {
        this.gulden = 0;
        this.ladang = new Ladang();
        this.deckAktif = new DeckAktif();
        this.deck = new ArrayList<>();
    }

    public Player(Integer gulden) {
        this.gulden = gulden;
        this.ladang = new Ladang(); 
        this.deckAktif = new DeckAktif();
        this.deck = new ArrayList<>();
    }

    public Player(Player player) {
        this.gulden = player.gulden;
        this.ladang = player.ladang; 
        this.deckAktif = player.deckAktif;
        this.deck = player.deck;
    }

    public void removeFromDeckAktif(int idx) {
        deckAktif.removeKartu(idx);
    }

    public void jual(Produk p, int index) {
        setGulden(gulden + p.getHarga());
        removeFromDeckAktif(index);
        ArrayList<Holdable> test = this.deckAktif.getCleanDeck();
        // for (Holdable h:test) {
        //     System.out.println("TEST: "+h.getNama()+" "+index);
        // }
    }

    public void beli(Produk p) throws UangTidakCukupException, DeckFullException {
        if (gulden < p.getHarga()) throw new UangTidakCukupException();
        addToDeckAktif(p.turnToHoldable(this));
        setGulden(gulden - p.getHarga());
        p.setPemilik(this);
    }

    public void setGulden(Integer gulden) {
        this.gulden = gulden;
    }

    public Integer getGulden() {
        return this.gulden;
    }

    public void addToDeck(Holdable kartu) throws DeckFullException {
        if (this.deck.size() >= 40) {
            throw new DeckFullException();
        }
        this.deck.add(kartu);
    }

    public void addToDeckAktif(Holdable kartu) throws DeckFullException {
        if (this.deckAktif.getJumlahKartu() >= 6) {
            throw new DeckFullException();
        }

        this.deckAktif.addKartu(kartu);
    }

    public void setDeck(ArrayList<Holdable> deck) {
        this.deck = deck;
    }

    public void shuffleDeck() {
        Collections.shuffle(this.deck);
    }

    public ArrayList<Holdable> getDeck() {
        return this.deck;
    }
    
    public DeckAktif getDeckAktif() {
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

    public Integer isInKartuAktif(Holdable h) {
        for (int i = 0; i < kartuAktif.size(); i++) {
            if (kartuAktif.get(i).getIsi() == h) {
                return i;
            }
        }
        return -1;
    }

    public ArrayList<Holdable> getTopDeck() {
        Integer numKartu = 4;
        if (this.deck.size() <= 5) {
            numKartu = 1;
        }
        else if (this.deckAktif.getJumlahKartu() > 2) {
            numKartu = 6-this.deckAktif.getJumlahKartu();
        }

        ArrayList<Holdable> drawnKartu = new ArrayList<>();
        for (int i=0; i<numKartu; i++) {
            System.out.println(deck.get(i).getNama());
            drawnKartu.add(deck.get(i).turnToHoldable(this));
        }
        // System.out.println("HUH");
        for (Holdable h: drawnKartu) {
            System.out.println(h.getNama());
        }

        return drawnKartu;
    }

    public void draw() {
        Integer numKartu = 4;
        if (this.deck.size() <= 5) {
            numKartu = 1;
        }
        else if (this.deckAktif.getJumlahKartu() > 2) {
            numKartu = 6-this.deckAktif.getJumlahKartu();
        }

        ArrayList<Holdable> drawnKartu = getTopDeck();
        // System.out.println("DRAWWW");
        for (Holdable h: drawnKartu) {
            // System.out.println(h.getNama());
            this.deckAktif.addKartu(h.turnToHoldable(this));
        }

        for (int i=0; i<numKartu; i++) {
            this.deck.remove(0);
        }
    }

    public Integer getDeckCardCount() {
        return this.deck.size();
    }

    public Integer getActiveCardCount() {
        return this.deckAktif.getJumlahKartu();
    }
    
}

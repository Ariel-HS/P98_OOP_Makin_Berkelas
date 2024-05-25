package P98.Deck;
import java.util.*;
import P98.Interface.*;

public class Deck {
    private ArrayList<Holdable> kartu;

    public Deck() {
        this.kartu = new ArrayList<>();
    }

    public Deck(Deck deck) {
        this.kartu = new ArrayList<>();
        // for (Holdable)
    }

    public void addKartu(Holdable newKartu) {
        this.kartu.add(newKartu);
    }

    public ArrayList<Holdable> getTopKartu(Integer numKartu) {
        ArrayList<Holdable> listKartu = new ArrayList<>();
        
        for (int i=0; i<numKartu; i++) {
            listKartu.add(this.kartu.get(i).turnToHoldable(null));
        }

        return listKartu;
    }

    public void drawTopKartu(Integer numKartu) {
        for (int i=0; i<numKartu; i++) {
            this.kartu.remove(0);
        }
    }

    public void shuffleDeck() {
        Collections.shuffle(this.kartu);
    }

    public void clearDeck() {
        this.kartu.clear();
    }

    public Integer getJumlahKartu() {
        return this.kartu.size();
    }

    public ArrayList<Holdable> getDeck() { // for testing purposes only
        return this.kartu;
    }
}
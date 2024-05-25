package P98.Deck;
import java.util.*;
import P98.Interface.*;
import P98.Makhluk.Tumbuhan.Tumbuhan;

public class Deck {
    private ArrayList<Holdable> kartu;

    public Deck() {
        this.kartu = new ArrayList<>();
    }

    public Deck(Deck deck) {
        this.kartu = deck.getDeck();
        // for (Holdable)
    }

    public void addKartu(Holdable newKartu) {
        this.kartu.add(newKartu);
    }

    public void removeKartu(int idx) {
        this.kartu.remove(idx);
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
        int cnt = 0;
        for (Holdable h : kartu) {
            if (!h.getNama().isEmpty())
                cnt++;
        }

        return cnt;
    }

    public ArrayList<Holdable> getDeck() { // for testing purposes only
        return this.kartu;
    }
}
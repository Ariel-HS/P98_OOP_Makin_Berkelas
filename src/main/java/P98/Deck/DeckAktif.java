package P98.Deck;
import java.util.*;
import P98.Interface.*;
import P98.Makhluk.Tumbuhan.Tumbuhan;

public class DeckAktif {
    private ArrayList<Holdable> kartu;

    public DeckAktif() {
        this.kartu = new ArrayList<>();
        for (int i=0;i<6;i++) {
            kartu.add(new Tumbuhan());
        }
    }

    public void addKartu(Holdable newKartu) {
        // System.out.println("BEFORE ADD");
        // for (Holdable h:kartu) {
        //     System.out.println(h.getNama());
        // }
        for (int i=0;i<6;i++) {
            if (kartu.get(i).getNama().equals("")){
                kartu.set(i,newKartu);
                break;
            }
        }

        // System.out.println("AFTER ADD");
        // for (Holdable h:kartu) {
        //     System.out.println(h.getNama());
        // }
    }

    public void removeKartu(int idx) {
        this.kartu.set(idx, new Tumbuhan());
    }

    // public ArrayList<Holdable> getTopKartu(Integer numKartu) {
    //     ArrayList<Holdable> listKartu = new ArrayList<>();
        
    //     for (int i=0; i<numKartu; i++) {
    //         listKartu.add(this.kartu.get(i).turnToHoldable(null));
    //     }

    //     return listKartu;
    // }

    // public void drawTopKartu(Integer numKartu) {
    //     for (int i=0; i<numKartu; i++) {
    //         this.kartu.remove(0);
    //     }
    // }

    // public void shuffleDeck() {
    //     Collections.shuffle(this.kartu);
    // }

    // public void clearDeck() {
    //     this.kartu.clear();
    // }

    public Integer getJumlahKartu() {
        int cnt = 0;
        for (Holdable h : kartu) {
            if (!h.getNama().isEmpty())
                cnt++;
        }
        
        return cnt;
    }

    public ArrayList<Holdable> getCleanDeck() { // for testing purposes only
        ArrayList<Holdable> cleanDeck = new ArrayList<>();
        for (Holdable h : kartu) {
            if (!h.getNama().isEmpty())
                cleanDeck.add(h);
        }
        return cleanDeck;
    }

    public ArrayList<Holdable> getDeck() { // for testing purposes only
        return this.kartu;
    }
}
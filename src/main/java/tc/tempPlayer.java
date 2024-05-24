package tc;
import P98.newComponent.*;
import java.util.*;

public class tempPlayer {
	private testDeckAktif deckAktif;
	public ArrayList<Integer> previousPosition = new ArrayList<Integer>();
	public ArrayList<Card> kartuAktif = new ArrayList<Card>();
	
	public tempPlayer(Integer i) {
		deckAktif = new testDeckAktif();
		if(i == 0) {
			bar example1 = new bar();
			Produk example2 = new Produk("Jagung",100,25);
			Produk example3 = new Produk("Jagung",100,25);
			deckAktif.addCard(example1);
			deckAktif.addCard(example2);
			deckAktif.addCard(example3);
		} else {
			Produk example1 = new Produk("Jagung",100,25);
			Produk example2 = new Produk("Jagung",100,25);
			Produk example3 = new Produk("Jagung",100,25);
			Produk example4 = new Produk("Jagung",100,25);
			deckAktif.addCard(example1);
			deckAktif.addCard(example2);
			deckAktif.addCard(example3);
			deckAktif.addCard(example4);
		}
	}
	
	public testDeckAktif getDeckAktif() {
		return deckAktif;
	}
}

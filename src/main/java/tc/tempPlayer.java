package tc;
import P98.newComponent.*;
import P98.Tumbuhan.*;
import P98.Hewan.*;
import java.util.*;

public class tempPlayer {
	private testDeckAktif deckAktif;
	public ArrayList<Integer> previousPositionX = new ArrayList<Integer>();
	public ArrayList<Integer> previousPositionY = new ArrayList<Integer>();
	public ArrayList<Card> kartuAktif = new ArrayList<Card>();
	
	public tempPlayer(Integer i) {
		deckAktif = new testDeckAktif();
		if(i == 0) {
			Holdable example1 = (Holdable) new Tumbuhan();
			Holdable example2 = (Holdable) new Karnivora();
			Holdable example3 = (Holdable) new Herbivora();
			deckAktif.addCard(example1);
			deckAktif.addCard(example2);
			deckAktif.addCard(example3);
		} else {
			foo example1 = new foo();
			foo example2 = new foo();
			foo example3 = new foo();
			foo example4 = new foo();
			deckAktif.addCard(example1);
			
			+deckAktif.addCard(example2);
			deckAktif.addCard(example3);
			deckAktif.addCard(example4);
		}
	}
	
	public testDeckAktif getDeckAktif() {
		return deckAktif;
	}
}

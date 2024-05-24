package tc;

import java.util.*;

public class testDeckAktif {
	private ArrayList<Holdable> kartu;

	public testDeckAktif() {
		kartu = new ArrayList<Holdable>();
	}

	public ArrayList<Holdable> getKartu() {
		return kartu;
	}

	public Holdable getContent(Integer idx) {
		// add exception later
		return kartu.get(idx);
	}

	public void addCard(Holdable cardContent) {
		if (kartu.size() < 6) { // sebaiknya diganti dengan exception
			kartu.add(cardContent);
		}
	}
}

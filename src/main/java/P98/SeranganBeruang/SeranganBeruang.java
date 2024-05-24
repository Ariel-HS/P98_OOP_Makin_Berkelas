package P98.SeranganBeruang;

import java.util.Random;
import java.util.List;
import java.awt.Point;

import P98.Ladang.Ladang;
import P98.Makhluk.Makhluk;
import P98.Makhluk.Tumbuhan.Tumbuhan;

public class SeranganBeruang {
    private List<Point> subgrid;
    private float timeLeft;
    private Ladang ladang;

    public SeranganBeruang() {
        Random random = new Random();
        timeLeft = random.nextInt(60 - 30 + 1) + 30;
        for (int i = 0; i < 6; i++) {
            subgrid.add(new Point(random.nextInt(6), random.nextInt(5)));
        }
    }

    public void reduceTime() {
        timeLeft -= 0.1;
    }

    public boolean startAttack() {
        Makhluk attacked;
        for (int i = 0; i < 6; i++) {
            attacked = ladang.getContent(subgrid.get(i));
            if (attacked.hasTrap()) {
                return false; // beruang tertangkap
            }
            if (attacked.isProtected()) {
                subgrid.remove(i);
            }
        }

        for (int i = 0; i < subgrid.size(); i++) {
            ladang.addMakhluk(new Tumbuhan(), subgrid.get(i));
        }
    }
}

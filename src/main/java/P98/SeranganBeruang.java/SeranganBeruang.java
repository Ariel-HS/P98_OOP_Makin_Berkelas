package P98.SeranganBeruang.java;

import java.util.Random;

import javax.swing.plaf.basic.BasicTabbedPaneUI.TabSelectionHandler;

import java.util.List;
import java.awt.Point;

import P98.Ladang.Ladang;
import P98.Makhluk.Makhluk;
import P98.Tumbuhan.Tumbuhan;

public class SeranganBeruang extends Thread {
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

    public void run() {
        while (timeLeft != 0) {
            timeLeft -= 0.1;
            notify();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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

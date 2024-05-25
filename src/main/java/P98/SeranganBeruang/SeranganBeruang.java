package P98.SeranganBeruang;

import java.util.Random;

import javax.swing.plaf.basic.BasicTabbedPaneUI.TabSelectionHandler;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.Point;

import P98.Interface.Holdable;
import P98.Ladang.Ladang;
import P98.Makhluk.Makhluk;
import P98.Makhluk.Tumbuhan.Tumbuhan;
import P98.newComponent.Slot;

public class SeranganBeruang extends Thread {
    private List<Point> subgrid = new ArrayList<Point>();
    private float timeLeft;
    private Ladang ladang;
    private List<Slot> frames = new ArrayList<Slot>();
    private final Object lock;

    public SeranganBeruang(Ladang l, Object lock) {
        this.lock = lock;
        ladang = l;
        Random random = new Random();
        timeLeft = random.nextInt(60 - 30 + 1) + 30;
        for (int i = 0; i < 6; i++) {
            int x = random.nextInt(5);
            int y = random.nextInt(4);
            subgrid.add(new Point(x,y));
            Slot temp = new Slot((10 + x*161) - 5, (49 + y*181) - 5, Color.RED, 122, 172);
            temp.setBounds((10 + x*161) - 5, (49 + y*181) - 5, 122, 172);
            frames.add(temp);
        }
    }

    public void run() {
        synchronized (lock) {
            while (timeLeft > 0) {
                try {
                    Thread.sleep(100);
                    timeLeft -= 0.1;
                    System.out.println(timeLeft);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            lock.notify();
        }
    }

    public Float getTimeLeft() {
        return this.timeLeft;
    }

    public Slot getFrame(int i) {
        return this.frames.get(i);
    }

    public boolean startAttack() {
        for (int i = 0; i < 6; i++) {
            if (ladang.getContent(subgrid.get(i)) instanceof Makhluk) {
                Makhluk attacked = (Makhluk) ladang.getContent(subgrid.get(i));
                if (attacked.hasTrap()) {
                    return false; // beruang tertangkap
                }
                if (attacked.isProtected()) {
                    subgrid.remove(i);
                }
            }
        }

        for (int i = 0; i < subgrid.size(); i++) {
            ladang.addMakhluk(new Tumbuhan(), subgrid.get(i));
        }

        return true;
    }
}

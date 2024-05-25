package P98.newComponent;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.border.LineBorder;

import P98.Makhluk.Tumbuhan.Tumbuhan;
import P98.Player.Player;


public class Slot extends JComponent {
	private int screenX;
	private int screenY;
	private int width;
	private int height;
	private boolean special; // special means ladang
	private Card content;
	public boolean occupied;
	
	
	public Slot(int x, int y, boolean Special) {
		width = 110;
		height = 160;
		occupied = false;
		screenX = x;
		screenY = y;
		special = Special;
		setContent(new Card(new ArrayList<Slot>(), new Tumbuhan(), new Player()));
		changeOccupied();
		if(!special) {
		    setBorder(new LineBorder(Color.RED, 3));
		    setBackground(Color.RED);}
		else {
			System.out.println("red");
		    setBorder(new LineBorder(Color.BLACK, 3));
		    setBackground(Color.BLACK);			
		}
	    setBounds(screenX, screenY, width, height);
	    setOpaque(false);
	    setLocation(screenX,screenY);
	}

	public Slot(int x, int y, Color color, int widths, int heights) { // For frame serangan beruang
		occupied = false;
		screenX = x;
		screenY = y;
		special = false;
		width = widths;
		height = heights;
		setBorder(new LineBorder(color, 3));
		setBackground(color);	
		
	    setBounds(screenX, screenY, widths, heights);
	    setOpaque(false);
	    setLocation(screenX,screenY);
	}
	
	public int getSlotX() {
		return screenX;
	}
	
	public int getSlotY() {
		return screenY;
	}
	
	public int getHeight() {
		return height ;
	}
	
	public int getWidth() {
		return width ;
	}
	
	public void setContent(Card _content) {
		content = _content;
		changeOccupied();
	}
	
	public boolean getOccupied() {
		return occupied;
	}
	
	public void changeOccupied() {
		occupied = !occupied;
	}
	
	public Card getContent() {
		return content;
	}
	
	public boolean isLadang() {
		return special;
	}
}

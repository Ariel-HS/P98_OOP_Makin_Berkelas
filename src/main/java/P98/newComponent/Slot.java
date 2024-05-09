package P98.newComponent;

import java.awt.*;

import javax.swing.JComponent;
import javax.swing.border.LineBorder;

public class Slot extends JComponent {
	private int screenX;
	private int screenY;
	private int width = 110;
	private int height = 160;
	private boolean special; // true means only hands
	private Card<? extends Holdable> content;
	public boolean occupied;
	
	
	public Slot(int x, int y, boolean Special) {
		occupied = false;
		screenX = x;
		screenY = y;
		special = Special;
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
	
	public void setContent(Card<? extends Holdable> _content) {
		content = _content;
		changeOccupied();
	}
	
	public boolean getOccupied() {
		return occupied;
	}
	
	public void changeOccupied() {
		occupied = !occupied;
	}
	
	public Card<? extends Holdable> getContent() {
			return content;
	}
}

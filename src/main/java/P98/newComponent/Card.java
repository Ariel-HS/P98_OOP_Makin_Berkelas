package P98.newComponent;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;

public class Card<T extends Holdable>
    extends JComponent {

  private volatile int screenX = 0;
  private volatile int screenY = 0;
  private volatile int myX = 0;
  private volatile int myY = 0;
  private int height = 150;
  private int width = 100;
  private T content;
  private ArrayList<Slot> temp;
  private Card<T> thisCard;

  public T getIsi(){
	  return content;
  }
  
  // return the index of slot in temp if found
  // else return -1 as false
  
  private int inSlot(ArrayList<Slot> _slotPos) {
	    for (int i = 0; i < _slotPos.size(); i++) {
	        Slot currentSlot = _slotPos.get(i);
	        int slotX = currentSlot.getSlotX();
	        int slotY = currentSlot.getSlotY();
	        int slotWidth = currentSlot.getWidth();
	        int slotHeight = currentSlot.getHeight();
//	        System.out.println(myX);
//	        System.out.println(slotX - width +"," + slotX + slotWidth);
//	        System.out.println(slotY - height +"," + slotY + slotHeight);	

	        if (myX >= slotX - width && myX <= slotX + slotWidth &&
	            myY >= slotY - height && myY <= slotY + slotHeight) {
	            return i; // Return the index if the card is on top of this slot
	        }
	    }
	    return -1; // Return -1 if the card is not on top of any slot
	}
  
  public void insertSlot() {
   	  int prevSlot = inSlot(temp); // before index of slot before myX and myY is updated
	  int tempX = myX;
      int tempY = myY;
      myX = getX();
      myY = getY();
      int slotNumber = inSlot(temp);
      
      if (slotNumber >= 0 && temp.get(slotNumber).getOccupied() == false) {
          System.out.println("ada dalam slot");
          myX = temp.get(slotNumber).getSlotX() + 5;
          myY = temp.get(slotNumber).getSlotY() + 5;
          setLocation(temp.get(slotNumber).getSlotX() + 5, temp.get(slotNumber).getSlotY() + 5);
          temp.get(slotNumber).setContent(thisCard);
          if(prevSlot >= 0) {
        	  temp.get(prevSlot).setContent(null);
          }
      } else if (slotNumber >= 0 && temp.get(slotNumber).getOccupied()) { // Check if slotNumber is valid
          if(content != temp.get(slotNumber).getContent().getIsi()) {
    	  content.interact(temp.get(slotNumber).getContent().getIsi());}
          setLocation(tempX, tempY);
          myX = tempX;
          myY = tempY;
      } else {
          System.out.println("di luar slot, returning back");
          setLocation(tempX, tempY);
          myX = tempX;
          myY = tempY;
      }
      System.out.println(myX + "," + myY);  
  }
  
  
  public Card(ArrayList<Slot> _temp, T Content) {
	temp = _temp;
    setBorder(new LineBorder(Color.BLUE, 3));
    setBackground(Color.WHITE);
    setBounds(10, 10, width, height);
    setOpaque(false);
    thisCard = this;
    content = Content;
    
    addMouseListener(new MouseListener() {

      @Override
      public void mouseClicked(MouseEvent e) {
    	  System.out.println("tampilin belakang kartu");
      }

      @Override
      public void mousePressed(MouseEvent e) {
        screenX = e.getXOnScreen();
        screenY = e.getYOnScreen();

        myX = getX();
        myY = getY();
      }
      

      
      @Override
      public void mouseReleased(MouseEvent e) {
    	  thisCard.insertSlot();
      }

      @Override
      public void mouseEntered(MouseEvent e) { }

      @Override
      public void mouseExited(MouseEvent e) { }

    });
    addMouseMotionListener(new MouseMotionListener() {

      @Override
      public void mouseDragged(MouseEvent e) {
        int deltaX = e.getXOnScreen() - screenX;
        int deltaY = e.getYOnScreen() - screenY;

        setLocation(myX + deltaX, myY + deltaY);
      }

      @Override
      public void mouseMoved(MouseEvent e) { }

    });
  }

//  public static void main(String[] args) {
//	    JFrame f = new JFrame("Swing Hello World");
//
//	    // by doing this, we prevent Swing from resizing
//	    // our nice component
//	    f.setLayout(null);
//	    Slot tempat = new Slot(400,400);
//
//	    Card mc = new Card(tempat);
//	    f.add(mc);
//	    f.add(tempat);
//
//	    f.setSize(1000, 1000);
//
//	    f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//	    f.setVisible(true);
//	  }
}



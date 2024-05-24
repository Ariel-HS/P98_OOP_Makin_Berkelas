package P98.newComponent;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;

import P98.testDnD.Screen;
import P98.Interface.*;

public class Card extends JComponent {

  private volatile int screenX = 0;
  private volatile int screenY = 0;
  private volatile int myX = 0;
  private volatile int myY = 0;
  private int height = 150;
  private int width = 100;
  private Holdable content;
  private ArrayList<Slot> temp;
  private Card thisCard;
  private boolean isMine = true;
  private Image image;

  public Holdable getIsi(){
	  return content;
  }
  
  public void setPunyaLawan() {
	  isMine = false;
  }
  
  public void setPunyaSaya() {
	  isMine = true;
  }
  
  public boolean isDefaultPosition() {
	  return myX ==0 && myY ==0;
  }
  
  public void setX(Integer _x) {
	  myX = _x;
  }
  
  public void setY(Integer _y) {
	  myY = _y;
  }
  
  public int getmyX() {
	  return myX;
  }
  
  public int getmyY() {
	  return myY;
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
          myX = temp.get(slotNumber).getSlotX() + 5;// +5 biar goodlooking, dihilangkan bisa tapi ga center
          myY = temp.get(slotNumber).getSlotY() + 5;
          setLocation(temp.get(slotNumber).getSlotX() + 5, temp.get(slotNumber).getSlotY() + 5);
          temp.get(slotNumber).setContent(thisCard);
          if(prevSlot >= 0) {
        	  temp.get(prevSlot).setContent(null);
          }
      } else if (slotNumber >= 0 && temp.get(slotNumber).getOccupied()) { // Check if slotNumber is valid
          if(content != temp.get(slotNumber).getContent().getIsi()) {
    	      // content.interact(temp.get(slotNumber).getContent().getIsi());
          }
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
  
  public void insertSlot(Integer idx) {
	  int prevSlot = inSlot(temp);
	  Integer slotX = temp.get(idx).getSlotX()+5;
	  Integer slotY = temp.get(idx).getSlotY()+5;
      myX = temp.get(idx).getSlotX() + 5;
      myY = temp.get(idx).getSlotY() + 5;
	  this.setLocation(slotX, slotY);
	  temp.get(idx).setContent(thisCard);
      if(prevSlot >= 0) {
    	  temp.get(prevSlot).setContent(null);
      }
  }
  
  private void showWindow(){
	  if(!Screen.getTheresAWindow()) {
	  Screen.setTheresAWindow(true);
      JFrame frame = new JFrame("New Window");
      frame.setSize(800, 400);
      frame.setResizable(false);
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      frame.addWindowListener(new WindowAdapter() {
          @Override
          public void windowClosed(WindowEvent e) {
              Screen.setTheresAWindow(false);
          }
      });
      frame.setLayout(null); // Use absolute positioning
      JLabel nameOfContent = new JLabel(this.content.getNama());
      nameOfContent.setFont(new Font("Serif", Font.BOLD, 56));
      nameOfContent.setBounds(300, 20, 700, 80);
      frame.add(nameOfContent);
      JLabel gambar = new JLabel();
      StringBuilder firstField = new StringBuilder();
      if(this.content.getClass().getName().contains("foo")) {
    	  firstField.append("Umur : ");
    	  String placeHolderUmur = "12";
    	  firstField.append(placeHolderUmur);
    	  String placeHolderEfekUmur = "(13)";
    	  firstField.append(placeHolderEfekUmur);
      } else {
    	  System.out.println(this.content.getClass().getName() );
    	  firstField.append("Berat : ");
      }
      JLabel field1Label = new JLabel(firstField.toString());
      field1Label.setFont(new Font("Serif", Font.BOLD, 30));
      field1Label.setBounds(20, 120 , 600, 40);
      frame.add(field1Label);
      StringBuilder secondField = new StringBuilder();
      secondField.append("Efek : ");
      JLabel field2Label = new JLabel(secondField.toString());
      field2Label.setFont(new Font("Serif", Font.BOLD, 30));
      field2Label.setBounds(20, 170 , 600, 40);
      frame.add(field2Label);
      
      frame.setVisible(true);
	  }
  }
  
@Override
  protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      if (image != null) {
          // Draw the image
          g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
      }
  }
  
  
  public void determineImage() {
	  if(this.content.getNama().equals("Domba")) {
		  image = new ImageIcon(getClass().getResource("/Hewan/mareep.png")).getImage();
	  } else if (this.content.getNama().equals("Beruang")) {
		  image = new ImageIcon(getClass().getResource("/Hewan/ursaring.png")).getImage();
	  } else if (this.content.getNama().equals("Hiu Darat")) {
		  image = new ImageIcon(getClass().getResource("/Hewan/sharpedo.png")).getImage();
	  } else if (this.content.getNama().equals("Sapi")) {
		  image = new ImageIcon(getClass().getResource("/Hewan/miltank.png")).getImage();
	  } else if (this.content.getNama().equals("Kuda")) {
		  image = new ImageIcon(getClass().getResource("/Hewan/rapidash.png")).getImage();
	  } else if(this.content.getNama().equals("Ayam")) {
		  image = new ImageIcon(getClass().getResource("/Hewan/torchic.png")).getImage();
	  } else if(this.content.getNama().equals("Jagung")) {
		  image = new ImageIcon(getClass().getResource("/Produk/corn.png")).getImage();
	  }
	  // lanjutkan nanti malas
	  //return pathToImage;
  }
  
  public Card(ArrayList<Slot> _temp, Holdable Content) {
	  temp = _temp;
    setBorder(new LineBorder(Color.BLUE, 3));
    setBackground(Color.WHITE);
    setBounds(10, 10, width, height);
    setOpaque(false);
    thisCard = this;
    content = Content;
    this.determineImage();
    //image = new ImageIcon(this.determineImage());
    
    
    addMouseListener(new MouseListener() {

      @Override
      public void mouseClicked(MouseEvent e) {
    	  if(isMine) {
    	  System.out.println("tampilin belakang kartu");
    	  showWindow();
    	  }
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
//	    foo example1 = new foo();
//	    Slot tempat = new Slot(400,400,false);
//	    ArrayList<Slot> idk = new ArrayList<Slot>();
//	    idk.add(tempat);
//	    Card mc = new Card(idk,example1);
//	    mc.setLocation(20, 40);
//	    f.add(mc);
//	    f.add(tempat);
//
//	    f.setSize(1000, 1000);
//
//	    f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//	    f.setVisible(true);
//	  }
}



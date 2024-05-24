package P98.newComponent;
import P98.Item.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;

import P98.testDnD.Screen;
import P98.Interface.*;
import P98.Item.Item;
import P98.Ladang.Ladang;
import P98.Makhluk.Makhluk;
import P98.Makhluk.Hewan.Hewan;
import P98.Makhluk.Tumbuhan.Tumbuhan;

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
	private Integer prevPosIdx = 999;
	
	public Integer getPrevPosIdx(){
		return prevPosIdx;
	}
	
	public void setPrevPosIdx(Integer newidx) {
		this.prevPosIdx = newidx;
	}
	
	public Holdable getIsi() {
		return content;
	}

	public void setPunyaLawan() {
		isMine = false;
	}

	public void setPunyaSaya() {
		isMine = true;
	}

	public boolean isDefaultPosition() {
		return myX == 0 && myY == 0;
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
	
	public ArrayList<Slot> getTemp(){
		return this.temp;
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

			if (myX >= slotX - width && myX <= slotX + slotWidth && myY >= slotY - height
					&& myY <= slotY + slotHeight) {
				return i; // Return the index if the card is on top of this slot
			}
		}
		return -1; // Return -1 if the card is not on top of any slot
	}
	
	public boolean isinLadang() {
		return temp.get(inSlot(temp)).isLadang();
	}

	public void insertSlot() {
		int prevSlot = inSlot(temp); // before index of slot before myX and myY is updated
		int tempX = myX;
		int tempY = myY;
		myX = getX();
		myY = getY();
		int slotNumber = inSlot(temp);
		if(temp.get(slotNumber).getOccupied()) {
			System.out.println("aaaaaaaaaaaaaaaa");
		}
		
		if (slotNumber >= 0 && temp.get(slotNumber).getOccupied() == false) {
			
			System.out.println("ada dalam slot");
			myX = temp.get(slotNumber).getSlotX() + 5;// +5 biar goodlooking, dihilangkan bisa tapi ga center
			myY = temp.get(slotNumber).getSlotY() + 5;
			setLocation(temp.get(slotNumber).getSlotX() + 5, temp.get(slotNumber).getSlotY() + 5);
			temp.get(slotNumber).setContent(thisCard);
			if (prevSlot >= 0) {
				temp.get(prevSlot).setContent(null);
			}
		} else if (slotNumber >= 0 && temp.get(slotNumber).getOccupied()) { // Check if slotNumber is valid
			if (content != temp.get(slotNumber).getContent().getIsi()) {
				// if area ladang interact(getMakhluk)
				// else if area dek akfif interact(getisi)
				// content.interact(temp.get(slotNumber).getContent().getIsi());
				System.out.println("lsdkfslkfj");
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
		this.setPrevPosIdx(slotNumber);
		System.out.println(myX + "," + myY);
	}

	public void insertSlot(Integer idx, Ladang l) {
		int prevSlot = inSlot(temp);
		Integer slotX = temp.get(idx).getSlotX() + 5;
		Integer slotY = temp.get(idx).getSlotY() + 5;
		myX = temp.get(idx).getSlotX() + 5;
		myY = temp.get(idx).getSlotY() + 5;
		this.setLocation(slotX, slotY);
		temp.get(idx).setContent(thisCard);
		if (idx < 20)
			l.addMakhluk(thisCard.getIsi(), new Point(idx%5, (int) (idx / 5)));
		this.setPrevPosIdx(idx);
		if (prevSlot >= 0) {
			temp.get(prevSlot).setContent(null);
		}
	}

	private void showWindow() {
		if (!Screen.getTheresAWindow() ) {
			if(content instanceof Makhluk) {
			Makhluk m = (Makhluk) content;
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
			
			StringBuilder firstField = new StringBuilder();
			if (content instanceof Tumbuhan) {
				firstField.append("Umur: ");
			} else {
				firstField.append("Berat: ");
			}
			firstField.append(Integer.toString(m.getUnitAsli()));
			firstField.append(" (");
			firstField.append(Integer.toString(m.getUnitPanen()));
			firstField.append(")");

			JLabel field1Label = new JLabel(firstField.toString());
			field1Label.setFont(new Font("Serif", Font.BOLD, 30));
			field1Label.setBounds(20, 120, 600, 40);
			frame.add(field1Label);
			StringBuilder secondField = new StringBuilder();
			secondField.append("Efek : ");
			secondField.append(m.getItems());

			JLabel field2Label = new JLabel(secondField.toString());
			field2Label.setFont(new Font("Serif", Font.BOLD, 30));
			field2Label.setBounds(20, 170, 600, 40);
			frame.add(field2Label);
			//gambar
			JLabel gambar = new JLabel();
			gambar.setBounds(500, 75, 200, 200);
			ImageIcon icon= new ImageIcon(this.image.getScaledInstance(200, 200, Image.SCALE_SMOOTH));
			gambar.setIcon(icon);
			frame.add(gambar);
			frame.setVisible(true);
			} else if (content instanceof Item) {
				// pilih holdable yang ingin dipilih
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
				Integer idx =0;
				Integer forPositionX = 0;
				Integer forPositionY = 0;
				while (idx <temp.size()) {
					Integer yValue = 40 * forPositionY ;
					for(int j=0;j<5;j++) {
						if(idx<temp.size()) {
						if(temp.get(idx).getContent()!=null) {
							if(temp.get(idx).getContent().isinLadang()) {
								Integer xValue = 160 * forPositionX;
								final Integer finalIdx = idx;
								JButton pilihanButton = new JButton(temp.get(idx).getContent().getIsi().getNama());
								pilihanButton.setFont(new Font("Serif", Font.BOLD, 15));
								pilihanButton.setBounds(xValue, yValue, 150, 40);
						          pilihanButton.addActionListener(new ActionListener() {
						              @Override
						              public void actionPerformed(ActionEvent e) {
						                try {
						                  content.interact(temp.get(finalIdx).getContent().getIsi());
						                } catch (Exception excep) {
						                  System.out.println(excep.toString());
						                }
						                frame.dispose();
						              }
						            });
						            frame.add(pilihanButton);
								forPositionX++;
							}
						}
						}
						idx++;
					}
					forPositionX = 0;
					forPositionY++;
				}

				
				frame.setVisible(true);
			}
		}
	}
	
	public void intersectOccupation(ArrayList<Slot> otherTemp) {
		for(int i=0;i < this.temp.size();i++) {
			if(otherTemp.get(i).occupied) {
				this.temp.get(i).occupied = true;
			}
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
		if (this.content.getNama().equals("Domba")) {
			//System.out.println("Working Directory = " + System.getProperty("user.dir"));
			image = new ImageIcon(getClass().getResource("/Assets/Hewan/mareep.png")).getImage();
		} else if (this.content.getNama().equals("Beruang")) {
			image = new ImageIcon(getClass().getResource("/Assets/Hewan/ursaring.png")).getImage();
		} else if (this.content.getNama().equals("Hiu Darat")) {
			image = new ImageIcon(getClass().getResource("/Assets/Hewan/sharpedo.png")).getImage();
		} else if (this.content.getNama().equals("Sapi")) {
			image = new ImageIcon(getClass().getResource("/Assets/Hewan/miltank.png")).getImage();
		} else if (this.content.getNama().equals("Kuda")) {
			image = new ImageIcon(getClass().getResource("/Assets/Hewan/rapidash.png")).getImage();
		} else if (this.content.getNama().equals("Ayam")) {
			image = new ImageIcon(getClass().getResource("/Assets/Hewan/torchic.png")).getImage();
		} else if (this.content.getNama().equals("Jagung")) {
			//System.out.println("Working Directory = " + System.getProperty("user.dir"));
			image = new ImageIcon(getClass().getResource("/Assets/Produk/corn.png")).getImage();
		} else if (this.content.getNama().equals("Susu")) {
			image = new ImageIcon(getClass().getResource("/Assets/Produk/susu.png")).getImage();
		} else if (this.content.getNama().equals("Accelerate")){
			image = new ImageIcon(getClass().getResource("/Assets/Item/Accelerate.png")).getImage();
		} else if (this.content.getNama().equals("Destroy")) {
			image = new ImageIcon(getClass().getResource("/Assets/Item/Destroy.png")).getImage();
		}
		// lanjutkan nanti
		// return pathToImage;
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
		// image = new ImageIcon(this.determineImage());
		if(isMine) {
		addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (isMine) {
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
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

		});
		addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				if(isMine) {
				int deltaX = e.getXOnScreen() - screenX;
				int deltaY = e.getYOnScreen() - screenY;

				setLocation(myX + deltaX, myY + deltaY);
				}
			}

			@Override
			public void mouseMoved(MouseEvent e) {
			}

		});
		}
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

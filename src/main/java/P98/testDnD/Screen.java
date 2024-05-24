package P98.testDnD;

import java.util.ArrayList;

import P98.Deck.Deck;
import P98.Interface.Holdable;
import P98.Item.Accelerate;
import P98.Item.Delay;
import P98.Makhluk.Tumbuhan.Tumbuhan;
import P98.Player.Player;
import P98.Produk.Produk;
import P98.Produk.ProdukHewan;
import P98.Produk.ProdukTumbuhan;
import P98.Toko.Pair;
import P98.Toko.Toko;
import P98.newComponent.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.awt.event.ActionEvent;

public class Screen {

	private JFrame frame;
	private ArrayList<Player> testPlayers;
	private ArrayList<Slot> slots;
	private JFrame f = new JFrame("Swing Hello World");
	//private ArrayList<Card> cardsInFocus;
	private static boolean theresAwindow = false;
	public static Integer turn =0;
	private Toko toko = new Toko();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		System.setProperty("sun.java2d.uiScale", "1.0"); // Handles DPI scaling
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Screen window = new Screen();
					// window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public String getImagePath(Holdable content) {
		if (content.getNama().equals("Domba")) {
			return "/Assets/Hewan/mareep.png";
		} else if (content.getNama().equals("Beruang")) {
			return "/Assets/Hewan/ursaring.png";
		} else if (content.getNama().equals("Hiu Darat")) {
			return "/Assets/Hewan/sharpedo.png";
		} else if (content.getNama().equals("Sapi")) {
			return "/Assets/Hewan/miltank.png";
		} else if (content.getNama().equals("Kuda")) {
			return "/Assets/Hewan/rapidash.png";
		} else if (content.getNama().equals("Ayam")) {
			return "/Assets/Hewan/torchic.png";
		} else if (content.getNama().equals("Jagung")) {
			return "/Assets/Produk/corn.png";
		} else if (content.getNama().equals("Susu")) {
			return "/Assets/Produk/susu.png";
		} else if (content.getNama().equals("Daging Beruang")) {
			return "/Assets/Produk/susu.png"; 
		} else if (content.getNama().equals("Daging Domba")) {
			return "/Assets/Produk/Daging Domba.png";
		} else if (content.getNama().equals("Daging Kuda")) {
			return "/Assets/Produk/Daging Kuda.png";
		}
		else {
			return "/Assets/Hewan/torchic.png";
		}
	}

	private void showToko() {
    if (!Screen.getTheresAWindow()) {
      Screen.setTheresAWindow(true);
      JFrame frame = new JFrame("Toko");
      frame.setSize(800, 800);
      frame.setResizable(false);
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      frame.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosed(WindowEvent e) {
          Screen.setTheresAWindow(false);
        }
      });

      List<Pair<Produk, Integer>> itemToko = toko.getItemList();
   Player player = testPlayers.get(turn%2);
      Integer idx = 0;
      JPanel contentPane = new JPanel();
      contentPane.setLayout(null); // Set custom layout for manual positioning
   System.out.println(itemToko.size());

      while (idx < itemToko.size()) {
        Integer yValue = 35 * idx;
        for (int j = 0; j < 3; j++) {
          if (idx < itemToko.size()) {
            Integer xValue = 210 * j;

      Produk pro = itemToko.get(idx).getFirst();
      EtalaseToko etalase = new EtalaseToko(idx, xValue, yValue, pro.getNama(), itemToko.get(idx).getSecond(), pro.getHarga());
      System.out.println(etalase.getJumlah());
            // Create JPanel for each product display
            JPanel productPanel = new JPanel();
            productPanel.setBounds(xValue, yValue, 200, 100);
            productPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            contentPane.add(productPanel);

      System.out.println(etalase.getImage());
            // Image (assuming image path stored in 'imagePath' variable)
            Image temp =  etalase.getImage();
            ImageIcon imageIcon = new ImageIcon(temp.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
            JLabel imageLabel = new JLabel(imageIcon);
            imageLabel.setLocation(50, 50);
            productPanel.add(imageLabel);

            // Product Name
            JLabel nameLabel = new JLabel(etalase.getNama());
            nameLabel.setLocation(70, 10);
            productPanel.add(nameLabel);

            // Price
            String priceText = "Harga: " + etalase.getHarga();
            JLabel priceLabel = new JLabel(priceText);
            priceLabel.setLocation(70, 35);
            productPanel.add(priceLabel);

            // Quantity
            String quantityText = "Jumlah: " + etalase.getJumlah();
            JLabel quantityLabel = new JLabel(quantityText);
            quantityLabel.setLocation(70, 60);
            productPanel.add(quantityLabel);
            // Create and configure button
            JButton removeButton = new JButton("Buy");
            removeButton.setBounds(130, 75, 60, 20);
            removeButton.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                // lakukan buy
               // umtuk sekarang baru menghapus doang
       try {
      player.beli(pro);
	  try {
	  	toko.buyProduk(pro);
		  etalase.decrJumlah();
		}
		catch (Exception ex) {
		}
		if (etalase.getJumlah() == 0){
			contentPane.remove(productPanel);
		} else {
       String quantityText = "Jumlah: " + etalase.getJumlah();
       quantityLabel.setText(quantityText);
       productPanel.repaint();
      }
      contentPane.repaint();
       } catch (Exception ex) {
      System.err.println(ex.getMessage());
       }
        }
    });
    productPanel.add(removeButton);
    contentPane.add(productPanel);
          idx++;
          }
        }
      }
      //render kartu
      idx = 0;
      ArrayList<Card> kartuOnDisplay= testPlayers.get(turn%2).kartuAktif;
      while (idx < kartuOnDisplay.size()) {
          Integer yValue = 500+(35 * idx);
          
          for (int j = 0; j < 3; j++) {
           if (idx < kartuOnDisplay.size()&& !kartuOnDisplay.get(idx).isinLadang() && kartuOnDisplay.get(idx).getIsi() instanceof Produk) {
              Integer xValue = 210 * j;

     Produk pro = (Produk) kartuOnDisplay.get(idx).getIsi();
        EtalaseToko etalase = new EtalaseToko(idx, xValue, yValue, pro.getNama(), 0, pro.getHarga());

              // Create JPanel for each product display
              JPanel productPanel = new JPanel();
              productPanel.setBounds(xValue, yValue, 200, 100);
              productPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
              contentPane.add(productPanel);

              // Image (assuming image path stored in 'imagePath' variable)
             Image temp =  etalase.getImage();
             ImageIcon imageIcon = new ImageIcon(temp.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
              JLabel imageLabel = new JLabel(imageIcon);
              imageLabel.setBounds(10, 10, 50, 50);
              productPanel.add(imageLabel);

              // Product Name
              JLabel nameLabel = new JLabel(etalase.getNama());
              nameLabel.setBounds(70, 10, 100, 20);
              productPanel.add(nameLabel);

              // Create and configure button
              JButton removeButton = new JButton("Sell");
              removeButton.setBounds(130, 75, 60, 20);
              removeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
               //Lakukan sell
               // Again for now will just delete it
                  // Remove the product panel from its parent (contentPane)
      player.jual(pro, etalase.getId());
                  toko.sellProduk(pro);
      contentPane.remove(productPanel);
                  // Might need to repaint the content pane for the change to be reflected
                  contentPane.repaint();
                  // Potentially update frame size if needed
                  // frame.pack();
                }
              });
              productPanel.add(removeButton);
            }
            idx++;
          }
        }
      
      frame.getContentPane().add(contentPane);
      frame.setVisible(true);
    }
  }
	/**
	 * Create the application.
	 */
	public Screen() {
		testPlayers = new ArrayList<Player>();
		Player player1 = new Player(1000000);
		Player player2 = new Player(1);
		testPlayers.add(player1);
		testPlayers.add(player2);
		//testing toko
		Produk example2 = new ProdukTumbuhan("Jagung",player1,100,25);
		Produk example3 = new ProdukHewan("Susu",player1,23,23);
		Produk example4 = new ProdukTumbuhan("Jagung",player1,100,25);
		Produk example5 = new ProdukHewan("Susu",player1,23,23);
		toko.sellProduk(example2);
		toko.sellProduk(example3);
		toko.sellProduk(example4);
		toko.sellProduk(example5);

		//cardsInFocus = player1.kartuAktif;
		initialize();
	}

	public static void setTheresAWindow(boolean condition) {
		theresAwindow = condition;
	}

	public static boolean getTheresAWindow() {
		return theresAwindow;
	}

	public void setCards(Integer idx,boolean punyaLawan) {
		Player current = this.testPlayers.get(idx);
		System.out.println("ldkjfsldkfj");
		Deck deckAktif = current.getDeckAktif();

		// HAPUS
		Accelerate i1 = new Accelerate(current);
		Accelerate i2 = new Accelerate(current);
		Delay i3 = new Delay(current);
		Tumbuhan t1 = new Tumbuhan("Jagung", 0, 0, 10, 0, new ProdukTumbuhan(), current);
		t1.addItem(i1);
		t1.addItem(i2);
		t1.addItem(i3);
		// // for testing
		try {
			current.addToDeckAktif(t1);
			// current.addToDeckAktif(new Acc);
			// current.addToDeckAktif(new Tumbuhan("Zomm"));
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// deckAktif.addKartu();

		ArrayList<Holdable> currentCards = deckAktif.getTopKartu(deckAktif.getJumlahKartu());
		
		for (int j = 0; j < currentCards.size(); j++) {
				current.kartuAktif.add(new Card(slots, currentCards.get(j)));
				if(current.kartuAktif.get(j).getPrevPosIdx()==999) {
					for (int i = 0; i < slots.size(); i++) {
						if (!slots.get(i).occupied && !slots.get(i).isLadang()) {
							// Place the card to unoccupied hand
							current.kartuAktif.get(j).insertSlot(i, current.getLadang());
							if(punyaLawan) {
								current.kartuAktif.get(j).setPunyaLawan();
							} else {
								current.kartuAktif.get(j).setPunyaSaya();
							}
							f.getContentPane().add(current.kartuAktif.get(j));
							System.out.println("masuk sini");
						}
					}				
				}else {
					current.kartuAktif.get(j).insertSlot(current.kartuAktif.get(j).getPrevPosIdx(), current.getLadang());
					if(punyaLawan) {
						current.kartuAktif.get(j).setPunyaLawan();
					} else {
						current.kartuAktif.get(j).setPunyaSaya();
					}
					f.getContentPane().add(current.kartuAktif.get(j));
					System.out.println("masuk sono");
				}
			} 
	}
	
	public void setCards2(Integer idx,boolean punyaLawan) {
		Player current = this.testPlayers.get(idx);
		Player previous = this.testPlayers.get((idx+1)%2);
		ArrayList<Holdable> currentCards = current.getDeckAktif().getDeck();
		
		ArrayList<Holdable> previousCards = previous.getDeckAktif().getDeck();
		for (int j = 0; j < currentCards.size(); j++) {// cards
			current.kartuAktif.add(new Card(slots, currentCards.get(j)));
			if(current.kartuAktif.get(j).getPrevPosIdx()!= 999 && current.kartuAktif.get(j).isinLadang()) {
				current.kartuAktif.get(j).insertSlot(current.kartuAktif.get(j).getPrevPosIdx(), current.getLadang());
				current.kartuAktif.get(j).setPunyaLawan();
				f.getContentPane().add(current.kartuAktif.get(j));
				System.out.println("masuk sono");
			}
		}
		for (int j = 0; j < previousCards.size(); j++) {																// cards
			previous.kartuAktif.add(new Card(slots, previousCards.get(j)));
			if(!previous.kartuAktif.get(j).isinLadang()) {
				previous.kartuAktif.get(j).insertSlot(previous.kartuAktif.get(j).getPrevPosIdx(), current.getLadang());
				//previous.kartuAktif.get(j).intersectOccupation(current.kartuAktif.get(0).getTemp());
				
				previous.kartuAktif.get(j).setPunyaSaya();
				f.getContentPane().add(previous.kartuAktif.get(j));
				System.out.println("masuk sono");
			}
	}
	}

	public void clearCards() {
        Component[] components = f.getContentPane().getComponents();

        for (Component component : components) {
            if (component instanceof Card) {
                f.getContentPane().remove(component);
            }
        }

        f.getContentPane().revalidate();
        f.getContentPane().repaint();
        for(int i=0;i<slots.size();i++) {
        	slots.get(i).occupied = false;
        }
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		// by doing this, we prevent Swing from resizing
		// our nice component
		slots = new ArrayList<Slot>();
		f.getContentPane().setLayout(null);
		Slot Ladang1_4 = new Slot(508, 49, true);
		Ladang1_4.setLocation(508, 49);
		Slot Ladang1_1 = new Slot(10, 49, true);
		Ladang1_1.setLocation(10, 49);
		Slot Ladang1_2 = new Slot(171, 49, true);
		Ladang1_2.setLocation(171, 49);

		Slot Ladang1_3 = new Slot(344, 49, true);
		Ladang1_3.setLocation(344, 49);
		f.getContentPane().add(Ladang1_3);

		Slot Ladang1_5 = new Slot(686, 49, true);
		Ladang1_5.setLocation(686, 49);
		f.getContentPane().add(Ladang1_5);

		Slot tangan1 = new Slot(10, 810, false);
		tangan1.setLocation(10, 810);
		f.getContentPane().add(tangan1);

		Slot tangan2 = new Slot(171, 810, false);
		tangan2.setBounds(171, 810, 110, 160);
		f.getContentPane().add(tangan2);

		Slot tangan3 = new Slot(344, 810, false);
		tangan3.setBounds(344, 810, 110, 160);
		f.getContentPane().add(tangan3);

		Slot tangan4 = new Slot(508, 810, false);
		tangan4.setBounds(508, 810, 110, 160);
		f.getContentPane().add(tangan4);

		Slot tangan5 = new Slot(686, 810, false);
		tangan5.setBounds(686, 810, 110, 160);
		f.getContentPane().add(tangan5);

		Slot tangan6 = new Slot(865, 810, false);
		tangan6.setBounds(865, 810, 110, 160);
		f.getContentPane().add(tangan6);

		slots.add(tangan1);
		slots.add(tangan2);
		slots.add(tangan3);
		slots.add(tangan4);
		slots.add(tangan5);
		slots.add(tangan6);

		// testing purpose
//	    foo example1 = new foo();
//	    bar example2 = new bar();
//	    
//	    Card mc2 = new Card(slots,example1);
//	    mc2.setLocation(20, 820);
//	    Card mc = new Card(slots,example2);
//	    mc.setLocation(186, 820);
//	    mc.insertSlot();
//	    mc2.insertSlot();
//	    mc.setBackground(new Color(0, 0, 255));
//	    
//	    f.getContentPane().add(mc);
//	    f.getContentPane().add(mc2);
		f.getContentPane().add(Ladang1_4);
		f.getContentPane().add(Ladang1_1);
		f.getContentPane().add(Ladang1_2);

		Slot Ladang2_1 = new Slot(10, 230, true);
		Ladang2_1.setBounds(10, 230, 110, 160);
		f.getContentPane().add(Ladang2_1);

		Slot Ladang2_2 = new Slot(171, 230, true);
		Ladang2_2.setBounds(171, 230, 110, 160);
		f.getContentPane().add(Ladang2_2);

		Slot Ladang2_3 = new Slot(344, 230, true);
		Ladang2_3.setBounds(344, 230, 110, 160);
		f.getContentPane().add(Ladang2_3);

		Slot Ladang2_4 = new Slot(508, 230, true);
		Ladang2_4.setBounds(508, 230, 110, 160);
		f.getContentPane().add(Ladang2_4);

		Slot Ladang2_5 = new Slot(686, 230, true);
		Ladang2_5.setBounds(686, 230, 110, 160);
		f.getContentPane().add(Ladang2_5);

		Slot Ladang3_1 = new Slot(10, 411, true);
		Ladang3_1.setBounds(10, 411, 110, 160);
		f.getContentPane().add(Ladang3_1);

		Slot Ladang3_2 = new Slot(171, 411, true);
		Ladang3_2.setBounds(171, 411, 110, 160);
		f.getContentPane().add(Ladang3_2);

		Slot Ladang3_3 = new Slot(344, 411, true);
		Ladang3_3.setBounds(344, 411, 110, 160);
		f.getContentPane().add(Ladang3_3);

		Slot Ladang3_4 = new Slot(508, 411, true);
		Ladang3_4.setBounds(508, 411, 110, 160);
		f.getContentPane().add(Ladang3_4);

		Slot Ladang3_5 = new Slot(686, 411, true);
		Ladang3_5.setBounds(686, 411, 110, 160);
		f.getContentPane().add(Ladang3_5);

		Slot Ladang4_5 = new Slot(686, 592, true);
		Ladang4_5.setBounds(686, 592, 110, 160);
		f.getContentPane().add(Ladang4_5);

		Slot Ladang4_4 = new Slot(508, 592, true);
		Ladang4_4.setBounds(508, 592, 110, 160);
		f.getContentPane().add(Ladang4_4);

		Slot Ladang4_3 = new Slot(344, 592, true);
		Ladang4_3.setBounds(344, 592, 110, 160);
		f.getContentPane().add(Ladang4_3);

		Slot Ladang4_2 = new Slot(171, 592, true);
		Ladang4_2.setBounds(171, 592, 110, 160);
		f.getContentPane().add(Ladang4_2);

		Slot Ladang4_1 = new Slot(10, 592, true);
		Ladang4_1.setBounds(10, 592, 110, 160);
		f.getContentPane().add(Ladang4_1);

		slots.add(Ladang1_4);
		slots.add(Ladang1_1);
		slots.add(Ladang1_2);
		slots.add(Ladang1_3);
		slots.add(Ladang1_5);
		slots.add(Ladang2_1);
		slots.add(Ladang2_2);
		slots.add(Ladang2_3);
		slots.add(Ladang2_4);
		slots.add(Ladang2_5);
		slots.add(Ladang3_1);
		slots.add(Ladang3_2);
		slots.add(Ladang3_3);
		slots.add(Ladang3_4);
		slots.add(Ladang3_5);
		slots.add(Ladang4_1);
		slots.add(Ladang4_2);
		slots.add(Ladang4_3);
		slots.add(Ladang4_4);
		slots.add(Ladang4_5);

		JButton TokoButton = new JButton("Toko");
		TokoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showToko();
			}
		});
		TokoButton.setBounds(1204, 273, 143, 53);
		f.getContentPane().add(TokoButton);

		ButtonGroup G = new ButtonGroup();

		JRadioButton LadangLawanButton = new JRadioButton("Ladang Lawan");
		LadangLawanButton.setBounds(1204, 196, 143, 39);
		f.getContentPane().add(LadangLawanButton);

		JRadioButton ladangkuButton = new JRadioButton("Ladangku");
		ladangkuButton.setSelected(true);
		ladangkuButton.setBounds(1204, 127, 143, 39);
		f.getContentPane().add(ladangkuButton);
		ActionListener actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearCards();
				if(ladangkuButton.isSelected()) {
					setCards((turn)%2,false);
				} else {
					setCards2((turn+1)%2,true);
//					for(int i=0;i<slots.size();i++) {
//						if(slots.get(i).isLadang()) {
//							slots.get(i).occupied = true;
//						}
//					}
				}
			}
		};

		// Add the action listener to each radio button
		ladangkuButton.addActionListener(actionListener);
		LadangLawanButton.addActionListener(actionListener);
		G.add(ladangkuButton);
		G.add(LadangLawanButton);

		JButton SaveButton = new JButton("Save State");
		SaveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.setEnabled(false);
				JFrame loadFrame = new JFrame();
				loadFrame.setSize(1440, 1080);
				
				// Add title
				JLabel loadTitle = new JLabel("Save State", SwingConstants.CENTER);
				loadTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
				loadTitle.setBounds(720, 20, 300, 60);
				loadFrame.setLayout(null);
				loadFrame.add(loadTitle);
				
				JPanel panel = new JPanel();
				panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
				panel.setBounds(600, 100, 500, 50);
			
				// Add combo box
				JComboBox<String> extOptions = new JComboBox<>();
				extOptions.setFont(new Font("Tahoma", Font.PLAIN, 20));
				List<String> supportedExtensions = new ArrayList<>();  
				supportedExtensions.add("TXT");
				supportedExtensions.add("JSON");
				supportedExtensions.add("XML");
			
				for (String ext : supportedExtensions)
					extOptions.addItem(ext);
			
				JLabel formatField = new JLabel("Format:", SwingConstants.CENTER);
				formatField.setFont(new Font("Tahoma", Font.PLAIN, 20));
				formatField.setBounds((loadFrame.getWidth() / 2) - 0, 20, 300, 60);
				
				panel.add(formatField);
				panel.add(extOptions);
				loadFrame.add(panel);
			
				JPanel panel2 = new JPanel();
				panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
				panel2.setBounds(600, 200, 500, 50);
			
				// Add folder path
				JLabel folderField = new JLabel("Folder:", SwingConstants.CENTER);
				folderField.setFont(new Font("Tahoma", Font.PLAIN, 20));
				folderField.setBounds((loadFrame.getWidth() / 2) - 150, 30, 600, 30);
				panel2.add(folderField);
				loadFrame.add(panel2);

				JTextField folderInputField = new JTextField();
				folderInputField.setFont(new Font("Tahoma", Font.PLAIN, 20));
				folderInputField.setPreferredSize(new Dimension(300, 30));
				panel2.add(folderInputField);
			
				JButton saveButton = new JButton("Save");
				saveButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
				saveButton.setBounds(600, 300, 500, 50);
				loadFrame.add(saveButton);

				JButton exit = new JButton("Keluar");
				exit.setBounds((loadFrame.getWidth()/2)-50, 600, 100, 30);
				exit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					loadFrame.dispose(); // Close the frame
					f.setEnabled(true);
				}
				});
				loadFrame.add(exit);
				loadFrame.setVisible(true);
			}
		});
		SaveButton.setBounds(1204, 369, 143, 53);
		f.getContentPane().add(SaveButton);

		JButton LoadButton = new JButton("Load State");
		LoadButton.setBounds(1204, 467, 143, 53);
		LoadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.setEnabled(false);
				f.setFocusableWindowState(false);
				JFrame loadFrame = new JFrame();
				loadFrame.setSize(1440, 1080);
				
				// Add title
				JLabel loadTitle = new JLabel("Load State", SwingConstants.CENTER);
				loadTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
				loadTitle.setBounds(720, 20, 300, 60);
				loadFrame.setLayout(null);
				loadFrame.add(loadTitle);
				
				JPanel panel = new JPanel();
				panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
				panel.setBounds(600, 100, 500, 50);
			
				// Add combo box
				JComboBox<String> extOptions = new JComboBox<>();
				extOptions.setFont(new Font("Tahoma", Font.PLAIN, 20));
				List<String> supportedExtensions = new ArrayList<>();  
				supportedExtensions.add("TXT");
				supportedExtensions.add("JSON");
				supportedExtensions.add("XML");
			
				for (String ext : supportedExtensions)
					extOptions.addItem(ext);
			
				JLabel formatField = new JLabel("Format:", SwingConstants.CENTER);
				formatField.setFont(new Font("Tahoma", Font.PLAIN, 20));
				formatField.setBounds((loadFrame.getWidth() / 2) - 0, 20, 300, 60);
				
				panel.add(formatField);
				panel.add(extOptions);
				loadFrame.add(panel);
			
				JPanel panel2 = new JPanel();
				panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
				panel2.setBounds(600, 200, 500, 50);
			
				// Add folder path
				JLabel folderField = new JLabel("Folder:", SwingConstants.CENTER);
				folderField.setFont(new Font("Tahoma", Font.PLAIN, 20));
				folderField.setBounds((loadFrame.getWidth() / 2) - 150, 30, 600, 30);
				panel2.add(folderField);
				loadFrame.add(panel2);

				JTextField folderInputField = new JTextField();
				folderInputField.setFont(new Font("Tahoma", Font.PLAIN, 20));
				folderInputField.setPreferredSize(new Dimension(300, 30));
				panel2.add(folderInputField);
			
				JButton loadButton = new JButton("Load");
				loadButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
				loadButton.setBounds(600, 300, 500, 50);
				loadFrame.add(loadButton);
				loadButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// load folder, throw exception if invalid
					}
				});

				JButton exit = new JButton("Keluar");
				exit.setBounds((loadFrame.getWidth()/2)-50, 600, 100, 30);
				exit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					loadFrame.dispose(); // Close the frame
					f.setEnabled(true);
					f.setFocusableWindowState(true);
				}
				});
				loadFrame.add(exit);
				loadFrame.setVisible(true);
			}
		});
		f.getContentPane().add(LoadButton);

		JButton PluginButton = new JButton("Plugin");
		PluginButton.addActionListener(new ActionListener() {
			File selectedFile;
			public void actionPerformed(ActionEvent e) {
				f.setEnabled(false);
				f.setFocusable(false);
				JFrame plugin_frame = new JFrame("Plugin Frame");
				plugin_frame.setSize(1440, 1080);
				plugin_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				plugin_frame.setLayout(null);
		
				JLabel heading_plugin = new JLabel("Plugin");
				heading_plugin.setFont(new Font("Tahoma", Font.PLAIN, 30));
				heading_plugin.setBounds((plugin_frame.getWidth() / 2) - 50, 20, 100, 60); // Adjusted for better centering
				plugin_frame.add(heading_plugin);
		
				// Create a JPanel for the file plugin part
				JPanel f_plugin = new JPanel();
				f_plugin.setLayout(new BoxLayout(f_plugin, BoxLayout.X_AXIS)); // Corrected layout for vertical arrangement
				f_plugin.setBounds(450, 100, 300, 200); // Set bounds for the JPanel
		
				JLabel fileLabel = new JLabel("File Plugin:");
				fileLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center-align the label within the JPanel
		
				JButton chooseFileButton = new JButton("Choose File");
				chooseFileButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center-align the button within the JPanel
				chooseFileButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						JFileChooser fileChooser = new JFileChooser();
						FileNameExtensionFilter filter = new FileNameExtensionFilter("jar File", "jar");
						fileChooser.setFileFilter(filter);
		
						int result = fileChooser.showOpenDialog(plugin_frame);
		
						if (result == JFileChooser.APPROVE_OPTION) {
							selectedFile = fileChooser.getSelectedFile();
							fileLabel.setText("File Plugin: " + selectedFile.getName());
						}
					}
				});
		
				JButton upload_button = new JButton("Upload");
				upload_button.setBounds((plugin_frame.getWidth() / 2) - 250, 250, 500, 20);
		
				JLabel infoLabel = new JLabel();
				infoLabel.setBounds((plugin_frame.getWidth() / 2) - 250, 300, 500, 20); // Set bounds below the upload button
				infoLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the text
		
				upload_button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// panggil jar reader
						if (selectedFile != null) { // jika file valid dan berhasil
							infoLabel.setText("Plugin file loaded successfully");
							infoLabel.setForeground(Color.GREEN);
						} else {
							infoLabel.setText("Error: File is not a valid jar");
							infoLabel.setForeground(Color.RED);
						}
					}
				});
		
				JButton exit = new JButton("Keluar");
				exit.setBounds((plugin_frame.getWidth()/2)-50, 600, 100, 30);
				exit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						plugin_frame.dispose(); // Close the frame
						f.setEnabled(true);
						f.setFocusable(true);
					}
				});

				
				f_plugin.add(Box.createVerticalStrut(10));
				f_plugin.add(chooseFileButton);
				f_plugin.add(Box.createVerticalStrut(10));
				f_plugin.add(fileLabel);
				f_plugin.add(Box.createVerticalStrut(10));
		
				// Add the JPanel to the frame
				plugin_frame.add(f_plugin);
				plugin_frame.add(upload_button);
				plugin_frame.add(infoLabel);
				plugin_frame.add(exit);
		
				plugin_frame.setVisible(true);
			}
		});
		PluginButton.setBounds(1204, 565, 143, 53);
		f.getContentPane().add(PluginButton);

		JButton deck = new JButton("DECK (cur/max)");
		deck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		deck.setBounds(1134, 844, 203, 109);
		f.getContentPane().add(deck);

		JLabel player1label = new JLabel("Player 1 :");
		player1label.setFont(new Font("Tahoma", Font.PLAIN, 22));
		player1label.setBounds(866, 302, 111, 39);
		f.getContentPane().add(player1label);

		JLabel player2label = new JLabel("Player 2 :");
		player2label.setFont(new Font("Tahoma", Font.PLAIN, 22));
		player2label.setBounds(865, 383, 111, 39);
		f.getContentPane().add(player2label);

		JLabel p1gulden = new JLabel("0000");
		p1gulden.setFont(new Font("Tahoma", Font.PLAIN, 22));
		p1gulden.setBounds(976, 308, 77, 27);
		f.getContentPane().add(p1gulden);

		JLabel p2gulden = new JLabel("0000");
		p2gulden.setFont(new Font("Tahoma", Font.PLAIN, 22));
		p2gulden.setBounds(976, 389, 77, 27);
		f.getContentPane().add(p2gulden);
		JLabel cp1 = new JLabel("Current Player");
		cp1.setForeground(new Color(0, 128, 0));
		cp1.setBackground(new Color(0, 255, 0));
		cp1.setBounds(1045, 312, 90, 27);
		f.getContentPane().add(cp1);
		
		JLabel cp2 = new JLabel("Current Player");
		cp2.setForeground(new Color(0, 128, 0));
		cp2.setBackground(Color.GREEN);
		cp2.setBounds(1045, 393, 90, 27);
		f.getContentPane().add(cp2);
		cp2.setVisible(false);
		JButton nextButton = new JButton("NEXT");
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearCards();
				turn += 1;
				setCards(turn%2,false);
				ladangkuButton.setSelected(true);
				testPlayers.get(0).nextTurn();
				testPlayers.get(1).nextTurn();
			}
		});
		nextButton.setBounds(875, 228, 143, 53);
		f.getContentPane().add(nextButton);

		JLabel turnLable = new JLabel("Turn :");
		turnLable.setFont(new Font("Tahoma", Font.PLAIN, 22));
		turnLable.setBounds(923, 86, 66, 39);
		f.getContentPane().add(turnLable);

		JLabel turnCountLable = new JLabel(turn.toString());
		turnCountLable.setFont(new Font("Tahoma", Font.PLAIN, 22));
		turnCountLable.setBounds(943, 128, 32, 27);
		f.getContentPane().add(turnCountLable);
		
		///actions
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				testPlayers.get(0).nextTurn();
				testPlayers.get(1).nextTurn();
				clearCards();
				turn += 1;
				
				setCards(turn%2,false);
				ladangkuButton.setSelected(true);
				if(cp1.isVisible()) {
					cp2.setVisible(true);
					cp1.setVisible(false);
				} else {
					cp2.setVisible(false);
					cp1.setVisible(true);					
				}
				turnCountLable.setText(turn.toString());
			}
		});

		setCards(0,false);

//	    Slot Ladang2_1 = new Slot(0, 400, false);
//	    Ladang2_1.setBounds(10, 261, 110, 160);
//	    f.getContentPane().add(Ladang2_1);
//	    
//	    Slot Ladang2_2 = new Slot(0, 400, false);
//	    Ladang2_2.setBounds(171, 261, 110, 160);
//	    f.getContentPane().add(Ladang2_2);
//	    
//	    Slot Ladang2_3 = new Slot(0, 400, false);
//	    Ladang2_3.setBounds(344, 261, 110, 160);
//	    f.getContentPane().add(Ladang2_3);
//	    
//	    Slot Ladang2_4 = new Slot(0, 400, false);
//	    Ladang2_4.setBounds(519, 261, 110, 160);
//	    f.getContentPane().add(Ladang2_4);
//	    
//	    Slot Ladang2_5 = new Slot(0, 400, false);
//	    Ladang2_5.setBounds(686, 261, 110, 160);
//	    f.getContentPane().add(Ladang2_5);

		f.setSize(1440, 1080);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		System.out.println(width);
		System.out.println(height);

		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setVisible(true);
		// foo testBryan = new foo();
		// Card kartuBe = new Card(slots, testBryan);
		// kartuBe.insertSlot(11);
		// f.getContentPane().add(kartuBe);
	}
}

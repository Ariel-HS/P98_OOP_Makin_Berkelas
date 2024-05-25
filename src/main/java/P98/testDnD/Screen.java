package P98.testDnD;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Random;

import P98.Interface.Holdable;
import P98.Item.Accelerate;
import P98.Item.Delay;
import P98.Makhluk.Hewan.Herbivora;
import P98.Makhluk.Hewan.Hewan;
import P98.Makhluk.Tumbuhan.Tumbuhan;
import P98.Player.Player;
import P98.Deck.*;
import P98.GameController.GameController;
import P98.Produk.Produk;
import P98.Produk.ProdukHewan;
import P98.Produk.ProdukTumbuhan;
import P98.newComponent.*;
import P98.Interface.*;
import P98.Player.*;
import P98.Makhluk.Tumbuhan.*;
import P98.Produk.*;
import P98.Toko.*;
import P98.testDnD.TokoFrame;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionListener;
import java.io.*;
import java.lang.reflect.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class Screen {

	public JFrame frame;
	private ArrayList<Slot> slots;
	private JFrame f = new JFrame("Swing Hello World");
	//private ArrayList<Card> cardsInFocus;
	private static boolean theresAwindow = false;
	private HashMap<String,String> supportedExtensions = new HashMap<>();  

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
	/**
	 * Create the application.
	 */
	public Screen() {
		// cardsInFocus = new ArrayList<Card>();
		supportedExtensions.put("TXT", "null");

		//cardsInFocus = player1.kartuAktif;
		initialize();
	}

	public static void setTheresAWindow(boolean condition) {
		theresAwindow = condition;
	}

	public static boolean getTheresAWindow() {
		return theresAwindow;
	}
	public void setCardDeck(boolean currentPlayer) {
		Player current;
		if (currentPlayer) {
			current = GameController.getCurrentPlayer();
		} else {
			current = GameController.getPreviousPlayer();
		}
		current = GameController.getCurrentPlayer();
		ArrayList<Holdable> currentCards = current.getDeckAktif().getCleanDeck();

		for (int j = 0; j < currentCards.size(); j++) {
			// time setting up											// cards
			current.kartuAktif.add(new Card(slots, currentCards.get(j), current));
			if (currentCards.get(j).getNama().isEmpty()) continue;

			if(current.kartuAktif.get(j).getPrevPosIdx()==999) {
				for (int i = 0; i < slots.size(); i++) {
					if (!slots.get(i).occupied && !slots.get(i).isLadang()) {
						// Place the card to unoccupied hand
						current.kartuAktif.get(j).insertSlot(i);
						if(!currentPlayer) {
							current.kartuAktif.get(j).setPunyaLawan();
						} else {
							current.kartuAktif.get(j).setPunyaSaya();
						}
						f.getContentPane().add(current.kartuAktif.get(j));
					}
				}				
			}else {
				current.kartuAktif.get(j).insertSlot(current.kartuAktif.get(j).getPrevPosIdx());
				if(!currentPlayer) {
					current.kartuAktif.get(j).setPunyaLawan();
				} else {
					current.kartuAktif.get(j).setPunyaSaya();
				}
				f.getContentPane().add(current.kartuAktif.get(j));
			}
		} 
	}

	public void setCardLadang(boolean currentPlayer) {
		Player current;
		if (currentPlayer) {
			current = GameController.getCurrentPlayer();
		} else {
			current = GameController.getPreviousPlayer();
		}
		List<Holdable> currentCards = current.getLadang().getKartu();
		
		for (int j = 0; j < currentCards.size(); j++) {
			// time setting up											// cards
			if (currentCards.get(j).getNama().isEmpty()) continue;
			Card newCard = new Card(slots, currentCards.get(j), current);

			newCard.insertSlot(j);
			if(!currentPlayer) {
				newCard.setPunyaLawan();
			} else {
				newCard.setPunyaSaya();
			}
			f.getContentPane().add(newCard);			
		}
	}

	public void clearCards() {
		// System.out.println("Test here");

		GameController.getCurrentPlayer().kartuAktif.clear();
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
		// initialize game controller
		GameController.loadConfig();
		List<JButton> listButt = new ArrayList<JButton>();

		// by doing this, we prevent Swing from resizing
		// our nice component
		slots = new ArrayList<Slot>();
		f.getContentPane().setLayout(null);
		Slot Ladang1_1 = new Slot(10, 49, true);
		Ladang1_1.setLocation(10, 49);
		Slot Ladang1_2 = new Slot(171, 49, true);
		Ladang1_2.setLocation(171, 49);
		
		Slot Ladang1_3 = new Slot(332, 49, true);
		Ladang1_3.setLocation(332, 49);
		f.getContentPane().add(Ladang1_3);
		Slot Ladang1_4 = new Slot(493, 49, true);
		Ladang1_4.setLocation(493, 49);

		Slot Ladang1_5 = new Slot(654, 49, true);
		Ladang1_5.setLocation(654, 49);
		f.getContentPane().add(Ladang1_5);

		Slot tangan1 = new Slot(10, 810, false);
		tangan1.setLocation(10, 810);
		f.getContentPane().add(tangan1);

		Slot tangan2 = new Slot(171, 810, false);
		tangan2.setBounds(171, 810, 110, 160);
		f.getContentPane().add(tangan2);

		Slot tangan3 = new Slot(332, 810, false);
		tangan3.setBounds(332, 810, 110, 160);
		f.getContentPane().add(tangan3);

		Slot tangan4 = new Slot(493, 810, false);
		tangan4.setBounds(493, 810, 110, 160);
		f.getContentPane().add(tangan4);
		
		f.getContentPane().add(Ladang1_4);
		f.getContentPane().add(Ladang1_1);
		f.getContentPane().add(Ladang1_2);

		Slot tangan5 = new Slot(654, 810, false);
		tangan5.setBounds(654, 810, 110, 160);
		f.getContentPane().add(tangan5);

		Slot tangan6 = new Slot(815, 810, false);
		tangan6.setBounds(815, 810, 110, 160);
		f.getContentPane().add(tangan6);

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

		Slot Ladang2_1 = new Slot(10, 230, true);
		Ladang2_1.setBounds(10, 230, 110, 160);
		f.getContentPane().add(Ladang2_1);

		Slot Ladang2_2 = new Slot(171, 230, true);
		Ladang2_2.setBounds(171, 230, 110, 160);
		f.getContentPane().add(Ladang2_2);

		Slot Ladang2_3 = new Slot(332, 230, true);
		Ladang2_3.setBounds(332, 230, 110, 160);
		f.getContentPane().add(Ladang2_3);

		Slot Ladang2_4 = new Slot(493, 230, true);
		Ladang2_4.setBounds(493, 230, 110, 160);
		f.getContentPane().add(Ladang2_4);

		Slot Ladang2_5 = new Slot(654, 230, true);
		Ladang2_5.setBounds(654, 230, 110, 160);
		f.getContentPane().add(Ladang2_5);

		Slot Ladang3_1 = new Slot(10, 411, true);
		Ladang3_1.setBounds(10, 411, 110, 160);
		f.getContentPane().add(Ladang3_1);

		Slot Ladang3_2 = new Slot(171, 411, true);
		Ladang3_2.setBounds(171, 411, 110, 160);
		f.getContentPane().add(Ladang3_2);

		Slot Ladang3_3 = new Slot(332, 411, true);
		Ladang3_3.setBounds(332, 411, 110, 160);
		f.getContentPane().add(Ladang3_3);

		Slot Ladang3_4 = new Slot(493, 411, true);
		Ladang3_4.setBounds(493, 411, 110, 160);
		f.getContentPane().add(Ladang3_4);

		Slot Ladang3_5 = new Slot(654, 411, true);
		Ladang3_5.setBounds(654, 411, 110, 160);
		f.getContentPane().add(Ladang3_5);

		Slot Ladang4_5 = new Slot(654, 592, true);
		Ladang4_5.setBounds(654, 592, 110, 160);
		f.getContentPane().add(Ladang4_5);

		Slot Ladang4_4 = new Slot(493, 592, true);
		Ladang4_4.setBounds(493, 592, 110, 160);
		f.getContentPane().add(Ladang4_4);

		Slot Ladang4_3 = new Slot(332, 592, true);
		Ladang4_3.setBounds(332, 592, 110, 160);
		f.getContentPane().add(Ladang4_3);

		Slot Ladang4_2 = new Slot(171, 592, true);
		Ladang4_2.setBounds(171, 592, 110, 160);
		f.getContentPane().add(Ladang4_2);

		Slot Ladang4_1 = new Slot(10, 592, true);
		Ladang4_1.setBounds(10, 592, 110, 160);
		f.getContentPane().add(Ladang4_1);

		
		slots.add(Ladang1_1);
		slots.add(Ladang1_2);
		slots.add(Ladang1_3);
		slots.add(Ladang1_4);
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

		slots.add(tangan1);
		slots.add(tangan2);
		slots.add(tangan3);
		slots.add(tangan4);
		slots.add(tangan5);
		slots.add(tangan6);

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
				// clearCards();
				Integer turn = GameController.getTurn();
				GameController.getCurrentPlayer().kartuAktif.clear();
				clearCards();
				if(ladangkuButton.isSelected()) {
					// setCards((turn)%2,false);
					setCardDeck(true);
					setCardLadang(true);
				} else {
					setCardDeck(true);
					setCardLadang(false);
					// setCards2((turn+1)%2,true);
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
		listButt.add(SaveButton);
		SaveButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			SaveFrame saveFrame = new SaveFrame(f, supportedExtensions);
		}
		});
		SaveButton.setBounds(1204, 369, 143, 53);
		f.getContentPane().add(SaveButton);

		JLabel turnLable = new JLabel("Turn :");
		turnLable.setFont(new Font("Tahoma", Font.PLAIN, 22));
		turnLable.setBounds(923, 86, 66, 39);
		f.getContentPane().add(turnLable);

		JLabel turnCountLable = new JLabel("1");
		turnCountLable.setFont(new Font("Tahoma", Font.PLAIN, 22));
		turnCountLable.setBounds(943, 128, 27, 27);
		f.getContentPane().add(turnCountLable);

		JButton deck = new JButton("DECK (cur/max)");
		listButt.add(deck);
		deck.setBounds(1134, 844, 203, 109);
		f.getContentPane().add(deck);

		JLabel p1gulden = new JLabel("0");
		p1gulden.setFont(new Font("Tahoma", Font.PLAIN, 22));
		p1gulden.setBounds(976, 308, 77, 27);
		f.getContentPane().add(p1gulden);

		JLabel p2gulden = new JLabel("0");
		p2gulden.setFont(new Font("Tahoma", Font.PLAIN, 22));
		p2gulden.setBounds(976, 389, 77, 27);
		f.getContentPane().add(p2gulden);

		JButton LoadButton = new JButton("Load State");
		listButt.add(LoadButton);
		LoadButton.setBounds(1204, 467, 143, 53);
		f.getContentPane().add(LoadButton);
		LoadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoadFrame loadFrame = new LoadFrame(f, supportedExtensions);
				Integer turn = GameController.getTurn();
				// setCards(turn%2,false);
				ladangkuButton.setSelected(true);
				turnCountLable.setText(String.valueOf(turn));
				deck.setText("DECK ("+String.valueOf(GameController.getCurrentCardCount())+"/40)");
				p1gulden.setText(GameController.getPlayer1().getGulden().toString());
				p2gulden.setText(GameController.getPlayer2().getGulden().toString());
				
				clearCards();
				setCardDeck(true);
				setCardLadang(true);
			}
		});

		JButton PluginButton = new JButton("Plugin");
		PluginButton.setBounds(1204, 565, 143, 53);
		f.getContentPane().add(PluginButton);
		PluginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> results = GameController.loadPlugin();
				if (results.size()>0) {
					try{
						// System.out.println(results.size());
						for (String s:results) {
							Class pluginClass = GameController.classLoader.loadClass(s);
							// System.out.println(pluginClass.getSimpleName());
							Method method = pluginClass.getDeclaredMethod("getExtension");
							Object pluginObj = pluginClass.getDeclaredConstructor().newInstance();
							String extension = (String) method.invoke(pluginObj);
							supportedExtensions.put(extension.toUpperCase(), s);

							JOptionPane.showMessageDialog(PluginButton, 
							"Plugin extension "+extension+" berhasil dimuat");
						}
					} catch (Exception exc) {
						System.out.println(exc.getMessage());
						System.out.println("hey");
						JOptionPane.showMessageDialog(PluginButton, "Plugin gagal dimuat");
					}
				} else {
					JOptionPane.showMessageDialog(PluginButton, "Plugin gagal dimuat");
				}
			}
		});

		JLabel player1label = new JLabel("Player 1 :");
		player1label.setFont(new Font("Tahoma", Font.PLAIN, 22));
		player1label.setBounds(866, 302, 111, 39);
		f.getContentPane().add(player1label);

		JLabel player2label = new JLabel("Player 2 :");
		player2label.setFont(new Font("Tahoma", Font.PLAIN, 22));
		player2label.setBounds(865, 383, 111, 39);
		f.getContentPane().add(player2label);

		JButton nextButton = new JButton("NEXT");
		listButt.add(nextButton);
		nextButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				clearCards();
				GameController.next();
				// showShuffleWindow();
				Integer turn = GameController.getTurn();
				if (turn > 20) {
					String pemenenang = GameController.getTopPlayer();
					JOptionPane.showMessageDialog(f, "Sudah lewat 20 Turn!\n"+pemenenang+" menang!!!");
					f.dispose();
					return;
				}
				if (GameController.getCurrentPlayer().getActiveCardCount() < 6) {
					ShuffleDialog dialog = new ShuffleDialog(frame, GameController.getCurrentPlayer());
				} 
				// setCards(turn%2,false);
				setCardDeck(true);
				setCardLadang(true);
				ladangkuButton.setSelected(true);
				turnCountLable.setText(String.valueOf(turn));
				
				Random r = new Random();
				Integer x = r.nextInt(10);
				if (x == 1) {
					for (JButton butt : listButt) {
						System.out.println(butt);
						butt.setEnabled(false);
					}
					GameController.tahapSeranganBeruang(f);
					
					clearCards();
					setCardDeck(true);
					setCardLadang(true);
					System.out.println("butt");
					for (JButton butt : listButt) {
						butt.setEnabled(true);
					}
				}
			}

		});
		nextButton.setBounds(875, 228, 143, 53);
		f.getContentPane().add(nextButton);

		JButton TokoButton = new JButton("Toko");
		TokoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!Screen.getTheresAWindow()) {
					TokoFrame tokoFrame = new TokoFrame(f, GameController.toko, p1gulden, p2gulden);
					clearCards();
					setCardDeck(true);
					setCardLadang(true);
				}
			}
		});
		TokoButton.setBounds(1204, 273, 143, 53);
		f.getContentPane().add(TokoButton);

		// setCards(0,false);
		setCardDeck(true);
		setCardLadang(true);

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
//		foo testBryan = new foo();
//		Card kartuBe = new Card(slots, testBryan);
//		kartuBe.insertSlot(11);
//		f.getContentPane().add(kartuBe);

		ShuffleDialog dialog = new ShuffleDialog(frame, GameController.getCurrentPlayer());
		setCardDeck(true);
		setCardLadang(true);
		ladangkuButton.setSelected(true);
	}

}

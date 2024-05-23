package P98.testDnD;

import java.awt.EventQueue;

import java.util.ArrayList;

import P98.newComponent.*;
import P98.Player.*;
import P98.Deck.*;
import P98.Interface.*;
import P98.newComponent.*;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;

public class Screen {

	private JFrame frame;
	private ArrayList<Player> testPlayers;
	private ArrayList<Slot> slots;
	private JFrame f = new JFrame("Swing Hello World");
	private Deck cardsInFocus;
	private static boolean theresAwindow = false;
	public static Integer turn =0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		System.setProperty("sun.java2d.uiScale", "1.0"); // Handles DPI scaling
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Screen window = new Screen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Screen() {
		testPlayers = new ArrayList<Player>();
		Player player1 = new Player(0);
		Player player2 = new Player(0);
		testPlayers.add(player1);
		testPlayers.add(player2);
	

		cardsInFocus = player1.getDeckAktif();
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
		ArrayList<Holdable> currentCards = current.getDeckAktif().getDeck();
		System.out.println(currentCards);
		for (int j = 0; j < currentCards.size(); j++) {
																									// time setting up											// cards
				current.kartuAktif.add(new Card(slots, currentCards.get(j)));
				if(current.kartuAktif.get(j).getPrevPosIdx()==999) {
					for (int i = 0; i < slots.size(); i++) {
						if (!slots.get(i).occupied && !slots.get(i).isLadang()) {
							// Place the card to unoccupied hand
							current.kartuAktif.get(j).insertSlot(i);
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
					current.kartuAktif.get(j).insertSlot(current.kartuAktif.get(j).getPrevPosIdx());
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
					setCards((turn+1)%2,true);
					
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
			}
		});
		SaveButton.setBounds(1204, 369, 143, 53);
		f.getContentPane().add(SaveButton);

		JButton LoadButton = new JButton("Load State");
		LoadButton.setBounds(1204, 467, 143, 53);
		f.getContentPane().add(LoadButton);

		JButton PluginButton = new JButton("Plugin");
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

		JButton nextButton = new JButton("NEXT");
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearCards();
				turn += 1;
				setCards(turn%2,false);
				ladangkuButton.setSelected(true);
			}
		});
		nextButton.setBounds(875, 228, 143, 53);
		f.getContentPane().add(nextButton);

		JLabel turnLable = new JLabel("Turn :");
		turnLable.setFont(new Font("Tahoma", Font.PLAIN, 22));
		turnLable.setBounds(923, 86, 66, 39);
		f.getContentPane().add(turnLable);

		JLabel turnCountLable = new JLabel("0");
		turnCountLable.setFont(new Font("Tahoma", Font.PLAIN, 22));
		turnCountLable.setBounds(943, 128, 21, 27);
		f.getContentPane().add(turnCountLable);

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
//		foo testBryan = new foo();
//		Card kartuBe = new Card(slots, testBryan);
//		kartuBe.insertSlot(11);
//		f.getContentPane().add(kartuBe);
	}
}

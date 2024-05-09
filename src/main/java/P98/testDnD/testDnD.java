package P98.testDnD;

import java.awt.EventQueue;

import java.util.ArrayList;

import P98.newComponent.*;

import javax.swing.JFrame;
import java.awt.Color;

public class testDnD {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testDnD window = new testDnD();
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
	public testDnD() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		JFrame f = new JFrame("Swing Hello World");

	    // by doing this, we prevent Swing from resizing
	    // our nice component
	    f.getContentPane().setLayout(null);
	    Slot Ladang1_4 = new Slot(508,49,true);
	    Ladang1_4.setLocation(508, 49);
	    Slot Ladang1_1 = new Slot(10,49,true);
	    Ladang1_1.setLocation(10, 49);
	    Slot Ladang1_2 = new Slot(171, 49,true);
	    Ladang1_2.setLocation(171, 49);
	    
	    Slot Ladang1_3 = new Slot(344, 49, true);
	    Ladang1_3.setLocation(344, 49);
	    f.getContentPane().add(Ladang1_3);
	    
	    Slot Ladang1_5 = new Slot(686, 49, true);
	    Ladang1_5.setLocation(686, 49);
	    f.getContentPane().add(Ladang1_5);
	    ArrayList<Slot> ladang = new ArrayList<Slot>();
	    ladang.add(Ladang1_4);
	    ladang.add(Ladang1_1);
	    ladang.add(Ladang1_2);
	    ladang.add(Ladang1_3);
	    ladang.add(Ladang1_5);
	    
	    Slot tangan1 = new Slot(10, 810, false);
	    tangan1.setBounds(10, 810, 110, 160);
	    f.getContentPane().add(tangan1);
	    
	    Slot tangan2 = new Slot(171, 810, false);
	    tangan2.setBounds(171, 810, 110, 160);
	    f.getContentPane().add(tangan2);
	    
	    Slot tangan3 = new Slot(344, 810, false);
	    tangan3.setBounds(344, 810, 110, 160);
	    f.getContentPane().add(tangan3);
	    
	    ladang.add(tangan1);
	    ladang.add(tangan2);
	    ladang.add(tangan3);
	    
	    //testing purpose
	    foo example1 = new foo();
	    bar example2 = new bar();
	    
	    Card mc2 = new Card(ladang,example1);
	    mc2.setLocation(20, 820);
	    Card mc = new Card(ladang,example2);
	    mc.setLocation(186, 820);
	    mc.insertSlot();
	    mc2.insertSlot();
	    mc.setBackground(new Color(0, 0, 255));
	    
	    f.getContentPane().add(mc);
	    f.getContentPane().add(mc2);
	    f.getContentPane().add(Ladang1_4);
	    f.getContentPane().add(Ladang1_1);
	    f.getContentPane().add(Ladang1_2);
	    
    
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

	    f.setSize(1920, 1080);

	    f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    f.setVisible(true);
	}
}

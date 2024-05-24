package P98.testDnD;

import P98.GameController.GameController;
import P98.newComponent.*;
import P98.Interface.*;
import P98.Player.*;
import P98.Ladang.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ShuffleDialog extends javax.swing.JDialog {
    private ArrayList<Slot> slots = new ArrayList<Slot>();

    public ShuffleDialog(java.awt.Frame parent) {
        super(parent);
        this.setSize(340, 650);
        this.setResizable(false);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setLayout(null);

		Slot Slot1 = new Slot(30, 30, false);
		Slot1.setBounds(30, 30, 110, 160);
		Slot Slot2 = new Slot(170, 30, false);
		Slot2.setBounds(170, 30, 110, 160);
		Slot Slot3 = new Slot(30, 220, false);
		Slot3.setBounds(30, 220, 110, 160);
		Slot Slot4 = new Slot(170, 220, false);
		Slot4.setBounds(170, 220, 110, 160);

		this.add(Slot1);
		this.add(Slot2);
		this.add(Slot3);
		this.add(Slot4);

		slots.add(Slot1);
		slots.add(Slot2);
		slots.add(Slot3);
		slots.add(Slot4);

        showCards();

        JButton shuffleButton = new JButton("Shuffle");
		shuffleButton.setBounds(84, 410, 143, 53);
		this.add(shuffleButton);
        shuffleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                GameController.callShuffle();
                clearCards();
                showCards();
			}
		});

        JButton confirmButton = new JButton("Confirm");
		confirmButton.setBounds(84, 493, 143, 53);
		this.add(confirmButton);
        confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                GameController.callDraw();
                dispose();
			}
		});

        this.setModal(true);
        this.setVisible(true);
    }

    private void clearCards() {
        Component[] components = this.getContentPane().getComponents();

        for (Component component : components) {
            if (component instanceof Card) {
                this.getContentPane().remove(component);
            }
        }

        this.getContentPane().revalidate();
        this.getContentPane().repaint();
        for(int i=0;i<slots.size();i++) {
        	slots.get(i).occupied = false;
        }
    }

    private void showCards() {
        ArrayList<Holdable> cards = GameController.getTopDeck();
        System.out.println("Show cards");

        for (int i = 0; i < cards.size(); i++) {
			Card newCard = new Card(slots, cards.get(i));
            newCard.setCanMove(false);
            for (int j = 0; j < slots.size(); j++) {
                if (!slots.get(j).occupied) {
                    // Place the card to unoccupied hand
                    newCard.insertSlot(i, new Ladang());
                    this.getContentPane().add(newCard);
                }
            }				
		} 
    }
}

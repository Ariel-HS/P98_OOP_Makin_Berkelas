package P98.testDnD;

import java.io.File;
import java.lang.reflect.Method;
import java.util.*;
import java.util.List;

import javax.swing.*;

import P98.GameController.GameController;
import P98.Interface.Holdable;
import P98.Interface.Plugin;
import P98.Player.Player;
import P98.Produk.Produk;
import P98.newComponent.*;
import P98.Toko.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class TokoFrame extends javax.swing.JDialog {

    public TokoFrame(java.awt.Frame parent, Toko toko, JLabel p1gulden, JLabel p2gulden) {
        super(parent); 
        this.setSize(800, 800);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        List<Pair<Produk, Integer>> itemToko = toko.getItemList();
        Player player = GameController.getCurrentPlayer();
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
                            // untuk sekarang baru menghapus doang
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
                                p1gulden.setText(GameController.getPlayer1().getGulden().toString());
                                p2gulden.setText(GameController.getPlayer2().getGulden().toString());
                                dispose();
                                new TokoFrame(parent, toko, p1gulden, p2gulden);
                            } catch (Exception ex) {
                                System.err.println(ex.getMessage());
                                JOptionPane.showMessageDialog(contentPane, ex.getMessage());
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
        ArrayList<Holdable> currentCards = player.getDeckAktif().getDeck();
        ArrayList<Slot> slots = player.kartuAktif.get(0).getTemp();
        ArrayList<Card> kartuOnDisplay = new ArrayList<>();
		for (int j = 0; j < currentCards.size(); j++) {
			kartuOnDisplay.add(new Card(slots, currentCards.get(j).turnToHoldable(player), player));
        }
        idx = 0;
        while (idx < kartuOnDisplay.size()) {
            Integer yValue = 500+(35 * idx);
            
            for (int j = 0; j < 3; j++) {
                if (idx < kartuOnDisplay.size() && !kartuOnDisplay.get(idx).getIsi().getNama().equals("") 
                && kartuOnDisplay.get(idx).getIsi() instanceof Produk) {
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
                            JPanel newProductPanel = new JPanel();
                            // Integer xInteger;
                            // Integer yInteger;
                            // newProductPanel.setBounds(, , 200, 100);
                            // newProductPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                            // contentPane.add(newProductPanel);
                            // Might need to repaint the content pane for the change to be reflected
                            contentPane.repaint();
                            // Potentially update frame size if needed
                            // frame.pack();
                            p1gulden.setText(GameController.getPlayer1().getGulden().toString());
                            p2gulden.setText(GameController.getPlayer2().getGulden().toString());
                            dispose();
                            new TokoFrame(parent, toko, p1gulden, p2gulden);
                        }
                        });
                    productPanel.add(removeButton);
                } 
                idx++;
            }
        }
        this.getContentPane().add(contentPane);

        this.setModal(true);
        this.setVisible(true);
    }
}

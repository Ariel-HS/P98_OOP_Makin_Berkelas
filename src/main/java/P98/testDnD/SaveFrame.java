package P98.testDnD;

import java.io.File;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import P98.GameController.GameController;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SaveFrame extends javax.swing.JDialog {
    private ArrayList<String> supportedExtensions = new ArrayList<>();  
    private File directory;

    public SaveFrame(java.awt.Frame parent, ArrayList<String> extensions) {
        super(parent);
        for (String s: extensions) {
            supportedExtensions.add(s);
        }
        this.setSize(1440, 1080);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(null);
        
        // Add title
        JLabel loadTitle = new JLabel("Save State", SwingConstants.CENTER);
        loadTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
        loadTitle.setBounds(720, 20, 300, 60);
        this.setLayout(null);
        this.add(loadTitle);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBounds(600, 100, 500, 50);
    
        // Add combo box
        JComboBox<String> extOptions = new JComboBox<>();
        extOptions.setFont(new Font("Tahoma", Font.PLAIN, 20));
    
        for (String ext : supportedExtensions)
        extOptions.addItem(ext);
    
        JLabel formatField = new JLabel("Format:", SwingConstants.CENTER);
        formatField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        formatField.setBounds((this.getWidth() / 2) - 0, 20, 300, 60);
        
        panel.add(formatField);
        panel.add(extOptions);
        this.add(panel);
    
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
        panel2.setBounds(600, 200, 500, 50);
    
        // Add folder path
        JLabel folderField = new JLabel("Folder:", SwingConstants.CENTER);
        folderField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        folderField.setBounds((this.getWidth() / 2) - 150, 30, 600, 30);
        panel2.add(folderField);
        this.add(panel2);

        JButton uploadButton = new JButton("Upload Folder");
        uploadButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        uploadButton.setPreferredSize(new Dimension(300, 30));
        panel2.add(uploadButton);
        uploadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser openFileChooser = new JFileChooser();
                    openFileChooser.setCurrentDirectory(new File("./"));
                    openFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    
                    int retcode = openFileChooser.showOpenDialog(openFileChooser);
                    if (retcode != JFileChooser.APPROVE_OPTION) {
                        throw new Exception("No file chosen");
                    }

                    directory = openFileChooser.getSelectedFile();
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(uploadButton, exc.getMessage());
                }
            }
        });
    
        JButton saveButton = new JButton("Save");
        saveButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        saveButton.setBounds(600, 300, 500, 50);
        this.add(saveButton);
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (directory != null) {
                    String extChosen = extOptions.getSelectedItem().toString();
                    if (extChosen.equals("TXT")){
                        GameController.save(directory);
                    } // else call plugin 

                    dispose();
                }
            }
        });
    
        JButton exit = new JButton("Keluar");
        exit.setBounds((this.getWidth()/2)-50, 600, 100, 30);
        exit.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            dispose(); // Close the frame
            }
        });
        this.add(exit);

        this.setModal(true);
        this.setVisible(true);
    }
}

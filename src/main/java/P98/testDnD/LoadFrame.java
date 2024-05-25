package P98.testDnD;

import java.io.File;
<<<<<<< HEAD
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
=======
import java.util.ArrayList;
>>>>>>> refactor-banyak

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
<<<<<<< HEAD
import P98.Plugin.Plugin;
=======
>>>>>>> refactor-banyak

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoadFrame extends javax.swing.JDialog {
<<<<<<< HEAD
    private HashMap<String,String> supportedExtensions = new HashMap<>();  
    private File directory;

    public LoadFrame(java.awt.Frame parent, HashMap<String,String> extensions) {
        super(parent);
        for (Map.Entry<String,String> entry: extensions.entrySet()) {
            supportedExtensions.put(entry.getKey(), entry.getValue());
=======
    private ArrayList<String> supportedExtensions = new ArrayList<>();  
    private File directory;

    public LoadFrame(java.awt.Frame parent, ArrayList<String> extensions) {
        super(parent);
        for (String s: extensions) {
            supportedExtensions.add(s);
>>>>>>> refactor-banyak
        }
        this.setSize(1440, 1080);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(null);
        
        // Add title
        JLabel loadTitle = new JLabel("Load State", SwingConstants.CENTER);
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
    
<<<<<<< HEAD
        for (String ext : supportedExtensions.keySet())
=======
        for (String ext : supportedExtensions)
>>>>>>> refactor-banyak
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
    
        JButton loadButton = new JButton("Load");
        loadButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        loadButton.setBounds(600, 300, 500, 50);
        this.add(loadButton);
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (directory != null) {
                    String extChosen = extOptions.getSelectedItem().toString();
                    if (extChosen.equals("TXT")){
                        GameController.load(directory);
<<<<<<< HEAD
                    } else {
                        try {
                            String className = supportedExtensions.get(extChosen);
                            Class<?> pluginClass = GameController.classLoader.loadClass(className);
                            Object pluginObj = pluginClass.getDeclaredConstructor().newInstance();

                            pluginClass.getMethod("load",File.class).invoke(pluginObj, directory);
                            // if (extension.equals("XML")) {
                            //     classToLoad.getMethod("printMessage").invoke(pluginObj);
                            // }
                        } catch (Exception exc) {
                            System.out.println(exc.getMessage());
                        }                       
                    }
=======
                    } // else call plugin
>>>>>>> refactor-banyak
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

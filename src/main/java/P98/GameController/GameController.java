package P98.GameController;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;
import java.lang.reflect.*;
import P98.Hewan.*;
import P98.Tumbuhan.*;
import P98.Produk.*;
import P98.Produk.*;
import P98.Player.*;
import P98.Item.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GameController {
    private static Integer turnNumber = 0;
    private static ArrayList<Player> playerList = new ArrayList<>();
    private static ArrayList<Hewan> listHewan = new ArrayList<>();
    private static ArrayList<Produk> listProduk = new ArrayList<>();
    private static ArrayList<Tumbuhan> listTumbuhan = new ArrayList<>();
    private static ArrayList<Item> listItem = new ArrayList<>();
    private static Boolean isOn = false;

    public static void clearConfig() {
        listHewan.clear();
        listProduk.clear();
        listTumbuhan.clear();
        listItem.clear();
    }

    // public static void restartGame() {
    //     turnNumber = 0;
    //     playerList.clear();
    //     // toko.clear();
    // }

    public static void start() {
        String folderPath = "./config";
        File hewanConfig = new File(folderPath+"/hewan.txt");
        File produkConfig = new File(folderPath+"/produk.txt");
        File tumbuhanConfig = new File(folderPath+"/tanaman.txt");

        Produk dagingBeruang = new ProdukHewan("Daging Beruang",500,12);
        Hewan beruang = new Omnivora("Beruang",25,dagingBeruang);
        listHewan.add(beruang);
        listProduk.add(dagingBeruang);

        // append items

        // add players
        playerList.add(new Player());
        playerList.add(new Player());

        // append Hewan, Tumbuhan, Produk
        try {
            if (isOn) {
                throw new Exception("Permainan sudah dimulai");
            }

            Scanner produkScanner = new Scanner(produkConfig);
            int nProduk = Integer.valueOf(produkScanner.nextLine());
            System.out.println(nProduk);
            for (int i=0; i<nProduk; i++) {
                String nama = produkScanner.nextLine();
                System.out.println(nama);
                String jenis = produkScanner.nextLine();
                System.out.println(jenis);
                Integer harga = Integer.valueOf(produkScanner.nextLine());
                System.out.println(harga);
                Integer bobot = Integer.valueOf(produkScanner.nextLine());
                System.out.println(bobot);

                Produk produk;
                if (jenis.equals("Hewan")) {
                    produk = new ProdukHewan(nama, harga, bobot);
                } else if (jenis.equals("Tumbuhan")) {
                    produk = new ProdukTumbuhan(nama, harga, bobot);
                } else {
                    produkScanner.close();
                    throw new Exception("Invalid tipe produk");
                }
                
                listProduk.add(produk);
            }
            produkScanner.close();

            Scanner hewanScanner = new Scanner(hewanConfig);
            int nHewan = Integer.valueOf(hewanScanner.nextLine());
            System.out.println(nHewan);
            for (int i=0; i<nHewan; i++) {
                String nama = hewanScanner.nextLine();
                System.out.println(nama);
                String jenis = hewanScanner.nextLine();
                System.out.println(jenis);
                String produk = hewanScanner.nextLine();
                System.out.println(produk);
                Produk produkHewan = listProduk.stream().filter((p -> p.getNama().equals(produk))) // asumsi tipe sesuai
                                    .findAny().orElse(null);
                if (produkHewan == null) {
                    hewanScanner.close();
                    throw new Exception("Produk untuk hewan not found");
                }
                Integer batasPanen = Integer.valueOf(hewanScanner.nextLine());
                System.out.println(batasPanen);
                
                Hewan hewan;
                if (jenis.equals("Karnivora")) {
                    hewan = new Karnivora(nama, batasPanen, produkHewan);
                } else if (jenis.equals("Herbivora")) {
                    hewan = new Herbivora(nama, batasPanen, produkHewan);
                } else if (jenis.equals("Omnivora")) {
                    hewan = new Omnivora(nama, batasPanen, produkHewan);
                } else {;
                    hewanScanner.close();
                    throw new Exception("Invalid tipe hewan");             
                }

                listHewan.add(hewan);
            }
            hewanScanner.close();
            
            Scanner tumbuhanScanner = new Scanner(tumbuhanConfig);
            int nTumbuhan = Integer.valueOf(tumbuhanScanner.nextLine());
            System.out.println(nTumbuhan);
            for (int i=0; i<nTumbuhan; i++) {
                String nama = tumbuhanScanner.nextLine();
                System.out.println(nama);
                String produk = tumbuhanScanner.nextLine();
                System.out.println(produk);
                Produk produkTumbuhan = listProduk.stream().filter((p -> p.getNama().equals(produk))) // asumsi tipe sesuai
                                    .findAny().orElse(null);
                if (produkTumbuhan == null) {
                    tumbuhanScanner.close();
                    throw new Exception("Produk untuk tumbuhan not found");
                }
                Integer batasPanen = Integer.valueOf(tumbuhanScanner.nextLine());
                System.out.println(batasPanen);
                
                Tumbuhan tumbuhan = new Tumbuhan(nama, batasPanen, produkTumbuhan);

                listTumbuhan.add(tumbuhan);
            }
            tumbuhanScanner.close();

            // File test = new File("test.txt");
            // Scanner testScanner = new Scanner(test);
            // testScanner.close();

            isOn = true;
        } catch (FileNotFoundException e) {
            System.out.println("Config not found. Pastikan file config terdapat di folder config");
            System.out.println(e.getMessage());
            clearConfig();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            clearConfig();
        }
    }

    public static void lihatLadang() {
        // GUI Stuff
    }

    public static void lihatLadangLawan() {
        // GUI Stuff
    }

    public static void bukaToko() {
        // GUI Stuff
    }

    public static void save() {
        // GUI Stuff   
    }

    public static void load() {
        // GUI Stuff
    }

    public static void loadPlugin() {
        // GUI Stuff
        try {
            Class c = Class.forName("Plugin");
            System.out.println("Found plugin with class: " + c.getName());    
        } catch (Exception e) {
            System.out.println("Plugin not found");
        }   
    }

    public static void save(String absPath) {

    }

    public static void load(String folderPath) {
        // save state
        Integer saveTurn = turnNumber;
        ArrayList<Player> savePlayers = playerList;
        // saveToko = toko

        try {
            JFileChooser openFileChooser = new JFileChooser();
            openFileChooser.setCurrentDirectory(new File("./"));
            openFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int retcode = openFileChooser.showOpenDialog(openFileChooser);
            if (retcode != JFileChooser.APPROVE_OPTION) {
                throw new Exception("No file chosen");
            }

            File directory = openFileChooser.getSelectedFile();
            String path = directory.getAbsolutePath();
            // System.out.println(path);
            File player1 = new File(path+"/player1.txt");
            File player2 = new File(path+"/player2.txt");
            File gamestate = new File(path+"/gamestate.txt");

            Scanner player1Scanner = new Scanner(player1);
            playerList.get(0).setGulden(Integer.valueOf(player1Scanner.nextLine()));
            System.out.println(playerList.get(0).getGulden());
            Integer jumlahDeck1 = Integer.valueOf(player1Scanner.nextLine());
            System.out.println(jumlahDeck1);
            Integer jumlahDeckAktif1 = Integer.valueOf(player1Scanner.nextLine());
            System.out.println(jumlahDeckAktif1);
            for (int i=0; i<jumlahDeckAktif1; i++) {
                String kartu = player1Scanner.nextLine();
                System.out.println(kartu);
                // create Kartu
            }
            Integer jumlahLadang1 = Integer.valueOf(player1Scanner.nextLine());
            System.out.println(jumlahLadang1);
            for (int i=0; i<jumlahLadang1; i++) {
                String[] line = player1Scanner.nextLine().split(" ");
                String lokasi = line[0];
                String nama = line[1];
                String unitPanen = line[2];
                Integer nItem = Integer.valueOf(line[3]);
                for (int j=1; j<=nItem; j++) {
                    String namaItem = line[i+j];
                    // Item item = listItem.stream().filter((p -> p.getNama().equals(namaItem))) 
                    //                 .findAny().orElse(null);
                    // if (item == null) {
                    //     player1Scanner.close();
                    //     throw new Exception("Item untuk makhluk not found");
                    // }
                }

                // do things
            }
            player1Scanner.close();

            Scanner player2Scanner = new Scanner(player2);
            playerList.get(1).setGulden(Integer.valueOf(player2Scanner.nextLine()));
            System.out.println(playerList.get(1).getGulden());
            Integer jumlahDeck2 = Integer.valueOf(player2Scanner.nextLine());
            System.out.println(jumlahDeck2);
            Integer jumlahDeckAktif2 = Integer.valueOf(player2Scanner.nextLine());
            System.out.println(jumlahDeckAktif2);
            for (int i=0; i<jumlahDeckAktif2; i++) {
                String kartu = player2Scanner.nextLine();
                System.out.println(kartu);
                // create Kartu
            }
            Integer jumlahLadang2 = Integer.valueOf(player2Scanner.nextLine());
            System.out.println(jumlahLadang2);
            for (int i=0; i<jumlahLadang2; i++) {
                String[] line = player2Scanner.nextLine().split(" ");
                String lokasi = line[0];
                String nama = line[1];
                String unitPanen = line[2];
                Integer nItem = Integer.valueOf(line[3]);
                for (int j=1; j<=nItem; j++) {
                    String namaItem = line[i+j];
                    // Item item = listItem.stream().filter((p -> p.getNama().equals(namaItem))) 
                    //                 .findAny().orElse(null);
                    // if (item == null) {
                    //     player2Scanner.close();
                    //     throw new Exception("Item untuk makhluk not found");
                    // }
                }

                // do things
            }
            player2Scanner.close();
            
            Scanner gamestateScanner = new Scanner(gamestate);
            turnNumber = Integer.valueOf(gamestateScanner.nextLine());
            System.out.println(turnNumber);
            Integer nProduk = Integer.valueOf(gamestateScanner.nextLine());
            System.out.println(nProduk);
            for (int i=0; i<nProduk; i++) {
                String namaProduk = gamestateScanner.nextLine();
                System.out.println(namaProduk);
                // Produk produk = listProduk.stream().filter((p -> p.getNama().equals(namaProduk))) 
                //                     .findAny().orElse(null);
                // if (produk == null) {
                //     gamestateScanner.close();
                //     throw new Exception("Produk untuk toko not found");
                // }

                // append produk ke toko
            }
            gamestateScanner.close();
        }  catch (FileNotFoundException e) {
            System.out.println("State file not found");
            System.out.println(e.getMessage());
            turnNumber = saveTurn;
            playerList = savePlayers;
        }
        catch (Exception e) {
            turnNumber = saveTurn;
            playerList = savePlayers;
            // toko = saveToko
            System.out.println(e.getMessage());
        } 
    }
}
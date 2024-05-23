package P98.GameController;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;
import java.lang.reflect.*;

import P98.Deck.*;
import P98.Exception.NoKartuException;
import P98.Hewan.*;
import P98.Interface.Holdable;
import P98.Tumbuhan.*;
import P98.Produk.*;
import P98.Produk.*;
import P98.Player.*;
import P98.Item.*;

import javax.swing.JFileChooser;

public class GameController {
    private static Integer turnNumber = 0;
    private static Player player1 = new Player();
    private static Player player2 = new Player();
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

    public static void loadConfig() {
        String folderPath = "./config";
        File hewanConfig = new File(folderPath+"/hewan.txt");
        File produkConfig = new File(folderPath+"/produk.txt");
        File tumbuhanConfig = new File(folderPath+"/tanaman.txt");

        Produk dagingBeruang = new ProdukHewan("Daging Beruang",500,12);
        Hewan beruang = new Omnivora("Beruang",25,dagingBeruang);
        listHewan.add(beruang);
        listProduk.add(dagingBeruang);

        // append items
        listItem.add(new Accelerate());
        listItem.add(new Delay());
        listItem.add(new Destroy());
        listItem.add(new InstantHarvest());
        listItem.add(new Protect());
        listItem.add(new Trap());

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

        initDeck();
        player1.shuffleDeck();
        player2.shuffleDeck();
        // System.out.println("Here");
        // for (Holdable h: player1.getDeck().getDeck()) {
        //     h.print();
        // }
        // System.out.println("Here");
        // for (Holdable h: player2.getDeck().getDeck()) {
        //     h.print();
        // }        
        // System.out.println("Aman");
    }

    public static void initDeck() {
        // add kartu to deck
        try {
            for (Hewan h:listHewan) {
                for (int j=0;j<2;j++) {
                    player1.addToDeck(h.turnToHoldable());
                    player2.addToDeck(h.turnToHoldable());
                }
            }
            for (Produk p:listProduk) {
                if (p.getNama().equals("Labu") || p.getNama().equals("Susu") || p.getNama().equals("Jagung")) {
                    player1.addToDeck(p.turnToHoldable());
                    player2.addToDeck(p.turnToHoldable());
                }
                player1.addToDeck(p.turnToHoldable());
                player2.addToDeck(p.turnToHoldable());
            }
            for (Tumbuhan t:listTumbuhan) {
                for (int j=0;j<2;j++) {
                    player1.addToDeck(t.turnToHoldable());
                    player2.addToDeck(t.turnToHoldable());
                }
            }
            for (Item i: listItem) {
                if (i.getNama().equals("Accelerate") || i.getNama().equals("Delay") 
                    || i.getNama().equals("Instant Harvest") || i.getNama().equals("Trap")) {
                    player1.addToDeck(i.turnToHoldable());
                    player2.addToDeck(i.turnToHoldable());
                }
                player1.addToDeck(i.turnToHoldable());
                player2.addToDeck(i.turnToHoldable());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
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

    public static void load() {
        // save state
        Integer saveTurn = turnNumber;
        Player savePlayer1 = new Player(player1);
        Player savePlayer2 = new Player(player2);
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
            File player1File = new File(path+"/player1.txt");
            File player2File = new File(path+"/player2.txt");
            File gamestate = new File(path+"/gamestate.txt");

            Scanner player1Scanner = new Scanner(player1File);
            player1.setGulden(Integer.valueOf(player1Scanner.nextLine()));
            System.out.println(player1.getGulden());
            Integer jumlahDeck1 = Integer.valueOf(player1Scanner.nextLine());
            System.out.println(jumlahDeck1);
            Integer jumlahDeckAktif1 = Integer.valueOf(player1Scanner.nextLine());
            System.out.println(jumlahDeckAktif1);
            for (int i=0; i<jumlahDeckAktif1; i++) {
                String[] line = player1Scanner.nextLine().split(" ");
                String kartu = line[1];
                System.out.println(kartu);
                Holdable newKartu = createKartu(kartu);
                player1.addToDeckAktif(newKartu);
            }
            Integer jumlahLadang1 = Integer.valueOf(player1Scanner.nextLine());
            System.out.println(jumlahLadang1);
            for (int i=0; i<jumlahLadang1; i++) {
                String[] line = player1Scanner.nextLine().split(" ");
                String lokasi = line[0];
                String nama = line[1];
                String unitPanen = line[2];
                Integer nItem = Integer.valueOf(line[3]);
                for (int j=0; j<nItem; j++) {
                    String namaItem = line[4+j];
                    Item item = listItem.stream().filter((p -> p.getNama().equals(namaItem))) 
                                    .findAny().orElse(null);
                    if (item == null) {
                        player1Scanner.close();
                        // System.out.println(namaItem);
                        throw new Exception("Item untuk makhluk not found");
                    }
                }

                // do things
            }
            player1Scanner.close();

            Scanner player2Scanner = new Scanner(player2File);
            player2.setGulden(Integer.valueOf(player2Scanner.nextLine()));
            System.out.println(player2.getGulden());
            Integer jumlahDeck2 = Integer.valueOf(player2Scanner.nextLine());
            System.out.println(jumlahDeck2);
            Integer jumlahDeckAktif2 = Integer.valueOf(player2Scanner.nextLine());
            System.out.println(jumlahDeckAktif2);
            for (int i=0; i<jumlahDeckAktif2; i++) {
                String[] line = player2Scanner.nextLine().split(" ");
                String kartu = line[1];
                System.out.println(kartu);
                Holdable newKartu = createKartu(kartu);
                player2.addToDeckAktif(newKartu);
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
                    Item item = listItem.stream().filter((p -> p.getNama().equals(namaItem))) 
                                    .findAny().orElse(null);
                    if (item == null) {
                        player2Scanner.close();
                        throw new Exception("Item untuk makhluk not found");
                    }
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
                String[] line = gamestateScanner.nextLine().split(" ");
                String namaProduk = line[0].replace("_", " ");
                System.out.println(namaProduk);
                Produk produk = listProduk.stream().filter((p -> p.getNama().equals(namaProduk))) 
                                    .findAny().orElse(null);
                if (produk == null) {
                    gamestateScanner.close();
                    throw new Exception("Produk untuk toko not found");
                }
                Integer jumlahProduk = Integer.valueOf(line[1]);

                // append produk ke toko
            }
            gamestateScanner.close();

            // System.out.println("Here");
            // for (Holdable h: player1.getDeckAktif().getDeck()) {
            //     h.print();
            // }
            // System.out.println("Here");
            // for (Holdable h: player2.getDeckAktif().getDeck()) {
            //     h.print();
            // }        
            // System.out.println("Aman");
        }  catch (FileNotFoundException e) {
            System.out.println("State file not found");
            System.out.println(e.getMessage());
            turnNumber = saveTurn;
            player1 = savePlayer1;
            player2 = savePlayer2;
        }
        catch (Exception e) {
            turnNumber = saveTurn;
            player1 = savePlayer1;
            player2 = savePlayer2;
            // toko = saveToko
            System.out.println(e.getMessage());
        } 
    }

    public static Holdable createKartu(String nama) throws NoKartuException {
        for (Hewan h: listHewan) {
            if (h.getNama().equals(nama)) {
                return h.turnToHoldable();
            }
        }
        for (Produk p: listProduk) {
            if (p.getNama().equals(nama)) {
                return p.turnToHoldable();
            }
        }
        for (Tumbuhan t: listTumbuhan) {
            if(t.getNama().equals(nama)) {
                return t.turnToHoldable();
            }
        }
        for (Item i: listItem) {
            if (i.getNama().equals(nama)) {
                return i.turnToHoldable();
            }
        }

        throw new NoKartuException(nama);
    }
}
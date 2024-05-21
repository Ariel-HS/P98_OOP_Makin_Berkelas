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


        // append Hewan, Tumbuhan, Produk
        try {
            if (isOn) {
                System.out.println("Permainan sudah dimulai");
                return;
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
                    System.out.println("Invalid tipe produk");
                    clearConfig();
                    produkScanner.close();
                    return;
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
                    System.out.println("Produk untuk hewan not found");
                    clearConfig();
                    hewanScanner.close();
                    return;
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
                } else {
                    System.out.println("Invalid tipe hewan");
                    clearConfig();
                    hewanScanner.close();
                    return;                    
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
                    System.out.println("Produk untuk tumbuhan not found");
                    clearConfig();
                    tumbuhanScanner.close();
                    return;
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
        try {
            File player1 = new File(folderPath+"/player1.txt");
            File player2 = new File(folderPath+"/player2.txt");
            File gamestate = new File(folderPath+"/gamestate.txt");

            Scanner player1Scanner = new Scanner(player1);
            while (player1Scanner.hasNextLine()) {
                String line = player1Scanner.nextLine();
                System.out.println(line);
            }

            Scanner player2Scanner = new Scanner(player2);
            while (player2Scanner.hasNextLine()) {
                String line = player2Scanner.nextLine();
                System.out.println(line);
            }
            
            Scanner gamestateScanner = new Scanner(gamestate);
            while (gamestateScanner.hasNextLine()) {
                String line = gamestateScanner.nextLine();
                System.out.println(line);
            }

            player1Scanner.close();
            player2Scanner.close();
            gamestateScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
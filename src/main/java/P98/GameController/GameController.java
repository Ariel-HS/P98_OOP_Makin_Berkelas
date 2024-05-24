package P98.GameController;
import java.util.*;
import java.io.*;
import java.lang.reflect.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.w3c.dom.*;

// import com.google.gson.Gson;

import P98.Deck.*;
import P98.Exception.NoKartuException;
import P98.Makhluk.Hewan.*;
import P98.Interface.Holdable;
import P98.Makhluk.Tumbuhan.*;
import P98.Produk.*;
import P98.Player.*;
import P98.Plugin.Plugin;
import P98.Item.*;
import P98.App;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GameController {
    private static Integer turnNumber = 1;
    private static Player player1 = new Player();
    private static Player player2 = new Player();
    private static Player currentPlayer = player1;
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

        Player p = new Player();
        Produk dagingBeruang = new ProdukHewan("Daging Beruang",p,500,12);
        Hewan beruang = new Omnivora("Beruang", 0, 0, 0, 25, dagingBeruang, p);
        listHewan.add(beruang);
        listProduk.add(dagingBeruang);

        // append items
        listItem.add(new Accelerate(p));
        listItem.add(new Delay(p));
        listItem.add(new Destroy(p));
        listItem.add(new InstantHarvest(p));
        listItem.add(new Protect(p));
        listItem.add(new Trap(p));

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
                    produk = new ProdukHewan(nama, p, harga, bobot);
                } else if (jenis.equals("Tumbuhan")) {
                    produk = new ProdukTumbuhan(nama, p, harga, bobot);
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
                Produk produkHewan = listProduk.stream().filter((pr -> pr.getNama().equals(produk))) // asumsi tipe sesuai
                                    .findAny().orElse(null);
                if (produkHewan == null) {
                    hewanScanner.close();
                    throw new Exception("Produk untuk hewan not found");
                }
                Integer batasPanen = Integer.valueOf(hewanScanner.nextLine());
                System.out.println(batasPanen);
                
                Hewan hewan;
                if (jenis.equals("Karnivora")) {
                    hewan = new Karnivora(nama, 0, 0, 0, batasPanen, produkHewan, p);
                } else if (jenis.equals("Herbivora")) {
                    hewan = new Herbivora(nama, 0, 0, 0, batasPanen, produkHewan, p);
                } else if (jenis.equals("Omnivora")) {
                    hewan = new Omnivora(nama, 0, 0, 0, batasPanen, produkHewan, p);
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
                Produk produkTumbuhan = listProduk.stream().filter((pr -> pr.getNama().equals(produk))) // asumsi tipe sesuai
                                    .findAny().orElse(null);
                if (produkTumbuhan == null) {
                    tumbuhanScanner.close();
                    throw new Exception("Produk untuk tumbuhan not found");
                }
                Integer batasPanen = Integer.valueOf(tumbuhanScanner.nextLine());
                System.out.println(batasPanen);
                
                Tumbuhan tumbuhan = new Tumbuhan(nama, 0, 0, 0, batasPanen, produkTumbuhan, p);

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

        player1.setDeck(randomDeck(40));
        player2.setDeck(randomDeck(40));
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

    public static Deck randomDeck(Integer num) {
        Deck deck = new Deck();
        for (Hewan h:listHewan) {
            for (int j=0;j<2;j++) {
                deck.addKartu(h.turnToHoldable());
            }
        }
        for (Produk p:listProduk) {
            if (p.getNama().equals("Labu") || p.getNama().equals("Susu") || p.getNama().equals("Jagung")) {
                deck.addKartu(p.turnToHoldable());
            }
            deck.addKartu(p.turnToHoldable());
        }
        for (Tumbuhan t:listTumbuhan) {
            for (int j=0;j<2;j++) {
                deck.addKartu(t.turnToHoldable());
            }
        }
        for (Item i: listItem) {
            if (i.getNama().equals("Accelerate") || i.getNama().equals("Delay") 
                || i.getNama().equals("Instant Harvest") || i.getNama().equals("Trap")) {
                deck.addKartu(i.turnToHoldable());
            }
            deck.addKartu(i.turnToHoldable());
        }

        deck.shuffleDeck();
        deck.drawTopKartu(40-num);
        return deck;
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

    public static void next() {
        turnNumber++;
        if (turnNumber%2 == 0) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }

        System.out.println(turnNumber);

        ArrayList<Holdable> deckAktif = currentPlayer.getDeckAktif().getDeck();
        System.out.println("Deck Aktif next");
    }

    public static String loadPlugin() {
        try {
            JFileChooser openFileChooser = new JFileChooser();
            openFileChooser.setCurrentDirectory(new File("./"));
            openFileChooser.setFileFilter(new FileNameExtensionFilter("Plugin files", "XML", "JSON"));

            int retcode = openFileChooser.showOpenDialog(openFileChooser);
            if (retcode != JFileChooser.APPROVE_OPTION) {
                throw new Exception("No file chosen");
            }

            File file = openFileChooser.getSelectedFile();
            String fileName = file.toString();
            int index = fileName.lastIndexOf(".");
            String extension = fileName.substring(index+1);
            
            if (extension.equals("xml")) {
                return loadPluginXML(file);
            } else {
                return loadPluginJSON(file);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return "Plugin not found";
        }
    }

    public static String loadPluginXML(File file) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(file);

            NodeList nodeList = doc.getElementsByTagName("Plugin");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() != Element.TEXT_NODE) {
                    // get Plugin class
                    Class<?> aClass = Class.forName("P98.Plugin."+node.getNodeName());
                    // create instance of Plugin class
                    Plugin pluginObj = (Plugin) aClass.getConstructor(String.class, String.class)
                                        .newInstance(node.getAttributes().getNamedItem("nama").getNodeValue(), 
                                        node.getAttributes().getNamedItem("message").getNodeValue());

                    // call method
                    return (String) aClass.getMethod("printMessage").invoke(pluginObj);
                }
            }

            return "Plugin not found";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();

            return "Plugin not found";
        }
        
    }

    public static String loadPluginJSON(File file) {
        try {
            JSONObject jObject = (JSONObject)JSONValue.parse(new FileReader(file));

            if (jObject.containsKey("Plugin")) {
                JSONObject jPlugin = (JSONObject)jObject.get("Plugin");
                if (jPlugin.containsKey("nama") && jPlugin.containsKey("message")) {
                    Class<?> aClass = Class.forName("P98.Plugin.Plugin");
                    // create instance of Plugin class
                    Plugin pluginObj = (Plugin) aClass.getConstructor(String.class, String.class)
                                        .newInstance(jPlugin.get("nama"), 
                                        jPlugin.get("message"));

                    // call method
                    return (String) aClass.getMethod("printMessage").invoke(pluginObj);
                }
            }

            return "Plugin not found";

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return "Plugin not found";
        }
        
    }

    public static void save(File directory) {
        try {
            String path = directory.getAbsolutePath();
            // System.out.println(path);
            FileWriter player1File = new FileWriter(path+"/player1.txt");
            FileWriter player2File = new FileWriter(path+"/player2.txt");
            FileWriter gamestate = new FileWriter(path+"/gamestate.txt");

            PrintWriter player1Writer = new PrintWriter(player1File);
            PrintWriter player2Writer = new PrintWriter(player2File);
            PrintWriter gamestateWriter = new PrintWriter(gamestate);

            gamestateWriter.println(turnNumber);
            // print toko
            gamestateWriter.close();

            player1Writer.println(player1.getGulden().toString());
            player1Writer.println(player1.getDeckCardCount().toString());
            player1Writer.println(player1.getActiveCardCount().toString());
            for (Holdable h:player1.getDeckAktif().getDeck()) {
                player1Writer.println(h.getNama());
            }
            // print ladang
            player1Writer.close();

            player2Writer.println(player2.getGulden().toString());
            player2Writer.println(player2.getDeckCardCount().toString());
            player2Writer.println(player2.getActiveCardCount().toString());
            for (Holdable h:player2.getDeckAktif().getDeck()) {
                player2Writer.println(h.getNama());
            }
            player2Writer.close();

        } catch (Exception e) {
            // toko = saveToko
            System.out.println(e.getMessage());
        } 
    }

    public static void load(File directory) {
        try {
            String path = directory.getAbsolutePath();
            System.out.println(path);
            File player1File = new File(path+"/player1.txt");
            File player2File = new File(path+"/player2.txt");
            File gamestate = new File(path+"/gamestate.txt");

            Integer newTurn;
            Player newPlayer1 = new Player();
            Player newPlayer2 = new Player();
            // Toko newToko = new Toko();

            Scanner player1Scanner = new Scanner(player1File);
            newPlayer1.setGulden(Integer.valueOf(player1Scanner.nextLine()));
            System.out.println(newPlayer1.getGulden());
            Integer jumlahDeck1 = Integer.valueOf(player1Scanner.nextLine());
            System.out.println(jumlahDeck1);
            newPlayer1.setDeck(randomDeck(jumlahDeck1));
            Integer jumlahDeckAktif1 = Integer.valueOf(player1Scanner.nextLine());
            System.out.println(jumlahDeckAktif1);
            for (int i=0; i<jumlahDeckAktif1; i++) {
                String[] line = player1Scanner.nextLine().split(" ");
                String kartu = line[1];
                System.out.println(kartu);
                Holdable newKartu = createKartu(kartu);
                newPlayer1.addToDeckAktif(newKartu);
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

                    // add item to makhluk
                }

                // add makhluk to ladang
            }
            player1Scanner.close();

            Scanner player2Scanner = new Scanner(player2File);
            newPlayer2.setGulden(Integer.valueOf(player2Scanner.nextLine()));
            System.out.println(newPlayer2.getGulden());
            Integer jumlahDeck2 = Integer.valueOf(player2Scanner.nextLine());
            System.out.println(jumlahDeck2);
            newPlayer2.setDeck(randomDeck(jumlahDeck2));
            Integer jumlahDeckAktif2 = Integer.valueOf(player2Scanner.nextLine());
            System.out.println(jumlahDeckAktif2);
            for (int i=0; i<jumlahDeckAktif2; i++) {
                String[] line = player2Scanner.nextLine().split(" ");
                String kartu = line[1];
                System.out.println(kartu);
                Holdable newKartu = createKartu(kartu);
                newPlayer2.addToDeckAktif(newKartu);
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

                    // add item to makhluk
                }

                // add makhluk to ladang
            }
            player2Scanner.close();
            
            Scanner gamestateScanner = new Scanner(gamestate);
            newTurn = Integer.valueOf(gamestateScanner.nextLine());
            System.out.println(newTurn);
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

                // add produk to toko
            }
            gamestateScanner.close();

            turnNumber = newTurn;
            player1 = newPlayer1;
            player2 = newPlayer2;

            if (turnNumber%2 == 0) {
                currentPlayer = player2;
            } else {
                currentPlayer = player1;
            }
            // toko = newToko
            // System.out.println("Here ps");
            // for (Holdable h: player1.getDeck().getDeck()) {
            //     h.print();
            // }
            // System.out.println("Here ps");
            // for (Holdable h: player2.getDeck().getDeck()) {
            //     h.print();
            // }        
            // System.out.println("Here ak");
            // for (Holdable h: player1.getDeckAktif().getDeck()) {
            //     h.print();
            // }
            // System.out.println("Here ak");
            // for (Holdable h: player2.getDeckAktif().getDeck()) {
            //     h.print();
            // }        
            // System.out.println("Aman");
        }  catch (FileNotFoundException e) {
            System.out.println("State file not found");
            System.out.println(e.getMessage());
        }
        catch (Exception e) {
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

    public static Integer getCurrentCardCount() {
        return currentPlayer.getDeckCardCount();
    }

    public static Deck getCurrentDeck() {
        return currentPlayer.getDeck();
    }

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    public static Player getPreviousPlayer() {
        if (turnNumber % 2 == 0) {
            return player1;
        }

        return player2;
    }
    
    public static Integer getTurn() {
        return turnNumber;
    }

    public static ArrayList<Holdable> getTopDeck() {
        return currentPlayer.getTopDeck();
    }

    public static void callDraw() {
        currentPlayer.draw();
        ArrayList<Holdable> deckAktif = currentPlayer.getDeckAktif().getDeck();
        System.out.println("Deck Aktif draw");
    }

    public static void callShuffle() {
        currentPlayer.shuffleDeck();
    }
}

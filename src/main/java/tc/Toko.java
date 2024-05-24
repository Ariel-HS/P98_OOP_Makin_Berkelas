package tc;
import java.util.*;
public class Toko {
    private List<Pair<Produk,Integer>> itemList;

    public Toko() {
        itemList = new ArrayList<>();
    }
    
    public List<Pair<Produk,Integer>> getItemList(){
    	return itemList;
    }

    public void sellProduk(Produk p) {
        boolean found = false;
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getFirst().equals(p)) {
                Pair<Produk,Integer> item = itemList.get(i);
                item.setSecond(item.getSecond() + 1);

                found = true;
            }
        }

        if (!found) {
            itemList.add(new Pair<Produk,Integer>(p, 0)); // turn to produk dihapus sementara
        }
    }

    public void buyProduk(Produk p){ // exception handling dihapus sementara, found dicomment
        //boolean found = false;
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getFirst().equals(p)) {
                Pair<Produk,Integer> item = itemList.get(i);
                item.setSecond(item.getSecond() - 1);
                
                if (item.getSecond() == 0) {
                    itemList.remove(i);
                }

                //found = true;
            }
        }
    }
}
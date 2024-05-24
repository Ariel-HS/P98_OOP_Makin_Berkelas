package P98.Exception;

import java.lang.Exception;

public class TokoNoProdukException extends Exception {
    public TokoNoProdukException(String self) {
        super("Tidak ada produk " + self + " dalam toko!");
    }
}
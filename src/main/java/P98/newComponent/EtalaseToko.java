package P98.newComponent;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class EtalaseToko extends JComponent {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image image;
    private String nama;
    private String jumlah;
    private String harga;
    private int rectX = 10, rectY = 10, rectWidth = 200, rectHeight = 100;
    
    public String getNama() {
    	return this.nama;
    }
    
    public String getJumlah() {
    	return this.jumlah;
    }
    
    public String getHarga() {
    	return this.harga;
    }
	public void determineImage() {
		if (this.getNama().equals("Domba")) {
			//System.out.println("Working Directory = " + System.getProperty("user.dir"));
			image = new ImageIcon(getClass().getResource("/Assets/Hewan/mareep.png")).getImage();
		} else if (this.getNama().equals("Beruang")) {
			image = new ImageIcon(getClass().getResource("/Assets/Hewan/ursaring.png")).getImage();
		} else if (this.getNama().equals("Hiu Darat")) {
			image = new ImageIcon(getClass().getResource("/Assets/Hewan/sharpedo.png")).getImage();
		} else if (this.getNama().equals("Sapi")) {
			image = new ImageIcon(getClass().getResource("/Assets/Hewan/miltank.png")).getImage();
		} else if (this.getNama().equals("Kuda")) {
			image = new ImageIcon(getClass().getResource("/Assets/Hewan/rapidash.png")).getImage();
		} else if (this.getNama().equals("Ayam")) {
			image = new ImageIcon(getClass().getResource("/Assets/Hewan/torchic.png")).getImage();
		} else if (this.getNama().equals("Jagung")) {
			//System.out.println("Working Directory = " + System.getProperty("user.dir"));
			image = new ImageIcon(getClass().getResource("/Assets/Produk/corn.png")).getImage();
		}else if (this.getNama().equals("Susu")) {
			image = new ImageIcon(getClass().getResource("/Assets/Produk/susu.png")).getImage();
		}
		// lanjutkan nanti
		// return pathToImage;
	}
    public EtalaseToko(Integer x, Integer y,String Nama, String Jumlah, String Harga) {
    	this.nama=Nama;
    	this.jumlah=Jumlah;
    	this.harga = Harga;
    	this.rectX = x;
    	this.rectY = y;
    	determineImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the rectangle
        g2d.drawRect(rectX, rectY, rectWidth, rectHeight);

        // Draw the image (if loaded successfully)
        if (image != null) {
            int imageX = rectX + 10;
            int imageY = rectY + 10;
            int imageWidth = 50;
            int imageHeight = 50;
            g2d.drawImage(image, imageX, imageY, imageWidth, imageHeight, null);
        }

        // Draw the strings
        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        int lineHeight = 16;
        int textX = rectX + 70;
        int textY = rectY + 20;
        String[] strings = {this.nama,"Harga",this.harga,"Jumlah",this.jumlah};
        for (String str : strings) {
            g2d.drawString(str, textX, textY);
            textY += lineHeight;
        }
    }
}
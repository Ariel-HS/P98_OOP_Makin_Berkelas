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
    private int id;
	private Image image;
    private String nama;
    private Integer jumlah;
    private Integer harga;
    private int rectX = 10, rectY = 10, rectWidth = 200, rectHeight = 100;
    
    public int getId() {
        return this.id;
    }

    public String getNama() {
    	return this.nama;
    }
    
    public Integer getJumlah() {
    	return this.jumlah;
    }
    
    public Integer getHarga() {
    	return this.harga;
    }
	public void determineImage() {
		if (this.getNama().equals("Domba")) {
			image = new ImageIcon(getClass().getResource("/Hewan/mareep.png")).getImage();
		} else if (this.getNama().equals("Beruang")) {
			image = new ImageIcon(getClass().getResource("/Hewan/ursaring.png")).getImage();
		} else if (this.getNama().equals("Hiu Darat")) {
			image = new ImageIcon(getClass().getResource("/Hewan/sharpedo.png")).getImage();
		} else if (this.getNama().equals("Sapi")) {
			image = new ImageIcon(getClass().getResource("/Hewan/miltank.png")).getImage();
		} else if (this.getNama().equals("Kuda")) {
			image = new ImageIcon(getClass().getResource("/Hewan/rapidash.png")).getImage();
		} else if (this.getNama().equals("Ayam")) {
			image = new ImageIcon(getClass().getResource("/Hewan/torchic.png")).getImage();
		} else if (this.getNama().equals("Jagung")) {
			image = new ImageIcon(getClass().getResource("/Produk/corn.png")).getImage();
		}else if (this.getNama().equals("Susu")) {
			image = new ImageIcon(getClass().getResource("/Produk/susu.png")).getImage();
		} else if (this.getNama().equals("Accelerate")){
			image = new ImageIcon(getClass().getResource("/Item/Accelerate.png")).getImage();
		} else if (this.getNama().equals("Destroy")) {
			image = new ImageIcon(getClass().getResource("/Item/Destroy.png")).getImage();
		} else if (this.getNama().equals("Delay")) {
			image = new ImageIcon(getClass().getResource("/Item/Delay.png")).getImage();
		} else if(this.getNama().equals("Instant Harvest")) {
			image = new ImageIcon(getClass().getResource("/Item/InstantHarvest.png")).getImage();
		} else if(this.getNama().equals("Protect")) {
			image = new ImageIcon(getClass().getResource("/Item/Protect.png")).getImage();
		} else if(this.getNama().equals("Trap")) {
			image = new ImageIcon(getClass().getResource("/Produk/Trap.png")).getImage();
		} else if(this.getNama().equals("Daging Beruang")) {
			image = new ImageIcon(getClass().getResource("/Produk/Daging_Beruang.png")).getImage();
		} else if(this.getNama().equals("Daging Domba")) {
			image =new ImageIcon(getClass().getResource("/Produk/Daging_Domba.png")).getImage();
		}else if(this.getNama().equals("Daging Kuda")) {
			image = new ImageIcon(getClass().getResource("/Produk/Daging_Kuda.png")).getImage();
		}else if(this.getNama().equals("Pumpkin")) {
			image = new ImageIcon(getClass().getResource("/Produk/pumpkin.png")).getImage();
		} else if(this.getNama().equals("Shark Fin")) {
			image = new ImageIcon(getClass().getResource("/Produk/shark-fin.png")).getImage();
		} else if(this.getNama().equals("Strawberry")) {
			image = new ImageIcon(getClass().getResource("/Produk/strawberry.png")).getImage();
		} else if(this.getNama().equals("Telur")) {
			image = new ImageIcon(getClass().getResource("/Produk/telur.png")).getImage();
		} else if(this.getNama().equals("Corn Seeds")) {
			image = new ImageIcon(getClass().getResource("/Tanaman/corn seeds.png")).getImage();
		} else if(this.getNama().equals("Pumpkin Seeds")) {
			image = new ImageIcon(getClass().getResource("/Tanaman/pumpkin seeds.png")).getImage();
		} else if(this.getNama().equals("Strawberry Seeds")) {
			image = new ImageIcon(getClass().getResource("/Tanaman/strawberry seeds.png")).getImage();
		} else {
			System.out.println("AAAAAAA"+this.getNama());
		}
		// lanjutkan nanti
		// return pathToImage;
	}

    public Image getImage() {
        return this.image;
    }

    public void decrJumlah() {
        jumlah--;
    }

    public EtalaseToko(int id, Integer x, Integer y,String Nama, Integer Jumlah, Integer Harga) {
        this.id = id;
    	this.nama=Nama;
    	this.jumlah= Jumlah;
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
        String[] strings = {this.nama,"Harga",this.harga.toString(),"Jumlah",this.jumlah.toString()};
        for (String str : strings) {
            g2d.drawString(str, textX, textY);
            textY += lineHeight;
        }
    }
}
package P98.Plugin;

public class Plugin {
    private String nama;
    private String message;
    
    public Plugin(String nama, String message) {
        this.nama = nama;
        this.message = message;
    }

    public void printMessage() {
        System.out.println(this.message+" ~"+this.nama+"'s final message");
    }
}

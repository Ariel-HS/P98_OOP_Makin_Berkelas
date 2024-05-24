package P98.Plugin;

public class Plugin {
    private String nama;
    private String message;
    
    public Plugin(String nama, String message) {
        this.nama = nama;
        this.message = message;
    }

    public String printMessage() {
        String message = this.message+" ~"+this.nama+"'s final message";
        System.out.println(message);
        return message;
    }
}

import java.io.*;

public class PluginJSON implements Plugin {
    public PluginJSON() {

    }

    public String getExtension() {
        return "json";
    }
    
    public void save(File directory) {
        System.out.println("testsaveJSON");
    }
    
    public void load(File directory) {
        System.out.println("testloadJSON");
    }
    
}

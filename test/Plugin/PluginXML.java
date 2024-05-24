import java.io.*;

public class PluginXML implements Plugin {
    public PluginXML() {

    }

    public String getExtension() {
        return "XML";
    }
    
    public void save(File directory) {
        System.out.println("testsaveXML");
    }
    
    public void load(File directory) {
        System.out.println("testloadXML");
    }
    
}

package P98.Interface;

import java.io.*;

public interface Plugin {
    public String getExtension();
    
    public void save(File directory);
    
    public void load(File directory);
}

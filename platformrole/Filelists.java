import java.util.*;
import java.io.*;

class Filelists{

File dir = new File(".");
File [] files = dir.listFiles(new FilenameFilter() {
    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith("max");
    }
});

for (File maxfile : files) {
    System.out.println(maxfile);
}
}}
import java.io.*;
import java.io.File;
import java.io.IOException;
import java.lang.NullPointerException;
import java.lang.String;
import java.util.*;
public class CaptureFileNames{
/*public static void visitAllDirsAndFiles(File dir) {
    process(dir);

    if (dir.isDirectory()) {
        String[] children = dir.list();
        for (int i=0; i<children.length; i++) {
            visitAllDirsAndFiles(new File(dir, children[i]));
        }
    }
}

// Process only directories under dir
public static void visitAllDirs(File dir) {
    if (dir.isDirectory()) {
        process(dir);

        String[] children = dir.list();
        for (int i=0; i<children.length; i++) {
            visitAllDirs(new File(dir, children[i]));
        }
    }
}

// Process only files under dir
public static void visitAllFiles(File dir) {
try{
    if (dir.isDirectory()) {
        String[] children = dir.list();
        for (int i=0; i<children.length; i++) {
            visitAllFiles(new File(dir, children[i]));
        }
    } else {
        CaptureFileNames.process(dir);
    }
}catch(Exception e ){}
}*/

public static File[] process(File dir)
  {
      FilenameFilter filter = new FilenameFilter()
       {
      public boolean accept(File dir, String name)
      {
          return name.endsWith("sorted");
      }
       };
    //try{
    File files[]=dir.listFiles(filter);

   //String files[]=dir.list();
//   for(int i=0;i<files.length;i++)
 //     System.out.println(files[i]);
      return files;
   // }catch(NullPointerException e){System.out.println(e);}
  }

 /* public static void main(String[] args) throws IOException
  {
    CaptureFileNames obj = new CaptureFileNames();
    File dir = new File("/Users/sdhingra/workspace/role_platform");
    CaptureFileNames.process(dir);
  }*/
}

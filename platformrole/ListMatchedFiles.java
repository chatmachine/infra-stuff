import java.io.*;
import java.lang.String;
import java.lang.System;
import java.util.*;
import java.io.File;
public class ListMatchedFiles{
File dir = new File("/Users/sdhingra");

  String[] children = dir.list();
  int index =children.length;

  
  if(index){
      // Either dir does not exist or is not a directory
  }else{
      for (int i=0; i < children.length; i++) {
          // Get filename of file or directory
          String filename = children[i];
      }
  }

  // It is also possible to filter the list of returned files.
  // This example does not return any files that start with `.'.
  FilenameFilter filter = new FilenameFilter() {
      public boolean accept(File dir, String name) {
          return !name.startsWith("R");
      }
  };
  children = dir.list(filter);

  // The list of files can also be retrieved as File objects
  File[] files = dir.listFiles();

  // This filter only returns directories
  FileFilter fileFilter = new FileFilter() {
      public boolean accept(File file) {
          return file.isDirectory();
      }
  };
  files = dir.listFiles(fileFilter);
   public static void main(String[] args){
  ListMatchedFiles obj = new ListMatchedFiles();
    for(int j=0;j<files.length;j++)
    System.out.println("file in current directory:"+this.files[j]);
  }
}

import java.io.*;
import java.lang.ArrayIndexOutOfBoundsException;
import java.lang.Exception;
import java.lang.String;
import java.lang.System;
import java.util

public class diff_platform_v1{
    public static void main(String[] args) {
        String[] lines = new String[0];
        String path = "/Users/sdhingra/twitterweb_cpu_user";
        BufferedReader br = null;
        try {
            File file = new File(path);
            br = new BufferedReader(
                 new InputStreamReader(
                 new FileInputStream(file)));
            String line;
            while( (line = br.readLine()) != null )
            {
                lines = add(line, lines);
            }
            br.close();
        }catch(IOException e) {
            System.out.println("read error: " + e.getMessage());
        }
        printdiff(lines);
    }

   private static String[] add(String s, String[] array) {
        int len = array.length;
        String[] temp = new String[len+1];
        System.arraycopy(array, 0, temp, 0, len);
        temp[len] = s;
        return temp;
    }

    private static void printdiff(String[] data) {
      String path1= "/Users/sdhingra/twitterweb_beetles";
        BufferedReader br1 = null;
       String[] tokens={"dummy","tummy"};
       String line;
       String delims = "[\\.]";
      String delim="[,]";
        String[] token={"dummy","tummy"};
      
      try {
            File file1 = new File(path1);



        for(int i = 0; i < data.length; i++)
        {
          int flag=i;
          br1 = new BufferedReader(
                 new InputStreamReader(new FileInputStream(file1)));
           token = data[i].split(delim);

            while( (line = br1.readLine()) != null )
            {

              try
              {
              tokens = line.split(delims);
               // System.out.println(tokens[0]);
              }catch(ArrayIndexOutOfBoundsException e)
              {}

                if(token[0].compareTo (tokens[0]) == 0)
                {
             //    System.out.println(token[0]+","+token[1]);
                }
                 else
                {
                  if (flag==i)
                  {
                    System.out.println(token[0]+","+token[1]);
                    flag=-1;
                  }

                }

            }
br1.close();

           }


        }catch(IOException e) {
            System.out.println("read error: " + e.getMessage());
        }
    }
}

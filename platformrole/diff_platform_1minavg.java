import java.io.*;
import java.io.IOException;
import java.lang.*;
import java.lang.ArrayIndexOutOfBoundsException;
import java.lang.Exception;
import java.lang.String;
import java.lang.System;
import java.util.*;
import java.io.FileOutputStream;
import java.lang.Math;
public class diff_platform_1minavg{
    GetYesterdayDate date = new GetYesterdayDate();
    //static File dir = new File("/home/sdhingra/"+"20111104"+"-avg");
    static File dir = new File("/home/sdhingra/"+GetYesterdayDate.GetDate()+"-avg");
    static File[] files=CaptureFileNames.process(dir);
    static int ptr=0;
    static String path="";
    public static void main(String[] args) throws IOException {
       while(ptr<files.length)
       {
        String[] lines = new String[0];
        path = files[ptr].toString();
        BufferedReader br = null;
         ptr++;
        try {
            File file = new File(path);
            br = new BufferedReader(
                 new InputStreamReader(
                 new FileInputStream(file)));
            String line;
            while( (line = br.readLine()) != null )
            {
                lines = add(line, lines);//Copying file to an array
            }
        //    br.close();
        }catch(IOException e) {
            System.out.println("read error: " + e.getMessage());
        }catch(NullPointerException e)
        {
            System.out.println("no files generated yet: " + e.getMessage());
        }
             printmax(lines);//getting max utilization values
       }
    }

   private static String[] add(String s, String[] array) {
        int len = array.length;
        String[] temp = new String[len+1];
        System.arraycopy(array, 0, temp, 0, len);
        temp[len] = s;
        return temp;
    }


  public static int getIndex(int perc, int count){
       int tmp;
       if((perc>100)||(perc<0))
       {
        System.out.println("Please provide the value between 0 and 100");
        return 0;
       }
       tmp=(int)java.lang.Math.round((perc*count*.01)+0.5);
       //System.out.println(""+count+"index"+tmp);
             return tmp;

   }
 

    private static void printmax(String[] data) {
      String file1= path;
      //String file2=dir.toString()+"/RX_metrics";
      String file3=dir.toString()+"/CPU_metrics";
      //String file4=dir.toString()+"/TX_metrics";
      //String file5=dir.toString()+"/MEM_metrics";
      //String file6=dir.toString()+"/MAP_metrics";
      //String file7=dir.toString()+"/TOT_metrics";
      String lastdate=GetYesterdayDate.GetDate();
       BufferedWriter out =null,out7=null,out2=null,out3=null,out4=null,out5=null,out6=null;
       String[] tokens={"",""};
       String line;
       String hostname="";
       String[] plat_role;
       String metric="";
       long temp1,temp2;
/*     String delims = "[\\.]";*/
       String delim="[,]";
       String[] token={"",""};
       int index95,index50; 
       int cnt; 
       long[] smartarray= new long[3349548];              
     try {
           // File file1 = new File(path1);
                temp1=1;
                temp2=1;
                cnt=0;
          //      out = new BufferedWriter(new FileWriter(file1,true));
          //      out2 =new BufferedWriter(new FileWriter(file2,true));
                out3 =new BufferedWriter(new FileWriter(file3,true));
          //      out4 =new BufferedWriter(new FileWriter(file4,true));
          //      out5 =new BufferedWriter(new FileWriter(file5,true));
          //      out6 =new BufferedWriter(new FileWriter(file6,true));
          //      out7=new BufferedWriter(new FileWriter(file7,true));    
            
        if(data.length==1)
        {
                index95=0;
                index50=0;
                plat_role=RolePlatform.getPlatformRole(file1);
                token = data[0].split(delim);
               // System.out.println(java.lang.Long.valueOf(token[0]));
                smartarray[0]=java.lang.Long.valueOf(token[1]);
                if(path.contains("CPU"))
                out3.write( plat_role[0]+","+plat_role[1]+","+smartarray[0]+","+smartarray[index95]+","+smartarray[index50]+","+lastdate+"\n");

        }   
        for(int i = 0; i < data.length; i++)
        {
            token = data[i].split(delim);
         //   tokens=data[i+1].split(delim);
            smartarray[cnt]=java.lang.Long.valueOf(token[1]);
            cnt=cnt+1;   
        }    
              if(data.length > 1){
               BestQuickSort sorter = new BestQuickSort();               //incrementing count because by default its 1 less 
               sorter.sort(smartarray,cnt) ;
               index95=getIndex(95,cnt);
               index50=getIndex(50,cnt);
               plat_role=RolePlatform.getPlatformRole(file1);
                --index95; 
                --index50;
                 --cnt; 
                if(path.contains("CPU"))
                out3.write(plat_role[0]+","+plat_role[1]+","+smartarray[cnt]+","+smartarray[index95]+","+smartarray[index50]+","+lastdate+"\n");  
               } 
                /*  else if(path.contains("TX"))
                 {
                 out4.write(hostname+","+plat_role[0]+","+plat_role[1]+","+smartarray[cnt]+","+smartarray[index95]+","+smartarray[index50]+","+lastdate+"\n");
                  cnt=0;}  
                else if(path.contains("RX"))
                 {
                 out2.write(hostname+","+plat_role[0]+","+plat_role[1]+","+smartarray[cnt]+","+smartarray[index95]+","+smartarray[index50]+","+lastdate+"\n");
                 cnt=0; 
                 }  
                else if(path.contains("MEM"))
                 {
                 out5.write(hostname+","+plat_role[0]+","+plat_role[1]+","+smartarray[cnt]+","+smartarray[index95]+","+smartarray[index50]+","+lastdate+"\n");
                 cnt=0;
                 }  
                else if(path.contains("MAP"))
                 {  
                 out6.write(hostname+","+plat_role[0]+","+plat_role[1]+","+smartarray[cnt]+","+smartarray[index95]+","+smartarray[index50]+","+lastdate+"\n");
                 cnt=0;}
                else{ // if(path.contains("TOT"))
                 out7.write(hostname+","+plat_role[0]+","+plat_role[1]+","+smartarray[cnt]+","+smartarray[index95]+","+smartarray[index50]+","+lastdate+"\n");
                 cnt=0;
                  } */
                // cnt=0;//resetting count after outputting stats
           // }  

        //out.close();
        //out2.close();
        //out3.close();
        //out4.close();
       // out5.close();
       // out6.close();
       // out7.close();       
        out3.close();    
        } catch (IOException e) {
            System.out.println("write error:" + e.getMessage());
        } catch (NumberFormatException e){
           System.out.println(" not a number:" + e.getMessage());  
        }

   }      
 
}

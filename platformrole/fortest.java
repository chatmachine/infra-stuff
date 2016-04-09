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
public class fortest{
    GetYesterdayDate date = new GetYesterdayDate();
    //static File dir = new File("/home/sdhingra/data_automation/"+"20111014"+"_cpu");
    static File dir = new File("/home/sdhingra/data_automation/"+GetYesterdayDate.GetDate());
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
      String file2=dir.toString()+"/RX_metrics";
      String file3=dir.toString()+"/CPU_metrics";
      String file4=dir.toString()+"/TX_metrics";
      String file5=dir.toString()+"/MEM_metrics";
      String file6=dir.toString()+"/MAP_metrics";
      String file7=dir.toString()+"/TOT_metrics";
      String file8=dir.toString()+"/MBWR_metrics";
      String file9=dir.toString()+"/MBWW_metrics";
      String lastdate=GetYesterdayDate.GetDate();
       BufferedWriter out =null,out7=null,out2=null,out3=null,out4=null,out5=null,out6=null,out8=null,out9=null;
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
                out2 =new BufferedWriter(new FileWriter(file2,true));
                out3 =new BufferedWriter(new FileWriter(file3,true));
                out4 =new BufferedWriter(new FileWriter(file4,true));
                out5 =new BufferedWriter(new FileWriter(file5,true));
                out6 =new BufferedWriter(new FileWriter(file6,true));
                out7 =new BufferedWriter(new FileWriter(file7,true));    
                out8 =new BufferedWriter(new FileWriter(file8,true));
                out9 =new BufferedWriter(new FileWriter(file9,true));    
            
        for(int i = 0; i < data.length-1; i++)
        {
           token = data[i].split(delim);
           tokens=data[i+1].split(delim);
           hostname = token[0];
           //metric=token[1];
            
           //System.out.println("token"+token[0]);   
           //System.out.println("tokens"+tokens[0]);
           // System.out.println(token[0].compareTo(tokens[0])==0);   
           if (token[0].compareTo(tokens[0])==0) //if hostnames are same
            {
              hostname = token[0];
             // temp1 =java.lang.Long.valueOf(token[1]);
             // temp2=Long.valueOf(tokens[1]);
              smartarray[cnt]=java.lang.Long.valueOf(token[1]);
              //smartarray[cnt+1]=temp2;
       /*      if(temp1>temp2)
               metric=token[1];
             else  
                metric=tokens[1];*/
              cnt=cnt+1;   
             //count=0; 
             //System.out.println(token[0]+"temp1"+cnt); 
            }
          else
            {                //customized quick sort to get percentile
               smartarray[cnt]=java.lang.Long.valueOf(token[1]);
               BestQuickSort sorter = new BestQuickSort();               //incrementing count because by default its 1 less 
               if(cnt==0)
               { 
                //smartarray[cnt]=java.lang.Long.valueOf(token[1]);
                index95=0;
                index50=0;
               // System.out.println("else"+"index95:"+index95+",count:"+cnt);
                plat_role=RolePlatform.getPlatformRole(file1);
                if(path.contains("CPU"))
                out3.write( hostname+","+plat_role[0]+","+plat_role[1]+","+smartarray[0]+","+smartarray[index95]+","+smartarray[index50]+","+lastdate+"\n");  
                else if(path.contains("TX"))
                 {
                 out4.write(hostname+","+plat_role[0]+","+plat_role[1]+","+smartarray[0]+","+smartarray[index95]+","+smartarray[index50]+","+lastdate+"\n");
                  }  
                else if(path.contains("RX"))
                 {
                 out2.write(hostname+","+plat_role[0]+","+plat_role[1]+","+smartarray[0]+","+smartarray[index95]+","+smartarray[index50]+","+lastdate+"\n");
                  }  
                else if(path.contains("MEM"))
                 {
                 out5.write(hostname+","+plat_role[0]+","+plat_role[1]+","+smartarray[0]+","+smartarray[index95]+","+smartarray[index50]+","+lastdate+"\n");
                  }  
               else if(path.contains("MAP"))
                  {
                 out6.write(hostname+","+plat_role[0]+","+plat_role[1]+","+smartarray[0]+","+smartarray[index95]+","+smartarray[index50]+","+lastdate+"\n");
                   }
               else if(path.contains("MBWR"))
                  {
                 out8.write(hostname+","+plat_role[0]+","+plat_role[1]+","+smartarray[0]+","+smartarray[index95]+","+smartarray[index50]+","+lastdate+"\n");
                   }
               else if(path.contains("MBWW"))
                  {
                 out9.write(hostname+","+plat_role[0]+","+plat_role[1]+","+smartarray[0]+","+smartarray[index95]+","+smartarray[index50]+","+lastdate+"\n");
                   }
                else //if(path.contains("TOT"))
                  {
                  out7.write(hostname+","+plat_role[0]+","+plat_role[1]+","+smartarray[0]+","+smartarray[index95]+","+smartarray[index50]+","+lastdate+"\n");
                  }
 
               } 
               else{  
               //smartarray[cnt]=java.lang.Long.valueOf(token[1])
               cnt++; 
               sorter.sort(smartarray,cnt) ;
               index95=getIndex(95,cnt);
               index50=getIndex(50,cnt);
               plat_role=RolePlatform.getPlatformRole(file1);
              //  System.out.println(""+index95+","+index50+","+cnt+""+"\n"); 
                --index95; 
                --index50;
                 --cnt; 
              //  System.out.println(""+index95+","+index50+","+cnt+""+"\n"); 
                if(path.contains("CPU")){
                out3.write( hostname+","+plat_role[0]+","+plat_role[1]+","+smartarray[cnt]+","+smartarray[index95]+","+smartarray[index50]+","+lastdate+"\n");  
                cnt=0;
                }
                else if(path.contains("TX"))
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
               else if(path.contains("MBWR"))
                  {
                 out8.write(hostname+","+plat_role[0]+","+plat_role[1]+","+smartarray[cnt]+","+smartarray[index95]+","+smartarray[index50]+","+lastdate+"\n");
                 cnt=0;
                   }
               else if(path.contains("MBWW"))
                  {
                 out9.write(hostname+","+plat_role[0]+","+plat_role[1]+","+smartarray[cnt]+","+smartarray[index95]+","+smartarray[index50]+","+lastdate+"\n");
                  cnt=0;
                   }
                else{ // if(path.contains("TOT"))
                 out7.write(hostname+","+plat_role[0]+","+plat_role[1]+","+smartarray[cnt]+","+smartarray[index95]+","+smartarray[index50]+","+lastdate+"\n");
                 cnt=0;
                  }
                // cnt=0;//resetting count after outputting stats
                }
           // }  

         }
       
         }          
        //out.close();
        out2.close();
        out3.close();
        out4.close();
        out5.close();
        out6.close();
        out7.close();           
        out8.close();
        out9.close();   
        } catch(IOException e) {
            System.out.println("write error: " + e.getMessage());
        }
   }      
 
}

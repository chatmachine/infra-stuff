import java.io.*;
import java.util.regex.Matcher;
import java.util.*;
import java.util.regex.*;
class CommandRT
{ 
public static Double process(String role,String platform,String timestamp,String p95th,int plat_cnt ,String q_type) 
{ 
//String[] envp = {"PATH=$PATH:$HOME/bin:$HOME/.gem/ruby/1.8/bin"};
String cmd="";
if(q_type.equals("CPU"))
{
 cmd = "cql -T 600 --dc smf1 '100 - (ts(SUM,system.cpu,colony(&quot"+"mo audubon\\.role\\."+ role +" \\&  mo audubon\\.platform\\."+platform+"\""+"),idle))'  --start &quot" + timestamp + "&quot  --end &quot" + timestamp + "&quot  --yes-i-wanna-make-an-expensive-query" ; 
//String cmd = "cql -T 600 --dc smf1 '100 - (ts(SUM,system.cpu,colony(\""+"mo audubon\.role\."+ role +" \&  mo audubon\.platform\."+platform+"),idle))'  --start "\"" + timestamp + "\""  --end "\"" + timestamp + "\"" ; 
cmd =cmd.replace("&quot","\"");        
cmd =cmd.replace("\\","");        
//System.out.println("in Command"+cmd); 
//String role1 =role;
}
if(q_type.equals("LOAD"))
{
 cmd = "cql -T 600 --dc smf1 '(ts(AVG,system.load,colony(&quot"+"mo audubon\\.role\\."+ role +" \\&  mo audubon\\.platform\\."+platform+"\""+"),1))'  --start &quot" + timestamp + "&quot  --end &quot" + timestamp + "&quot  --yes-i-wanna-make-an-expensive-query" ; 
cmd =cmd.replace("&quot","\"");        
cmd =cmd.replace("\\","");        
}

double met_dev=0.0;
String[] cmd1 = {"/bin/sh","-c",cmd };
try 
{       ProcessBuilder pb = new ProcessBuilder(cmd1);
	pb.redirectErrorStream(true);
	Process p = pb.start();
	BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));    
	//Process p=Runtime.getRuntime().exec(cmd1); 
	//p.waitFor();
	//BufferedReader reader=new BufferedReader(new InputStreamReader(p.getInputStream())); 
	String line=null;
	Pattern regexp = Pattern.compile("^(.+?\\:00)\\s+(.+?)$");
	Matcher matcher;
        String[] time_stats ={"",""};
	while(reader.ready() == false ){}//System.out.println("false");}
        while((line=reader.readLine())!=null) 
	{
        //if (role1.equals("composition"))
        //System.out.println(line);
        matcher=regexp.matcher(line);
        try{
        if ( matcher.find() && (Double.parseDouble(matcher.group(2))>=0.0)) {
         //System.out.print("in if");
          time_stats[0]=matcher.group(1);
          time_stats[1]=matcher.group(2);
          met_dev = met_dev + Math.abs(Double.parseDouble(p95th)-Double.parseDouble(time_stats[1]));
                             }
         else{
               if(line.contains("Error:"));
               line=line+'\n';
             } 
	}catch(Exception e)
        {e.printStackTrace();}	
       
        }   
        met_dev = met_dev/plat_cnt; 
       //reader.close();
       p.destroy();
          
        //System.out.println("met_dev"+met_dev); 
} 
catch(IOException e1) {
e1.printStackTrace();} 
catch(IllegalThreadStateException e2) {e2.printStackTrace();} 
//catch(InterruptedException e) {e.printStackTrace();} 
catch(Exception e3) {e3.printStackTrace();} 
return met_dev;
//System.out.println("Done"); 
}

public static String processTxErrors(String role,String platform,String timestamp,String p95th)
{
String cmd2 = "cql -T 600 --dc smf1  'avg(rate(ts(SUM, system.network, colony(&quot"+"mo audubon\\.role\\."+ role +" \\&  mo audubon\\.platform\\."+platform+"\""+"),if/eth0/tx_errs))/60)'  --start &quot" + timestamp + "&quot  --end &quot" + timestamp + "&quot --yes-i-wanna-make-an-expensive-query " ; 
cmd2 =cmd2.replace("&quot","\"");        
cmd2 =cmd2.replace("\\","");        
//System.out.println("in Command"+cmd); 
return execCommand(cmd2);
}

public static String processTxDrops(String role,String platform,String timestamp,String p95th)
{
String cmd1 = "cql -T 600 --dc smf1  'avg(rate(ts(SUM, system.network ,colony(&quot"+"mo audubon\\.role\\."+ role +" \\&  mo audubon\\.platform\\."+platform+"\""+"),if/eth0/tx_drop))/60)'  --start &quot" + timestamp + "&quot  --end &quot" + timestamp + "&quot --yes-i-wanna-make-an-expensive-query " ; 
cmd1 =cmd1.replace("&quot","\"");        
cmd1 =cmd1.replace("\\",""); 
return execCommand(cmd1);
}

private static String execCommand(String cmd){
String packet_err = "0.0";
String[] cmd1 = {"/bin/sh","-c",cmd };
try 
{       ProcessBuilder pb = new ProcessBuilder(cmd1);
	pb.redirectErrorStream(true);
	Process p = pb.start();
	BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));    
	//Process p=Runtime.getRuntime().exec(cmd1); 
	//p.waitFor();
	//BufferedReader reader=new BufferedReader(new InputStreamReader(p.getInputStream())); 
	String line=null;
	Pattern regexp = Pattern.compile("^(.+?\\:00)\\s+(.+?)$");
	Matcher matcher;
        String[] time_stats ={"",""};
	while(reader.ready() == false ){}//System.out.println("false");}
        while((line=reader.readLine())!=null) 
	{
        //if (role1.equals("composition"))
        //System.out.println(line);
        matcher=regexp.matcher(line);
        try{
        if (matcher.find() && (Double.parseDouble(matcher.group(2))>=0.0))
         {
         //System.out.print("in if");
          packet_err = matcher.group(2);
         }
         else
         {
               if(line.contains("Error:"));
               line=line+'\n';
         } 
	}catch(Exception e)
        {e.printStackTrace();}	
       
        }   
       //reader.close();
        p.destroy();
          
} 
catch(IOException e1) {
e1.printStackTrace();} 
catch(IllegalThreadStateException e2) {e2.printStackTrace();} 
//catch(InterruptedException e) {e.printStackTrace();} 
catch(Exception e3) {e3.printStackTrace();} 
return packet_err;} 
}

import java.io.*;
import java.util.regex.*;
import java.util.regex.Matcher;
public class RolePlatform {

  /**
  * Verifies that the  regular expression is extracting role,platform from filenames folloing the convention "Metric_role_platform_sorted":
  */
 // static String platform;
 // static String role;

  public static String[] getPlatformRole( String f_name ) {
    //Pattern and Matcher are used here, not String.matches(regexp),
    //since String.matches(regexp) would repeatedly compile the same
    //regular expressiona
    String[] rp={"",""};
    String filename=f_name;
    Pattern regexp = Pattern.compile("(.+?)_(.+?)__(.+)_sorted");
    Matcher matcher = regexp.matcher(filename);
        if ( matcher.find()) {
          rp[0]=matcher.group(2);
           rp[1]=matcher.group(3);
         }
        else{
          String msg = "File" + f_name + " is bad file format ";
          rp[0]="";
          rp[1]=""; 
          throw new IllegalStateException(msg);
        }
      return rp;
      }
 /* public RolePlatform(){
  platform="";
  role="";
  }*/


  /**
  * Test harness.
  */
/*  public static void main (String[] arguments) {
        String[] ropl;
       ropl=RolePlatform.getPlatformRole("/home/sdhingra/data_automation/20111011/CPU_hadoop_scribe_daemon__bumblebee_d_sorted");
        System.out.println(ropl[0]+"\n"+ropl[1]);
  }*/
} 


import java.text.*;
import java.util.*;
import java.util.Calendar;

public class  GetYesterdayDate{

public static String GetCurrentDate() {
   Calendar cal = Calendar.getInstance();
   DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
   cal.add(Calendar.DATE, 0);
   return dateFormat.format(cal.getTime()).toString();  
}

public static String GetDate() {
   Calendar cal = Calendar.getInstance();
   DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
   cal.add(Calendar.DATE, -1);
   return dateFormat.format(cal.getTime()).toString();  
}
public static String GetPreWeekDate() {
   Calendar cal = Calendar.getInstance();
   DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
   cal.add(Calendar.DATE, -8);
   return dateFormat.format(cal.getTime()).toString();  
}
public static String GetPrebiweekDate() {
   Calendar cal = Calendar.getInstance();
   DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
   cal.add(Calendar.DATE, -15);
   return dateFormat.format(cal.getTime()).toString();  
}
public static String GetPreviousDate() {
   Calendar cal = Calendar.getInstance();
   DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
   cal.add(Calendar.DATE, -2);
   return dateFormat.format(cal.getTime()).toString();  
}
public static String GetPreWeekDate(String dt) {
    //DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");	
    Date date = new Date();
    SimpleDateFormat in = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat out = new SimpleDateFormat("yyyy-MM-dd");
    ConcurrentDateFormatAccess obj = new ConcurrentDateFormatAccess();
  try{
     if(dt!=null)
     date = obj.convertStringToDate(dt) ;
     }catch(ParseException e)
     {e.printStackTrace();}
     catch(Exception e)
     {e.printStackTrace();}  
    Calendar cal = Calendar.getInstance();
   try{
   cal.setTime(date);
      } catch(NullPointerException e)
     {e.printStackTrace();}
   cal.add(Calendar.DATE, -7);
   return out.format(cal.getTime()).toString();  
}
public static String GetPrebiweekDate(String dt) {
    Date date = new Date();
    SimpleDateFormat in = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat out = new SimpleDateFormat("yyyy-MM-dd");
    ConcurrentDateFormatAccess obj = new ConcurrentDateFormatAccess();
  try{
     if(dt!=null)
     date = obj.convertStringToDate(dt) ;
     }catch(ParseException e)
     {e.printStackTrace();}
     catch(Exception e)
     {e.printStackTrace();}  
    Calendar cal = Calendar.getInstance();
   try{
   cal.setTime(date);
      } catch(NullPointerException e)
     {e.printStackTrace();}
   cal.add(Calendar.DATE, -14);
   return out.format(cal.getTime()).toString();  
}
}

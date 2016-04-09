import java.text.*;
import java.sql.Date;
import java.util.Calendar;

public class  GetYesterdayDate{
public static String GetDate() {
   Calendar cal = Calendar.getInstance();
   DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
   cal.add(Calendar.DATE, -1);
   return dateFormat.format(cal.getTime()).toString();  
}
}

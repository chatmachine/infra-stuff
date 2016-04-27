import java.util.Hashtable;
public class PlatformMapping{

public static int get_plat_core_ID(String role_plat){
 Hashtable<String, Integer> hashtable = new Hashtable<String, Integer>();
Integer n = null;
hashtable.put("katydid",8);
hashtable.put("beetle",12);
hashtable.put("pegasus",12);
hashtable.put("apollo",12);
hashtable.put("bumblebee",8);
hashtable.put("yellowjacket",8);
hashtable.put("gemini",12);
hashtable.put("scarab",12);
hashtable.put("ladybug",8);
hashtable.put("aquila",12);
hashtable.put("cygnus",4);
hashtable.put("corvis",16);
hashtable.put("draco",12);
hashtable.put("pictor",12);
hashtable.put("dioscuri",12);
hashtable.put("hydra",64);
n = hashtable.get(role_plat);
//System.out.println(n);
if(n == null)
n=new Integer("0");
return n;
}

//System.out.println("number of entries: " + hashtable.size());

}


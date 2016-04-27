import java.util.*;
public class Mem {
    private int count;
    private String role;
    private String platform;
    private double p95th;
    private double total;
    
 
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public void setP95th(String p95th) {
        this.p95th = Double.parseDouble(p95th);
    }

    public double getP95th(){
        return p95th;
    }
    
    public void setTotal(String total) {
        this.total = Double.parseDouble(total);
    }

    public double getTotal() {
        return total;
    }
}



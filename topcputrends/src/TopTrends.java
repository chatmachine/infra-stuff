import java.util.*;
public class TopTrends {
    private int count;
    private String category;
    private double u_7d;
    private double u_14d;
    private double p_7d;
    private double p_14d;
    private double p95th;
 
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public void setU_7d(String u_7d) {
        this.u_7d = Double.parseDouble(u_7d);
    }

    public double getU_7d(){
        return u_7d;
    }
    
    public void setU_14d(String u_14d) {
        this.u_14d = Double.parseDouble(u_14d);
    }

    public double getU_14d(){
        return u_14d;
    }
    
    public void setP_7d(String p_7d) {
        this.p_7d = Double.parseDouble(p_7d);
    }

    public double getP_7d(){
        return p_7d;
    }
    
    public void setP_14d(String p_14d) {
        this.p_14d = Double.parseDouble(p_14d);
    }

    public double getP_14d(){
        return p_14d;
    }
    
    public double getP95th(){
        return p95th;
    }
    
    public void setP95th(String p95th) {
        this.p95th = Double.parseDouble(p95th);
    }
}



import java.util.*;
public class ProcSpreadTrends {
    private int count;
    private String role;
    private String platform;
    private double u_7d;
    private double u_14d;
    private double p_7d;
    private double p_14d;
    private double perc;
    private double su_7d;
    private double su_14d;
    private double sp_7d;
    private double sp_14d;
    private double spread;
 
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
    
    public double getPerc(){
        return perc;
    }
    
    public void setPerc(String perc) {
        this.perc = Double.parseDouble(perc);
    }
    
    public void setSu_7d(String su_7d) {
        this.su_7d = Double.parseDouble(su_7d);
    }

    public double getSu_7d(){
        return su_7d;
    }
    
    public void setSu_14d(String su_14d) {
        this.su_14d = Double.parseDouble(su_14d);
    }

    public double getSu_14d(){
        return su_14d;
    }
    
    public void setSp_7d(String sp_7d) {
        this.sp_7d = Double.parseDouble(sp_7d);
    }

    public double getSp_7d(){
        return sp_7d;
    }
    
    public void setSp_14d(String sp_14d) {
        this.sp_14d = Double.parseDouble(sp_14d);
    }

    public double getSp_14d(){
        return sp_14d;
    }
    
    public double getSpread(){
        return spread;
    }
    
    public void setSpread(String spread) {
        this.spread = Double.parseDouble(spread);
    }
}



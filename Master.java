import java.util.*;
public class Master {
    private int count;
    private String role;
    private String platform;
    private double perc;
    private double spread;
    private double load1; 
    private double disk_util;
    private double mem_p95th;
    private double tx_p95th;
    private double rx_p95th;
    private double txpp95th;
    private double rxpp95th;
    private double l_spread;
    private double num_writes;
    private double num_reads;
    private double time_writes;
    private double time_reads;
    
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

    public void setPerc(String perc) {
        this.perc = Double.parseDouble(perc);
    }

    public double getPerc(){
        return perc;
    }
    
    public void setSpread(String spread) {
        this.spread = Double.parseDouble(spread);
    }

    public double getSpread() {
        return spread;
    }
    
    public void setLoad1(String load1) {
        this.load1 = Double.parseDouble(load1);
    }

    public double getLoad1() {
        return load1;
    }
    
    public double getTx_p95th() {
        return tx_p95th;
    }
    
    public void setTx_p95th(String tx_p95th ) {
        this.tx_p95th=Double.parseDouble(tx_p95th);
    }
    
    public double getRx_p95th() {
        return rx_p95th;
    }
    
    public void setRx_p95th(String rx_p95th ) {
        this.rx_p95th=Double.parseDouble(rx_p95th);
    }
    
   public double getRxpp95th() {
        return rxpp95th;
    }
    
    public void setRxpp95th(String rxpp95th ) {
        this.rxpp95th=Double.parseDouble(rxpp95th);
    }
    
    public double getTxpp95th() {
        return txpp95th;
    }
    
    public void setTxpp95th(String txpp95th ) {
        this.txpp95th=Double.parseDouble(txpp95th);
    }
    
    public double getMem_p95th() {
        return mem_p95th;
    }
    
    public void setMem_p95th(String mem_p95th ) {
        this.mem_p95th=Double.parseDouble(mem_p95th);
    }
  
    public double getDisk_util() {
        return disk_util;
    }
    
    public void setDisk_util(String disk_util ) {
        this.disk_util=Double.parseDouble(disk_util);
    }
    
    public double getL_spread() {
        return l_spread;
    }
    
    public void setL_spread(String l_spread) {
        this.l_spread=Double.parseDouble(l_spread);
    }
    
    public double getNum_writes() {
        return num_writes;
    }
    
    public void setNum_writes(String num_writes) {
        this.num_writes=Double.parseDouble(num_writes);
    }
    
    public double getNum_reads() {
        return num_reads;
    }
    
    public void setNum_reads(String num_reads) {
        this.num_reads=Double.parseDouble(num_reads);
    }
    
    public double getTime_writes() {
        return num_writes;
    }
    
    public void setTime_writes(String time_writes) {
        this.time_writes=Double.parseDouble(time_writes);
    }
    
    public double getTime_reads() {
        return time_reads;
    }
    
    public void setTime_reads(String time_reads) {
        this.time_reads=Double.parseDouble(time_reads);
    }
   
}



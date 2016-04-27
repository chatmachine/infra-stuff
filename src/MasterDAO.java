import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.text.*;

public class MasterDAO {
    Connection connection;
    Statement stmt;
    private int noOfRecords;
    public MasterDAO() { }
 
    private static Connection getConnection()
            throws SQLException,
                ClassNotFoundException
    {
        Connection con = ConnectionFactory.
                getInstance().getConnection();
        return con;
    }
 
    public List<Master> viewAllMetrics(
                int offset,
                int noOfRecords, String dt)
    {
      String query = "select SQL_CALC_FOUND_ROWS role ,platform , count , IFNULL(perc,-1) as perc ,IFNULL(spread,-1) as spread , IFNULL(l_spread,-1) as l_spread ,IFNULL(load1,-1) as load1 , IFNULL(rx_p95th,-1) as rx_p95th, IFNULL(tx_p95th,-1) as tx_p95th , IFNULL(rxpp95th,-1) as rxpp95th, IFNULL(txpp95th,-1) as txpp95th, IFNULL((d_used/d_total)*100,-1) as disk_util, IFNULL(mem_p95th,-1) as mem_p95th , IFNULL(num_reads,-1) as num_reads , IFNULL(num_writes,-1) as num_writes ,IFNULL(time_reads,-1) as time_reads, IFNULL(time_writes,-1) as time_writes from cpu_info_atla where date = '"+dt+"' limit " + offset + ", " + noOfRecords;
        List<Master> list = new ArrayList<Master>();
        Master metric = null;
        int core_cnt = 0;
        DecimalFormat df = new DecimalFormat("##.##");
        DecimalFormat df1 = new DecimalFormat("###.##");
        String[] splitted = null;
        try {
            connection = getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                metric = new Master();
                splitted = (rs.getString("platform")).split("_");
                core_cnt = PlatformMapping.get_plat_core_ID(splitted[0]);
                metric.setCount(core_cnt*rs.getInt("count"));
                metric.setRole(rs.getString("role"));
                metric.setPlatform(rs.getString("platform"));
                metric.setPerc(df.format(rs.getDouble("perc")));
                metric.setSpread(df.format(rs.getDouble("spread")));
                metric.setL_spread(df.format(rs.getDouble("l_spread")));
                metric.setLoad1(df.format(rs.getDouble("load1")));
                metric.setRx_p95th(df1.format(1000*rs.getDouble("rx_p95th")));
                metric.setTx_p95th(df1.format(1000*rs.getDouble("tx_p95th")));
                metric.setRxpp95th(df1.format(1000*rs.getDouble("rxpp95th")));
                metric.setTxpp95th(df1.format(1000*rs.getDouble("txpp95th")));
                metric.setMem_p95th(df.format(rs.getDouble("mem_p95th")));
                metric.setDisk_util(df.format(rs.getDouble("disk_util")));
                metric.setNum_reads(df.format(rs.getDouble("num_reads")));
                metric.setNum_writes(df.format(rs.getDouble("num_writes")));
                metric.setTime_reads(df.format(rs.getDouble("time_reads")));
                metric.setTime_writes(df.format(rs.getDouble("time_writes")));
                list.add(metric);
            }
            rs.close();
 
            rs = stmt.executeQuery("SELECT FOUND_ROWS()");
            if(rs.next())
                this.noOfRecords = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally
        {
            try {
                if(stmt != null)
                    stmt.close();
                if(connection != null)
                    connection.close();
                } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
 
    public int getNoOfRecords() {
        return noOfRecords;
    }
}


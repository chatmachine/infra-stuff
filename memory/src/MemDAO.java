import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.text.*;

public class MemDAO {
    Connection connection;
    Statement stmt;
    private int noOfRecords;
    public MemDAO() { }
 
    private static Connection getConnection()
            throws SQLException,
                ClassNotFoundException
    {
        Connection con = ConnectionFactory.
                getInstance().getConnection();
        return con;
    }
 
    public List<Mem> viewAllMetrics(
                int offset,
                int noOfRecords, String dt)
    {
      String query = "select SQL_CALC_FOUND_ROWS role ,platform , count , IFNULL(p95th,-1) as p95th, IFNULL(total,-1) as total  from mem_info_atla where date = '"+dt+"' limit "
                 + offset + ", " + noOfRecords;
        List<Mem> list = new ArrayList<Mem>();
        Mem metric = null;
        int core_cnt = 0;
        DecimalFormat df = new DecimalFormat("###.##");
        String[] splitted = null;
        try {
            connection = getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                metric = new Mem();
                splitted = (rs.getString("platform")).split("_");
                core_cnt = PlatformMapping.get_plat_core_ID(splitted[0]);
                metric.setCount(rs.getInt("count"));
                metric.setRole(rs.getString("role"));
                metric.setPlatform(rs.getString("platform"));
                metric.setP95th(df.format(rs.getDouble("p95th")));
                metric.setTotal(df.format(rs.getDouble("total")));
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


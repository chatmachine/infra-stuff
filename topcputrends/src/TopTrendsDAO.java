import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.text.*;

public class TopTrendsDAO {
    Connection connection;
    Statement stmt;
    private int noOfRecords;
    public TopTrendsDAO() { }
 
    private static Connection getConnection()
            throws SQLException,
                ClassNotFoundException
    {
        Connection con = ConnectionFactory.
                getInstance().getConnection();
        return con;
    }
 
    public List<TopTrends> viewAllMetrics(
                int offset,
                int noOfRecords, String dt)
    {
      /*String query = "select SQL_CALC_FOUND_ROWS role ,platform , count , perc ,spread from cpu_info where date = '"+dt+"' limit "
                 + offset + ", " + noOfRecords;*/
  String query = "SELECT m.category , m.count, m.p95th, ytd.p95th , tpp.p95th, (m.p95th-ytd.p95th)*100/ytd.p95th as trend2,(m.p95th-tpp.p95th)*100/tpp.p95th as trend3    FROM (SELECT *  FROM category_info_atla WHERE date = '"+GetYesterdayDate.GetDate()+"') m , (SELECT *  FROM category_info_atla WHERE date = '"+GetYesterdayDate.GetPreWeekDate()+"') ytd ,(SELECT *  FROM category_info_atla WHERE date = '"+GetYesterdayDate.GetPrebiweekDate()+"') tpp  where (m.category = ytd.category  AND ytd.category =tpp.category )" ;


        List<TopTrends> list = new ArrayList<TopTrends>();
        TopTrends metric = null;
       // int core_cnt = 0;
        DecimalFormat df = new DecimalFormat("##.##");
        String[] splitted = null;
        try {
            connection = getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                metric = new TopTrends();
             // splitted = (rs.getString("platform")).split("_");
                //core_cnt = PlatformMapping.get_plat_core_ID(splitted[0]);
                metric.setCount(rs.getInt("m.count"));
                metric.setCategory(rs.getString("m.category"));
                metric.setP95th(df.format(rs.getDouble("m.p95th")));
                metric.setU_7d(df.format(rs.getDouble("ytd.p95th")));
                metric.setU_14d(df.format(rs.getDouble("tpp.p95th")));
                metric.setP_7d(df.format(rs.getDouble("trend2")));
                metric.setP_14d(df.format(rs.getDouble("trend3")));
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


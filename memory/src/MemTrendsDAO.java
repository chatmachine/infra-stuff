import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.text.*;

public class MemTrendsDAO {
    Connection connection;
    Statement stmt;
    private int noOfRecords;
    private String query; 
    private String preweekdate = "", prebiweekdate = ""; 
    public MemTrendsDAO() { }
   
    private static Connection getConnection()
            throws SQLException,
                ClassNotFoundException
    {
        Connection con = ConnectionFactory.
                getInstance().getConnection();
        return con;
    }
 
    public List<MemTrends> viewAllMetrics(
                int offset,
                int noOfRecords, String dt)
    {
      /*String query = "select SQL_CALC_FOUND_ROWS role ,platform , count , perc ,spread from cpu_info where date = '"+dt+"' limit "
                 + offset + ", " + noOfRecords;*/
      try{
       preweekdate = GetYesterdayDate.GetPreWeekDate(dt.trim());
       prebiweekdate = GetYesterdayDate.GetPrebiweekDate(dt.trim());
         }catch(NullPointerException e)
          {
           e.printStackTrace();
          } 
      
        if(preweekdate.equals(""))
        {   
       query = "SELECT m.role ,m.platform, m.count, total, m.p95th as p95th1, ytd.p95th as p95th2 , tpp.p95th as p95th3, (m.p95th-ytd.p95th)*100/ytd.p95th as trend2,(m.p95th-tpp.p95th)*100/tpp.p95th as trend3    FROM (SELECT *  FROM mem_info WHERE date = '"+GetYesterdayDate.GetDate()+"') m , (SELECT *  FROM mem_info_atla WHERE date = '"+GetYesterdayDate.GetPreWeekDate()+"') ytd ,(SELECT *  FROM mem_info_atla WHERE date = '"+GetYesterdayDate.GetPrebiweekDate()+"') tpp  where (m.role = ytd.role AND m.platform = ytd.platform AND ytd.role =tpp.role AND ytd.platform =tpp.platform )" ;
        }
        else
         {
/*  query = "SELECT SQL_CALC_FOUND_ROWS m.role ,m.platform, m.count, m.total, m.p95th, ytd.p95th , tpp.p95th, (m.p95th-ytd.p95th)*100/ytd.p95th as trend2, (m.p95th-tpp.p95th)*100/tpp.p95th as trend3  FROM (SELECT role ,platform, count ,total, p95th  FROM mem_info WHERE date = '"+dt+"') m , (SELECT role ,platform ,count ,p95th  FROM mem_info WHERE date = '"+preweekdate+"') ytd ,(SELECT role ,platform ,count , p95th  FROM mem_info WHERE date = '"+prebiweekdate+"') tpp  where (m.role = ytd.role AND m.platform = ytd.platform AND ytd.role =tpp.role AND ytd.platform =tpp.platform )" ;*/
 query = "SELECT SQL_CALC_FOUND_ROWS m.role , m.platform, m.count, IFNULL(m.total, IFNULL(ytd.total,-1)) as total, IFNULL(m.p95th,-1) as p95th1 , IFNULL(ytd.p95th,-1) as p95th2 ,IFNULL(tpp.p95th,-1) as p95th3 , IFNULL((m.p95th-ytd.p95th)*100/ytd.p95th,-1) as trend2, IFNULL((m.p95th-tpp.p95th)*100/tpp.p95th,-1) as trend3 FROM (SELECT role , platform ,count, total as total, p95th as p95th FROM mem_info_atla WHERE date = '"+dt+"')  m LEFT OUTER JOIN ((SELECT role ,platform ,count, total , p95th  FROM mem_info_atla WHERE date = '"+preweekdate+"') AS ytd  LEFT OUTER JOIN (SELECT role ,platform ,count, total , p95th  FROM mem_info_atla WHERE date = '"+prebiweekdate+"') AS tpp ON ytd.role=tpp.role and ytd.platform =tpp.platform )  ON m.role = ytd.role and m.platform=ytd.platform";
         }
        List<MemTrends> list = new ArrayList<MemTrends>();
        MemTrends metric = null;
        int core_cnt = 0;
        DecimalFormat df = new DecimalFormat("##.##");
        String[] splitted = null;
        try {
            connection = getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                metric = new MemTrends();
                splitted = (rs.getString("platform")).split("_");
                core_cnt = PlatformMapping.get_plat_core_ID(splitted[0]);
                metric.setCount(rs.getInt("m.count"));
                metric.setTotal(df.format(rs.getDouble("total")));
                metric.setRole(rs.getString("m.role"));
                metric.setPlatform(rs.getString("m.platform"));
                metric.setP95th(df.format(rs.getDouble("p95th1")));
                metric.setU_7d(df.format(rs.getDouble("p95th2")));
                metric.setU_14d(df.format(rs.getDouble("p95th3")));
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


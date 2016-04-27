import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.text.*;

public class CpuSpreadTrendsDAO {
    Connection connection;
    Statement stmt;
    private int noOfRecords;
    private String query;
    private String preweekdate = "", prebiweekdate = ""; 
    public CpuSpreadTrendsDAO() { }
 
    private static Connection getConnection()
            throws SQLException,
                ClassNotFoundException
    {
        Connection con = ConnectionFactory.
                getInstance().getConnection();
        return con;
    }
 
    public List<ProcSpreadTrends> viewAllMetrics(
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
   /*query = "SELECT m.role ,m.platform, m.count, m.perc as p95th1, ytd.perc as p95th2 , tpp.perc as p95th3, (m.perc-ytd.perc)*100/ytd.perc as trend2,(m.perc-tpp.perc)*100/tpp.perc as trend3    FROM (SELECT *  FROM cpu_info WHERE date = '"+GetYesterdayDate.GetDate()+"') m , (SELECT *  FROM cpu_info WHERE date = '"+GetYesterdayDate.GetPreWeekDate()+"') ytd ,(SELECT *  FROM cpu_info WHERE date = '"+GetYesterdayDate.GetPrebiweekDate()+"') tpp  where (m.role = ytd.role AND m.platform = ytd.platform AND ytd.role =tpp.role AND ytd.platform =tpp.platform )" ;*/
 query = "SELECT SQL_CALC_FOUND_ROWS m.role , m.platform, m.count, IFNULL(m.perc,-1) as p95th1 , IFNULL(ytd.perc,-1) as p95th2 ,IFNULL(tpp.perc,-1) as p95th3 ,IFNULL(m.spread,-1) as spd1 , IFNULL(ytd.spread,-1) as spd2 ,IFNULL(tpp.spread,-1) as spd3 , IFNULL((m.perc-ytd.perc)*100/ytd.perc,-1) as trend2, IFNULL((m.perc-tpp.perc)*100/tpp.perc,-1) as trend3, IFNULL((m.spread-ytd.spread)*100/ytd.spread,-1) as sptrend2, IFNULL((m.spread-tpp.spread)*100/tpp.spread,-1) as sptrend3   FROM (SELECT role , platform ,count, perc, spread FROM cpu_info_atla WHERE date = '"+GetYesterdayDate.GetDate()+"')  m LEFT OUTER JOIN ((SELECT role ,platform ,count, perc, spread  FROM cpu_info_atla WHERE date = '"+GetYesterdayDate.GetPreWeekDate()+"') AS ytd  LEFT OUTER JOIN (SELECT role ,platform ,count , perc,spread  FROM cpu_info_atla WHERE date = '"+GetYesterdayDate.GetPrebiweekDate()+"') AS tpp ON ytd.role=tpp.role and ytd.platform =tpp.platform )  ON m.role = ytd.role and m.platform=ytd.platform";

        }
        else
        {
 query = "SELECT SQL_CALC_FOUND_ROWS m.role , m.platform, m.count, IFNULL(m.perc,-1) as p95th1 , IFNULL(ytd.perc,-1) as p95th2 ,IFNULL(tpp.perc,-1) as p95th3 ,IFNULL(m.spread,-1) as spd1 , IFNULL(ytd.spread,-1) as spd2 ,IFNULL(tpp.spread,-1) as spd3 , IFNULL((m.perc-ytd.perc)*100/ytd.perc,-1) as trend2, IFNULL((m.perc-tpp.perc)*100/tpp.perc,-1) as trend3, IFNULL((m.spread-ytd.spread)*100/ytd.spread,-1) as sptrend2, IFNULL((m.spread-tpp.spread)*100/tpp.spread,-1) as sptrend3   FROM (SELECT role , platform ,count, perc, spread FROM cpu_info_atla WHERE date = '"+dt+"')  m LEFT OUTER JOIN ((SELECT role ,platform ,count, perc, spread  FROM cpu_info_atla WHERE date = '"+preweekdate+"') AS ytd  LEFT OUTER JOIN (SELECT role ,platform ,count , perc,spread  FROM cpu_info_atla WHERE date = '"+prebiweekdate+"') AS tpp ON ytd.role=tpp.role and ytd.platform =tpp.platform )  ON m.role = ytd.role and m.platform=ytd.platform";
        } 


        List<ProcSpreadTrends> list = new ArrayList<ProcSpreadTrends>();
        ProcSpreadTrends metric = null;
        int core_cnt = 0;
        DecimalFormat df = new DecimalFormat("##.##");
        String[] splitted = null;
        try {
            connection = getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                metric = new ProcSpreadTrends();
                splitted = (rs.getString("platform")).split("_");
                core_cnt = PlatformMapping.get_plat_core_ID(splitted[0]);
                metric.setCount(core_cnt*rs.getInt("m.count"));
                metric.setRole(rs.getString("m.role"));
                metric.setPlatform(rs.getString("m.platform"));
                metric.setPerc(df.format(rs.getDouble("p95th1")));
                metric.setU_7d(df.format(rs.getDouble("p95th2")));
                metric.setU_14d(df.format(rs.getDouble("p95th3")));
                metric.setP_7d(df.format(rs.getDouble("trend2")));
                metric.setP_14d(df.format(rs.getDouble("trend3")));
                metric.setSpread(df.format(rs.getDouble("spd1")));
                metric.setSu_7d(df.format(rs.getDouble("spd2")));
                metric.setSu_14d(df.format(rs.getDouble("spd3")));
                metric.setSp_7d(df.format(rs.getDouble("sptrend2")));
                metric.setSp_14d(df.format(rs.getDouble("sptrend3")));
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


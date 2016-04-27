import java.io.*;
import java.util.*;
import java.sql.*;
import java.io.IOException;
public class ConnectionFactory {
    //static reference to itself
    private static ConnectionFactory instance = new ConnectionFactory();
    String driverClass = "com.mysql.jdbc.Driver";
    //private constructor
    private ConnectionFactory() {
        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
 
    public static ConnectionFactory getInstance()   {
        return instance;
    }
 
    public Connection getConnection() throws SQLException,
    ClassNotFoundException {
    String user="",password="",url="";
    Properties prop = new Properties();
    try{
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("dbproperties.txt");
    prop.load(inputStream);
    user = prop.getProperty("userName") ;
    password = prop.getProperty("password") ;
     url = prop.getProperty("url");
       }
       catch(IOException e){e.printStackTrace();}
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }
}



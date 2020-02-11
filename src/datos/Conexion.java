package datos;
import java.sql.*;

public class Conexion
{
    private static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/sga?useSSL=false";
    private static String JDBC_USER = "root";
    private static String JDBC_PASS = "Alex112525";
    private static Driver driver = null;

    public static synchronized Connection getConnection()
        throws SQLException
    {
        if(driver == null)
        {
            try{
                Class jdbcDriverClass = Class.forName(JDBC_DRIVER);
                driver = (Driver) jdbcDriverClass.newInstance();
                DriverManager.registerDriver(driver);
            }catch (Exception e){
                   System.out.println("Fallo al cargar el driver");
                   e.printStackTrace();
                }
        }
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
    }

    public static void close(ResultSet rs)
    {
        try {
            if(rs != null)
            {
                rs.close();
            }
        }catch(SQLException sqle){
                sqle.printStackTrace();
            }
    }

    public static void close(PreparedStatement stmt)
    {
        try {
            if(stmt != null)
            {
                stmt.close();
            }
        }catch (SQLException sqle){
                sqle.printStackTrace();
            }
    }

    public static void close(Connection conn)
    {
        try {
            if(conn != null)
            {
                conn.close();;
            }
        }catch (SQLException sqle){
                sqle.printStackTrace();
            }
    }

}

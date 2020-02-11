package datos;
import domain.Persona;
import java.sql.*;
import java.util.*;

public class PersonasJDBC
{
    private java.sql.Connection userConn;
    private final String SQL_INSERT = "INSERT INTO persona(nombre, apellido) VALUES(?,?)";
    private final String SQL_UPDATE = "UPDATE persona SET nombre=?, apellido=? WHERE idpersona=?";
    private final String SQL_DELETE = "DELETE FROM persona WHERE idpersona=?";
    private final String SQL_SELECT = "SELECT idpersona,nombre,apellido FROM persona ORDER BY idpersona";

    public PersonasJDBC(Connection conn)
    {
        this.userConn = conn;
    }


    public int insert(String nombre, String apellido)
            throws SQLException
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        int rows = 0;

        try{
            conn = (this.userConn != null ? this.userConn : Conexion.getConnection());
            stmt = conn.prepareStatement(SQL_INSERT);
            int index = 1;
            stmt.setString(index++,nombre);
            stmt.setString(index++,apellido);
            System.out.println("Ejecutando Query= " + SQL_INSERT);

            rows = stmt.executeUpdate();
            System.out.println("Registros afectados= " + rows);
        }finally {
                Conexion.close(stmt);
                if(this.userConn == null)
                    Conexion.close(conn);
                }
        return rows;
    }

    public int Update(int id_persona, String nombre, String apellido)
            throws SQLException
    {
        Connection conn = null;
        PreparedStatement stmt = null;

        int rows = 0;

        try{
            conn = (this.userConn != null ? this.userConn : Conexion.getConnection());
            stmt = conn.prepareStatement(SQL_UPDATE);
            int index = 1;
            stmt.setString(index++,nombre);
            stmt.setString(index++,apellido);
            stmt.setInt(index, id_persona);
            System.out.println("Ejecutando Query = " + SQL_UPDATE);

            rows = stmt.executeUpdate();
            System.out.println("Registros actualizados = " + rows);
        }finally {
            Conexion.close(stmt);
            if(this.userConn == null)
                Conexion.close(conn);
            }
        return rows;
    }

    public  int delete(int id_persona)
            throws SQLException
    {
        Connection conn = null;
        PreparedStatement stmt = null;

        int rows = 0;

        try{
            conn = (this.userConn != null ? this.userConn : Conexion.getConnection());
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, id_persona);
            System.out.println("Ejecutando Query = " + SQL_DELETE);

            rows = stmt.executeUpdate();
            System.out.println("Registros eliminados =" + rows);
        }finally {
            Conexion.close(stmt);
            if(this.userConn == null)
                Conexion.close(conn);
        }
        return rows;
    }

    public List<Persona> select()
            throws SQLException
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Persona persona = null;
        List<Persona> personas = new ArrayList();

        try{
            conn = (this.userConn != null ? this.userConn : Conexion.getConnection());
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            while(rs.next())
            {
                int id_persona = rs.getInt(1);
                String nombre = rs.getString(2);
                String apellido = rs.getString(3);

                persona = new Persona(id_persona, nombre, apellido);
                personas.add(persona);
            }

            System.out.println("Ejecutando Query = " + SQL_INSERT);
        }finally {
                Conexion.close(rs);
                Conexion.close(stmt);
                if(this.userConn == null)
                    Conexion.close(conn);
            }
        return personas;
    }
}

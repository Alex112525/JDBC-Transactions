package manejopersonas;

import datos.PersonasJDBC;
import datos.Conexion;
import java.sql.*;

public class ManejoPersonas
{
    public static void main(String[] args)
    {
        Connection conn = null;

        try {
            conn = Conexion.getConnection();
            if(conn.getAutoCommit())
                conn.setAutoCommit(false);

            PersonasJDBC personasJDBC = new PersonasJDBC(conn);

            personasJDBC.Update(1, "Pancho", "Gutierritos");
            personasJDBC.insert("Miguel","Casas");
            conn.commit();
        }catch (SQLException e) {
                try {
                    System.out.println("Entramos al rollback");
                    e.printStackTrace(System.out);
                    conn.rollback();
                }catch (SQLException er){
                        er.printStackTrace(System.out);
                    }
            }

        //personasJDBC.insert("alberto", "Juarez");
        //personasJDBC.Update(2, "Pancho", "Gutierritos");
        //personasJDBC.delete(2);
        //List<Persona> personas = personasJDBC.select();
        //for(Persona persona : personas)
        //{
        //    System.out.println(persona);
        //    System.out.println(" ");
        //}
    }
}

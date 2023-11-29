
package Conector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Conector {
    
    public static Connection getconnection(){
        Connection conexion = null;
        var basedatos = "estudiantes_db";
        var url = "jdbc:mysql://localhost:3306/" + basedatos;
        var usuario = "root";
        var pass = "";
        
        //cargamos la clase del driver de mysql en memoria
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url,usuario,pass);
       
        
         }   catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conexion;
    }
}





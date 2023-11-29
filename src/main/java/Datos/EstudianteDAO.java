
package Datos;
import Dominio.Estudiante;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import static Conector.Conector.getconnection;
import java.sql.Connection;
import java.sql.SQLException;

//DAO - Data Access Object



public class EstudianteDAO {
    
    //Metodo para listar
    public List<Estudiante> listarEstudiantes(){
        //Creo lista de estudiantes
        List<Estudiante> estudiantes = new ArrayList<>();
        //Objeto necesario para preparar la sentencia sql a la base de datos
        PreparedStatement ps;
       //Objeto que permite almacenar el resultado obtenido de la base de datos
        ResultSet rs;
        //Objeto para conectar con la base de datos (importado de manera estatica)
        Connection conector = getconnection();
        //Sentencia sql a ejecutar
        String sql = "SELECT * FROM estudiante ORDER BY id_estudiante";
        
        try{
        //Mando la consulta    
        ps = conector.prepareStatement(sql);
        //Recibo la consulta
        rs = ps.executeQuery();
        
        //Mientras el result set tenga datos los cargamos en una variable de tipo estudiante
        
        while(rs.next()){
            //Se crea el estudiante con constructor vacio 
            var estudiante = new Estudiante();
            
        //Se llena el estudiante con el metodo set mediante los datos que trajo el resultSet
            estudiante.setIdEstudiante(rs.getInt("id_estudiante"));
            estudiante.setNombre(rs.getString("nombre"));
            estudiante.setTelefono(rs.getString("telefono"));
            estudiante.setMail(rs.getString("email"));
            
        //Una vez recuperados los datos los cargamos a la lista
            estudiantes.add(estudiante);
                
        }//Fin while
        } catch(SQLException ex){
            System.out.println("Ocurrio un error al seleccionar datos: " + ex.getMessage());
        }
        finally{ //Cierro la conexion
            try{
                conector.close();
            }catch(SQLException ex){
                System.out.println("Ocurrio un error al cerrar la conexion: " + ex.getMessage());
            }
        }
        return estudiantes;
    }
    
    
    
    //Metodo para buscar estudiantes por su ID
    public boolean buscarPorID(Estudiante estudiante){
    //Objeto necesario para preparar la sentencia sql a la base de datos
        PreparedStatement ps;
    //Objeto que permite almacenar el resultado obtenido de la base de datos
        ResultSet rs;
    //Objeto para conectar con la base de datos (importado de manera estatica)
        Connection conector = getconnection();
    //Sentencia sql a ejecutar
        String sql = "SELECT * FROM estudiante WHERE id_estudiante = ?";
        try{
        //Abre la conexion
            ps = conector.prepareStatement(sql);
        //Se le dice que buscar    
            ps.setInt(1, estudiante.getIdEstudiante());
        //Ejecuta la busqueda    
            rs = ps.executeQuery();
        //Si encuentra la busqueda    
            if(rs.next()){
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setMail(rs.getString("email"));
                return true;
            }
        }catch(SQLException ex){
            System.out.println("Error al buscar id: " + ex.getMessage());   
        }
        finally{ //Cierro la conexion
            try{
                conector.close();
            }catch(SQLException ex){
                System.out.println("Ocurrio un error al cerrar la conexion: " + ex.getMessage());
            }
        }  
        return false;
    }
    
    
    
    
    //Metodo para agregar estudiantes
    public boolean agregarEstudiante(Estudiante estudiante){
          //Objeto necesario para preparar la sentencia sql a la base de datos
        PreparedStatement ps;
    //Objeto para conectar con la base de datos (importado de manera estatica)
        Connection conector = getconnection();
    //Sentencia sql a ejecutar
        String sql = "INSERT INTO estudiante(nombre, telefono, email) VALUES(?, ?, ?)";
       try{
            ps = conector.prepareStatement(sql);
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getTelefono());
            ps.setString(3, estudiante.getMail());
            ps.execute();
            return true;
       }catch(SQLException ex){
           System.out.println("Error al intentar agregar estudiante: " + ex.getMessage());
       }
         finally{ //Cierro la conexion
            try{
                conector.close();
            }catch(SQLException ex){
                System.out.println("Ocurrio un error al cerrar la conexion: " + ex.getMessage());
            }
        } 
       return false;
       
    }
    
    
    
    //Metodo para modificar estudiante
    public boolean modificarEstudiante(Estudiante estudiante){
    //Objeto necesario para preparar la sentencia sql a la base de datos
        PreparedStatement ps;
    //Objeto para conectar con la base de datos (importado de manera estatica)
        Connection conector = getconnection();
    //Sentencia sql a ejecutar
        String sql = "UPDATE estudiante SET nombre=?, telefono=?, email=? WHERE id_estudiante=?";
    try{    
        ps= conector.prepareStatement(sql);
        ps.setString(1, estudiante.getNombre());
        ps.setString(2, estudiante.getTelefono());
        ps.setString(3, estudiante.getMail());
        ps.setInt(4, estudiante.getIdEstudiante());
        ps.execute();
        return true;
    }catch(SQLException ex){
        System.out.println("Ocurrio un error al modificar: " + ex.getMessage());
    }
    finally{
        try{ //Cerramos la conexion
            conector.close();
        }catch(SQLException ex){
            System.out.println("Ocurrio un error al cerrar: " + ex.getMessage());
        }
    }
    return false;
 }
 
    
    
    public boolean eliminarEstudiante(Estudiante estudiante){
         //Objeto necesario para preparar la sentencia sql a la base de datos
        PreparedStatement ps;
    //Objeto para conectar con la base de datos (importado de manera estatica)
        Connection conector = getconnection();
    //Sentencia sql a ejecutar
        String sql = "DELETE FROM estudiante WHERE id_estudiante=?";
        try{
        ps = conector.prepareStatement(sql);
        ps.setInt(1, estudiante.getIdEstudiante());
        ps.execute();
        return true;
        }catch(SQLException ex){
            System.out.println("No se pudo eliminar el estudiante: " + ex.getMessage());
        }
        finally{
            try{
                conector.close();
            }catch(SQLException ex){
                System.out.println("No se pudo cerrar base de datos: " + ex.getMessage());
            }
        }
        return false;
    }
    
           /* 
        //Obtenemos conexion a base de datos
        var conexion = Conector.getconnection();
        //Confirmamos
        if(conexion != null){
            System.out.println("Conexion exitosa: " + conexion);
        } else{
            System.out.println("Error al conectarse");
        }
        
        
        
        //Objeto para probar los metodos SQL
        var estudianteDAO = new EstudianteDAO();
        
        
        
        
        //Agregar estudiante
        var nuevoEstudiante = new Estudiante("Milo bebe", "435345", "milo@remilo.com");
        
        var agregado = estudianteDAO.agregarEstudiante(nuevoEstudiante);
        
        if(agregado){
            System.out.println("Estudiante agregado: " + nuevoEstudiante);
        }else{
            System.out.println("No se pudo agregar estudiante: " + nuevoEstudiante);
        }
        
        
        
        //listar los estudiantes
        System.out.println("Listado estudiantes: ");
        
        List<Estudiante> estudiantes = estudianteDAO.listarEstudiantes();
        
        for (Estudiante e : estudiantes) {
            System.out.println(e.toString());
        }
        
        
        //Buscar estudiante por ID
        var estudiante1 = new Estudiante(4);
        //Estudiante antes de la busqueda
        System.out.println("Estudiante antes de la busqueda: " + estudiante1);
        //Estudiante despues de la busqueada 
        var encontrado = estudianteDAO.buscarPorID(estudiante1); 
        if(encontrado){
            System.out.println("Estudiante encontrado: " + estudiante1);
        }else{
            System.out.println("No se encontro estudiante: " + estudiante1);
        }
        
        
        
        //Modificar estudiante
        var estudiantemodificar = new Estudiante(1,"Martin yo","198909","yo@reyo.com");
        
        var modificado = estudianteDAO.modificarEstudiante(estudiantemodificar);
        
        if(modificado){
            System.out.println("Se modifico estudiante: " + estudiantemodificar);   
        }else{
            System.out.println("No se pudo realizar la modificacion: " + estudiantemodificar);
        }
        

        
        //Eliminar estudiante
        var estudianteeliminar = new Estudiante(4);
        var eliminado = estudianteDAO.eliminarEstudiante(estudianteeliminar);
        if (eliminado){
            System.out.println("Estudiante eliminado: " + estudianteeliminar);
        }else{
            System.out.println("No se pudo eliminar estudiante: " + estudianteeliminar);
        }
         */ 
    
}

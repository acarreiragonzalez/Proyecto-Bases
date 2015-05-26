/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author acarreiragonzalez
 */
public class Conexion {
    private Connection conn = null;
    public Connection CrearBD(){
       try{
         //obtenemos el driver de para mysql
         Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
         //obtenemos la conexión
         conn = DriverManager.getConnection("jdbc:derby:.\\DB\\Derby.DB;create=true");
         if (conn!=null){
            JOptionPane.showMessageDialog(null,"Conectouse á base de datos");
            String creartabla="create table Clientes(nombre varchar(50), ape1 varchar(50), ape2 varchar(50), direccion varchar(40))";
            String desc="disconnect;";
            try {
            PreparedStatement pstm = conn.prepareStatement(creartabla);
            pstm.execute();
            pstm.close();
            
            PreparedStatement pstm2 = conn.prepareStatement(desc);
            pstm2.execute();
            pstm2.close();
            
           JOptionPane.showMessageDialog(null,"BD Creada correctamente");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
         }
      }catch(SQLException e){
       JOptionPane.showMessageDialog(null,e.getMessage(),"Error" ,  JOptionPane.ERROR_MESSAGE);
      }catch(ClassNotFoundException e){
         JOptionPane.showMessageDialog(null,e.getMessage(),"Error" ,  JOptionPane.ERROR_MESSAGE);
      }
       return conn;
  }
    
     public Connection AccederBD(){
       try{
         //obtenemos el driver de para mysql
         Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
         //obtenemos la conexión
         conn = DriverManager.getConnection("jdbc:derby:.\\DB\\Derby.DB");
         if (conn!=null){
           
         }
      }catch(SQLException e){
       JOptionPane.showMessageDialog(null,e.getMessage(),"Error" ,  JOptionPane.ERROR_MESSAGE);
      }catch(ClassNotFoundException e){
         JOptionPane.showMessageDialog(null,e.getMessage(),"Error" ,  JOptionPane.ERROR_MESSAGE);
      }
       return conn;
  }
     
      public void cerracon (){
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
     
}

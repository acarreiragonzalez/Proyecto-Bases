/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author acarreiragonzalez
 */
public class Operaciones {
    
     public Object [][] select(String table, String fields, String where,Connection con){
      int registros = 0;      
      String colname[] = fields.split(",");

      //apartado de sql
      String q ="SELECT " + fields + " FROM " + table;
      String q2 = "SELECT count(*) as total FROM " + table;
      if(where!=null)
      {
          q+= " WHERE " + where;
          q2+= " WHERE " + where;
      }
       try{
         PreparedStatement pstm = con.prepareStatement(q2);
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
      }
    
    //crease a matriz cas filas necesarias
    Object[][] data = new String[registros][fields.split(",").length];
    //realizamos la consulta sql y llenamos los datos en la matriz "Object"
    
    //realizase a consulta sql e henchemos os datos na matriz "Object creada"
      try{
         PreparedStatement pstm = con.prepareStatement(q);
         ResultSet res = pstm.executeQuery();
         int i = 0;
         while(res.next()){
            for(int j=0; j<=fields.split(",").length-1;j++){
                data[i][j] = res.getString( colname[j].trim() );
            }
            i++;         }
         res.close();
          }catch(SQLException e){
         System.out.println(e);
    }
    return data;
 }
      public boolean insert(String table, String fields, String values,Connection con) 
    {
        boolean res=false;
        //Insercción na tabla
        String q=" INSERT INTO " + table + " ( " + fields + " ) VALUES ( " + values + " ) ";
        //Ejecución da consulta
        try {
            PreparedStatement pstm = con.prepareStatement(q);
            pstm.execute();
            pstm.close();
            res=true;
            
           JOptionPane.showMessageDialog(null,"Insertado correctamente");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
        
      return res;
    }
     
     
     
     public Object[][] getClientes(Connection con)
    {
        Object[][] res = this.select("Clientes", "nombre, ape1, ape2,direccion", null,con);
        if( res.length > 0)
            return res;
        else
            return null;
    } 
    
    
    
}

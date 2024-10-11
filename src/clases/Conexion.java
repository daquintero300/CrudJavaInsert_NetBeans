package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import javax.swing.JOptionPane;


public class Conexion {
    
    Connection cn;
    PreparedStatement ps;
    ResultSet rs;
    
    
    public Conexion(){
        
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/servi","root","");
            System.out.println("Conectado BD Servi");
            JOptionPane.showMessageDialog(null,"Conectado BD Servi");
           
        }   catch (Exception e) {
            System.out.println("Error al conectar BD Servi "+e.toString());
            JOptionPane.showMessageDialog(null,"Error al conectar BD Servi"+e.toString());
        }
    }
    
    
    public int RegUsuario(String id_cc,String tipo_documento,String nombre,String apellido,String fecha_nacimiento,String telefono,String email,String contrasena){
        int res=0;
        
        try {
            ps=cn.prepareStatement("insert into usuarios (id_cc,tipo_documento,nombre,apellido,fecha_nacimiento,telefono,email,contrasena) values (?,?,?,?,?,?,?,?);");
            ps.setString (1, id_cc);
            ps.setString (2, tipo_documento);
            ps.setString (3, nombre);
            ps.setString (4, apellido);
            ps.setString (5, fecha_nacimiento);
            ps.setString (6, telefono);
            ps.setString (7, email);
            ps.setString (8, contrasena);
            res=ps.executeUpdate();
            System.out.println("Usuario registrado correctamente");
            JOptionPane.showMessageDialog(null,"Usuario registrado correctamente");
            
        } catch (Exception e) {
            System.out.println("Error al registrarse "+e.toString());
            JOptionPane.showMessageDialog(null,"Error al registrarse");
        }
        return res;
    }  
            
}


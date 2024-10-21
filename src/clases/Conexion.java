package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.util.ArrayList;
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
    
    
  
    
    public int RegUsuario(String id,String tipo_documento,String nombre,String apellido,String fecha_nacimiento,String telefono,String email,String contrasena){
        int res=0;
        
        try {
            ps=cn.prepareStatement("insert into usuarios (id,tipo_documento,nombre,apellido,fecha_nacimiento,telefono,email,contrasena) values (?,?,?,?,?,?,?,?);");
            ps.setString (1, id);
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
    
    
    
    public ArrayList<LoginGetSet> login(String email, String contrasena){
        ArrayList<LoginGetSet> res= new ArrayList<>();
        try {
            ps=cn.prepareStatement("SELECT * FROM usuarios WHERE email=? AND contrasena=?");
            ps.setString(1, email);
            ps.setString(2, contrasena);
            rs=ps.executeQuery();
            while (rs.next()) {
                LoginGetSet log = new LoginGetSet();
                log.setId(rs.getString("id"));
                log.setTipo_documento(rs.getString("tipo_documento"));
                log.setNombre(rs.getString("nombre"));
                log.setApellido(rs.getString("apellido"));
                log.setFecha_nacimiento(rs.getString("fecha_nacimiento"));
                log.setTelefono(rs.getString("telefono"));
                log.setEmail(rs.getString("email"));
                log.setContrasena(rs.getString("contrasena"));
                res.add(log);   
            }
            if (res.isEmpty()) {
                System.out.println("Acceso denegado");
            } else {
                System.out.println("Login exitoso");
            }
        } catch (Exception e) {
        }
        return res;
    }
            
}


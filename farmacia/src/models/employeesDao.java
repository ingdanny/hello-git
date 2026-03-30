/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author PtIngHome
 */
public class employeesDao {
    //instanciar la coneccion
    conectionMySQL cn = new conectionMySQL();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    
    //varaiables para enviar datos entre interfaces 
    public static int idUser=0;
    public static String fullNameUser="";
    public static String userNameUser="";
    public static String addressUser="";
    public static String telephoneUser="";
    public static String emailUser="";
    public static String rolUser="";
    
      
    
    //Metodo de login
    public employees loginQuery(String user, String password){
        String query = "SELECT * FROM employees WHERE userName=? AND password=?";
        employees employee= new employees();
        try {
            conn = cn.getConnection();
            pst =conn.prepareStatement(query);
            //enviar parametros 
            pst.setString(1, user);
            pst.setString(2, password);
            rs=pst.executeQuery();
            
            if (rs.next()){
                employee.setId(rs.getInt("id"));
                idUser=employee.getId();
                employee.setFullName("fullName");
                fullNameUser=employee.getFullName();
                employee.setAddress("address");
                addressUser=employee.getAddress();
                employee.setTelephone("telephone");
                telephoneUser=employee.getTelephone();
                employee.setEmail("email");
                emailUser=employee.getEmail();
                employee.setRol("rol");
                rolUser=employee.getRol();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al Obtener al Empleado"+ e);
        }
        return employee;
    }
    
    
    //Resgistrar empleados 
    public boolean registerEmployeeQuery(employees employe){
        String query = "INSERT INTO employees (id, fullName, userName, address, telephone, email, password, rol, created, update) VALUES(?,?,?,?,?,?,?,?,?,?) )";
        Timestamp dateTime = new Timestamp(new Date().getTime());
        
        try {
            conn=cn.getConnection();
            pst =conn.prepareStatement(query);
            
            pst.setInt(1, employe.getId());
            pst.setString(2, employe.getFullName());
            pst.setString(3, employe.getUserName());
            pst.setString(4, employe.getAddress());
            pst.setString(5, employe.getTelephone());
            pst.setString(6, employe.getEmail());
            pst.setString(7, employe.getPassword());
            pst.setString(8, employe.getRol());
            pst.setTimestamp(9, dateTime );
            pst.setTimestamp(10, dateTime);
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar al empleado"+ e);
            return false;
        }
    }


    //Listar empleados
    public List listEmployeeQuery (String value){
        List<employees> listEmployees= new ArrayList();
        
        String query = "SELECT * FROM employees ORDER BY rol ASC";
        String querySearchEmployee = "SELECT * FROM employees WHERE id LIKE '%"+ value +"%'";
        
        try {
            conn=cn.getConnection();
            if(value.equalsIgnoreCase("")){
                pst=conn.prepareStatement(query);
                rs=pst.executeQuery();
            }else{
                pst=conn.prepareStatement(querySearchEmployee);
                rs=pst.executeQuery();
            }
        while(rs.next()){
            employees employee= new employees();
            employee.setId(rs.getInt("id"));
            employee.setFullName(rs.getString("fullName"));
            employee.setUserName(rs.getString("userName"));
            employee.setAddress(rs.getString("address"));
            employee.setTelephone(rs.getString("telephone"));
            employee.setEmail(rs.getString("email"));
            employee.setPassword(rs.getString("password"));
            employee.setRol(rs.getString("rol"));
            listEmployees.add(employee);
        }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return listEmployees;
    }
    
    
    //Modificar Empleado
    public boolean updateEmployeeQuery(employees employe){
        String query = "UPDATE employees SET fullName=?, userName=?, address=?, telephone=?, email=?, rol=?, update=? WHERE id=?";
        
        Timestamp dateTime = new Timestamp(new Date().getTime());
        
        try {
            conn=cn.getConnection();
            pst =conn.prepareStatement(query);
            
            pst.setString(1, employe.getFullName());
            pst.setString(2, employe.getUserName());
            pst.setString(3, employe.getAddress());
            pst.setString(4, employe.getTelephone());
            pst.setString(5, employe.getEmail());
            pst.setString(6, employe.getRol());
            pst.setTimestamp(7, dateTime );
            pst.setInt(8, employe.getId());
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar al empleado"+ e);
            return false;
        }
    }
    
    
    //Eliminar Empleado
    public boolean deleteEmployeeQuery(int id){
        String query = "DELELTE FORM employees WHERE id= " + id;
        
        try {
            conn=cn.getConnection();
            pst =conn.prepareStatement(query);
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " No puede eliminar un epmleado que tenga realacion con otra tabla "+ e);
            return false;
        }
    }
    
    
    //Cambiar contraseña 
    public boolean updateEmployeePassword(employees employe){
        String query= "UPDATE employees SET password WHERE userName = '"+userNameUser+"'";
        
        try {
            conn=cn.getConnection();
            pst =conn.prepareStatement(query);
            pst.setString(1, employe.getPassword());
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar la contraseña"+ e);
            return false;
        }
    
    }

}

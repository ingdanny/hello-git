/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author PtIngHome
 */
public class customersDAO {
     //instanciar la coneccion
    conectionMySQL cn = new conectionMySQL();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    
    //resgistrar clientes
     public boolean registerCustomersQuery(customers customer){
        String query = "INSERT INTO customers (id, fullName, address, telephone, email, created, update) VALUES(?,?,?,?,?,?,?) )";
        Timestamp dateTime = new Timestamp(new Date().getTime());
        
        try {
            conn=cn.getConnection();
            pst =conn.prepareStatement(query);
            
            pst.setInt(1, customer.getId());
            pst.setString(2, customer.getFullName());
            pst.setString(3, customer.getAddress());
            pst.setString(4, customer.getTelephone());
            pst.setString(5, customer.getEmail());
            pst.setTimestamp(6, dateTime );
            pst.setTimestamp(7, dateTime);
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar al cliente"+ e);
            return false;
        } 
    }
    
    
    //listar clinetes
     public List listCustomerQuery (String value){
        List<customers> listCustomer= new ArrayList();
        
        String query = "SELECT * FROM customers";
        String querySearchCustomer = "SELECT * FROM customers WHERE id LIKE '%"+ value +"%'";
        
        try {
            conn=cn.getConnection();
            if(value.equalsIgnoreCase("")){
                pst=conn.prepareStatement(query);
                rs=pst.executeQuery();
            }else{
                pst=conn.prepareStatement(querySearchCustomer);
                rs=pst.executeQuery();
            }
        while(rs.next()){
            customers customer= new customers();
            customer.setId(rs.getInt("id"));
            customer.setFullName(rs.getString("fullName"));
            customer.setAddress(rs.getString("address"));
            customer.setTelephone(rs.getString("telephone"));
            customer.setEmail(rs.getString("email"));
            listCustomer.add(customer);
        }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return listCustomer;
    }
 
     
     //modificar Clinete
     public boolean updateCustomersQuery(customers customer){
        String query = "UPDATE customers SET fullName=?, address=?, telephone=?, email=?, update=? WHERE id=?";
        Timestamp dateTime = new Timestamp(new Date().getTime());
        
        try {
            conn=cn.getConnection();
            pst =conn.prepareStatement(query);
            
            
            pst.setString(1, customer.getFullName());
            pst.setString(2, customer.getAddress());
            pst.setString(3, customer.getTelephone());
            pst.setString(4, customer.getEmail());
            pst.setTimestamp(5, dateTime );
            pst.setInt(6, customer.getId());
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar los datos del cliente"+ e);
            return false;
        } 
    }
     
     
     //eliminar clinetes
     public boolean deleteCustomerQuery(int id){
        String query = "DELELTE FORM customers WHERE id= " + id;
        
        try {
            conn=cn.getConnection();
            pst =conn.prepareStatement(query);
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " No puede eliminar un cliente que tenga realacion con otra tabla "+ e);
            return false;
        }
    }
     
     
}

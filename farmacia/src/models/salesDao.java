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
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author PtIngHome
 */
public class salesDao {
    
    //instanciar la coneccion
    conectionMySQL cn = new conectionMySQL();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    
    //registrar una venta 
    public boolean registerSaleQuery (int customerId, int employeeId, double total){
        String query="INSERT INTO sales (customerId, employeeId, total, saleDate) VALUES (?,?,?,?)";
        Timestamp dateTime = new Timestamp(new Date().getTime());
        
        try {
            conn=cn.getConnection();
            pst =conn.prepareStatement(query);
            
            pst.setInt(1, customerId);
            pst.setInt(2, employeeId);
            pst.setDouble(3, total);
            pst.setTimestamp(4, dateTime );
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }      
    }
    
    
    //Resgistrar detalles de la venta
    public boolean registerSaleDetailQuery(int productId, int saleId, int saleQuantity, double salePrice, double saleSubtotal){
        String query="INSERT INTO sale_details (productId, saleId, saleQuantity, salePrice, saleSubtotal) VALUES (?,?,?,?,?)";
        Timestamp dateTime = new Timestamp(new Date().getTime());
        
        try {
            conn=cn.getConnection();
            pst =conn.prepareStatement(query);
            
            pst.setInt(1, productId);
            pst.setInt(2, saleId);
            pst.setInt(3, saleQuantity);
            pst.setDouble(4, salePrice);
            pst.setDouble(5, saleSubtotal);
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }         
    }
    
    
    
    
    
    
}

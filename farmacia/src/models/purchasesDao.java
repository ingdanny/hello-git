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
public class purchasesDao {
    //instanciar la coneccion
    conectionMySQL cn = new conectionMySQL();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    
    //registrar compra
    public boolean registerPurchaseQuery(int supplierId, int employeeId, double total){
        String query="INSERT INTO purchases (supplierId, employeeId, total, created) VALUES (?,?,?,?)";
        Timestamp dateTime = new Timestamp(new Date().getTime());
        
        try {
            conn=cn.getConnection();
            pst =conn.prepareStatement(query);
            
            pst.setInt(1, supplierId);
            pst.setInt(2, employeeId);
            pst.setDouble(3, total);
            pst.setTimestamp(4, dateTime );
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar la compra"+ e);
            return false;
        }      
    }
    
    
    //Regitrar detalles de la compra
    public boolean registerPurchaseDetailQuery(int purchaseId, double purchasePrice, int purchaseAmount, double purchaseSubtotal, int productId){
        String query="INSERT INTO purchase_details (purchaseId, puchasePrice, purchaseAmout, pucrhaseSubtotal, purchaseDate, productId) VALUES (?,?,?,?,?,?)";
        Timestamp dateTime = new Timestamp(new Date().getTime());
        
        try {
            conn=cn.getConnection();
            pst =conn.prepareStatement(query);
            
            pst.setInt(1, purchaseId);
            pst.setDouble(2, purchasePrice);
            pst.setInt(3, purchaseAmount);
            pst.setDouble(4, purchaseSubtotal);
            pst.setTimestamp(5, dateTime );
            pst.setInt(6, productId);
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar los detalles de la compra"+ e);
            return false;
        }      
        
    }
    
    
    //obtener id de la compra
    public int purchaseId (){
        int id=0;
        String query="SELEC MAX(id) AS id FROM purchases";
        
        try {
            conn=cn.getConnection();
            pst =conn.prepareStatement(query);
            rs=pst.executeQuery();
            
            if(rs.next()){
                id=rs.getInt("id");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }      
        return id;
    }
 
    
    //Listar todas las comrpas realizadas 
    public List listAllPurchasesquery(){
        List<purchases> listPurchase= new ArrayList();
        String query = "SELECT pu.*, su.name AS supplierName FROM purchases pu, suppliers su where pu.supplierId=su.id ORDER BY pu.id ASC";
        
        try {
            conn=cn.getConnection();
            pst =conn.prepareStatement(query);
            rs=pst.executeQuery();
            
            while(rs.next()){
                purchases purchase=new purchases();
                purchase.setId(rs.getInt("id"));
                purchase.setSupplierNameProduct(rs.getString("supplierName"));
                purchase.setTotal(rs.getDouble("total"));
                purchase.setCreated(rs.getString("created"));
                listPurchase.add(purchase);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }      
        return listPurchase;
    }
    
    
    //Listar compras para imprimir factura 
    public List listPurchasesDetailquery(int id){
        List<purchases> listPurchases= new ArrayList();
        String query = "SELECT pu.created, pude.purchasePrice, pude.purchaseAmount, pude.purchaseSubtotal, su.name AS supplierName, pro.name AS productName, em.fullName FROM purchases pu INNER JOIN purchase_details pude ON pu.id = pude.purchaseId INNER JOIN products pro ON pude.productID = pro.id INNER JOIN suppliers su ON pu.supplierId= su.id INNER JOIN employees em ON pu.id=em.id WHERE pu.id=?";
        
        try {
            conn=cn.getConnection();
            pst =conn.prepareStatement(query);
            pst.setInt(1, id);
            rs=pst.executeQuery();
            
            while(rs.next()){
                purchases purchase=new purchases();
                purchase.setProductName(rs.getString("productName"));
                purchase.setPurchaseAmount(rs.getInt("purchaseAmount"));
                purchase.setPurchasePrice(rs.getDouble("purchasePrice"));
                purchase.setPurchaseSubtotal(rs.getDouble("purchaseSubtotal"));
                purchase.setSupplierNameProduct(rs.getString("supplierName"));
                purchase.setCreated(rs.getString("created"));
                purchase.setPurcharser(rs.getString("fullName"));
                listPurchases.add(purchase);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }      
        return listPurchases;
    }
    
    
}

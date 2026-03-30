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
public class suppliersDao {
    //instanciar la coneccion
    conectionMySQL cn = new conectionMySQL();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    
    
    //Resgistrar proveedor
    public boolean registerSuppliersQuery(suppliers supplier){
        String query = "INSERT INTO suppliers (name, description, address, telephone, email, city, created, update) VALUES(?,?,?,?,?,?,?,?) )";
        Timestamp dateTime = new Timestamp(new Date().getTime());
        
        try {
            conn=cn.getConnection();
            pst =conn.prepareStatement(query);
            
            pst.setString(1, supplier.getName());
            pst.setString(2, supplier.getDescription());
            pst.setString(3, supplier.getAddress());
            pst.setString(4, supplier.getTelephone());
            pst.setString(5, supplier.getEmail());
            pst.setString(6, supplier.getCity());
            pst.setTimestamp(7, dateTime );
            pst.setTimestamp(8, dateTime);
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar al proveedor"+ e);
            return false;
        } 
    }
    
    
    //Listar Proveedores
    public List listSuppliersQuery (String value){
        List<suppliers> listSupplier= new ArrayList();
        
        String query = "SELECT * FROM supliers";
        String querySearchSuplier = "SELECT * FROM suppliers WHERE name LIKE '%"+ value +"%'";
        
        try {
            conn=cn.getConnection();
            if(value.equalsIgnoreCase("")){
                pst=conn.prepareStatement(query);
                rs=pst.executeQuery();
            }else{
                pst=conn.prepareStatement(querySearchSuplier);
                rs=pst.executeQuery();
            }
        while(rs.next()){
            suppliers supplier= new suppliers();
            supplier.setId(rs.getInt("id"));
            supplier.setName(rs.getString("name"));
            supplier.setDescription(rs.getString("description"));
            supplier.setAddress(rs.getString("address"));
            supplier.setTelephone(rs.getString("telephone"));
            supplier.setEmail(rs.getString("email"));
            supplier.setCity(rs.getString("city"));
            listSupplier.add(supplier);
        }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return listSupplier;
    }
    
    
    //Modificar Proveedor
    public boolean updateSuppliersQuery(suppliers supplier){
        String query = "UPDATE suppliers SET name=?, description=?, address=?, telephone=?, email=?, city=?, update=? WHERE id=?";
        Timestamp dateTime = new Timestamp(new Date().getTime());
        
        try {
            conn=cn.getConnection();
            pst =conn.prepareStatement(query);
            
            
            pst.setString(1, supplier.getName());
            pst.setString(2, supplier.getDescription());
            pst.setString(3, supplier.getAddress());
            pst.setString(4, supplier.getTelephone());
            pst.setString(5, supplier.getEmail());
            pst.setString(6, supplier.getCity());
            pst.setTimestamp(7, dateTime );
            pst.setInt(8, supplier.getId());
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar los datos del proveedor"+ e);
            return false;
        } 
    }
    
    
    //Eliminar proveedores
    public boolean deleteSuppliersQuery(int id){
        String query = "DELELTE FORM suppliers WHERE id= " + id;
        
        try {
            conn=cn.getConnection();
            pst =conn.prepareStatement(query);
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " No puede eliminar un proveedor que tenga realacion con otra tabla "+ e);
            return false;
        }
    }
    
}

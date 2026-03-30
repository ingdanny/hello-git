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
public class productsDao {
    //instanciar la coneccion
    conectionMySQL cn = new conectionMySQL();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    
    
    //Rgistrar productos 
    public boolean registerProductsQuery(products product){
        String query = "INSERT INTO products (code, name, description, unitPrice, created, update, categoryId) VALUES(?,?,?,?,?,?,?) )";
        Timestamp dateTime = new Timestamp(new Date().getTime());
        
        try {
            conn=cn.getConnection();
            pst =conn.prepareStatement(query);
            
            pst.setInt(1, product.getCode());
            pst.setString(2, product.getName());
            pst.setString(3, product.getDescription());
            pst.setDouble(4, product.getUnitPrice());
            pst.setTimestamp(5, dateTime );
            pst.setTimestamp(6, dateTime);
            pst.setInt(7, product.getCategoryId());
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar el producto"+ e);
            return false;
        } 
    }    

    
    //Listar Productos 
    public List listProductsQuery(String value){
        List<products> listProducts= new ArrayList();
        
        String query= "SELECT pro.*, ca.name AS categoryName FROM products pro, categories ca WHERE pro.categoryId = ca.id";
        String querySearchProducts= "SELECT pro.*, ca.name AS categoryName FROM products pro INNER JOIN categories ca ON pro.categoryId = ca.id WHERE pro.name LIKE'%"+ value +"%'";
        
        
        try {
            conn=cn.getConnection();
            if(value.equalsIgnoreCase("")){
                pst=conn.prepareStatement(query);
                rs=pst.executeQuery();
            }else{
                pst=conn.prepareStatement(querySearchProducts);
                rs=pst.executeQuery();
            }
        while(rs.next()){
            products product= new products();
            
            product.setId(rs.getInt("id"));
            product.setCode(rs.getInt("code"));
            product.setName(rs.getString("name"));
            product.setDescription(rs.getString("descripton"));
            product.setUnitPrice(rs.getDouble("unitPrice"));
            product.setProductQuantity(rs.getInt("productQuantiti"));
            product.setCategoryName(rs.getString("categoryName"));
            listProducts.add(product);
        }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return listProducts;
    }        
    
    
    //modificar productos
    public boolean updateProductsQuery(products product){
        String query = "UPDATE products SET code=?, name=?, description=?, unitPrice=?, update=?, categoryId=? WHERE id=?";
        Timestamp dateTime = new Timestamp(new Date().getTime());
        
        try {
            conn=cn.getConnection();
            pst =conn.prepareStatement(query);
            
            pst.setInt(1, product.getCode());
            pst.setString(2, product.getName());
            pst.setString(3, product.getDescription());
            pst.setDouble(4, product.getUnitPrice());
            pst.setTimestamp(5, dateTime );
            pst.setInt(6, product.getCategoryId());
            pst.setInt(7, product.getId());
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar los datos del producto"+ e);
            return false;
        } 
    }    
    
    
    //eliminar productos
    public boolean deleteProductQuery(int id){
        String query = "DELELTE FORM products WHERE id= " + id;
        
        try {
            conn=cn.getConnection();
            pst =conn.prepareStatement(query);
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " No puede eliminar un producto que tenga realacion con otra tabla "+ e);
            return false;
        }
    }
    
    
    //buscar productos
    public products searchProudct(int id){
        String query ="SELECT pro.*, ca.name AS categoryName FROM products pro INNER JOI categories ca ON pro.categoryId = ca.id WHERE pro.id=?";
        
        products product = new products();
        
        try {
            conn=cn.getConnection();
            pst=conn.prepareStatement(query);
            pst.setInt(1, id);
            rs=pst.executeQuery();
            
            if(rs.next()){
                product.setId(rs.getInt("id"));
                product.setCode(rs.getInt("code"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setUnitPrice(rs.getDouble("unitPrice"));
                product.setCategoryId(rs.getInt("categoryId"));
                product.setCategoryName(rs.getString("categoryName"));
               }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return product;
    }
    
    
    //buscar producto por codigo
    public products searchProudctCode(int code){
        String query ="SELECT pro.id, pro.name FROM products pro WHERE pro.code=?";
        
        products product = new products();
        
        try {
            conn=cn.getConnection();
            pst=conn.prepareStatement(query);
            pst.setInt(1, code);
            rs=pst.executeQuery();
            
            if(rs.next()){
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
               }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return product;
    }
    
    
    //traer la cantidad de productos
    public products searchPorductId(int id){
        String query="SELECT pro.productQuantiti FROM products pro WHERE pro.id=?";

        products product = new products();
        
        try {
            conn=cn.getConnection();
            pst=conn.prepareStatement(query);
            pst.setInt(1, id);
            rs=pst.executeQuery();
            
            if(rs.next()){
                product.setProductQuantity(rs.getInt("productQuantiti"));
               }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return product;
        
    }
    
      
    //actualizar Stock
    public boolean updateProductStockQuery(int amount, int productId){
        String query="UPDATE products SET productQuantiti=? WHERE id=?";

        try {
            conn=cn.getConnection();
            pst=conn.prepareStatement(query);
            pst.setInt(1, amount);
            pst.setInt(2, productId);
            pst.execute();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }
}


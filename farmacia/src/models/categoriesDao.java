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
public class categoriesDao {
    //instanciar la coneccion
    conectionMySQL cn = new conectionMySQL();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    
    
    //registrar categorias
    public boolean resgisterCategoriesQuery(categories category){
        String query = "INSERT INTO categories (name, created, update) VALUES(?,?,?) )";
        Timestamp dateTime = new Timestamp(new Date().getTime());
        
        try {
            conn=cn.getConnection();
            pst =conn.prepareStatement(query);
            
            pst.setString(1, category.getName());
            pst.setTimestamp(2, dateTime );
            pst.setTimestamp(3, dateTime);
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar la categoria"+ e);
            return false;
        } 
        
        
    }
    
    
    //Listar Categorias
    public List listCategoriesQuery (String value){
        List<categories> listCategories= new ArrayList();
        
        String query = "SELECT * FROM categories";
        String querySearchCategories = "SELECT * FROM categories WHERE id LIKE '%"+ value +"%'";
        
        try {
            conn=cn.getConnection();
            if(value.equalsIgnoreCase("")){
                pst=conn.prepareStatement(query);
                rs=pst.executeQuery();
            }else{
                pst=conn.prepareStatement(querySearchCategories);
                rs=pst.executeQuery();
            }
        while(rs.next()){
            categories category= new categories();
            category.setId(rs.getInt("id"));
            category.setName(rs.getString("name"));
            listCategories.add(category);
        }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return listCategories;
    }
    
    
    //modificar categorias
    public boolean updateCategoriesQuery(categories category){
        String query = "UPDATE categories SET name=?, update=? WHERE id=?";
        Timestamp dateTime = new Timestamp(new Date().getTime());
        
        try {
            conn=cn.getConnection();
            pst =conn.prepareStatement(query);
            
            pst.setString(1, category.getName());
            pst.setTimestamp(2, dateTime );
            pst.setInt(3, category.getId());
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar los datos de la categoria"+ e);
            return false;
        } 
        
        
    }
    
    
    //eliminar Categorias
    public boolean deleteCategoriesQuery(int id){
        String query = "DELELTE FORM categories WHERE id= " + id;
        
        try {
            conn=cn.getConnection();
            pst =conn.prepareStatement(query);
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " No puede eliminar una categoria que tenga realacion con otra tabla "+ e);
            return false;
        }
    }
    
}

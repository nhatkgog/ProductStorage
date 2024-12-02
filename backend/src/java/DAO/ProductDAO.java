/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import ConnectDB.DBContext;
import Model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class ProductDAO {
    public void createProduct(Product p) {
        String query = "insert into Product(ProductName, Quantity)\n"
                + "values(?, ?)";
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, p.getName());
            ps.setInt(2, p.getQuantity());
            ps.execute();
            DBContext.GetInstance().close(conn, ps, null);
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Product> getAllProduct() {
        String query = "select * from Product\n";
        ArrayList<Product> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getInt(1), rs.getString(2), rs.getInt(3));
                list.add(p);
            }
            DBContext.GetInstance().close(conn, ps, rs);
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public void deleteProduct(int id) {
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            PreparedStatement statement;
            String sql = "DELETE FROM Product WHERE ProductID=?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();
            DBContext.GetInstance().close(conn, statement, null);
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }

    public void updateProduct(Product p) {
        String sql = " UPDATE Product\n" + "SET Quantity = ?\n" + "WHERE ProductID = ?";
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, p.getQuantity());
            statement.setInt(2, p.getId());
            statement.execute();
            DBContext.GetInstance().close(conn, statement, null);
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectDB;

import DAO.ProductDAO;
import Model.Product;


/**
 *
 * @author Administrator
 */
public class TestDB {
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();
        productDAO.updateProduct(new Product(1, null, 10));
        productDAO.getAllProduct().forEach(System.out::println);
    }
}

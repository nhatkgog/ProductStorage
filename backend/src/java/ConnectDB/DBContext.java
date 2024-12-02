/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class DBContext implements DatabaseInfor{
    private static Connection conn = null;
    private static DBContext dbcontext = null;

    public static Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()){
            try {
                Class.forName(driverName);
                conn = DriverManager.getConnection(url, user, pass);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return conn;
    }
    
    public static DBContext GetInstance(){
        if (dbcontext == null){
            dbcontext = new DBContext();
        }
        return dbcontext;
    }

    public void close(Connection conn, PreparedStatement ps, ResultSet rs)
            throws SQLException {
        if (rs != null && !rs.isClosed()) {
            rs.close();
        }

        if (ps != null && !ps.isClosed()) {
            ps.close();
        }
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
}

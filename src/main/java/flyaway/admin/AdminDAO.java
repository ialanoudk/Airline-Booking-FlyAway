    package flyaway.admin;


import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import flyaway.MySQLDB; 

public class AdminDAO { 
    private Connection conn;
     
    public AdminDAO() {
        try {
			this.conn  = MySQLDB.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
      


    protected void connect_db() throws SQLException {
    	try {
			conn  = MySQLDB.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    } 
    protected void disconnect_db() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
    

          
    public boolean adminLogin(String email, String pass) throws SQLException { 
    	connect_db(); 
        String sql = "SELECT * FROM admin where email = '"+email+"' and `pass` = '"+pass+"'";  
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        boolean exist = false; 
        while (resultSet.next()) {
        	exist = true;
        }
         
        resultSet.close();
        statement.close();
         
        disconnect_db();
         
        return exist;
    } 
}

    

    package flyaway.airlines;


import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import flyaway.MySQLDB;


public class AireLineDAO {

    private Connection conn;
     
    public AireLineDAO() {    }
      

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
     
    public boolean add(String name) throws SQLException {
    	connect_db();
        String sql = "INSERT INTO airline (name) VALUES (?)";          
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, name);
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect_db();
        return rowInserted;
    }
     
    public List<AireLine> getAirlines() throws SQLException {
    	connect_db() ;
        List<AireLine> listairline = new ArrayList<AireLine>(); 
        String sql = "SELECT * FROM airline"; 
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");  
            AireLine airline = new AireLine(id, name);
            listairline.add(airline);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect_db();
         
        return listairline;
    }
     
    public boolean delete(String id) throws SQLException {
    	connect_db() ;
        String sql = "DELETE FROM airline where id = ?";
         
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1,id);
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect_db();
        return rowDeleted;     
    }
     
    public boolean update(String id , String name) throws SQLException {
    	connect_db() ;
        String sql = "UPDATE airline SET name = ?";
        sql += " WHERE id = ?"; 
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, name);
        statement.setString(2, id);
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect_db();
        return rowUpdated;     
    }
     
    public AireLine getAirline(int id) throws SQLException {
    	connect_db() ;
        AireLine airline = null;
        String sql = "SELECT * FROM airline WHERE id = ?"; 
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id); 
        ResultSet resultSet = statement.executeQuery(); 
        if (resultSet.next()) {
            String name = resultSet.getString("name"); 
            airline = new AireLine(id, name);
        } 
        resultSet.close();
        statement.close(); 
        return airline;
    }
}

    

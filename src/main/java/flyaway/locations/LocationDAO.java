package flyaway.locations;


import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import flyaway.MySQLDB;


public class LocationDAO {

    private Connection conn;
     
    public LocationDAO() {
    	
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
     
    public boolean add(String name) throws SQLException {
    	connect_db();
        String sql = "INSERT INTO locations (name) VALUES (?)";          
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, name);
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect_db();
        return rowInserted;
    }
     
    public List<Locations> getLocations() throws SQLException {
    	connect_db() ;
        List<Locations> listlocations = new ArrayList<Locations>(); 
        String sql = "SELECT * FROM locations"; 
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");  
            Locations locations = new Locations(id, name);
            listlocations.add(locations);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect_db();
         
        return listlocations;
    }
     
    public boolean delete(String id) throws SQLException {
    	connect_db() ;
        String sql = "DELETE FROM locations where id = ?";
         
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, id);
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect_db();
        return rowDeleted;     
    }
     
    public boolean update(String id, String name) throws SQLException {
    	connect_db() ;
        String sql = "UPDATE locations SET name = ?";
        sql += " WHERE id = ?"; 
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, name);
        statement.setString(2, id);
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect_db();
        return rowUpdated;     
    }
     
    public Locations getlocations(int id) throws SQLException {
    	connect_db() ;
        Locations locations = null;
        String sql = "SELECT * FROM locations WHERE id = ?"; 
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id); 
        ResultSet resultSet = statement.executeQuery(); 
        if (resultSet.next()) {
            String name = resultSet.getString("name"); 
            locations = new Locations(id, name);
        } 
        resultSet.close();
        statement.close(); 
        return locations;
    }
}

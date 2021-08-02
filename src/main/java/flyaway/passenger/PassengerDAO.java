package flyaway.passenger;


import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import flyaway.MySQLDB;


public class PassengerDAO {

    private Connection conn;
     
    public PassengerDAO() {
    	
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
    

    
    public boolean login(String email, String pass) throws SQLException { 
    	connect_db(); 
        String sql = "SELECT * FROM passenger where email = '"+email+"' and `pass` = '"+pass+"'";  
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
    
    public boolean register(String name, String phone, String email,String pass) throws SQLException {
    	connect_db();
        String sql = "INSERT INTO passenger (name, phone, email, pass) VALUES (? , ? , ? ,?)";          
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, name);
        statement.setString(2, phone);
        statement.setString(3, email);
        statement.setString(4, pass);
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect_db();
        return rowInserted;
    }


	public Passenger getPassenger(String email) throws SQLException {
		connect_db() ; 
		Passenger p = null;
        String sql = "SELECT * FROM passenger where email = '"+email+"'"; 
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
        	int id = resultSet.getInt("id");  
        	String phone = resultSet.getString("phone"); 
        	String name = resultSet.getString("name"); 
        	p = new Passenger(id, name , email, phone );
        }
         
        resultSet.close();
        statement.close();
         
        disconnect_db();
         
        return p;
	} 


	public Passenger getPassenger(int id) throws SQLException {
		connect_db() ; 
		Passenger p = null;
        String sql = "SELECT * FROM passenger where id = '"+id+"'"; 
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) { 
        	String phone = resultSet.getString("phone"); 
        	String name = resultSet.getString("name"); 
        	String email = resultSet.getString("email"); 
        	p = new Passenger(id, name , email, phone );
        }
         
        resultSet.close();
        statement.close();
         
        disconnect_db();
         
        return p;
	} 
     
    /*
    public List<Passenger> getLocations() throws SQLException {
    	connect_db() ;
        List<Passenger> listlocations = new ArrayList<Passenger>(); 
        String sql = "SELECT * FROM locations"; 
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");  
            Passenger locations = new Passenger(id, name);
            listlocations.add(locations);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect_db();
         
        return listlocations;
    }
     
    public boolean delete(Passenger locations) throws SQLException {
    	connect_db() ;
        String sql = "DELETE FROM locations where id = ?";
         
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, locations.getId());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect_db();
        return rowDeleted;     
    }
     
    public boolean update(Passenger locations) throws SQLException {
    	connect_db() ;
        String sql = "UPDATE locations SET name = ?";
        sql += " WHERE id = ?"; 
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, locations.getname());
        statement.setInt(2, locations.getId());
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect_db();
        return rowUpdated;     
    }
     
    public Passenger getlocations(int id) throws SQLException {
    	connect_db() ;
        Passenger locations = null;
        String sql = "SELECT * FROM locations WHERE id = ?"; 
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id); 
        ResultSet resultSet = statement.executeQuery(); 
        if (resultSet.next()) {
            String name = resultSet.getString("name"); 
            locations = new Passenger(id, name);
        } 
        resultSet.close();
        statement.close(); 
        return locations;
    }
    */
}

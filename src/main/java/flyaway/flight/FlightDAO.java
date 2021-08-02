    package flyaway.flight;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import flyaway.MySQLDB;
import flyaway.passenger.Passenger;


public class FlightDAO {

    private Connection conn;
     
    public FlightDAO() {
    	
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
     
    public boolean add(Flight flight) throws SQLException {
    	connect_db();
        String sql = "INSERT INTO flight (from_location, to_location, total_seats, flight_date, price , airline_id) VALUES (?, ?, ?, ?, ?, ?)";          
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, Integer.parseInt(flight.getfromlocation()));
        statement.setInt(2, Integer.parseInt(flight.gettolocation()));
        statement.setInt(3, flight.gettotalseats());
        statement.setString(4, flight.getdate());
        statement.setString(5, flight.getprice()); 
        statement.setInt(6, flight.getairlineid()); 
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect_db();
        return rowInserted;
    }
     
    public List<Flight> getflight() throws SQLException {
    	connect_db() ;
        List<Flight> listF= new ArrayList<Flight>(); 
        String sql = "SELECT * FROM flight"; 
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String from_location = resultSet.getString("from_location"); 
            String to_location = resultSet.getString("to_location");  
            int total_seats = resultSet.getInt("total_seats");  
            int airline_id = resultSet.getInt("airline_id");  
            String flight_date = resultSet.getString("flight_date");  
            String price = resultSet.getString("price");  
            Flight f = new Flight(id, from_location, to_location,airline_id , total_seats, flight_date, price);
            listF.add(f);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect_db();
         
        return listF;
    }
    
    
    public Flight findflight(int fromLoc, int toLoc, int airline, String flight_date) throws SQLException {
    	connect_db() ;
        Flight f = null; 
        String sql = "SELECT * FROM `flight` WHERE from_location = "+fromLoc+" and to_location = "+toLoc+" and airline_id = "+airline+" and flight_date = '"+flight_date+"' ";  
        System.out.println();
        System.out.println(sql+" "+fromLoc+" "+toLoc+" "+airline);
        PreparedStatement statement = conn.prepareStatement(sql);
        //statement.setInt(1, fromLoc);
        //statement.setInt(2, toLoc);
        //statement.setInt(3, airline); 
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int id = resultSet.getInt("id"); 
            int total_seats = resultSet.getInt("total_seats");    
            String price = resultSet.getString("price");  
            f = new Flight(id, Integer.toString(fromLoc), Integer.toString(toLoc), airline, total_seats, flight_date, price);
            
        }
         
        resultSet.close();
        statement.close();
         
        disconnect_db();
         
        return f;
    }
     
    public boolean delete(String id) throws SQLException {
    	connect_db() ;
        String sql = "DELETE FROM flight where id = ?";
         
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, id);
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect_db();
        return rowDeleted;     
    }
     
    
    public Flight getflight(int id) throws SQLException {
    	connect_db() ;
        Flight flight = null;
        String sql = "SELECT * FROM flight WHERE id = ?"; 
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id); 
        ResultSet resultSet = statement.executeQuery(); 
        if (resultSet.next()) { 
            String from_location = resultSet.getString("from_location"); 
            String to_location = resultSet.getString("to_location");  
            int total_seats = resultSet.getInt("total_seats");  
            String flight_date = resultSet.getString("flight_date");  
            String price = resultSet.getString("price");  
            int airline_id = resultSet.getInt("airline_id");   
            flight = new Flight(id, from_location, to_location, airline_id, total_seats, flight_date, price); 
        } 
        resultSet.close();
        statement.close(); 
        return flight;
    }

	public boolean availabeSeats(Flight f, int no_seats) throws SQLException {
		// TODO Auto-generated method stub
		connect_db() ;
		int sum_seats = 0; 
        String sql = "SELECT sum(no_seates) FROM `flight_booking` WHERE flight_id = ?"; 
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, f.getId()); 
        ResultSet resultSet = statement.executeQuery(); 
        if (resultSet.next()) { 
        	sum_seats = resultSet.getInt("sum(no_seates)");  
        } 

        resultSet.close();
        statement.close(); 
		return ((f.gettotalseats() - sum_seats) >= no_seats);
	}

	public boolean confirm(int passenger_id, int flight_id, int seats_no) throws SQLException {
		connect_db();
        String sql = "INSERT INTO flight_booking (flight_id, passenger_id, book_date, no_seates) VALUES (?, ?, now(), ?)";          
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, flight_id);
        statement.setInt(2, passenger_id);
        statement.setInt(3, seats_no); 
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect_db();
        return rowInserted;
	}
	
	public List<Book>  listOfBooking(int passenger_id) throws SQLException {
		connect_db() ;
        List<Book> listF= new ArrayList<Book>();
        String sql = "SELECT * FROM flight_booking"; 
        if(passenger_id != 0)
        	sql = "SELECT * FROM flight_booking where passenger_id = "+passenger_id; 
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int flight_id = resultSet.getInt("flight_id"); 
            int pass_id = resultSet.getInt("passenger_id");  
            String book_date = resultSet.getString("book_date"); 
            int no_seates = resultSet.getInt("no_seates");  
            Book f = new Book(id, new Flight(flight_id), new Passenger(pass_id), no_seates , book_date);
            listF.add(f);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect_db();
         
        return listF;
	}
}

    

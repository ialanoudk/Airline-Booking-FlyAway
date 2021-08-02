    package flyaway.flight;

import java.sql.SQLException;

import flyaway.airlines.AireLineDAO;
import flyaway.locations.*;

public class Flight {
    protected int id;
    private String from_location;
    private String to_location	;
    private int total_seats;
    private int airline_id;
    private String flight_date;
    private String price;
    private LocationDAO location;
    private AireLineDAO airLineDB;
    private String tolocationtitle;
    private String fromlocationtitle;
    private String airlinetitle;
 
    public Flight() {
    }
 
    public Flight(int id) {
        this.id = id;
        FlightDAO fdb = new FlightDAO();
        try {
			Flight f = fdb.getflight(id);
			if(f != null) {
				this.from_location = f.getfromlocation();
		        this.to_location = f.gettolocation();
		        this.total_seats = f.gettotalseats();
		        this.flight_date = f.getdate();
		        this.airline_id = f.getairlineid();
		        this.price = f.getprice();
			}else {
				this.from_location = "Flight Deleted";
		        this.to_location =  "Flight Deleted";
		        this.total_seats = 0;
		        this.flight_date =  "Flight Deleted";
		        this.airline_id = 0;
		        this.price =  "0.0";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
 
    public Flight(int id, String from_location, String to_location, int airline_id, int total_seats, String flight_date, String price) {
        this(from_location, to_location, airline_id, total_seats, flight_date, price);
        this.id = id;
    }
     
    public Flight(String from_location, String to_location, int airline_id, int total_seats, String flight_date, String price) {
        this.from_location = from_location;
        this.to_location = to_location;
        this.total_seats = total_seats;
        this.flight_date = flight_date;
        this.airline_id = airline_id;
        this.price = price;
    }
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }

    public String getfromlocation() {
        return from_location;
    }

    public String gettolocation() {
        return to_location;
    }
 
    public int gettotalseats() {
    	return total_seats;
    }


    public String getdate() {
    	return flight_date;
    }

    public String getprice() {
    	return price;
    }
    
    public int getairlineid() {
    	return airline_id;
    }

    public String getfromlocationtitle() {
    	location = new LocationDAO();
    	fromlocationtitle = "";
    	try {
    		if(location.getlocations(Integer.parseInt(from_location)) != null)
    			fromlocationtitle =  location.getlocations(Integer.parseInt(from_location)).getname();
    		else
    			fromlocationtitle = "Deleted"; 
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return fromlocationtitle;
    }
    
    public String gettolocationtitle() {
    	location = new LocationDAO();
    	tolocationtitle = "";
    	try {
    		if(location.getlocations(Integer.parseInt(to_location)) != null)
    			tolocationtitle=  location.getlocations(Integer.parseInt(to_location)).getname();
    		else
    			tolocationtitle = "Deleted";
    		
    	
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return tolocationtitle;
    }
    
    public String getairlinetitle() {
    	airLineDB = new AireLineDAO();
    	airlinetitle = "";
    	try {
    		if(airLineDB.getAirline(airline_id) != null)
    			airlinetitle=  airLineDB.getAirline(airline_id).getname();
    		else
    			airlinetitle = "Deleted";
    	
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return airlinetitle;
    }
}
    

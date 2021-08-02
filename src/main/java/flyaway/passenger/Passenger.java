package flyaway.passenger;

import java.sql.SQLException;

import flyaway.flight.Flight;
import flyaway.flight.FlightDAO;

public class Passenger {
    protected int id;
    protected String name; 
    protected String email; 
    protected String phone; 
 
    public Passenger() {
    }
 
    public Passenger(int id) {
        this.id = id;

        PassengerDAO pdb = new PassengerDAO();
        try {
        	Passenger p = pdb.getPassenger(id);
			this.name = p.getname();
	        this.email = p.getemail();
	        this.phone = p.getphone(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
 
    public Passenger(int id, String name, String email, String phone) {
        this(name , email, phone);
        this.id = id;
    }
     
    public Passenger(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }

    public String getname() {
        return name;
    }

    public String getemail() {
        return email;
    }
    
    public String getphone() {
        return phone;
    }
 
    public void setname(String name) {
        this.name = name;
    }
 
}
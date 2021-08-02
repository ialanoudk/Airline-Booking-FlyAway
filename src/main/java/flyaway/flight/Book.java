    package flyaway.flight;

import java.sql.SQLException;

import flyaway.airlines.AireLineDAO;
import flyaway.locations.*;
import flyaway.passenger.Passenger;

public class Book {
    protected int id;
    private Flight flight;
    private Passenger passenger; 
    private int no_seates;
    private String book_date;
 
    public Book() {
    }
 
    public Book(int id) {
        this.id = id;
    }
 
    public Book(int id, Flight flight, Passenger passenger,  int no_seates, String book_date) {
        this(flight, passenger,  no_seates, book_date);
        this.id = id;
    }
     
    public Book(Flight flight, Passenger passenger,  int no_seates, String book_date) {
    	this.flight = flight;
    	this.passenger = passenger;
    	this.no_seates = no_seates;
    	this.book_date = book_date;
    }
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Flight getFlight() {
        return flight;
    }
 
    public int getnoseats() {
    	return no_seates;
    }


    public String getbookdate() {
    	return book_date;
    }

}
    

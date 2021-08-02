    package flyaway.admin.pages;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import flyaway.airlines.*;
import flyaway.flight.Book;
import flyaway.flight.Flight;
import flyaway.flight.FlightDAO;
import flyaway.locations.*;
import flyaway.passenger.Passenger;
import flyaway.passenger.PassengerDAO;
  
public class adminBookListServlet extends HttpServlet {   
	LocationDAO locationDB;
	AireLineDAO airLineDB;
	PassengerDAO passenger;
    FlightDAO flight_db = new FlightDAO();
    
    public void init() { 
    	locationDB = new LocationDAO();
    	airLineDB = new AireLineDAO();
    	passenger= new PassengerDAO();
    } 
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 

        HttpSession session = request.getSession();  
        RequestDispatcher dispatcher = request.getRequestDispatcher("my_books.jsp");
        dispatcher.forward(request, response);
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        

        HttpSession session = request.getSession();  
	    boolean adminLogin = false;
	    if(session.getAttribute("adminLogin") != null) {
	    	adminLogin=  (boolean)session.getAttribute("adminLogin");
	    }

    	if(!adminLogin){    
    		response.sendRedirect(request.getContextPath() + "/admin/login");
    	}else { 
            RequestDispatcher dispatcher = request.getRequestDispatcher("users_books.jsp"); 
        	String email= (String)session.getAttribute("email"); 
		    try {
				Passenger p =  passenger.getPassenger(email);
	            request.setAttribute("passenger", p);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    List<Book> listBooks= new ArrayList<Book>();
		    try {
				listBooks = flight_db.listOfBooking(0);
	            request.setAttribute("listBooks", listBooks);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            dispatcher.forward(request, response);
    	}
    }
    
}
    

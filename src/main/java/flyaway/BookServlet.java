package flyaway;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import flyaway.airlines.*;
import flyaway.flight.Flight;
import flyaway.flight.FlightDAO;
import flyaway.locations.*;
import flyaway.passenger.Passenger;
import flyaway.passenger.PassengerDAO;
  
public class BookServlet extends HttpServlet {   
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
    	int flight_id = (int)session.getAttribute("flight_id");
    	int seats_no = (int)session.getAttribute("seats_no"); 
    	int passenger_id = (int)session.getAttribute("id"); 
    	boolean confirm;
		try {
			confirm = flight_db.confirm(passenger_id, flight_id,seats_no);
	        request.setAttribute("confirm", confirm);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        RequestDispatcher dispatcher = request.getRequestDispatcher("book.jsp");
        dispatcher.forward(request, response);
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        

        HttpSession session = request.getSession(); 
        int seats_no = 0;
        int flight_id = 0;
        if(request.getParameter("seats_no") != null) {
	        seats_no = Integer.parseInt(request.getParameter("seats_no")); 
	        flight_id = Integer.parseInt(request.getParameter("id"));
        }else {   
        	flight_id = (int)session.getAttribute("flight_id");
        	seats_no = (int)session.getAttribute("seats_no");  
        }
        Flight flight = null;
        try {
			flight = flight_db.getflight(flight_id);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	    session.setAttribute("flight_id", flight_id);  
	    session.setAttribute("seats_no", seats_no);  
	    int id = -1;
	    if(session.getAttribute("id") != null) {
	    	id=  (int)session.getAttribute("id");
	    }


    	if(id==-1){    
    		response.sendRedirect(request.getContextPath() + "/login.jsp");
    	}else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("book.jsp"); 
        	String email= (String)session.getAttribute("email"); 
		    try {
				Passenger p =  passenger.getPassenger(email);
	            request.setAttribute("passenger", p);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            request.setAttribute("flight", flight);
            dispatcher.forward(request, response);
    	}
    }
    
}
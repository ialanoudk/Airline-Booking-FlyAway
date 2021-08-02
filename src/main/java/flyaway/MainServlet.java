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
  
public class MainServlet extends HttpServlet {   
	LocationDAO locationDB;
	AireLineDAO airLineDB;
	FlightDAO flightDB;
    List<Locations> list;
    List<AireLine> airList;
    
    public void init() { 
    	locationDB = new LocationDAO();
    	airLineDB = new AireLineDAO();
    	flightDB = new FlightDAO();
    }
    
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 

        int fromLoc = Integer.parseInt(request.getParameter("from"));
        int toLoc = Integer.parseInt(request.getParameter("to"));
        int airline = Integer.parseInt(request.getParameter("airline"));
        String flight_date = request.getParameter("date");
        int no_seats = Integer.parseInt(request.getParameter("no_seats"));
        boolean availabe_seats = false;
        Flight flight = null;
		try {
			flight = flightDB.findflight(fromLoc, toLoc, airline, flight_date);
			if(flight != null)
				availabe_seats = flightDB.availabeSeats(flight, no_seats);
 
			list = locationDB.getLocations();
			airList = airLineDB.getAirlines();
	        request.setAttribute("locations", list);
	        request.setAttribute("airList", airList);
	        request.setAttribute("flight", flight);
	        request.setAttribute("availabe_seats", availabe_seats);
	        request.setAttribute("fromLoc", fromLoc);
	        request.setAttribute("toLoc", toLoc);
	        request.setAttribute("airline", airline);
	        request.setAttribute("no_seats", no_seats);
	        request.setAttribute("flight_date", flight_date); 
	        request.setAttribute("submit", true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
        dispatcher.forward(request, response);
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
 
		try {
			list = locationDB.getLocations();
			airList = airLineDB.getAirlines();
	        request.setAttribute("locations", list);
	        request.setAttribute("airList", airList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
        dispatcher.forward(request, response);
    }
    
}
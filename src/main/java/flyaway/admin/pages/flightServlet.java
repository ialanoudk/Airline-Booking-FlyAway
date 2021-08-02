    package flyaway.admin.pages;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import flyaway.admin.AdminDAO;
import flyaway.airlines.AireLine;
import flyaway.airlines.AireLineDAO;
import flyaway.flight.Flight;
import flyaway.flight.FlightDAO;
import flyaway.locations.LocationDAO;
import flyaway.locations.Locations; 
  
public class flightServlet extends HttpServlet {   
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

        String fromLoc = request.getParameter("from");
        String toLoc = request.getParameter("to");
        String airline = request.getParameter("airline");
        String flight_date = request.getParameter("date");
        String no_seats = request.getParameter("no_seats");
        String price = request.getParameter("price"); 
         
        try { 
        	Flight f = new Flight(fromLoc, toLoc, Integer.parseInt(airline) , Integer.parseInt(no_seats), flight_date, price);
			if(flightDB.add(f))
			{ 
		        response.sendRedirect("./listflights");
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("flightForm.jsp"); 
			    dispatcher.forward(request, response);
			}
		} catch (SQLException | IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
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
			try {
				list = locationDB.getLocations();
				airList = airLineDB.getAirlines();
		        request.setAttribute("locations", list);
		        request.setAttribute("airList", airList);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        RequestDispatcher dispatcher = request.getRequestDispatcher("flightForm.jsp");
	        dispatcher.forward(request, response); 
    	}
    }
    
}
    

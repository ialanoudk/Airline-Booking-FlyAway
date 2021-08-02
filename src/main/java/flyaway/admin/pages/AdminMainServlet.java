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

import flyaway.airlines.*;
import flyaway.flight.Flight;
import flyaway.flight.FlightDAO;
import flyaway.locations.*;
import flyaway.passenger.Passenger;
  
public class AdminMainServlet extends HttpServlet {   
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

         

        RequestDispatcher dispatcher = request.getRequestDispatcher("admin_home.jsp");
        dispatcher.forward(request, response);
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        

        HttpSession session = request.getSession();  
	    if(session.getAttribute("adminLogin") == null) {
    		response.sendRedirect(request.getContextPath() + "/admin/login");
	    }else {
	        RequestDispatcher dispatcher = request.getRequestDispatcher("admin_home.jsp");
	        dispatcher.forward(request, response);
	    }
    }
    
}
    

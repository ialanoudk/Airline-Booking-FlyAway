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
  
public class flightListServlet extends HttpServlet {   
	FlightDAO fdb = null;
    public void init() { 
    	fdb = new FlightDAO (); 
    }
    
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 
 
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
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("flightList.jsp");
	    	List<Flight> list;
			try {
				list = fdb.getflight();
		        request.setAttribute("listFlights", list);
			    dispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
    
}
    

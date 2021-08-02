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
import flyaway.passenger.PassengerDAO;
  
public class LogoutServlet extends HttpServlet {   
	PassengerDAO passenger = null;
    public void init() { 
    	passenger = new PassengerDAO(); 
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 

         
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        

        HttpSession session=request.getSession();  
        session.invalidate();  
        response.sendRedirect("./");
    }
    
    
}
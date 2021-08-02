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
  
public class RegisterServlet extends HttpServlet {   
	PassengerDAO passenger = null;
    public void init() { 
    	passenger = new PassengerDAO(); 
    }
    
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 

        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        String phone = request.getParameter("phone");
        String name = request.getParameter("name");

        boolean valid_user = false;
        try {
			if(passenger.register(name, phone, email, pass))
			{
			    HttpSession session=request.getSession();  

			    Passenger p =  passenger.getPassenger(email);
			    session.setAttribute("id",p.getId());   
			    session.setAttribute("email", p.getemail()); 
			    session.setAttribute("name", p.getname());  

		        RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
		        dispatcher.forward(request, response);
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			    request.setAttribute("valid_user", valid_user);
			    dispatcher.forward(request, response);
			}
		} catch (SQLException | IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
 
    }
    
}
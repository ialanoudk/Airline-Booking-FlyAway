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
import flyaway.locations.LocationDAO;
import flyaway.locations.Locations; 
  
public class updateAirlineServlet extends HttpServlet {   
	AireLineDAO a = null;
    public void init() { 
    	a = new AireLineDAO(); 
    }
    
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 

        String name = request.getParameter("name"); 
        String id = request.getParameter("id"); 
        boolean error = false;
        try {
			if(a.update(id, name))
			{ 

		        response.sendRedirect("./listairlines");
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("airlineForm.jsp");
			    request.setAttribute("error", error);
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
	        String ev = request.getParameter("ev"); 
	        String id = request.getParameter("id"); 
	    	if(ev.equals("delete"))
	    	{
	    		try {
					if(a.delete(id))
					{
				        response.sendRedirect("./listairlines");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}else {
		        AireLine l;
				try {
					l = a.getAirline(Integer.parseInt(id));
			        request.setAttribute("airline", l);
			        RequestDispatcher dispatcher = request.getRequestDispatcher("airlineForm.jsp");
			        dispatcher.forward(request, response);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
    	}
 
 
    }
    
}
    

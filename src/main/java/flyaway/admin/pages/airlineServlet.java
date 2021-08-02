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
import flyaway.airlines.AireLineDAO;
import flyaway.locations.LocationDAO; 
  
public class airlineServlet extends HttpServlet {   
	AireLineDAO loc = null;
    public void init() { 
    	loc = new AireLineDAO(); 
    }
    
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 

        String name = request.getParameter("name"); 
        boolean error = false;
        try {
			if(loc.add(name))
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("airlineForm.jsp"); 
		    dispatcher.forward(request, response); 
    	}
 
    }
    
}
    

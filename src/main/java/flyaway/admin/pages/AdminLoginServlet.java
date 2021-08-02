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
  
public class AdminLoginServlet extends HttpServlet {   
	AdminDAO admin = null;
    public void init() { 
    	admin = new AdminDAO(); 
    }
    
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 

        String email = request.getParameter("email");
        String pass = request.getParameter("pass");

        boolean valid_user = false;
        try {
			if(admin.adminLogin(email, pass))
			{
			    HttpSession session=request.getSession();  
			    session.setAttribute("adminLogin", true);  

		        response.sendRedirect("./");
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("admin_login.jsp");
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("admin_login.jsp"); 
	    dispatcher.forward(request, response); 
 
    }
    
}
    

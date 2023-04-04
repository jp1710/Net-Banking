package bankingApplication;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Servlet implementation class AdminMenuServlet
 */
//@WebServlet("/AdminMenu")
public class AdminMenuServlet extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		 
		try {
			String option = request.getParameter("atmMenuOptions");
			
			if(option.equals("checkAtmBalance"))
			{
				RequestDispatcher dispatcher = request.getRequestDispatcher("/CheckATMBalance.jsp");
				dispatcher.forward(request, response);
			}
			else if(option.equals("showCustomers"))
			{
				RequestDispatcher dispatcher = request.getRequestDispatcher("/showCustomersServlet");
				dispatcher.forward(request, response);
			}
			else if(option.equals("loadCash"))
			{
				RequestDispatcher dispatcher = request.getRequestDispatcher("/LoadCash.jsp");
				dispatcher.forward(request, response);
			}
			else if(option.equals("addCustomers"))
			{
				RequestDispatcher dispatcher = request.getRequestDispatcher("/AddCustomers.jsp");
				dispatcher.forward(request, response);
			}
		}
		catch(Exception e)
		{
			System.out.println("Error occurred while admin choosing menu options! "+e);
			response.sendRedirect("ErrorPage.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

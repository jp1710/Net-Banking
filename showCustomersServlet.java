package bankingApplication;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;

/**
 * Servlet implementation class showCustomersServlet
 */
//@WebServlet("/showCustomers")
public class showCustomersServlet extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession(false);
		
		
		if(session.getAttribute("user")!=null)
		{
			if(session.getAttribute("user").equals("customer"))
			{
				response.sendRedirect("CustomerOperations.jsp");
			} 
			else
			{
				PrintWriter out = response.getWriter();
				ArrayList<ArrayList<String>> lists = new ArrayList<ArrayList<String>>();
				
				response.setContentType("text/html");
				try {
					Class.forName("org.postgresql.Driver");
					lists = BankDatabase.getAllAccountDetails();
					JSONArray list = BankDatabase.getAllAccountDetailsObj();
					request.setAttribute("customerDetails", lists);
					request.setAttribute("customerDetailsObj", list);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/ShowCustomerDetails.jsp");
					dispatcher.forward(request, response);				}
				catch(Exception e)
				{
					System.out.println("Error occurred while showing customer Details!! "+e);
					response.sendRedirect("ErrorPage.jsp");
				}
			}
		}
		else
		{
			response.sendRedirect("homePage.jsp");
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

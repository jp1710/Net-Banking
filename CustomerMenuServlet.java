package bankingApplication;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CustomerMenuServlet
 */
//@WebServlet("/CustomerMenu")
public class CustomerMenuServlet extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		 
		try {
			String option = request.getParameter("atmMenuOptions");
			
			if(option.equals("checkBalance"))
			{
				RequestDispatcher dispatcher = request.getRequestDispatcher("/CheckCustomerBalance.jsp");
				dispatcher.forward(request, response);
			}
			else if(option.equals("withdrawMoney"))
			{
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WithdrawMoney.jsp");
				dispatcher.forward(request, response);
			}
			else if(option.equals("transferMoney"))
			{
				RequestDispatcher dispatcher = request.getRequestDispatcher("/TransferMoney.jsp");
				dispatcher.forward(request, response);
			}
			else if(option.equals("miniStatement"))
			{
//				RequestDispatcher dispatcher = request.getRequestDispatcher("/MiniStatement.jsp");
//				dispatcher.forward(request, response);
				response.sendRedirect("MiniStatementServlet");
			}
		}
		catch(Exception e)
		{
			System.out.println("Error occurred while customer choosing menu options! "+e);
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

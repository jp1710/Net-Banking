package bankingApplication;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CheckCustomerBalanceServlet
 */
//@WebServlet("/CheckCustomerBalance")
public class CheckCustomerBalanceServlet extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		try {
			Class.forName("org.postgresql.Driver");
			if(request.getParameter("balancePin")!=null)
			{
					if(request.getParameter("balancePin")!="")
					{
						String accountNo = session.getAttribute("accountNo")+"";
						
						request.setAttribute("accountNo", accountNo);
						String balance = BankDatabase.getAccountBalance(accountNo)+"";
						request.setAttribute("accountBalance", balance);
					}
//					else
//					{
//						request.setAttribute("errorMsg","Account Pin is missing!!");
//					}
				
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/CheckCustomerBalance.jsp");
			dispatcher.forward(request, response);
			
		}
		catch(Exception e)
		{
			System.out.println("Error occurred while fetching customer balance "+e);
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

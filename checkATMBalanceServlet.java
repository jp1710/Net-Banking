package bankingApplication;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.RequestDispatcher;

/**
 * Servlet implementation class checkBalanceServlet
 */
//@WebServlet("/checkATMBalance")
public class checkATMBalanceServlet extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Class.forName("org.postgresql.Driver");
			if(request.getParameter("atmId")!=null)
			{
				if(request.getParameter("atmId")!="")
				{
					int id = Integer.parseInt(request.getParameter("atmId"));
					int balance = BankDatabase.getATMBalance(id);
					
					request.setAttribute("ATMBalance", balance);
					request.setAttribute("ATMId", id);
				}
				else
				{
					request.setAttribute("errorMsg","ATM ID is missing!!");
				}
				
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/CheckATMBalance.jsp");
			dispatcher.forward(request, response);
		}
		catch(Exception e)
		{
			System.out.println("Error occurred while checking Atm balance!"+e);
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

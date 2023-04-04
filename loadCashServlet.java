package bankingApplication;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletRequest;
import javax.servlet.RequestDispatcher;

/**
 * Servlet implementation class loadCashServlet
 */
//@WebServlet("/loadCash")
public class loadCashServlet extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		final int ONE_LAKH = 100000;
		
		try {
			if(request.getParameter("loadAmount")!=null && request.getParameter("loadAtmId")!=null)
			{
				if(request.getParameter("loadAmount")!="" && request.getParameter("loadAtmId")!="")
				{
					int amount = Integer.parseInt(request.getParameter("loadAmount"));
					int id = Integer.parseInt(request.getParameter("loadAtmId"));
					
					
					
					int total = BankDatabase.getATMBalance(id);
					if(total>=0)
					{
						request.setAttribute("loadAmount", amount);
						request.setAttribute("loadAtmId", id);
						
						int n = amount/ONE_LAKH;
						int tho = n*20,hun=n*300,fiv=n*100;
						int thousand = BankDatabase.getATMValues("THOUSAND",1) + tho;
						int hundred = BankDatabase.getATMValues("HUNDRED",1) + hun;
						int fiveHundred = BankDatabase.getATMValues("FIVE_HUNDRED",1) + fiv;
						
						request.setAttribute("hundred", hun);
						request.setAttribute("thousand", tho);
						request.setAttribute("fiveHundred", fiv);
						
						BankDatabase.updateATMValues("THOUSAND",thousand,1);
						BankDatabase.updateATMValues("HUNDRED",hundred,1);
						BankDatabase.updateATMValues("FIVE_HUNDRED",fiveHundred,1);
						BankDatabase.updateATMBalance(amount+total, id);
					}
				}
				else
				{
					String error = "";
					if(request.getParameter("loadAmount")=="")
					{
						error+="Amount is missing!! ";
					}
					if(request.getParameter("loadAtmId")=="")
					{
						error+="ATM ID is missing!!";
					}
					request.setAttribute("errorMsg",error);
				}
				
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/LoadCash.jsp");
			dispatcher.forward(request, response);
		}
		catch(Exception e)
		{
			System.out.println("Error occurred while loading cash "+e);
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


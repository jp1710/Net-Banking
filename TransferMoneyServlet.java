package bankingApplication;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class TransferMoneyServlet
 */
//@WebServlet("/TransferMoney")
public class TransferMoneyServlet extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		
		try {
			Class.forName("org.postgresql.Driver");
			if(session!=null)
			{
					String accountNo = request.getSession(false).getAttribute("accountNo")+"";
					//String toAccountNo = req.getParameter("toAccountNo");
					if(request.getParameter("transferAmount")!=null && request.getParameter("toAccountNo")!=null && request.getParameter("toAccountPin")!=null)
					{
						if(request.getParameter("transferAmount")!="" && request.getParameter("toAccountNo")!="" && request.getParameter("toAccountPin")!="")
						{
							int transferAmount = Integer.parseInt(request.getParameter("transferAmount"));
							
							if(transferAmount!=0)
							{

								String toAccountNo = EncodeDecodeData.decode(request.getParameter("toAccountNo"));
								//toAccountNo = EncodeDecodeData.decode(toAccountNo);
								
											int fromTotalBalance = BankDatabase.getAccountBalance(accountNo);
											int toTotalBalance = BankDatabase.getAccountBalance(toAccountNo);
											if(fromTotalBalance >= transferAmount)
											{
												int fromBalance = fromTotalBalance - transferAmount;
												int toBalance = toTotalBalance + transferAmount;
												
												BankDatabase.updateAccountBalance(fromBalance, accountNo);
												String remarks = "'Funds Transfered to "+toAccountNo+"'";
												BankDatabase.insertTransaction(accountNo,remarks,"DEBIT",transferAmount);
												
												
												toBalance = toTotalBalance + transferAmount;
												BankDatabase.updateAccountBalance(toBalance, toAccountNo);
												remarks = "'Funds Transfered from "+accountNo+"'";
												BankDatabase.insertTransaction(toAccountNo,remarks,"CREDIT",transferAmount);
												
												request.setAttribute("transferAmount",transferAmount );
												request.setAttribute("toAccountNo",toAccountNo);
												request.setAttribute("fromBalance", fromBalance);
												
											}
											else
											{
												//out.println("Insufficient Fund!!");
												request.setAttribute("errorMsg", "Insufficient Fund in your Account!!");
											}
							}
							else
							{
								request.setAttribute("errorMsg", "Amount should be greater than 0!!");
							}
						}
					}
						
					RequestDispatcher dispatcher = request.getRequestDispatcher("/TransferMoney.jsp");
					dispatcher.forward(request, response);
			}
			else
			{
				response.sendRedirect("homePage.jsp");
			}
		}
		catch(Exception e)
		{
			System.out.println("Error occurred while transfering money "+e);
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

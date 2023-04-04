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
 * Servlet implementation class WithdrawMoneyServlet
 */
//@WebServlet("/WithdrawMoney")
public class WithdrawMoneyServlet extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession(false);
		
		final int HUNDRED = 100;
		final int THOUSAND = 1000;
		final int FIVE_HUNDRED = 500;
		
 
		try {
			Class.forName("org.postgresql.Driver");
			if(session!=null)
			{
				String accountNo = session.getAttribute("accountNo")+"";
				
					if(request.getParameter("withdrawAccountPin")!=null && request.getParameter("withdrawAmount")!=null)
					{
						
						int withdrawMoney = Integer.parseInt(request.getParameter("withdrawAmount"));
						
						//System.out.println(accountNo+"-"+pin);
						
						
								int balance = BankDatabase.getAccountBalance(accountNo);
								int atmMoney = BankDatabase.getATMValues("TOTAL_BALANCE", 1);
								System.out.println(balance+"///"+withdrawMoney);
								if(withdrawMoney <= balance)
								{
									if(withdrawMoney <= atmMoney)
									{
										int wd_money = withdrawMoney;
										int thousand=0,hundred=0,fiveHundred=0;
										
										if(withdrawMoney < THOUSAND)
										{
											hundred = wd_money/HUNDRED;
										}
										else if(withdrawMoney <= 5*THOUSAND)
										{
											thousand = 1;
											wd_money-=THOUSAND;
											if(wd_money <= 3*THOUSAND)
											{
												fiveHundred = wd_money/FIVE_HUNDRED;
												wd_money-=fiveHundred*FIVE_HUNDRED;
											}
											else
											{
												fiveHundred = 6;
												wd_money-=fiveHundred*FIVE_HUNDRED;
											}
											hundred = wd_money/HUNDRED;
										}
										else
										{
											thousand = 3;
											wd_money-=3*THOUSAND;
											fiveHundred = wd_money/FIVE_HUNDRED;
											wd_money%=FIVE_HUNDRED;
											hundred = wd_money/HUNDRED;
										}
										int valHundred = BankDatabase.getATMValues("HUNDRED",1);
										int valFiveHundred = BankDatabase.getATMValues("FIVE_HUNDRED",1);
										int valThousand = BankDatabase.getATMValues("THOUSAND",1);
										
										if(valHundred!=-1 && valFiveHundred!=-1 && valThousand!=-1 && atmMoney!=-1)
										{
											valHundred -= hundred;
											valFiveHundred -= fiveHundred;
											valThousand -= thousand;
											
											BankDatabase.updateATMValues("HUNDRED",valHundred,1);
											BankDatabase.updateATMValues("FIVE_HUNDRED",valFiveHundred,1);
											BankDatabase.updateATMValues("THOUSAND",valThousand,1);
											
											int money = balance-withdrawMoney;
											request.setAttribute("wdMoney", withdrawMoney);
											request.setAttribute("currBalance", money);
											request.setAttribute("THOUSAND", thousand);
											request.setAttribute("HUNDRED",hundred);
											request.setAttribute("FIVE_HUNDRED",fiveHundred);
											
											atmMoney-=withdrawMoney;
											BankDatabase.updateATMValues("TOTAL_BALANCE",atmMoney,1);
											
											BankDatabase.insertTransaction(accountNo,"'Debited from ATM'","DEBIT",withdrawMoney);
										
											BankDatabase.updateAccountBalance(money, accountNo);
											request.setAttribute("currBalance", money);
											
										}
									}
									else
									{
										//less money in atm
										String str = "Insufficient fund in the ATM Machine! Try with lesser Amount!!";
										request.setAttribute("errorMsg", str);
									}
								}
								else
								{
									//less balance in account
									String str = "Insufficient fund in your Account!Try with lesser Amount!!";
									request.setAttribute("errorMsg", str);
								}
					}
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WithdrawMoney.jsp");
					dispatcher.forward(request, response);
				
			}
			else
			{
				response.sendRedirect("homePage.jsp");
			}
		}
		catch(Exception e)
		{
			System.out.println("Error occurred while withdrawing money "+e);
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


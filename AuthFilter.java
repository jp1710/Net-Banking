package bankingApplication;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpSession;

import bankingApplication.BankDatabase;
import bankingApplication.EncodeDecodeData;
import bankingApplication.StringValidate;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class AuthFilter
 */
//@WebFilter("/*")
public class AuthFilter implements Filter {

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		
		try {
			Class.forName("org.postgresql.Driver");
				
				
				
				if(req.getParameter("userAccountNumber")!=null && req.getParameter("userAccountPin")!=null)
				{
					if(req.getParameter("userAccountNumber")!="" && req.getParameter("userAccountPin")!="")
					{
						String accountNo = req.getParameter("userAccountNumber");
						String pin = req.getParameter("userAccountPin");
						accountNo = EncodeDecodeData.decode(accountNo);
						pin = EncodeDecodeData.decode(pin);
						
						System.out.println(accountNo+"-"+pin);
						
						
						if(!StringValidate.checkSymbols(accountNo))
						{
							req.setAttribute("errorMsg","Account Number should only contain Alphabets or Numbers or Both!!");
							RequestDispatcher dispatcher = req.getRequestDispatcher("/homePage.jsp");
							dispatcher.forward(req, res);
						}
						else
						{
							if(BankDatabase.checkAdminAccountNo(accountNo))
							{	
								if(BankDatabase.checkAdminPin(accountNo, pin))
								{
									//System.out.println("admin");
									System.out.println(accountNo+"-"+pin);
									String name = BankDatabase.getAdminName(accountNo);
									//session.removeAttribute("errorMsg");
									
									session.setAttribute("user", "admin");
									session.setAttribute("userName", name);
									session.setAttribute("accountNo", accountNo);
									chain.doFilter(req, res);
								}
								else
								{
									//session.setAttribute("errorMsg", "Invalid Pin!");
									req.setAttribute("errorMsg","Invalid Pin" );
									RequestDispatcher dispatcher = req.getRequestDispatcher("/homePage.jsp");
									dispatcher.forward(req, res);
								}
							}
							else if(BankDatabase.checkCustomerAccountNo(accountNo))
							{
								if(BankDatabase.checkCustomerPin(accountNo, pin))
								{
									String name = BankDatabase.getAccountHolderName(accountNo);
									//session.removeAttribute("errorMsg");
									session.setAttribute("user", "customer");
									session.setAttribute("userName", name);
									session.setAttribute("accountNo", accountNo);
									chain.doFilter(req, res);
								}
								else
								{
									//session.setAttribute("errorMsg", "Invalid Pin!!");
									req.setAttribute("errorMsg","Invalid Pin" );
									RequestDispatcher dispatcher = req.getRequestDispatcher("/homePage.jsp");
									dispatcher.forward(req, res);
								}
							}
							else
							{
								//session.setAttribute("errorMsg","Account Number Not Found!!");
								req.setAttribute("errorMsg","Account Number Not Found!!" );
								RequestDispatcher dispatcher = req.getRequestDispatcher("/homePage.jsp");
								dispatcher.forward(req, res);
							}
						}
					}
				}
				else if(session.getAttribute("accountNo")!=null)
				{
					String accountNo = session.getAttribute("accountNo")+"";
					
					if(req.getParameter("balancePin")!=null)
					{
						if(req.getParameter("balancePin")!="")
						{
							String pin = req.getParameter("balancePin");
							
							pin = EncodeDecodeData.decode(pin);
							
							if(BankDatabase.checkCustomerPin(accountNo, pin))
							{
//								req.setAttribute("accountNo", accountNo);
//								String balance = BankDatabase.getAccountBalance(accountNo)+"";
//								req.setAttribute("accountBalance", balance);
								chain.doFilter(req, res);
							}
							else
							{
								req.setAttribute("errorMsg", "InvalidPin");
								RequestDispatcher dispatcher = req.getRequestDispatcher("/CheckCustomerBalance.jsp");
								dispatcher.forward(req, res);
							}
						}
						else
						{
							RequestDispatcher dispatcher = req.getRequestDispatcher("/CheckCustomerBalance.jsp");
							dispatcher.forward(req, res);
						}
					}
					else if(req.getParameter("transferAmount")!=null && req.getParameter("toAccountNo")!=null && req.getParameter("toAccountPin")!=null)
					{
//						int transferAmount = Integer.parseInt(req.getParameter("transferAmount"));
						if(req.getParameter("transferAmount")!="" && req.getParameter("toAccountNo")!="" && req.getParameter("toAccountPin")!="")
						{
							String pin = req.getParameter("toAccountPin");
							
							
							String toAccountNo = EncodeDecodeData.decode(req.getParameter("toAccountNo"));
							//toAccountNo = EncodeDecodeData.decode(toAccountNo);
							pin = EncodeDecodeData.decode(pin);
							
							if(BankDatabase.checkCustomerAccountNo(toAccountNo))
							{
								if(accountNo.equals(toAccountNo))
								{
									//out.println("Cannot Transfer to Self!!");
									req.setAttribute("errorMsg", "Cannot Transfer to Self!!");
									RequestDispatcher dispatcher = req.getRequestDispatcher("/TransferMoney.jsp");
									dispatcher.forward(req, res);
								}
								else
								{
									if(BankDatabase.checkCustomerPin(accountNo, pin))
									{				
										chain.doFilter(req,res);
									}
									else
									{
										//out.println("Invalid Pin!!");
										req.setAttribute("errorMsg", "Invalid Pin!!");
										RequestDispatcher dispatcher = req.getRequestDispatcher("/TransferMoney.jsp");
										dispatcher.forward(req, res);
									
									}
								}
							}
							else
							{
								//out.println("Account Number Not Found!!");
								req.setAttribute("errorMsg", "Account Number Not Found!!");
								RequestDispatcher dispatcher = req.getRequestDispatcher("/TransferMoney.jsp");
								dispatcher.forward(req, res);
							}
						}
						else
						{
							RequestDispatcher dispatcher = req.getRequestDispatcher("/TransferMoney.jsp");
							dispatcher.forward(req, res);
						}
					}
					else if(req.getParameter("withdrawAccountPin")!=null && req.getParameter("withdrawAmount")!=null)
					{
						final int THOUSAND = 1000;
						if(req.getParameter("withdrawAccountPin")!="" && req.getParameter("withdrawAmount")!="")
						{
							String pin = req.getParameter("withdrawAccountPin");
							pin = EncodeDecodeData.decode(pin);
							int withdrawMoney = Integer.parseInt(req.getParameter("withdrawAmount"));
							
							if(withdrawMoney <= 10*THOUSAND)
							{
								if(BankDatabase.checkCustomerPin(accountNo, pin))
								{
									System.out.println(accountNo+"[[[]]]"+pin);
									chain.doFilter(req,res);
								}
								else
								{
									//wrong pin
									req.setAttribute("errorMsg", "Invalid Pin!!");
									RequestDispatcher dispatcher = req.getRequestDispatcher("/WithdrawMoney.jsp");
									dispatcher.forward(req, res);
								}
							}
							else
							{
								//withdraw money more than 10000
								String str = "Withdraw Amount Limit Exceeded! Maximum Limit is 10000 INR/ per Transaction!!";
								req.setAttribute("errorMsg", str);
								RequestDispatcher dispatcher = req.getRequestDispatcher("/WithdrawMoney.jsp");
								dispatcher.forward(req, res);
							}
						}
						else
						{
							RequestDispatcher dispatcher = req.getRequestDispatcher("/WithdrawMoney.jsp");
							dispatcher.forward(req, res);
						}
					}
					else
					{
						chain.doFilter(req,res);
					}
				}
				else
				{
					chain.doFilter(req,res);
				}
				
		}
		catch(Exception e)
		{
			System.out.println("Error Occurred while validating user login details! "+e);
			res.sendRedirect("ErrorPage.jsp");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

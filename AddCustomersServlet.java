package bankingApplication;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bankingApplication.BankDatabase;
import bankingApplication.EncodeDecodeData;
import bankingApplication.StringValidate;

/**
 * Servlet implementation class AddCustomerServlet
 */
//@WebServlet("/AddCustomers")
public class AddCustomersServlet extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Class.forName("org.postgresql.Driver");
			
					
			if(request.getParameter("addAccountHolderName")!=null && request.getParameter("addAccountPin")!=null)
			{
				if(request.getParameter("addAccountHolderName")!="" && request.getParameter("addAccountPin")!="")
				{
					String accountName = request.getParameter("addAccountHolderName");
					String pin = request.getParameter("addAccountPin");
					accountName = EncodeDecodeData.decode(accountName);
					pin = EncodeDecodeData.decode(pin);
					
					if(!StringValidate.checkAlphabets(accountName))
					{				
						request.setAttribute("errorMsg", "Account name should only contain alphabets!!");
					}
					else
					{
						if(BankDatabase.getAnyAccountHolderName(accountName))
						{
							request.setAttribute("errorMsg", "An account with the name-"+accountName+" already exists!!");
						}
						else
						{
							if(pin.length()==5)
							{
								request.setAttribute("addName", accountName);
								request.setAttribute("password", pin);
								BankDatabase.insertAccountDetailsValues(accountName, pin);
								
								String accountNo = BankDatabase.getCustomerAccountNo(accountName);
								request.setAttribute("addAccountNo",accountNo);
							}
							else
							{
								request.setAttribute("errorMsg", "Account Pin Length should be 5!!!!");
							}
						}
					}
				}
				else
				{
					String error = "";
					if(request.getParameter("addAccountHolderName")=="")
					{
						error+="Account Name is missing!! ";
					}
					if(request.getParameter("addAccountPin")=="")
					{
						error+="Account Pin is missing!!";
					}
					request.setAttribute("errorMsg",error);
				}
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/AddCustomers.jsp");
			dispatcher.forward(request, response);
		}
		catch(Exception e)
		{
			System.out.println("Error occurred while adding new customers "+e);
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

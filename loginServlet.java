package bankingApplication;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class loginServlet
 */
//@WebServlet("/login")
public class loginServlet extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		if(session!=null)
		{
			
//			String accountNo = request.getParameter("userAccountNumber");
//			String pin = request.getParameter("userAccountPin");
//			accountNo = EncodeDecodeData.decode(accountNo);
//			pin = EncodeDecodeData.decode(pin);
//			
//			System.out.println(accountNo+"-"+pin);
			//System.out.println(session.getAttribute("errorMsg"));
			if(session.getAttribute("errorMsg")!=null)
			{
				//out.println("alert(\'"+session.getAttribute("errorMsg")+"\';");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/homePage.jsp");
				dispatcher.forward(request, response);
			}
			else if(session.getAttribute("user").equals("admin"))
			{
				response.sendRedirect("AdminOperations.jsp");
			}
			else if(session.getAttribute("user").equals("customer"))
			{
				response.sendRedirect("CustomerOperations.jsp");
			}
			else
			{
				response.sendRedirect("homePage.jsp");
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

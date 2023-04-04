package bankingApplication;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;


/**
 * Servlet implementation class MiniStatementServlet
 */
//@WebServlet("/MiniStatement")
public class MiniStatementServlet extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession(false);
		if(session.getAttribute("user")!=null)
		{
			if(session.getAttribute("user").equals("admin"))
			{
				response.sendRedirect("AdminOperations.jsp");
			}
			else
			{
				PrintWriter out = response.getWriter();
				ArrayList<ArrayList<String>> lists = new ArrayList<ArrayList<String>>();
				JSONArray list = new JSONArray();
				response.setContentType("text/html");
				
				try {
					Class.forName("org.postgresql.Driver");
					String accountNo = request.getSession(false).getAttribute("accountNo")+"";
					//System.out.println(accountNo);
					String accountName = request.getSession(false).getAttribute("userName")+"";
					
					//JSONArray list = new JSONArray();
					if(accountNo=="" && accountName=="")
					{
						response.sendRedirect("CustomerOperations.jsp");
					}
					else
					{
						int balance = BankDatabase.getAccountBalance(accountNo);
						lists = BankDatabase.getTransactionDetails(accountNo);
						list = BankDatabase.getTransactionDetailsObj(accountNo);
						request.setAttribute("transaction", lists);
						request.setAttribute("transactionObj", list);
						request.setAttribute("miniBalance", balance);
						
						StringBuffer str = new StringBuffer();
						str.append("ACCOUNT_NAME	ACCOUNT_NO	ACCOUNT_BALANCE\n");
						str.append(accountName+"	"+accountNo+"	"+balance+"\n");
						str.append("TRANSACTION_ID	TRANSACTION_REMARKS		TRANSACTION_TYPE	TRANSACTION_AMOUNT\n");
						if(list!=null)
						{
							for(int i=0;i<list.length();i++)
							{
								JSONObject obj = list.getJSONObject(i);
								str.append(obj.getString("transactionId")+"	"+obj.getString("transactionRemarks")+
											"		"+obj.getString("transactionType")+"	"+obj.getString("transactionAmount")+"\n");
							}
						}
						else
						{
							str.append("---		---		---		---");
						}
						
						FileOutputStream outputStream = new FileOutputStream(new File("/home/local/ZOHOCORP/prasaath-13170/Downloads/eclipse/ZIDE/BankingApplication/src/MiniStatement-"+accountNo+".csv/"));
						
						byte[] buffer = str.toString().getBytes("UTF-8");
						outputStream.write(buffer);
						
						RequestDispatcher dispatcher = request.getRequestDispatcher("/MiniStatement.jsp");
						dispatcher.forward(request, response);

					}
				}
				catch(Exception e)
				{
					System.out.println("Error occurred while generating mini-statement "+e);
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

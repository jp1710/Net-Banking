package bankingApplication;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.json.JSONArray;
import org.json.JSONObject;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Download
 */
//@WebServlet("/Download")
public class DownloadServlet extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			
		HttpSession session = request.getSession(false);
		String accountNo = session.getAttribute("accountNo")+"";
		
		if(accountNo!=null)
		{
			File file = new File("/home/local/ZOHOCORP/prasaath-13170/Downloads/eclipse/ZIDE/BankingApplication/src/MiniStatement-"+accountNo+".csv/");
			String filePath = file.getAbsolutePath();
			
			FileInputStream inputStream = null;
			ServletOutputStream outputStream = null;
			
			
	        
	        //System.out.println(filePath);
	        
	        try {
	        	
	        	inputStream = new FileInputStream(filePath+"/");
	        	outputStream = response.getOutputStream();
	        	
	        	byte[] buffer = new byte[1024];
	        	
	        	int in;
	        	while((in=inputStream.read(buffer))>0)
	        	{
	        		//System.out.println(new String(buffer,StandardCharsets.UTF_8));
	        		outputStream.write(buffer,0,in);
	        	}
	        	response.setContentType("application/octet-stream");
	            response.setHeader("Content-disposition", "attachment; filename=MiniStatement-"+accountNo+"-"+new Date()+".csv");
	        }
	        catch(Exception e)
	        {
	        	System.out.println("Error occurred while downloading MiniStatement!! "+e);
	        	response.sendRedirect("ErrorPage.jsp");
	        }
	        finally {
	        	if(inputStream!=null)
	        	{
	        		inputStream.close();
	        	}
	        	if(outputStream!=null)
	        	{
	        		outputStream.close();
	        		outputStream.flush();
	        	}
	        }
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

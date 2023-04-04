//$Id$
package bankingApplication;

import java.sql.*;
import java.util.*;

import javax.servlet.ServletException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




public class BankDatabase {
		
		static final String url = "jdbc:postgresql://localhost:5432/banking";
		static final String username = "root";
		static final String password = "root";
		
		public static int getIntValue(String str) throws Exception 
		{
			Connection connection = null;	
			Statement statement = null;
			ResultSet resultSet = null;
			try {	
				connection = DriverManager.getConnection(url, username, password);	
				
					statement = connection.createStatement();
					resultSet = statement.executeQuery(str);
					resultSet.next();
					int value = resultSet.getInt(1);
					return value;
				
				
			}
			catch (SQLException e)
			{
				throw new SQLException("Error occurred in executing SQL query!! "+e);
			}
			catch(Exception e) {
				throw new Exception("Connection to Database Failed!! "+e);
			}
			finally {
				if(connection!=null)
				{
					connection.close();
				}
				if(statement!=null)
				{
					statement.close();
				}
				if(resultSet!=null)
				{
					resultSet.close();
				}
			}
		}
		
		public static int getTotalAccountCount() throws Exception
		{
			return getIntValue("SELECT COUNT(*) FROM CUSTOMER_DETAILS;");
		}
		
		public static JSONObject getTotalAccountCountObj() throws Exception
		{
			int value = getIntValue("SELECT COUNT(*) FROM CUSTOMER_DETAILS;");
			JSONObject obj = new JSONObject();
			obj.put("totalAccountCount", value);
			return obj;
		}

		public static String getAccountHolderName(String accountNo) throws Exception
		{
			Connection connection = null;	
			Statement statement = null;
			ResultSet resultSet = null;
			try {
				connection = DriverManager.getConnection(url, username, password);	
				
					statement = connection.createStatement();
					resultSet = statement.executeQuery("SELECT ACCOUNT_HOLDER FROM CUSTOMER_DETAILS WHERE ACCOUNT_NO="+accountNo+";");
					resultSet.next();
					String name = resultSet.getString(1);
					return name;
				
				
			}
			catch (SQLException e) {
				throw new SQLException("Error occurred in executing SQL query!! "+e);
			}
			catch (Exception e) {
				throw new Exception("Connection to Database Failed!! "+e);
			}
			finally {
				if(connection!=null)
				{
					connection.close();
				}
				if(statement!=null)
				{
					statement.close();
				}
				if(resultSet!=null)
				{
					resultSet.close();
				}
			}
		}
		
		public static JSONObject getAccountHolderNameObj(String accountNo) throws Exception
		{
			Connection connection = null;	
			Statement statement = null;
			ResultSet resultSet = null;
			try {
				connection = DriverManager.getConnection(url, username, password);	
				
					statement = connection.createStatement();
					resultSet = statement.executeQuery("SELECT ACCOUNT_HOLDER FROM CUSTOMER_DETAILS WHERE ACCOUNT_NO="+accountNo+";");
					resultSet.next();
					String name = resultSet.getString(1);
					
					JSONObject obj = new JSONObject();
					obj.put("accountHolderName", name);
					return obj;
				
				
			}
			catch (SQLException e) {
				throw new SQLException("Error occurred in executing SQL query!! "+e);
			}
			catch (Exception e) {
				throw new Exception("Connection to Database Failed!! "+e);
			}
			finally {
				if(connection!=null)
				{
					connection.close();
				}
				if(statement!=null)
				{
					statement.close();
				}
				if(resultSet!=null)
				{
					resultSet.close();
				}
			}
		}
		
		public static String getAdminName(String accountNo) throws Exception
		{
			Connection connection = null;	
			Statement statement = null;
			ResultSet resultSet = null;
			try {
				connection = DriverManager.getConnection(url, username, password);	
				
					statement = connection.createStatement();
					resultSet = statement.executeQuery("SELECT ACCOUNT_HOLDER FROM ADMIN_DETAILS WHERE ACCOUNT_NO='"+accountNo+"';");
					resultSet.next();
					String name = resultSet.getString(1);
					return name;
				
				
			}
			catch (SQLException e) {
				throw new SQLException("Error occurred in executing SQL query!! "+e);
			}
			catch (Exception e) {
				throw new Exception("Connection to Database Failed!! "+e);
			}
			finally {
				if(connection!=null)
				{
					connection.close();
				}
				if(statement!=null)
				{
					statement.close();
				}
				if(resultSet!=null)
				{
					resultSet.close();
				}
			}
		}
		
		public static JSONObject getAdminNameObj(String accountNo) throws Exception
		{
			Connection connection = null;	
			Statement statement = null;
			ResultSet resultSet = null;
			try {
				connection = DriverManager.getConnection(url, username, password);	
				
					statement = connection.createStatement();
					resultSet = statement.executeQuery("SELECT ACCOUNT_HOLDER FROM ADMIN_DETAILS WHERE ACCOUNT_NO='"+accountNo+"';");
					resultSet.next();
					String name = resultSet.getString(1);
					JSONObject obj = new JSONObject();
					obj.put("adminName", name);
					return obj;
				
				
			}
			catch (SQLException e) {
				throw new SQLException("Error occurred in executing SQL query!! "+e);
			}
			catch (Exception e) {
				throw new Exception("Connection to Database Failed!! "+e);
			}
			finally {
				if(connection!=null)
				{
					connection.close();
				}
				if(statement!=null)
				{
					statement.close();
				}
				if(resultSet!=null)
				{
					resultSet.close();
				}
			}
		}
		
		public static void updateValue(String str) throws Exception 
		{
			Connection connection = null;	
			Statement statement = null;
			try {	
				connection = DriverManager.getConnection(url, username, password);	
				
					statement = connection.createStatement();
					statement.executeUpdate(str);
				
				
			}
			catch(SQLException e) {
				throw new SQLException("Error occurred in executing SQL query!! "+e);
			}
			catch(Exception e) {
				throw new Exception("Connection to Database Failed!! "+e);
			}
			finally {
				if(connection!=null)
				{
					connection.close();
				}
				if(statement!=null)
				{
					statement.close();
				}
			}
		}
		
		public static ArrayList<ArrayList<String>> getAllAccountDetails() throws Exception
		{
			return getAllList("SELECT * FROM CUSTOMER_DETAILS ORDER BY ACCOUNT_NO;");
		}
		
		public static JSONArray getAllAccountDetailsObj() throws Exception
		{
			return getAllListObj("SELECT * FROM CUSTOMER_DETAILS ORDER BY ACCOUNT_NO;");
		}
		
		public static ArrayList<ArrayList<String>> getThisAccountDetail(String accountNo) throws Exception
		{
			return getAllList("SELECT * FROM CUSTOMER_DETAILS WHERE ACCOUNT_NO="+accountNo+";");
		}
		
		public static JSONObject getThisAccountDetailObj(String accountNo) throws Exception
		{
			JSONObject obj = new JSONObject();
			 obj.put("thisAccountDetail",getAllList("SELECT * FROM CUSTOMER_DETAILS WHERE ACCOUNT_NO="+accountNo+";"));
			 return obj;
		}
		
		public static ArrayList<ArrayList<String>> getAllList(String query) throws Exception
		{
			Connection connection = null;	
			Statement statement = null;
			ResultSet resultSet = null;
			try {
				connection = DriverManager.getConnection(url, username, password);	
				
					statement = connection.createStatement();	
					
					resultSet = statement.executeQuery(query);
					ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
					
					while(resultSet.next())
					{
						list.add(new ArrayList<String>(Arrays.asList(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4))));
					}
					return list;
				
				
			}
			catch(SQLException e)
			{
				throw new SQLException("Error occurred in executing SQL query!! "+e);
			}
			catch (Exception e) {
				throw new Exception("Connection to Database Failed!! "+e);
			}
			finally {
				if(connection!=null)
				{
					connection.close();
				}
				if(statement!=null)
				{
					statement.close();
				}
				if(resultSet!=null)
				{
					resultSet.close();
				}
			}
		}
		
		public static JSONArray getAllListObj(String query) throws Exception
		{
			Connection connection = null;	
			Statement statement = null;
			ResultSet resultSet = null;
			try {
				connection = DriverManager.getConnection(url, username, password);	
				
					statement = connection.createStatement();	
					
					resultSet = statement.executeQuery(query);
					JSONArray list = new JSONArray();
					
					while(resultSet.next())
					{
						JSONObject obj = new JSONObject();
						
						obj.put("accountNo",resultSet.getString(1));
						obj.put("accountName",resultSet.getString(2));
						obj.put("accountBalance",resultSet.getString(4));
						list.put(obj);
					}
					return list;
				
				
			}
			catch(SQLException e)
			{
				throw new SQLException("Error occurred in executing SQL query!! "+e);
			}
			catch (Exception e) {
				throw new Exception("Connection to Database Failed!! "+e);
			}
			finally {
				if(connection!=null)
				{
					connection.close();
				}
				if(statement!=null)
				{
					statement.close();
				}
				if(resultSet!=null)
				{
					resultSet.close();
				}
			}
		}
		
		public static ArrayList<ArrayList<String>> getTransactionDetails(String accountNo) throws Exception
		{
			Connection connection = null;	
			Statement statement = null;
			ResultSet resultSet = null;
			try {
				connection = DriverManager.getConnection(url, username, password);	
				
					statement = connection.createStatement();	
					
					resultSet = statement.executeQuery("SELECT * FROM TRANSACTION_DETAILS WHERE ACCOUNT_NO='"+accountNo+"' ORDER BY TRANSACTION_ID DESC LIMIT 10;");
					ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
					
					while(resultSet.next())
					{
						list.add(new ArrayList<String>(Arrays.asList(resultSet.getString(1),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6))));
					}
					
					return list;
				
				
			}
			catch(SQLException e) {
				throw new SQLException("Error occurred in executing SQL query!! "+e);
			}
			catch (Exception e) {
				throw new Exception("Connection to Database Failed!! "+e);
			}
			finally {
				if(connection!=null)
				{
					connection.close();
				}
				if(statement!=null)
				{
					statement.close();
				}
				if(resultSet!=null)
				{
					resultSet.close();
				}
			}
		}
		
		
		public static JSONArray getTransactionDetailsObj(String accountNo) throws Exception
		{
			JSONArray responseList = new JSONArray();
			Connection connection = null;	
			Statement statement = null;
			ResultSet resultSet = null;
			try {
				connection = DriverManager.getConnection(url, username, password);	
				
					statement = connection.createStatement();	
					
					resultSet = statement.executeQuery("SELECT * FROM TRANSACTION_DETAILS WHERE ACCOUNT_NO='"+accountNo+"' ORDER BY TRANSACTION_ID DESC LIMIT 10;");
					
					while(resultSet.next())
					{
						JSONObject responseObj = new JSONObject();
						responseObj.put("transactionId", resultSet.getString(1));
						responseObj.put("transactionRemarks", resultSet.getString(4));
						responseObj.put("transactionType", resultSet.getString(5));
						responseObj.put("transactionAmount", resultSet.getString(6));
						responseList.put(responseObj);
					}
					
					return responseList;
				
				
			}
			catch(SQLException e) {
				throw new SQLException("Error occurred in executing SQL query!! "+e);
			}
			catch (Exception e) {
				throw new Exception("Connection to Database Failed!! "+e);
			}
			finally {
				if(connection!=null)
				{
					connection.close();
				}
				if(statement!=null)
				{
					statement.close();
				}
				if(resultSet!=null)
				{
					resultSet.close();
				}
			}
		}

		public static void insertAccountDetailsValues(String name,String pin) throws Exception
		{
			Connection connection = null;
			Statement statement = null;
			String accPin = HashPassword.hashing(pin);
			try {
				connection = DriverManager.getConnection(url, username, password);	
					
					statement = connection.createStatement();
					String query = "INSERT INTO CUSTOMER_DETAILS (ACCOUNT_NO,ACCOUNT_HOLDER,PIN_NUMBER,BALANCE) "+
									"VALUES (nextval('CUSTOMER_ACC'),'"+name+"','"+accPin+"',5000.0);";
					statement.executeUpdate(query);
				
				
			}
			catch(SQLException e)
			{
				throw new SQLException("Error occurred in executing SQL query!! "+e);
			}
			catch (Exception e) {
				throw new Exception("Connection to Database Failed!! "+e);
			}
			finally {
				if(connection!=null)
				{
					connection.close();
				}
				if(statement!=null)
				{
					statement.close();
				}
			}
		}
		
		public static String getCustomerAccountNo(String name) throws Exception
		{
			Connection connection = null;	
			Statement statement = null;
			ResultSet resultSet = null;
			try {
				connection = DriverManager.getConnection(url, username, password);	
				
					statement = connection.createStatement();
					resultSet = statement.executeQuery("SELECT ACCOUNT_NO FROM CUSTOMER_DETAILS WHERE ACCOUNT_HOLDER='"+name+"';");
					resultSet.next();
					String accountNo = resultSet.getString(1);
					return accountNo;
			}
			catch (SQLException e) {
				throw new SQLException("Error occurred in executing SQL query!! "+e);
			}
			catch (Exception e) {
				throw new Exception("Connection to Database Failed!! "+e);
			}
			finally {
				if(connection!=null)
				{
					connection.close();
				}
				if(statement!=null)
				{
					statement.close();
				}
				if(resultSet!=null)
				{
					resultSet.close();
				}
			}
		}
		
		public static JSONObject getCustomerAccountNoObj(String name) throws Exception
		{
			Connection connection = null;	
			Statement statement = null;
			ResultSet resultSet = null;
			try {
				connection = DriverManager.getConnection(url, username, password);	
				
					statement = connection.createStatement();
					resultSet = statement.executeQuery("SELECT ACCOUNT_NO FROM CUSTOMER_DETAILS WHERE ACCOUNT_HOLDER='"+name+"';");
					resultSet.next();
					String accountNo = resultSet.getString(1);
					JSONObject obj = new JSONObject();
					obj.put("customerAccountNo", accountNo);
					return obj;
			}
			catch (SQLException e) {
				throw new SQLException("Error occurred in executing SQL query!! "+e);
			}
			catch (Exception e) {
				throw new Exception("Connection to Database Failed!! "+e);
			}
			finally {
				if(connection!=null)
				{
					connection.close();
				}
				if(statement!=null)
				{
					statement.close();
				}
				if(resultSet!=null)
				{
					resultSet.close();
				}
			}
		}
		
		public static void updateATMBalance(int amount,int id) throws Exception
		{
			
			updateATMValues("TOTAL_BALANCE",amount,id);
		}
		
		public static void updateAccountBalance(int balance,String accountNo) throws Exception
		{
			updateValue("UPDATE CUSTOMER_DETAILS SET BALANCE="+balance+" WHERE ACCOUNT_NO="+accountNo+";");
		}
		
		public static int getAccountBalance(String accountNo) throws Exception
		{
			
			return getIntValue("SELECT BALANCE FROM CUSTOMER_DETAILS WHERE ACCOUNT_NO="+accountNo+";");
		}
		
		public static JSONObject getAccountBalanceObj(String accountNo) throws Exception
		{
			JSONObject obj = new JSONObject();
			obj.put("accountBalance", getIntValue("SELECT BALANCE FROM CUSTOMER_DETAILS WHERE ACCOUNT_NO="+accountNo+";"));
			return obj;
		}

		public static void insertTransaction(String accountNo,String remarks,String type,int money) throws Exception
		{
			updateValue("INSERT INTO TRANSACTION_DETAILS (TRANSACTION_ID,ACCOUNT_NO,BRANCH_ID,TRANSACTION_REMARKS,TRANSACTION_TYPE,TRANSACTION_AMOUNT)"+
					"VALUES (DEFAULT,"+accountNo+",1,"+remarks+",'"+type+"',"+money+");");
		}

		public static void updateATMValues(String columnValue,int value,int id) throws Exception
		{
			updateValue("UPDATE ATM_MACHINE SET "+columnValue+" = "+value+" WHERE ID="+id+";");
		}
		
		public static int getATMValues(String columnValue,int id) throws Exception
		{		
			return getIntValue("SELECT "+columnValue+" FROM ATM_MACHINE WHERE ID="+id+";");
		}
		
		public static JSONObject getATMValuesObj(String columnValue,int id) throws Exception
		{		
			JSONObject obj = new JSONObject();
			obj.put("atmValues", getIntValue("SELECT "+columnValue+" FROM ATM_MACHINE WHERE ID="+id+";"));
			return obj;
		}
		
		public static int getATMBalance(int id) throws Exception
		{
			return getATMValues("TOTAL_BALANCE",id);
		}
		
		public static JSONObject getATMBalanceObj(int id) throws Exception
		{
			return getATMValuesObj("TOTAL_BALANCE",id);
		}
		
		public static boolean checkCustomerPin(String accountNo,String pin) throws Exception
		{
			Connection connection = null;
			Statement statement = null;
			ResultSet resultSet = null;
			try {
				connection = DriverManager.getConnection(url, username, password);	
				
					statement = connection.createStatement();	
					
					resultSet = statement.executeQuery("SELECT PIN_NUMBER FROM CUSTOMER_DETAILS WHERE ACCOUNT_NO="+accountNo+";");
					//ResultSet resultSet = PSQL.getResultSetValue("SELECT PIN_NUMBER FROM BANK_DET WHERE ACCOUNT_NO="+accountNo+";");
					resultSet.next();
					String value = resultSet.getString(1);
					String pinValue = HashPassword.hashing(pin);
					System.out.println(value+"---"+pinValue);
					if(pinValue.equals(value)) return true;
					return false;
				
				
			}
			catch(SQLException e) {
				throw new SQLException("Error occurred in executing SQL query!! "+e);
			}
			catch (Exception e) {
				throw new Exception("Connection to Database Failed!! "+e);
			}
			finally {
				if(connection!=null)
				{
					connection.close();
				}
				if(statement!=null)
				{
					statement.close();
				}
				if(resultSet!=null)
				{
					resultSet.close();
				}
			}
		}
		
		
		public static boolean checkAdminPin(String accountNo,String pin) throws Exception
		{
			Connection connection = null;
			Statement statement = null;
			ResultSet resultSet = null;
			try {
				connection = DriverManager.getConnection(url, username, password);	
				
					statement = connection.createStatement();	
					
					resultSet = statement.executeQuery("SELECT PIN_NUMBER FROM ADMIN_DETAILS WHERE ACCOUNT_NO='"+accountNo+"';");
					//ResultSet resultSet = PSQL.getResultSetValue("SELECT PIN_NUMBER FROM BANK_DET WHERE ACCOUNT_NO="+accountNo+";");
					resultSet.next();
					String value = resultSet.getString(1);
					String pinValue = HashPassword.hashing(pin);
					
					if(pinValue.equals(value)) return true;
					return false;
			}
			catch(SQLException e) {
				throw new SQLException("Error occurred in executing SQL query!! "+e);
			}
			catch (Exception e) {
				throw new Exception("Connection to Database Failed!! "+e);
			}
			finally {
				if(connection!=null)
				{
					connection.close();
				}
				if(statement!=null)
				{
					statement.close();
				}
				if(resultSet!=null)
				{
					resultSet.close();
				}
			}
		}
		
		
		public static boolean checkCustomerAccountNo(String accountNo) throws Exception
		{
			Connection connection = null;
			Statement statement = null;
			ResultSet resultSet = null;
			try {
				connection = DriverManager.getConnection(url, username, password);	
				
					statement = connection.createStatement();	
					
					resultSet = statement.executeQuery("SELECT ACCOUNT_NO FROM CUSTOMER_DETAILS;");
					//resultSet = PSQL.getResultSetValue("SELECT ACCOUNT_NO FROM BANK_DET;");
					while(resultSet.next())
					{
						String value = resultSet.getString(1);
						
						if(value.equals(accountNo))
						{
							return true;
						}
					}
					return false;
				
				
				
			}
			catch(SQLException e) {
				throw new SQLException("Error occurred in executing SQL query!! "+e);
			}
			catch (Exception e) {
				throw new Exception("Connection to Database Failed!! "+e);
			}
			finally {
				if(connection!=null)
				{
					connection.close();
				}
				if(statement!=null)
				{
					statement.close();
				}
				if(resultSet!=null)
				{
					resultSet.close();
				}
			}
		}
		
		public static boolean checkAdminAccountNo(String accountNo) throws Exception
		{
			Connection connection = null;
			Statement statement = null;
			ResultSet resultSet = null;
			try {
				connection = DriverManager.getConnection(url, username, password);	
				
					statement = connection.createStatement();	
					
					resultSet = statement.executeQuery("SELECT ACCOUNT_NO FROM ADMIN_DETAILS;");
					//resultSet = PSQL.getResultSetValue("SELECT ACCOUNT_NO FROM BANK_DET;");
					while(resultSet.next())
					{
						String value = resultSet.getString(1);
						
						if(value.equals(accountNo))
						{
							return true;
						}
					}
					return false;
				
			}
			catch(SQLException e) {
				throw new SQLException("Error occurred in executing SQL query!! "+e);
			}
			catch (Exception e) {
				throw new Exception("Connection to Database Failed!! "+e);
			}
			finally {
				if(connection!=null)
				{
					connection.close();
				}
				if(statement!=null)
				{
					statement.close();
				}
				if(resultSet!=null)
				{
					resultSet.close();
				}
			}
		}
		
		public static boolean getAnyAccountHolderName(String accountName) throws Exception
		{
			Connection connection = null;	
			Statement statement = null;
			ResultSet resultSet = null;
			try {
				connection = DriverManager.getConnection(url, username, password);	
				
					statement = connection.createStatement();
					resultSet = statement.executeQuery("SELECT COUNT(*) FROM CUSTOMER_DETAILS WHERE ACCOUNT_HOLDER = '"+accountName+"';");
					resultSet.next();
					int count = resultSet.getInt(1);
					
					if(count!=0)
					{
						return true;
					}
					return false;
				
				
			}
			catch (SQLException e) {
				throw new SQLException("Error occurred in executing SQL query!! "+e);
			}
			catch (Exception e) {
				throw new Exception("Connection to Database Failed!! "+e);
			}
			finally {
				if(connection!=null)
				{
					connection.close();
				}
				if(statement!=null)
				{
					statement.close();
				}
				if(resultSet!=null)
				{
					resultSet.close();
				}
			}
		}
		
		
		public static void createTable() throws Exception
		{
			Connection connection = null;
			Statement statement = null;
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(url, username, password);	
				try {
					statement = connection.createStatement();
					
					String query = "CREATE TABLE CUSTOMER_DETAILS "+
							"(ACCOUNT_NO INT PRIMARY KEY,"+
							"ACCOUNT_HOLDER VARCHAR(20) NOT NULL,"+
							"PIN_NUMBER VARCHAR(32) NOT NULL,"+
							"BALANCE INT NOT NULL);";
					statement.executeUpdate(query);
					
					query = "CREATE TABLE ATM_MACHINE ("+
							"ID SERIAL PRIMARY KEY,"+
							"BRANCH_NAME VARCHAR(20) NOT NULL,"+
							"HUNDRED INT NOT NULL,"+
							"FIVE_HUNDRED INT NOT NULL,"+
							"THOUSAND INT NOT NULL,"+
							"TOTAL_BALANCE REAL NOT NULL);";
					statement.executeUpdate(query);
					
					query = "CREATE TABLE TRANSACTION_DETAILS ("+
									"TRANSACTION_ID SERIAL PRIMARY KEY,"+
									"ACCOUNT_NO INT,"+
									"BRANCH_ID INT,"+
									"TRANSACTION_REMARKS VARCHAR(100) NOT NULL,"+
									"TRANSACTION_TYPE VARCHAR(100) NOT NULL,"+
									"TRANSACTION_AMOUNT REAL NOT NULL,"+
									"FOREIGN KEY(ACCOUNT_NO) " + 
										"REFERENCES CUSTOMER_DETAILS(ACCOUNT_NO)"
										+ "ON DELETE CASCADE,"+
									"FOREIGN KEY(BRANCH_ID)"+ 
										"REFERENCES ATM_MACHINE(ID));";
					statement.executeUpdate(query);
					
					query = "CREATE TABLE ADMIN_DETAILS "+
							"(ADMIN_ID INT PRIMARY KEY,"+
							"ACCOUNT_NO VARCHAR(8) NOT NULL,"+
							"ACCOUNT_HOLDER VARCHAR(20) NOT NULL,"+
							"PIN_NUMBER VARCHAR(32) NOT NULL);";
					statement.executeUpdate(query);
					
					query = "CREATE SEQUENCE CUSTOMER_ACC\n" + 
							"START 101\n" + 
							"INCREMENT 1\n" +  
							"MINVALUE 101\n" +
							"OWNED BY CUSTOMER_DETAILS.ACCOUNT_NO;";
					statement.executeUpdate(query);
					
				query = "CREATE SEQUENCE ADMIN_ACC\n" + 
							"START 1\n" + 
							"INCREMENT 1\n" + 
							"MINVALUE 1\n" +
							"OWNED BY ADMIN_DETAILS.ADMIN_ID;";
					statement.executeUpdate(query);
					
				
			}
			catch(SQLException e) {
				throw new SQLException("Error occurred in SQL query!"+e);
			}
			catch (Exception e) {
				throw new Exception("Connection to database Failed!!"+e);
			} 
			finally {
				if(connection!=null)
				{
					connection.close();
				}
				if(statement!=null)
				{
					statement.close();
				}
			}
		}
		
		public static void insertTable() throws Exception
		{
			Connection connection = null;
			Statement statement = null;
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(url, username, password);	
				try {
					statement = connection.createStatement();
					
					String adminPin_1 = HashPassword.hashing("12345");
					String adminPin_2 = HashPassword.hashing("23456");
					String adminPin_3 = HashPassword.hashing("34567");
					String customerPin_1 = HashPassword.hashing("10101");
					String customerPin_2 = HashPassword.hashing("10102");
					String customerPin_3 = HashPassword.hashing("10103");
					String customerPin_4 = HashPassword.hashing("10104");
					String customerPin_5 = HashPassword.hashing("10105");
					
					
					String query = "INSERT INTO CUSTOMER_DETAILS "+
									"VALUES "+
									"(nextval('CUSTOMER_ACC'),'Harish','"+customerPin_1+"',5000),"
									+ "(nextval('CUSTOMER_ACC'),'Suresh','"+customerPin_2+"',5000),"
									+ "(nextval('CUSTOMER_ACC'),'Kumar','"+customerPin_3+"',5000),"
									+ "(nextval('CUSTOMER_ACC'),'John','"+customerPin_4+"',5000),"
									+ "(nextval('CUSTOMER_ACC'),'Yusuf','"+customerPin_5+"',5000);";
					statement.executeUpdate(query);
					
				
					query = "INSERT INTO ADMIN_DETAILS "+
							"VALUES "+
							"(nextval('ADMIN_ACC'),CONCAT('ad',currval('ADMIN_ACC')),'AKASH','"+adminPin_1+"'),"
							+ "(nextval('ADMIN_ACC'),CONCAT('ad',currval('ADMIN_ACC')),'RAJ','"+adminPin_2+"'),"
							+ "(nextval('ADMIN_ACC'),CONCAT('ad',currval('ADMIN_ACC')),'NAVEEN','"+adminPin_3+"')";
					statement.executeUpdate(query);
					
					query = "INSERT INTO ATM_MACHINE (ID,BRANCH_NAME,HUNDRED,FIVE_HUNDRED,THOUSAND,TOTAL_BALANCE) "+
							"VALUES "+
							"(1,'ZOHO',0,0,0,0);";
					statement.executeUpdate(query);
				
			}
			catch(SQLException e) {
				throw new SQLException("Error occurred in SQL query!"+e);
			}
			catch (Exception e) {
				throw new Exception("Connection to database Failed!!"+e);
			} 
			finally {
				if(connection!=null)
				{
					connection.close();
				}
				if(statement!=null)
				{
					statement.close();
				}
			}
		}
		
		public static void main(String args[]) throws Exception
		{
			createTable();
			insertTable();
		}

}

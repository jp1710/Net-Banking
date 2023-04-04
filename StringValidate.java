//$Id$
package bankingApplication;

public class StringValidate {
	
	public static boolean alphaNum(char ch)
	{
		if(ch>=65 && ch<=90 || ch>=97 && ch<=122 || ch-'0'>=0 && ch-'0'<=9)
		{
			return true;
		}
		return false;
	}
	
	public static boolean alpha(char ch)
	{
		if(ch>=65 && ch<=90 || ch>=97 && ch<=122)
		{
			return true;
		}
		return false;
	}
	
	public static boolean checkSymbols(String str)
	{
		for(int i=0;i<str.length();i++)
		{
			if(!alphaNum(str.charAt(i)))
			{
				return false;
			}
		}
		return true;
	}
	
	public static boolean checkAlphabets(String str)
	{
		return ((str != null) && (!str.equals(""))
              && (str.matches("^[a-zA-Z]*$")));
	}
	
}


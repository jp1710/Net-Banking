//$Id$
package bankingApplication;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class EncodeDecodeData {

	public static String encode(String str) throws UnsupportedEncodingException
	{
		byte[] encodedBytes;
		try {
			encodedBytes = Base64.getEncoder().encode(str.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new UnsupportedEncodingException("Error occurred during encoding String! "+ e); 
		}
		return new String(encodedBytes);
	}
	
	public static String decode(String str) throws UnsupportedEncodingException
	{
		byte[] decodedBytes = Base64.getDecoder().decode(str.getBytes("UTF-8"));
		return new String(decodedBytes);
	}
}

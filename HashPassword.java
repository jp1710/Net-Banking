//$Id$
package bankingApplication;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.xml.bind.DatatypeConverter;

public class HashPassword {

	public static byte[] RandomSalt()
    {
        byte[] salt = new byte[16];
        SecureRandom random
            = new SecureRandom();
        random.nextBytes(salt);
        return salt;
    }
	
	public static String hashing(String input) throws IOException, NoSuchAlgorithmException
	{
		final String ALGORITHM = "MD5";
		ByteArrayOutputStream byte_Stream = new ByteArrayOutputStream();
		
		byte[] salt = RandomSalt();

	    //byte_Stream.write(salt);
	    byte_Stream.write(input.getBytes());
	    byte[] valueToHash = byte_Stream.toByteArray();
	    MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
	    String value = DatatypeConverter.printHexBinary(messageDigest.digest(valueToHash));
	    return value;
	}
	
//	public static void main(String[] args) throws NoSuchAlgorithmException, IOException
//	{
//		String hash = hashing("Hi");
//		System.out.println(hash);
//	}
	
}

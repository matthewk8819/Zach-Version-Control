
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Blob {

	//SHA1 String
	private String SHA1;
	//zipped file contents
	private String zipped;
	
	//constructor
	public Blob(String fileName) throws Exception {
		//creates zipped string of contents
		zipped = (String)compress(getFileString(fileName));
		
		//creates SHA1 string of zipped
		SHA1 = encryptThisString(zipped);
		
		//creates new file called SHA1
		File file = new File(SHA1);	
		
		//puts file in objects folder
		PrintWriter pw = new PrintWriter("objects/"+ file);
		
		//copies contents into file
		pw.append(zipped);
		pw.close();
	}
	
	//gets contents of file
	private  String getFileString(String fileName) throws IOException{
		Path filePath = Path.of(fileName);
		
		return Files.readString(filePath);
	}
	
	//SHA1 method
	private String encryptThisString(String input)
    {
        try {
            // getInstance() method is called with algorithm SHA-1
            MessageDigest md = MessageDigest.getInstance("SHA-1");
 
            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest(input.getBytes());
 
            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);
 
            // Convert message digest into hex value
            String hashtext = no.toString(16);
 
            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
 
            // return the HashText
            return hashtext;
        }
 
        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
	
	//zipping method
	public static byte[] compress(final String str) throws IOException {
	    if ((str == null) || (str.length() == 0)) {
	      return null;
	    }
	    ByteArrayOutputStream obj = new ByteArrayOutputStream();
	    GZIPOutputStream gzip = new GZIPOutputStream(obj);
	    gzip.write(str.getBytes("UTF-8"));
	    gzip.flush();
	    gzip.close();
	    return obj.toByteArray();
	  }
	
}

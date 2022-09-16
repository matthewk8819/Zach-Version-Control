import static org.junit.Assert.assertTrue;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class JUnitTreeTester {
	
	private static Tree bush;
	
	private static ArrayList<String> arr;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		arr = new ArrayList<String>();
		arr.add("blob: agfghbavbvovjvbofvo1");
		arr.add("blob: aghgbb3u123n55uu1q11");
		arr.add("tree: kamehamehahahahahah1");
		
		bush = new Tree(arr);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void checkFileName() {
		String sha = bush.getSha1();
		String test = "";
		for (String str: arr) {
			test+=str;
		}
		test = encryptThisString(test);
		assertTrue(test.equals(sha));
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
	
	@Test
	void checkFileContents() {
		Scanner scanner = new Scanner("objects/"+bush.getSha1());
		int count = 0;
		while (scanner.hasNextLine()) {
			count++;
			scanner.nextLine();
		}
		scanner.close();
		assertTrue(count==arr.size());
	}

}

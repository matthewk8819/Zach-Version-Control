import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;

public class Commit {

	private Commit parent = null;
	private Commit next = null;
	private String pTree = null;
	private String summary, author, date;

	// constructor
	public Commit(String fileName, String sum, String auth, Commit p) {
		parent = p;
		author = auth;
		summary = sum;
		date = getDate();
		pTree = "objects/" + fileName;
	}

	// writing method
	public void writeFile() throws IOException {
		String str = "";
		ArrayList<String> arr = getContents();
		for (String s : arr) {
			if (!s.equals(null)) {
				str += s;
			}
		}
		String sha1 = encryptThisString(str);
		File file = new File(sha1);

		FileWriter fw = new FileWriter("objects/" + file);
		BufferedWriter bw = new BufferedWriter(fw);
		for (String s : arr) {
			if (!s.equals(null)) {
				bw.write(s);
			}
			bw.newLine();
		}
		bw.close();
		fw.close();
	}

	// gets contents of file
	private ArrayList<String> getContents() {
		ArrayList<String> arr = new ArrayList<String>();
		arr.add(pTree);
		if (parent != null) {
			arr.add(parent.getLocation());
		} else {
			arr.add(null);
		}
		if (next != null) {
			arr.add(parent.getLocation());
		} else {
			arr.add(null);
		}
		arr.add(author);
		arr.add(date);
		arr.add(summary);
		return arr;
	}

	// gets location of commit
	public String getLocation() {
		return pTree;
	}

	// date method
	private String getDate() {
		Date date = new Date();
		return date.toString();
	}

	// SHA1 method
	private String encryptThisString(String input) {
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

}

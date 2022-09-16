import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.*;

public class Tree {
	private String output;
	private String sha1;
	
	public Tree (ArrayList<String> pairs) {
		output="";
		for (String pair: pairs) {
			output+=pair+"\n";
		}
		sha1 = generateSha1(output);
		Path p = Paths.get("objects"+File.separator+sha1);
        try {
            Files.writeString(p, output, StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	
	private static String generateSha1(String text) {
		String sha1 = "";
		
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
	        digest.reset();
	        digest.update(text.getBytes("utf8"));
	        sha1 = String.format("%040x", new BigInteger(1, digest.digest()));
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return sha1;
	}
	
	public String getSha1(){
		return sha1;
	}
}

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Index {

	//index file
	private File index;
	
	//constructor
	public Index() {
		
	}
	
	//initiating method
	public void initiate() throws IOException {
		//creates new objects folder
		File objects = new File("objects");		
		objects.mkdir();
		
		//initializes index file
		index = new File("index");
		FileWriter fw = new FileWriter(index);
		fw.close();
	}
	
	//creates and adds new blob to index and objects folder
	public void add(String fileName) throws Exception {
		//creates new blob in objects folder
		Blob b = new Blob(fileName);
		
		//adds blob to index
		PrintWriter pw = new PrintWriter(index);
		pw.append(fileName + " : " + b.getSHA1());
		pw.close();
	}
	
	//removes blob from index and objects folder
	public void remove(String fileName) {
		
	}
}

package Testers;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Git.Blob;
import Git.Index;

class indexTester {
	
	/*
	 * We just need to initialize an index with a blob
	 */
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		//make txt1 and blob
		File txtFile = new File("newFile");
		FileWriter writer = new FileWriter(txtFile);
		writer.append("This is some content");
		writer.close();
		
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		
	}

	@Test
	void indexTests() throws IOException {
		Index index = new Index();
		
	}

}

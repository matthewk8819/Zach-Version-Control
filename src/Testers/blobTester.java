package Testers;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Git.Blob;

class blobTester {

	/*
	 * For blob tester: need a txt file
	 * for testing: add that file to objects as a blob
	 * test if it works by checking file exists
	 * remove the file after in teardown
	 * 
	 * afterthought: in blob, maybe should make objects folder and delete manually
	 */
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		//make txt1
		File txtFile = new File("newFile");
		FileWriter writer = new FileWriter(txtFile);
		writer.append("This is some content");
		writer.close();
		
		//make txt2
		File txtFile2 = new File("newFile2");
		FileWriter writer2 = new FileWriter(txtFile2);
		writer2.append("This is some content2");
		writer2.close();
		
		File objects = new File("objects");
		objects.mkdir();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		File f = new File("newFile");
		f.delete();
		File f2 = new File("newFile2");
		f2.delete();
		
		
		File objects = new File("objects");
		objects.delete();
		
		
	}

	@Test
	void blobTest() throws Exception {
		Blob blob = new Blob("newFile");
		File f = new File("objects/f3705a38abd5d2bd1f4fecda606d216216c536b1");
		assertTrue(f.exists());
		f.delete();
		
		Blob blob2 = new Blob("newFile2");
		File f2 = new File("objects/cf869ab0e737e40727714ba7d55998a660fb439e");
		assertTrue(f2.exists());
		f2.delete();
	}

}

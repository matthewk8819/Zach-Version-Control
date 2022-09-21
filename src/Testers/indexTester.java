package Testers;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Git.Blob;
import Git.Index;

class indexTester {
	
	/*
	 * We just need to initialize an index with a blob
	 * THING TO FIX FOR INDEX: 
	 * 1) I don't think you need blob as parameter for add/remove, just do it in the meethod
	 * 2) add the making of the objects folder (pretty sure this is still what we want could be wrong tho)
	 * 3) Remove needs some help - says it runs but doesn't delete it actualy 
	 */
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		//make txt1 and blob
		File txtFile = new File("newFile");
		FileWriter writer = new FileWriter(txtFile);
		writer.append("This is some content");
		writer.close();
		
		File objects = new File("objects");
		objects.mkdir();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		//delete the objects, index, and newfile
		File f = new File("index");
		f.delete();
		f = new File("objects/f3705a38abd5d2bd1f4fecda606d216216c536b1");
		f.delete();
		f = new File("objects");
		f.delete();
		f = new File("newFile");
		f.delete();
	}

	@Test
	void indexTests() throws Exception {
		Index index = new Index();
		File f = new File("index");
		Scanner indexScanner = new Scanner(f);
		Blob b = new Blob("newFile");
		index.add("newFile", b);
		assertTrue((new File("objects/f3705a38abd5d2bd1f4fecda606d216216c536b1")).exists());
		assertTrue(indexScanner.nextLine().equals("newFile : f3705a38abd5d2bd1f4fecda606d216216c536b1"));
		index.remove("newfile", b);
		assertFalse((new File("objects/f3705a38abd5d2bd1f4fecda606d216216c536b1")).exists());
	}

}

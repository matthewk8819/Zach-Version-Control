package Testers;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Git.Blob;
import Git.Git;

class GitTester {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Path p = Paths.get("testText1.txt");
        try {
            Files.writeString(p, "sample text", StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Path p2 = Paths.get("testText2.txt");
        try {
            Files.writeString(p2, "example", StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Path p3 = Paths.get("testText3.txt");
        try {
            Files.writeString(p3, "the one piece is real", StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	
	private static String readFile(String fileName) throws IOException {
		Path filePath = Path.of(fileName);
		return Files.readString(filePath);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		File testText1 = new File("testText1.txt");
		testText1.delete();
		File testText2 = new File("testText2.txt");
		testText2.delete();
		File testText3 = new File("testText3.txt");
		testText3.delete();
	}
	
	static void testInit() throws IOException {
		File objects = new File("objects");
		objects.delete();
		File index = new File("index");
		index.delete();
		Git gitty = new Git();
		gitty.initiate();
		
		objects = new File("objects");
		assertTrue(objects.exists());
		index = new File("index");
		assertTrue(index.exists());
	}
	
	static void testBlob() throws Exception {
		Blob b = new Blob("testText1.txt");
		File blobFile = new File("objects"+File.separator+"86f441fa0e99f2a36784217a323cea1f5fc0b7f6");
		assertTrue(blobFile.exists());
		assertTrue(readFile("objects"+File.separator+"86f441fa0e99f2a36784217a323cea1f5fc0b7f6").equals("sample text"));
		
	}
	
	static void testAdd() throws Exception {
		Path p = Paths.get("index");
        try {
            Files.writeString(p, "", StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		Git gitty = new Git();
		gitty.initiate();
		
		gitty.add("testText1.txt");
		File blobFile = new File("objects"+File.separator+"86f441fa0e99f2a36784217a323cea1f5fc0b7f6");
		assertTrue(blobFile.exists());

		assertTrue(readFile("index").equals("testText1.txt : 86f441fa0e99f2a36784217a323cea1f5fc0b7f6\n"));
	}
	
	static void testRemove() throws Exception {
		Git gitty = new Git();
		gitty.initiate();
		
		gitty.add("testText1.txt");
		gitty.remove("testText1.txt");
		File removedFile = new File("objects"+File.separator+"86f441fa0e99f2a36784217a323cea1f5fc0b7f6");
		assertFalse(removedFile.exists());
		
		assertTrue(readFile("index").equals(""));
	}
	
	static void testAddAndRemove() throws Exception {
		Git gitty = new Git();
		gitty.initiate();
		gitty.add("testText1.txt");
		gitty.add("testText2.txt");
		gitty.add("testText3.txt");
		gitty.remove("testText2.txt");
		
		File removedFile = new File("objects"+File.separator+"c3499c2729730a7f807efb8676a92dcb6f8a3f8f");
		assertFalse(removedFile.exists());
		File shouldBeThere1 = new File("objects"+File.separator+"86f441fa0e99f2a36784217a323cea1f5fc0b7f6");
		assertTrue(shouldBeThere1.exists());
		File shouldBeThere2 = new File("objects"+File.separator+"7bcab67e89683d681a243e64f9a5d15633960aa5");
		
		assertTrue(readFile("index").equals("testText1.txt : 86f441fa0e99f2a36784217a323cea1f5fc0b7f6\ntestText2.txt : 7bcab67e89683d681a243e64f9a5d15633960aa5\n"));
		
		
		
	}

	@Test
	void test() throws Exception {
		testBlob();
		testInit();
		testAdd();
		testRemove();
		testAddAndRemove();
	}

}

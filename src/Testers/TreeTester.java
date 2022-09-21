package Testers;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Git.Tree;

class treeTester {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		File obj = new File("objects");
		obj.mkdir();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		File objFile = new File("objects/93f59490a5ebdfcc20bb3121f86df8e8e3b05c08");
		objFile.delete();
		File obj = new File("objects");
		obj.delete();
	}

	@Test
	void treeTest() {
		ArrayList<String> goku = new ArrayList<String>();
		goku.add("tree : agfghbavbvovjvbofvo1");
		goku.add("blob : aghgbb3u123n55uu1q11");
		goku.add("blob : kamehamehahahahahah1");
		Tree fortniteAmongUsBalls = new Tree(goku);	
		
		File objFile = new File("objects/93f59490a5ebdfcc20bb3121f86df8e8e3b05c08");
		assertTrue(objFile.exists());
	}

}

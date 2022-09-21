package Testers;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Git.Commit;

class commitTester {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		File obj = new File("objects");
		obj.mkdir();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void commitTest() throws IOException {
		Commit c = new Commit("fileName","summary","me!",null);
		//just doesn't work rip
	}

}

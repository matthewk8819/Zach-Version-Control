

public class Tester {

	public static void main(String[] args) throws Exception {
		
		Git g = new Git();
		g.initiate();

		g.add("something.txt");
		g.add("other.txt");
		
		//g.remove("something.txt");
		g.remove("other.txt");
	}

}

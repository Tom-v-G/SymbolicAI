import java.util.*;

public class Main {
	public static void main(String[] args) {
		String file = "data/board.txt";
		Vector<String> Test = new Vector<String>();
		//Test.add("block");
		Test.add("test2");
		System.out.println(Test.indexOf("block"));
		Game g=new Game();
		g.test();
//		State hoi = new State();
//		hoi.read(file);
////      	
//		System.out.println(hoi.toString());
//		State test =  hoi.Copy();
////		test.changeBoard(1, 1, 'G');
////		System.out.println(test.toString());
////		System.out.println(hoi.toString());
//		
//		System.out.println(Arrays.toString(hoi.getScore()));
//		hoi.changeScore(0, 5);
//		System.out.println(Arrays.toString(hoi.getScore()));
//		System.out.println(Arrays.toString(test.getScore()));


//		System.out.println(hoi.getTurn());
//		hoi.execute("up");
//		System.out.println(hoi.getTurn());
//		System.out.println(test.getTurn());		
//		hoi.printCoordinates();
//		
//		System.out.println("\nTest 1\n");
//		
//		file = "data/test_board_1";
//		State test_1 = new State();
//		test_1.read(file);
//		System.out.println(test_1.toString());
//		test_1.printCoordinates();
//		System.out.println(test_1.legalMoves());
//		
//		System.out.println("\nTest 2\n");
//		
//		file = "data/test_board_2";
//		State test_2 = new State();
//		test_2.read(file);
//		System.out.println(test_2.toString());
//		test_2.printCoordinates();
//		System.out.println(test_2.legalMoves(1));
//		
//		System.out.println("\nTest copy function \n");
//		State test_3 = test_2.copy();
//		System.out.println(test_3.toString());
//		test_3 = test_1.copy();
//		System.out.println(test_3.toString());
//		
//		test_3.execute("up");
//		test_3.execute("block");
//		test_3.execute("eat");
//		test_3.execute("down");
//		test_3.execute("down");
//		System.out.println(test_3.toString());
		
	}
}
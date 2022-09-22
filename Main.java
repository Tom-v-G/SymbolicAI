public class Main {
	public static void main(String[] args) {
		String file = "data/board.txt";
		//Game g=new Game();
		//g.test();
		State hoi = new State();
		hoi.read(file);
		
		System.out.println(hoi.toString());
		hoi.printCoordinates();
	}
}

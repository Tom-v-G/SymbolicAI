import java.util.Vector;

public class Game {
	State b;
	public Game() {
		b=new State();
		b.read("data/board.txt");
	}
	public void test() {
		
		//System.out.println(minimax(b, b.turn, 11, 0));
		
		while (!b.isLeaf()){
			System.out.println(b.toString());
			System.out.println("Legal moves for agent with turn:"+b.legalMoves());
			b.execute(b.legalMoves().get((int)(Math.random()*b.legalMoves().size())));
		}
	}
	
	public State minimax(State s, int forAgent, int maxDepth, int depth) {
		double INF = 99999;
		
		if ( depth == maxDepth || s.isLeaf() ) {
			return s;
		}
		else if ( ( s.turn % 2 ) == forAgent ) { //max
			double g = -INF;
			for (String m: s.legalMoves(forAgent)) {
				State copyS = s.copy();
				g = Math.max(g, minimax(copyS, forAgent, maxDepth, depth).value(forAgent));
				
			}
		}	
//			Vector<double> values = new Vector<double>();
//			for ( String m: s.legalMoves(forAgent) ) {
//				State copyS = s.copy();
//				copyS.execute(m);
//				if (copyS.isLeaf()) {
//					values copyS.value(forAgent);
//				}
//				else {
//					forAgent = (forAgent + 1) % 2;
//					depth ++;
//					copyS = minimax(copyS, forAgent, maxDepth, depth);
//					
//				}
//				
//				
//			}
//		}
		
	}
	
}

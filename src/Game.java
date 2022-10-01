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
		
		if ( depth == maxDepth || s.isLeaf()) {
			return s;
		}

		else if ( ( (depth + forAgent) % 2 ) == forAgent ) { //max
			double g = -INF;
			State bestState = new State();
			String bestMove = "";
			for (String m: s.legalMoves(forAgent)) {
				State copyS = s.copy();
				copyS.execute(m);
				State minimaxS = minimax(copyS, forAgent, maxDepth, depth + 1); 
				if(minimaxS.value(forAgent) > g){
					g = minimaxS.value(forAgent);
					bestState = minimaxS;
					bestMove = m;
				}
			}
			if(depth == 0){
				s.execute(bestMove);
				return s;
			}
			return bestState;			
		}
	
		else if ( ((depth + forAgent) % 2) != forAgent) { //min
			double g = INF;
			int newAgent = (forAgent+1) % 2;
			State worstState = new State();
			for (String m: s.legalMoves(newAgent)) {
				State copyS = s.copy();
				copyS.execute(m);
				State minimaxS = minimax(copyS, newAgent, maxDepth, depth + 1); 
				if(minimaxS.value(forAgent) < g){
					g = minimaxS.value(forAgent);
					worstState = minimaxS;
				}
			}

			return worstState;

		}

		else {
			System.out.println("Oepsiewoepsie");
			return s;
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

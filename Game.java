import java.util.Vector;

public class Game {
	State b;
	public Game() {
		b=new State();
		b.read("data/board.txt");
	}
	public void test() {
		
		//System.out.println(minimax(b, b.getTurn(), 11, 0));
		
		//while (!b.isLeaf()){
		//	System.out.println(b.toString());
		//	System.out.println("Legal moves for agent with turn:"+b.legalMoves());
		//	b.execute(b.legalMoves().get((int)(Math.random()*b.legalMoves().size())));
		//}
		
		while(!b.isLeaf()){
			System.out.println(b.toString());
			System.out.println("Legal moves for agent " + (b.getTurn() % 2) +": " + b.legalMoves());
			b = minimax(b, (b.getTurn() % 2), 11, 0);
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
				State copyS = s.Copy();
				copyS.execute(m);
				State minimaxS = minimax(copyS, forAgent, maxDepth, depth + 1); 
				if(minimaxS.value(forAgent) > g){ //choose the best move
					g = minimaxS.value(forAgent);
					bestState = minimaxS.Copy();
					bestMove = m;
				}
				else if (minimaxS.value(forAgent) == g){ //if values are the same, ddo a random move
					if(Math.random() > 0.5){
						g = minimaxS.value(forAgent);
						bestState = minimaxS.Copy();
						bestMove = m;
					}
				}
			}
			if(depth == 0){
				s.execute(bestMove);
				System.out.println("The best move is: " + bestMove);
				//System.out.println(s.toString());
				return s;
			}
			return bestState;			
		}
	
		else if ( ((depth + forAgent) % 2) != forAgent) { //min
			double g = INF;
			int newAgent = (forAgent+1) % 2;
			State worstState = new State();

			for (String m: s.legalMoves(newAgent)) {
				State copyS = s.Copy();
				copyS.execute(m);
				State minimaxS = minimax(copyS, forAgent, maxDepth, depth + 1); 
				if(minimaxS.value(forAgent) < g){
					g = minimaxS.value(forAgent);
					worstState = minimaxS.Copy();
				}
			}

			return worstState;

		}

		else {
			System.out.println("Oepsiewoepsie");
			return s;
		}
	}
	
}

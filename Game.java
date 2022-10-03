import java.util.Vector;

public class Game {
	State b;
	public Game() {
		b=new State();
		b.read("data/test_board_1.txt");
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
			//b = minimax(b, (b.getTurn() % 2), 13, 0);
			b = alfabeta(b, (b.getTurn() % 2), 20, 0, -1, 1);
		}

	}
	
	public State minimax(State s, int forAgent, int maxDepth, int depth) {
		double INF = 99999;		
		if ( depth == maxDepth || s.isLeaf()) {
			return s;
		}

		else if ( ( (depth + forAgent) % 2 ) == forAgent ) { //max
			double g = (-1 * INF);
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
//				else if (minimaxS.value(forAgent) == g){ //if values are the same, ddo a random move
//					if(Math.random() > 0.5){
//						g = minimaxS.value(forAgent);
//						bestState = minimaxS.Copy();
//						bestMove = m;
//					}
//				}
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
		
	public State alfabeta(State s, int forAgent, int maxDepth, int depth, double alfa, double beta) {
		double INF = 99999;		
		if ( depth == maxDepth || s.isLeaf()) {
			return s;
		}

		else if ( ( (depth + forAgent) % 2 ) == forAgent ) { //max
			double g = (-1 * INF);
			State bestState = new State();
			String bestMove = "";
			
			for (String m: s.legalMoves(forAgent)) {
				State copyS = s.Copy();
				copyS.execute(m);
				State alfabetaS = alfabeta(copyS, forAgent, maxDepth, depth + 1, alfa, beta); 
				if(alfabetaS.value(forAgent) > g){ //choose the best move
					g = alfabetaS.value(forAgent);
					bestState = alfabetaS.Copy();
					bestMove = m;
				}
				alfa = Math.max(alfa, g);
				if(g >= beta){
					break;
				}
//				else if (minimaxS.value(forAgent) == g){ //if values are the same, ddo a random move
//					if(Math.random() > 0.5){
//						g = minimaxS.value(forAgent);
//						bestState = minimaxS.Copy();
//						bestMove = m;
//					}
//				}
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
				State alfabetaS = alfabeta(copyS, forAgent, maxDepth, depth + 1, alfa, beta); 
				if(alfabetaS.value(forAgent) < g){
					g = alfabetaS.value(forAgent);
					worstState = alfabetaS.Copy();
				}

				beta = Math.min(beta, g); //update beta
				if(alfa >= g){
					break;
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


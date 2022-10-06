import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class State implements Cloneable{
	private char[][] board; 
	private int[] agentA, agentB;
	private int[] score;
	private int turn;
	private int food;
	private Vector<String> moves;

	public State() {
		this.agentA = new int[] {0,0};
		this.agentB = new int[] {0,0};
		this.score = new int[] {0,0};
		this.turn = 0;
		this.food = 0;
		this.moves = new Vector<String>();
	}

	public int getTurn(){
		return this.turn; 
	}
	
	public int[] getScore(){
		return this.score;
	}

	public void changeScore(int agent, int toAdd){
		score[agent] += toAdd;
	}

	public void changeBoard(int i, int j, char C) {
		board[i][j] = C;
	}

	public void read(String file) {
		java.io.RandomAccessFile text;
		try {
			text = new java.io.RandomAccessFile(file, "r");
			String s = text.readLine();
			
			int Width = Integer.parseInt(s.split(" ")[0]);
			int Height = Integer.parseInt(s.split(" ")[1]);
			
			/* this.food = 0; 
			score = new int[2];
			score[0] = 0; score[1] = 0; */
			board = new char[Height][Width];
			
			for(int i = 0; i < Height; i++) {
				String line = text.readLine();
				char[] characters = line.toCharArray();
				for(int j = 0; j < Width; j++) {
					board[i][j] = characters[j];
					if(characters[j] == 'A'){
						agentA[0] = i;
						agentA[1] = j;
						board[i][j] = ' ';
					}
					else if(characters[j] == 'B'){
						agentB[0] = i;
						agentB[1] = j;
						board[i][j] = ' ';
					}
					else if(characters[j] == '*'){
						food++;
					}
				}
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String toString() {
		String s = "";
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				if(i == agentA[0] && j == agentA[1]) {s+= 'A';}
				else if (i == agentB[0] && j == agentB[1]) {s+= 'B';}
				else {s+= board[i][j];}
			}
			s+="\n";
		}
		
		return s;
	}
	
	public void printCoordinates() {
		System.out.println("Character A: " + agentA[0] + " - " + agentA[1]);
		System.out.println("Character B: " + agentB[0] + " - " + agentB[1]);
	}
	
//	protected Object clone() throws CloneNotSupportedException{
//		State Copy = (State) super.clone();
//		Copy.moves = (Vector) moves.clone();
//		return Copy;
//	}

	public State Copy() { //copies State variables to a new State
		int[] newAgentA = new int[agentA.length];
		System.arraycopy(agentA, 0 , newAgentA, 0, agentA.length);
		int[] newAgentB = new int[agentB.length];
		System.arraycopy(agentB, 0 , newAgentB, 0, agentB.length);
		int[] newScore = new int[score.length];
		System.arraycopy(score, 0 , newScore, 0, score.length);
		char[][] newBoard = new char[board.length][board[0].length];
		for(int i = 0; i < board.length; i++){
			System.arraycopy(board[i], 0, newBoard[i], 0, board[i].length);
		}

		Vector newMoves = new Vector(moves);
		
		State newState = new State();
		newState.board = newBoard;
		newState.agentA = newAgentA;
		newState.agentB = newAgentB;
		newState.score  = newScore;
		newState.moves = newMoves;
		newState.turn = turn;
		newState.food = food;

		return newState;
	}
	
	public Vector<String> legalMoves(int agent){
		Vector<String> legal_moves = new Vector<String>(); //initialises string vector
		int agent_coordinates[] = new int[] {0,0};
		if(agent == 0) { //checks whose turn it is
			agent_coordinates = agentA;
		}
		else if(agent == 1) {
			agent_coordinates = agentB;
		}
		if(board[agent_coordinates[0]][agent_coordinates[1]] == '*') { //if food
			legal_moves.add("eat");
		}
		if(board[agent_coordinates[0]][agent_coordinates[1]] != '*' && board[agent_coordinates[0]][agent_coordinates[1]] != '#') { //if placing wall is legal
			legal_moves.add("block"); 
		}
		if(agent_coordinates[0] > 0) { //if agent is not against upper wall
			if(board[agent_coordinates[0]-1][agent_coordinates[1]] != '#'){
				legal_moves.add("up");
			}
		}
		if(agent_coordinates[0] < board.length - 1) { //if agent is not against lower wall
			if(board[agent_coordinates[0]+1][agent_coordinates[1]] != '#'){
				legal_moves.add("down");
			}
		}
		if(agent_coordinates[1] > 0) { //if agent is not against left wall
			if(board[agent_coordinates[0]][agent_coordinates[1]-1] != '#'){
				legal_moves.add("left");
			}
		}
		if(agent_coordinates[1] < board[0].length - 1) { //if agent is not against right wall
			if(board[agent_coordinates[0]][agent_coordinates[1]+1] != '#'){
				legal_moves.add("right");
			}
		}
		
		
		return legal_moves;
	}
	
	public Vector<String> legalMoves(){
		return legalMoves(turn % 2); 
	}
	
	public void execute(String action) {
	
		if(action == "up") {
			if(turn % 2 == 0) { agentA[0] = agentA[0] - 1; }
			else { 				agentB[0] = agentB[0] - 1; }
		}
		if(action == "down") {
			if(turn % 2 == 0) { agentA[0] = agentA[0] + 1; }
			else { 				agentB[0] = agentB[0] + 1; }
		}
		if(action == "left") {
			if(turn % 2 == 0) { agentA[1] = agentA[1] - 1; }
			else { 				agentB[1] = agentB[1] - 1; }
		}
		if(action == "right") {
			if(turn % 2 == 0) { agentA[1] = agentA[1] + 1; }
			else { 				agentB[1] = agentB[1] + 1; }
		}
		
		if(action == "eat") {
			if(turn % 2 == 0) { 
				board[agentA[0]][agentA[1]] = ' ';
				food--;
				score[0]++;
			}
			else { 
				board[agentB[0]][agentB[1]] = ' ';
				food--;
				score[1]++;
			}
		}
		
		if(action == "block") {
			if(turn % 2 == 0) { board[agentA[0]][agentA[1]] = '#';}
			else { 				board[agentB[0]][agentB[1]] = '#';}
		
		}
		turn++;
		moves.add(action);
//		System.out.print(" score 0: " + score[0]);
//		System.out.print(" score 1: " + score[1]);
		//System.out.println(action);
		//System.out.println(this.toString());
	}
	
	public boolean isLeaf() {
		
		if ( legalMoves(0).isEmpty() || legalMoves(1).isEmpty() || food <= 0 ) {
			return true;
		} 
		if (legalMoves(0).lastElement() == "block" || legalMoves(1).lastElement() == "block") {
			return true;
		}
		
//		if ( legalMoves(0).isEmpty() || legalMoves(0).indexOf("block") == 0 || legalMoves(1).indexOf("block") == 0 || legalMoves(1).isEmpty() || food <= 0 ) {
//			return true;
//		}  
		
		return false;
	}
	
	public double value(int agent) {
		
		double value = 0;
		
		if ( isLeaf() ) {
			if ( legalMoves(agent).isEmpty() || legalMoves(agent).lastElement() == "block") {
				value = -1;
			}
			else if ( legalMoves((agent + 1) % 2).isEmpty() || legalMoves((agent + 1 ) % 2 ).lastElement() == "block" ) {
				value = 1;
			}
			else if ( score[agent] > score[(agent + 1) % 2] ) {
				value = 1;
			}
			else if ( score[agent] < score[(agent + 1) % 2] ) {
				value = -1;
			}
		}
		
		return value;
	}
	
}

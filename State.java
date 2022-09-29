import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class State {
	char[][] board; 
	int[] agentA, agentB;
	int[] score;
	int turn;
	int food;
	Vector<String> moves;
	
	public State() {
		this.agentA = new int[] {0,0};
		this.agentB = new int[] {0,0};
		this.score = new int[] {0,0};
		this.turn = 0;
		this.food = 0;
		this.moves = new Vector<String>();
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
					}
					else if(characters[j] == 'B'){
						agentB[0] = i;
						agentB[1] = j;
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
	
	public State copy() { //copies State variables to a new State
		State to_return = new State();
		to_return.agentA = this.agentA;
		to_return.agentB = this.agentB;
		to_return.score = this.score;
		to_return.turn = this.turn;
		to_return.food = this.food;
		to_return.moves = this.moves;
		to_return.board = this.board;
		
		return to_return;
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
		if(agent_coordinates[0] > 0) { //if agent is not against upper wall
			if(board[agent_coordinates[0]-1][agent_coordinates[1]] != '#'){
				legal_moves.add("up");
			}
		}
		if(agent_coordinates[0] < board.length) { //if agent is not against lower wall
			if(board[agent_coordinates[0]+1][agent_coordinates[1]] != '#'){
				legal_moves.add("down");
			}
		}
		if(agent_coordinates[1] > 0) { //if agent is not against left wall
			if(board[agent_coordinates[0]][agent_coordinates[1]-1] != '#'){
				legal_moves.add("left");
			}
		}
		if(agent_coordinates[1] < board[0].length) { //if agent is not against right wall
			if(board[agent_coordinates[0]][agent_coordinates[1]+1] != '#'){
				legal_moves.add("right");
			}
		}
		if(board[agent_coordinates[0]][agent_coordinates[1]] == '*') { //if food
			legal_moves.add("eat");
		}
		if(board[agent_coordinates[0]][agent_coordinates[1]] != '*' && board[agent_coordinates[0]][agent_coordinates[1]] != '#') { //if placing wall is legal
			legal_moves.add("block"); 
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
	}
	
}

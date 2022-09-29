import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class State {
	char[][] board; 
	int[] agentA, agentB;
	int[] score;
	int turn;
	int food;
	
	public State() {
		this.agentA = new int[] {0,0};
		this.agentB = new int[] {0,0};
		this.score = new int[] {0,0};
		this.turn = 0;
		this.food = 0;
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
				s+= board[i][j];
			}
			s+="\n";
		}
		
		return s;
	}
	
	public void printCoordinates() {
		System.out.println("Character A: " + agentA[0] + " - " + agentA[1]);
		System.out.println("Character B: " + agentB[0] + " - " + agentB[1]);
	}
	
	public State copy() {
		State to_return = new State();
		to_return.agentA = this.agentA;
		to_return.agentB = this.agentB;
		to_return.score = this.score;
		to_return.turn = this.turn;
		to_return.food = this.food;
		to_return.board = this.board;
		
		return to_return;
	}
	
}

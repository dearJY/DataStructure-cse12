/*
* Name: Jingyi Ouyang
* ID: A53108909
* Login: cs12feg
*/

package hw1;


import java.util.Scanner;
import java.util.Random;

/**
* A class that implements the game - "tic-tac-toe"
* @version 1.0
* @author  Jingyi Ouyang
* @since   2016-09-27
*/ 

public class HW1 {

	static char[] [] board = new char[3][3];
	static Scanner input=new Scanner(System.in);


	//Object of Stats class to maintain statistics
	static Stats stat = new Stats();  

	
	/**
	 * Prints the TicTacToe board
	 * @param arr: The board so far
	 */
	public static void printBoard(char [][] arr){
		System.out.println();
		for (int i=0; i<3; i++)
		{
			for (int j=0; j<3; j++)
			{
				System.out.print(arr[i][j]);
				if(j!=2)

					//Print the | for readable output
					System.out.print(" " + "|" + " ");    
			}
			System.out.println();
			if(i!=2) {
				System.out.print("_   _   _ ");    // Print _ for readability
				System.out.println();;
			}
		}
	}
	
	/**
	 * Clear the TicTacToe board before starting a new game
	 * @param arr: The board so far
	 */
	public static void clearBoard(char [][] arr){
		for (int i=0; i<3; i++)
		{
			for (int j=0; j<3; j++)
			{
				arr[i][j]=' ';
			}
		}
	}
	
	/** Determines if the player with the specified token wins
	 * 
	 * @param symbol: Specifies whether the player is X or O
	 * @return true if player has won, false otherwise
	 */
	 public static boolean isWon(char symbol) {
		  
		//horizontal
	    for (int i = 0; i < 3; i++) {
	      if (board[i][0] == symbol 
	          && board[i][1] == symbol
	          && board[i][2] == symbol) {
	        return true;
	       }
	     }
	    
	      //vertical
	    for (int i = 0; i < 3; i++) {
	    	if (board[0][i] == symbol
	    		&& board[1][i] == symbol
	    		&& board[2][i] == symbol) {
	    		return true;
	    	}
	      }
	    
	    //right diagonal
	    if (board[0][0] == symbol
	    	&& board[1][1] == symbol
	    	&& board[2][2] == symbol) {
	    	return true;
	    }
	    
	    //left diagonal
	    if (board[0][2] == symbol
	    	&& board[1][1] == symbol
	    	&& board[2][0] == symbol) {
	    	return true;
	    }

	    return false;
	}

	  /** Determines if the cell is occupied
	   * 
	   * @param row: Row of the cell to be checked
	   * @param col: Column of the cell to be checked
	   * @return true if the cell is occupied, false otherwise
	   */
	public static boolean isOccupied(int row, int col){
		  
		 if (board[row][col]!=' ') return false;
		 else return true;
	}
	  
	  /** Determines who starts the game
	   */
	public static int whoStarts(){

		  Random rand = new Random();
		  int starter = rand.nextInt(2);
		  return starter;
		  
	}
	  
	  /** takes care of the human's move
	   * 1. Prompt for a cell, then column
	   * 2. Puts a symbol (X or O) on the board
	   * 3. Prints the updated board
	   * 4. If a human wins: prints, updates stats and returns true
	   * 5. If not a win yet, returns false */
	  
	public static boolean humanTurn(char symbol) {
		//Prompt for a cell. User must enter 
	  	//row and column with a space in between. 
		System.out.print("\n\nEnter your move: (row column): " );
		int row = input.nextInt();
		int col = input.nextInt();
		board[row][col] = symbol;
		printBoard(board);
		return isWon(symbol);
			
	} 
	  
	  /** takes care of the computer's move
	   * 1. Generates numbers until finds an empty cell
	   * 2. Puts a symbol (X or O) on the board
	   * 3. Prints the updated board
	   * 4. If a comp wins: prints, updates stats and returns true
	   * 5. If not a win yet, returns false */
	  
	public static boolean compTurn(char symbol) {

	   //Choose a random row (0-2) and column (0-2)
	   //Check if the randomly chosen cell is occupied. Keep choosing 
	   //until an empty cell is found
		int row;
		int col;
		do{
		    Random rand = new Random();
		    row = rand.nextInt(3);
		    col = rand.nextInt(3);
		    continue;
		} while(!isOccupied(row,col));
		   

		//Mark the move, print the board and check if computer won
	    System.out.println("\n\nMy Move is: "+row+" "+ col);
		board[row][col] = symbol;
		printBoard(board);
		return isWon(symbol);
			
	  }
	  
	  /** If human goes first:
	   * We have 9 moves in total (max). 8 moves will be in a loop
	   * and the last human move is outside of the loop:
	   * 1. human goes first, with a X
	   * 2. If the returned value is true (human won), then boolean flag=true
	   *    and we break out of the loop. done indicates that the game is over.
	   * 3. If the game is not over, then it is computer's turn. 
	   * 4. If the returned value is true (comp won), then boolean flag=true
	   *    and we break out of the loop. done indicates that the game is over
	   * 5. Repeat the two steps above 3 more times. 
	   * 6. If the done is still false, then a human performs one more move and
	   * we check if the move led to the win or tie.    
	   * */
	  
	public static void humanFirst(){
			
		boolean done=false;

		for (int i=0; i<4; i++) {	
		if (humanTurn('X')) {
					done=true;
					stat.incrementUserWins();
					System.out.println("You won!!");
					System.out.println();
					break;
				}
		if (compTurn('O')){
					done=true;
					stat.incrementComputerWins();
					System.out.println("I won!!");
					System.out.println();
					break;
				}
		    }  //end of for loop;

		if (!done){
		    if (!humanTurn('X')) {
		    	System.out.println("\n\nA tie!");
		    	stat.incrementTies();
		    }
		}
	}
	  
	  /**
	   * Same logic as above, only the first computer's move happens before
	   * the loop. We do not need to check for winning combination here, since
	   * comp can't win after one move. 
	   * After the loop we check if the game is done. If not, report a tie and
	   * update statistics.
	   */
	public static void compFirst(){
	    
	    boolean done = false;
	    
	    done  = compTurn('X');

	    for(int i=0; i<4; i++) {
	    	if (humanTurn('0')) {
				done=true;
				stat.incrementUserWins();
				System.out.println("You won!!");
				System.out.println();
				break;
			}
			if (compTurn('X')){
				done=true;
				stat.incrementComputerWins();
				System.out.println("I won!!");
				System.out.println();
				break;
			}
		}

	    if(!done) {
	    	System.out.println("\n\nA tie!");
    		stat.incrementTies();
	    }
		  
	}
	  
	public static void main(String[] args) {
		
		// input from the user, if he wants to play another game
		String playAgain=""; 

		// input from the user, if he wants to clear stats
		String clearStats=""; 

		//play until 'n' is pressed
		do {      
			clearBoard(board);   //clear the baord

			//Generate Random Assignment, determines who goes first;
			int move = whoStarts();
			if (move == 0) {
				System.out.println("\nI start first. I choose X and you get 0");
				compFirst();
			}
			else{ 
				System.out.println("\nYou start first. You get X and I get 0");
				humanFirst(); 
			}
		
			//Print statistics and ask if a user wants to repeat a game
			stat.printStats();
			System.out.println();
			System.out.println("Play again?");
			playAgain = input.next();

			// If user enters 'y', ask to clear statistic
			if(playAgain.charAt(0) == 'y') {
				System.out.println("clear statistics and restart the game?");
				clearStats = input.next();
				if(clearStats.charAt(0) == 'y') {
					stat.reset();
				}
			}	
		} while(playAgain.charAt(0)!='n'); // //If user enters 'n', quit the game	
	    
	    System.out.println("\nBye, see you later!");
	}
}

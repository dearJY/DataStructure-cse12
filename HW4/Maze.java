//package hw4;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Maze {

	public Square[][] maze;
	protected int numRows=0;
	protected int numCols=0;
	private Square start;
	private Square finish;

	@SuppressWarnings("resource")
	public boolean loadMaze(String fname){

		String line;
		BufferedReader inputStrem;
		StringTokenizer st;


		try {
			int currentRow = 0;

			inputStrem = new BufferedReader(new FileReader(fname));
			line = inputStrem.readLine();
			if(line != null)
			{
				st = new StringTokenizer(line);
				numRows = Integer.parseInt(st.nextToken());
				numCols = Integer.parseInt(st.nextToken());
				maze = new Square[numRows][numCols];
			}
			else {
				return false;
			}

			while ((line = inputStrem.readLine()) != null) {
				if (numRows == 0) {  
					// true if reading first line in file, containing  numRows numColums
					st = new StringTokenizer(line);
					numRows = Integer.parseInt(st.nextToken());
					numCols = Integer.parseInt(st.nextToken());
					maze = new Square[numRows][numCols];
				} else if (line.length() == 1)
					break; 
				else {
					int col=0;
					for (int c = 0; c < line.length(); c++) {

						if(line.charAt(c) == ' ') continue;
						maze[currentRow][col] = new Square(currentRow, col, line.charAt(c)-'0');

						//save position of start and exit
						if(maze[currentRow][col].getType() == 2) {
							start  = maze[currentRow][col];
							start.setVisited();
						}else if(maze[currentRow][col].getType() == 3) {
							finish = maze[currentRow][col];
						}
						col++;
					}
					currentRow ++;
				}
			}
		}
		catch (IOException e) {
			System.out.println (e.toString());
			System.out.println("Could not find file " + fname);
			return false;
		} 

		return true;
	}

	/**
	 * return an ArrayList of the Square neighbors of the parameter Square sq.
	 * @param sq
	 * @return ArrayList<Square>
	 */
	public ArrayList<Square> getNeighbors(Square sq){

		ArrayList<Square> res = new ArrayList<>();
		int[][] d = {{-1,0},{0,1},{1,0},{0,-1}};

		for (int i=0;i<4;i++){

			int x = sq.getRow()+d[i][0];
			int y = sq.getCol()+d[i][1];

			if (x<0 || x> maze.length-1 || y<0 || y> maze[0].length-1) {

				continue;
			}

			if(maze[x][y].getType()!=1) {
				res.add(maze[x][y]);
			}

		}

		return res;

	}

	/**
	 * return the saved start locations.
	 * @return Square
	 */
	public Square getStart(){

		return start;
	}

	/**
	 * return the saved finish locations.
	 * @return Square
	 */
	public Square getFinish(){

		return finish;

	}

	/**
	 * Set the Square at (row,col) as visited.
	 * @param row
	 * @param col
	 */
	public void setVisit(int row, int col)
	{
		maze[row][col].setVisited();
	}

	/**
	 * Clear all visited squares.
	 */
	public void clearMaze() {

		for(int i=0;i<maze.length;i++) {
			for(int j=0;j<maze[0].length;j++) {
				maze[i][j].clear();
			}
		}
	}

	/**
	 * Return a String representation of this Maze
	 * @return
	 */
	public String toString() {

		String s="";
		for (int r = 0; r < numRows; r++) 
		{
			for (int c = 0; c < numCols; c++) {
				s=s+maze[r][c].toString()+" ";
			}

			s=s+"\n";
		}
		return s;  
	}


}

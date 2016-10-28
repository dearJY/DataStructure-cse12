
//package hw4;

import java.util.*;


public abstract class MazeSolver {

	protected Maze maze = new Maze();
	private String path="Found the Escape!"; 
	int[][] visited ;
	boolean gameOver=false; //when stop our game
	private Square next;//next square in the worklist
	private Square last;//last element being explored
	private StringBuilder p = new StringBuilder();

	/**
	 * Create an empty worklist
	 */
	abstract void makeEmpty();

	/**
	 * Return true if the worklist is empty
	 * @return boolean
	 */
	abstract boolean isEmpty();

	/**
	 * Add the given Square to the worklist
	 * @param sq
	 */
	abstract void add(Square sq);

	/**
	 * Return the ”next” item from the worklist
	 * @return Sqaure
	 */
	abstract Square next();


	/**
	 * a constructor that takes a Maze as a parameter
	 * and stores it in a variable that the children classes can access.
	 * @param maze2
	 */
	MazeSolver(Maze maze2){

		this.maze = maze2;

	}

	/**
	 * Getter for maze
	 * @return Maze
	 */
	public Maze getMaze() {

		return this.maze;
	}

	/**
	 * Repeatedly call step() until you get to the exit square or the worklist is empty.
	 */
	public void solve()
	{
		//add start to the worklist
		add(maze.getStart());

		do {
			next = next();//get next element
			last = step();//explore next element and store it
		} while (!isEmpty() && !next.isFinish());

		//find the path and record it
		if(isSolved()) {

			trackPath();
		}

	}

	/**
	 * Perform one iteration of the algorithm
	 * @return
	 */
	public Square step()
	{
		//if find the exit, game is over
		if(next.isFinish()) {
			gameOver = true;
			return next;
		}


		ArrayList<Square> neighbors = maze.getNeighbors(next);

		//add neighbors into worklist and change their types
		for(int i=0;i<neighbors.size();i++) {
			Square n = neighbors.get(i);
			if(n.isVisited()) {
				continue;
			} else {
				add(n);
				n.setVisited();
				if(!n.isStart() && !n.isFinish()) n.changeType(4);
				n.setPre(next);
			}
		}
		next.setExplored();//change next to explored status
		if(!next.isStart())next.changeType(5);

		return next;

	}

	/**
	 * he application program can use to see if this algorithm has solved this maze.
	 * @return boolean
	 */
	public boolean isSolved() {

		return gameOver;
	}

	/**
	 * @return Path from S to E as a list of coordinates [i,j]
	 * If not solvable, print message
	 */
	public String getPath() { 

		if (isSolved()) return path;
		else 
		{
			path = "Uh Oh!! There's no escape!!";
			return path;

		}

	}

	/**
	 * get the coordinates of valid path
	 *
	 * @return String
	 */
	public String trackPath() {

		int count = 0;

		//get coordinates of squares in final
		//path and add them into a string
		while(!last.isStart()) {

			//always add first beacause we track the
			//path from exit to start
			p.insert(0,last.coordinate());

			//every ten squares in a row
			count++;
			if(count%10 == 0) {
				p.insert(0,'\n');
			}

			if(!last.isFinish()) {
				last.changeType(6);//change type to "x"
			}

			last = last.getPre();//get to previous one

		}

		p.insert(0,last.coordinate());// coordinates of start
		p.insert(0,'\n');
		p.insert(0,path);
		path = p.toString();
		return path;
	}
}




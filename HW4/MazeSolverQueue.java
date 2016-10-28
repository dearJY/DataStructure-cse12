

//package hw4;

/**
 * use queue as worklist
 */
public class MazeSolverQueue extends MazeSolver {

	/**
	 * worklist
	 */
	MyQueue<Square> queue = new MyQueue<>();

	/**
	 * constructor
	 * @param maze
	 */
	MazeSolverQueue(Maze maze){
		super(maze);
	}

	/**
	 * override
	 * Create an empty worklist
	 */
	public void makeEmpty(){

		queue = new MyQueue<>();
	}

	/**
	 * override
	 * Return true if the worklist is empty
	 * @return boolean
	 */
	public boolean isEmpty(){

		if(queue.isEmpty()) {

			return true;
		} else {

			return false;
		}

	}

	/**
	 * override
	 * Add the given Square to the worklist
	 * @param sq
	 */
	public void add(Square sq) {

		queue.addElement(sq);
	}

	/**
	 * override
	 * Return the ”next” item from the worklist
	 * @return Sqaure
	 */
	public Square next() {

		return queue.removeElement();
	}

	/**
	 * get the worklist
	 * @return MyQueue<Square>
	 */
	public MyQueue<Square> getQueue() {

		return queue;
	}


	public static void main( String[] args )
	  {
		Maze myMaze = new Maze();
		boolean load = myMaze.loadMaze(args[0]);
		if(!load) {
			System.out.println("Oops!! Could not load the Maze");
		} else {
			MazeSolverQueue queueSolver = new MazeSolverQueue(myMaze);
			queueSolver.solve();
			System.out.println(queueSolver.getPath() +"\n");
			System.out.println(queueSolver.getMaze().toString());
			System.out.println("Number of squares remaining in the worklist = "+ queueSolver.getQueue().size() );
		}
	  }

}


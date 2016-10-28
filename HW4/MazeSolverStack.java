

//package hw4;

/**
 * use stack as worklist
 */
public class MazeSolverStack extends MazeSolver {

	/**
	 * worklist
	 */
	private MyStack<Square> stack = new MyStack<>();

	/**
	 * counstructor
	 * @param maze
	 */
	MazeSolverStack(Maze maze){
		super(maze);
	}

	/**
	 * override
	 * Create an empty worklist
	 */
	public void makeEmpty(){

		stack = new MyStack<>();
	}

	/**
	 * override
	 * Return true if the worklist is empty
	 * @return boolean
	 */
	public boolean isEmpty(){

		if(stack.isEmpty()) {
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

		stack.addElement(sq);
	}

	/**
	 * override
	 * Return the ”next” item from the worklist
	 * @return Sqaure
	 */
	public Square next() {

		return stack.removeElement();
	}

	/**
	 * get the worklist
	 * @return MyStack<Sqaure>
	 */
	public MyStack<Square> getStack() {

		return stack;
	}

	public static void main( String[] args )
	{
		Maze myMaze = new Maze();
		boolean load = myMaze.loadMaze(args[0]);
		if(!load) {
			System.out.println("Oops!! Could not load the Maze");
		} else {
			MazeSolverStack stackSolver = new MazeSolverStack(myMaze);
			stackSolver.solve();
			System.out.println(stackSolver.getPath() +"\n");
			System.out.println(stackSolver.getMaze().toString());
			System.out.println("Number of squares remaining in the worklist = "+ stackSolver.getStack().size() );
		}
	}
}

/**
 * Name: Jingyi Ouyang
 * PID: A53108909
 */

//package hw4

import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MazeTester {

    private Maze maze2;
    private Maze mazeT;
    private boolean load;

    @Before
    public void setUp() {

        maze2 = new Maze();
        mazeT = new Maze();
        load = maze2.loadMaze("maze-2.txt");
    }


    /**
     * test loadMaze()
     */
    @Test
    public void testLoadMaze() {

        assertTrue("load maze-2", load);
        assertFalse("load unkonwn maze", mazeT.loadMaze("mazeT.txt"));
    }

    /**
     * test getStart()
     */
    @Test
    public void testGetStart() {

        int sRow = maze2.getStart().getRow();
        int sCol = maze2.getStart().getCol();
        assertEquals("row of start", 6, sRow);
        assertEquals("col of start", 4, sCol);
    }

    /**
     * test getFinish()
     */
    @Test
    public void testGetFinish() {

        int sRow = maze2.getFinish().getRow();
        int sCol = maze2.getFinish().getCol();
        assertEquals("row of start", 6, sRow);
        assertEquals("col of start", 11, sCol);
    }

    /**
     * test getNeighbors()
     */
    @Test
    public void testNeighbors() {

        //right top corner
        Square corner1 = maze2.maze[0][0];
        ArrayList<Square> a1 = maze2.getNeighbors(corner1);
        int amounts1 = a1.size();
        assertEquals("amounts:corner1",1,amounts1);
        assertEquals("row:corner1",0,a1.get(0).getRow());
        assertEquals("col:corner1",1,a1.get(0).getCol());

        //left down corner
        Square corner2 = maze2.maze[6][12];
        ArrayList<Square> a2 = maze2.getNeighbors(corner2);
        int amounts2 = a2.size();
        assertEquals("amounts:corner2",2,amounts2);
        assertEquals("row:corner2 North",5,a2.get(0).getRow());
        assertEquals("col:corner2 North",12,a2.get(0).getCol());
        assertEquals("row:corner2 West",6,a2.get(1).getRow());
        assertEquals("col:corner2 West",11,a2.get(1).getCol());

        // in the middle of the maze having 4 valid neighbors
        Square middle = maze2.maze[4][6];
        ArrayList<Square> a3 = maze2.getNeighbors(middle);
        int amounts3 = a3.size();
        assertEquals("amounts:corner1",4,amounts3);
        assertEquals("row:corner2 North",3,a3.get(0).getRow());
        assertEquals("col:corner2 North",6,a3.get(0).getCol());
        assertEquals("row:corner2 East",4,a3.get(1).getRow());
        assertEquals("col:corner2 East",7,a3.get(1).getCol());
        assertEquals("row:corner2 South",5,a3.get(2).getRow());
        assertEquals("col:corner2 South",6,a3.get(2).getCol());
        assertEquals("row:corner2 West",4,a3.get(3).getRow());
        assertEquals("col:corner2 West",5,a3.get(3).getCol());

        //pass a wall in the left border
        Square border1 = maze2.maze[1][0];
        ArrayList<Square> a4 = maze2.getNeighbors(border1);
        int amounts4 = a4.size();
        assertEquals("amounts:corner2",2,amounts4);
        assertEquals("row:corner2 North",0,a4.get(0).getRow());
        assertEquals("col:corner2 North",0,a4.get(0).getCol());
        assertEquals("row:corner2 South",2,a4.get(1).getRow());
        assertEquals("col:corner2 South",0,a4.get(1).getCol());

    }

    /**
     * print maze
     */
    @Test
    public void testToString() {

        System.out.println(maze2.toString());
    }


    }

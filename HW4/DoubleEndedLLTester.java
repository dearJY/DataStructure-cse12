

//package hw4

import org.junit.*;
import static org.junit.Assert.*;


public class DoubleEndedLLTester {

    private DoubleEndedLL<Integer> empty ;
    private DoubleEndedLL<Integer> one ;
    private DoubleEndedLL<Integer> several1 ;
    private DoubleEndedLL<String>  slist ;
    private DoubleEndedLL<Integer> several2;
    static final int DIM = 5;

    @Before
    public void setUp()
    {
        empty = new DoubleEndedLL<Integer>();
        one = new DoubleEndedLL<Integer>();
        one.addFirst(new Integer(0));
        several1 = new DoubleEndedLL<Integer>();
        several2 = new DoubleEndedLL<Integer>();

        // List: 1,2,3,...,Dim
        for (int i = DIM; i > 0; i--)
            several1.addFirst(new Integer(i));
        // List:1,2,3,...,Dim
        for (int i = 1; i<=DIM; i++)
            several2.addLast(new Integer(i));

        // List: "First","Last"
        slist = new DoubleEndedLL<String>();
        slist.addFirst("First");
        slist.addFirst("Last");

    }

    /** Test if size of lists are correct */
    @Test
    public void testListSize()
    {
        assertEquals("Check Empty Size",0,empty.size());
        assertEquals("Check One Size",1,one.size());
        assertEquals("Check Several Size",DIM,several1.size());
        assertEquals("Check Several Size",DIM,several2.size());
    }

    /** Test isEmpty */
    @Test
    public void testEmpty()
    {
        assertTrue("empty is empty",empty.isEmpty());
        assertTrue("one is not empty",!one.isEmpty());
        assertTrue("several is not empty",!several1.isEmpty());
    }

    /**
     * Test add First method
     */
    @Test
    public void testAddF() {

        for(int i=0; i<DIM; i++) {
            assertEquals("element"+i, new Integer(i+1),several1.get(i));
        }
    }

    /**
     * Test add Last method
     */
    @Test
    public void testAddL() {

        for(int i=0; i<DIM; i++) {
            assertEquals("element"+i, new Integer(i+1),several2.get(i));
        }
    }

    /**
     * Test remove the first node
     */
    @Test
    public void testRemoveF() {

        assertEquals("remove 1st node of slist","Last",slist.removeFirst());
        assertEquals("size after removing",1,slist.size());
    }

    /** Test NullPointerException on removeFirst*/
    @Test
    public void testRemove1() {

        try {
            empty.removeFirst();
            fail("Should have generated an exception");
        } catch(NullPointerException e) {
            //normal
        }
    }

    /**
     * Test remove the last node
     */
    @Test
    public void testRemoveL() {

        assertEquals("remove Last node of slist","First",slist.removeLast());
        assertEquals("size after removing",1,slist.size());
    }

    /** Test NullPointerException on removeLast*/
    @Test
    public void testRemove2() {

        try {
            empty.removeLast();
            fail("Should have generated an exception");
        } catch(NullPointerException e) {
            //normal
        }
    }
}

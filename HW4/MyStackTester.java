

//package hw4

import org.junit.*;
import static org.junit.Assert.*;

public class MyStackTester {

    private MyStack<Integer> several;
    private MyStack<Integer> empty;
    static final int DIM = 5;

    @Before
    public void setUp() {

        several = new MyStack();
        empty = new MyStack();

        for(int i=0; i<DIM;i++) {
            several.addElement(i+1);
        }

    }

    /**
     * test isEmpty
     */
    @Test
    public void testEmpty() {
        assertFalse("several empty",several.isEmpty());
        assertTrue("empty is empty", empty.isEmpty());
    }

    /**
     * test remove element
     */
    @Test
    public void testRemove() {

        assertEquals("remove the last element",new Integer(5),several.removeElement());
        assertEquals("remove the second last element",new Integer(4),several.removeElement());
        assertEquals("several size",3,several.size());

    }

    /**
     * test size
     */
    @Test
    public void testSize() {

        assertEquals("empty size",0,empty.size());
        assertEquals("several size",5,several.size());
    }
}

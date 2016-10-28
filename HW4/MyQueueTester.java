

//package hw4

import org.junit.*;
import static org.junit.Assert.*;

public class MyQueueTester {

    private MyQueue<Integer> several1;
    private MyQueue<Integer> several2;
    private MyQueue<Integer> empty;

    @Before
    public void setUp() {

        several1 = new MyQueue();
        several2 = new MyQueue(5);
        empty = new MyQueue();

        for(int i=0; i<11;i++) {
            several1.addElement(i+1);
        }

        for(int i=0; i<5;i++) {
            several2.addElement(i+1);
        }
    }

    /**
     * test isEmpty
     */
    @Test
    public void testEmpty() {
        assertFalse("several is empty",several1.isEmpty());
        assertFalse("several is empty",several2.isEmpty());
        assertTrue("empty is  empty", empty.isEmpty());
    }

    /**
     * test remove element
     */
    @Test
    public void testRemove() {

        assertEquals("remove the first element",new Integer(1),several1.removeElement());
        assertEquals("remove the second element",new Integer(2),several1.removeElement());

        several2.addElement(6);
        for(int i=0;i<=5;i++) {

            assertEquals("remove the element",new Integer(i+1),several2.removeElement());
        }
        assertEquals("remove the element",null,several2.removeElement());
    }

    /**
     * test size
     */
    @Test
    public void testSize() {

        assertEquals("several1 size",20,several1.size());

        several2.addElement(6);
        assertEquals("several2 size",10,several2.size());



    }

}


package hw2;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 *  Title: class DoublyLinkedListTester
 *  Description: JUnit test class for DoublyLinkedList class
 * */

public class DoublyLinkedList12Tester
{
    private DoublyLinkedList12<Integer> empty ;
    private DoublyLinkedList12<Integer> one ;
    private DoublyLinkedList12<Integer> several ;
    private DoublyLinkedList12<String>  slist ;
    static final int DIM = 5;
    ListIterator<Integer> itr1;
    ListIterator<Integer> itr2;
    ListIterator<Integer> itr3;
    ListIterator<String> itr4;

    /**
     * Standard Test Fixture. An empty list, a list with one entry (0) and
     * a list with several entries (0,1,2)
     */
    @Before
    public void setUp()
    {
        empty = new DoublyLinkedList12<Integer>();
        one = new DoublyLinkedList12<Integer>();
        one.add(0,new Integer(0));
        several = new DoublyLinkedList12<Integer>() ;
        // List: 1,2,3,...,Dim
        for (int i = DIM; i > 0; i--)
            several.add(0,new Integer(i));

        // List: "First","Last"
        slist = new DoublyLinkedList12<String>();
        slist.add("First");
        slist.add("Last");

        //iterator of DLL
        itr1 = empty.listIterator();
        itr2 = one.listIterator();
        itr3 = several.listIterator();
        itr4 = slist.listIterator();
    }

    /** Test if heads of the lists are correct */
    @Test
    public void testGetHead()
    {
        assertEquals("Check 0",new Integer(0),one.get(0)) ;
        assertEquals("Check 0",new Integer(1),several.get(0)) ;
    }

    /** Test if size of lists are correct */
    @Test
    public void testListSize()
    {
        assertEquals("Check Empty Size",0,empty.size()) ;
        assertEquals("Check One Size",1,one.size()) ;
        assertEquals("Check Several Size",DIM,several.size()) ;
    }

    /** Test setting a specific entry */
    @Test
    public void testSet()
    {
        slist.set(1,"Final");
        assertEquals("Setting specific value", "Final",slist.get(1));
    }

    /** Test isEmpty */
    @Test
    public void testEmpty()
    {
        assertTrue("empty is empty",empty.isEmpty()) ;
        assertTrue("one is not empty",!one.isEmpty()) ;
        assertTrue("several is not empty",!several.isEmpty()) ;
    }

    /** Test out of bounds exception on get */
    @Test
    public void testGetException()
    {
        try
        {
            empty.get(0);
            // This is how you can test when an exception is supposed
            // to be thrown
            fail("Should have generated an exception");
        }
        catch(IndexOutOfBoundsException e)
        {
            //  normal
        }
    }


    /** Test iterator on empty list and several list */
    @Test
    public void testIterator()
    {
        int counter = 0 ;
        ListIterator<Integer> iter;
        for (iter = empty.listIterator() ; iter.hasNext(); )
        {
            fail("Iterating empty list and found element") ;
        }
        counter = 0 ;
        for (iter = several.listIterator() ; iter.hasNext(); iter.next())
            counter++;
        assertEquals("Iterator several count", counter, DIM);
    }

    /** Test out of bounds exception on add(index,data)*/
    @Test
    public void testAdd1() {

        try {
            empty.add(-1,1);
            empty.add(1,1);
            several.add(DIM,6);
            fail("Should have generated an exception");
        } catch(IndexOutOfBoundsException e) {
            //normal
        }
    }

    /** Test Null pointer exception on add(index,data) and add(data)*/
    @Test
    public void testAdd2() {

        try {
            empty.add(0,null);
            empty.add(null);
            fail("Should have generated an exception");
        } catch(NullPointerException e) {
            //normal
        }

    }

    /** Test out of bounds exception on set*/
    @Test
    public void testSet1() {

        try {
            several.set(DIM,6);
            several.set(-1,-1);
            fail("Should have generated an exception");
        } catch(IndexOutOfBoundsException e) {
            //normal
        }
    }

    /** Test Null pointer exception on set*/
    @Test
    public void testSet2() {

        try {
            empty.set(0,null);
            several.set(0,null);
            fail("Should have generated an exception");
        } catch(NullPointerException e) {
            //normal
        }
    }

    /** Test remove */
    @Test
    public void testRemove() {

        assertEquals("remove 1st node of slist","First",slist.remove(0));
        assertEquals("size after removing",1,slist.size());
    }

    /** Test out of bound on remove*/
    @Test
    public void testRemove1() {

        try {
            empty.remove(0);
            slist.remove(3);
            slist.remove(-1);
            fail("Should have generated an exception");
        } catch(IndexOutOfBoundsException e) {
            //normal
        }
    }

    /** Test clear*/
    @Test
    public void testClear() {

        several.clear();
        assertEquals("size of several",0,several.size());

        //get first element
        try {
            several.get(0);
            fail("Should have generated an exception");
        } catch(IndexOutOfBoundsException e) {
            //normal
        }
    }

    /**Test next index */
    @Test
    public void testNextIndex() {

        assertEquals(0,itr3.nextIndex());
    }

    /** Test no such element exception of next*/
    @Test
    public void testNext1() {

        try {
            itr1.next();
            fail("Should have generated an exception");
        } catch (NoSuchElementException e){
            //normal
        }

    }

    /**Test next of iterator*/
    @Test
    public void testINext() {

        itr3.next();
        itr3.next();
        assertEquals("the element iterator points at",new Integer(3),itr3.next());
        assertEquals("index",3,itr3.nextIndex());

    }

    /** Test no such element of previous*/
    @Test
    public void testPrev1() {

        try {
            itr1.previous();
            fail("Should have generated exception");
        } catch(NoSuchElementException e) {
            //normal
        }

    }

    /** Test previous of iterator*/
    @Test
    public void testIPrev() {

        itr3.next();
        itr3.next();
        assertEquals("the element iterator points at",new Integer(2),itr3.previous());
        assertEquals("index",0,itr3.previousIndex());
    }

    /** Test hasPrevious */
    @Test
    public void testHasPrev() {

        assertFalse(itr3.hasPrevious());
        itr3.next();
        itr3.next();
        assertTrue(itr3.hasPrevious());

    }

    /** Test Null pointer exception of add*/
    @Test
    public void testIAdd1() {

        try {
            itr3.add(null);
            fail("Should have generated exception");
        } catch(NullPointerException e) {
            //normal
        }
    }

    /** Test add of iterator*/
    @Test
    public void testIAdd() {

        itr3.next();
        itr3.add(0);
        assertEquals("index",2,itr3.nextIndex());
        assertEquals("next element",new Integer(2),itr3.next());
        assertEquals("previous element",new Integer(2),itr3.previous());
    }

    /** Test Illegal state exception on remove of iterator*/
    @Test
    public void testIRemove1() {

        try {
            itr3.remove();
            fail("Should have generated exception1");
        } catch(IllegalStateException e) {
            //normal
        }

        try {
            itr3.next();
            itr3.add(0);
            itr3.remove();
            fail("Should have generated exception2");
        } catch(IllegalStateException e) {
            //normal
        }

        try {
            itr3.next();
            itr3.remove();
            itr3.remove();
            fail("Should have generated exception3");
        } catch(IllegalStateException e) {
            //normal
        }
    }

    /** Test remove of iterator*/
    @Test
    public void testIRemove() {

        itr3.next();
        itr3.remove();
        assertEquals("element after remove1",new Integer(2),itr3.next());
        itr3.next();
        itr3.next();
        itr3.previous();
        itr3.remove();
        assertEquals("element after remove2",new Integer(5),itr3.next());
        assertEquals("index",3,itr3.nextIndex());
    }

    /** Test exception on set of iterator*/
    @Test
    public void testISet1() {

        try {
            itr3.set(new Integer(0));
            fail("Should have generated exception1");
        } catch(IllegalStateException e) {
            //normal
        }

        try {
            itr3.next();
            itr3.add(0);
            itr3.set(1);
            fail("Should have generated exception2");
        } catch(IllegalStateException e) {
            //normal
        }

        try {
            itr3.next();
            itr3.remove();
            itr3.set(1);
            fail("Should have generated exception3");
        } catch(IllegalStateException e) {
            //normal

        }

        try {
            itr3.next();
            itr3.set(null);
            fail("Should have generated exception4");
        } catch(NullPointerException e) {
            //normal
        }
    }

    /** Test set of iterator*/
    @Test
    public void testISet() {
        itr3.next();
        itr3.set(6);
        assertEquals("element after setting1",new Integer(2),itr3.next());
        itr3.previous();
        itr3.set(7);
        assertEquals("element after setting2",new Integer(7),itr3.next());
    }
}

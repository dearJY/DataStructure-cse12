
package hw2;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ExtraCreditTester {

    private DoublyLinkedList12<Integer> several;
    static final int DIM = 5;

    @Before
    public void setUp() {

        several = new DoublyLinkedList12<Integer>() ;
        // List: 1,2,3,...,Dim
        for (int i = DIM; i > 0; i--)
            several.add(0,new Integer(i));

    }

    /**
     * test reverse and concatenate
     */
    @Test
    public void testRAC() {

        DoublyLinkedList12<Integer> newList = new DoublyLinkedList12<Integer>();
        for (int i = 3; i > 0; i--) {
            newList.add(0, new Integer(i));
        }

        several.reverseAndConcat(newList);

        //test if reverse and concatenate correctly
        int[] array = {1,2,3,3,2,1};

        for(int i=0;i<6;i++) {
            assertEquals(""+i+"element",new Integer(array[i]),newList.get(i));
        }

        //test if original list is changed
        for(int i=0; i<5;i++ ) {
            assertEquals(""+i+"element of original list",new Integer(i+1),several.get(i));
        }
    }


}



//package hw6

import org.junit.*;

import java.util.*;

import static org.junit.Assert.*;

public class dHeapTester {

    private dHeap<Integer> empty;
    private dHeap<Integer> max;
    private dHeap<Integer> min;

    @Before
    public void setup() {
        empty = new dHeap<>();
        max = new dHeap<>(5, true);
        min = new dHeap<>(10, false,5);

        for(int i=0; i<5;i++) {
            max.add(i);
        }

        for(int i=11; i>0;i--) {
            min.add(i);
        }
    }

    @Test
    public void testOutput() {

        while(max.size()>0) {
            System.out.println(max.remove());
        }

        System.out.println();

        while(min.size()>0) {
            System.out.println(min.remove());
        }
        System.out.println();
    }

    @Test
    public void testSize() {
        assertEquals("empty size",0,empty.size());
        assertEquals("max size",5,max.size());
        assertEquals("min size",11,min.size());
    }

    @Test
    public void testRemove() {

        try{
            empty.remove();
        }catch(NoSuchElementException e){
            //normal
        }

        assertEquals( "remove max",new Integer(4),max.remove());
        assertEquals("remove min",new Integer(1),min.remove());

        while(max.size()>0) {
            System.out.println(max.remove());
        }

        System.out.println();

        while(min.size()>0) {
            System.out.println(min.remove());
        }
        System.out.println();
    }

    @Test
    public void testAdd() {

        try{
            min.add(null);
        }catch(NullPointerException e) {
            //normal
        }

        min.add(12);
        min.add(3);
        while(min.size()>0) {
            System.out.println(min.remove());
        }
    }

}

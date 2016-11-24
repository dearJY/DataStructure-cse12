
import org.junit.*;

import java.util.*;

import static org.junit.Assert.*;

public class HashTableTester {

    private HashTable a;
    private HashTable b;
    private HashTable c;
    @Before
    public void setup() {
        a = new HashTable(5);
        for(int i=0;i<5;i++) {
            a.insert(""+i);
        }
        b = new HashTable(5,"hashTable1.txt");
        for(int i=0;i<5;i++) {
            b.insert(""+i);
        }
        c = new HashTable(10);

    }

    @Test
    public void testGetSize() {
        assertEquals("number of elements 5",5,a.getSize());
    }
    @Test
    public void testInsert() {
        assertTrue("insert 5",a.insert("5"));
        assertEquals("number of elements 6",6,a.getSize());
        assertFalse("insert 5 twice",a.insert("5"));
        try {
            a.insert(null);
            fail("should throw exception");
        } catch(NullPointerException e) {
            //normal
        }
    }

    @Test
    public void testLookup() {
        assertTrue("lookup 1",a.lookup("1"));
        assertFalse("lookup 6",a.lookup("6"));
        try {
            a.lookup(null);
            fail("should throw exception");
        } catch(NullPointerException e) {
            //normal
        }
    }

    @Test
    public void testDelete() {
        assertTrue("delete 1",a.delete("1"));
        assertEquals("number of elements 4",4,a.getSize());
        assertFalse("delete 6",a.delete("6"));
        try {
            a.delete(null);
            fail("should throw exception");
        } catch(NullPointerException e) {
            //normal
        }
    }

    @Test
    public void testPrintTable() {
       c.insert("the");
        c.insert("quick");
        c.insert("brown");
        c.insert("fox");
        c.insert("jumps");
        c.insert("over");
        c.insert("the");
        c.insert("lazy");
        c.insert("dog");

    }


}

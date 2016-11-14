

import org.junit.*;

import java.util.*;

import static org.junit.Assert.*;

public class BSTreeTester {

    private BSTree<Integer> a;
    private BSTree<Integer> b;

    @Before
    public void setUp() {

        a = new BSTree<>();
        b = new BSTree<>();

        a.insert(10);
        a.insert(12);
        a.insert(8);
        a.insert(11);
        a.insert(13);
        a.insert(9);
        a.insert(7);

    }

    @Test
    public void testGetRoot() {

        assertEquals("get root", new Integer(10),a.getRoot().getKey());
    }

    @Test
    public void testSize() {

        assertEquals("get size",7,a.getSize());
    }

    @Test
    public void testNullInsert() {

        try {
            a.insert(null);
            fail("Should have generated an exception");
        }catch(NullPointerException e) {
            //normal
        }
    }

    @Test
    public void testFindKey() {

        assertTrue("find 11",a.findKey(11));
        assertFalse("find 1", a.findKey(1));

        try{
            a.findKey(null);
            fail("Should have generated an exception");
        }catch(NullPointerException e) {
            //normal
        }
    }

    @Test
    public void testInsertInfo() {

        a.insertInformation(10,1);
        assertEquals("add info to node 10",new Integer(1),a.getRoot().getRelatedInfo().peekLast());

        try{
            a.insertInformation(null,0);
            fail("Should have generated an exception");
        }catch(NullPointerException e) {
            //normal
        }

        try{
            a.insertInformation(11,null);
            fail("Should have generated an exception");
        }catch(NullPointerException e) {
            //normal
        }

        try{
            a.insertInformation(1,0);
            fail("Should have generated an exception");
        }catch(IllegalArgumentException e) {
            //normal
        }

    }

    @Test
    public void testMoreInfo() {

        try{
            a.findMoreInformation(null);
            fail("Should have generated an exception");
        }catch(NullPointerException e) {
            //normal
        }

        assertEquals("find info of root",new LinkedList<Integer>(),a.findMoreInformation(10));
        a.insertInformation(12,1);
        assertEquals("find info of 12",new Integer(1),a.findMoreInformation(12).peekLast());

    }

    @Test
    public void testInorderTraverse() {

        Integer[] bArr = new Integer[7];
        try{
            b.inorderTraversal(bArr);
            fail("Should have generated an exception");
        }catch(NullPointerException e) {
            //normal
        }

        Integer[] aArr = new Integer[7];
        a.inorderTraversal(aArr);
        for(int i=7;i<14;i++) {
            assertEquals("check" + i,new Integer(i),aArr[i-7]);
        }
    }

    @Test
    public void testHeight() {

        assertEquals("test height1",2,a.findHeight());

        a.insert(14);
        a.insert(15);
        assertEquals("test height2",4,a.findHeight());
    }

    @Test
    public void testLeaf() {

        assertEquals("test leaf1",4,a.leafCount());

        a.insert(14);
        a.insert(6);
        assertEquals("test leaf2",4,a.leafCount());
    }
}


package hw2;

import java.util.*;

/**
* define DoublyLinkedList
*/
public class DoublyLinkedList12<E> extends AbstractList<E> {
  
  private int nelems;
  private Node head;
  private Node tail;
  
  protected class Node {
	  
    E data;
    Node next;
    Node prev;
    
    /** Constructor to create singleton Node */
    public Node(E element)
    {
        data = element;

    }

    /** Constructor to create singleton link it between previous and next 
      *   @param element Element to add, can be null
      *   @param prevNode predecessor Node, can be null
      *   @param nextNode successor Node, can be null 
      */
    public Node(E element, Node prevNode, Node nextNode)
    {
        data = element;
        prev = prevNode;
        next = nextNode;

    }
    /** Remove this node from the list. Update previous and next nodes */
    public void remove()
    {
        prev.next = this.next;
        next.prev = this.prev;
        this.next = null;
        this.prev = null;// Do I have to write this???

    }
    /** Set the previous node in the list
      *  @param p new previous node
      */
    public void setPrev(Node p)
    {
        Node temp = this.prev;
        temp.next = p;
        p.next = this;
        this.prev = p;
        p.prev = temp;

    }
    /** Set the next node in the list
      *  @param n new next node
      */
    public void setNext(Node n)
    {
        Node temp = this.next;
        this.next = n;
        n.next = temp;
        temp.prev = n;
        n.prev = this;

    }
    
    /** Set the element 
      *  @param e new element 
      */
    public void setElement(E e)
    {
        this.data = e; // ??

    }

    /** Accessor to get the next Node in the list */
    public Node getNext()
    {
        return this.next; // XXX-CHANGE-XXX

    }

    /** Accessor to get the prev Node in the list */
    public Node getPrev()
    {
        return this.prev; // XXX-CHANGE-XXX

    }

    /** Accessor to get the Nodes Element */
    public E getElement()
    {
        return this.data; // XXX-CHANGE-XXX

    }

  }

  /** ListIterator implementation */
  protected class MyListIterator implements ListIterator<E> {

      private boolean forward;
      private boolean canRemove;
      private Node left,right; // Cursor sits between these two nodes
      private int idx;        // Tracks current position. what next() would return

      /** constructor of MyListIterator */
      public MyListIterator()
      {
          if( head.next == tail ) {
              forward = false;
              canRemove = false;
          }else {
              forward = true;
              canRemove = false;
          }

          left = head;
          right = head.next;
      }

      /**
       * Adds an element to the list. The element is inserted between the left and right pointers,
       * that is, immediately before the node that would be retrieved by next()
       * and immediately after the node that would be retrieved by previous()
       * @param e
       * @throws NullPointerException
       */
      @Override
      public void add(E e) throws NullPointerException {

          if( e == null ) {
              throw new NullPointerException();
          }

          Node a = new Node(e);
          right.setPrev(a);
          left = a;
          idx++;
          canRemove = false;

      }

      /**
       * Checks if there is another element to be retrieved by calling next
       * @return boolean
       */
      @Override
      public boolean hasNext() {

          if( right == tail ) {
              return false;
          }else return true;
      }

      /**
       * Checks if there is another element to be retrieved by calling previous
       * @return boolean
       */
      @Override
      public boolean hasPrevious() {

          if( left == head ) {
              return false;
          }
          return true;

      }

      /**
       * Advances through the list by one index, and retrieves the next element
       * @return E
       * @throws NoSuchElementException
       */
      @Override
      public E next() throws NoSuchElementException {

          if( right == tail ) {
              throw new NoSuchElementException();
          }
          left = right;
          right = right.next;
          idx++;
          canRemove = true;
          forward = true;
          return left.data;


      }

      /**
       * Retrieves the index of the next element (that would be retrieved by next() call)
       * @return int
       */
      @Override
      public int nextIndex() {

          return idx;

      }

      /**
       * Retreats through the list by one index, and retrieves the previous element
       * @return E
       * @throws NoSuchElementException
       */
      @Override
      public E previous() throws NoSuchElementException {

          if( left == head ) {
              throw new NoSuchElementException();
          }
          right = left;
          left = left.prev;
          idx--;
          canRemove = true;
          forward = false;
          return right.data;

      }

      /**
       * Retrieves the index of the previous element (that would be retrieved by previous() call)
       * @return int
       */
      @Override
      public int previousIndex() {

          return idx-1;

      }

      /**
       * Removes from the list the last element that was returned by next() or previous()
       * @throws IllegalStateException
       */
      @Override
      public void remove() throws IllegalStateException {

          if( !canRemove ) throw new IllegalStateException();

          if( forward ) {
              left.prev.next = right;
              right.prev = left.prev;
              Node pre = left.prev;
              left.prev = null;
              left.next = null;
              left = pre;
              idx--;
          }else {
              Node next = right.next;
              Node pre = left;
              next.prev = pre;
              pre.next = next;
              right.prev = null;
              right.next = null;
              right = next;
          }

          canRemove = false;

      }

      /**
       * Replaces the last element returned by next() or previous() with a given element.
       * @param e
       * @throws NullPointerException
       * @throws IllegalStateException
       */
      @Override
      public void set(E e)
              throws NullPointerException, IllegalStateException {

          if( e == null ) {
              throw new NullPointerException();
          }

          if( !canRemove ) throw new IllegalStateException();

          if( forward ) {
              left.data = e;
          }else right.data = e;


      }
    
  }
  
  
  //  Implementation of the DoublyLinkedList12 Class
  
  
  /** Only 0-argument constructor is define */
  /**
  * Creates a new, empty doubly-linked list.
  */
  public DoublyLinkedList12()
  {
      head = new Node(null);
      tail = new Node(null);
      head.next = tail;
      tail.prev = head;

  }
  
  /**
  * Retrieves the amount of elements that are currently on the list.
  * 
  * @return Number of elements currently on the list
  */
  @Override
  public int size()
  {
    // need to implement the size method
    return nelems;

  }
  
  
   /**
     * Retrieves the element stored with a given index on the list.
     * 
     * @param index The index of the desired element.
     * @return The element stored in the Node with the desired index.
     * @throws IndexOutOfBoundsException if index received is out of bounds for 
     *             the current list. 
     */
  @Override
  public E get(int index) throws IndexOutOfBoundsException
  {
      if( index >= nelems || index<0 ) {
          throw new IndexOutOfBoundsException();
      }

      Node curr = this.getNth(index);
      return curr.data;

  }
  
  
 /**
     * Adds an element to a certain index in the list, shifting exist elements
     * create room. Does not accept null values.
     * 
     * @param index Where in the list to add the element.
     * @param data Data to be added.
     * @throws IndexOutOfBoundsException if index received is out of bounds for 
     *             the current list. 
     * @throws NullPointerException if data received is null.
     */
     @Override
    public void add(int index, E data) 
       throws IndexOutOfBoundsException,NullPointerException
    {
        if( data == null ) {
            throw new NullPointerException();
        }

        if( nelems == 0 && index !=0 ) {
            throw new IndexOutOfBoundsException();
        }

        if( nelems != 0 && index > nelems ) {
            throw new IndexOutOfBoundsException();
        }

        if( index<0 ) {
            throw new IndexOutOfBoundsException();
        }

        Node curr = getNth(index);
        Node a = new Node(data);
        curr.setPrev(a);
        nelems++;

    }

    /**
     * Add an element to the end of the list
     *
     * @param data data to be added
     * @throws NullPointerException if data received is null
     */
    public boolean add(E data) throws NullPointerException {

        if( data == null ) {
            throw new NullPointerException();
        }

        Node a = new Node(data);
        tail.setPrev(a);
        nelems++;
        return true;
    }

    /**
     * Sets the value of an element at a certain index in the list.
     *
     * @param index Where in the list the data should be added.
     * @param data  Data to add.
     * @return Element that was previously at this index.
     * @throws IndexOutOfBoundsException if index received is out of bounds for
     *                                   the current list.
     * @throws NullPointerException      if data received is null.
     */
    public E set(int index, E data)
            throws IndexOutOfBoundsException, NullPointerException {

        if( data == null ) {
            throw new NullPointerException();
        }

        if( index >= nelems || index<0 ) {
            throw new IndexOutOfBoundsException();
        }

        Node curr = this.getNth(index);
        E preE = curr.data;
        curr.setElement(data);

        return preE;

    }


    /**
     * remove the element from position i in this list.
     *
     * @param index Where in the list the data should be removed
     * @return Element that was removed
     * @throws IndexOutOfBoundsException if index received is out of bounds for
     *                                   the current list.
     */
    public E remove(int index) throws IndexOutOfBoundsException {

        if( index >= nelems || index<0 ) {
            throw new IndexOutOfBoundsException();
        }

        Node curr = this.getNth(index);
        E rem = curr.data;
        curr.remove();
        nelems--;

        return rem;
    }

    /**
     * Clear the linked list
     */
    public void clear() {
        head.next.prev = null;
        tail.prev.next = null;
        head.next = tail;
        tail.prev = head;
        nelems = 0;
    }

    /**
     * Determine if the list empty
     *
     * @return true if empty, false otherwise
     */
  public boolean isEmpty()
  {
      if( nelems == 0 ) {
          return true;
      }else return false;

  }

    /**
     * reverse and concatenate itself*/
    public void reverseAndConcat (DoublyLinkedList12<E> newList) {

        DoublyLinkedList12<E> copy = new DoublyLinkedList12<E>();

        for( int i=0;i< newList.size();i++ ) {
            copy.add(newList.get(i));
        }

        for( int i=newList.size()-1;i>=0;i-- ) {
            newList.add(copy.get(i));
        }

    }
  
  // Helper method to get the Node at the Nth index
  private Node getNth(int index) 
  {
      Node curr = this.head;
      for( int i = 0;i<=index;i++ ) {
          curr = curr.next;
      }
      return curr;

  }

   public Iterator<E> iterator()
   {
   return new MyListIterator();
   }
   public ListIterator<E> listIterator()
   {
   return new MyListIterator();
   }
}


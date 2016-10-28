/**
 * Name:Jingyi Ouyang
 * PID:A53108909
 */

//package hw4;

import java.util.*;

public class DoubleEndedLL<T> implements DoubleEndedLLInterface<T>{

    private int nelems;
    private Node head;
    private Node tail;

    protected class Node {

        T data;
        Node next;

        /** Constructor to create singleton Node */
        public Node(T element) {

            data = element;
        }

        /** Constructor to create singleton link it to nextNode
         *   @param element Element to add, can be null
         *   @param nextNode successor Node, can be null
         */
        public Node(T element, Node nextNode) {

            data = element;
            next = nextNode;
        }

        /** Set the next node in the list
         *  @param n new next node
         */
        public void setNext(Node n)
        {
            Node temp = this.next;
            this.next = n;
            n.next = temp;
        }

        /** Set the element
         *  @param e new element
         */
        public void setElement(T e)
        {
            this.data = e;

        }

        /** Accessor to get the next Node in the list */
        public Node getNext()
        {
            return this.next;

        }

        /** Accessor to get the Nodes Element */
        public T getElement()
        {
            return this.data;

        }

    }

    /**
     * non-arguments
     * Creates a new, empty singly-linked list.
     */
    public DoubleEndedLL() {

        head = new Node(null);
        tail = new Node(null);
        head.next = tail;
        nelems = 0;

    }


    /** Checks if the list is empty
     * @return returns true if the list is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {

        if( nelems == 0 ) {
            return true;
        }else return false;

    }

    /** Checks the size of the list
     * @return returns the number of elements in the list
     */
    @Override
    public int size() {

        return nelems;
    }


    /** Adds a new node to the front of the list
     * @param newItem - an element to add
     */
    @Override
    public void addFirst(T newItem) {

        Node newNode =  new Node(newItem);
        head.setNext(newNode);
        nelems++;

    }

    /** Adds a new node to the end of the list
     * @param newItem - an element to add
     */
    @Override
    public void addLast(T newItem) {

        Node dummy = new Node(null);
        tail.setElement(newItem);
        tail.setNext(dummy);
        tail = dummy;
        nelems++;

    }

    /** Removes a node from the beginning of the list
     * @return element the data found
     * @throws NullPointerException
     */

    public T removeFirst() throws NullPointerException{

        if(nelems!=0) {
            Node temp = head.next;
            head.next = temp.next;
            temp.next = null;
            nelems--;
            return temp.data;
        } else {
            throw new NullPointerException();
        }

    }


    /** Removes a node from the end of the list
     * @return element the data found
     * @throws NullPointerException
     */
    public T removeLast() throws NullPointerException{

        if (nelems != 0) {
            Node curr = this.head;
            for (int i = 0; i < size() - 1; i++) {
                curr = curr.next;
            }
            Node temp = curr.next;
            curr.next = tail;
            temp.next = null;
            nelems--;
            return temp.data;
        } else {
            throw new NullPointerException();
        }
    }

    /**
     * get the element in the position of index
     * @param index
     * @return T
     * @throws IndexOutOfBoundsException
     */
    public T get(int index) throws IndexOutOfBoundsException{

        if( index >= nelems || index<0 ) {
            throw new IndexOutOfBoundsException();
        }

        Node curr = this.head;
        for( int i = 0;i<=index;i++ ) {
            curr = curr.next;
        }
        return curr.data;

    }

}

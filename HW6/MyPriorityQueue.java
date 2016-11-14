

//package hw6

import java.util.*;

public class MyPriorityQueue<T extends Comparable <? super T>> {

    private int size;
    private dHeap<T> pq;

    /**
     * constructor
     * @param size
     */
    public MyPriorityQueue(int size) {
        this.size = size;
        pq = new dHeap<T>();
    }

    /**
     * add element into a priority queue
     * @param e
     * @throws NullPointerException
     */
    public void add(T e) throws NullPointerException {
        if(e == null) {
            throw new NullPointerException();
        }
        pq.add(e);
    }

    /**
     * remove a element from priority queue
     * @return T
     */
    public T poll() {
        if(pq.size() == 0) return null;
        return pq.remove();
    }
}

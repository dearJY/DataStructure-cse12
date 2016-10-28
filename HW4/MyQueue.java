

//package hw4

public class MyQueue<T> implements Stack_QueueInterface<T>{

    private int size;
    private int front;
    private int rear;
    private T[] queue;

    public MyQueue() {

        size = 10;
        front = 0;
        rear = 0;
        queue = (T[])new Object[size];
    }

    public MyQueue(int size) {

        this.size = size;
        front = 0;
        rear = 0;
        queue = (T[])new Object[size];
    }


    /** Tests if the storage is empty.
     * @return true a storage contains no items; false otherwise.
     */
    public boolean isEmpty() {

        if(front == rear) {
            return true;
        } else {
            return false;
        }
    }

    /** Adds an element to a storage
     * @param newItem - item to be added
     */
    public void addElement(T newItem) {

        if(rear - front == size) {
            largeQueue(queue);
        }
        queue[rear%size] = newItem;
        rear++;
    }

    /** Removes the object from the storage and returns
     * that object as the value of this function.
     * @return value of the removed object.
     */
    public T removeElement() {

        if(isEmpty()) {
            return null;
        }

        T rele = queue[front%size];
        front++;
        return rele;
    }

    /** Returns the size of the storage
     * @return the size of the storage
     */
    public int size(){

        return size;
    }

    private void largeQueue(T[] oldQueue) {

        int newSize = 2*size;
        T[] lQueue = (T[])new Object[newSize];
        for(int i=0;i<size;i++) {
            lQueue[i] = oldQueue[front%size];
            front++;
        }

        front = 0 ;
        rear = size;
        size = newSize;
        queue = lQueue;

    }

    public T getElement() {

        if(isEmpty()) {
            return null;
        }

        T rele = queue[front%size];
        return rele;

    }

}

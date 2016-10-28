

//package hw4;
public class MyStack<T> implements Stack_QueueInterface<T> {

    private DoubleEndedLL<T> stack =  new DoubleEndedLL();


    /** Tests if the storage is empty.
     * @return true a storage contains no items; false otherwise.
     */
    public boolean isEmpty() {

        return stack.isEmpty();
    }

    /** Adds an element to a storage
     * @param newItem - item to be added
     */
    public void addElement(T newItem) {

        stack.addFirst(newItem);
    }

    /** Removes the object from the storage and returns
     * that object as the value of this function.
     * @return value of the removed object.
     */
    public T removeElement() {

        return stack.removeFirst();
    }

    /** Returns the size of the storage
     * @return the size of the storage
     */
    public int size() {
        return stack.size();
    }

}

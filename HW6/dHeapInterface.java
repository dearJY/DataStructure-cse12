public interface dHeapInterface<T extends java.lang.Comparable<? super T>> {
    
    /**
     * Returns the number of elements stored in the heap.
     * 
     * @return The number of elements stored in the heap.
     */
    int size();
    
    /**
     * Removes and returns the smallest element stored in the heap. If the 
     * heap is empty, then this method throws a NoSuchElementException.
     * 
     * @return The smallest element stored in the heap.
     * @throws java.util.NoSuchElementException if the heap is empty
     */
    T remove() throws java.util.NoSuchElementException;
    
    /**
     * Adds the specified element to the heap; o cannot be null. 
     * Resizes the storage if full.
     * 
     * @param o The element to add.
     * @throws NullPointerException if o is null.
     */
    void add( T o ) throws NullPointerException;

}

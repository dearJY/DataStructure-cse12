

//package hw6

import java.util.*;


class dHeap <T extends Comparable <? super T>> implements dHeapInterface<T> {
		
	private boolean isMaxHeap = false;
	private T[] array;
	private int d = 2;
	private int size = 0;
	private T global;


	 /** O-argument constructor. Creates and empty dHeap with 
	 *  initial capacity = 5, and is a 2-min-heap 
	 */ 
	public dHeap()
	{
		array = (T[])new Comparable[5];
	}

	/** 
	 * Constructor to build a min or max dheap
	 * @param isMaxHeap  if true, this is a 2-max-heap, else a 2-min-heap 
	 * with initial size = 'capacity'
	 */ 
	public dHeap(int capacity, boolean isMaxHeap)
	{
		array = (T[])new Comparable[capacity];
		this.isMaxHeap = isMaxHeap;
	}

	/** 
	 * Constructor to build a with specified initial capacity and
	 * given number of children d. 
	 * @param capacity initial capacity of the heap.	
	 * @param isMaxHeap if true, this is a max-heap, else a min-heap 
	 * @param d number of children, 
	 * @exception if d is less than one, throw IllegalArgumentException();
	 */ 
	public dHeap(int capacity, boolean isMaxHeap, int d)
	{
		array = (T[])new Comparable[capacity];
		this.isMaxHeap = isMaxHeap;
		if (d<1){
			throw new IllegalArgumentException();
		}
		this.d = d;
	}

	/**
	 * the number of elements
	 * @return int
	 */
	public int size () {
		return this.size;
	}

	/**
	 * add an element into a Heap
	 * @param o The element to add.
	 * @throws NullPointerException
	 */
	public void add( T o ) throws NullPointerException{

		if(o == null) throw new NullPointerException();
		if (size == array.length){
			this.resize();
		}
		if(global == null || o.compareTo(global)>0) {
			global = o;
		}
		array[size] = o;
		bubbleUp(size);
		size++;

	}

	/**
	 * remove an element from heap
	 * @return T
	 * @throws NoSuchElementException
	 */
	public T remove() throws NoSuchElementException {
		if (size<=0) throw new NoSuchElementException();
		T res = array[0];
		array[0] = array[size-1];
		size--;
		trickleDown(0);
		return res;
	}

	/**
	 * helper method for comparison
	 * decide using bubble up or trickle down
	 * @param a
	 * @param b
	 * @return int
	 */
	private int compareHelper(T a, T b){
		if (isMaxHeap){
			return b.compareTo(a);
		}else{
			return a.compareTo(b);
		}
	}

	/**
	 * helper function
	 * trickle down an element
	 * @param index
	 */
	private void trickleDown(int index){
		int j = index;
		while (j<array.length){
			int swapIndex = j;
			for (int i=d*j+1;i<=j*d+d && i<size;i++){
				if (this.compareHelper(array[swapIndex],array[i])>0){
					swapIndex = i;
				}
			}
			if (swapIndex == j){
				break;
			}else{
				T tmp = array[swapIndex];
				array[swapIndex] = array[j];
				array[j] = tmp;
				j = swapIndex;
			}
		}
	}

	/**
	 * helper function
	 * bubble up an element
	 * @param index
	 */
	private void bubbleUp(int index){
		int j = index;
		int parent = (j-1)/d;
		while (this.compareHelper(array[parent], array[j]) > 0) {
			T tmp = array[parent];
			array[parent] = array[j];
			array[j] = tmp;
			j = parent;
			parent = (j-1)/d;
		}
	}

	/**
	 * double size
	 */
	private void resize(){
		T[] newArray = (T[])new Comparable[size*2];
		System.arraycopy(array,0,newArray,0,size);
		array = newArray;
	}

	/**
	 * find max in min-heap
	 * extra credits
	 * @return T
	 */
	public T findMaxinMin() {
		if(this.size()==0) {
			return null;
		}else return global;
	}

	public LinkedList<T> findLessThanK(T k) {
		LinkedList<T> res = new LinkedList<T>();
		findLessThanKHelper(0,res,k);
		return res;
	}

	private void findLessThanKHelper(int index, LinkedList<T> res, T k){
		if (index >= size || array[index].compareTo(k)>0){
			return;
		}

		res.add(array[index]);
		for (int i=d*index+1;i<=index*d+d && i<size;i++){
			findLessThanKHelper(i,res,k);
		}
	}

	public static void main(String args[]) {
		dHeap<Integer> min =new dHeap(20,false,2);
		for(int i=0; i<20;i++){
			min.add(i);
		}
		System.out.println(min.findMaxinMin());
		System.out.println(min.findLessThanK(10).toString());
	}

}

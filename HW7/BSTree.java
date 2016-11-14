

//package hw7;

import java.util.LinkedList;
import java.util.Queue;

public class BSTree<T extends Comparable<? super T>>{

	private int nelems;
	private BSTNode root;
	private int count = 0;
	private int leaf = 0;

	 protected class BSTNode{

		T key;
		LinkedList<T> relatedInfo = new LinkedList<>();
		BSTNode lChild;
		BSTNode rChild;

		 /**
		  * Constructor 1 of BSTNode
		  * @param lChild
		  * @param rChild
		  * @param relatedInfo
		  * @param key
		  */
		 public BSTNode(BSTNode lChild, BSTNode rChild, LinkedList<T> relatedInfo, T key) {

			 this.key = key;
			 this.relatedInfo = relatedInfo;
			 this.lChild = lChild;
			 this.rChild = rChild;
		 }

		 /**
		  * Constructor 2 of BSTNode
		  * @param lChild
		  * @param rChild
		  * @param key
		  */
		 public BSTNode(BSTNode lChild, BSTNode rChild, T key) {

			 this.key = key;
			 this.lChild = lChild;
			 this.rChild = rChild;
		 }

		 /**
		  * get key
		  * @return T
		  */
		 public T getKey() {

			 return key;
		 }

		 /**
		  * get left Child
		  * @return BSTNode
		  */
		 public BSTNode getLChild() {

			 return lChild;
		 }

		 /**
		  * get right Child
		  * @return BSTNode
		  */
		 public BSTNode getRChild() {

			 return rChild;
		 }

		 /**
		  * get related info
		  * @return linkedlist
		  */
		 public LinkedList<T> getRelatedInfo() {

			 return relatedInfo;
		 }

		 /**
		  * set value of left child
		  * @param newLChild
		  */
		 public void setLChild( BSTNode newLChild) {

			 this.lChild = newLChild;
		 }

		 /**
		  * set value of right child
		  * @param newRChild
		  */
		 public void setRChild( BSTNode newRChild) {

			 this.rChild = newRChild;
		 }

		 /**
		  * set related info
		  * @param newInfo
		  */
		 public void setRelatedInfo( LinkedList<T> newInfo) {

			 this.relatedInfo = newInfo;
		 }

		 /**
		  * add info to related info
		  * @param info
		  */
		 public void addNewInfo(T info) {

			 if(info == null) {
				 throw new NullPointerException();
			 }

			 relatedInfo.add(info);
		 }

		 /**
		  * remove info from related info
		  * @param info
		  * @return
		  */
		 public boolean removeInfo(T info) {

			 if(info == null) {
				 throw new NullPointerException();
			 }

			 if(relatedInfo.size() == 0) {
				 return false;
			 }else if(relatedInfo.contains(info)) {
				 relatedInfo.remove(info);
				 return true;
			 } else {
				 return false;
			 }

		 }
	}

	/**
	 * constructor of BSTree
	 */
	public BSTree() {

		nelems = 0;
		root = null;
	}

	/**
	 * get the root of BSTree
	 * @return BSTNode
	 */
	public BSTNode getRoot() {

		return this.root;
	}

	/**
	 * get number of elements
	 * @return
	 */
	public int getSize() {

		return nelems;
	}

	/**
	 * add a new node into BSTree
	 * @param key
	 */
	public void insert(T key) {

		if(key == null) {
			throw new NullPointerException();
		}

		//call helper function
		insertHelper(this.root,key);
		nelems++;

	}

	/**
	 * find a key in a BSTree
	 * @param key
	 * @return
	 */
	public boolean findKey(T key) {

		if(key == null) {
			throw new NullPointerException();
		}

		// call helper function
		return findKeyHelper(this.root,key);

	}

	/**
	 * insert information into linkedlist of node with key
	 * @param key
	 * @param info
	 */
	public void insertInformation(T key, T info) {

		if(key == null || info  == null) {
			throw new NullPointerException();
		}

		insertInfoHelper(this.root,key,info);

	}

	/**
	 * Return the LinkedList of the node with key
	 * @param key
	 * @return
	 */
	public LinkedList<T> findMoreInformation(T key) {

		if(key == null) {
			throw new NullPointerException();
		}

		return findInfoHelper(this.root,key);

	}

	/**
	 * in-order traversing BSTree
	 * @param keyArray
	 */
	public void inorderTraversal(T[] keyArray) {

		if(getSize() == 0) {
			throw new NullPointerException();
		}

		traverseHelper(this.root,keyArray);

	}

	/**
	 * find the height of BSTree
	 * @return int
	 */
	public int findHeight() {

		return findHeightHelper(this.root);
	}

	/**
	 * find the number of leaves
	 * @return
	 */
	public int leafCount() {
		return findLeaf(this.root);
	}


	public boolean isMirror(BSTree tree2){
		if (tree2 == null) return false;
		return isMirrorHelper(this.root,tree2.getRoot());
	}

	private boolean isMirrorHelper(BSTNode one, BSTNode two){
		if (one == null && two == null){
			return true;
		}else if (one == null || two == null || !one.key.equals(two.key)){
			return false;
		}else{
			return isMirrorHelper(one.getLChild(),two.getRChild()) && isMirrorHelper(one.getRChild(),two.getLChild());
		}
	}


	public void levelOrderTraversal(T[] keyArray){
		if (this.root == null) return;
		int index = 0;
		Queue<BSTNode> queue =new LinkedList<BSTNode>();
		queue.offer(this.root);
		while (!queue.isEmpty()){
			int size = queue.size();
			for (int i=0;i<size;i++){
				BSTNode cur = queue.poll();
				keyArray[index++] = cur.key;
				if (cur.lChild != null){
					queue.offer(cur.lChild);
				}
				if (cur.rChild != null){
					queue.offer(cur.rChild);
				}
			}
		}
	}

	/**
	 * helper function of insert method
	 * @param curr
	 * @param key
	 */
	private void insertHelper(BSTNode curr, T key) {

		//create a new BSTNode to finally add into BSTree
		BSTNode newChild = new BSTNode(null, null, key);

		if(this.root == null) {
			this.root= newChild;
			return;
		}

		if (curr.getKey().compareTo(key) == 0) { //base case1: find the key then not add

			return;

			//new node needs to be added to the right brance because it's greater than curr
		}else if (curr.getKey().compareTo(key)>0) {

			//base case 2: left child is null providing place for new node
			if(curr.getLChild() == null) {
				curr.setLChild(newChild);
			}else {
				insertHelper(curr.getLChild(),key);//continue looking for appropriate place to insert
			}

			//new node needs to be added to the left brance because it's greater than curr
		}else {

			//base case 3: right child is null providing place for new node
			if(curr.getRChild() == null) {
				curr.setRChild(newChild);
			}else {
				insertHelper(curr.getRChild(),key);
			}
		}
	}

	/**
	 * helper function of findKey method
	 * @param curr
	 * @param key
	 * @return
	 */
	private boolean findKeyHelper(BSTNode curr, T key) {

		//base case1: no node found with key value
		if(curr == null) {
			return false;
		}

		if(curr.getKey().compareTo(key)<0) {

			//key goes to right branch because it's greater than curr
			return findKeyHelper(curr.getRChild(),key);
		}else if (curr.getKey().compareTo(key)>0) {
			return findKeyHelper(curr.getLChild(),key);
		}else {
			return true;
		}
	}

	/**
	 * helper function of insertInfo method
	 * @param curr
	 * @param key
	 * @param info
	 */
	private void insertInfoHelper(BSTNode curr, T key,T info) {

		BSTNode keyNode = findKeyNode(curr,key);
		keyNode.addNewInfo(info);
	}

	/**
	 * helper function of findInformation method
	 * @param curr
	 * @param key
	 * @return
	 */
	private LinkedList<T> findInfoHelper(BSTNode curr, T key) {


		BSTNode keyNode = findKeyNode(curr,key);
		if (keyNode == null){
			return null;
		}
		return keyNode.getRelatedInfo();

	}

	/**
	 * helper function to return BSTNode with key
	 * @param curr
	 * @param key
	 * @return
	 */
	private BSTNode findKeyNode(BSTNode curr, T key) {

		//base case1: no node found with key value
		if(curr == null) {
			return null;
		}


		if(curr.getKey().compareTo(key)<0) {

			//key goes to right branch because it's greater than curr
			return findKeyNode(curr.getRChild(),key);
		}else if (curr.getKey().compareTo(key)>0) {
			return findKeyNode(curr.getLChild(),key);
		}else {
			//return the node with key
			return curr;
		}
	}

	/**
	 * helper function of inodertraverse
	 * @param curr
	 * @param keyArray
	 */
	private void traverseHelper(BSTNode curr, T[] keyArray) {

		if(curr != null) {
			traverseHelper(curr.getLChild(),keyArray);
			keyArray[count] = curr.getKey();
			count++;
			traverseHelper(curr.getRChild(),keyArray);
		}
	}

	/**
	 * helper function of findHeight method
	 * @param curr
	 * @return
	 */
	private int findHeightHelper(BSTNode curr) {

		if(curr == null) {
			return -1;
		}

		int left = findHeightHelper(curr.getLChild());
		int right = findHeightHelper(curr.getRChild());

		if(left>right) {
			return left+1;
		}else {
			return right+1;
		}
	}

	/**
	 * helper function of leafcount method
	 * @param curr
	 * @return int
	 */
	private int findLeaf(BSTNode curr) {

		if(nelems == 0) {
			return 0;
		}

		if(curr.getLChild() == null && curr.getRChild() == null) {
			return 1;
		}else if(curr.getLChild() == null) {
			return findLeaf(curr.getRChild());
		}else if(curr.getRChild() == null) {
			return findLeaf(curr.getLChild());
		} else {
			 return findLeaf(curr.getLChild()) + findLeaf(curr.getRChild());
		}

	}
}

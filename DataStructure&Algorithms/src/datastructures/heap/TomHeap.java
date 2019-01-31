package datastructures.heap;

public class TomHeap {

	/*
	 * Heap Can be 
	 * 1. Max Heap --> highest value is at root of tree
	 * 2. Min Heap --> lowest value is at the root of the tree
	 * 
	 * Heap Shape property
	 * 1. All leaves are either at depth d or d-1 (for some value d).
	 * 2. All of the leaves at depth d-1 are to the right of the leaves at depth d.
	 * 3. (a) There is at most 1 node with just 1 child. (b) That child is the left child of its parent, and (c) it is the rightmost leaf at depth d.
	 * 
	 * Link http://pages.cs.wisc.edu/~vernon/cs367/notes/11.PRIORITY-Q.html
	 * 
	 * 
	 * Useful when traversing array representing heap...
	 * • Its parent is (x-1) / 2.
	 * • Its left child is 2*x + 1.
	 * • Its right child is 2*x + 2.
	 * 
	 * • A heap offers removal of the largest item, and insertion, in O(N*logN) time.
	 * 
	 * What does the term complete mean when applied to binary trees?
	 * A: All the rows are filled with nodes, except possibly the bottom one.
	 * 
	 * What does the term weakly ordered mean when applied to heaps?
	 * A: Both the right and left children have keys less than (or equal to) the parent.
	 * 
	 *  To “trickle up” a node in a descending heap means
	 *  A: to repeatedly exchange it with its parent until it’s larger than its parent.
	 * 
	 * A heap can be represented by an array because a heap
	 * A: because its complete 
	 * 
	 * 
	 * 
	 */
	
	
	
	
	private TomHeapNode[] heapArray;
	private int maxSize; //size of array 
	private int currentSize; //number of nodes in the array
	
	public TomHeap(int mx) {
		this.maxSize = mx;
		this.currentSize = 0; 
		this.heapArray= new TomHeapNode[maxSize];
	}
	
	public boolean insert(int key) {
		if(currentSize == maxSize) {
			return false;
		}
		TomHeapNode newNode = new TomHeapNode(key);
		heapArray[this.currentSize] = newNode;
		trickleUp(this.currentSize++);
		return true;
	}
	
	public void trickleUp(int index) {
		
		int parent = (index - 1 )/ 2; //get parent index of new inserted node
		
		TomHeapNode bottom = heapArray[index];
		
		while(index > 0 && heapArray[parent].data < bottom.data) {
			
			heapArray[index] = heapArray[parent];
			index = parent;
			parent = (parent - 1 ) / 2;
		}
		
		heapArray[index] = bottom; 
		
	}
	
	//Delete Root of array (item with max key)
	public TomHeapNode remove() {
		TomHeapNode rootToRemove = heapArray[0];
		heapArray[0] = heapArray[--currentSize]; // assign last variable in array to root then decrease size; 
		trickleDown(0);
		return rootToRemove;
	}
	
	public void trickleDown(int index) {
		int largerChild; 
		TomHeapNode root = heapArray[index];
		
		//while node has atleast 1 child 
		while(index < currentSize/2) { 
			int leftChild = 2*index+1; 
			int rightChild = leftChild+1; 
			
			//find larger child 
			if(rightChild < currentSize && heapArray[leftChild].data < heapArray[rightChild].data) {
				
				largerChild = rightChild;
				
			}else {
				largerChild = leftChild;
			}
			
			if ( root.data >= heapArray[largerChild].data) {
				break;
			}
			heapArray[index] = heapArray[largerChild];
			index = largerChild;       
		}
		
		heapArray[index] = root;
		
	}
	
	public boolean change(int index, int newValue) {
		if(index < 0 || index >= currentSize) {
			return false;
		}
		//remember old
		int oldValue = heapArray[index].data;
		heapArray[index].data = newValue;
		
		if(oldValue < newValue) {
			trickleUp(index);
		}else {
			trickleDown(index);
		}
		return true; 
	}
	
}

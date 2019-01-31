package datastructures.heap;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class HeapAlgorithms {

	/* Convert Min Heap to Max Heap */ 
	
	public static void convertMaxHeap(int[] minArr, int n) {
		//traverse through all nodes with children starting with right most internal node (last node with children) 
		for (int i = (n-2)/2; i>= 0; --i) {
			maxHeapify(minArr, i, n);
		}
		
	}
	/*
	 * variable i is the root of subtree 
	 * maxHeapify is checking to see if the subtree is a heap --> modify it necessarily by swapping root with children if needed and trickle down till all subtress are heaps 
	 * as you recurse the function i is the index of the node that got swapped 
	 */
	
	public static void maxHeapify(int[] minArr, int i, int n) {
		/*
		 * Find children of root index 
		 */
		int l = 2*i + 1;
		int r = 2*i + 2;
		int largest = i;
		
		//need to do extra check to make sure that left and right are not out of index of arr 
		//find which index is largest in subtree 
		if(l < n && minArr[l] > minArr[i]) {
			largest = l;
		}
		if(r < n && minArr[r] > minArr[largest]) {
			largest = r;
		}
		
		//subtree is already a heap so no need to do any swaps (if largest == i) 
		if(largest != i) {
			int temp = minArr[i];
			minArr[i] = minArr[largest];
			minArr[largest] = temp;
			maxHeapify(minArr, largest, n);
		}
		
		
	}

	public static void main(String[] args) {
//		int arr[] = {3, 5, 9, 6, 8, 20, 10, 12, 18, 9}; 
//        int n = arr.length; 
//        
//        convertMaxHeap(arr, n);
//        
//        System.out.println(arr);
		
		

		
	}
	
	
	
}

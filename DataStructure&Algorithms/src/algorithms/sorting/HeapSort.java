package algorithms.sorting;

public class HeapSort {

	/*
	 * Run Time = O(NLogN) 
	 */
	
	public static void sort(int[] array){
		buildMaxHeap(array, array.length);
		int heapSize = array.length;
		for(int i = array.length - 1; i > 0; i--){
			swap(array, 0, i);
			heapSize = heapSize - 1; 
			maxHeapify(array, 0, heapSize);
		}
		
	}
	
	private static void buildMaxHeap(int[] array, int heapSize){
		int lastElementIndex = array.length -1;
		int parentIndex = (lastElementIndex - 1)/2;
		for(int i = parentIndex; i>=0; i--){
			maxHeapify(array, i, heapSize);
		}
		
		
	}
	
	private static void maxHeapify(int[] array, int currentIndex, int heapSize){
		int left = 2*currentIndex + 1;
		int right = 2*currentIndex +2; 
		int largest = currentIndex;
		
		if(left < heapSize && array[left] > array[currentIndex]){
			largest = left;
		}
		if(right < heapSize && array[right] > array[largest]){
			largest = right;
		}
		if(largest!=currentIndex){
			swap(array, currentIndex, largest);
			maxHeapify(array, largest, heapSize);
		}
	}
	
	private static void swap(int[] array, int i, int j) {
		int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}
	
	
	public static void main(String[] args){
		int[] arr = new int[]{12, 35, 87, 15, 26, 9, 28, 7};
		HeapSort.sort(arr);
	}
}

package algorithms.sorting;

public class SelectionSort {

	//Run Times
	//Best, Avg, Worst - O(n^2)
	public static void sort(int[] array){
		 if(array == null || array.length < 2) {
			 return;
		 }
		
		 int n = array.length;
		 
		 for(int i = 0; i < n-1; i++){
			 int minIndex = i;
			 for (int j = i+1; j < n; j++){
				 if(array[minIndex] > array[j]){
					 minIndex = j;
				 }
			 }
			 if(i != minIndex && array[i] != array[minIndex]){
				 int temp = array[i];
				 array[i] = array[minIndex];
				 array[minIndex] = temp;
			 }
			 
		 }
		 
		
	}
	
}

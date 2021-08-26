package algorithms.sorting;

import java.util.concurrent.TimeUnit;

public class BubbleSort {

	// Run Times 
	// Best Case - O(n)
	// Average - O(n^2)
	public void sort(int[] array){
		if(array == null || array.length < 2){
			return;
		}
		
		int n = array.length;
		boolean swapped = true; 
		
		while(swapped){
			swapped = false; 
			for(int i = 1; i < n; i++){
				if(array[i-1] > array[i]){
					int temp = array[i-1];
					array[i-1] = array[i];
					array[i]= temp;
					swapped = true;
				}
			}
			n--;
		}
		
	}
	
	public void sortPractice(int[] array) throws Exception{
		
		if(array == null || array.length < 2){
			return;
		}
		
		int n = array.length; 
		
		while(n > 1){
			for(int i = 0; i < n-1; i++){
				int nextPosition = i + 1; 
				if (array[i] > array[nextPosition]){
					int temp = array[i];
					array[i] = array[nextPosition];
					array[nextPosition] = temp;
				}
				
				
			}
			n--;
			
		}
		throw new Exception();
		
		
		
		
		
	}
	
	
	public static void main(String[] args){
//		int[] arr = {12, 3, 26, 9, 11, 4, 1}; 
//		BubbleSort bs = new BubbleSort();
//		bs.sortPractice(arr);
//		System.out.println("Sorted array: "  + Arrays.toString(arr));

		
		double aa = Math.log(4)/Math.log(2);
		double bb = (long) 1- Math.log(0.5);
		double bbb = Math.log(4)/bb;
		double cc = 4/aa;
		double dd = 4/bbb; 
		
		double ee = 4/(cc+dd);
		
		System.out.println(bb);
		

	}
	
}


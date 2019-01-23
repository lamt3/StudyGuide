package algorithms.array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArrayAlgorithms {

	public static int findMaxSubArray(int[] input){
		int maxSum = input[0];
		for(int i = 1; i< input.length; i++){
			if(input[i] > maxSum){
				maxSum = input[i];
			}
		}
		int start = 0;
		int end = 0;
		for(int window=2; window<=input.length; ++window){
			for(int i = 0; i <= input.length - window + 1; ++i){
				int endingIndex = window + i -1; 
				int currentSum = 0;
				for(int j = i; j<endingIndex; ++j){
					currentSum = currentSum + input[j];
				}
				if(currentSum> maxSum){
					start = i;
					end = endingIndex;
					maxSum = currentSum;
				}
				
			}
			
		}
		
		return maxSum;
	}
	
	public static int findMaximumSubArrayKandane(int[] input){
		int size = input.length; 
		int max = Integer.MIN_VALUE;
		int current = 0;
		
		for(int i = 0; i <size; i++){
			current = current + input[i];
			if(max < current){
				max = current;
			}
			if(current < 0){
				current = 0;
			}
		}
		return current;
	}
	
	public static List<Integer> findPeakElements(int[] input){
		List<Integer> peakElements = new ArrayList<Integer>();
		for(int i = 0; i < input.length; i++){
			if(i == 0){
				if(input[i] > input[i+1]){
					peakElements.add(input[0]);
				}
			}else if(i == input.length - 1){
				if(input[i] > input[i-1]){
					peakElements.add(input[i]);
				}
				
				
			}else{
				if(input[i] > input[i+1] && input[i] > input[i-1]){
					peakElements.add(input[i]);
				}
				
			}

		}		
		return peakElements;
		
	}
	
	public static Integer findPeakElementsEfficient(int[] input){
		List<Integer> peakElements = new ArrayList<Integer>();
		int n = input.length;
		int start = 0;
		int end = n -1;
		
		while(start<=end){
			int mid = (start + end)/2;
			if((mid==0||input[mid-1] <=input[mid]) && (mid == n - 1 || input[mid] >= input[mid+1])){
				return input[mid];
			}else if( mid > 0 && input[mid - 1] > input[mid]){
				end = mid - 1;
			}else{
				start = mid + 1;
			}
		}
		return null;
		
	}
	
	public static List<Integer> getLeaders(int[] input){
		List<Integer> leaders = new ArrayList<Integer>();
		int inputSize = input.length;
		int currentLeader = input[inputSize-1];
		leaders.add(currentLeader);
		for(int i = inputSize - 2; i >=0; i--){
			if(input[i] > currentLeader){
				currentLeader = input[i];
				leaders.add(currentLeader);
			}
		}
		
		return leaders;
	}
	
	public static Set<Integer> getDuplicates(int[] array){
		int n = array.length;
		Set<Integer> duplicates = new HashSet<Integer>();
		for(int i = 0; i < n; i++){
			if(array[Math.abs(array[i])] > 0){
				System.out.println(Math.abs(array[i]));
				System.out.println(-array[Math.abs(array[i])]);
				System.out.println(array[Math.abs(array[i])]);
				array[Math.abs(array[i])] = -array[Math.abs(array[i])];
			}else{
				duplicates.add(Math.abs(array[i]));
			}
		}
		return duplicates;
		
	}
	
	public static int getFibonacci(int n){
		int a = 0;
		int b = 1; 
		int c = a +b;
		
		for(int i = 2; i <= n; i++){
			c = a + b;
			a = b;
			b =c;
		}
		return c; 
		
	}
	
	public static void reArrangePositivesNegatives(int[] array){
		for(int i = 0; i < array.length; i++){
			if(isNotInRightPosition(i, array[i])){
				int nextOpoositeIndex = getNextOpposite(array, i);
				if(nextOpoositeIndex != -1){
					rotateSubarrayRightbyOneElement(array, i, nextOpoositeIndex);
				}else{
					break;
				}
			}
			
		}
		
		
	}
	
	private static void rotateSubarrayRightbyOneElement(int[] array, int low, int high){
		int lastElement = array[high];
		for(int i = high; i > low; i--){
			array[i] = array[i-1];
		}
		array[low] = lastElement;
	}
	
	private static int getNextOpposite(int[] array, int index){
		for(int i = index +1; i < array.length; i++){
			if((array[i] * array[index]) < 0){
				return i;
			}
		}
		return -1;
	}
	
	private static boolean isNotInRightPosition(int index, int value){
		if(index % 2 == 0){
			if(value < 0){
				return true;
			}else{
				return false;
			}
		}else{
			if(value<0){
				return false;
			}else{
				return true;
			}
		}		
	}
	
	public static void main(String[] args){
//		int[] arr = new int[]{98, 23, 54, 12, 20, 7, 27};
//		System.out.println(ArrayAlgorithms.findMaxSubArray(arr));
//		int a = ArrayAlgorithms.findPeakElementsEfficient(arr);
//		System.out.println(a);
//		List<Integer> a = ArrayAlgorithms.getLeaders(arr);
//		for(int i : a){
//			System.out.println(i);
//		}
		
		int[] arr = new int[]{-1, 1, -3, -12, -3, -7, -27};
		ArrayAlgorithms.reArrangePositivesNegatives(arr);
		for(int i : arr){
			System.out.println(i);
		}
		
		
	}
	
}

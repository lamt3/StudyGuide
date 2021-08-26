package algorithms.array;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class ArrayAlgos2 {

	public static int findEqual(int[] arr, int n) {

		int sum = 0; // initialize sum of whole array
		int leftsum = 0; // initialize leftsum

		/* Find sum of the whole array */
		for (int i = 0; i < n; ++i)
			sum += arr[i];

		for (int i = 0; i < n; ++i) {
			sum -= arr[i]; // sum is now right sum for index i

			if (leftsum == sum)
				return i;

			leftsum += arr[i];
		}

		/* If no equilibrium index found, then return 0 */
		return -1;

	}

	// https://www.geeksforgeeks.org/sliding-window-maximum-maximum-of-all-subarrays-of-size-k/
	static void printMax(int arr[], int n, int k) {
		// Create a Double Ended Queue, Qi that will store indexes of array elements
		// The queue will store indexes of useful elements in every window and it will
		// maintain decreasing order of values from front to rear in Qi, i.e.,
		// arr[Qi.front[]] to arr[Qi.rear()] are sorted in decreasing order
		Deque<Integer> Qi = new LinkedList<Integer>();

		/* Process first k (or first window) elements of array */
		int i;
		for (i = 0; i < k; ++i) {
			// For every element, the previous smaller elements are useless so
			// remove them from Qi
			while (!Qi.isEmpty() && arr[i] >= arr[Qi.peekLast()])
				Qi.removeLast(); // Remove from rear

			// Add new element at rear of queue
			Qi.addLast(i);
		}

		// Process rest of the elements, i.e., from arr[k] to arr[n-1]
		for (; i < n; ++i) {
			// The element at the front of the queue is the largest element of
			// previous window, so print it
			System.out.print(arr[Qi.peek()] + " ");

			// Remove the elements which are out of this window
			while ((!Qi.isEmpty()) && Qi.peek() <= i - k)
				Qi.removeFirst();

			// Remove all elements smaller than the currently
			// being added element (remove useless elements)
			while ((!Qi.isEmpty()) && arr[i] >= arr[Qi.peekLast()])
				Qi.removeLast();

			// Add current element at the rear of Qi
			Qi.addLast(i);

		}

		// Print the maximum element of last window
		System.out.print(arr[Qi.peek()]);
	}

	static class Interval {
		int buy, sell;
	}

	static class StockBuySell  
	{ 
	    // This function finds the buy sell schedule for maximum profit 
	    void stockBuySell(int price[], int n)  
	    { 
	        // Prices must be given for at least two days 
	        if (n == 1) 
	            return; 
	          
	        int count = 0; 
	  
	        // solution array 
	        ArrayList<Interval> sol = new ArrayList<Interval>(); 
	  
	        // Traverse through given price array 
	        int i = 0; 
	        while (i < n - 1)  
	        { 
	            // Find Local Minima. Note that the limit is (n-2) as we are 
	            // comparing present element to the next element.  
	            while ((i < n - 1) && (price[i + 1] <= price[i])) 
	                i++; 
	  
	            // If we reached the end, break as no further solution possible 
	            if (i == n - 1) 
	                break; 
	  
	            Interval e = new Interval(); 
	            e.buy = i++; 
	            // Store the index of minima 
	              
	  
	            // Find Local Maxima.  Note that the limit is (n-1) as we are 
	            // comparing to previous element 
	            while ((i < n) && (price[i] >= price[i - 1])) 
	                i++; 
	  
	            // Store the index of maxima 
	            e.sell = i-1; 
	            sol.add(e); 
	              
	            // Increment number of buy/sell 
	            count++; 
	        } 
	  
	        // print solution 
	        if (count == 0) 
	            System.out.println("There is no day when buying the stock "
	                                                  + "will make profit"); 
	        else 
	            for (int j = 0; j < count; j++) 
	                System.out.println("Buy on day: " + sol.get(j).buy 
	                        +"        " + "Sell on day : " + sol.get(j).sell); 
	          
	        return; 
	    }
	}
	  
	
	
	
	public static void main(String[] args) {
//		int arr[] = { -7, 1, 5, 2, -4, 3, 0 }; 
//		ArrayAlgos2.findEqual(arr, arr.length);
		
//		int arr[]={12, 1, 78, 90, 57, 89, 56}; 
//        int k=3; 
//        printMax(arr, arr.length,k);
        
        StockBuySell s = new StockBuySell();
        int[] prices = {100, 180, 260, 310, 40, 535, 695}; 
        s.stockBuySell(prices, prices.length);
		
	}
	

}


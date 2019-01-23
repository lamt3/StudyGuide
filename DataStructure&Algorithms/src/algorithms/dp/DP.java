package algorithms.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DP {

	// 1. Min Cost Problem
	public static int minCost(int[][] cost, int m, int n) {

		int i, j;
		int tc[][] = new int[m + 1][n + 1];
		tc[0][0] = cost[0][0];

		// initalize first column of total cost aray
		for (i = 1; i <= m; i++) {
			tc[i][0] = tc[i - 1][0] + cost[i][0];
		}

		// intailize first row of tc array
		for (j = 1; j <= n; j++) {
			tc[0][j] = tc[0][j - 1] + cost[0][j];
		}

		// construct rest of tc array
		for (i = 1; i <= m; i++) {
			for (j = 1; j <= n; j++) {
				tc[i][j] = min(tc[i - 1][j - 1], tc[i - 1][j], tc[i][j - 1]) + cost[i][j];
			}
		}
		return tc[m][n];

	}

	/* A utility function that returns minimum of 3 integers */
	private static int min(int x, int y, int z) {
		if (x < y)
			return (x < z) ? x : z;
		else
			return (y < z) ? y : z;
	}

	// 2. Max Product of Sub Array
	public static int maxProduct(int[] A) {
		if (A == null || A.length == 0) {
			return 0;
		}
		int max = A[0], min = A[0], result = A[0];
		for (int i = 1; i < A.length; i++) {
			int temp = max;
			max = Math.max(Math.max(max * A[i], min * A[i]), A[i]);
			min = Math.min(Math.min(temp * A[i], min * A[i]), A[i]);
			if (max > result) {
				result = max;
			}
		}
		return result;
	}

	// 3. Max Continguous Sum of Sub Array
	public static int maxSum(int[] A) {
		if (A == null || A.length == 0) {
			return 0;
		}
		int max = A[0], result = A[0];
		for (int i = 1; i < A.length; i++) {
			max = Math.max(max + A[i], A[i]);
			if (max > result) {
				result = max;
			}
		}
		return result;
	}

	// 4. Knapsack problem
	/*
	 * 1) Optimal Substructure: To consider all subsets of items, there can be two
	 * cases for every item: (1) the item is included in the optimal subset, (2) not
	 * included in the optimal set. Therefore, the maximum value that can be
	 * obtained from n items is max of following two values. 1) Maximum value
	 * obtained by n-1 items and W weight (excluding nth item). 2) Value of nth item
	 * plus maximum value obtained by n-1 items and W minus weight of the nth item
	 * (including nth item).
	 * 
	 * If weight of nth item is greater than W, then the nth item cannot be included
	 * and case 1 is the only possibility.
	 *
	 * Definitions: max(val[i-1]+k[i-1][w-wt[i-1]], k[i-1][w])
	 * val[i-1]+k[i-1][w-wt[i-1]] ==> Value of Current Item (val[i-1]) + Max Value
	 * of n-1 item and Total weight minus weight of Current Item k[i-1][w] ==> Max
	 * value of current weight (Excluding current item)
	 * 
	 * Always compare [Value of Current Item + Max value of Left over items (items
	 * not current item) and the Weight after subtracting Weight of Current Item ]
	 * to [Max Value of Current Weight (excluding current item)]
	 * 
	 * 
	 */

	public static int knapSack(int W, int wt[], int val[], int n) {
		int i, w;
		int k[][] = new int[n + 1][W + 1];
		for (i = 0; i <= n; i++) {
			for (w = 0; w <= W; w++) {
				if (i == 0 || w == 0) {
					k[i][w] = 0;
				} else if (wt[i - 1] <= w) {
					k[i][w] = max(val[i - 1] + k[i - 1][w - wt[i - 1]], k[i - 1][w]);
					System.out.println("val[i-1]= " + val[i - 1]);
					System.out.println(w - wt[i - 1]);
					System.out.println(k[i - 1][w - wt[i - 1]]);
					System.out.println(k[i - 1][w]);
				} else {
					k[i][w] = k[i - 1][w];
					System.out.println("ELSE STATEMENT: " + k[i - 1][w]);

				}
			}
		}
		return k[n][W];
	}

	public static int max(int a, int b) {
		return (a > b) ? a : b;
	}

	public static int minCostToFillBag(int[] cost, int n, int W) {

		List<Integer> value = new ArrayList<Integer>();
		List<Integer> wt = new ArrayList<Integer>();

		int size = 0;

		// traverse the original cost[] array and skip
		// unavailable packets (ones marked as -1) and make val[] and wt[]
		// array. size variable tells the available
		// number of distinct weighted packets

		for (int i = 0; i < n; i++) {
			if (cost[i] != -1) {
				value.add(cost[i]);
				wt.add(i + 1);
				size++;
			}
		}

		n = size;
		int min_cost[][] = new int[n + 1][W + 1];

		// fill 0th row with infinity
		for (int i = 0; i <= W; i++)
			min_cost[0][i] = Integer.MAX_VALUE;

		// fill 0'th column with 0
		for (int i = 1; i <= n; i++)
			min_cost[i][0] = 0;

		for (int i = 1; i < n; i++) {
			for (int w = 1; w <= W; w++) {
				// here we check we get minimum cost
				// either by including it or excluding
				// it
				if (wt.get(i - 1) <= w) {

					min_cost[i][w] = Math.min(value.get(i - 1) + min_cost[i][w - wt.get(i - 1)], min_cost[i - 1][w]);

				} else {
					// wt[i-1]>w means weight of current item is more than the weight capcity (w)

					min_cost[i][w] = min_cost[i - 1][w];
				}

			}

		}
		// exactly weight W can not be made by
		// given weights
		return (min_cost[n][W] == Integer.MAX_VALUE) ? -1 : min_cost[n][W];
	}

	// At each index in array... store the min number of steps to reach to that
	// index. Increment by 1 until reach end of the array.
	public static int minJumps(int[] arr, int n) {
		int jumps[] = new int[n]; // jumps[n-1] will hold the
									// result
		int i, j;

		if (n == 0 || arr[0] == 0)
			return Integer.MAX_VALUE; // if first element is 0,
										// end cannot be reached

		jumps[0] = 0;

		// Find the minimum number of jumps to reach arr[i]
		// from arr[0], and assign this value to jumps[i]
		for (i = 1; i < n; i++) {
			jumps[i] = Integer.MAX_VALUE;
			for (j = 0; j < i; j++) {
				// Check if can jump from position j to i incrementing the value from arr at
				// position j (arr[j])
				if (i <= j + arr[j] && jumps[j] != Integer.MAX_VALUE) {
					jumps[i] = Math.min(jumps[i], jumps[j] + 1);
					break;
				}
			}
		}
		return jumps[n - 1];
	}

	public static int getMaxGold(int[][] gold, int m, int n) {

		int[][] maxGold = new int[m][n];

		for (int[] rows : maxGold) {
			Arrays.fill(rows, 0);
		}

		for (int col = 0; col < m; col++) {

			for (int row = 0; row < n; row++) {

				// First column will always be filled with first column of original gold table
				if (col == 0) {
					maxGold[row][col] = gold[row][col];
				} else {

					// can go right, right-up, right-down
					// So need to check left, left-up, left-down

					// check left
					int left = col - 1 >= 0 ? maxGold[row][col - 1] : 0;

					// check left-top
					int leftTop = (col - 1 >= 0 && row - 1 >= 0) ? maxGold[row - 1][col - 1] : 0;

					// check bottom-left
					int leftBottom = (col - 1 >= 0 && row + 1 < n) ? maxGold[row + 1][col - 1] : 0;

					maxGold[row][col] = Math.max(left + gold[row][col],
							Math.max(leftTop + gold[row][col], leftBottom + gold[row][col]));

				}

			}

		}
		int max = maxGold[0][m - 1];
		for (int i = 1; i < m; i++) {
			max = Math.max(max, maxGold[i][m - 1]);
		}
		return max;

	}

	public static int countChange(int[] coins, int m, int inputCoin) {

		int[] maxChange = new int[inputCoin + 1];

		Arrays.fill(maxChange, 0);
		maxChange[0]=1;
		for (int i =0; i <= m; i++) {
			for (int j = coins[i]; j <= inputCoin; j++) {
				maxChange[j] = maxChange[j] + maxChange[j-coins[i]];   
			}

		}

		return maxChange[inputCoin];
	}

	public static void main(String args[]) {

		/*
		 * 1. Min Cost Problem From position (0, 0) to (m, n) whats min cost?
		 */
		// int cost[][] = { { 1, 2, 3 }, { 4, 8, 2 }, { 1, 5, 3 } };
		// System.out.println(minCost(cost, 2, 2));

		// 2. Max Product of SubArray
		// int[] a = { -2, -3, 4, -1, -2, 1, 5, -3 };
		// int answer = maxProduct(a);

		// 3. Max Sum of SubArray
		// int[] a = { -2, -3, 4, -1, -2, 1, 5, -3 };
		// int answer = maxSum(a);

		// 4, Knapsack
		// int val[] = new int[]{60, 100, 120};
		// int wt[] = new int[]{1, 2, 3};
		// int W = 5;
		// int n = val.length;
		// System.out.println(knapSack(W, wt, val, n));

		// 5. Min Cost to Fill Bag
		// int cost[] = { 1, 2, 3, 4, 5 }, W = 5;
		// int n = cost.length;
		// System.out.println(minCostToFillBag(cost, n, W));

		// 6. Min # of jumps to get to end of array
		// int arr[] = { 2, 3, 6, 1, 0, 9 };
		// System.out.println("Minimum number of jumps to reach end is : " +
		// minJumps(arr, arr.length));

		// 7. Gold Mine Problem
		// int gold[][] = { { 1, 3, 1, 5 }, { 2, 2, 4, 1 }, { 5, 0, 2, 3 }, { 0, 6, 1, 2
		// } };
		// int m = 4, n = 4;
		// System.out.print(getMaxGold(gold, m, n));

		// 8. Coin Change
		int arr[] = { 1, 2, 3 };
		int m = arr.length;
		int n = 4;
		System.out.println(countChange(arr, m, n));

	}
}

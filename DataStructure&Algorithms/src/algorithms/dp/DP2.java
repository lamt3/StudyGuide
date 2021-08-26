package algorithms.dp;

import java.util.Arrays;

public class DP2 {

	public static int integerBreak(int n) {
		int[][] ans = new int[n + 1][n + 1];

		for (int i = 0; i <= n; i++) {
			ans[0][i] = 1;
			ans[1][i] = 1;
		}

		for (int i = 2; i <= n; i++) {
			for (int j = 0; j <= n; j++) {
				if (j == 0) {
					ans[i][j] = 0;
				} else if (j >= i) {
					ans[i][j] = Math.max(i * ans[i][j - i], Math.max(i * (j - i), ans[i - 1][j]));
				} else {
					ans[i][j] = ans[i - 1][j];
				}
			}
		}
		System.out.println(ans[n - 2][n - 2]);
		return ans[n][n];
	}

	/*
	 * Inputs: denomination ==> int[] denom {1,2,5,10} int amount = 25 int
	 * fixedCount = 3
	 * 
	 * Can we make a combination of 25 given denom array with exactly 3 counts
	 * Output: boolean
	 * 
	 * In this Example, true --> 5, 5, 10
	 * 
	 * 
	 * 
	 */

	public static boolean canMakeChange(int[] denom, int amount, int fixedCount, int count, int currentCoinPosition) {

		if (amount == 0 && count == fixedCount) {
			return true;
		}
		if (amount == 0 && count < fixedCount) {
			return false;
		}

		if (amount < 0 || count >= fixedCount) {
			return false;
		}

		for (int coin = currentCoinPosition; coin < denom.length; coin++) {
			if (canMakeChange(denom, amount - denom[coin], fixedCount, count + 1, coin)) {
				return true;
			}
		}
		return false;

	}

	public static int numSteps(int[] steps, int amount) {

		if (amount == 0) {
			return 1;
		}
	
		if (amount < 0 ) {
			return 0;
		}
		int nCombos = 0;
		/*
		 * Use this algo when subset result ordering is treated as different 
		 * i.e 
		 * 
		 * Given array [1,2] 
		 * How many ways can make 4
		 * 1,1,1,1
		 * 2,2
		 * 1,1,2
		 * 1,2,1
		 * 2,1,1 
		 * 		- 111 211 121 are treated as different subsets
		 * 
		 * Key here is that we don't we always start back at 0 in the recursion when iterating over array
		 * 
		 * Use above algo (can Make Change) when we care about ordering is treated as same 
		 * 	i.e 1,1,2  1,2,1  2,1,1 ==> there are considered the same 
		 * 	For this instance you want to keep track of array position to iterate by passing in "currentCoinPosition" variable in recursion  
		 */
		for (int step = 0; step < steps.length; step++) {
			nCombos += numSteps(steps, amount - steps[step]); 
			
		}
		return nCombos;

	}

	public static boolean search(int[] groups, int row, int[] nums, int target) {
		if (row < 0)
			return true;
		int v = nums[row--];
		for (int i = 0; i < groups.length; i++) {
			if (groups[i] + v <= target) {
				groups[i] += v;
				if (search(groups, row, nums, target))
					return true;
				groups[i] -= v;
			}
			if (groups[i] == 0)
				break;
		}
		return false;
	}

	public static boolean canPartitionKSubsets(int[] nums, int k) {
		int sum = Arrays.stream(nums).sum();
		if (sum % k > 0)
			return false;
		int target = sum / k;

		Arrays.sort(nums);
		int row = nums.length - 1;
		if (nums[row] > target)
			return false;
		while (row >= 0 && nums[row] == target) {
			row--;
			k--;
		}
		return search(new int[k], row, nums, target);
	}

	static int sum(int arr[], int from, int to) {
		int total = 0;
		for (int i = from; i <= to; i++)
			total += arr[i];
		return total;
	}

	// for n boards and k partitions
	static int partition(int arr[], int n, int k) {
		// base cases
		if (k == 1) // one partition
			return sum(arr, 0, n - 1);
		if (n == 1) // one board
			return arr[0];

		int best = Integer.MAX_VALUE;

		// find minimum of all possible maximum
		// k-1 partitions to the left of arr[i],
		// with i elements, put k-1 th divider
		// between arr[i-1] & arr[i] to get k-th
		// partition
		for (int i = 1; i <= n; i++) {
			int part = partition(arr, i, k - 1);
			int s = sum(arr, i, n - 1);
			best = Math.min(best, Math.max(part, s));
		}
			

		return best;
	}

	public static void main(String[] args) {
		//#1.
		// int[] denom = new int[] { 1, 2, 5, 10 };
		// int fixedCount = 3;
		// int amount = 25;
		// boolean a = DP2.canMakeChange(denom, amount, fixedCount, 0, 0);
		// System.out.println(a);

		//#2.
		// int[] nums = new int[] {1,2,3,4};
		// int k =2;
		// DP2.canPartitionKSubsets(nums, k);
		
		
		//3.
//		int arr[] = { 10, 20, 60, 50, 30, 40 };
//		int n = arr.length;
//		int k = 3;
//		DP2.partition(arr, n, k);
		
		//4.Num Steps Rec
		//for input int[] steps [1, 2] and n = 4 ==
		int[] steps = new int[] {1,2};
		int n = 4;
		System.out.println(DP2.numSteps(steps, n));
		

	}

}

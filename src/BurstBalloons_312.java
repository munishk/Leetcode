public class BurstBalloons_312 {

	/*
	 * Using recursion. Time complexity n!
	 */
	public int maxCoins(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}

		if (nums.length == 1) {
			return nums[0];
		}
		int max = 0;
		for (int i = 0; i < nums.length; i++) {
			int left = i - 1 < 0 ? 1 : nums[i - 1];
			int right = i + 1 >= nums.length ? 1 : nums[i + 1];
			int currMax = nums[i] * left * right + maxCoins(newArray(nums, i));
			max = Math.max(currMax, max);
		}
		return max;
	}

	private int[] newArray(int[] arr, int exclude) {
		int[] newArray = new int[arr.length - 1];
		int index = 0;
		for (int i = 0; i < arr.length; i++) {
			if (i == exclude) {
				continue;
			}
			newArray[index++] = arr[i];
		}
		return newArray;
	}

	/*
	 * Using memoization. Time complexity : n^2.
	 */
	public int maxCoinsMemo(int[] nums) {
		int[] newNums = new int[nums.length + 2];
		newNums[0] = 1;
		int n = 1;
		for (int val : nums) {
			newNums[n++] = val;
		}
		newNums[n++] = 1;
		return maxCoinsMemo(newNums, 0, n - 1, new int[n][n]);
	}

	private int maxCoinsMemo(int[] nums, int left, int right, int[][] memo) {
		if (memo[left][right] > 0) {
			return memo[left][right];
		}

		if (left + 1 == right) {
			return 0;
		}

		int max = 0;
		for (int i = left + 1; i < right; i++) {
			int currMax = nums[i] * nums[left] * nums[right] + maxCoinsMemo(nums, left, i, memo)
					+ maxCoinsMemo(nums, i, right, memo);
			max = Math.max(max, currMax);
		}
		memo[left][right] = max;
		return max;
	}
	
	/*
	 * Using DP. Time complexity : n^3.
	 */
	public int maxCoinsDP(int[] nums) {
        int[] newNums = new int[nums.length+2];
        int n = 0;
        newNums[n++] = 1;
        for(int val: nums) {
            newNums[n++] = val;
        }
       newNums[n++] = 1;
      
      int[][] dp = new int[n][n];
      
      for(int l=2; l<n; l++) {
          for(int i=0; i<n-l; i++) {
              int j = i+l;
              for(int k=i+1; k<j; k++) {
                  dp[i][j] = Math.max(dp[i][j], newNums[i]*newNums[j]*newNums[k] + dp[i][k] + dp[k][j]);
              }
          }
      }
      return dp[0][n-1];
    }

	public static void main(String[] args) {
		BurstBalloons_312 obj = new BurstBalloons_312();
		int[] nums = {3, 1, 5, 8};
		System.out.println(obj.maxCoinsDP(nums));

	}

}

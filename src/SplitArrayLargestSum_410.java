public class SplitArrayLargestSum_410 {
	
	/*
	 * With memo
	 */
	 public int splitArrayMemo(int[] nums, int m) {
	        return splitArrayMemo(nums, m, 0, new int[nums.length][m]);
	    }
	    
	    private int splitArrayMemo(int[] nums, int m, int start, int[][] memo) {
	        if(memo[start][m-1] > 0) {
	            return memo[start][m-1];
	        }
	        if(m == 1) {
	           memo[start][m-1] = sum(nums, start);
	           return memo[start][m-1];
	        }
	        
	        int leftSum = 0; int minSum = Integer.MAX_VALUE;
	        for(int i=start;i<nums.length-m+1; i++) {
	            leftSum+=nums[i];
	            int rightSum = splitArrayMemo(nums, m-1, i+1, memo);
	            minSum = Math.min(minSum, Math.max(leftSum, rightSum));
	        }
	        
	        memo[start][m-1] = minSum;
	        return memo[start][m-1];
	    }

	
	 public int splitArray(int[] nums, int m) {
	        return splitArray(nums, m, 0);
	    }
	    
	 /*
	  * Without memo or dp. Time complexity
	  */
	    private int splitArray(int[] nums, int m, int start) {
	        if(m == 1) {
	           return sum(nums, start); 
	        }
	        
	        int leftSum = 0; int minSum = Integer.MAX_VALUE;
	        for(int i=start;i<nums.length-m+1; i++) {
	            leftSum+=nums[i];
	            int rightSum = splitArray(nums, m-1, i+1);
	            minSum = Math.min(minSum, Math.max(leftSum, rightSum));
	        }
	        return minSum;
	    }
	    
	    private int sum(int[] nums, int start) {
	        int sum = 0;
	        for(int i=start; i<nums.length; i++) {
	            sum+=nums[i];
	        }
	        return sum;
	    }

	public static void main(String[] args) {
		int[] nums = {1,4,4};
		SplitArrayLargestSum_410 obj = new SplitArrayLargestSum_410();
		System.out.println(obj.splitArray(nums, 3));

	}

}

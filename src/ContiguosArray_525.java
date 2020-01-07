public class ContiguosArray_525 {
	
	/*
	 * Using recursion. Time compexity O(2^N)
	 */
	public int findMaxLength(int[] nums) {
        return findMaxLength(nums, 0, nums.length-1);
    }
    
    
    private int findMaxLength(int[] nums, int start, int end) {
        if(equalCount(nums, start, end)) {
            return end-start+1;
        }
        return Math.max(findMaxLength(nums, start+1, end), findMaxLength(nums, start, end-1));
    }
    
    
    private boolean equalCount(int[] nums, int start, int end) {
        int zero = 0; int one = 0;
        for(int i=start; i<=end; i++) {
           if(nums[i] == 0) zero++;
           else one++;
        }
        return zero == one;
    }
	
	public static void main(String[] args) {
		
	}

}

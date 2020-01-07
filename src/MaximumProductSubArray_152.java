public class MaximumProductSubArray_152 {
	
	public int maxProduct(int[] nums) {
        int max = nums[0]; int curr = nums[0]; int min = nums[0];
        for(int i=1; i<nums.length; i++) {
        	int newCurr = Math.max(nums[i], Math.max(nums[i] * curr, nums[i] * min));
            min = Math.min(nums[i], Math.min(nums[i]* curr, nums[i] * min));
            max = Math.max(newCurr, max);
            curr = newCurr;
        }
        return max;
    }

	public static void main(String[] args) {
		MaximumProductSubArray_152 obj = new MaximumProductSubArray_152();
		int[] nums = {-4, -3, -2};
		System.out.println(obj.maxProduct(nums));

	}

}

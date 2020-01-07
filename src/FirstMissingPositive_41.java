public class FirstMissingPositive_41 {
	
	public int firstMissingPositive(int[] nums) {
	    int n = nums.length;
	    for(int i = 0; i < n; i++) {
	        while(nums[i] > 0 && nums[i] <= n && nums[i] != nums[nums[i] - 1])
	            swap(nums, i, nums[i] - 1);
	    }
	    for(int i = 0; i < n; i++)
	        if(nums[i] != i + 1)
	            return i + 1;
	    return n + 1;
	}

	private void swap(int[] nums, int i, int j) {
	    nums[i] ^= nums[j];
	    nums[j] ^= nums[i];
	    nums[i] ^= nums[j];
	}
	public static void main(String[] args) {
		FirstMissingPositive_41 obj = new FirstMissingPositive_41();
		int[] nums = {3,4,-1,1};
		System.out.println(obj.firstMissingPositive(nums));

	}

}

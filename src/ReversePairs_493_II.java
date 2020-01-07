import java.util.Arrays;

public class ReversePairs_493_II {
	
	public int reversePairs(int[] nums) {
	    int res = 0;
	    int[] copy = Arrays.copyOf(nums, nums.length);
	    int[] bit = new int[copy.length + 1];
	    
	    Arrays.sort(copy);
	    
	    for (int ele : nums) {
	        res += search(bit, index(copy, 2L * ele + 1));
	        insert(bit, index(copy, ele));
	    }
	    
	    return res;
	}

	private int index(int[] arr, long val) {
	    int l = 0, r = arr.length - 1, m = 0;
	    	
	    while (l <= r) {
	    	m = l + ((r - l) >> 1);
	    		
	    	if (arr[m] >= val) {
	    	    r = m - 1;
	    	} else {
	    	    l = m + 1;
	    	}
	    }
	    
	    return l + 1;
	}
	
	private int search(int[] bit, int i) {
	    int sum = 0;
	    
	    while (i < bit.length) {
	        sum += bit[i];
	        i += i & -i;
	    }

	    return sum;
	}

	private void insert(int[] bit, int i) {
	    while (i > 0) {
	        bit[i] += 1;
	        i -= i & -i;
	    }
	}

	public static void main(String[] args) {
		ReversePairs_493_II obj = new ReversePairs_493_II();
		int[] nums = {1,3,2,3,1};
		System.out.println(obj.reversePairs(nums));

	}

}

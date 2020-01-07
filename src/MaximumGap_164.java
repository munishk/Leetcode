import java.util.Arrays;

public class MaximumGap_164 {
	
	public int maximumGap(int[] num) {
	    if (num == null || num.length < 2)
	        return 0;
	    // get the max and min value of the array
	    int min = num[0];
	    int max = num[0];
	    for (int i:num) {
	        min = Math.min(min, i);
	        max = Math.max(max, i);
	    }
	    // the minimum possibale gap, ceiling of the integer division
	    int gap = (int)Math.ceil((double)(max - min)/(num.length - 1));
	    int[] bucketsMIN = new int[num.length - 1]; // store the min value in that bucket
	    int[] bucketsMAX = new int[num.length - 1]; // store the max value in that bucket
	    Arrays.fill(bucketsMIN, Integer.MAX_VALUE);
	    Arrays.fill(bucketsMAX, Integer.MIN_VALUE);
	    // put numbers into buckets
	    for (int i:num) {
	        if (i == min || i == max)
	            continue;
	        int idx = (i - min) / gap; // index of the right position in the buckets
	        bucketsMIN[idx] = Math.min(i, bucketsMIN[idx]);
	        bucketsMAX[idx] = Math.max(i, bucketsMAX[idx]);
	    }
	    // scan the buckets for the max gap
	    int maxGap = Integer.MIN_VALUE;
	    int previous = min;
	    for (int i = 0; i < num.length - 1; i++) {
	        if (bucketsMIN[i] == Integer.MAX_VALUE && bucketsMAX[i] == Integer.MIN_VALUE)
	            // empty bucket
	            continue;
	        // min value minus the previous value is the current gap
	        maxGap = Math.max(maxGap, bucketsMIN[i] - previous);
	        // update previous bucket value
	        previous = bucketsMAX[i];
	    }
	    maxGap = Math.max(maxGap, max - previous); // updata the final max value gap
	    return maxGap;
	}
	
	 public int maximumGap1(int[] nums) {
	        if(nums == null || nums.length < 2) {
	            return 0;
	        }
	        
	        int n = nums.length;
	        
	        //find min, max
	        int min=nums[0], max = nums[0];
	        for(int val: nums) {
	            min = Math.min(min, val);
	            max = Math.max(max, val);
	        }
	        
	        //find the gap
	        int gap = (int) Math.ceil((double) (max - min)/(n-1));
	        int[] minBucket = new int[n-1];
	        int[] maxBucket = new int[n-1];
	        
	        Arrays.fill(minBucket, Integer.MAX_VALUE);
	        Arrays.fill(maxBucket, Integer.MIN_VALUE);
	        
	        for(int val: nums) {
	            if(val == min || val == max) continue; //Exclude min/max from bucket
	            int idx = (val - min)/gap;
	            minBucket[idx] = Math.min(minBucket[idx], val);
	            maxBucket[idx] = Math.max(maxBucket[idx], val);
	        }
	        int prev = min;
	        int maxGap = min;
	        for(int i=0; i<n-1; i++) {
	            if(minBucket[i] == Integer.MAX_VALUE && maxBucket[i] == Integer.MIN_VALUE) continue; //bucket could be empty
	            maxGap = Math.max(maxGap, minBucket[i] - prev);
	            prev = maxBucket[i];
	        }
	        
	        return Math.max(maxGap, max-prev);
	    }

	public static void main(String[] args) {
		MaximumGap_164 obj  = new MaximumGap_164();
		int[] num = {1, 10000000};
		System.out.println(obj.maximumGap(num));
		System.out.println(obj.maximumGap1(num));
		//System.out.println(Math.ceil((double)16/6));

	}

}

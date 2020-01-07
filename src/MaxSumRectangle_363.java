import java.util.TreeSet;

public class MaxSumRectangle_363 {
	
	 public int maxSumSubmatrix(int[][] matrix, int k) {
	        int max = Integer.MIN_VALUE;
	        int m = matrix.length; int n = matrix[0].length;
	        int[] arr = new int[m]; 
	        for(int l=0; l<n; l++) {
	            arr = new int[m]; 
	            for(int r=l;r<n;r++) {
	                for(int i=0; i<m; i++) {
	                    arr[i]+=matrix[i][r];
	                }
	                int sum = findMaxSum(arr, k);
	                max  = Math.max(sum, max);
	            }
	        }
	        return max;
	    }
	    
	    private int findMaxSum(int[] arr, int k) {
	        TreeSet<Integer> set = new TreeSet<>();
	        set.add(0);
	        int sum = 0; int max = Integer.MIN_VALUE;
	        for(int val: arr) {
	            sum = sum + val;
	            Integer ceiling = set.ceiling(sum-k);
	            if(ceiling != null) {
	                max = Math.max(max, sum-ceiling);
	            }
	            set.add(sum);
	        }
	        return max;
	    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

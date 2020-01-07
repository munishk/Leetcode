public class Solution74 {
	
	 public boolean searchMatrix(int[][] matrix, int target) {
	        int n = matrix.length * matrix[0].length;
	        return binarySearch(matrix, 0, n-1, target);
	    }
	    
	    boolean binarySearch(int[][] matrix, int low, int high, int target) {
	        boolean found = false;
	        while(low <= high) {
	            int mid = low + (high-low)/2;
	            int diff = compare(matrix, target, mid);
	            if(diff == 0) {
	                found = true;
	                break;
	            }
	            
	            if(diff > 0) {
	                low = mid+1;
	            }else {
	                high = mid-1;
	            }
	        }
	        return found;
	    }
	    
	    int compare(int[][] matrix, int target, int mid) {
	        int row = mid/matrix.length;
	        int col = mid%matrix.length;
	        return target - matrix[row][col];
	    }

	public static void main(String[] args) {
		Solution74 sol = new Solution74();
		int[][] matrix = {{1}};
		boolean res = sol.searchMatrix(matrix, 1);
		System.out.println(res);

	}

}

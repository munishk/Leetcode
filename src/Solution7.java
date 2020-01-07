import java.util.ArrayList;
import java.util.List;

public class Solution7 {
	 int count = 0;
//	    public int numberOfArithmeticSlices(int[] A) {
//	        //countArithmeticSlices(A, 0, -1, 0,  -1);
//	    	countArithmeticSlices(A, new int[A.length], 0, 0);
//	        return count;
//	    }
	    
	    
	    public int numberOfArithmeticSlices(int[] A) {
	        int n = A.length;
	       List<List<Integer>> list = new ArrayList<>();
	       
	       for(int i=0; i<=A.length-3; i++) {
	           List<Integer> subList = new ArrayList<>();
	           subList.add(A[i]);
	           list.add(subList);
	       }
	       List<List<Integer>> newList = new ArrayList<>();
	       List<Integer> indexList = new ArrayList<>();
	       for(int i=0; i< list.size(); i++) {
	         List<Integer> subList = list.get(i);
	         for(int j=i+1; j<=A.length-2; j++) {
	           List<Integer> newSubList = new ArrayList<>(subList);
	           newSubList.add(A[j]);
	           newList.add(newSubList);
	           indexList.add(j);
	        }
	       }
	       
	       List<List<Integer>> finalList = new ArrayList<>();
	       list = newList;
	       for(int k=3; k<=n; k++) {
	           newList = new ArrayList<>();
	          List<Integer> newIndexList = new ArrayList<>();
	           for(int i=0; i<list.size(); i++) {
	               List<Integer> subList = list.get(i);
	               for(int j=indexList.get(i) +1; j<n; j++) {
	                  int l = subList.size();
	                  if(subList.get(l-1) - subList.get(l-2) == A[j] - subList.get(l-1)) {
	                      List<Integer> newSubList =  new ArrayList<>(subList);
	                      newSubList.add(A[j]);
	                      newList.add(newSubList);
	                      newIndexList.add(j);
	                      finalList.add(newSubList);
	                  }
	               }
	           }
	           list = newList;
	           indexList = newIndexList;
	       }
	       System.out.println(finalList);
	       return finalList.size();
	    }
	    
	    private void countArithmeticSlices(int[] A, int index, int prevIndex, int count, int diff) {
	        if(index >= A.length) {
	            return;
	        }
	        if(count >= 2) {
	            this.count++;
	        }
	        //inclusive
	        int currDiff = -1;
	        if(prevIndex >= 0) {
	            currDiff = A[index] - A[prevIndex];
	        }
	        
	        if(diff == -1 || currDiff == diff) {
	           countArithmeticSlices(A, index+1, index, count+1, currDiff);
	        }
	        
	        countArithmeticSlices(A, index+1, prevIndex, count, diff);
	    }
	    

	      private void countArithmeticSlices(int[] A, int[] temp, int index, int tempIndex) {		        
		        if(index >= A.length) {
		            return;
		        }
		        
		        if(tempIndex < 2 ||  A[index] - temp[tempIndex-1] == temp[tempIndex-1] - temp[tempIndex-2]) {
		            temp[tempIndex] = A[index];
		            if(tempIndex >=2) {
		        	  this.count++;
		        	  print(temp, tempIndex);
		            }
		          countArithmeticSlices(A, temp, index+1, tempIndex+1);
		        }
		        //excl
		        countArithmeticSlices(A, temp, index+1, tempIndex);
		    }
	    
	    void print(int[] temp, int index) {
	    	StringBuilder sb = new StringBuilder();
	    	for(int i=0; i<=index; i++) {
	    		sb.append(temp[i] + " ");
	    	}
	    	System.out.println(sb);
	    }
	    
	    public int getMoneyAmount(int n) {
	        int total = 0;
	        int guess = n-1;
	        while(guess >= 1 ) {
	            total+=guess;
	            guess-=2;
	        }
	        return total;
	     }

	public static void main(String[] args) {
		Solution7 sol = new Solution7();
		//int[] nums = {2,4,6,8,10};
		//System.out.println(sol.numberOfArithmeticSlices(nums));
		System.out.println(sol.getMoneyAmount(4));

	}

}

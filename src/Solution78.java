import java.util.ArrayList;
import java.util.List;

public class Solution78 {
	
	public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> finalList = new ArrayList<>();
        finalList.add(new ArrayList<>());
        
        List<Integer> indexList = new ArrayList<>();
        indexList.add(-1);
        List<List<Integer>> list = new ArrayList<>();
        list.add(new ArrayList<>());
        for(int k=1; k<=nums.length; k++) {
            List<List<Integer>> newList = new ArrayList<>();
            List<Integer> newIndexList = new ArrayList<>();
            for(int i=0; i<list.size(); i++) {
              List<Integer> subList = list.get(i);
              for(int j=indexList.get(i)+1; j<nums.length; j++) {
                 List<Integer> newSubList = new ArrayList<>(subList);
                 newSubList.add(nums[j]);
                 newIndexList.add(j);
                 newList.add(newSubList);
                 finalList.add(newSubList);
            }
          }
          list = newList;
          indexList = newIndexList;
       }
        return finalList;
    }
	
	 public int rob(int[] nums) {
	        if(nums.length < 1) {
	            return 0;
	        }
	        int[] dp = new int[nums.length];
	        for(int i=0; i<nums.length; i++) {
	            dp[i] = Math.max((i-2 >= 0?dp[i-2]:0) + nums[i], i-1>=0?dp[i-1]:0);
	        }
	      return dp[nums.length-1];
	    }

	public static void main(String[] args) {
		Solution78 sol = new Solution78();
		//System.out.println(sol.subsets(new int[] {3,2,4,1}));
		int val = sol.rob(new int[] {1,1,1});
		System.out.println(val);

	}

}

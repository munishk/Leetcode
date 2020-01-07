import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subset {
	
	 /*
	  * subset using binary bit representation
	  */
	 public List<List<Integer>> subsetsBinary(int[] nums) {
	        int n = nums.length;
	        int count  = 1 << n;
	        Arrays.sort(nums);
	        List<List<Integer>> list = new ArrayList<>();
	        for(int i=0; i< count; i++) {
	            List<Integer> subList = new ArrayList<>();
	            for(int j=0; j<nums.length; j++) {
	                if(((1<<j) & i) != 0 && (j-1 < 0 || nums[j] != nums[j-1])) {
	                    subList.add(nums[j]);
	                }
	            }
	            //if(!subList.isEmpty() || i == 0) {
                list.add(subList);
	           // }
	        }
	        return list;
	    }
	 
	 
	 public List<List<Integer>> subsetsRecursion(int[] nums) {
		 List<List<Integer>> list = new ArrayList<>();
		 list.add(new ArrayList<>());
		 subsetsRecursionUtil(nums, 0, list, new ArrayList<>());
		 return list;
	 }
	 
	 private void subsetsRecursionUtil(int[] nums, int index, List<List<Integer>> list, List<Integer> sublist) {
		 if(index >= nums.length) return;
		 
		 for(int i=index; i<nums.length; i++) {
			 sublist.add(nums[i]);
			 list.add(new ArrayList<>(sublist));
			 subsetsRecursionUtil(nums, i+1, list, sublist);
			 sublist.remove(sublist.size()-1);
		 }
	 }
	 
	 public List<List<Integer>> subsetsRecursionWithDups(int[] nums) {
		 List<List<Integer>> list = new ArrayList<>();
		 list.add(new ArrayList<>());
		 subsetsRecursionWithDupsUtil(nums, 0, list, new ArrayList<>());
		 return list;
	 }
	 
	 private void subsetsRecursionWithDupsUtil(int[] nums, int index, List<List<Integer>> list, List<Integer> sublist) {
		 if(index >= nums.length) return;
		 
		 for(int i=index; i<nums.length; i++) {
			 sublist.add(nums[i]);
			 list.add(new ArrayList<>(sublist));
			 subsetsRecursionUtil(nums, i+1, list, sublist);
			 sublist.remove(sublist.size()-1);
			 while(i< nums.length -1 && nums[i] == nums[i+1]) {
				 i++;
			 }
		 }
	 }

	public static void main(String[] args) {
		Subset sol = new Subset();
		int[] nums = {1,2,2};
		System.out.println(sol.subsetsRecursion(nums));

	}

}

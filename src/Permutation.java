import java.util.*;
import java.util.stream.Collectors;

public class Permutation {
	
	public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res= new ArrayList<>();
        permuteUtil(nums, 0, res);
        return res;
    }
    
    private void permuteUtil(int[] nums, int index, List<List<Integer>> res) {
        if(index>= nums.length) {
        	res.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
        }
        
        for(int i=index; i<nums.length; i++) {
                swap(nums, index, i);
                permuteUniqueUtil(nums, index+1, res);
                swap(nums, index, i);
        }
    }
	
	public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res= new ArrayList<>();
        permuteUniqueUtil(nums, 0, res);
        return res;
    }
    
    private void permuteUniqueUtil(int[] nums, int index, List<List<Integer>> res) {
        if(index>= nums.length) {
        	res.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
        }
        Set<Integer> visited = new HashSet<>();
        for(int i=index; i<nums.length; i++) {
            if(!visited.contains(nums[i])) {
                swap(nums, index, i);
                permuteUniqueUtil(nums, index+1, res);
                swap(nums, index, i);
                visited.add(nums[i]);
            }
        }
    }
    
    private void swap1(int[] nums, int i, int j) {
        nums[i] = nums[i] + nums[j];
        nums[j] = nums[i] - nums[j];
        nums[i] = nums[i] - nums[j];
    }
    private void swap(int[] nums, int i, int j) {
       int temp = nums[i];
       nums[i] = nums[j];
       nums[j] = temp;
    }

	public static void main(String[] args) {
		Permutation sol = new Permutation();
		int[] nums = {2,1,1};
		//System.out.println(sol.permute(nums));
		System.out.println(sol.permuteUnique(nums));

	}

}

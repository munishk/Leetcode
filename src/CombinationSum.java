import java.util.*;

public class CombinationSum {
	
	 public List<List<Integer>> combinationSum(int[] candidates, int target) {
	        List<List<Integer>> list = new ArrayList<>();
	        List<Integer> subList = new ArrayList<>();
	        combinationSumUtilRecursion(candidates, 0, subList, list, target);
	        return list;
	    }
	    
	    void combinationSumUtilRecursion(int[] candidates, int index, List<Integer> subList, List<List<Integer>> list, int target) {
	        if(target == 0) {
	            list.add(new ArrayList<>(subList));
	            return;
	        }
	        
	        if(index >= candidates.length || target < 0) {
	            return;
	        }
	        
	        if(candidates[index] > target) {
	        	combinationSumUtilRecursion(candidates, index+1, subList, list, target);
	        }else {
	        
	        subList.add(candidates[index]);
	        combinationSumUtilRecursion(candidates, index, subList, list, target-candidates[index]);
	        subList.remove(subList.size()-1);
	        combinationSumUtilRecursion(candidates, index+1, subList, list, target);
	        }
	    }
	    
	    private List<List<Integer>> combinationSumIterative(int[] candidates, int target) {
	    	List<List<List<Integer>>> dp = new ArrayList<>();
	    	for(int i=1; i<=target; i++) {
	    		List<List<Integer>> newList = new ArrayList<>();
	    		for(int j=0; j<candidates.length && i >= candidates[j]; j++) {
	    			if(i == candidates[j])  { newList.add(Arrays.asList(candidates[j]));}
	    			else {
	    				for(List<Integer> sublist : dp.get(i-candidates[j]-1)) {
	    					if(candidates[j] <= sublist.get(0)) {
	    					List<Integer> newSublist = new ArrayList<>(sublist);
	    					newSublist.add(candidates[j]);
	    					newList.add(newSublist);	
	    					}
	    				}
	    			}
	    		}
	    		dp.add(newList);
	    	}
	    	return dp.get(target-1);
	    }

	public static void main(String[] args) {
		CombinationSum cs = new CombinationSum();
		int[] candidates = {2,3,5,6,7};
		System.out.println(cs.combinationSum(candidates, 7));
		System.out.println(cs.combinationSumIterative(candidates, 7));

	}

}

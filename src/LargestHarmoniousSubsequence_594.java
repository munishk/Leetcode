import java.util.HashMap;
import java.util.Map;

public class LargestHarmoniousSubsequence_594 {
	
	public int findLHS(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int val: nums) {
            map.put(val, map.getOrDefault(val, 0) + 1);
        }
        
        int max = 0;
        for(Integer key: map.keySet()) {
            int count = map.get(key) + map.getOrDefault(key+1, 0);
            max = Math.max(max, count);
        }
        return max;
    }

	public static void main(String[] args) {
		LargestHarmoniousSubsequence_594 obj = new LargestHarmoniousSubsequence_594();
		int[] nums = {1,3,2,2,5,2,3,7};
		System.out.println(obj.findLHS(nums));

	}

}

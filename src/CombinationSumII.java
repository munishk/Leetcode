import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumII {

	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		List<List<Integer>> res = new ArrayList<>();
		Arrays.sort(candidates);
		combinationSumUtil(candidates, 0, target, new ArrayList<>(), res);
		return res;
	}

	private void combinationSumUtil(int[] candidates, int index, int target, List<Integer> list,
			List<List<Integer>> res) {
		if (index >= candidates.length) {
			return;
		}

		for (int i = index; i < candidates.length && candidates[i] <= target;) {
			list.add(candidates[i]);
			if (target == candidates[i]) {
				res.add(new ArrayList<>(list));
			} else {
				combinationSumUtil(candidates, i + 1, target - candidates[i], list, res);
			}
			list.remove(list.size() - 1);
			i++;
			while (i < candidates.length && candidates[i] == candidates[i - 1]) {
				i++;
			}
		}
	}

	
	public List<List<Integer>> combinationSum2AnotherWay(int[] candidates, int target) {
		List<List<Integer>> res = new ArrayList<>();
		Arrays.sort(candidates);
		combinationSum2AnotherWayUtil(candidates, 0, target, new ArrayList<>(), res);
		return res;
	}
	@SuppressWarnings("unused")
	private void combinationSum2AnotherWayUtil(int[] candidates, int index, int target, List<Integer> list,
			List<List<Integer>> res) {
		if (index >= candidates.length) {
			return;
		}

		if (candidates[index] > target) {
			return;
		}

		list.add(candidates[index]);
		if (candidates[index] == target) {
			res.add(new ArrayList<>(list));
			list.remove(list.size() - 1);
		} else {
			combinationSumUtil(candidates, index + 1, target - candidates[index], list, res);
			list.remove(list.size() - 1);
			int newIndex = index+1;
			while(newIndex < candidates.length && candidates[newIndex] == candidates[newIndex-1]) {
				newIndex++;
			}
			combinationSumUtil(candidates, newIndex, target, list, res);
		}
	}

	public static void main(String[] args) {
		CombinationSumII sol = new CombinationSumII();
		System.out.println("####### Using correct way ###########");
		System.out.println(sol.combinationSum2(new int[] {1, 1,1,1,1,1,1}, 5));
		
		System.out.println("####### Using other way ###########");
		System.out.println(sol.combinationSum2AnotherWay(new int[] {1, 1,1,1,1,1,1}, 5));

	}

}

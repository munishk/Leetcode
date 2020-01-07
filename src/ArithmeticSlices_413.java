import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArithmeticSlices_413 {

	public int numberOfArithmeticSlices(int[] A) {
		if (A == null || A.length < 3) {
			return 0;
		}

		int n = A.length;
		List<List<Integer>> res = new ArrayList<>();
		List<List<Integer>> list = new ArrayList<>();
		for (int i = 0; i < n - 2; i++) {
			for (int j = i + 1; j < n - 1; j++) {
				list.add(Arrays.asList(i, j));
			}
		}

		while (!list.isEmpty()) {
			List<List<Integer>> newList = new ArrayList<>();
			for (List<Integer> currList : list) {
				int i1 = currList.get(currList.size() - 2);
				int i2 = currList.get(currList.size() - 1);
				for (int i3 = i2 + 1; i3 < n; i3++) {
					if (A[i3] - A[i2] == A[i2] - A[i1]) {
						List<Integer> newSubList = new ArrayList<>(currList);
						newSubList.add(i3);
						newList.add(newSubList);
					}
				}
			}
			res.addAll(newList);
			list = newList;
		}
		for (List<Integer> subList : res) {
			System.out.print("[");
			for(int i: subList) {
			   System.out.print(A[i] + " ");
			}
			System.out.println("]");
		}
		return res.size();
	}

	public static void main(String[] args) {
		ArithmeticSlices_413 obj = new ArithmeticSlices_413();
		int[] A = {0,2000000000,-294967296};
		System.out.println(obj.numberOfArithmeticSlices(A));
		int a = 2000000000;
		System.out.println(Integer.toBinaryString(a));
		int b = -294967296;
		System.out.println(Integer.toBinaryString(b));
		System.out.println(b-a);

	}

}

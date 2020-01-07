import java.util.TreeMap;

public class ReversePairs_493 {

	public int reversePairs1(int[] nums) {
		TreeMap<Long, Long> map = new TreeMap<>();
		long count = 0;
		for (int i = 0; i < nums.length; i++) {
			java.util.SortedMap<Long, Long> subMap = map.tailMap((long) nums[i] * 2 + 1);
			for (Long val : subMap.values()) {
				count = count + val;
			}

			Long existingCount = map.get(nums[i]);
			if (existingCount != null) {
				map.put((long) nums[i], existingCount + 1);
			} else {
				map.put((long) nums[i], 1L);
			}
		}
		return (int) count;
	}

	public int reversePairsUsingDivideAndMerge(int[] nums) {
		return reversePairs(nums, 0, nums.length - 1);
	}

	private int reversePairs(int[] nums, int low, int high) {
		if (low >= high) {
			return 0;
		}
		int count = 0;
		int mid = (low + high) / 2;
		count += reversePairs(nums, low, mid);
		count += reversePairs(nums, mid + 1, high);

		for (int l = low; l <= mid; l++) {
			for (int r = mid + 1; r <= high; r++) {
				if (nums[l] > 2 * (long) nums[r]) {
					count++;
				}
			}
		}
		return count;
	}

	public class BSTNode {
		int count;
		int val;
		BSTNode left, right;

		void incrementCount() {
			this.count++;
		}

		public BSTNode(int val) {
			this.val = val;
			this.count = 1;
		}
	}

	private int search(BSTNode root, int val) {
		if (root == null) {
			return 0;
		}

		if (val <= root.val) {
			return root.count + search(root.left, val);
		} else {
			return search(root.right, val);
		}
	}

	public BSTNode insert(BSTNode root, int val) {
		if (root == null) {
			return new BSTNode(val);
		}

		if (val == root.val) {
			root.incrementCount();
			return root;
		}

		if (val < root.val) {
			root.left = insert(root.left, val);
		} else {
			root.incrementCount();
			root.right = insert(root.right, val);
		}
		return root;
	}

	public int reversePairs(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int count = 0;
		BSTNode root = new BSTNode(nums[0]);
		for (int i = 1; i < nums.length; i++) {
			count += search(root, 2 * nums[i] + 1);
			insert(root, nums[i]);
		}
		return count;
	}

	public static void main(String[] args) {
		int[] nums = {0,1,2,3,4,5,6,7,8,9,10};
        ReversePairs_493 obj = new ReversePairs_493();
        System.out.println(obj.reversePairs(nums));
	}

}

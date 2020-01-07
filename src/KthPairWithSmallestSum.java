import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class KthPairWithSmallestSum {

	class PQItem {
		int i, j, val;

		public PQItem(int i, int j, int val) {
			this.i = i;
			this.j = j;
			this.val = val;
		}
	}

	public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
	    List<int[]> res = new ArrayList<>();
	     if(nums1.length < 1 || nums2.length < 1) {
	         return res;
	     }
		PriorityQueue<PQItem> pq = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);
		for (int j = 0; j < nums2.length; j++) {
			pq.add(new PQItem(0, j, nums1[0] + nums2[j]));
		}
		for (int i = 0; i < Math.min(k, nums1.length*nums2.length); i++) {
			PQItem item = pq.poll();
			res.add(new int[] { nums1[item.i], nums2[item.j] });
			if(item.i+1 < nums1.length) {
		    	pq.add(new PQItem(item.i + 1, item.j, nums1[item.i + 1] + nums2[item.j]));
			}
		}
		return res;
	}

	public static void main(String[] args) {
		KthPairWithSmallestSum sol = new KthPairWithSmallestSum();
		int[] nums1 = {1,1,2};
		int[] nums2 = {1,2,3};
		System.out.println(sol.kSmallestPairs(nums1, nums2, 10));

	}

}

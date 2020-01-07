import util.StringUtils;

public class SegmentTree {
	int[] arr;
	
	SegmentTree(int n) {
		arr=  new int[n];
	}
	
	int getMid(int low, int high) {
		return low + (high-low)/2;
	}
	
	void applyIncrement(int[][] updates) {
		for(int[] update: updates) {
			applyIncrement(update[0], update[1], update[2]);
		}
	}
	
	void applyIncrement(int from, int to, int increment) {
		applyIncrement(from, to, increment, 0, 0, arr.length-1);
	}
	
	
	void applyIncrement(int from, int to, int increment, int si, int ss, int se) {
		//If from and to are not in segment range.
		if(to < ss || from > se) {
			return;
		}
		
		if(ss== se) {
			arr[ss] = arr[ss] + increment;
			return;
		}
		
		int mid = getMid(ss, se);
		applyIncrement(from, to, increment, si*2+1, ss, mid);
		applyIncrement(from, to, increment, si*2+2, mid+1, se);
	}
	
	void print() {
		System.out.println(StringUtils.toString(arr));
	}
	
	public static void main(String[] args) {
		SegmentTree tree = new SegmentTree(5);
		int[][] updates = {{1,3,2}, {2,4,3}, {0,2,-2}};
		tree.applyIncrement(updates);
		tree.print();
	}

}

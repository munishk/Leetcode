import java.util.*;
import java.util.stream.Collectors;

public class Solution {
	public int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			if (map.get(target - nums[i]) != null) {
				return new int[] { map.get(target - nums[i]), i };
			}
			map.put(nums[i], i);
		}
		return null;
	}

	public int[] countBits(int num) {
		int[] result = new int[num + 1];
		result[0] = 0;
		// result[1]=1;

		for (int i = 1; i <= num; i++) {
			int twosExp = (int) Math.floor(Math.log(i) / Math.log(2));
			int powerOfTwo = (int) Math.pow(2, twosExp);
			result[i] = 1 + result[i - powerOfTwo];
		}
		return result;
	}

	static class TreeNode {
		int value;
		TreeNode left, right;

		TreeNode(int value) {
			this.value = value;
		}
	}

	public List<List<Integer>> findLeaves(TreeNode root) {
		List<List<Integer>> result = new ArrayList<>();
		while (root != null) {
			if (root.left == null && root.right == null) {
				List<Integer> leafList = new ArrayList<>();
				leafList.add(root.value);
				result.add(leafList);
				break;
			}
			List<Integer> subList = new ArrayList<>();
			removeAndCollectLeaves(root, subList, null);
			if (!subList.isEmpty()) {
				result.add(subList);
			}
		}
		return result;
	}

	private void removeAndCollectLeaves(TreeNode root, List<Integer> result, TreeNode parent) {
		if (root == null) {
			return;
		}

		// root is leaf node
		if (root.left == null && root.right == null) {
			result.add(root.value);
			// Set parent to null for this leaf
			if (parent != null) {
				if (parent.left == root) {
					parent.left = null;
				} else {
					parent.right = null;
				}
			}
		} else {
			removeAndCollectLeaves(root.left, result, root);
			removeAndCollectLeaves(root.right, result, root);
		}
	}

	public int[][] reconstructQueue(int[][] people) {
		Arrays.sort(people, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0];
			}
		});

		LinkedList<int[]> result = new LinkedList<>();
		for (int[] singlePerson : people) {
			result.add(singlePerson[1], singlePerson);
		}

		return result.toArray(new int[0][0]);
	}

	public int[][] multiply(int[][] A, int[][] B) {
		int m = A.length;
		int n = A[0].length;
		int l = B[0].length;
		int[][] C = new int[m][l];

		Map<Integer, Map<Integer, Integer>> bMap = new HashMap<>();
		for (int i = 0; i < n; i++) {
			bMap.put(i, new HashMap<>());
			for (int j = 0; j < l; j++) {
				if (B[i][j] != 0) {
					bMap.get(i).put(j, B[i][j]);
				}
			}
		}

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (A[i][j] != 0) {
					for (int k : bMap.get(j).keySet()) {
						C[i][k] += A[i][j] * bMap.get(j).get(k);
					}
				}
			}
		}
		return C;
	}

	static class KeyAndCount {
		int key;
		int count;

		KeyAndCount(int key) {
			this.key = key;
			this.count = 1;
		}

		void incrementCount() {
			count++;
		}
	}

	public List<Integer> topKFrequent(int[] nums, int k) {
		Map<Integer, KeyAndCount> map = new HashMap<>();
		PriorityQueue<KeyAndCount> pq = new PriorityQueue<>(k, new Comparator<KeyAndCount>() {

			@Override
			public int compare(KeyAndCount o1, KeyAndCount o2) {
				return o1.count - o2.count;
			}
		});

		for (int key : nums) {
			KeyAndCount freq = map.get(key);
			if (freq == null) {
				freq = new KeyAndCount(key);
				map.put(key, freq);
			} else {
				freq.incrementCount();
			}

			if (pq.size() == k && (freq.key == pq.peek().key || freq.count > pq.peek().count)) {
				pq.remove();
				pq.add(freq);
			} else if (pq.size() < k) {
				if (!pq.isEmpty() && pq.peek().key == freq.key) {
					pq.remove();
				}
				pq.add(freq);
			}
		}
		LinkedList<Integer> res = new LinkedList<>();
		while (!pq.isEmpty()) {
			res.addFirst(pq.remove().key);
		}
		return res;
	}

	static class HeapNode {
		int val;
		int row;
		int col;

		HeapNode(int val, int row, int col) {
			this.val = val;
			this.row = row;
			this.col = col;
		}
	}

	public List<String> generateAbbreviations(String word) {
		Set<String> result = new HashSet<>();
		generateAbbreviationsUtil("", "", word, result);
		return result.stream().collect(Collectors.toList());
	}

	private void generateAbbreviationsUtil(String prefix, String suffix, String word, Set<String> result) {
		if (word.isEmpty()) {
			return;
		}

		for (int dist = 1; dist <= word.length(); dist++) {
			for (int i = 0; i < word.length() - dist + 1; i++) {
				String s = word.substring(0, i) + dist + word.substring(i + dist);
				String newWord = prefix + s + suffix;
				result.add(newWord);

				int distLength = String.valueOf(dist).length();
				if (i + distLength + 1 < s.length()) {
					generateAbbreviationsUtil(prefix + s.substring(0, i + distLength + 1), suffix,
							s.substring(i + distLength + 1), result); // with
																		// prefix
				}

				if (i - 1 >= 0) {
					generateAbbreviationsUtil(prefix, s.substring(i - 1) + suffix, s.substring(0, i - 1), result); // with
																													// suffix
				}

			}
		}
	}

	public String intToRoman(int num) {
		int[] intValues = { 1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000 };
		String[] romanValues = { "I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M" };

		StringBuilder sb = new StringBuilder();
		for (int i = intValues.length - 1; i >= 0; i--) {
			if (num >= intValues[i]) {
				int count = num / intValues[i];
				for (int j = 0; j < count; j++) {
					sb.append(romanValues[i]);
				}
				num = num - intValues[i] * count;
				if (num == 0) {
					break;
				}
			}
		}
		return sb.toString();
	}

	public int maxProduct(String[] words) {
		return maxProduct(words, 0, new int[2], 0);
	}

	private int maxProduct(String[] words, int index, int[] temp, int tempIndex) {
		if (tempIndex == 2) {
			return product(words, temp);
		}

		if (index >= words.length) {
			return 0;
		}
		temp[tempIndex] = index;
		return Math.max(maxProduct(words, index + 1, temp, tempIndex + 1),
				maxProduct(words, index + 1, temp, tempIndex));

	}

	private int product(String[] words, int[] temp) {
		String first = words[temp[0]];
		String second = words[temp[1]];
		if (containsSameLetter(first, second)) {
			return 0;
		}
		return first.length() * second.length();
	}

	private boolean containsSameLetter(String first, String second) {
		int[] charFreq = new int[27];

		for (int i = 0; i < first.length(); i++) {
			charFreq[first.charAt(i) - 'a']++;
		}

		for (int i = 0; i < second.length(); i++) {
			if (charFreq[second.charAt(i) - 'a'] > 0) {
				return true;
			}
		}
		return false;
	}

	static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public ListNode oddEvenList(ListNode head) {
		if (head == null) {
			return head;
		}

		ListNode currentOdd = head;
		ListNode evenTail = currentOdd.next;
		int dist = 2;
		while (true) {
			ListNode nextOdd = getNextOdd(currentOdd, dist);
			if (nextOdd == null) {
				break;
			}
			ListNode evenHead = currentOdd.next;
			evenTail.next = nextOdd.next;
			currentOdd.next = nextOdd;
			nextOdd.next = evenHead;
			currentOdd = nextOdd;
			evenTail = evenTail.next;
			dist++;
		}
		return head;
	}

	private ListNode getNextOdd(ListNode currentOdd, int dist) {
		ListNode current = currentOdd;
		int i = 0;
		for (; i < dist && current != null; i++) {
			current = current.next;
		}
		return current;
	}

	Solution() {
	};

	int[] nums;
	Map<Integer, Integer> map = new HashMap<>();

	public Solution(int[] nums) {
		this.nums = nums;
	}

	public int pick(int target) {
		Integer startIndex = map.get(target);
		if (startIndex == null) {
			startIndex = 0;
		} else {
			startIndex = (startIndex + 1) % nums.length;
		}

		int i = startIndex;
		do {
			if (nums[i] == target) {
				map.put(target, i);
				return i;
			}
			i = (i + 1) % nums.length;
		} while (i != startIndex);

		return -1;
	}

	public void rotate(int[][] matrix) {
		int n = matrix.length;
		for (int x = 0; x < n; x++) {
			for (int y = x; y < n - 1 - x; y++) {
				int temp = matrix[x][y];
				matrix[x][y] = matrix[n - 1 - y][x];
				matrix[n - 1 - y][x] = matrix[n - 1 - x][n - 1 - y];
				matrix[n - 1 - x][n - 1 - y] = matrix[y][n - 1 - x];
				matrix[y][n - 1 - x] = temp;
			}
		}
	}
	
	public int findKthLargest1(int[] nums, int k) {
	       PriorityQueue<Integer> pq =  new PriorityQueue<>();
	       for(int i=0; i<k; i++) {
	           pq.add(nums[i]);
	       }
	       
	       for(int i=k; i<nums.length; i++) {
	           if(nums[i] > pq.peek()) {
	               pq.remove();
	               pq.add(nums[i]);
	           }
	       }
	       return pq.peek();
	    }
	
	
	public int findKthLargest(int[] nums, int k) {
	       int kth = nums.length-k;
	       return heapSort(nums, 0, nums.length-1, kth);
	    }
	    
	    private int heapSort(int[] nums, int low, int high, int kth) {
	        if(low == high) {
	            return nums[low];
	        }
	        
	        int mid = low + (high - low)/2;
	        int pivotIndex = findPivotIndex(nums, low, high, nums[mid]);
	        if(pivotIndex == kth) {
	            return nums[pivotIndex];
	        }
	        if(kth > pivotIndex) {
	           return heapSort(nums, pivotIndex+1, high, kth);
	        }else {
	           return heapSort(nums, low, pivotIndex-1, kth);
	        }
	    }
	    
	    private int findPivotIndex(int[] nums, int low, int high, int pivot) {
	        int i=low-1;
	        for(int j=low; j<=high; j++) {
	            if(nums[j] < pivot) {
	                swap(nums, ++i, j);
	            }
	        }
	        return i+1;
	    }
	    
	    void swap(int[] nums, int i, int j) {
	    	int temp = nums[i];
	        nums[i] = nums[j];
	        nums[j] = temp;
	    }
	    
	    
	    static class HeapNodeItem {
	        int prime;
	        int count = 1;
	        HeapNodeItem(int prime) {
	            this.prime = prime;
	        }
	        
	        void incrementCount() {
	            count++;
	        }
	    }
	    public int nthSuperUglyNumber(int n, int[] primes) {
	        if(n == 1) {
	            return 1;
	        }
	        
	        PriorityQueue<HeapNodeItem> pq = new PriorityQueue<>( ( o1, o2) -> o1.prime * o1.count - o2.prime*o2.count);
	        for(int i=0; i< primes.length; i++) {
	            pq.add(new HeapNodeItem(primes[i]));
	        }
	        
	        for(int i=2; i<n; i++) {
	            HeapNodeItem curr = pq.poll();
	            curr.incrementCount();
	            pq.add(curr);
	        }
	        HeapNodeItem res = pq.peek();
	        return res.prime * res.count;
	    }

	public static void main(String[] args) {
		Solution sol = new Solution();
		int[] nums = {2,1};
		System.out.println(sol.findKthLargest(nums, 1));
		System.out.println(sol.findKthLargest1(nums, 1));
		
		
//		int[][] matrix = { {1,2,3},
//				{4,5,6},
//				{7,8,9}
//		         };
//		System.out.println("Before:");
//		printArray(matrix);
//		sol.rotate(matrix);
//		System.out.println("After:");
//		printArray(matrix);
		
//		int[] nums = { 1, 2, 3, 3, 3 };
//		Solution sol = new Solution(nums);
//		System.out.println(sol.pick(3));
//		System.out.println(sol.pick(3));
//		System.out.println(sol.pick(3));
//		System.out.println(sol.pick(3));
//		System.out.println(sol.pick(3));
//		System.out.println(sol.pick(3));
		// int[] result = sol.countBits(16);
		// System.out.println(StringUtils.toString(result));

		// int[][] people = {{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
		// int[][] result = sol.reconstructQueue(people);
		// print(result);

		// int[][] A = { { 1, 0, 0 }, { -1, 0, 3 } };
		//
		// int[][] B = { { 7, 0, 0 }, { 0, 0, 0 }, { 0, 0, 1 } };
		//
		// int[][] result = sol.multiply(A, B);
		// printArray(result);
		//
		// int[] input = {1,1,1,2,2,3};
		// System.out.println(sol.topKFrequent(input, 2));

		// System.out.println(sol.kthSmallest(matrix, 9));
		// List<String> result = sol.generateAbbreviations("interaction");
		// System.out.println(result);
		//
		// String[] output = result.toArray(new String[0]);
		// //String[] output =
		// {"2t1r4on","inter5n","i1te6n","1nte2c1ion","1n1eracti2","int3ct1o1","2teract3","i2era1ti2","inter3ion","3era2i2","i2era1ti1n","5a3on","int2a1tion","1ntera2ion","3er1c3n","in2ra1t3","2te1a1t3","i2er1ction","in1er1c1io1","int3c4","i1t1r2ti1n","1n1e1a2io1","1n1e1a1t3","i4act2n","in1e1a3o1","i1te2c1ion","i5c3n","in1e2c1io1","1nter2t1on","i1t2a1ti2","inter1c4","1nt1ra1tion","1n1eract3","inte1ac1i2","interactio1","1nt1r1ct3","i3r5n","3er1cti1n","2t1r4o1","i2erac1i1n","i10","inter3io1","i2er2ti2","in3a2i2","2t3ction","i1terac4","1nt2act3","1ntera2io1","in3ac1i2","int2a1tio1","1nt1r2ti1n","1nt4ti1n","3e1ac2on","3e1a3o1","int1rac1i1n","i4a2i1n","3e3ti2","in1e1a3on","1nter1c1i1n","i1te2c1io1","1nt1ra1tio1","1nter2t1o1","2t1ra1ti2","i1te1ac1ion","inter1c1ion","i1t2ac1io1","i4ac2on","1nte1a3o1","in1eractio1","3e1a1t1on","2t3ctio1","i2e4ion","i1t1r3i1n","i1t1r1ct1on","3e1ac2o1","1n2ra3on","int2ac1ion","i3r3i2","1n1e1a1ti1n","i1ter1c1io1","3e1a3on","2t2ac1i1n","i1ter2t1o1","int1r1ction","i1t2ac1ion","in3ac4","1n2ract1o1","i2erac4","3e1ac4","in2r1c1io1","i1te1ac1io1","in1era2i2","int2ac1i2","inter1c1io1","3eract1o1","in1eraction","in1e1a1tion","in1era2ion","4r1ct3","3e1a1t1o1","2ter1cti2","i1t1r1ct1o1","i2e4io1","7ti2","in1er1c3n","int1rac4","i1ter2t1on","1n1e1a2ion","in1era2io1","i3rac3n","1nter3i1n","in1e2c1ion","i1te4io1","3er3i2","int1r1ctio1","1n2ract1on","1n2ra3o1","i1ter1ct3","i3r1ct1o1","1nt1r1cti1n","int3c2o1","1n3a3on","1nt2a1tio1","in1e2c3n","1n1er1c1i2","i1t1r3i2","in5t1o1","i1t2a1tion","2t1r1ctio1","3e6n","2te2c1i2","int1r2ti1n","i2eract3","i1teractio1","i1t8","in1e1ac1ion","3e1ac1io1","1n1er2ti2","in3a1tion","4r1ction","int4tio1","int4t2n","4r6","1n1e1ac4","i7i1n","4rac1i1n","int3c2on","i1t2ac1i2","in3ac1ion","int2a4n","1nt3ct3","i1ter3io1","2t4t3","in1era4n","1n3a3o1","i3r1ct1on","i2e1a1t3","i1te3ti1n","in2ract1o1","inter2t1on","1n1e2cti1n","in1e1ac1io1","inte7","2ter1ct1on","i1ter1c1ion","4r2t3","3e1ac1ion","4r1c1i2","int2ac1io1","1nt2a1t2n","3erac2o1","1n2r3io1","4r3io1","2t1rac1i2","1nt8","2t4ti1n","1nte3ti2","inte1a5","i1t7n","2t1rac3n","i9n","1nt3cti2","intera4n","1nter3i2","int1ra1t3","i1te1a1tio1","i2e3t3","in3ac1io1","1n2r2t3","2te2c1i1n","1n1e1acti2","in2ract1on","i1t3ct1o1","2te1actio1","inter2t1o1","i2e1a1ti2","i1ter3ion","2ter1ct1o1","in4ct1o1","in1e1a2io1","i1t1r1c3n","4r3ion","1n1era2ion","1nte1ac4","i2e2ct1on","3e2ction","2t2a1ti1n","1ntera5","inte2c3n","in3a1ti2","1nte2c4","1ntera2i2","i1teraction","i1t5i2","1nte2c1io1","2t1r1ction","i1te1a1tion","int2ac3n","i1t2a1tio1","1n4c1i1n","inter1ct1o1","in5t1on","1n1e1ac1i1n","in1er3i1n","i1t3ct1on","in4c4","in1e1a2ion","in4ct1on","i2er1ctio1","5a3o1","intera3on","1n1era2io1","int3ct2n","1n1e6n","i1t2ac4","4r1ctio1","i2e2ct1o1","1n1er3i2","1nter1c3n","int4tion","i2e1ac2o1","1n2r1cti1n","i1te1act1on","i5ction","in1e4i1n","1n1e4ion","in1era5","2t2a2i1n","inte5on","inter1ct1on","1nt1ra5","in1e2c4","2te1a2i1n","1n1er1ct3","i4acti1n","int2act1o1","i2er2tio1","int2a2i2","i1ter1c2on","i3r2t3","int1ra5","int1ra2i2","i3ra2i1n","1nte5o1","inte1ac1io1","4ra3on","1nter1c2o1","i2er1c2on","i2er1ct1on","1ntera1t3","3erac1io1","6cti1n","inte5o1","1n2r1c1io1","1nter2tio1","i2e2c1i2","i1ter2tion","1n2rac1ion","1nt1ra1t1on","i1t1r1cti2","1nt3cti1n","1n1e4io1","i1ter1ct2n","1nte1ac3n","i4a3o1","2t1ra4n","3er1c1io1","i1te1act1o1","int2act1on","2te1action","i1t1r1ction","i1ter1c2o1","i1te1a1ti2","3e1a1tion","int2a1t1on","1nt1r4on","1n3a1ti1n","i2e2ct3","i2er1c2o1","1nter1c2on","1nte2ct2n","3erac1ion","1n5tion","1n2rac2on","interac1i2","1n1er2t1on","1n3ac3n","1n1e2c4","1n4ct2n","3e2ctio1","1n2r1c1ion","1n1era5","3era2i1n","i2er6","inte2c1io1","i2er1ct2n","int2a1t1o1","i1t1ra2i2","1nte1a1ti2","i3ra2i2","i4a3on","i1t1ractio1","3er1c1ion","int3c1io1","1nte7","i1t1r1ctio1","2t3ct1on","i3r1ction","1n4c2on","i1t3cti2","in1e1act1on","1nt1r4o1","3e1a1tio1","in2r3i1n","inte1act3","i4a1ti1n","2te3t1o1","in1erac3n","1n5tio1","i3ra1ti1n","2terac2on","1n2rac2o1","1n2r3ion","in1er4on","i1te3t2n","3erac2on","i1t4ti2","1n1er2t1o1","inte2c1ion","int3c1ion","int1r1ct1o1","i5ctio1","1n1e2c1i2","inter1ct2n","2t3ct1o1","i1t1raction","1nte5on","i1teract3","i2er2tion","3eract2n","in2rac4","i1te2c1i2","2tera1ti1n","1n4c2o1","i1ter1cti1n","inte1ac1ion","i1ter2tio1","2t4ti2","i1te1a4n","i3r1c3n","2te3t1on","int1ra1t2n","5a5","3era5","2terac2o1","3e2c4","2t3c1i2","1n1eracti1n","3erac3n","in1e1ac1i2","int1r1ct1on","i1terac1i2","1nte2c2o1","1nt2a1t1on","i1t3c1io1","in4ction","int4t1o1","1nte1a1ti1n","i1te2cti1n","inter2tion","1ntera1t1o1","2t1r1ct1on","in1e1a1tio1","2te1ac2o1","2t2ac2on","2ter2ti2","in2r1c1ion","i2e1action","int1r3i1n","i3rac2on","i1t1rac3n","2t2ac2o1","4r2t2n","2t1r2t2n","1n2rac4","5ac1io1","2ter1ction","inte3ti1n","i2e7","1n1e2ct3","inter1ct3","in2r1c1i2","int1r1cti2","1nterac1i2","1nte3tio1","1nt1r3io1","in1erac1i2","i1t3c1ion","2teraction","inter2tio1","i3r1ctio1","1n3acti1n","intera1ti2","1nt2a1t1o1","in4c2on","1ntera1t1on","in1e1act1o1","2t1r1ct1o1","1n1er2t3","i3raction","i2er2t3","i3ractio1","2ter1ctio1","1nt1r3ion","2ter4o1","i3rac2o1","in4c2o1","i2er1ct3","inter1c2on","1nte3tion","i1t4t3","5ac1ion","2teract2n","in1er4o1","int5io1","1nt1rac1io1","int1ra1ti1n","in5tion","i3ract2n","1nt1ra1t1o1","1nte1ac1i2","1nt1r1cti2","in1e2cti1n","2teractio1","4r2t1o1","i1t2a1t1o1","2tera2i1n","1n2r1ct3","9o1","in1er2t1on","in1e6n","1nt5ion","2t1ra3o1","inter1c2o1","2t2action","2t2ac4","1nt2ac1io1","1n2ra2i1n","i1te1a1t1o1","i1te1ac1i2","1nt1rac1ion","1nt1r1c1io1","2t2act2n","2te1act2n","inte1a1ti2","in5tio1","in4ctio1","4rac4","1nte2c2on","3e1a2i1n","int4t1on","2t1ra1ti1n","int2a1ti2","i1t2a1t1on","in1er1c1ion","9on","i1tera1ti2","i2e1actio1","3e1act2n","int1r1c3n","2te1ac2on","i6t3","1nte2cti1n","i2er1ct1o1","in1er2t1o1","1nt5io1","i3r3i1n","i1te1a1t1on","1n2rac1io1","1n2ract2n","in4ct2n","1nt2ac1ion","in3ac3n","interaction","in5ti2","4ra3o1","1nt1r1c1ion","5a2i1n","1ntera1ti2","in3acti1n","i1te5o1","i1te1actio1","i2eraction","3e4io1","1nte2cti2","i1t3ct2n","interac4","2te2ct2n","i1te3t1on","int2a2io1","in1erac1ion","1n1era1tio1","3er2tion","i2eract2n","i2er2t1on","1n1e2ct1on","1nte1a1t1o1","1n1e2ct1o1","in1era1tio1","inter1c1i2","i1t1r2ti2","3e1act1on","3er1ct2n","i1t1ra5","2te3tion","i1te1a1t2n","2te1ac3n","in2rac3n","i1t2a2ion","i1t2ac3n","1n4cti1n","int1ra2io1","1n2ract3","1n1e1ac3n","1n1e2ct2n","1nt1racti2","int5i1n","inte3t1o1","1nte1a1t1on","i1te3t1o1","4ract2n","i3r2ti1n","i1te5on","3e4ion","4ra2i1n","i1t4tion","2te2cti1n","in1erac1io1","int1r2ti2","i2er2t1o1","2ter1c1i2","inte1a1t1o1","1n1era1tion","2t2actio1","int2actio1","i1ter1c4","in2ra2i1n","intera1ti1n","in2r2t3","1nt1ractio1","i2erac2on","inte1acti1n","inte3t1on","4ra1t1o1","1n2r1c2o1","int1ra2ion","3era1t1on","1n1erac1i2","i1t1ract1on","in4cti1n","i2er1c4","5act3","5act2n","in2ract2n","1n1e1action","1nt2a2i1n","2te2c2o1","1n1er1ctio1","1n2r2tion","i2e3ti1n","1nte2ctio1","2t1ra2ion","in1e1actio1","1n4c3n","in3a1t1o1","int1ractio1","1nt1raction","2te2c2on","2ter4on","2t2ac1i2","int1r1c4","int1r1c1i1n","1n5t1on","i2erac2o1","in2r1cti2","1n2r1c2on","i3rac4","1nt2act1on","in1e1action","inter1cti1n","i2er3i1n","3era1t1o1","2t1r3i2","1nt2a3on","1n1er1ction","1n3acti2","3e1act1o1","2t1ra2io1","in1era1ti2","1n2r1ct2n","in1era1tion","in3a3o1","1n2r2tio1","4rac2on","4r1cti2","2ter3io1","in3a3on","in1e1act3","in3a1t1on","1nte6n","2te4i1n","2t5i2","interac3n","int1raction","2te3tio1","in7on","i2e1a2i1n","5ac2on","1n5t1o1","1n2r1ct1o1","1n2r5n","i3rac1i2","interac1ion","2te4i2","3erac4","1n1e1a1ti2","in1er2ti1n","1nt1r2t2n","3er1c1i1n","2t7n","i1t1rac4","i2e1a1ti1n","int3c3n","1nterac1io1","int3cti1n","1nt2a3o1","1nter4o1","i1t1r1c1ion","2te1ac1ion","1nt2act2n","3er2t3","i1terac1i1n","5a1t1o1","1n1e1ac1i2","i3ra1t3","i1tera4n","i4a1tion","5ac2o1","i2era1t1o1","4r1c1i1n","in7o1","1nt1ra1t2n","in1e1a1t1on","1n1er3ion","interac1io1","1n2raction","in1e2ct1on","1nte1action","1n3a1t2n","1nterac1ion","in2rac2on","1n1e2c2o1","int2acti2","int1ract3","1ntera1tio1","i2e6n","2t1ra1t3","1nt1ra3o1","1nter4on","8i1n","5a1t1on","2te1ac1io1","2tera3o1","1n1e1actio1","2t3c3n","2tera4n","in1er1cti1n","int1ra4n","i1tera1t2n","in1e1a1t1o1","i4a1t2n","1nte1actio1","1nter2ti1n","in3act2n","6cti2","1nt2act1o1","1nt1r2t3","in1e2ct1o1","in2rac2o1","1n9","2t1r2t1on","4racti1n","1nt3c1ion","inte1a4n","1n1er1cti2","i2e1act1o1","i2era2i1n","1ntera1tion","2te1a2i2","i1te1a1t3","inte1a1t1on","in3ac2on","1nt1ra3on","2tera3on","i2era1t3","1n1e2c2on","in2r1ct1on","i1tera1t1o1","1n6i1n","in2r1c3n","1n1era1ti2","2t1r5n","i6ti2","4ra1t1on","2t1r2t1o1","inte2ction","1n1e3tion","1nt4t3","1nt3c1io1","i2e1act1on","int1racti2","i2eractio1","in3ac2o1","inte3t2n","1n3a2i2","in1e1a1ti2","3e1a1ti1n","in2r1ct1o1","in3a5","3e2cti2","i1t1r1c1io1","i2er1c1i1n","i2era1t1on","i1t2a2io1","inte1a2i2","i1tera1t1on","in1e3ti1n","5acti1n","1n1e3tio1","int1r5n","1n1erac4","3eracti1n","i2e1act3","int2a1t3","i4a1tio1","1n1er3io1","2t1racti2","2t1racti1n","inte2ctio1","2te2c3n","in4c1io1","i1te2c2on","intera3o1","1n1e7","1n2r2t1o1","2t2act3","i2e1ac1io1","i1te3tio1","4r5n","i2e3t2n","2t1ra1t2n","1n1era1t1on","int8","1nte1ac2o1","2t1r3i1n","11","3e1actio1","inte2ct3","int1ra1t1on","i1t2acti1n","interact1on","i4a2i2","2t1rac1io1","3e2c1i1n","in2r2ti1n","i2e1ac2on","1nt1ract1o1","3e1a1t2n","2t2act1o1","i1tera3on","i1t1ra1ti1n","in1e1ac2o1","in2r1ction","in4c1ion","2ter1ct3","i1teracti2","i5c1i1n","inte1a2i1n","2t1r1c1io1","i1te2c2o1","in2ra1ti1n","in1er1c1i2","6c3n","int4t3","i1te1a2ion","1n1e1a2i2","inte2c1i2","i4acti2","in6i1n","i1t1ra2i1n","i1te1a2io1","2terac1i2","2t2act1on","1n3a1t3","i7i2","inter2t3","3e1action","in2ra2i2","1n1e1a5","1nt1ract1on","1nt2ac2o1","int1ra1t1o1","1nt1r1ct1o1","interact1o1","2t1rac1ion","1nt2a1ti1n","2t5i1n","1nteract3","i1t3cti1n","inter2t2n","4r2ti1n","1n3ac1ion","3era1tio1","i1t5io1","int3c1i2","in1e3ti2","2t1r1c1ion","1nte2ct1on","1nt1ra2ion","1nter1c1i2","i2e1act2n","1n2ra4n","i1t1r2t3","interac2on","i1te2c3n","1nte1ac1i1n","1nt1rac3n","i2e2cti2","3e2ct3","2te6n","in2r5n","1n2r2ti2","1nt2ac2on","1nt2actio1","inte4i2","i1t5ion","in1era1t3","i5c2o1","1nt1r1ct1on","inte6n","1n2r2t1on","1nte4i1n","2te1a1ti1n","i2e1a4n","4rac3n","2ter2ti1n","in2r3i2","1nte2ct1o1","2te2c1io1","i2e1ac1ion","1nte1ac2on","1n3ac1io1","1nt1r3i2","3era1t3","interac2o1","6c4","in3a1tio1","1n2ractio1","1nt2action","1n1er6","i1ter2t2n","3er3i1n","1ntera4n","1n1era3o1","i1t1ra1t3","inte1a1t3","6c1ion","2t3c1ion","i1ter4on","i6tio1","2t1r1c3n","i3rac1i1n","3e3t3","1n1e5o1","2ter3ion","2t1ra2i2","i1te1a2i2","1n1e1act1o1","5a1tio1","4rac2o1","1nt5i2","2tera1ti2","4r1c3n","2t1r2tion","1n2r1ct1on","1n2ra1ti1n","inte2ct1o1","i1te1acti2","2t1r1ct2n","i1t2a3on","i1t1rac2on","1nt3ct1on","interact2n","i1t1ract1o1","3era1t2n","inte4ion","1n1era3on","3era1tion","2te3t3","1nte1a3on","1n5t3","i3r1c1i1n","2t3c1io1","inte1act2n","1nt2ac4","3e1act3","1n1e1act1on","5a1tion","1n1e5on","int1r4o1","1nt1ra2io1","1nter2ti2","2t3cti2","2t1r2tio1","1nter1cti1n","i1tera5","2t2a1ti2","i3ra1t1on","i1t2a3o1","i1te2c4","4ra2i2","i1t1rac2o1","1nt3ct1o1","i2e2ction","i5c2on","in2r1ctio1","1n1e3t2n","i1tera3o1","2terac1i1n","inte4io1","i3ra1ti2","inte1a1tio1","1n1er1c1i1n","1n1e2c1ion","2t1r1c2on","2te1act1on","in1e2cti2","i4ac3n","i1t1ract2n","int1r4on","i3r1cti2","1nt4ti2","1n1er2ti1n","2ter1c1i1n","in2ractio1","i1te4i1n","1nterac2o1","i1tera2io1","int2action","1n1e1a4n","inte1ac2o1","i4ac1i1n","in9","i3ra1t1o1","in1e1ac4","4ra1tio1","i4a1t1o1","3er1c2o1","i2e2ctio1","1n1er2t2n","1nteracti1n","1n3a2ion","2ter1c4","i1t1r1c1i2","intera2i1n","3er1c2on","1n1e3t1o1","1n1era1t1o1","in1er3i2","3e3ti1n","4r3i2","i1tera2ion","int2a2ion","i1tera1tion","6c1io1","i2era5","3er2tio1","i1te1action","2tera1t1o1","i2e2c4","i1t3ct3","i6tion","1n1e2c1io1","1n2r1c4","2te1act1o1","2t1r1c2o1","i5ct2n","1n1erac1i1n","i1tera1tio1","inte1a1tion","in2raction","4ra1tion","inte1ac2on","1n1e3t1on","2te1a1ti2","1n3a2io1","1nte2c3n","i4a1t1on","inte2ct1on","inter1c1i1n","3e1a2io1","3er5n","1nteract2n","3er6","1n3act1on","i1t1r3ion","i1te1ac1i1n","1n1er1c1ion","i1t2a1t3","i1ter3i1n","in1e2c1i2","2tera1t1on","int2ac1i1n","2t6on","1n1e1act2n","1nt1rac1i1n","2t2ac1ion","1nt5i1n","4r3i1n","i1t1r1ct2n","2t2acti2","2te5on","inte1ac4","i2e1a1t1o1","2t2a1tion","1nteraction","int1ra1tio1","1nter1c4","i2er4o1","int1ra1ti2","2t1r1cti1n","3e1a2ion","1n2ra1t2n","i1te1a1ti1n","4ra4n","1nter1ct3","1n3act1o1","1n3ac1i2","in1eract2n","i1tera2i1n","3e2c1i2","in1e1a1ti1n","2t1ra5","1n1er1c1io1","i3r6","1nt1r1c2on","1nt3c2on","i1t1r3io1","i3rac1io1","2t6o1","1n3a4n","2t2ac1io1","3eracti2","1n1erac3n","i2e5o1","i3r2ti2","in4cti2","1nterac2on","int1r3io1","i3r1c4","1n1era4n","i3racti2","2te5o1","2terac3n","int4ti1n","i2er4on","2t2a1tio1","1nteractio1","1nt1r1ction","1nte2c1i1n","6ct1on","5a1ti2","int4ti2","1nte1act1o1","i1t1r1c2on","4ract1o1","1nte2c1i2","1nt1r1c2o1","1n1e1ac2o1","2te1ac1i2","i5ct3","1nt3c2o1","1ntera2i1n","i2er1cti1n","2t1ract3","i2e1acti1n","inter1cti2","1n2r4o1","i2e5on","3er1ct3","int1r3ion","i1t1r2tion","4r1cti1n","in1er6","i4a2io1","2t1r2ti1n","i1te4i2","in1erac2o1","1nt1r1ctio1","1n5t2n","1nt1ra1ti1n","6ct1o1","i2erac1ion","in3ac1i1n","2teracti1n","1n1e1ac2on","3er1c4","1nt1ra2i2","2t1ract1o1","4ra1ti2","1n2r6","2t1rac4","i1ter4o1","in2ra1ti2","1nt1r1c3n","1n2r4on","i1t1r2tio1","1nter1c1ion","5a1t3","i1t1rac1i2","in1erac2on","i4a2ion","5ac1i1n","int1ra1tion","intera1t3","int5i2","2te1a5","i2e1acti2","1n1erac2o1","1nt3ctio1","2te1a3o1","2ter2t1on","i1t1r1ct3","i3ra1t2n","1n1er1c4","i1t1rac1ion","1n3a5","3er1ctio1","2te3t2n","1n1er5n","1nte1a2i1n","i6t1on","2te2c1ion","1n1e1a3on","i1t1ra1t1on","i5c1io1","2t2a1t2n","i4act1o1","1nt3c3n","2t2a4n","1nter1c1io1","i3ra1tion","int1r1cti1n","1n2r1ctio1","3e2cti1n","2te1a3on","2ter2t1o1","2te1a4n","inte2c1i1n","1nt1r6","i3ract3","int1rac1i2","in1eracti1n","1n4c1ion","i1t1r1c4","i1t1ra1t1o1","in1era2i1n","i6t1o1","in1e1a1t3","1n1e1ac1ion","1n4c1i2","in1e1a2i1n","inte1ac1i1n","i3ra1tio1","1nt1r5n","2t4t2n","in1er1ct3","in4c3n","1nte1act2n","1n2r1ction","1n2ra1t3","in1e1ac1i1n","i1te1ac3n","2t1r2ti2","i1t3c4","in1e1ac2on","int2a1ti1n","2ter6","1nt2a2i2","5ac1i2","1nte4i2","in2r2t2n","1n2racti2","3er2t1o1","in6i2","in1e2ct3","1n1eract1on","3e1acti2","int2act3","in3a1ti1n","i2erac3n","i1t4t1o1","i7ion","1nt1r2tio1","3er1ction","3er2t1on","i5c1ion","i1t1rac1io1","i1te2c1i1n","i1te3tion","2t3cti1n","1n1eract1o1","1n1erac2on","in1e3t3","i1te2ct2n","1nt1r2tion","i1ter1c1i1n","in2rac1i2","i4act1on","1nt7n","6c1i2","in1e1act2n","2t8","i2er1c1i2","2t3ct2n","2t2ac3n","2t4tion","1nt1r1ct2n","i7io1","i2e2c3n","in5ti1n","int3cti2","1n3actio1","4rac1io1","in2r1ct3","2tera1tio1","i3r1cti1n","1n1e2ctio1","i2e1a3o1","in1era1t1on","2t1r1c1i2","1n1era2i1n","1nter1ct1on","1n2r3i1n","1n6ion","2ter5n","i4a5","3e1a4n","1n2ra2ion","1n1er4on","int1r1c2o1","1nteract1on","in1e4i2","i2era4n","1n1er1ct1o1","int3c1i1n","7t2n","1nte1a1tio1","1n8n","i1teracti1n","4r4o1","i1t1racti1n","i1ter5n","6ction","in1er2t3","2tera1tion","i1t6o1","in1er2ti2","int1r2tio1","int2a1t2n","int1r1c2on","i4ac4","in1era1t1o1","2te7","i2e1a3on","1nter1ct1o1","inte3tio1","1n2ra2io1","i1t4t1on","1n6io1","1n1er4o1","int1ract1o1","1n1eract2n","in1e4ion","2t1ra1tio1","intera2i2","4r4on","1nteract1o1","inte2c2on","1n4c1io1","6ctio1","i1t2a2i2","inter2ti1n","1n1e4i1n","i1t1r2t1on","1nt1ra1ti2","inter1c3n","1n3act3","int1r2tion","2t2a3on","1n1e1ac1io1","i1t6on","1n1er1ct1on","1nt6on","5a4n","1nt6o1","int1ract1on","2ter2t2n","3eract3","4r1c1ion","1n1er3i1n","i2er5n","3era2io1","i2era1t2n","3e4i2","in3a2i1n","1nt1ra1t3","i1te1a3o1","1n2rac1i1n","i1terac3n","int7n","i1t2acti2","inte2c2o1","3e3t2n","4ra1t3","4rac1ion","int1r1ct2n","2t1r6","1nt3ction","i5c1i2","1n6i2","in1er1cti2","1n1e1a3o1","2te1acti1n","2t2a3o1","i1t1r2t1o1","int1r6","1n1e2ction","2tera2io1","i3racti1n","4r1c1io1","i8o1","3erac1i1n","3e1ac1i1n","in2ra3o1","1n2r1c1i1n","1nte3ti1n","3era2ion","i1te1a3on","inte1ac3n","in2ra1t1on","i2e4i1n","i1ter6","2t1ract1on","i3r1ct3","in1eract3","in1e2ction","2t2a5","i1ter1c3n","i1tera2i2","1nt1rac4","1nter1cti2","2tera2ion","1nt2a1ti2","2t1ra2i1n","in2r3ion","i3r1c1i2","i3ra4n","i1te3ti2","inte2ct2n","2te4io1","inte3t3","2t2acti1n","4ra5","in1e2ctio1","3er1c1i2","1nte3t3","i1t2ac1i1n","1nte1a4n","in2ra3on","1n2r1c3n","i8on","in2ra1t1o1","i3ra2io1","inte3ti2","2te2ct1o1","2t2a2io1","1nte1act1on","i1t1r1c2o1","2t1ra1tion","i1t4t2n","i2er2ti1n","in1er1c1i1n","1nte2ction","in1e4io1","1n1e1a2i1n","in2rac1i1n","2te4ion","in2r3io1","1n1eraction","1nt2a5","i1te1a5","i1t2a4n","7ti1n","1nt2ac1i1n","1nt1r2t1o1","interacti1n","1n2ra2i2","2t1r2t3","1nter3ion","int1r1ct3","i5cti1n","2te1a2ion","1n1era2i2","2te2ct1on","2t2a2ion","1nte1a1tion","i2eracti2","in2r6","i1t4tio1","2t1ract2n","in3act3","inter3i1n","in1erac1i1n","i2era1tion","1n2ra5","3e1a1t3","in2r1c1i1n","3er2ti2","i2eract1o1","i3ra2ion","1n1eractio1","1nte1act3","i1t2a1t2n","1n1e4i2","4rac1i2","i4a1t3","1n1er1c3n","i1t3c2on","i4action","i1ter1cti2","inte3tion","1nt1r2t1on","1nt4tio1","1nter3io1","1nt1rac1i2","i1t1ract3","i1t3c1i1n","i4ac1i2","2te1a2io1","i2er1c3n","1nte3t2n","1n3a1ti2","1n3action","i1t1r1cti1n","i2era1tio1","i2e1a5","i2eract1on","1nt1r3i1n","i1t3c2o1","i2e1a1t2n","3e7","i1t1ra1ti2","i4actio1","1n5ti1n","2terac4","i2era2i2","1n2r1c1i2","in1erac4","1n1e2c3n","1nt2ac1i2","i2e2c1i1n","1nt4tion","inter3i2","1nt2ac3n","1nt3ct2n","i6t2n","1ntera1ti1n","i2er3io1","1n1e3t3","inter1ctio1","3er2t2n","1n2ra1t1o1","3e2ct1o1","int2ac2on","1n1e1acti1n","1n1er1ct2n","2te1acti2","in5t3","inter2ti2","1nte1a2i2","1nte1a1t2n","i2e4i2","1n1er2tion","i1te7","1nter5n","int2a5","2t1rac2on","int1rac2on","2t4t1on","i3r1ct2n","1nter1ctio1","i2era2ion","3er4on","inte2cti1n","i2e2c1io1","1n1e3ti1n","int1r1c1ion","1nt1r1c1i1n","in1e1acti1n","inter1ction","i2er3ion","2tera1t3","i1t2act3","in2ra5","i1t1racti2","2ter2t3","int1ract2n","4r2t1on","int2ac2o1","3e1a1ti2","i3r3io1","in1era3o1","int1r2t1o1","i1te1ac4","int1r2t1on","6c2o1","1nt1ra4n","1n1erac1ion","1n1era1t3","i3r3ion","in1e1a1t2n","2t1ra3on","1n1er2tio1","2t4t1o1","5action","i1te2ctio1","3e2ct1on","i2e2c1ion","i2e1ac4","i1t2act2n","i2era2io1","3er4o1","interac1i1n","in1e2c2o1","5a2ion","i2eracti1n","2tera1t2n","in3action","2t1ra1t1on","4ra2io1","8io1","i1te2ction","i2e3ti2","1n1er1c2o1","i5ct1o1","3er2ti1n","i3r1c2on","2t3c1i1n","i1terac1ion","1n1erac1io1","i1ter3i2","6c2on","2ter1cti1n","1n1er1c2on","i1t2a2i1n","in1e3tio1","7tio1","7t3","5actio1","int5ion","1nterac1i1n","i2er3i2","4ra2ion","6ct2n","5a2io1","i3r2tion","inte1acti2","in3actio1","2te1act3","i1t4ti1n","in1er2t2n","8ion","1n2ra1t1on","in1e2c2on","1n4ct3","int1rac2o1","i5ct1on","3e2c2o1","in2rac1io1","in3acti2","1n1era1ti1n","i1terac1io1","2t1ra1t1o1","in1er1ctio1","in1e7","in1er1ction","in2rac1ion","5ac3n","2t1rac2o1","in4c1i2","int1r3i2","in2ra2ion","7tion","in1er5n","i4ac1io1","intera1t2n","i3r2tio1","i4a1ti2","i1t2act1o1","1nt3c1i1n","2te2c4","1nte4io1","1nt3c1i2","3e3t1on","4raction","2tera5","2t1r1c1i1n","2te1a1t1on","1n4c4","1nte3t1o1","1ntera3on","2t1r1c4","in5t2n","2te1a1t2n","1n2r2ti1n","in1e3t2n","i2e3tion","in2ra1tio1","i1ter1ctio1","i2er1c1io1","inte1actio1","1n2rac1i2","i1te3t3","1nt1racti1n","3e1ac1i2","i3ract1o1","1nt4t2n","inte1a1t2n","2t1ractio1","in3a2ion","in2ra2io1","3eractio1","3e3t1o1","i3r4o1","in3a4n","1ntera3o1","1nte4ion","4ractio1","in1e1ac3n","in2r4o1","1nte3t1on","in1e1a4n","interact3","1n1er1cti1n","i2e2ct2n","5a2i2","i1ter1ction","i2er1c1ion","i2e1ac3n","i3ract1on","intera5","1n1e1a1t1on","int1racti1n","in1e2ct2n","in1e3tion","i2e1a2ion","2t1raction","1nt4t1o1","in1er2tion","3eraction","inte2c4","i1t1ra1t2n","in1er1ct2n","i3r4on","i2er1cti2","i2e1ac1i2","in2r2t1on","2te2ct3","int3ction","1nter1ct2n","intera1tio1","2teract1on","1n4ction","i2e1a1tion","i1t2a5","i1t1r4o1","i1t1r2t2n","i1ter2ti2","2t1r3ion","1nterac4","2te1ac1i1n","in2r4on","1ntera1t2n","1n2rac3n","in1er1c2on","1n3a1tio1","2te2ctio1","2t3ct3","i1t2ac2on","3era3o1","2te2cti2","int3ct3","in1er2tio1","1n1e1a1t1o1","i2e1a2io1","1nt4t1on","4r2ti2","1n2racti1n","1nte1acti1n","i2e2c2o1","i1ter2ti1n","1nte1a5","1n4cti2","int3ctio1","i1t2act1on","in2r2t1o1","1nter2t2n","i2e1a1tio1","2t2a1t3","intera1tion","3e4i1n","2te1a1t1o1","1n4ctio1","2t1r3io1","2teract1o1","in1er1c2o1","i1t2ac2o1","inte2cti2","3era3on","1n3a1tion","in2ra1tion","i2e3tio1","2te2ction","1nt2a4n","1n1e1act3","inte1action","2teracti2","3erac1i2","4r1c2o1","interacti2","3e1a2i2","1nter2tion","i3ra3on","101","1nter6","in3a2io1","6ct3","int1rac3n","i2e2c2on","2terac1ion","1n3ac1i1n","1nt1ract3","in1e1a5","4r2tion","in1er3io1","i1t1ra3o1","i1te1ac2on","5acti2","1n1e2c1i1n","1nt1ra2i1n","1nterac3n","i1t3ction","1n5ti2","2ter1c1ion","in1era1t2n","1nte1ac1ion","int1r2t2n","in2r1ct2n","4r1c2on","i5c4","4racti2","10n","i1t1ra3on","inte1a3on","i3ra3o1","i1t5i1n","2t4tio1","i1te2ct1o1","3e5o1","1n2ra1ti2","i1ter2t3","1n7on","i1t1r4on","i5cti2","1n3a2i1n","int1r2t3","2terac1io1","4r2tio1","in2r2tion","4r1ct2n","3er3io1","1n1e2cti2","i1te2ct3","1n1e1a1t2n","in1er3ion","2t3c2on","in1eract1o1","1nte2ct3","2ter1c1io1","2t3c2o1","1nt2acti1n","7t1o1","i1te1act3","in2racti1n","in2racti2","1nte1ac1io1","1nter2t3","3era4n","inte1a3o1","1nte1a1t3","i2era3o1","1n1e3ti2","i1te2ct1on","2t2a1t1on","1n7o1","intera2io1","in2r1c4","1n2ra1tion","in4ct3","i1t1rac1i1n","i1t2a1ti1n","i1t1r1c1i1n","in1er1ct1o1","int1ra3o1","6c1i1n","i3r2t2n","1nte1a2ion","in1eract1on","2ter1c3n","int2a3o1","i1tera1t3","i2e1a2i2","1nt1ract2n","2t1r1cti2","2ter1ct2n","5act1on","i1te1act2n","7t1on","4ract3","3er1cti2","3e2c1ion","2t2a1t1o1","1nteracti2","in1e3t1on","i2era3on","4ra1t2n","in1e5o1","2t1r1ct3","i3r2t1o1","1n2ra1tio1","i2e2cti1n","in2r1cti1n","inte4i1n","2tera2i2","1nte1a2io1","int1ra3on","1n3ac4","i1t1ra1tion","int2a3on","i1te1a2i1n","in1er1ct1on","3era1ti2","5act1o1","3e1a5","3e2c3n","i3r2t1on","2t1rac1i1n","in3act1on","in1e3t1o1","1nt2a1tion","2t5ion","in1e5on","1n4ct1o1","i1t1r5n","2ter2tio1","2te1a1tio1","3e3tio1","1nt1rac2o1","1nt1r1c1i2","3er1ct1on","3e2c2on","i2erac1i2","inte1a2io1","1nte1acti2","1nt2a2ion","2ter1c2on","2t3c4","i1t1ra1tio1","i2erac1io1","i1teract1on","1n1e1a1tion","int2acti1n","8i2","1n1era1t2n","1n2r2t2n","1n2r3i2","i1ter1ct1on","i1t1ra2io1","1nt1r1c4","i1te4ion","in2r2ti2","in8n","inte1act1on","in6io1","i1t3c3n","in3act1o1","3eract1on","i4ac1ion","5a1t2n","2t5io1","int6on","intera1t1o1","2te1a1tion","intera2ion","i4ac2o1","3e3tion","inter4on","1nt1rac2on","1n3act2n","in1e2c1i1n","2ter2tion","4ract1on","in2r1c2o1","in2ra4n","i1teract1o1","1nt2a2io1","i1tera1ti1n","3er1ct1o1","i1te2cti2","int2a2i1n","i1te1acti1n","in1era1ti1n","i2e1ac1i1n","i3r1c2o1","i3ra5","2t2a2i2","1n1e1a1tio1","i1t3c1i2","inte1a1ti1n","inte1act1o1","1nt1r2ti2","1nt3c4","4ra1ti1n","3e2c1io1","1n2r1cti2","int6o1","i1terac2o1","in1e1acti2","in2ra1t2n","i4act3","2te3ti2","intera1t1on","in1er1c4","3er3ion","inter4o1","1nt2acti2","i2e3t1o1","i1t1ra4n","i3r1c1io1","in2r1c2on","3e1ac3n","in2r2tio1","in1era3on","1n3a1t1o1","int1rac1io1","i6ti1n","2ter3i1n","i3rac1ion","4r1ct1o1","3e2ct2n","inter6","in3a1t2n","1n3ac2o1","in1e1a2i2","2te3ti1n","int1r1c1i2","i1t2action","2te1ac4","i1terac2on","4r1c4","int2act2n","i1te1ac2o1","1n4ct1on","i2e3t1on","in3a1t3","in1eracti2","in4c1i1n","in2ract3","3era1ti1n","inte1a2ion","i3r1c1ion","int3ct1on","i1ter1c1i2","i1teract2n","int2ac4","2ter1c2o1","i1t3ctio1","i2er2t2n","i1t1ra2ion","2ter3i2","in6ion","1n3a1t1on","5ac4","4r1ct1on","int1rac1ion","3e1acti1n","1n3ac2on","i1ter1ct1o1","1nt2a1t3","5a1ti1n","i4a4n","1nter1ction","i2e1a1t1on","i1t2actio1","int1r1c1io1","i1t1r6","int1ra2i1n","3e5on"};
		// String[] expected =
		// {"11","10n","9o1","9on","8i2","8i1n","8io1","8ion","7t3","7t2n","7t1o1","7t1on","7ti2","7ti1n","7tio1","7tion","6c4","6c3n","6c2o1","6c2on","6c1i2","6c1i1n","6c1io1","6c1ion","6ct3","6ct2n","6ct1o1","6ct1on","6cti2","6cti1n","6ctio1","6ction","5a5","5a4n","5a3o1","5a3on","5a2i2","5a2i1n","5a2io1","5a2ion","5a1t3","5a1t2n","5a1t1o1","5a1t1on","5a1ti2","5a1ti1n","5a1tio1","5a1tion","5ac4","5ac3n","5ac2o1","5ac2on","5ac1i2","5ac1i1n","5ac1io1","5ac1ion","5act3","5act2n","5act1o1","5act1on","5acti2","5acti1n","5actio1","5action","4r6","4r5n","4r4o1","4r4on","4r3i2","4r3i1n","4r3io1","4r3ion","4r2t3","4r2t2n","4r2t1o1","4r2t1on","4r2ti2","4r2ti1n","4r2tio1","4r2tion","4r1c4","4r1c3n","4r1c2o1","4r1c2on","4r1c1i2","4r1c1i1n","4r1c1io1","4r1c1ion","4r1ct3","4r1ct2n","4r1ct1o1","4r1ct1on","4r1cti2","4r1cti1n","4r1ctio1","4r1ction","4ra5","4ra4n","4ra3o1","4ra3on","4ra2i2","4ra2i1n","4ra2io1","4ra2ion","4ra1t3","4ra1t2n","4ra1t1o1","4ra1t1on","4ra1ti2","4ra1ti1n","4ra1tio1","4ra1tion","4rac4","4rac3n","4rac2o1","4rac2on","4rac1i2","4rac1i1n","4rac1io1","4rac1ion","4ract3","4ract2n","4ract1o1","4ract1on","4racti2","4racti1n","4ractio1","4raction","3e7","3e6n","3e5o1","3e5on","3e4i2","3e4i1n","3e4io1","3e4ion","3e3t3","3e3t2n","3e3t1o1","3e3t1on","3e3ti2","3e3ti1n","3e3tio1","3e3tion","3e2c4","3e2c3n","3e2c2o1","3e2c2on","3e2c1i2","3e2c1i1n","3e2c1io1","3e2c1ion","3e2ct3","3e2ct2n","3e2ct1o1","3e2ct1on","3e2cti2","3e2cti1n","3e2ctio1","3e2ction","3e1a5","3e1a4n","3e1a3o1","3e1a3on","3e1a2i2","3e1a2i1n","3e1a2io1","3e1a2ion","3e1a1t3","3e1a1t2n","3e1a1t1o1","3e1a1t1on","3e1a1ti2","3e1a1ti1n","3e1a1tio1","3e1a1tion","3e1ac4","3e1ac3n","3e1ac2o1","3e1ac2on","3e1ac1i2","3e1ac1i1n","3e1ac1io1","3e1ac1ion","3e1act3","3e1act2n","3e1act1o1","3e1act1on","3e1acti2","3e1acti1n","3e1actio1","3e1action","3er6","3er5n","3er4o1","3er4on","3er3i2","3er3i1n","3er3io1","3er3ion","3er2t3","3er2t2n","3er2t1o1","3er2t1on","3er2ti2","3er2ti1n","3er2tio1","3er2tion","3er1c4","3er1c3n","3er1c2o1","3er1c2on","3er1c1i2","3er1c1i1n","3er1c1io1","3er1c1ion","3er1ct3","3er1ct2n","3er1ct1o1","3er1ct1on","3er1cti2","3er1cti1n","3er1ctio1","3er1ction","3era5","3era4n","3era3o1","3era3on","3era2i2","3era2i1n","3era2io1","3era2ion","3era1t3","3era1t2n","3era1t1o1","3era1t1on","3era1ti2","3era1ti1n","3era1tio1","3era1tion","3erac4","3erac3n","3erac2o1","3erac2on","3erac1i2","3erac1i1n","3erac1io1","3erac1ion","3eract3","3eract2n","3eract1o1","3eract1on","3eracti2","3eracti1n","3eractio1","3eraction","2t8","2t7n","2t6o1","2t6on","2t5i2","2t5i1n","2t5io1","2t5ion","2t4t3","2t4t2n","2t4t1o1","2t4t1on","2t4ti2","2t4ti1n","2t4tio1","2t4tion","2t3c4","2t3c3n","2t3c2o1","2t3c2on","2t3c1i2","2t3c1i1n","2t3c1io1","2t3c1ion","2t3ct3","2t3ct2n","2t3ct1o1","2t3ct1on","2t3cti2","2t3cti1n","2t3ctio1","2t3ction","2t2a5","2t2a4n","2t2a3o1","2t2a3on","2t2a2i2","2t2a2i1n","2t2a2io1","2t2a2ion","2t2a1t3","2t2a1t2n","2t2a1t1o1","2t2a1t1on","2t2a1ti2","2t2a1ti1n","2t2a1tio1","2t2a1tion","2t2ac4","2t2ac3n","2t2ac2o1","2t2ac2on","2t2ac1i2","2t2ac1i1n","2t2ac1io1","2t2ac1ion","2t2act3","2t2act2n","2t2act1o1","2t2act1on","2t2acti2","2t2acti1n","2t2actio1","2t2action","2t1r6","2t1r5n","2t1r4o1","2t1r4on","2t1r3i2","2t1r3i1n","2t1r3io1","2t1r3ion","2t1r2t3","2t1r2t2n","2t1r2t1o1","2t1r2t1on","2t1r2ti2","2t1r2ti1n","2t1r2tio1","2t1r2tion","2t1r1c4","2t1r1c3n","2t1r1c2o1","2t1r1c2on","2t1r1c1i2","2t1r1c1i1n","2t1r1c1io1","2t1r1c1ion","2t1r1ct3","2t1r1ct2n","2t1r1ct1o1","2t1r1ct1on","2t1r1cti2","2t1r1cti1n","2t1r1ctio1","2t1r1ction","2t1ra5","2t1ra4n","2t1ra3o1","2t1ra3on","2t1ra2i2","2t1ra2i1n","2t1ra2io1","2t1ra2ion","2t1ra1t3","2t1ra1t2n","2t1ra1t1o1","2t1ra1t1on","2t1ra1ti2","2t1ra1ti1n","2t1ra1tio1","2t1ra1tion","2t1rac4","2t1rac3n","2t1rac2o1","2t1rac2on","2t1rac1i2","2t1rac1i1n","2t1rac1io1","2t1rac1ion","2t1ract3","2t1ract2n","2t1ract1o1","2t1ract1on","2t1racti2","2t1racti1n","2t1ractio1","2t1raction","2te7","2te6n","2te5o1","2te5on","2te4i2","2te4i1n","2te4io1","2te4ion","2te3t3","2te3t2n","2te3t1o1","2te3t1on","2te3ti2","2te3ti1n","2te3tio1","2te3tion","2te2c4","2te2c3n","2te2c2o1","2te2c2on","2te2c1i2","2te2c1i1n","2te2c1io1","2te2c1ion","2te2ct3","2te2ct2n","2te2ct1o1","2te2ct1on","2te2cti2","2te2cti1n","2te2ctio1","2te2ction","2te1a5","2te1a4n","2te1a3o1","2te1a3on","2te1a2i2","2te1a2i1n","2te1a2io1","2te1a2ion","2te1a1t3","2te1a1t2n","2te1a1t1o1","2te1a1t1on","2te1a1ti2","2te1a1ti1n","2te1a1tio1","2te1a1tion","2te1ac4","2te1ac3n","2te1ac2o1","2te1ac2on","2te1ac1i2","2te1ac1i1n","2te1ac1io1","2te1ac1ion","2te1act3","2te1act2n","2te1act1o1","2te1act1on","2te1acti2","2te1acti1n","2te1actio1","2te1action","2ter6","2ter5n","2ter4o1","2ter4on","2ter3i2","2ter3i1n","2ter3io1","2ter3ion","2ter2t3","2ter2t2n","2ter2t1o1","2ter2t1on","2ter2ti2","2ter2ti1n","2ter2tio1","2ter2tion","2ter1c4","2ter1c3n","2ter1c2o1","2ter1c2on","2ter1c1i2","2ter1c1i1n","2ter1c1io1","2ter1c1ion","2ter1ct3","2ter1ct2n","2ter1ct1o1","2ter1ct1on","2ter1cti2","2ter1cti1n","2ter1ctio1","2ter1ction","2tera5","2tera4n","2tera3o1","2tera3on","2tera2i2","2tera2i1n","2tera2io1","2tera2ion","2tera1t3","2tera1t2n","2tera1t1o1","2tera1t1on","2tera1ti2","2tera1ti1n","2tera1tio1","2tera1tion","2terac4","2terac3n","2terac2o1","2terac2on","2terac1i2","2terac1i1n","2terac1io1","2terac1ion","2teract3","2teract2n","2teract1o1","2teract1on","2teracti2","2teracti1n","2teractio1","2teraction","1n9","1n8n","1n7o1","1n7on","1n6i2","1n6i1n","1n6io1","1n6ion","1n5t3","1n5t2n","1n5t1o1","1n5t1on","1n5ti2","1n5ti1n","1n5tio1","1n5tion","1n4c4","1n4c3n","1n4c2o1","1n4c2on","1n4c1i2","1n4c1i1n","1n4c1io1","1n4c1ion","1n4ct3","1n4ct2n","1n4ct1o1","1n4ct1on","1n4cti2","1n4cti1n","1n4ctio1","1n4ction","1n3a5","1n3a4n","1n3a3o1","1n3a3on","1n3a2i2","1n3a2i1n","1n3a2io1","1n3a2ion","1n3a1t3","1n3a1t2n","1n3a1t1o1","1n3a1t1on","1n3a1ti2","1n3a1ti1n","1n3a1tio1","1n3a1tion","1n3ac4","1n3ac3n","1n3ac2o1","1n3ac2on","1n3ac1i2","1n3ac1i1n","1n3ac1io1","1n3ac1ion","1n3act3","1n3act2n","1n3act1o1","1n3act1on","1n3acti2","1n3acti1n","1n3actio1","1n3action","1n2r6","1n2r5n","1n2r4o1","1n2r4on","1n2r3i2","1n2r3i1n","1n2r3io1","1n2r3ion","1n2r2t3","1n2r2t2n","1n2r2t1o1","1n2r2t1on","1n2r2ti2","1n2r2ti1n","1n2r2tio1","1n2r2tion","1n2r1c4","1n2r1c3n","1n2r1c2o1","1n2r1c2on","1n2r1c1i2","1n2r1c1i1n","1n2r1c1io1","1n2r1c1ion","1n2r1ct3","1n2r1ct2n","1n2r1ct1o1","1n2r1ct1on","1n2r1cti2","1n2r1cti1n","1n2r1ctio1","1n2r1ction","1n2ra5","1n2ra4n","1n2ra3o1","1n2ra3on","1n2ra2i2","1n2ra2i1n","1n2ra2io1","1n2ra2ion","1n2ra1t3","1n2ra1t2n","1n2ra1t1o1","1n2ra1t1on","1n2ra1ti2","1n2ra1ti1n","1n2ra1tio1","1n2ra1tion","1n2rac4","1n2rac3n","1n2rac2o1","1n2rac2on","1n2rac1i2","1n2rac1i1n","1n2rac1io1","1n2rac1ion","1n2ract3","1n2ract2n","1n2ract1o1","1n2ract1on","1n2racti2","1n2racti1n","1n2ractio1","1n2raction","1n1e7","1n1e6n","1n1e5o1","1n1e5on","1n1e4i2","1n1e4i1n","1n1e4io1","1n1e4ion","1n1e3t3","1n1e3t2n","1n1e3t1o1","1n1e3t1on","1n1e3ti2","1n1e3ti1n","1n1e3tio1","1n1e3tion","1n1e2c4","1n1e2c3n","1n1e2c2o1","1n1e2c2on","1n1e2c1i2","1n1e2c1i1n","1n1e2c1io1","1n1e2c1ion","1n1e2ct3","1n1e2ct2n","1n1e2ct1o1","1n1e2ct1on","1n1e2cti2","1n1e2cti1n","1n1e2ctio1","1n1e2ction","1n1e1a5","1n1e1a4n","1n1e1a3o1","1n1e1a3on","1n1e1a2i2","1n1e1a2i1n","1n1e1a2io1","1n1e1a2ion","1n1e1a1t3","1n1e1a1t2n","1n1e1a1t1o1","1n1e1a1t1on","1n1e1a1ti2","1n1e1a1ti1n","1n1e1a1tio1","1n1e1a1tion","1n1e1ac4","1n1e1ac3n","1n1e1ac2o1","1n1e1ac2on","1n1e1ac1i2","1n1e1ac1i1n","1n1e1ac1io1","1n1e1ac1ion","1n1e1act3","1n1e1act2n","1n1e1act1o1","1n1e1act1on","1n1e1acti2","1n1e1acti1n","1n1e1actio1","1n1e1action","1n1er6","1n1er5n","1n1er4o1","1n1er4on","1n1er3i2","1n1er3i1n","1n1er3io1","1n1er3ion","1n1er2t3","1n1er2t2n","1n1er2t1o1","1n1er2t1on","1n1er2ti2","1n1er2ti1n","1n1er2tio1","1n1er2tion","1n1er1c4","1n1er1c3n","1n1er1c2o1","1n1er1c2on","1n1er1c1i2","1n1er1c1i1n","1n1er1c1io1","1n1er1c1ion","1n1er1ct3","1n1er1ct2n","1n1er1ct1o1","1n1er1ct1on","1n1er1cti2","1n1er1cti1n","1n1er1ctio1","1n1er1ction","1n1era5","1n1era4n","1n1era3o1","1n1era3on","1n1era2i2","1n1era2i1n","1n1era2io1","1n1era2ion","1n1era1t3","1n1era1t2n","1n1era1t1o1","1n1era1t1on","1n1era1ti2","1n1era1ti1n","1n1era1tio1","1n1era1tion","1n1erac4","1n1erac3n","1n1erac2o1","1n1erac2on","1n1erac1i2","1n1erac1i1n","1n1erac1io1","1n1erac1ion","1n1eract3","1n1eract2n","1n1eract1o1","1n1eract1on","1n1eracti2","1n1eracti1n","1n1eractio1","1n1eraction","1nt8","1nt7n","1nt6o1","1nt6on","1nt5i2","1nt5i1n","1nt5io1","1nt5ion","1nt4t3","1nt4t2n","1nt4t1o1","1nt4t1on","1nt4ti2","1nt4ti1n","1nt4tio1","1nt4tion","1nt3c4","1nt3c3n","1nt3c2o1","1nt3c2on","1nt3c1i2","1nt3c1i1n","1nt3c1io1","1nt3c1ion","1nt3ct3","1nt3ct2n","1nt3ct1o1","1nt3ct1on","1nt3cti2","1nt3cti1n","1nt3ctio1","1nt3ction","1nt2a5","1nt2a4n","1nt2a3o1","1nt2a3on","1nt2a2i2","1nt2a2i1n","1nt2a2io1","1nt2a2ion","1nt2a1t3","1nt2a1t2n","1nt2a1t1o1","1nt2a1t1on","1nt2a1ti2","1nt2a1ti1n","1nt2a1tio1","1nt2a1tion","1nt2ac4","1nt2ac3n","1nt2ac2o1","1nt2ac2on","1nt2ac1i2","1nt2ac1i1n","1nt2ac1io1","1nt2ac1ion","1nt2act3","1nt2act2n","1nt2act1o1","1nt2act1on","1nt2acti2","1nt2acti1n","1nt2actio1","1nt2action","1nt1r6","1nt1r5n","1nt1r4o1","1nt1r4on","1nt1r3i2","1nt1r3i1n","1nt1r3io1","1nt1r3ion","1nt1r2t3","1nt1r2t2n","1nt1r2t1o1","1nt1r2t1on","1nt1r2ti2","1nt1r2ti1n","1nt1r2tio1","1nt1r2tion","1nt1r1c4","1nt1r1c3n","1nt1r1c2o1","1nt1r1c2on","1nt1r1c1i2","1nt1r1c1i1n","1nt1r1c1io1","1nt1r1c1ion","1nt1r1ct3","1nt1r1ct2n","1nt1r1ct1o1","1nt1r1ct1on","1nt1r1cti2","1nt1r1cti1n","1nt1r1ctio1","1nt1r1ction","1nt1ra5","1nt1ra4n","1nt1ra3o1","1nt1ra3on","1nt1ra2i2","1nt1ra2i1n","1nt1ra2io1","1nt1ra2ion","1nt1ra1t3","1nt1ra1t2n","1nt1ra1t1o1","1nt1ra1t1on","1nt1ra1ti2","1nt1ra1ti1n","1nt1ra1tio1","1nt1ra1tion","1nt1rac4","1nt1rac3n","1nt1rac2o1","1nt1rac2on","1nt1rac1i2","1nt1rac1i1n","1nt1rac1io1","1nt1rac1ion","1nt1ract3","1nt1ract2n","1nt1ract1o1","1nt1ract1on","1nt1racti2","1nt1racti1n","1nt1ractio1","1nt1raction","1nte7","1nte6n","1nte5o1","1nte5on","1nte4i2","1nte4i1n","1nte4io1","1nte4ion","1nte3t3","1nte3t2n","1nte3t1o1","1nte3t1on","1nte3ti2","1nte3ti1n","1nte3tio1","1nte3tion","1nte2c4","1nte2c3n","1nte2c2o1","1nte2c2on","1nte2c1i2","1nte2c1i1n","1nte2c1io1","1nte2c1ion","1nte2ct3","1nte2ct2n","1nte2ct1o1","1nte2ct1on","1nte2cti2","1nte2cti1n","1nte2ctio1","1nte2ction","1nte1a5","1nte1a4n","1nte1a3o1","1nte1a3on","1nte1a2i2","1nte1a2i1n","1nte1a2io1","1nte1a2ion","1nte1a1t3","1nte1a1t2n","1nte1a1t1o1","1nte1a1t1on","1nte1a1ti2","1nte1a1ti1n","1nte1a1tio1","1nte1a1tion","1nte1ac4","1nte1ac3n","1nte1ac2o1","1nte1ac2on","1nte1ac1i2","1nte1ac1i1n","1nte1ac1io1","1nte1ac1ion","1nte1act3","1nte1act2n","1nte1act1o1","1nte1act1on","1nte1acti2","1nte1acti1n","1nte1actio1","1nte1action","1nter6","1nter5n","1nter4o1","1nter4on","1nter3i2","1nter3i1n","1nter3io1","1nter3ion","1nter2t3","1nter2t2n","1nter2t1o1","1nter2t1on","1nter2ti2","1nter2ti1n","1nter2tio1","1nter2tion","1nter1c4","1nter1c3n","1nter1c2o1","1nter1c2on","1nter1c1i2","1nter1c1i1n","1nter1c1io1","1nter1c1ion","1nter1ct3","1nter1ct2n","1nter1ct1o1","1nter1ct1on","1nter1cti2","1nter1cti1n","1nter1ctio1","1nter1ction","1ntera5","1ntera4n","1ntera3o1","1ntera3on","1ntera2i2","1ntera2i1n","1ntera2io1","1ntera2ion","1ntera1t3","1ntera1t2n","1ntera1t1o1","1ntera1t1on","1ntera1ti2","1ntera1ti1n","1ntera1tio1","1ntera1tion","1nterac4","1nterac3n","1nterac2o1","1nterac2on","1nterac1i2","1nterac1i1n","1nterac1io1","1nterac1ion","1nteract3","1nteract2n","1nteract1o1","1nteract1on","1nteracti2","1nteracti1n","1nteractio1","1nteraction","i10","i9n","i8o1","i8on","i7i2","i7i1n","i7io1","i7ion","i6t3","i6t2n","i6t1o1","i6t1on","i6ti2","i6ti1n","i6tio1","i6tion","i5c4","i5c3n","i5c2o1","i5c2on","i5c1i2","i5c1i1n","i5c1io1","i5c1ion","i5ct3","i5ct2n","i5ct1o1","i5ct1on","i5cti2","i5cti1n","i5ctio1","i5ction","i4a5","i4a4n","i4a3o1","i4a3on","i4a2i2","i4a2i1n","i4a2io1","i4a2ion","i4a1t3","i4a1t2n","i4a1t1o1","i4a1t1on","i4a1ti2","i4a1ti1n","i4a1tio1","i4a1tion","i4ac4","i4ac3n","i4ac2o1","i4ac2on","i4ac1i2","i4ac1i1n","i4ac1io1","i4ac1ion","i4act3","i4act2n","i4act1o1","i4act1on","i4acti2","i4acti1n","i4actio1","i4action","i3r6","i3r5n","i3r4o1","i3r4on","i3r3i2","i3r3i1n","i3r3io1","i3r3ion","i3r2t3","i3r2t2n","i3r2t1o1","i3r2t1on","i3r2ti2","i3r2ti1n","i3r2tio1","i3r2tion","i3r1c4","i3r1c3n","i3r1c2o1","i3r1c2on","i3r1c1i2","i3r1c1i1n","i3r1c1io1","i3r1c1ion","i3r1ct3","i3r1ct2n","i3r1ct1o1","i3r1ct1on","i3r1cti2","i3r1cti1n","i3r1ctio1","i3r1ction","i3ra5","i3ra4n","i3ra3o1","i3ra3on","i3ra2i2","i3ra2i1n","i3ra2io1","i3ra2ion","i3ra1t3","i3ra1t2n","i3ra1t1o1","i3ra1t1on","i3ra1ti2","i3ra1ti1n","i3ra1tio1","i3ra1tion","i3rac4","i3rac3n","i3rac2o1","i3rac2on","i3rac1i2","i3rac1i1n","i3rac1io1","i3rac1ion","i3ract3","i3ract2n","i3ract1o1","i3ract1on","i3racti2","i3racti1n","i3ractio1","i3raction","i2e7","i2e6n","i2e5o1","i2e5on","i2e4i2","i2e4i1n","i2e4io1","i2e4ion","i2e3t3","i2e3t2n","i2e3t1o1","i2e3t1on","i2e3ti2","i2e3ti1n","i2e3tio1","i2e3tion","i2e2c4","i2e2c3n","i2e2c2o1","i2e2c2on","i2e2c1i2","i2e2c1i1n","i2e2c1io1","i2e2c1ion","i2e2ct3","i2e2ct2n","i2e2ct1o1","i2e2ct1on","i2e2cti2","i2e2cti1n","i2e2ctio1","i2e2ction","i2e1a5","i2e1a4n","i2e1a3o1","i2e1a3on","i2e1a2i2","i2e1a2i1n","i2e1a2io1","i2e1a2ion","i2e1a1t3","i2e1a1t2n","i2e1a1t1o1","i2e1a1t1on","i2e1a1ti2","i2e1a1ti1n","i2e1a1tio1","i2e1a1tion","i2e1ac4","i2e1ac3n","i2e1ac2o1","i2e1ac2on","i2e1ac1i2","i2e1ac1i1n","i2e1ac1io1","i2e1ac1ion","i2e1act3","i2e1act2n","i2e1act1o1","i2e1act1on","i2e1acti2","i2e1acti1n","i2e1actio1","i2e1action","i2er6","i2er5n","i2er4o1","i2er4on","i2er3i2","i2er3i1n","i2er3io1","i2er3ion","i2er2t3","i2er2t2n","i2er2t1o1","i2er2t1on","i2er2ti2","i2er2ti1n","i2er2tio1","i2er2tion","i2er1c4","i2er1c3n","i2er1c2o1","i2er1c2on","i2er1c1i2","i2er1c1i1n","i2er1c1io1","i2er1c1ion","i2er1ct3","i2er1ct2n","i2er1ct1o1","i2er1ct1on","i2er1cti2","i2er1cti1n","i2er1ctio1","i2er1ction","i2era5","i2era4n","i2era3o1","i2era3on","i2era2i2","i2era2i1n","i2era2io1","i2era2ion","i2era1t3","i2era1t2n","i2era1t1o1","i2era1t1on","i2era1ti2","i2era1ti1n","i2era1tio1","i2era1tion","i2erac4","i2erac3n","i2erac2o1","i2erac2on","i2erac1i2","i2erac1i1n","i2erac1io1","i2erac1ion","i2eract3","i2eract2n","i2eract1o1","i2eract1on","i2eracti2","i2eracti1n","i2eractio1","i2eraction","i1t8","i1t7n","i1t6o1","i1t6on","i1t5i2","i1t5i1n","i1t5io1","i1t5ion","i1t4t3","i1t4t2n","i1t4t1o1","i1t4t1on","i1t4ti2","i1t4ti1n","i1t4tio1","i1t4tion","i1t3c4","i1t3c3n","i1t3c2o1","i1t3c2on","i1t3c1i2","i1t3c1i1n","i1t3c1io1","i1t3c1ion","i1t3ct3","i1t3ct2n","i1t3ct1o1","i1t3ct1on","i1t3cti2","i1t3cti1n","i1t3ctio1","i1t3ction","i1t2a5","i1t2a4n","i1t2a3o1","i1t2a3on","i1t2a2i2","i1t2a2i1n","i1t2a2io1","i1t2a2ion","i1t2a1t3","i1t2a1t2n","i1t2a1t1o1","i1t2a1t1on","i1t2a1ti2","i1t2a1ti1n","i1t2a1tio1","i1t2a1tion","i1t2ac4","i1t2ac3n","i1t2ac2o1","i1t2ac2on","i1t2ac1i2","i1t2ac1i1n","i1t2ac1io1","i1t2ac1ion","i1t2act3","i1t2act2n","i1t2act1o1","i1t2act1on","i1t2acti2","i1t2acti1n","i1t2actio1","i1t2action","i1t1r6","i1t1r5n","i1t1r4o1","i1t1r4on","i1t1r3i2","i1t1r3i1n","i1t1r3io1","i1t1r3ion","i1t1r2t3","i1t1r2t2n","i1t1r2t1o1","i1t1r2t1on","i1t1r2ti2","i1t1r2ti1n","i1t1r2tio1","i1t1r2tion","i1t1r1c4","i1t1r1c3n","i1t1r1c2o1","i1t1r1c2on","i1t1r1c1i2","i1t1r1c1i1n","i1t1r1c1io1","i1t1r1c1ion","i1t1r1ct3","i1t1r1ct2n","i1t1r1ct1o1","i1t1r1ct1on","i1t1r1cti2","i1t1r1cti1n","i1t1r1ctio1","i1t1r1ction","i1t1ra5","i1t1ra4n","i1t1ra3o1","i1t1ra3on","i1t1ra2i2","i1t1ra2i1n","i1t1ra2io1","i1t1ra2ion","i1t1ra1t3","i1t1ra1t2n","i1t1ra1t1o1","i1t1ra1t1on","i1t1ra1ti2","i1t1ra1ti1n","i1t1ra1tio1","i1t1ra1tion","i1t1rac4","i1t1rac3n","i1t1rac2o1","i1t1rac2on","i1t1rac1i2","i1t1rac1i1n","i1t1rac1io1","i1t1rac1ion","i1t1ract3","i1t1ract2n","i1t1ract1o1","i1t1ract1on","i1t1racti2","i1t1racti1n","i1t1ractio1","i1t1raction","i1te7","i1te6n","i1te5o1","i1te5on","i1te4i2","i1te4i1n","i1te4io1","i1te4ion","i1te3t3","i1te3t2n","i1te3t1o1","i1te3t1on","i1te3ti2","i1te3ti1n","i1te3tio1","i1te3tion","i1te2c4","i1te2c3n","i1te2c2o1","i1te2c2on","i1te2c1i2","i1te2c1i1n","i1te2c1io1","i1te2c1ion","i1te2ct3","i1te2ct2n","i1te2ct1o1","i1te2ct1on","i1te2cti2","i1te2cti1n","i1te2ctio1","i1te2ction","i1te1a5","i1te1a4n","i1te1a3o1","i1te1a3on","i1te1a2i2","i1te1a2i1n","i1te1a2io1","i1te1a2ion","i1te1a1t3","i1te1a1t2n","i1te1a1t1o1","i1te1a1t1on","i1te1a1ti2","i1te1a1ti1n","i1te1a1tio1","i1te1a1tion","i1te1ac4","i1te1ac3n","i1te1ac2o1","i1te1ac2on","i1te1ac1i2","i1te1ac1i1n","i1te1ac1io1","i1te1ac1ion","i1te1act3","i1te1act2n","i1te1act1o1","i1te1act1on","i1te1acti2","i1te1acti1n","i1te1actio1","i1te1action","i1ter6","i1ter5n","i1ter4o1","i1ter4on","i1ter3i2","i1ter3i1n","i1ter3io1","i1ter3ion","i1ter2t3","i1ter2t2n","i1ter2t1o1","i1ter2t1on","i1ter2ti2","i1ter2ti1n","i1ter2tio1","i1ter2tion","i1ter1c4","i1ter1c3n","i1ter1c2o1","i1ter1c2on","i1ter1c1i2","i1ter1c1i1n","i1ter1c1io1","i1ter1c1ion","i1ter1ct3","i1ter1ct2n","i1ter1ct1o1","i1ter1ct1on","i1ter1cti2","i1ter1cti1n","i1ter1ctio1","i1ter1ction","i1tera5","i1tera4n","i1tera3o1","i1tera3on","i1tera2i2","i1tera2i1n","i1tera2io1","i1tera2ion","i1tera1t3","i1tera1t2n","i1tera1t1o1","i1tera1t1on","i1tera1ti2","i1tera1ti1n","i1tera1tio1","i1tera1tion","i1terac4","i1terac3n","i1terac2o1","i1terac2on","i1terac1i2","i1terac1i1n","i1terac1io1","i1terac1ion","i1teract3","i1teract2n","i1teract1o1","i1teract1on","i1teracti2","i1teracti1n","i1teractio1","i1teraction","in9","in8n","in7o1","in7on","in6i2","in6i1n","in6io1","in6ion","in5t3","in5t2n","in5t1o1","in5t1on","in5ti2","in5ti1n","in5tio1","in5tion","in4c4","in4c3n","in4c2o1","in4c2on","in4c1i2","in4c1i1n","in4c1io1","in4c1ion","in4ct3","in4ct2n","in4ct1o1","in4ct1on","in4cti2","in4cti1n","in4ctio1","in4ction","in3a5","in3a4n","in3a3o1","in3a3on","in3a2i2","in3a2i1n","in3a2io1","in3a2ion","in3a1t3","in3a1t2n","in3a1t1o1","in3a1t1on","in3a1ti2","in3a1ti1n","in3a1tio1","in3a1tion","in3ac4","in3ac3n","in3ac2o1","in3ac2on","in3ac1i2","in3ac1i1n","in3ac1io1","in3ac1ion","in3act3","in3act2n","in3act1o1","in3act1on","in3acti2","in3acti1n","in3actio1","in3action","in2r6","in2r5n","in2r4o1","in2r4on","in2r3i2","in2r3i1n","in2r3io1","in2r3ion","in2r2t3","in2r2t2n","in2r2t1o1","in2r2t1on","in2r2ti2","in2r2ti1n","in2r2tio1","in2r2tion","in2r1c4","in2r1c3n","in2r1c2o1","in2r1c2on","in2r1c1i2","in2r1c1i1n","in2r1c1io1","in2r1c1ion","in2r1ct3","in2r1ct2n","in2r1ct1o1","in2r1ct1on","in2r1cti2","in2r1cti1n","in2r1ctio1","in2r1ction","in2ra5","in2ra4n","in2ra3o1","in2ra3on","in2ra2i2","in2ra2i1n","in2ra2io1","in2ra2ion","in2ra1t3","in2ra1t2n","in2ra1t1o1","in2ra1t1on","in2ra1ti2","in2ra1ti1n","in2ra1tio1","in2ra1tion","in2rac4","in2rac3n","in2rac2o1","in2rac2on","in2rac1i2","in2rac1i1n","in2rac1io1","in2rac1ion","in2ract3","in2ract2n","in2ract1o1","in2ract1on","in2racti2","in2racti1n","in2ractio1","in2raction","in1e7","in1e6n","in1e5o1","in1e5on","in1e4i2","in1e4i1n","in1e4io1","in1e4ion","in1e3t3","in1e3t2n","in1e3t1o1","in1e3t1on","in1e3ti2","in1e3ti1n","in1e3tio1","in1e3tion","in1e2c4","in1e2c3n","in1e2c2o1","in1e2c2on","in1e2c1i2","in1e2c1i1n","in1e2c1io1","in1e2c1ion","in1e2ct3","in1e2ct2n","in1e2ct1o1","in1e2ct1on","in1e2cti2","in1e2cti1n","in1e2ctio1","in1e2ction","in1e1a5","in1e1a4n","in1e1a3o1","in1e1a3on","in1e1a2i2","in1e1a2i1n","in1e1a2io1","in1e1a2ion","in1e1a1t3","in1e1a1t2n","in1e1a1t1o1","in1e1a1t1on","in1e1a1ti2","in1e1a1ti1n","in1e1a1tio1","in1e1a1tion","in1e1ac4","in1e1ac3n","in1e1ac2o1","in1e1ac2on","in1e1ac1i2","in1e1ac1i1n","in1e1ac1io1","in1e1ac1ion","in1e1act3","in1e1act2n","in1e1act1o1","in1e1act1on","in1e1acti2","in1e1acti1n","in1e1actio1","in1e1action","in1er6","in1er5n","in1er4o1","in1er4on","in1er3i2","in1er3i1n","in1er3io1","in1er3ion","in1er2t3","in1er2t2n","in1er2t1o1","in1er2t1on","in1er2ti2","in1er2ti1n","in1er2tio1","in1er2tion","in1er1c4","in1er1c3n","in1er1c2o1","in1er1c2on","in1er1c1i2","in1er1c1i1n","in1er1c1io1","in1er1c1ion","in1er1ct3","in1er1ct2n","in1er1ct1o1","in1er1ct1on","in1er1cti2","in1er1cti1n","in1er1ctio1","in1er1ction","in1era5","in1era4n","in1era3o1","in1era3on","in1era2i2","in1era2i1n","in1era2io1","in1era2ion","in1era1t3","in1era1t2n","in1era1t1o1","in1era1t1on","in1era1ti2","in1era1ti1n","in1era1tio1","in1era1tion","in1erac4","in1erac3n","in1erac2o1","in1erac2on","in1erac1i2","in1erac1i1n","in1erac1io1","in1erac1ion","in1eract3","in1eract2n","in1eract1o1","in1eract1on","in1eracti2","in1eracti1n","in1eractio1","in1eraction","int8","int7n","int6o1","int6on","int5i2","int5i1n","int5io1","int5ion","int4t3","int4t2n","int4t1o1","int4t1on","int4ti2","int4ti1n","int4tio1","int4tion","int3c4","int3c3n","int3c2o1","int3c2on","int3c1i2","int3c1i1n","int3c1io1","int3c1ion","int3ct3","int3ct2n","int3ct1o1","int3ct1on","int3cti2","int3cti1n","int3ctio1","int3ction","int2a5","int2a4n","int2a3o1","int2a3on","int2a2i2","int2a2i1n","int2a2io1","int2a2ion","int2a1t3","int2a1t2n","int2a1t1o1","int2a1t1on","int2a1ti2","int2a1ti1n","int2a1tio1","int2a1tion","int2ac4","int2ac3n","int2ac2o1","int2ac2on","int2ac1i2","int2ac1i1n","int2ac1io1","int2ac1ion","int2act3","int2act2n","int2act1o1","int2act1on","int2acti2","int2acti1n","int2actio1","int2action","int1r6","int1r5n","int1r4o1","int1r4on","int1r3i2","int1r3i1n","int1r3io1","int1r3ion","int1r2t3","int1r2t2n","int1r2t1o1","int1r2t1on","int1r2ti2","int1r2ti1n","int1r2tio1","int1r2tion","int1r1c4","int1r1c3n","int1r1c2o1","int1r1c2on","int1r1c1i2","int1r1c1i1n","int1r1c1io1","int1r1c1ion","int1r1ct3","int1r1ct2n","int1r1ct1o1","int1r1ct1on","int1r1cti2","int1r1cti1n","int1r1ctio1","int1r1ction","int1ra5","int1ra4n","int1ra3o1","int1ra3on","int1ra2i2","int1ra2i1n","int1ra2io1","int1ra2ion","int1ra1t3","int1ra1t2n","int1ra1t1o1","int1ra1t1on","int1ra1ti2","int1ra1ti1n","int1ra1tio1","int1ra1tion","int1rac4","int1rac3n","int1rac2o1","int1rac2on","int1rac1i2","int1rac1i1n","int1rac1io1","int1rac1ion","int1ract3","int1ract2n","int1ract1o1","int1ract1on","int1racti2","int1racti1n","int1ractio1","int1raction","inte7","inte6n","inte5o1","inte5on","inte4i2","inte4i1n","inte4io1","inte4ion","inte3t3","inte3t2n","inte3t1o1","inte3t1on","inte3ti2","inte3ti1n","inte3tio1","inte3tion","inte2c4","inte2c3n","inte2c2o1","inte2c2on","inte2c1i2","inte2c1i1n","inte2c1io1","inte2c1ion","inte2ct3","inte2ct2n","inte2ct1o1","inte2ct1on","inte2cti2","inte2cti1n","inte2ctio1","inte2ction","inte1a5","inte1a4n","inte1a3o1","inte1a3on","inte1a2i2","inte1a2i1n","inte1a2io1","inte1a2ion","inte1a1t3","inte1a1t2n","inte1a1t1o1","inte1a1t1on","inte1a1ti2","inte1a1ti1n","inte1a1tio1","inte1a1tion","inte1ac4","inte1ac3n","inte1ac2o1","inte1ac2on","inte1ac1i2","inte1ac1i1n","inte1ac1io1","inte1ac1ion","inte1act3","inte1act2n","inte1act1o1","inte1act1on","inte1acti2","inte1acti1n","inte1actio1","inte1action","inter6","inter5n","inter4o1","inter4on","inter3i2","inter3i1n","inter3io1","inter3ion","inter2t3","inter2t2n","inter2t1o1","inter2t1on","inter2ti2","inter2ti1n","inter2tio1","inter2tion","inter1c4","inter1c3n","inter1c2o1","inter1c2on","inter1c1i2","inter1c1i1n","inter1c1io1","inter1c1ion","inter1ct3","inter1ct2n","inter1ct1o1","inter1ct1on","inter1cti2","inter1cti1n","inter1ctio1","inter1ction","intera5","intera4n","intera3o1","intera3on","intera2i2","intera2i1n","intera2io1","intera2ion","intera1t3","intera1t2n","intera1t1o1","intera1t1on","intera1ti2","intera1ti1n","intera1tio1","intera1tion","interac4","interac3n","interac2o1","interac2on","interac1i2","interac1i1n","interac1io1","interac1ion","interact3","interact2n","interact1o1","interact1on","interacti2","interacti1n","interactio1","interaction"};
		//
		// System.out.println(output.length + ":" + expected.length);
		// for(String outputStr : output) {
		// boolean found = false;
		// for(String expectedStr: expected) {
		// if(expectedStr.equals(outputStr)) {
		// found = true;
		// break;
		// }
		// }
		// if(!found) {
		// System.out.println(outputStr);
		// }
		// }
		// for(int i=1; i<49; i++) {
		// System.out.println(sol.intToRoman(i));
		// }
		//
		// System.out.println((int)Math.sqrt(24));

		// String[] words = {"abcw", "baz", "foo", "bar", "xtfn", "abcdef"};
		// System.out.println(sol.maxProduct(words));

		// ListNode head = new ListNode(1);
		// ListNode two = new ListNode(2);
		// ListNode three = new ListNode(3);
		// ListNode four = new ListNode(4);
		// ListNode five = new ListNode(5);
		// head.next = two;
		// two.next = three;
		// three.next = four;
		// four.next = five;
		// ListNode res = sol.oddEvenList(head);
		// print(res);
	}

	static void print(ListNode node) {
		StringBuilder sb = new StringBuilder();
		ListNode current = node;
		while (current != null) {
			sb.append(current.val);
			current = current.next;
		}
		System.out.println(sb.toString());
	}

	static void print(int[][] arr) {
		for (int[] val : arr) {
			System.out.format("(%d,%d) ", val[0], val[1]);
		}
	}

	static void printArray(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.format("%2d", arr[i][j]);
			}
			System.out.println();
		}
	}
}

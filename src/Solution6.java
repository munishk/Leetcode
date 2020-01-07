import java.util.*;

public class Solution6 {


	public List<Integer> rightSideView(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		if (root == null) {
			return res;
		}

		Queue<TreeNode> q1 = new LinkedList<>();
		Queue<TreeNode> q2 = new LinkedList<>();

		q1.add(root);
		while (!q1.isEmpty() || !q2.isEmpty()) {
			if (!q1.isEmpty()) {
				res.add(q1.peek().val);
				while (!q1.isEmpty()) {
					TreeNode current = q1.remove();
					if (current.right != null) {
						q2.add(current.right);
					}
					if (current.left != null) {
						q2.add(current.left);
					}
				}
			}

			if (!q2.isEmpty()) {
				res.add(q2.peek().val);
				while (!q2.isEmpty()) {
					TreeNode current = q2.remove();
					if (current.right != null) {
						q1.add(current.right);
					}
					if (current.left != null) {
						q1.add(current.left);
					}
				}
			}
		}
		return res;
	}

	List<Integer> lexOrder(int n) {
		List<Integer> res = new ArrayList<>();
		for (int i = 1; i < 10 && i <= n; i++) {
			res.add(i);
			lexOrderUtil(n, res, i * 10);
		}
		return res;
	}

	void lexOrderUtil(int n, List<Integer> res, int curr) {
		if (curr > n) {
			return;
		}
		for (int i = 0; i < 10 && curr + i <= n; i++) {
			res.add(curr + i);
			lexOrderUtil(n, res, (curr + i) * 10);
		}
	}

	int count = 0;

	public int findKthNumber(int n, int k) {
		for (int i = 1; i < 10 && i < n; i++) {
			count++;
			if (count == k) {
				return i;
			}
			int res = findKthNumberUtil(n, i * 10, k);
			if (res != -1) {
				return res;
			}
		}
		return -1;
	}

	private int findKthNumberUtil(int n, int curr, int k) {
		if (curr > n) {
			return -1;
		}

		for (int i = 0; i < 10 && curr + i <= n; i++) {
			count++;
			if (count == k) {
				return curr + i;
			}
			int res = findKthNumberUtil(n, (curr + i) * 10, k);
			if (res != -1) {
				return res;
			}
		}
		return -1;
	}

	TreeNode buildTree(Object[] values) {
		int index = 0;
		TreeNode root = new TreeNode((int) values[index++]);
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);

		while (index < values.length) {
			TreeNode curr = q.remove();

			Object value = values[index++];
			if (value != null) {
				curr.left = new TreeNode((int) value);
				q.add(curr.left);
			}

			if (index < values.length) {
				value = values[index++];
				if (value != null) {
					curr.right = new TreeNode((int) value);
					q.add(curr.right);
				}
			}
		}
		return root;
	}
	
    
    public int pathSum(TreeNode root, int sum) {
       if(root == null) {
    	   return 0;
       }
       
      return findPath(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }
    
    private int findPath(TreeNode root, int sum) {
    	int res = 0;
    	if(root == null) {
    		return res;
    	}
    	
    	if(root.val == sum) {
    		res++;
    	}
    	
    	res+= findPath(root.left, sum - root.val);
    	res+= findPath(root.right, sum -root.val);
    	return res;
    }
    
    private void pathSumUtil(TreeNode root, int sum, int currSum) {
        if(root == null) {
            return;
        }
        
        
        if(sum - (root.val + currSum) == 0) {
            count++;
        }
        
        pathSumUtil(root.left, sum, currSum+ root.val);
        pathSumUtil(root.right, sum, currSum+ root.val);
        
        pathSumUtil(root.left, sum, 0);
        pathSumUtil(root.right, sum, 0);
        
    }
    
    public String parseTernary(String expression) {
        Stack<Character> stack = new Stack<>();
        int index = expression.length() -1;
        stack.push(expression.charAt(index--));
        while(index >= 0) {
           Character c = expression.charAt(index--);
           if(c == ':') {
               Character nextOperand = expression.charAt(index--);
               stack.push(nextOperand);
           }
           if(c == '?') {
               Character condition = expression.charAt(index--);
               Character op1 = stack.pop();
               Character op2 = stack.pop();
               if(condition == 'T') {
                   stack.push(op1);
               }else {
                   stack.push(op2);
               }
           }
        }
        return "" + stack.pop();
    }
    
    
    public int[][] generateMatrix(int n) {
        int[][] mat = new int[n][n];
        int left = 0; int right=n-1; int top=0; int bottom=n-1;
        int count =1; int max = n*n;
        while(count <= max) {
            //from left to right
            for(int i=left; i<=right && count <=max; i++) {
                mat[top][i] = count++;
            }
            top++;
            
            //from top to bottom
            for(int i=top; i<=bottom && count<=max; i++) {
                mat[i][right] = count++;
            }
            right--;
            
            //from right -> left
            for(int i=right; i>=left && count <= max;i--) {
                mat[bottom][i] = count++;
            }
            bottom--;
            
            for(int i=bottom; i>=top && count <= max; i--) {
                mat[i][left] = count++;
            }
            left++;
        }
        return mat;
    }
    
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int val: nums) {
            sum+=val;
        }
        
        if(sum%2 != 0) {
            return false;
        }
        
        sum = sum/2;
        boolean[] dp = new boolean[sum+1];
        dp[0] = true;
        for(int i=0; i<nums.length; i++) {
            for(int j=sum; j>=nums[i]; j--) {
                dp[j] = dp[j] || dp[j-nums[i]];
            }
        }
        return dp[sum];
    }
    
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        combineUtil(n,k,res,list, 1);
        return res;
    }
    
    private void combineUtil(int n, int k, List<List<Integer>> res, List<Integer> list, int curr) {
        if(list.size() == k) {
            res.add(new ArrayList(list));
            return;
        }
        
        if(curr > n) {
            return;
        }
        
        list.add(curr);
        combineUtil(n,k,res, list, curr+1);
        list.remove(list.size()-1);
        combineUtil(n,k,res, list, curr+1);
    }
    
    public List<List<Integer>> combineIter(int n, int k) {
        if (n == 0 || k == 0 || k > n) return Collections.emptyList();
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        for (int i = 1; i <= n + 1 - k; i++) res.add(Arrays.asList(i));
        for (int i = 2; i <= k; i++) {
            List<List<Integer>> tmp = new ArrayList<List<Integer>>();
            for (List<Integer> list : res) {
                for (int m = list.get(list.size() - 1) + 1; m <= n - (k - i); m++) {
                    List<Integer> newList = new ArrayList<Integer>(list);
                    newList.add(m);
                    tmp.add(newList);
                }
            }
            res = tmp;
        }
        return res;
    }
    
    public int minPathSum(int[][] grid) {
        int[][] table = new int[grid.length+1][grid[0].length+1];
        for(int i=0; i<table.length;i++) {
            for(int j=0; j<table[0].length; j++) {
                if(i==0|| j== 0) {
                    table[i][j] = Integer.MAX_VALUE;
                }else
                if(i==1 && j == 1) {
                    table[i][j] = grid[i-1][j-1];
                }else {
                    table[i][j] = grid[i-1][j-1] + Math.min(table[i][j-1], table[i-1][j]);
                }
            }
        }
      return table[grid.length][grid[0].length];
     }
    
    static class Interval {
    	int start, end;
    }
    public int eraseOverlapIntervals(Interval[] intervals) {
        int minCount = 0;
        if(intervals != null && intervals.length > 0) {
           Arrays.sort(intervals, (o1,o2) -> { return (o1.start-o2.start);});
           int start = intervals[0].start;
           int end =  intervals[0].end;
           for(int i=1; i< intervals.length; i++) {
              Interval curr =  intervals[i];
              if(curr.start < end) {
                  //found overlapping
                  if(curr.end < end) {
                      end = curr.end;
                      start = curr.start;
                  }
                minCount++;
              }else {
                      end  = curr.end;
                }
           }
        }
        return minCount;
    }

	public static void main(String[] args) {
		Solution6 sol = new Solution6();
		
		System.out.println(sol.minPathSum(new int[][] { {0}}));
		
		//System.out.println(sol.combineIter(5, 3));
		//Math.sqrt(12);
		
		//System.out.println(sol.canPartition(new int[] {1,5,11,5}));
		
		//int[][] res = sol.generateMatrix(5);
		//printArray(res);
		
		//System.out.println(sol.parseTernary("F?F?3:8:T?F:T?0:F?8:T"));
		
		//"F?(F?3:8):(T?F:(T?0:(F?8:T)))"
//
//		System.out.println(sol.lexOrder(100));
//
//		System.out.println(sol.findKthNumber(100, 10));

		//Object[] val ={10,5,-3,3,2,null,11,3,-2,null,1};
		//Object[] val = {5,4,8,11,null,13,4,7,2,null,null,5,1};
		//Object[] val = {-2,null,-3};
//		Object[] val ={1,null,2,null,3,null,4,null,5};
//		TreeNode root = sol.buildTree(val);
//		System.out.println(PrintTree.printTree(root));
//		System.out.println(sol.pathSum(root, 3));

		// TreeNode one = new TreeNode(1);
		// TreeNode two = new TreeNode(2);
		// TreeNode three = new TreeNode(3);
		// TreeNode four = new TreeNode(4);
		// TreeNode five = new TreeNode(5);
		//
		// one.left = two;
		// one.right = three;
		// three.right = four;
		// two.right = five;
		//
		// System.out.println(sol.rightSideView(one));

	}
	
	static void printArray(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.format("%4d", arr[i][j]);
			}
			System.out.println();
		}
	}

}

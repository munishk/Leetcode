public class LongestConsecutiveSequenceII_549 {
	
	 class Result {
	        int inc;
	        int dec;
	        
	        public Result(int inc, int dec) {
	            this.inc = inc;
	            this.dec = dec;
	        }
	        public Result() {}
	    }
	    int max;
	    
	    public int longestConsecutive(TreeNode root) {
	           longestConsecutiveUtil(root);
	           return max;
	    }
	    
	    public Result longestConsecutiveUtil(TreeNode root) {
	        if(root == null) {
	            return new Result();
	        }
	        
	        Result left = longestConsecutiveUtil(root.left);
	        Result right = longestConsecutiveUtil(root.right);
	        
	       int inc = 1, dec = 1;
	        if(root.left != null) {
	            if(root.left.val+1 == root.val) {
	              inc = left.inc +1;
	            }else if(root.val+1 == root.left.val) {
	              dec = left.dec+1;
	            }
	        }
	        
	        if(root.right != null) {
	            if(root.right.val+1 == root.val) {
	               inc = Math.max(inc, right.inc+1);
	            }else if(root.val+1 == root.right.val) {
	                dec = Math.max(dec, right.dec +1);
	            }
	        }
	        
	        max = Math.max(max, Math.max(inc, dec));
	        if(root.left != null && root.right != null) {
	            if(root.left.val +1 == root.val  && root.val+1 == root.right.val) {
	                max = Math.max(max, left.inc + right.dec + 1);
	            }else if(root.right.val+1 == root.val && root.val+1 == root.left.val) {
	                max = Math.max(max, right.inc + left.dec + 1);
	            }
	        }
	        return new Result(inc, dec);
	    }

	public static void main(String[] args) {
		LongestConsecutiveSequenceII_549 obj = new LongestConsecutiveSequenceII_549();
		Object[] values = {3,1,null,null,2};
		TreeNode root = TreeNode.buildTree(values);
		System.out.println(obj.longestConsecutive(root));

	}

}

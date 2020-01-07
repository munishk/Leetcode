/*
 * https://leetcode.com/problems/largest-bst-subtree/
 */
public class LargestBSTSubtree {
	
	int max = 0;
    private static class Result {
        int count, min, max;
        public Result(int count, int min, int max) {
            this.count = count;
            this.min = min;
            this.max = max;
        }
        
    }
    
     public int largestBSTSubtree(TreeNode root) {
         largestBSTSubtreeUtil(root);
         return max;
     }
    
    private Result largestBSTSubtreeUtil(TreeNode root) {
        if(root == null) {
            return new Result(0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }
        
        Result left = largestBSTSubtreeUtil(root.left);
        Result right = largestBSTSubtreeUtil(root.right);
       
       if(left.count == -1 || right.count == -1 || root.val <= left.max || root.val >= right.min) {
           return new Result(-1, 0,0);
       }
       
       int count = 1 + left.count + right.count;
       max = Math.max(count, max);
       return new Result(count, Math.min(root.val, left.min), Math.max(root.val, right.max));
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

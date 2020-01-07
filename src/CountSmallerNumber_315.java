import java.util.LinkedList;
import java.util.List;

public class CountSmallerNumber_315 {
	
	 class TreeNode {
	        int val;
	        TreeNode left,right;
	        
	        public TreeNode(int val) {
	            this.val = val;
	        }
	    }
	    
	    class BinarySearchTree {
	        private TreeNode root;
	        
	        int insert(int val) {
	            if(root == null) {
	                root = new TreeNode(val);
	                return 0;
	            }
	            int smallerCount = 0;
	            TreeNode curr = root;
	            TreeNode prev = root;
	            boolean rightInsert = false;
	            while(curr != null) {
	                prev = curr;
	                if(val > curr.val) {
	                    curr = curr.right;
	                    smallerCount++;
	                    rightInsert = true;
	                }else {
	                    curr = curr.left;
	                    rightInsert = false;
	                }
	            }
	            
	            if(rightInsert) {
	                prev.right = new TreeNode(val);
	            }else {
	                prev.left = new TreeNode(val);
	            }
	            return smallerCount;
	        }
	    }
	    
	   private BinarySearchTree bst = new BinarySearchTree();
	    
	    public List<Integer> countSmaller(int[] nums) {
	        LinkedList<Integer> res = new LinkedList<>();
	        if(nums ==  null|| nums.length == 0) {
	            return res;
	        }
	        
	        for(int i=nums.length-1; i>=0; i--) {
	            res.addFirst(bst.insert(nums[i]));
	        }
	        return res;
	    }

	public static void main(String[] args) {
		CountSmallerNumber_315 obj = new CountSmallerNumber_315();
		int[] nums = {5,2,6,1};
		List<Integer> res = obj.countSmaller(nums);
		System.out.println(res);

	}

}

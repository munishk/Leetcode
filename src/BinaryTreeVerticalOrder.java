import java.util.ArrayList;
import java.util.List;

public class BinaryTreeVerticalOrder {
	
	 public List<List<Integer>> verticalOrder(TreeNode root) {
	        int rootLevel = findRootLevel(root);
	        List<List<Integer>> list = new ArrayList<>();
	        for(int i=0; i< rootLevel; i++) {
	        	list.add(new ArrayList<>());
	        }
	        verticalOrderUtil(root, rootLevel, list);
	        return list;
	    }
	    
	    private void verticalOrderUtil(TreeNode root, int level, List<List<Integer>> list) {
	        if(root == null) {
	            return;
	        }
	        if(list.size() < level) {
	        	list.add(new ArrayList<>());
	        }
	        
	        List<Integer> subList = list.get(level-1);
	        subList.add(root.val);
	        
	        verticalOrderUtil(root.left, level-1, list); 
	        verticalOrderUtil(root.right, level+1, list);
	    }
	    
	   int findRootLevel(TreeNode root) {
	       if(root == null) {
	           return 0;
	       }
	       
	      return 1 + findRootLevel(root.left);
	   }
	   

	public static void main(String[] args) {
		BinaryTreeVerticalOrder sol = new BinaryTreeVerticalOrder();
		Object[] values = {3,9,8,4,0,1,7,null,null,null,2,5};
		TreeNode root = TreeNode.buildTree(values);
		System.out.println(sol.verticalOrder(root));

	}

}

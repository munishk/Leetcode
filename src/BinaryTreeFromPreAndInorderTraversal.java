import java.util.HashMap;
import java.util.Map;

public class BinaryTreeFromPreAndInorderTraversal {
	
	int preIndex = 0;
	/*
	 * Using class level variable for holding preIndex.
	 */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(preorder, inorder, 0, inorder.length-1, map);
    }
    
    private TreeNode buildTree(int[] preorder, int[] inorder, int is, int ie, Map<Integer, Integer> map) {
        if(ie < is) {
            return null;
        }
        int rootVal = preorder[preIndex++];
        TreeNode root = new TreeNode(rootVal);
        if(is == ie) {
            return root;
        }
        
        int rootIndex = map.get(rootVal);
        root.left = buildTree(preorder, inorder, is, rootIndex-1,map);
        root.right = buildTree(preorder, inorder, rootIndex+1, ie, map);
        return root;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

public class Solution261 {
	
	 public boolean validTree(int n, int[][] edges) {
	        int[] parent = new int[n];
	        for(int i=0; i<n; i++) {
	            parent[i] = i;
	        }
	        for(int[] edge : edges) {
	            if( find(parent, edge[0]) == find(parent, edge[1])) {
	                return false;
	            }
	            union(parent, edge[1], edge[0]);
	        }
	        
	        int root = find(parent,0);
	        for(int i=1; i<n; i++) {
	            if(find(parent,i) != root) {
	                return false;
	            }
	        }
	        return true;
	    }
	    
	    int find(int[] parent, int node) {
	        while(parent[node] != node) {
	            node = parent[node];
	        }
	        return node;
	    }
	    
	    void union(int[] parent, int node, int parentNode) {
	        parent[node] = parentNode;
	    }

	public static void main(String[] args) {
		Solution261 sol = new Solution261();
		
		boolean res = sol.validTree(4, new int[][]{{0,1}, {2,3}});
		System.out.println(res);

	}

}

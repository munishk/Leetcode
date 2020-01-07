/**
 * https://leetcode.com/problems/number-of-islands/
 * @author munishk
 *
 */
public class CountIsland {
	
	  /*
	   * Iterate over each island and recursively join other connected island with this.
	   */
	  public int numIslands(char[][] grid) {
	        int count = 0;
	        int m = grid.length;
	        if(m == 0) {
	            return 0;
	        }
	        int n =  grid[0].length;
	        boolean[][] visited = new boolean[m][n];
	        for(int i=0; i<m; i++) {
	            for(int j=0; j<n; j++) {
	                if(grid[i][j] == '1' && !visited[i][j]) {
	                    count++;
	                    joinIsland(grid, visited, i, j);
	                }
	            }
	        }
	        return count;
	    }
	    
	    private void joinIsland(char[][] grid, boolean[][] visited, int row, int col) {
	        if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || visited[row][col] || grid[row][col] == '0') {
	            return;
	        }
	        
	        visited[row][col] = true;
	        int[] x = {1, -1, 0, 0};
	        int[] y = {0, 0, 1, -1};
	        for(int k=0; k<4; k++) {
	            joinIsland(grid, visited, row+x[k], col+y[k]);
	        }
	    }
	    
	    /*
	     * Count number of island using disjoint set data structure. First count all islands and then keep doing union and reducing the count.
	     */
	    public int numIslandsDisjointSet(char[][] grid) {
	    	if(grid == null || grid.length == 0 || grid[0].length == 0) {return 0;}
	    	DisjointSet ds = new DisjointSet(grid);
	    	System.out.println(ds);
	        int[] rows = {1, -1, 0, 0};
	        int[] cols = {0, 0, 1, -1};
	        
	    	for(int i=0; i<grid.length; i++) {
	    		for(int j=0; j<grid[0].length; j++) {
	    			for(int k=0; k<4; k++) {
	    				if(grid[i][j] == '1') {
	    					int x = i + rows[k];
	    					int y = j + cols[k];
	    					if(x>=0 && x < grid.length && y >=0 && y<grid[0].length && grid[x][y] == '1') {
	    						ds.union(new Point(i,j), new Point(x,y));
	    					}
	    				}
	    			}
	    		}
	    	}
	    	System.out.println(ds);
	    	return ds.count;
	    }
	    
	    private static class DisjointSet {
	    	Point[][] parent;
	    	int count;
	    	
	    	public DisjointSet(char[][] grid) {
	    		parent = new Point[grid.length][grid[0].length];
	    		for(int i=0; i<grid.length; i++) {
	    			for(int j=0; j<grid[0].length; j++) {
	    				if(grid[i][j] == '1') {
	    					count++;
	     					parent[i][j] = new Point(i,j);
	    				}
	
	    			}
	    		}
	    	}
	    	
	    	public Point find(Point p) {
	    		if(parent[p.x][p.y].equals(p)) {
	    			return p;
	    		}
	    		parent[p.x][p.y] = find(parent[p.x][p.y]);
	    		return parent[p.x][p.y];
	    	}
	    	
	    	public void union(Point p1, Point p2) {
	    		Point p3 = find(p1);
	    		Point p4 = find(p2);
	    		if(!p3.equals(p4)) {
	    			parent[p4.x][p4.y] = p3;
	    			count--;
	    		}
	    	}

			@Override
			public String toString() {
				StringBuilder sb = new StringBuilder();
				for(Point[] charArr : parent) {
					for(Point p : charArr) {
					  sb.append(String.format("%6s", p));
					}
					sb.append("\n");
				}
				return sb.toString();
			}
	    }
	    
	    private static class Point {
	    	int x, y;
	    	
	    	public Point(int x, int y) {
	    		this.x = x;
	    		this.y = y;
	    		
	    	}
	    	
			@Override
			public int hashCode() {
				final int prime = 31;
				int result = 1;
				result = prime * result + x;
				result = prime * result + y;
				return result;
			}
			
			@Override
			public boolean equals(Object obj) {
				if (this == obj)
					return true;
				if (obj == null)
					return false;
				if (getClass() != obj.getClass())
					return false;
				Point other = (Point) obj;
				if (x != other.x)
					return false;
				if (y != other.y)
					return false;
				return true;
			}

			@Override
			public String toString() {
				return "(" + x + "," + y + ")";
			}
	    }

	public static void main(String[] args) {
		CountIsland sol = new CountIsland();
		//char[][] grid = { {'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
		char[][] grid = { {'1','1','1'},{'0','1','0'},{'1','1','1'}};
		System.out.println(sol.numIslandsDisjointSet(grid));

	}

}

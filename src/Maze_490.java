import java.util.LinkedList;

public class Maze_490 {

	 int[][] directions = {{-1,0}, {1,0}, {0, -1}, {0,1}};

	    public class Point {
	        int x, y;
	        public Point(int x, int y) {
	            this.x = x;
	            this.y = y;
	        }
	        
	        public boolean equals(Point other) {
	            return other.x == x && other.y == y;
	        }
	        
	        public String toString() {
	        	return String.format("%s, %s", x, y);
	        }
	    }
		public boolean hasPathBFS(int[][] maze, int[] start, int[] destination) {
			 if(maze ==  null | maze[0].length == 0) {
		         return false;
		     }
		     
		     int m = maze.length;
		     int n = maze[0].length;
		     Point s = new Point(start[0], start[1]);
		     Point d = new Point(destination[0], destination[1]);
		     boolean[][] visited = new boolean[m][n];
		     //Using BFS approach
		     LinkedList<Point> queue = new LinkedList<>();
		     queue.add(s);
		     visited[s.x][s.y] = true;
		     while(!queue.isEmpty()) {
		         Point p = queue.poll();
		         for(int[] dir: directions) {
			         int x = p.x;
			         int y = p.y;
		             while (x>=0 && x<m && y>=0 && y<n &&  maze[x][y]==0) {
		                 x = x + dir[0];
		                 y = y+ dir[1];
		             }
		             x = x - dir[0];
		             y = y - dir[1];
		             if(visited[x][y]) continue;
		             
		             if(d.x == x && d.y == y) {
		                 return true;
		             }
		             visited[x][y] = true;
		             queue.add(new Point(x, y));
		         }
		     }
		     return false;
		}
		
		
		public boolean hasPathDFS(int[][] maze, int[] start, int[] destination) {
		     if(maze ==  null | maze[0].length == 0) {
		         return false;
		     }
		     
		     int m = maze.length;
		     int n = maze[0].length;
		     Point s = new Point(start[0], start[1]);
		     Point d = new Point(destination[0], destination[1]);
		     boolean[][] visited = new boolean[m][n];
		     return hasPathDFS(maze, s, d, visited);
		}
		
		private boolean hasPathDFS(int[][] maze, Point start, Point dest, boolean[][] visited) {
		    if(start.x < 0 || start.x >= maze.length || start.y < 0 || start.y >= maze[0].length || visited[start.x][start.y]) {
		        return false;
		    }
		    
		    if(start.equals(dest)) {
		        return true;
		    }
		    
		    visited[start.x][start.y] = true;
		    for(int[] dir: directions) {
		        int x = start.x;
		        int y = start.y;
		        while(x>=0 && x<maze.length && y>=0 && y<maze[0].length && maze[x][y] ==0) {
		            x = x+ dir[0];
		            y = y + dir[1];
		        }
		        
		        x = x-dir[0];
		        y = y-dir[1];
		        if(hasPathDFS(maze, new Point(x,y), dest, visited)) {
		            return true;
		        }
		    }
		   // visited[start.x][start.y] = false;
		    return false;
		}
		
		
	public static void main(String[] args) {
		Maze_490 obj = new Maze_490();
//		int[][] maze ={
//				{0,1,0,1,0,0,0,0,0,0,1},
//				{0,1,0,1,1,1,0,1,1,0,0},
//				{1,0,0,0,0,0,0,0,0,0,1},
//				{0,0,0,1,1,1,0,1,0,0,1},
//				{1,1,0,0,0,1,0,0,0,1,1},
//				{0,1,0,0,0,0,0,1,0,1,0},
//				{0,0,0,0,1,0,0,1,1,1,0}
//				};
//		int[] start = {0,4};
//		int[] end = {0,6};
		
		int[][] maze = {{0,0,1,0,0},{0,0,0,0,0},{0,0,0,1,0},{1,1,0,1,1},{0,0,0,0,0}};
		int[] start = {0,4};
		int[] end = {3,2};
	
		System.out.println(obj.hasPathBFS(maze, start, end));
		System.out.println(obj.hasPathDFS(maze, start, end));
	}

}

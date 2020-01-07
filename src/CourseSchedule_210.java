import java.util.*;

public class CourseSchedule_210 {
	
	/*
	 * Topological sorting using bfs approach.
	 * Steps:
	 * 1. Create adjacency list and calculates incoming edges for each vertex/course.
	 * 2. Add vertex with incoming edge count 0 to queue.
	 * 3. Process elements from queue one by one, and add this to result array. 
	 *      - Using adjacency list info, decrease incoming edge count for all vertex which are dependent on this vertex.
	 *      - If incoming edge count is 0, add vertex to queue.
	 * 4. If result array contains all course/vertex, then there is no cycle else there exists some cycle.
	 * 
	 */
	public int[] findOrderBfs(int numCourses, int[][] prerequisites) {
		Map<Integer, List<Integer>> adjList = new HashMap<>();
		int[] incoming = new int[numCourses];
		for(int[] dep: prerequisites) {
		    adjList.putIfAbsent(dep[1], new ArrayList<>());
		    adjList.get(dep[1]).add(dep[0]);
		    incoming[dep[0]]++;
		}
		
		Queue<Integer> queue = new ArrayDeque<>();
		int[] out = new int[numCourses];
		int outIndex = 0;
		for(int i=0; i<numCourses; i++) {
		    if(incoming[i] == 0) {
		        queue.offer(i);
		    }
		}
		
		while(!queue.isEmpty()) {
		    int course = queue.poll();
		    out[outIndex++] = course;
		    if(adjList.get(course) != null) {
		        for(Integer nextCourse: adjList.get(course)) {
		            incoming[nextCourse]--;
		            if(incoming[nextCourse] == 0) {
		                queue.offer(nextCourse);
		            }
		        }
		    }
		}
		
		if(outIndex != numCourses) {
		    return new int[0];
		}
		
		return out;
	}


	/*
	 * Topological sorting using DFS approach.
	 * Steps:
	 * 1. Create adjacency list from given dependency information.
	 * 2. Start visiting all nodes using dfs approach.
	 * 3. If at any time, no unvisited node exist, then add this node to output stack and continue processing next element from stack.
	 */
	public int[] findOrderDfs(int numCourses, int[][] prerequisites) {
		Map<Integer, List<Integer>> adjList = new HashMap<>();
		for (int[] dependency : prerequisites) {
			adjList.putIfAbsent(dependency[1], new ArrayList<>());
			adjList.get(dependency[1]).add(dependency[0]);
		}
		int[] visited = new int[numCourses];
		LinkedList<Integer> res = new LinkedList<>();
		for (int i = 0; i < numCourses; i++) {
			if (visited[i] == 0) {
				boolean cycle = dfs(i, adjList, visited, res);
				if (cycle) {
					return new int[0];
				}
			}
		}

		int[] arr = new int[numCourses];
		int index = 0;
		for (Integer val : res) {
			arr[index++] = val;
		}
		return arr;
	}

	private boolean dfs(int course, Map<Integer, List<Integer>> adjList, int[] visited, LinkedList<Integer> res) {
		visited[course] = 1;
		while (true) {
			int nextCourse = getNextUnvisited(course, adjList, visited);
			if (nextCourse == -2) {
				return true;
			} else if (nextCourse == -1) {
				res.addFirst(course);
				visited[course] = 2;
				return false;
			}
			boolean ret = dfs(nextCourse, adjList, visited, res);
			if (ret) {
				return ret;
			}
		}
	}

	private int getNextUnvisited(int course, Map<Integer, List<Integer>> adjList, int[] visited) {
		if (adjList.get(course) != null) {
			for (Integer nextCourse : adjList.get(course)) {
				if (visited[nextCourse] == 1) {
					return -2;
				}
				if (visited[nextCourse] == 0) {
					return nextCourse;
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		CourseSchedule_210 obj = new CourseSchedule_210();
		int[][] pre = { { 1, 0 } };
		int[] out = obj.findOrderDfs(3, pre);
		for (int val : out) {
			System.out.print(val + " ");
		}

	}

}

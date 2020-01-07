import java.util.ArrayList;
import java.util.List;

public class BrickWall_554 {
	
	 public int leastBricks(List<List<Integer>> wall) {
	        int sum = 0;
	        for(int i=0; i<wall.get(0).size(); i++) {
	            sum += wall.get(0).get(i);
	        }
	        
	        int[][] dp = new int[wall.size()][sum+1];
	        for(int i=0; i<wall.size(); i++) {
	            sum = 0;
	            for(int j=0; j<wall.get(i).size(); j++) {
	                sum += wall.get(i).get(j);
	                dp[i][sum] = sum;
	            }
	        }
	        int min = wall.size();
	        for(int i=1; i<sum; i++) {
	            int count = wall.size();
	            for(int j=0; j<wall.size(); j++) {
	                if(dp[j][i] == i) {
	                    count--;
	                }
	            }
	            min = Math.min(min, count);
	        }
	        return min;
	    }

	public static void main(String[] args) {
		BrickWall_554 obj = new BrickWall_554();
		int[][] wall = {{1,2,2,1},
		 {3,1,2},
		 {1,3,2},
		 {2,4},
		 {3,1,2},
		 {1,3,1,1}};
       System.out.println(obj.leastBricks(convert(wall)));		

	}
	
	private static List<List<Integer>> convert(int[][] wall) {
		List<List<Integer>> res = new ArrayList<>();
		for(int i=0; i<wall.length; i++) {
			res.add(new ArrayList<>());
			for(int j=0; j<wall[i].length; j++) {
				res.get(i).add(wall[i][j]);
			}
		}
		return res;
	}

}

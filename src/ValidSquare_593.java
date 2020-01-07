import java.util.Arrays;

public class ValidSquare_593 {
	
	 public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
	        int[][] points =  new int[][] {p1, p2, p3, p4};
	        Arrays.sort(points, (o1, o2) -> o1[0] == o2[0] ? o1[1] -o2[1] : o1[0] -o2[0]);
	        
	        //find difference b/w first 2 points.
	        p1 = points[0];
	        p2 = points[1];
	        p3 = points[2];
	        p4 = points[3];
	        
	        double size = distance(p1, p2);
	        
	        //check distance b/w p1 p3
	        if(distance(p1, p3) != size) {
	            return false;
	        }
	        
	        if(distance(p3,p4) != size) {
	            return false;
	        }
	        
	        
	        if(distance(p2, p4) != size) {
	            return false;
	        }
	        
	        return true;
	    }
	    
	    private double distance(int[] p1, int[] p2) {
	        return Math.sqrt((p2[1]-p1[1])*(p2[1]-p1[1]) + (p2[0]-p1[0])*(p2[0]-p1[0]));
	    }

	public static void main(String[] args) {
		ValidSquare_593 obj = new ValidSquare_593();
		int[] p1 = {0,0};
		int[] p2 =		{1,1};
		int[] p3 = 		{1,0};
		int[] p4 =		{0,1};
		System.out.println(obj.validSquare(p1, p2, p3, p4));

	}

}

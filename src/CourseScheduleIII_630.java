import java.util.Arrays;

public class CourseScheduleIII_630 {
	
	 public int scheduleCourse(int[][] courses) {
	        Arrays.sort(courses, (o1, o2) -> o1[1]==o2[1] ? o2[0] -o1[0] : o1[1] - o2[1]);
	        
	        int start = 1; int count = 0;
	        for(int[] course: courses) {
	            if(start + course[0] -1 <= course[1]) {
	            	start +=course[0];
	                count++;
	            }
	        }
	        return count;
	    }

	public static void main(String[] args) {
		CourseScheduleIII_630 obj = new CourseScheduleIII_630();
		int[][] courses = {{1,2},{2,3}};
		System.out.println(obj.scheduleCourse(courses));

	}

}

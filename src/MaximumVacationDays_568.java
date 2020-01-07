public class MaximumVacationDays_568 {
	
	public int maxVacationDaysDP(int[][] flights, int[][] days) {
        if(flights == null || flights.length ==0 || days == null || days.length == 0) {
            return 0;
        }
        int cities = flights.length;
        int weeks = days[0].length;
        int[][] dp = new int[weeks][cities];
        for(int i=0; i<cities;i++) {
            if(i == 0 || flights[0][i] == 1) {
                dp[0][i] = days[i][0];
            }else {
                dp[0][i] = -1;
            }
        }
        for(int week=1; week<weeks; week++) {
            for(int city=0; city<cities; city++) {
                dp[week][city] = -1;
                for(int prevCity=0; prevCity<cities; prevCity++) {
                    if(dp[week-1][prevCity] != -1 &&(prevCity == city || flights[prevCity][city] == 1)) {
                        dp[week][city] = Math.max(dp[week][city], dp[week-1][prevCity] + days[city][week]);
                    }
                }
            }
        }
        
        int maxVacations = 0;
        for(int i=0; i<cities; i++) {
            maxVacations = Math.max(maxVacations, dp[weeks-1][i]);
        }
        return maxVacations;
    }
	
	public int maxVacationDays(int[][] flights, int[][] days) {
        return maxVacations(flights, days, 0, 0, days[0].length);
    }
    
    private int maxVacations(int[][] flights, int[][] days, int city, int week, int k) {
        if(week == k-1) {
            return days[city][week];
        }
        
        int max = 0, currMax = 0;
        for(int nextCity=0; nextCity<flights.length; nextCity++) {
            if(city == nextCity || flights[city][nextCity] == 1) {
                currMax = days[nextCity][week] + Math.max(maxVacations(flights, days, city, week+1, k), maxVacations(flights, days, nextCity, week+1, k));
                max = Math.max(max, currMax);
            }
        }
        return max;
    } 

	public static void main(String[] args) {
		MaximumVacationDays_568 obj = new MaximumVacationDays_568();
		int[][] flights = {{0,1,1},{1,0,1},{1,1,0}};
		int[][] days = {{1,3,1},{6,0,3},{3,3,3}};
		System.out.println(obj.maxVacationDays(flights, days));

	}

}

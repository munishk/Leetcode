public class PaintHouseII_265 {
	
	public int minCostII(int[][] costs) {
        if(costs == null || costs.length == 0 || costs[0].length == 0) {
            return 0;
        }
        
        int n = costs.length;
        int k = costs[0].length;
        int prevMin = 0; int secondPrevMin = 0; int prevMinIndex = -1;
        for(int i=0; i<n; i++) {
            int min = Integer.MAX_VALUE, secondMin = Integer.MAX_VALUE, minIndex = -1;
            for(int j=0;j<k; j++) {
                int currCost = costs[i][j] + (prevMinIndex == j?secondPrevMin:prevMin);
                if(minIndex == -1) {
                    min = currCost;
                    minIndex = j;
                }else if(currCost < min) {
                    secondMin = min;
                    min = currCost;
                    minIndex = j;
                }else if(currCost < secondMin) {
                    secondMin = currCost;
                }
            }
            prevMin = min;
            secondPrevMin =  secondMin;
            prevMinIndex = minIndex;
        }
     return prevMin;
    }

	public static void main(String[] args) {
		PaintHouseII_265 obj = new PaintHouseII_265();
		int[][] costs = {{1,5,3},{2,9,4}};
		System.out.println(obj.minCostII(costs));

	}

}

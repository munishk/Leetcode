import java.util.HashMap;
import java.util.Map;

public class ArithmeticSlicesII_446 {
	
	 public int numberOfArithmeticSlices(int[] A) {
         if(A == null || A.length < 3) {
            return 0;
        }
      int total = 0;
      Map<Integer, Integer>[] dp = new HashMap[A.length];
       for(int i=0; i<A.length; i++) {
           dp[i] = new HashMap<>();
           for(int j=0; j<i; j++) {
               long diff = (long)A[i]- (long)A[j];
               int c1 = dp[i].getOrDefault((int)diff, 0);
               int c2 = dp[j].getOrDefault((int)diff, 0);
               total+=c2;
               dp[i].put((int)diff, c1+c2+1);
           }
       }
       return total;
    }

	public static void main(String[] args) {
		ArithmeticSlicesII_446 obj = new ArithmeticSlicesII_446();
		int[] A = {2,4,6,8,10};
		System.out.println(obj.numberOfArithmeticSlices(A));

	}

}

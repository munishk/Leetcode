import util.Timer;

public class CoinChange_322 {
	
	public int coinChangeDp(int[] coins, int amount) {
	      int[] dp = new int[amount+1];
	      for(int i=1; i<=amount; i++) {
	          dp[i] = -1;
	          for(int j=0; j<coins.length; j++) {
	              if(i >= coins[j] && dp[i-coins[j]] != -1) {
	                  int coinCount = 1 + dp[i-coins[j]];
	                  dp[i] = dp[i] == -1? coinCount : Math.min(dp[i], coinCount);
	              }
	          }
	      }
	    return dp[amount];
	    }
	
	public int coinChange(int[] coins, int amount) {
        return coinChange(coins, amount, coins.length-1, 0);
    }
    
    private int coinChange(int[] coins, int amount, int index, int coinCount) {
        if(amount == 0) {
            return coinCount;
        }
        if(index < 0 || amount < 0) {
            return -1;
        }
        
        
        if(coins[index] > amount) {
            return coinChange(coins, amount, index-1, coinCount);
        }

        int incl = coinChange(coins, amount-coins[index], index, coinCount+1);
        int excl = coinChange(coins, amount, index-1, coinCount);    
        return incl == -1? excl : excl == -1? incl : (int)Math.min(incl, excl);
    }

	public static void main(String[] args) {
		CoinChange_322 obj = new CoinChange_322();
		int[] coins = {470,35,120,81,121};
				
		//int[] coins = {1};
	    Timer timer = new Timer();
	    timer.start();
		System.out.println(obj.coinChange(coins, 15000));
        timer.finish();
        timer.reset();
		System.out.println(obj.coinChangeDp(coins, 15000));
		timer.finish();
	}

}

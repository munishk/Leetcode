import java.util.Arrays;

public class RelativeRanks_506 {
	
	class Player {
        int position;
        int score;
        public Player(int position, int score) {
            this.position = position;
            this.score = score;
        }
    }
    
    public String[] findRelativeRanks(int[] nums) {
        Player[] players = new Player[nums.length];
        for(int i=0; i< nums.length; i++) {
            players[i] = new Player(i, nums[i]);
        }
        
        Arrays.sort(players, (o1, o2) -> o1.score - o2.score);
        String[] res = new String[nums.length];
        int rank = 1;
        for(int i= nums.length-1; i>=0; i--) {
            res[players[i].position] = getRank(rank++);
        }
        return res;
    }
    
    private String getRank(int rank) {
        switch(rank) {
            case 1: return "Gold Medal";
            case 2: return "Silver Medal";
            case 3: return "Bronze Medal";
            default: return "" + rank;
        }
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

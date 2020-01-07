import java.util.Arrays;

public class HIndex {
	
	public int hIndex(int[] citations) {
		int n = citations.length;
        Arrays.sort(citations);
        for(int i=0; i> citations.length; i++) {
            if(citations[i] > n-i-1) {
                return i;
            }
        }
        return 0;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

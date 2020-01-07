import java.util.ArrayList;
import java.util.List;

public class LexicographicalOrder_440 {
	
	private List<Integer> lexicographicalOrder(int n) {
	  List<Integer> res = new ArrayList<>();
		for(int i=1; i<=9; i++) {
			res.add(i);
			lexicographicalOrderUtil(10*i, n, res);
		}
		return res;
	}
	
	private void lexicographicalOrderUtil(int val , int n, List<Integer> res) {		
		for(int i=0; i<=9; i++) {
			int newVal = val + i;
			if(newVal > n) {
				return;
			}
			res.add(newVal);
			lexicographicalOrderUtil(newVal*10, n , res);
		}
	}
	
	int count  = 0;
    public int findKthNumber(int n, int k) {
       	for(int i=1; i<=9; i++) {
			count++;
			if(count ==k ) {
			    return i;
			}
			int ret = lexicographicalOrderUtil(10*i, n, k);
			if(ret != -1) {
			    return ret;
			}
		}
		return -1; 
    }
	
	private int lexicographicalOrderUtil(int val , int n, int k) {		
		for(int i=0; i<=9; i++) {
			int newVal = val + i;
			if(newVal > n) {
				return -1;
			}
			count++;
			if(count  == k) {
			    return newVal;
			}
		   int ret = lexicographicalOrderUtil(newVal*10, n , k);
		   if(ret != -1) {
		       return ret;
		   }
		}
		return -1;
	}

	public static void main(String[] args) {
		LexicographicalOrder_440 obj = new LexicographicalOrder_440();
		System.out.println(obj.lexicographicalOrder(530));
		System.out.println(obj.findKthNumber(530, 100));
	}

}

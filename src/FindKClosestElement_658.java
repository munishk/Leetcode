import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindKClosestElement_658 {
	
	public List<Integer> findClosestElements(List<Integer> arr, int k, int x) {
        int xIndex =  getXPos(arr, x);
        int l = xIndex, r = xIndex+1;
        int count = 0;
        while(count < k) {
            int low = l < 0? Integer.MAX_VALUE: arr.get(l);
            int high = r>= arr.size()? Integer.MAX_VALUE: arr.get(r);
            if(low != Integer.MAX_VALUE && Math.abs(x-low) <= Math.abs(high-x))  {
            	l--;
            }else {
            	r++;
            }
            count++;
        }
        
        List<Integer> res = new ArrayList<>();
        for(int i=l+1; i<r; i++) {
            res.add(arr.get(i));
        }
        
        return res;
    }
    
    private int getXPos(List<Integer> arr, int x) {
        int i=0;
        while(i<arr.size() && arr.get(i) < x) {
            i++;
        }
        return i-1;
    }

	public static void main(String[] args) {
		FindKClosestElement_658 obj = new FindKClosestElement_658();
		List<Integer> res= obj.findClosestElements(Arrays.asList(1,2,3,4,5), 4, -1);
        System.out.println(res);
	}

}

import util.StringUtils;

public class BeautifulArrangement_526 {
	
	int count = 0;
    public int countArrangement(int N) {
        int[] arr = new int[N];
        for(int i=0; i<N; i++) {
            arr[i] = i+1;
        }
        permutation(arr, 0);
        return count;
    }
    
    private void permutation(int[] arr, int index) {
        if(index >= arr.length) {
        	System.out.println(StringUtils.toString(arr));
            count++;
            return;
        }
        
        for(int i=index; i<arr.length; i++) {
            if(arr[i]%(index+1) == 0 || (index+1)%arr[i] ==0) {
                swap(arr, index, i);
                permutation(arr, index+1);
                swap(arr, index, i);
            } 
        }
    }
    
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

	public static void main(String[] args) {
		BeautifulArrangement_526 obj = new BeautifulArrangement_526();
		System.out.println(obj.countArrangement(3));

	}

}

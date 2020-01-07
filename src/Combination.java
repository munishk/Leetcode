import java.util.ArrayList;
import java.util.List;

public class Combination {
	
	public void combinationRecursive(int[] arr) {
		for(int k=1; k<= arr.length; k++) {
			System.out.println(String.format("*********** K - %s ***********", k));
		  combinationRecurive(arr, new int[arr.length], 0, 0, k);
		}
	}
	
	public void combinationRecurive(int[] arr, int [] temp, int index, int tempIndex, int k) {
		if(tempIndex == k) {
			print(temp, tempIndex);
			return;
		}
		
		if(index >= arr.length) {
			return;
		}
		
		temp[tempIndex] = arr[index];
		combinationRecurive(arr, temp, index+1, tempIndex+1, k);
		combinationRecurive(arr, temp, index+1, tempIndex, k);
	}
	
	void combinationIterative(int[] arr) {
		for(int k=1; k<=arr.length; k++) {
			System.out.println(String.format("*********** K - %s ***********", k));
			combinationIterative(arr, k);
		}
	}
	
	private static class Element {
		int val; int index;
		
		Element(int val, int index) {
			this.val = val;
			this.index = index;
		}
		
		@Override
		public String toString() {
			return ""+val;
		}
	}
	
	void combinationIterative(int[] arr, int k) {
		int n = arr.length;
		List<List<Element>> list = new ArrayList<>();
		for(int i=0; i<=n-k; i++) {
			List<Element> subList =  new ArrayList<>();
			subList.add(new Element(arr[i], i));
			list.add(subList);
		}
		List<List<Element>>  newList = null;
		for(int m=k-1; m>=1; m--) {
		newList = new ArrayList<>();
		for(int i=0; i<list.size(); i++) {
			List<Element> subList = list.get(i);
			for(int j= subList.get(subList.size()-1).index +1; j<=n-m; j++) {
				List<Element> newSubList = new ArrayList<>(subList);
				newSubList.add(new Element(arr[j], j));
				newList.add(newSubList);
			}
		}
		list = newList;
		}
		
		for(List<Element> subList : list) {
			System.out.println(subList);
		}
	}
	
	
	  void print(int[] temp, int index) {
	    	StringBuilder sb = new StringBuilder();
	    	for(int i=0; i<index; i++) {
	    		sb.append(temp[i] + " ");
	    	}
	    	System.out.println(sb);
	    }

	public static void main(String[] args) {
		Combination comb = new Combination();
		int[] arr= {2,4,6,8,10};
		//comb.combinationRecursive(arr);
		comb.combinationIterative(arr,3);

	}

}

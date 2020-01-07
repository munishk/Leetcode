import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShortestWordDistanceII {

	Map<String, List<Integer>> map = new HashMap<>();

	public ShortestWordDistanceII(String[] words) {
	       for(int i=0; i< words.length; i++) {
	           if(!map.containsKey(words[i])) {
	        	   
	             map.put(words[i], Arrays.asList(i));
	           } else {
	               map.get(words[i]).add(i);
	           }
	       }
	    }

	public int shortest(String word1, String word2) {
		List<Integer> firstList = map.get(word1);
		List<Integer> secondList = map.get(word2);
		int minDist = Integer.MAX_VALUE;
		for (Integer first : firstList) {
			for (Integer second : secondList) {
				minDist = Math.min(minDist, Math.abs(first - second));
			}
		}
		return minDist;
	}

	public static void main(String[] args) {
		String[] words = {"a", "b"};
		ShortestWordDistanceII sol = new ShortestWordDistanceII(words);
		System.out.println(sol.shortest("a", "b"));

	}

}

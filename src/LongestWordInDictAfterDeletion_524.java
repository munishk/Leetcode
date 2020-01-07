import java.util.*;

public class LongestWordInDictAfterDeletion_524 {

	public String findLongestWord(String s, List<String> d) {
		Set<String> dict = new HashSet<>();
		for (String word : d) {
			dict.add(word);
		}

		return findLongestWordDP(s, dict);
	}
	
	private String findLongestWordDP(String s, Set<String> dict) {
	      if(s ==  null || s.length() == 0) {
	          return "";
	      }
	      int n = s.length();
	      String[]dp = new String[n];
	      
	      dp[0] = "";
	      for(int i=0; i<n; i++) {
	          String str = s.substring(i, i+1);
	          if(dict.contains(str) && dp[0].compareTo(str) > 0) {
	              dp[0] = str;
	          }
	      }
	      
	      for(int i=2; i<=n; i++) {
	          dp[i-1] = dp[i-2];
	          for(int j=n-i; j>=0; j--) {
	              String str = s.substring(j, j+i);
	              if(dict.contains(str) && dp[i-1].compareTo(str) > 0){
	                  dp[i-1] = str;
	              }
	          }
	      }
	      return dp[n-1];
	      
	    }

	//Using DFS . Time complexity: O(n!)
	private String findLongestWord(String s, Set<String> dict, Map<String, String> memo) {
		if (s == null || s.length() == 0) {
			return "";
		}

		if (dict.contains(s)) {
			return s;
		}

		if (memo.containsKey(s)) {
			return memo.get(s);
		}
		String longestWord = null;
		;
		for (int i = 0; i < s.length(); i++) {
			String newString = s.substring(0, i) + s.substring(i + 1);
			String res = findLongestWord(newString, dict, memo);
			if (longestWord == null || longestWord.length() < res.length()) {
				longestWord = res;
			}
		}
		memo.put(s, longestWord);
		return longestWord;
	}
	
	/*
	 * Using BFS. Time compexity : O(n!)
	 */
	private String findLongestWord(String s, Set<String> dict) {
	       LinkedList<String> queue = new LinkedList<>();
	       if(dict.contains(s)) {
	           return s;
	       }
	       
	       queue.add(s);
	       boolean found = false;
	       String longestWord = null;
	       while(!queue.isEmpty() && longestWord == null) {
	           LinkedList newQ = new LinkedList<>();
	           String longest = null;
	           for(String str: queue) {
	               for(int i=0; i<str.length(); i++) {
	                   String newString = str.substring(0,i) + str.substring(i+1);
	                   if(dict.contains(newString)) {
	                       if(longestWord == null || longestWord.compareTo(newString) > 0) {
	                           longestWord = newString;
	                       }
	                   }else {
	                       newQ.add(newString);
	                   }
	               }
	           }
	           queue = newQ;
	       }
	       
	       return longestWord == null? "" : longestWord;
	    }

	public static void main(String[] args) {
		LongestWordInDictAfterDeletion_524 obj = new LongestWordInDictAfterDeletion_524();
		String s = "abpcplea";
		String[] dict = {"ale","apple","monkey","plea"};
		System.out.println(obj.findLongestWord(s, Arrays.asList(dict)));

	}

}

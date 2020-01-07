import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordBreakII_140 {
	
	public List<String> wordBreak(String s, List<String> wordDict) {
        List<String>[] dp = new ArrayList[s.length()];
        for(int i=0; i<s.length(); i++) {
            for(int j=0; j<i; j++) {
                String right = s.substring(j+1, i+1);
                if(wordDict.contains(right) && dp[j] != null) {
                    if(dp[i] == null) {
                       dp[i] = new ArrayList<>();
                    }
                    for(String left: dp[j]) {
                        dp[i].add(left + " " + right);
                    }
                }
            }
            String substr = s.substring(0,i+1);
            if(dp[i] == null && wordDict.contains(substr)) {
                dp[i] = new ArrayList<>();
                dp[i].add(substr);
            }
        }
        return dp[s.length()-1];
    }

	public static void main(String[] args) {
		WordBreakII_140 obj = new WordBreakII_140();
		String[] dict = 
			{"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"};
		System.out.println(obj.wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", Arrays.asList(dict)));

	}

}

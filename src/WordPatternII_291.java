import java.util.HashMap;
import java.util.Map;

public class WordPatternII_291 {
	
	public boolean wordPatternMatch(String pattern, String str) {
        return isPatternMatch(pattern, str, "", 0, 0, new HashMap<>());
    }
    
    
    private boolean isPatternMatch(String pattern, String str, String patternSofar, int strIndex, int patternIndex, Map<Character, String> patternMap) {
        if(pattern.equals(patternSofar) && strIndex == str.length()) {
            return true;
        }
        
        if(strIndex >= str.length() || patternIndex >= pattern.length()) {
            return false;
        }
        
        
        char patternChar = pattern.charAt(patternIndex);
        if(patternMap.containsKey(patternChar)) {
            String patternStr = patternMap.get(patternChar);
            String actualPatternStr = str.substring(strIndex, strIndex+patternStr.length());
            if(patternStr.equals(actualPatternStr)) {
            return isPatternMatch(pattern, str, patternSofar + patternChar, strIndex+ patternStr.length(), patternIndex+1, patternMap);
            }
        }else {
            for(int len=1; len<str.length()-patternIndex-1; len++) {
                String patternStr = str.substring(strIndex, strIndex+len);
                patternMap.put(patternChar, patternStr);
                boolean res = isPatternMatch(pattern, str, patternSofar+patternChar, strIndex+len, patternIndex+1, patternMap);
                if(res) {
                    return true;
                }
                patternMap.remove(patternChar);
            }
        }
        return false;
    }

	public static void main(String[] args) {
		WordPatternII_291 ob = new WordPatternII_291();
		System.out.println(ob.wordPatternMatch("abab", "redblueredblue"));
	}

}

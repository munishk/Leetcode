import java.util.HashMap;
import java.util.Map;

public class WordPattern_290 {
	
	/*
	 * Shorter version.
	 */
	public boolean wordPatternShorterVersion(String pattern, String str) {
		String[] parts = str.split(" ");
		if(pattern.length() != parts.length) {
			return false;
		}
		Map<Object, Integer> map = new HashMap<>();
		for(int i=0; i<pattern.length(); i++) {
			if(map.put(pattern.charAt(i), i) != map.put(parts[i], i)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean wordPattern(String pattern, String str) {
        Map<String, Integer> map = new HashMap<>();
        StringBuilder numberPattern1 = new StringBuilder();
        for(int i=0; i<pattern.length(); i++) {
            String c = pattern.substring(i, i+1);
            if(map.containsKey(c)) {
               numberPattern1.append(map.get(c)); 
            }else {
                numberPattern1.append(i);
                map.put(c, i);
            }
        }
        map.clear();
        
        //convert string to number pattern
        StringBuilder numberPattern2 = new StringBuilder();
        String[] parts = str.split(" ");
        for(int i=0; i<parts.length; i++) {
            String st = parts[i];
            if(map.containsKey(st)) {
                numberPattern2.append(map.get(st));
            }else {
                numberPattern2.append(i);
                map.put(st, i);
            }
        }
        
        return numberPattern1.toString().equals(numberPattern2.toString());
    }

	public static void main(String[] args) {
		WordPattern_290 obj = new WordPattern_290();
		System.out.println(obj.wordPatternShorterVersion("abc", "b c a"));

	}

}

import util.Timer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ScrambleString_87 {

	public boolean isScramble(String s1, String s2) {
		if (s1 == null && s2 == null) {
			return true;
		}

		if (s1 == null || s2 == null) {
			return false;
		}

		if (s1.length() != s2.length()) {
			return false;
		}

		if (s1.equals(s2)) {
			return true;
		}

        Map<String, Set<String>> mem = new HashMap<>();
		Set<String> scrambles = scrambleUtil(s1, mem);
		for (String scrambled : scrambles) {
			if (scrambled.equals(s2)) {
				return true;
			}
		}
		return false;
	}

	private Set<String> scrambleUtil(String s, Map<String, Set<String>> mem) {
		if (s == null || s.isEmpty()) {
			return new HashSet<>();
		}
		
		if(mem.get(s) != null) {
		    return mem.get(s);
		}

		if (s.length() == 1) {
			Set<String> res = new HashSet<>();
			res.add(s);
			mem.put(s, res);
			return res;
		}

		if (s.length() == 2) {
			Set<String> res = new HashSet<>();
			res.add("" + s.charAt(1) + s.charAt(0));
			mem.put(s, res);
			return res;
		}
		Set<String> res = new HashSet<>();
		for (int l = 1; l < s.length(); l++) {
			String leftStr = s.substring(0, l);
			String rightStr = s.substring(l);
			Set<String> leftSet = scrambleUtil(leftStr, mem);
			Set<String> rightSet = scrambleUtil(rightStr, mem);
			for (String left : leftSet) {
				for (String right : rightSet) {
					res.add(left + right);
				}
			}
			for (String left : leftSet) {
				res.add(rightStr + left);
			}

			for (String right : rightSet) {
				res.add(right + leftStr);
			}

			res.add(rightStr + leftStr);
		}
		mem.put(s, res);
		return res;
	}


	public static void main(String[] args) {
		ScrambleString_87 obj = new ScrambleString_87();
		Timer.start();
		System.out.println(obj.isScramble("abcdefghijdsfdfdfdf",
				"abcdefghijdsfdfdfmf"));
        Timer.finish();
	}

}

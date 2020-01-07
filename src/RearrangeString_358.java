import java.util.HashMap;
import java.util.Map;

public class RearrangeString_358 {

	public String rearrangeString(String s, int k) {
		if (k <= 1) {
			return s;
		}
		Map<Character, Integer> map = new HashMap<>();
		for (char c : s.toCharArray()) {
			map.putIfAbsent(c, 0);
			map.put(c, map.get(c) + 1);
		}

		Map<Character, Integer> duplicate = new HashMap<>();
		for (Map.Entry<Character, Integer> entry : map.entrySet()) {
			if (entry.getValue() > 1) {
				duplicate.put(entry.getKey(), entry.getValue());
			}
		}

		StringBuilder sb = new StringBuilder();
		for (Map.Entry<Character, Integer> dupEntry : duplicate.entrySet()) {
			char key = dupEntry.getKey();
			if (map.getOrDefault(key, 0) <= 1) {
				continue;
			}
			if (map.getOrDefault(key, 0) > 1 && map.size() < k) {
				return "";
			}

			sb.append(key);
			map.put(key, map.get(key) - 1);
			if (map.get(key) == 0) {
				map.remove(key);
			}
			int count = 1;
			for (char c : map.keySet()) {
				if (count > k)
					break;
				if (c == key)
					continue;
				sb.append(c);
				
				map.put(c, map.get(c) - 1);
				if (map.get(c) == 0) {
					map.remove(c);
				}
			}
		}

		for (char c : map.keySet()) {
			sb.append(c);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		RearrangeString_358 obj = new RearrangeString_358();
		String s = "aaabc";
		int k = 3;
		
		System.out.println(obj.rearrangeString(s, k));

	}

}

import java.util.*;

public class AlienDictionary_269 {

	public String alienOrder(String[] words) {
		Map<Character, Set<Character>> map = new HashMap<>();
		Map<Character, Integer> degree = new HashMap<>();

		for (String word : words) {
			for (char c : word.toCharArray()) {
				degree.put(c, 0);
			}
		}

		for (int i = 0; i < words.length - 1; i++) {
			String curr = words[i];
			String next = words[i + 1];
			int minLength = Math.min(curr.length(), next.length());
			for (int j = 0; j < minLength; j++) {
				char currChar = curr.charAt(j);
				char nextChar = next.charAt(j);
				if (currChar != nextChar) {
					map.putIfAbsent(currChar, new HashSet<>());
					Set<Character> set = map.get(currChar);
					if (!set.contains(nextChar)) {
						set.add(nextChar);
						degree.put(nextChar, degree.get(nextChar) + 1);
					}
				}
			}
		}

		// topological sorting using bfs
		Queue<Character> queue = new LinkedList<>();
		for (Map.Entry<Character, Integer> entry : degree.entrySet()) {
			if (entry.getValue() == 0) {
				queue.add(entry.getKey());
			}
		}
		StringBuilder sb = new StringBuilder();
		while (!queue.isEmpty()) {
			Character c = queue.poll();
			sb.append(c);
			if (map.get(c) != null) {
				for (Character cr : map.get(c)) {
					degree.put(cr, degree.get(cr) - 1);
					if (degree.get(cr) == 0) {
						queue.add(cr);
					}
				}
			}
		}
		return sb.length() != degree.size() ? "" : sb.toString();
	}

	public static void main(String[] args) {
		AlienDictionary_269 obj = new AlienDictionary_269();
		String[] words = { "wrt", "wrf", "er", "ett", "rftt" };
		System.out.println(obj.alienOrder(words));

	}

}

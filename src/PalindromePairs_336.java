import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PalindromePairs_336 {

	class TrieNode {
		TrieNode[] childs = new TrieNode[26];
		boolean isWord;
		Integer index;
	}

	class Trie {
		TrieNode root = new TrieNode();

		void insert(String word, int index) {
			TrieNode current = root;
			for (int i = 0; i < word.length(); i++) {
				char c = word.charAt(i);
				TrieNode next = current.childs[c - 'a'];
				if (next == null) {
					next = new TrieNode();
					current.childs[c - 'a'] = next;
				}
				current = next;
			}
			current.isWord = true;
			current.index = index;
		}

		void search(String word, List<List<Integer>> res, int secondIndex) {
			TrieNode current = root;
			boolean doDfs = true;
			for (int i = word.length() - 1; i >= 0; i--) {
				char c = word.charAt(i);
				TrieNode next = current.childs[c - 'a'];
				if (next == null) {
					doDfs = false; // no corrosponding string found
					break;
				}

				if (next.isWord && isPalindrome(word, 0, i - 1)) {
					List<Integer> list = new ArrayList<>();
					list.add(next.index);
					list.add(secondIndex);
					res.add(list);
				}
				current = next;
			}
			if (doDfs) {
				dfs(current, res, "", secondIndex);
			}

		}

		void dfs(TrieNode root, List<List<Integer>> res, String suffix, int secondIndex) {
			if (root == null) {
				return;
			}

			if (root.isWord && !suffix.isEmpty() && isPalindrome(suffix, 0, suffix.length() - 1)) {
				res.add(Arrays.asList(root.index, secondIndex));
			}

			for (int i = 0; i < 26; i++) {
				TrieNode next = root.childs[i];
				if (next != null) {
					dfs(next, res, suffix + (char) ('a' + i), secondIndex);
				}
			}
		}

		boolean isPalindrome(String s, int low, int high) {
			while (low < high) {
				if (s.charAt(low) != s.charAt(high)) {
					return false;
				}
				high--;
				low++;
			}
			return true;
		}
	}

	public List<List<Integer>> palindromePairs(String[] words) {
		Trie trie = new Trie();
		for (int i = 0; i < words.length; i++) {
			trie.insert(words[i], i);
		}
		List<List<Integer>> res = new ArrayList<>();
		for (int i = 0; i < words.length; i++) {
			trie.search(words[i], res, i);
		}
		return res;
	}

	public static void main(String[] args) {
		PalindromePairs_336 obj = new PalindromePairs_336();
		String[] words = { "bat", "tab", "cat" };
		System.out.println(obj.palindromePairs(words));

	}

}

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AddBoldTagInString_616 {

	class Pair {
		int start;
		int end;

		public Pair(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public String toString() {
			return "(" + start + ", " + end + ")";
		}
	}

	public String addBoldTag(String s, String[] dict) {
		List<Pair> pairs = new ArrayList<>();
		int index = 0;
		for (String substr : dict) {
			 index = s.indexOf(substr, index);
			while (index >= 0) {
				pairs.add(new Pair(index, index + substr.length()));
				 index = s.indexOf(substr, index + substr.length());
			}
		}

		// sort based on start index
		Collections.sort(pairs, (o1, o2) -> o1.start == o2.start ? o1.end - o2.end : o1.start - o2.start);

		// Merge pairs.
		int i = 1;
		while (i < pairs.size()) {
			Pair prev = pairs.get(i - 1);
			Pair curr = pairs.get(i);

			if (curr.start <= prev.end) {
				Pair mergedPair = new Pair(Math.min(prev.start, curr.start), Math.max(prev.end, curr.end));
				pairs.remove(i - 1);
				pairs.remove(i - 1);
				pairs.add(i - 1, mergedPair);
			} else {
				i++;
			}
		}

		StringBuilder sb = new StringBuilder();
		 index = 0;
		for (Pair pair : pairs) {
			int start = pair.start;
			int end = pair.end;
			sb.append(s.substring(index, start));
			sb.append("<b>");
			sb.append(s.substring(start, end));
			sb.append("</b>");
			index = end;
		}

		sb.append(s.substring(index, s.length()));
		return sb.toString();
	}
	
	public String addBoldTag_2(String s, String[] dict) {
        boolean[] bold = new boolean[s.length()];
        for (int i = 0, end = 0; i < s.length(); i++) {
            for (String word : dict) {
                if (s.startsWith(word, i)) {
                    end = Math.max(end, i + word.length());
                }
            }
            bold[i] = end > i;
        }
        
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!bold[i]) {
                result.append(s.charAt(i));
                continue;
            }
            int j = i;
            while (j < s.length() && bold[j]) j++;
            result.append("<b>" + s.substring(i, j) + "</b>");
            i = j - 1;
        }
        
        return result.toString();
    }

	public static void main(String[] args) {
		AddBoldTagInString_616 obj = new AddBoldTagInString_616();
		String s = "abcxyz123";
		String[] dict = { "abc","123" };
		System.out.println(obj.addBoldTag_2(s, dict));

	}

}

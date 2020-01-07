import java.util.ArrayList;
import java.util.List;

public class TextJustification_68 {

	public List<String> fullJustify(String[] words, int maxWidth) {
		if (words == null || words.length == 0) {
			return new ArrayList<>();
		}
		List<List<String>> list = new ArrayList<>();
		List<String> res = new ArrayList<>();
		int line = 0;
		int index = 0;
		int wordIndex = 0;
		while (wordIndex < words.length) {
			String word = words[wordIndex];
			if (index + word.length() < maxWidth) {
				if (list.size() <= line) {
					list.add(new ArrayList<>());
				}
				list.get(line).add(word);
				index += word.length() + 1;
				wordIndex++;
			} else {
				fixCurrentLine(list.get(line), index, maxWidth, res);
				line++;
				index = 0;
			}
		}
		fixCurrentLine(list.get(line), index, maxWidth, res);
		return res;
	}

	private void fixCurrentLine(List<String> words, int index, int maxWidth, List<String> res) {
		int remaining = maxWidth - index;
		int wordCount = words.size();
		StringBuilder sb = new StringBuilder();
		int spaceCount = wordCount == 1 ? remaining : remaining / (wordCount - 1);
		for (int i = 0; i < wordCount; i++) {
			sb.append(words.get(i));
			if (i == 0 && wordCount > 1) {
				int extraSpace = remaining - (remaining / (wordCount - 1)) * (wordCount - 1);
				appendSpace(sb, extraSpace);
			}
			if (wordCount == 1 || i < wordCount - 1) {
				appendSpace(sb, spaceCount + 1);
			}
		}
		res.add(sb.toString());
	}

	private void appendSpace(StringBuilder sb, int count) {
		for (int i = 0; i < count; i++) {
			sb.append(' ');
		}
	}

	public static void main(String[] args) {
		TextJustification_68 obj = new TextJustification_68();
		String[] words = { "" };
		System.out.println(obj.fullJustify(words, 0));

	}

}

import java.util.ArrayList;
import java.util.List;

public class EncoderDecoder_271 {

	// Encodes a list of strings to a single string.
	public String encode(List<String> strs) {
		if (strs == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for (String str : strs) {
			sb.append(str.length()).append(':').append(str);
		}
		return sb.toString();
	}

	// Decodes a single string to a list of strings.
	public List<String> decode(String s) {
		if (s == null) {
			return null;
		}
		List<String> res = new ArrayList<>();
		int index = 0;
		while (index < s.length()) {
			int len = getLength(s, index);
			int startIndex = index + String.valueOf(len).length() + 1;
			if(len == 0) {
                res.add("");
            }else {
            res.add(s.substring(startIndex, startIndex+len));
            }
			index = startIndex + len;
		}
		return res;
	}

	private int getLength(String s, int index) {
		int count = 0;
		char c = s.charAt(index++);
		while (c != ':') {
			count = count * 10 + (c - '0');
			c = s.charAt(index++);
		}
		return count;
	}

	public static void main(String[] args) {
		EncoderDecoder_271 obj = new EncoderDecoder_271();
		List<String> input = new ArrayList<>();
		input.add("");
		input.add("");
		System.out.println("Input: " +input);
		String encoded = obj.encode(input);
		System.out.println("Encoded: " + encoded);
		System.out.println("Decoded: " + obj.decode(encoded));

	}

}

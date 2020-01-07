import java.util.ArrayList;
import java.util.List;

public class RestoreIpAddress_93 {

	public List<String> restoreIpAddresses(String s) {
		List<String> res = new ArrayList<>();
		restoreIpAddresses(s, 0, 0, res, "");
		return res;
	}

	private void restoreIpAddresses(String s, int index, int group, List<String> res, String prefix) {
		if (group >= 3) {
			String part = s.substring(index);
			if (part.charAt(0) == '0' && part.length() > 1) {
				return;
			}
			if (part.length() <= 3 && Integer.parseInt(part) <= 255) {
				res.add(prefix + s.substring(index));
			}
			return;
		}

		if (index + 3 <= s.length() && s.substring(index, index + 3).equals("255")) {
			restoreIpAddresses(s, index + 3, group + 1, res, prefix + s.substring(index, index + 3) + ".");
		} else {
			for (int i = 1; i <= 3 && index + i <= s.length() && s.length() - (index + i) >= 3 - group; i++) {
				if ((i > 1 && s.charAt(index) == '0') || Integer.parseInt(s.substring(index, index + i)) > 255) {
					break;
				}
				restoreIpAddresses(s, index + i, group + 1, res, prefix + s.substring(index, index + i) + ".");
			}
		}
	}

	public static void main(String[] args) {
		RestoreIpAddress_93 obj = new RestoreIpAddress_93();
		System.out.println(obj.restoreIpAddresses("0000"));

	}

}

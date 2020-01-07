import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CanIWin_464 {

	public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
		boolean[] remaining = new boolean[maxChoosableInteger + 1];
		return canWin(remaining, desiredTotal, new ArrayList<>(), new HashMap<>());
	}

	private boolean canWin(boolean[] remaining, int remainingTotal, List<Integer> stack, Map<Integer, Boolean> mem) {
		Integer key = toInteger(remaining);
		if (mem.containsKey(key)) {
			System.out.println("Found:" + stack);
			return mem.get(key);
		}
		for (int i = remaining.length - 1; i >= 1; i--) {
			if (remaining[i] == true) {
				continue;
			}
			if (i >= remainingTotal) {
				List<Integer> res = new ArrayList<>(stack);
				res.add(i);
				System.out.println(res);
				mem.put(key, true);
				return true;
			}

			remaining[i] = true;
			stack.add(i);
			boolean canOtherWin = canWin(remaining, remainingTotal - i, stack, mem);
			if (!canOtherWin) {
				remaining[i] = false;
				stack.remove(stack.size() - 1);
				mem.put(key, true);
				return true;
			}
			remaining[i] = false;
			stack.remove(stack.size() - 1);
		}
		mem.put(key, false);
		return false;
	}

	private int toInteger(boolean[] remaining) {
		int res = 0;
		for (int i = remaining.length - 1; i > 0; i--) {
			res = res << 1;
			if (remaining[i] == true) {
				res |= 1;
			}
		}
		return res;
	}

	public static void main(String[] args) {
		CanIWin_464 obj = new CanIWin_464();
		System.out.println(obj.canIWin(10, 50));

	}

}

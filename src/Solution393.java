public class Solution393 {

	public boolean validUtf8(int[] data) {
		int count = 0;
		for (int val : data) {
			byte byteVal = (byte) val;
			printByte(byteVal);
			if (count == 0) {
				if ((byteVal & 0xF0) == 0xF0)
					count = 3;
				else if ((byteVal & 0xE0) == 0xE0)
					count = 2;
				else if ((byteVal & 0xC0) == 0xC0)
					count = 1;
				else if (byteVal >> 7 != 0)
					return false;
			} else {
				if (((byte) val & 0xD0) != 0x80) {
					return false;
				}
				count--;
			}
		}
		return true;
	}

	public boolean validUtf8_long(int[] data) {
		for (int i = 0; i < data.length;) {
			byte b = (byte) (data[i]);
			int byteCount = countMSBForOnes(b);
			if (byteCount == 0) {
				i++;
				continue;
			}

			if (byteCount == 1 || byteCount > 4) {
				return false;
			}

			if (!byteStartsWith10(data, i + 1, byteCount - 1)) {
				return false;
			}
			i = i + byteCount;
		}
		return true;
	}

	private boolean byteStartsWith10(int[] data, int start, int count) {
		if (start + count > data.length) {
			return false;
		}
		for (int i = start; i < start + count; i++) {
			printByte((byte) data[i]);
			if (((byte) data[i] & 0xC0) != 0x80) {
				return false;
			}
		}
		return true;
	}

	private int countMSBForOnes(byte data) {
		printByte(data);
		int count = 0;
		for (int i = 7; i >= 0; i--) {
			if ((1 << i & data) != 0) {
				count++;
			} else {
				break;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		Solution393 sol = new Solution393();
		int[] data = { 255 };
		int[] data1 = { 197, 130, 1 };
		int[] data2 = { 145 };
		System.out.println(sol.validUtf8(data));
		// System.out.println(sol.validUtf8(data1));
		// System.out.println(sol.validUtf8(data2));
		// test();

	}

	static void test() {
		int val = 197;
		System.out.println(val + ": " + Integer.toBinaryString(val));

		byte b = (byte) (val & 0xFF);
		printByte((byte) val);

	}

	static void printByte(byte val) {
		StringBuilder sb = new StringBuilder();
		for (int i = 7; i >= 0; i--) {
			if ((val & 1 << i) != 0) {
				sb.append("1");
			} else {
				sb.append("0");
			}
		}
		System.out.println(sb);
	}

}

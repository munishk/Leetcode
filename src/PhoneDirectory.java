import java.util.BitSet;

public class PhoneDirectory {

	BitSet bitSet;
	int maxNumbers;

	/**
	 * Initialize your data structure here
	 * 
	 * @param maxNumbers
	 *            - The maximum numbers that can be stored in the phone
	 *            directory.
	 */
	public PhoneDirectory(int maxNumbers) {
		bitSet = new BitSet(maxNumbers);
		this.maxNumbers = maxNumbers;
	}

	/**
	 * Provide a number which is not assigned to anyone.
	 * 
	 * @return - Return an available number. Return -1 if none is available.
	 */
	public int get() {
		for (int i = 0; i < maxNumbers; i++) {
			if (bitSet.get(i) == false) {
				bitSet.set(i);
				return i;
			}
		}
		return -1;
	}

	/** Check if a number is available or not. */
	public boolean check(int number) {
		return !bitSet.get(number);
	}

	/** Recycle or release a number. */
	public void release(int number) {
		bitSet.clear(number);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

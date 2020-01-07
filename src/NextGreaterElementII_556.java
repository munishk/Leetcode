import java.util.Arrays;

public class NextGreaterElementII_556 {
	
	public int nextGreaterElement(int n) {
        char[] digits = String.valueOf(n).toCharArray();
        
        for(int i=digits.length-2; i>=0; i--) {
            if(digits[i] < digits[i+1] ) {
                int swapIndex = findNextGreaterDigit(digits, i);
                swap(digits, i, swapIndex);
                sort(digits, i+1);
	                try {
	                return Integer.parseInt(new String(digits));
	                }catch(NumberFormatException ex) {
	                	return -1;
	                }
            }
        }
        return -1;
    }
    
    private void sort(char[] digits, int from) {
        char[] temp = new char[digits.length - from];
        System.arraycopy(digits, from, temp, 0, digits.length - from);
        Arrays.sort(temp);
        System.arraycopy(temp, 0, digits, from, digits.length - from);
    }
    
    private int findNextGreaterDigit(char[] digit, int index) {
        int foundIndex = index+1;
        for(int i=index+2; i<digit.length; i++) {
            if(digit[i] > digit[index] && digit[foundIndex] > digit[i]) {
                foundIndex = i;
            }
        }
        return foundIndex;
    }
    private void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

	public static void main(String[] args) {
		NextGreaterElementII_556 obj = new NextGreaterElementII_556();
		System.out.println(obj.nextGreaterElement(123456));

	}

}

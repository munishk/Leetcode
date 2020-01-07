import java.math.BigInteger;

public class RemoveKDigit_402 {
	
	public String removeKdigits(String num, int k) {
        if(num.length() == k) {
            return "0";
        }
        
        int index = 0; 
        BigInteger min = null;
        while(index+k <num.length()) {
            BigInteger val = new BigInteger(num.substring(0, index) + num.substring(index+k));
            if(min == null || min.compareTo(val) > 0) {
                min = val;
            }
            index++;
        }
        return min.toString();
    }

	public static void main(String[] args) {
		RemoveKDigit_402 obj  = new RemoveKDigit_402();
		System.out.println(obj.removeKdigits("10200", 1));

	}

}

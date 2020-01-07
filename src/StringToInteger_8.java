public class StringToInteger_8 {
	
	public int myAtoi(String str) {
        if(str == null|| str.trim().length() == 0) {
            return 0;
        }
        str = str.trim();
        long multiplier = 1;
        int index = 0;
        if(str.charAt(index) == '-') {
            multiplier = -1;
            index++;
        }else if(str.charAt(index) == '+') {
            multiplier = 1;
            index++;
        }
        
        long value = 0;
        for(int i= index; i<str.length(); i++) {
            char c = str.charAt(i);
            if(!Character.isDigit(c)) {
               break;
            }
            value = value*10 + (c - '0');
        }
        System.out.println(Long.toBinaryString(value*multiplier));
       
        long ret = value * multiplier;
        return ret == Integer.MAX_VALUE? Integer.MAX_VALUE: (int)ret;
    }

	public static void main(String[] args) {
		StringToInteger_8 obj = new StringToInteger_8();
		System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));
		System.out.println(Integer.MAX_VALUE);
		//System.out.println(obj.myAtoi("2147483648"));

	}

}

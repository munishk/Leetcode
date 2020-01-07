public class ReverseInteger_7 {
	
	public int reverse(int x)
	{
	    int result = 0;

	    while (x != 0)
	    {
	        int tail = x % 10;
	        int newResult = result * 10 + tail;
	        //if ((newResult - tail) / 10 != result)
	       // { return 0; }
	        result = newResult;
	        x = x / 10;
	    }

	    return result;
	}

	public static void main(String[] args) {
		ReverseInteger_7 obj = new ReverseInteger_7();
		System.out.println(obj.reverse(-321));

	}

}

public class ReadNChar_158 {
	
	private int read4(char[] buf) {
		buf[0] = 'a';
		return 1;
	}
	
	 public int read(char[] buf, int n) {
	        char[] tmp = new char[4];
	        int remaining = n;
	        int offset = 0;
	        while(remaining > 0) {
	            int count = read4(tmp);
	            System.arraycopy(tmp, 0, buf, offset, count);
	            offset = offset + count;
	            remaining  = remaining - count;
	            if(count < 4) {
	                break;
	            }
	        }
	       return offset; 
	    }

	public static void main(String[] args) {
		ReadNChar_158  obj  = new ReadNChar_158();
		char[] buf = new char[1];
        int count =	 obj.read(buf, 1);
        System.out.println(new String(buf));
	}

}

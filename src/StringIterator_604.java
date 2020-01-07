public class StringIterator_604 {
	
	String compressedString;
    int index = 0;
    int readCount = 0;
    int repCount = 0;

    public StringIterator_604(String compressedString) {
       this.compressedString  = compressedString;
       repCount = getCount(1);
    }
    
    public char next() {
        if(index >= compressedString.length()) {
            return ' ';
        }
        
        char c = compressedString.charAt(index);
        readCount++;
        if(readCount == repCount) {
            index += (String.valueOf(repCount).length() +1);
            if(index == compressedString.length() -1) {
                index = compressedString.length();
            }else {
              readCount  = 0;
              repCount = getCount(index+1);
            }
        }
       return c;
    }
    
    public boolean hasNext() {
        return index < compressedString.length();
    }
    
    private int getCount(int index) {
        if(index >= compressedString.length()) {
            return 0;
        }
        int count  = 0;
        char c = compressedString.charAt(index);
        while(Character.isDigit(c)) {
            count = count*10 + (c -'0');
            index++;
            if(index == compressedString.length()) break;
            c = compressedString.charAt(index);
        }
        return count;
    }

	public static void main(String[] args) {
		StringIterator_604 obj = new StringIterator_604("L1e2t1C1o1d1e1");
        System.out.println(obj.next());
        System.out.println(obj.next());
        System.out.println(obj.next());
        System.out.println(obj.next());
        System.out.println(obj.next());
        System.out.println(obj.next());
        System.out.println(obj.hasNext());
        System.out.println(obj.next());
        System.out.println(obj.hasNext());
        System.out.println(obj.next());
        System.out.println(obj.hasNext());
	}

}

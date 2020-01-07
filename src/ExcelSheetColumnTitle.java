public class ExcelSheetColumnTitle {
	
	 public String convertToTitle(int n) {
	        StringBuilder sb = new StringBuilder();
	        int count = 1, total = 0;
	        while((total + Math.pow(26, count)) < n) {
	            total = total + (int)Math.pow(26, count);
	            count++;
	        }
	        
	        n = n-total;
	        while(count-- > 1) {
	        	int divisor = (int)Math.pow(26, count);
	        	int res = (n-1)/divisor;
	            char c = (char) ('A' +  res);
	            sb.append(c);
	            n = n - res*divisor;
	        }
	        sb.append((char)('A' + (n-1)));
	        return sb.toString();
	    }

	public static void main(String[] args) {
		ExcelSheetColumnTitle obj = new ExcelSheetColumnTitle();
		System.out.println(obj.convertToTitle(26));

	}

}

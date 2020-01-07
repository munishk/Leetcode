public class ComplexNumberMultiplication_537 {
	
	public String complexNumberMultiply(String a, String b) {
        int aIndex = a.indexOf('+');
        int aReal = Integer.parseInt(a.substring(0, aIndex));
        int aImaginary = Integer.parseInt(a.substring(aIndex+1, a.length()-1));
        
        int bIndex = b.indexOf('+');
        int bReal = Integer.parseInt(b.substring(0, bIndex));
        int bImaginary = Integer.parseInt(b.substring(bIndex+1, b.length()-1));
        
        int real = aReal*bReal - aImaginary*bImaginary;
        int imaginary = aReal*bImaginary + bReal*aImaginary;
        return real  + "+" + imaginary + "i";
    }

	public static void main(String[] args) {
	ComplexNumberMultiplication_537 obj = new ComplexNumberMultiplication_537();
	String a = "1+1i";
	String b = "1+1i";
	System.out.println(obj.complexNumberMultiply(a, b));

	}

}

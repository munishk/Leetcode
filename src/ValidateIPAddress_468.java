public class ValidateIPAddress_468 {
	
	public String validIPAddress(String IP) {
        if(isValidIPv4(IP)) {
            return "IPv4";
        }else if (isValidIPv6(IP)) {
            return "IPv6";
        }else {
           return "Neither"; 
        }
    }
    
    private boolean isValidIPv6(String ip) {
        String[] groups = ip.split(":", -1);
        if(groups.length != 8) {
            return false;
        }
        
        for(int i=0; i<8; i++) {
            if(!isValidIPv6Group(groups[i])) {
                return false;
            }
        }
        return true;
    }
    
    private boolean isValidIPv6Group(String ip) {
        if(ip.length() == 0 || (ip.length() == 1 && ip.charAt(0) != '0')) {
            return false;
        }
        
        if(!(ip.length() == 1 || ip.length() == 4)) {
            return false;
        }
        
        for(int i=0; i<ip.length(); i++) {
            char c = ip.charAt(i);
            if (!(((c-'0') >= 0 && (c-'0') <= 9) || ((c-'a')>=0 && (c-'a') <=5) || ((c-'A')>=0 && (c-'A') <=5)))
                return false;
        }
        return true;
    }
    
    private boolean isValidIPv4(String ip) {
        String[] groups = ip.split("\\.", -1);
        if(groups.length != 4) {
            return false;
        }
       return validIPv4Segment(groups[0]) && validIPv4Segment(groups[1]) && validIPv4Segment(groups[2]) && validIPv4Segment(groups[3]);
    }
    
    private boolean validIPv4Segment(String segment) {
        if(segment.length() > 1 && segment.charAt(0) == '0') {
            return false;
        }
        try {
        int val = Integer.parseInt(segment);
        return val >= 0 && val <=255;
        }catch(NumberFormatException  ex) {
        	return false;
        }
    }

	public static void main(String[] args) {
		ValidateIPAddress_468 obj = new ValidateIPAddress_468();
		String ip = "2001:0db8:85a3:033:0:8A2E:0370:7334";
		//String ip = "2001:0db8:85a3:0:0:8A2E:0370:7334:";
		System.out.println(obj.validIPAddress(ip));
		//System.out.println(obj.isValidIPv4(ip));
		//System.out.println(obj.isValidIPv6(ip));
	}

}

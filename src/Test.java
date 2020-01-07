import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Test {

	public static void main(String[] args) {
       List<String> list = Arrays.asList("manish", "xy", "xz", "ab", "ac");
       Collections.sort(list, (o1, o2) -> o2.length() == o1.length() ? o1.compareTo(o2) : o2.length() - o1.length());
       System.out.println(list);
	}

}

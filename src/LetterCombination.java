import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombination {
	
	Map<Integer, char[]> digitMap = new HashMap<>();
	{
    digitMap.put(2, new char[] {'a','b','c'});
    digitMap.put(3, new char[] {'d','e','f'});
    digitMap.put(4, new char[] {'g','h','i'});
    digitMap.put(5, new char[] {'j','k','l'});
    digitMap.put(6, new char[] {'m','n','o'});
    digitMap.put(7, new char[] {'p','q','r','s'});
    digitMap.put(8, new char[] {'t','u','v'});
    digitMap.put(9, new char[] {'w','x','y','z'});
	}

    
    public List<String> letterCombinations(String digits) {
        List<char[]> charList = new ArrayList<>();
        for(char c: digits.toCharArray()) {
            charList.add(digitMap.get(c-'0'));
        }

        List<String> subList = new ArrayList<>();
        for(int i=0; i<charList.size();i++) {
           List<String> newSubList = new ArrayList<>();
           for(char c: charList.get(i)) {
               join(c, subList, newSubList);
           }
           subList = newSubList;
        }
        return subList;
    }
    
    private void join(char c, List<String> subList, List<String> res) {
        if (subList.isEmpty()) {
            res.add(""+c);
        }else {
        for(String str: subList) {
            res.add(str+c);
        }
        }
    }

	public static void main(String[] args) {
		LetterCombination sol = new LetterCombination();
		System.out.println(sol.letterCombinations("23"));

	}

}

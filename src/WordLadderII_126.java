import java.util.*;

public class WordLadderII_126 {
	
	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		List<List<String>> res = new ArrayList<>();
        List<List<String>> list = new ArrayList<>();
        list.add(Arrays.asList(beginWord));
        List<String> wordQueue = new ArrayList<>();
        wordQueue.add(beginWord);
        Set<String> visited = new HashSet<>();
        boolean found = false;
        while(!wordQueue.isEmpty()) {
            List<String> nextQueue = new ArrayList<>();
            List<List<String>> newList = new ArrayList<>();
            for(int i=0; i<wordQueue.size(); i++) {
                char[] wordArray = wordQueue.get(i).toCharArray();
                List<String> subList = list.get(i);
               outer: for(int j=0; j<wordArray.length; j++) {
                	char c = wordArray[j];
                    for(int k=0; k<26; k++) {
                        char replacementChar = (char) (k +'a');
                        if(c == replacementChar) { //if char is same as replacement char
                            continue;
                        }
                        wordArray[j] = replacementChar;
                        String nextWord = new String(wordArray);
                        if(nextWord.equals(endWord) && wordList.contains(nextWord)) {
                            List<String> newSublist = new ArrayList<>(subList);
                            newSublist.add(nextWord);
                            res.add(newSublist);
                            break outer;
                        }
                        
                        if(wordList.contains(nextWord) && !visited.contains(nextWord)) {
                            List<String> newSublist = new ArrayList<>(subList);
                            newSublist.add(nextWord);
                            newList.add(newSublist);
                            nextQueue.add(nextWord);
                            visited.add(nextWord);
                        }
                    }
                    wordArray[j] = c; //revert back
                }
            }
            list = newList;
            wordQueue = nextQueue;
            if(!res.isEmpty()) {
                return res;
            }

        }
        return res;
    }

	public static void main(String[] args) {
		WordLadderII_126 obj = new WordLadderII_126();
		String beginWord = "hit";
		String endWord = "cog";
		List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
		System.out.println(obj.findLadders(beginWord, endWord, wordList));

	}

}

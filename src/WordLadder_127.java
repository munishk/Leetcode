import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordLadder_127 {
	
	
	/*
	 * Using BFS approach.
	 */
	public int ladderLengthBFS(String beginWord, String endWord, Set<String> wordDict) {
        Set<String> reached = new HashSet<String>();
        reached.add(beginWord);
        wordDict.add(endWord);
        int distance = 1;
        while (!reached.contains(endWord)) {
            Set<String> toAdd = new HashSet<String>();
            for (String each : reached) {
            	System.out.println(each);
                for (int i = 0; i < each.length(); i++) {
                    char[] chars = each.toCharArray();
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        chars[i] = ch;
                        String word = new String(chars);
                        if (wordDict.contains(word)) {
                            toAdd.add(word);
                            wordDict.remove(word);
                        }
                    }
                }
            }
            distance++;
            if (toAdd.size() == 0) return 0;
            reached = toAdd;
            System.out.println("#########");
        }
        return distance;
    }
	
	/*
	 * Using BFS from both sides.
	 */
	public int ladderLengthBFS2Way(String beginWord, String endWord, List<String> wordList) {
		 Set<String> beginSet = new HashSet<>();
		 Set<String> endSet = new HashSet<>();
		 Set<String> dict = new HashSet<>(wordList);
		 dict.remove(endWord);
		 beginSet.add(beginWord);
		 endSet.add(endWord);
		 int len =1;
		 while(!beginSet.isEmpty() && !endSet.isEmpty()) {
		     if(beginSet.size() > endSet.size()) {
		         Set<String> temp = beginSet;
		         beginSet = endSet;
		         endSet = temp;
		     }
		     Set<String> neighbor = new HashSet<>();
		     for(String word: beginSet) {
		         char[] wordArr = word.toCharArray();
		         for(int i=0; i<wordArr.length; i++) {
		             char old = wordArr[i];
		             for(char c='a'; c<='z';c++) {
		                 wordArr[i] = c;
		                 String newWord = new String(wordArr);
		                 if(endSet.contains(newWord) ) {
		                     return len+1;
		                 }
		                 if(dict.contains(newWord)) {
		                     neighbor.add(newWord);
		                     dict.remove(newWord);
		                 }
		             }
		             wordArr[i] = old;
		         }
		     }
		         beginSet = neighbor;
		         len++;
		 }
		 return 0;
		}


	/*
	 * Using DFS approach. Time limit exceeded error.
	 */
	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		Set<String> wordSet = new HashSet<>();
		for (String word : wordList) {
			wordSet.add(word);
		}
		int min = ladderLengthDFS(beginWord, endWord, wordSet, new HashSet<>());
		return min == -1? 0: min;
	}

	private int ladderLengthDFS(String beginWord, String endWord, Set<String> wordList, Set<String> visited) {
		System.out.println(beginWord);
		if (beginWord.equals(endWord)) {
			return 1;
		}
		int min = -1;
		for (int i = 0; i < beginWord.length(); i++) {
			for (int j = 0; j < 26; j++) {
				char replacementChar = (char) ('a' + j);
				if (beginWord.charAt(i) == replacementChar) {
					continue;
				}
				String newWord = beginWord.substring(0, i) + replacementChar + beginWord.substring(i + 1);
				if (wordList.contains(newWord) && !visited.contains(newWord)) {
				    visited.add(newWord);
					int len = ladderLengthDFS(newWord, endWord, wordList, visited);
					if (len > -1) {
						min = min == -1 ? len + 1 : Math.min(min, len + 1);
					}
					visited.remove(newWord);
				}
			}
		}
		return min;
	}

	public static void main(String[] args) {
		WordLadder_127 obj = new WordLadder_127();
		//List<String> wordList = Arrays.asList("si","go","se","cm","so","ph","mt","db","mb","sb","kr","ln","tm","le","av","sm","ar","ci","ca","br","ti","ba","to","ra","fa","yo","ow","sn","ya","cr","po","fe","ho","ma","re","or","rn","au","ur","rh","sr","tc","lt","lo","as","fr","nb","yb","if","pb","ge","th","pm","rb","sh","co","ga","li","ha","hz","no","bi","di","hi","qa","pi","os","uh","wm","an","me","mo","na","la","st","er","sc","ne","mn","mi","am","ex","pt","io","be","fm","ta","tb","ni","mr","pa","he","lr","sq","ye");
		List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
		
		Set<String> dict = new HashSet<>(wordList);
		//System.out.println(obj.ladderLength("qa", "sq", wordList));
		System.out.println(obj.ladderLengthBFS2Way("hit", "cog", wordList));

	}

}

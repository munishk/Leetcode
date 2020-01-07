import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordSearchII_212 {
	  int[] rows = {0, 0 , -1, 1};
	    int[] cols = {-1, 1, 0, 0};
	    public List<String> findWords(char[][] board, String[] words) {
	        Set<String> res = new HashSet<>();
	        int m = board.length;
	        int n = board[0].length;
	        Set<String>[] wordsArray = new HashSet[26];
	        for(String word: words) {
	            char c = word.charAt(0);
	            Set<String> subList = wordsArray[c-'a'];
	            if(subList == null) {
	                subList = new HashSet<>();
	                wordsArray[c-'a'] = subList;
	            }
	            subList.add(word);
	        }
	        
	        for(int i=0; i<m; i++) {
	            for(int j=0; j<n; j++) {
	                 char c = board[i][j];
	                 Set<String> wordlist = wordsArray[c-'a'];
	                 if(wordlist == null) {
	                     continue;
	                 }
	                 
	                 for(String word : wordlist) {
	                    if(isPresent(board, i, j, word, 0, new HashSet<>())) {
	                        res.add(word);
	                    }
	                 }
	            }
	        }
	        return new ArrayList<>(res);
	    }
	    
	    private boolean isPresent(char[][] board, int x, int y, String word, int index, Set<Integer> visited) {
	        if(x<0 || x>=board.length || y <0 || y >= board[0].length || index >= word.length() || visited.contains(x*board[0].length + y)) {
	            return false;
	        }
	        
	        if(board[x][y] != word.charAt(index)) {
	            return false;
	        }
	        
	        if(index == word.length()-1) {
	            return true;
	        }
	        visited.add(x*board[0].length + y);
	        for(int i=0; i<4; i++) {
	            int newX = x + rows[i];
	            int newY = y + cols[i];
	            boolean found = isPresent(board, newX, newY, word, index+1, visited);
	            if(found) {
	                return true;
	            }
	        }
	        
	        visited.remove(x*board[0].length + y);
	        return false;
	    }

	public static void main(String[] args) {
		char[][] board = { { 'a', 'a' } };
		String[] words = { "aa" };
		WordSearchII_212 obj = new WordSearchII_212();
		System.out.println(obj.findWords(board, words));

	}

}

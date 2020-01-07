public class WordDictionary_211 {
	
	 private class TrieNode {
	        private boolean isTerminal;
	        private TrieNode[] childs = new TrieNode[26];
	    }
	    
	    private class Trie {
	        private TrieNode root = new TrieNode();
	        
	        public void insert(String word) {
	            TrieNode curr = root;
	            for(int i=0; i<word.length(); i++) {
	                char c = word.charAt(i);
	                if(curr.childs[c-'a'] == null) {
	                    curr.childs[c-'a'] = new TrieNode();
	                }
	                
	                curr = curr.childs[c-'a'];
	            }
	            curr.isTerminal = true;
	        }
	        
	        public boolean search(String word) {
	            return search(word, 0, root);
	        }
	        
	        private boolean search(String word, int index, TrieNode curr) {
	            if(curr == null) {
	                return false;
	            }
	            
	            if(index == word.length()) {
	                return curr.isTerminal;
	            }
	            
	            char c = word.charAt(index);
	            if(c == '.') {
	                for(int i=0; i<26; i++) {
	                  boolean res = search(word, index+1, curr.childs[i]);
	                  if(res) {
	                      return true;
	                  }
	                }
	             return false;
	            }else {
	               return  search(word, index+1, curr.childs[c-'a']);
	            }
	        }
	    }
	    

	    Trie trie;
	    /** Initialize your data structure here. */
	    public WordDictionary_211() {
	       trie = new Trie();
	    }
	    
	    /** Adds a word into the data structure. */
	    public void addWord(String word) {
	        trie.insert(word);
	    }
	    
	    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
	    public boolean search(String word) {
	       return trie.search(word);
	    }

	public static void main(String[] args) {
		WordDictionary_211 obj = new WordDictionary_211();
		obj.addWord("mad");
		obj.addWord("bad");
		obj.addWord("pad");
		System.out.println(obj.search("b.d"));

	}

}

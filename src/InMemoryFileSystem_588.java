import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryFileSystem_588 {
	
	   
	   public class DirNode {
	       Map<String,DirNode> childs;
	       boolean isFile;
	       String content;
	       String name;
	       
	        public DirNode(String name, String content) {
	           this.name = name;
	           this.content = content;
	           this.isFile = true;
	       }
	        
	        public DirNode(String name) {
		           this.name = name;
                   this.childs = new HashMap<>();
		       }
	       
	       public  List<String> ls() {
	            List<String> res = new ArrayList<>();
	            if(isFile) {
	            	res.add(name);
	            }else {
	                for(String name : childs.keySet()) {
	                    res.add(name);
	                }
	            }
	            return res;
	        }
	        
	        public DirNode addDir(String name) {
	        	DirNode newDir= new DirNode(name);
	            childs.put(name, newDir);
	            return newDir;
	        }
	        
	        public  DirNode getChild(String name) {
	            return childs.get(name);
	        }
	        
	         public  void addFile(String name, String content) {
	           DirNode file = new DirNode(name, content);
	           this.childs.put(name, file);
	        }
	        
	   }
	   

	   private DirNode root;
	   

	    public InMemoryFileSystem_588() {
	        root = new DirNode("/");
	    }
	    
	    public List<String> ls(String path) {
	    	String[] dirs = path.split("/");
	    	DirNode curr = root;
	        for(int i=1; i<dirs.length; i++) {
	        	DirNode next = curr.addDir(dirs[i]);
	            curr = next;
	        }
	        return curr.ls();
	    }
	    
	    public void mkdir(String path) {
	        String[] dirs = path.split("/");
	        DirNode curr = root;
	        for(int i=1; i<dirs.length; i++) {
	        	DirNode created = curr.addDir(dirs[i]);
	            curr = created;
	        }
	    }
	    
	    public void addContentToFile(String filePath, String content) {
	        String[] dirs = filePath.split("/");
	        DirNode curr = root;
	        for(int i=1; i<dirs.length-1; i++) {
	        	DirNode next = curr.getChild(dirs[i]);
	            curr = next;
	        }
	        curr.childs.put(dirs[dirs.length-1], new DirNode(dirs[dirs.length-1], content));
	    }
	    
	    public String readContentFromFile(String filePath) {
	        String[] dirs = filePath.split("/");
	        DirNode curr = root;
	        for(int i=1; i<dirs.length; i++) {
	        	DirNode next = curr.getChild(dirs[i]);
	            curr = next;
	        }
	        return curr.content;
	    }

	public static void main(String[] args) {
		InMemoryFileSystem_588 obj = new InMemoryFileSystem_588();
		System.out.println(obj.ls("/"));
		obj.mkdir("/a/b/c");
		obj.addContentToFile("/a/b/c/d", "hello");
		System.out.println(obj.ls("/"));
        System.out.println(obj.readContentFromFile("/a/b/c/d"));
	}

}

import java.util.HashMap;
import java.util.Map;

public class LRUCache_146 {

	 class Node {
	        Integer key;
	        Integer val;
	        Node prev;
	        Node next;
	        public Node(Integer key, Integer val) {
	            this.key = key;
	            this.val = val;
	        }
	    }
	    
	    Map<Integer, Node> map;
	    Node head, tail;
	    int max;


	    public LRUCache_146(int capacity) {
	        map = new HashMap<>(capacity);
	        this.max = capacity;
	    }
	    
	    public int get(int key) {
	        if(map.get(key) == null) {
	            return -1;
	        }
	        
	        Node node = map.get(key);
	        remove(node);
	        addNodeToTail(node);
	        return node.val;
	    }
	    
	    public void put(int key, int value) {
	         if(max <= 0) {
	            return;
	         }
	         if(map.containsKey(key)) {
	             Node node = map.get(key);
	             node.val = value;
	             return;
	         }
	         
	       Node node = new Node(key, value);
	       if(map.size() >= max) {
	           Node removed = removeOldNode();
	           map.remove(removed.key);
	        }
	        addNodeToTail(node);
			map.put(key, node);
	    }
	    
	    private void addNodeToTail(Node node) { 
	        if(tail == null) {
	            head = tail = node;
	        }else {
	            tail.next = node;
	            node.prev = tail;
	            tail = node;
	        }
	    }
	    
	    private Node removeOldNode() {
	        Node removed = null;
	        if(head == tail) {
	            removed = head;
	            head = null;
	            tail = null;
	        }else {
	          removed = head;
	          head = head.next;
	          head.prev = null;
	        }
	        return removed;
	    }
	    
	    
	    private void remove(Node node) {
	        if(node == head) {
	            if(head == tail) {
	                head = null;
	                tail = null;
	            }else {
	            head = head.next;
	            head.prev = null;
	            }
	            return;
	        }
	        
	        if(node.prev != null) {
	            node.prev.next = node.next;
	        }
	        
	        if(node.next != null) {
	            node.next.prev = node.prev;
	        }
	    }

	public static void main(String[] args) {
		LRUCache_146 obj = new LRUCache_146(2);
		obj.put(2, 1);
		System.out.println(obj.get(2));
		obj.put(3, 2);
		System.out.println(obj.get(2));
		System.out.println(obj.get(3));


	}

}

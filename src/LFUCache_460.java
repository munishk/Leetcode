import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class LFUCache_460 {

	class Node {
		LinkedHashSet<Integer> keys;
		Node prev;
		Node next;
		int freq;

		public Node(int freq) {
			this.keys = new LinkedHashSet<>();
			this.freq = freq;
		}

		@Override
		public String toString() {
			return freq + ":" + keys;
		}
		
	}

	Map<Integer, Integer> valueMap;
	Map<Integer, Node> nodeMap;
	Node head;
	int max;

	public LFUCache_460(int capacity) {
		valueMap = new HashMap<>(capacity);
		nodeMap = new HashMap<>();
		this.max = capacity;
	}

	public int get(int key) {
		if (valueMap.get(key) == null) {
			return -1;
		}

		incrementKey(key);

		return valueMap.get(key);
	}

	public void put(int key, int value) {
		if (max <= 0) {
			return;
		}

		if (valueMap.containsKey(key)) {
			incrementKey(key);
		} else {
			if (valueMap.size() >= max) {
				int oldKey = removeEldest();
				valueMap.remove(oldKey);
				nodeMap.remove(oldKey);
			}
			addKeyToHead(key);
		}

		valueMap.put(key, value);
	}

	private int removeEldest() {
		int key = head.keys.iterator().next();
		removeKey(head, key);
		return key;
	}

	private void incrementKey(int key) {
		Node node = nodeMap.get(key);
		Node newNode = new Node(node.freq + 1);
		newNode.keys.add(key);
		if (node.next == null) { // next node is null
			newNode.prev = node;
			node.next = newNode;
		} else if (node.next.freq == newNode.freq) { // next node has same freq
			node.next.keys.add(key);
			newNode = node.next;
		} else {
			newNode.next = node.next;
			node.next.prev = newNode;
			newNode.prev = node;
			node.next = newNode;
		}

		removeKey(node, key);
		nodeMap.put(key, newNode);
	}

	private void addKeyToHead(int key) {
		Node node = null;
		if (head == null) {
			node = new Node(0);
			node.keys.add(key);
			head = node;
		} else if (head.freq == 0) {
			node = head;
			head.keys.add(key);
		} else {
			node = new Node(0);
			node.keys.add(key);
			node.next = head;
			head.prev = node;
			head = node;
		}
		nodeMap.put(key, node);
	}

	private void removeKey(Node node, int key) {
		// finally remove old key
		node.keys.remove(key);
		if (node.keys.isEmpty()) {
			removeNode(node);
		}
	}

	private void removeNode(Node node) {
		if (node == head) {
			head = head.next;
			if (head != null) {
				head.prev = null;
			}
			return;
		}

		if (node.prev != null) {
			node.prev.next = node.next;
		}
		if (node.next != null) {
			node.next.prev = node.prev;
		}

	}

	public static void main(String[] args) {
		LFUCache_460 obj = new LFUCache_460(3);
		obj.put(2, 2);
		obj.put(1, 1);
		System.out.println(obj.get(2));
		System.out.println(obj.get(1));
		obj.put(3, 3);
		obj.put(4, 4);
		System.out.println(obj.get(3));
		System.out.println(obj.get(2));
		System.out.println(obj.get(1));
		System.out.println(obj.get(4));
	}

}

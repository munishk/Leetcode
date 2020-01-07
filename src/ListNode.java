public class ListNode {
	int val;
	ListNode next;
	
	public ListNode(int val) {
		this.val = val;
	}
	
	public static ListNode build(String input) {
		ListNode head = null; ListNode  currNode = null;
		String curr = "";
		for(int i=0; i<input.length();) {
			if(i+2 < input.length() && input.substring(i, i+2).equals("->")) {
				ListNode node = new ListNode(Integer.parseInt(curr));
				if(head == null) {
					head = node;
					currNode = head;
				}else {
					currNode.next = node;
					currNode = node;
				}
				curr = "";
				i+=2;
			}else {
				curr+=input.charAt(i);
				i++;
			}
		}
		print(head);
		return head;
	}
	
	public static void print(ListNode head) {
		StringBuilder sb = new StringBuilder();
		if(head != null) {
			sb.append(head.val);
		}
		ListNode curr = head.next;
		while(curr != null) {
			sb.append("->").append(curr.val);
			curr = curr.next;
		}
		System.out.println(sb);
	}

}

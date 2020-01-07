public class PartitionList {

	public ListNode partition(ListNode head, int x) {
		if (head == null)
			return head;

		ListNode left = null;
		ListNode curr = head;
		ListNode prev = null;
		while (curr != null && curr.val < x) {
			left = curr;
			curr = curr.next;
		}

		while (curr != null) {
			while (curr != null && curr.val >= x) {
				prev = curr;
				curr = curr.next;
			}

			if (curr != null) {
				prev.next = curr.next;
				if (left == null) {
					curr.next = head;
					left = curr;
					head = left;
				} else {
					curr.next = left.next;
					left.next = curr;
					left = left.next;
				}
				curr = prev.next;
			}
		}
		return head;
	}

	public static void main(String[] args) {
		PartitionList sol = new PartitionList();
		ListNode head = ListNode.build("1->4->3->2->5->2");
        ListNode res = sol.partition(head, 3);
        ListNode.print(res);
	}

}

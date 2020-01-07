public class ReverseLinkedListII {

	public ListNode reverseBetween(ListNode head, int m, int n) {
		if (n - m <= 0) {
			return head;
		}

		ListNode curr = head, left = null, right = null, prev = null, next = null;
		int count = 1;
		while (count < m) {
			left = curr;
			curr = curr.next;
			count++;
		}

		right = curr;
		prev = curr;
		curr = curr.next;
		count++;
		while (count <= n) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
			count++;
		}

		if (left == null) {
			right.next = next;
			return prev;
		}

		left.next = prev;
		right.next = next;
		return head;
	}

	public static void main(String[] args) {
		ListNode five = new ListNode(5);
		ListNode four = new ListNode(4);
		ListNode three = new ListNode(3);
		ListNode two = new ListNode(2);
		ListNode one = new ListNode(1);
		
		three.next = five;
		
		ReverseLinkedListII sol = new ReverseLinkedListII();
		System.out.println("###### Original ##########");
		ListNode.print(three);
		
		ListNode res = sol.reverseBetween(three, 1, 2);
		System.out.println("###### Output ##########");
		ListNode.print(res);

	}

}

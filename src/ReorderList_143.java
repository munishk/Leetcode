public class ReorderList_143 {
	
	public void reorderList(ListNode head) {
        if(head == null) return;
        //Find middle node
        ListNode mid = findMiddle(head);
        
        //reverse second half of list
        mid.next = reverse(mid.next);
        ListNode.print(head);
        
        ListNode left = head;
        ListNode right = mid.next;
        while(left != mid) {
            mid.next = right.next;
            right.next = left.next;
            left.next = right;
            left = right.next;
            right = mid.next;
        }
    }
    
    private ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    
    public ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while(curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

	public static void main(String[] args) {
		ReorderList_143 obj = new ReorderList_143();
		ListNode head = ListNode.build("1->2->3->4->5->6->7->8->9->10");
		ListNode.print(head);
		obj.reorderList(head); 
		ListNode.print(head);
		//ListNode res = obj.reverse(head.next.next);
		//ListNode.print(res);

	}

}

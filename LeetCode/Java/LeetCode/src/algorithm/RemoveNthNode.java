package algorithm;

public class RemoveNthNode {
	public static void main(String[] args)
	{
		ListNode head = null;
		
		ListNode l = new RemoveNthNode().new ListNode(1);
		head =l;
//		l.next = new RemoveNthNode().new ListNode(2);
//		l = l.next;
//		l.next = new RemoveNthNode().new ListNode(3);
//		l = l.next;
//		l.next = new RemoveNthNode().new ListNode(4);
//		l = l.next;
//		l.next = new RemoveNthNode().new ListNode(5);
//		l = l.next;
//		l.next = null;
		System.out.println("Previous:");
		printList(head);
		head=new RemoveNthNode().new Solution().removeNthFromEnd(head,1);
		
		System.out.println("Now:");
		printList(head);
	}
	public static void makeNull(ListNode node)
	{
		node = null;
	}
	public static void printList(ListNode node)
	{
		String msg ="";
		while(node != null)
		{
			msg+=node.val+"=>";
			node = node.next;
		}
		
		System.out.println(msg);
	}
	
	 class ListNode {
	      int val;
	      ListNode next;
	      ListNode(int x) { val = x; }
	 }
	 class Solution {
		    public ListNode removeNthFromEnd(ListNode head, int n) {
		    		
		    		
		    		ListNode deleteCandidate =null;		    		
		    		int size =0;
		    		
		    		
		    		
		    		ListNode previous = null;
		    		ListNode node = head;
		    		while(node != null)
		    		{
		    			if(size ==0)
		    			{
		    				deleteCandidate = node;
		    				
		    				size++;
		    			}
		    			else if(size== n)
		    			{
		    				previous = deleteCandidate;
		    				deleteCandidate = deleteCandidate.next;		    				
		    			}
		    			else
		    			{
		    				size++;
		    			}
		    			node = node.next;
		    		}
		    		if(previous != null)
		    		{
		    			previous.next = deleteCandidate.next;
		    		}
		    		else
		    		{
		    			head = head.next;
		    		}
		    		
		    		
		    		return head;
		    }
	}
}

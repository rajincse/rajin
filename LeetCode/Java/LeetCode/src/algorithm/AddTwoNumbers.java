package algorithm;

public class AddTwoNumbers {

	public static void main(String []args)
	{
		AddTwoNumbers instance = new AddTwoNumbers();
		ListNode l1 = instance.new ListNode(9);
		l1.next = instance.new ListNode(9);
		l1.next.next= instance.new ListNode(9);
		System.out.println(l1);
		
		ListNode l2 = instance.new ListNode(9);
		l2.next = instance.new ListNode(9);
		l2.next.next= instance.new ListNode(9);
		System.out.println(l2);
		
		ListNode result = instance.new Solution().addTwoNumbers(l1, l2);
		System.out.println(result);
	}
	//**
	// * Definition for singly-linked list.
	  public class ListNode {
	      int val;
	      ListNode next;
	      ListNode(int x) { val = x; }
	      @Override
	    public String toString() {
	    	// TODO Auto-generated method stub
	    	 String msg="";
	    	 ListNode node = this;
	    	 while(node != null)
	    	 {
	    		 msg+=node.val+"->";
	    		 node = node.next;
	    	 }
	    	 
	    	return msg;
	    }
	  }
	 
	public class Solution {
	    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
	        ListNode node1  = l1;
	        ListNode node2 = l2;
	        ListNode resultNode = null;
	    	ListNode currentNode =null;
	    	ListNode previousNode = null;
	        int val1 = 0;
	        int val2 = 0;
	        int inhand =0;
	        int result =0;
	        int sum =0;
	        
	        while(node1 != null || node2 != null)
	        {
	        	if(node1 != null)
	        	{
	        		val1 = node1.val;
	        		node1 = node1.next;
	        	}
	        	else
	        	{
	        		val1 = 0;
	        	}
	        	
	        	if(node2 != null)
	        	{
	        		val2 = node2.val;
	        		node2 = node2.next;
	        	}
	        	else
	        	{
	        		val2 = 0;
	        	}
	        	sum = val1+val2+inhand;
	        	result = (sum)%10;
	        	inhand = (sum)/10;
	        	currentNode = new ListNode(result);
	        	if(previousNode != null)
	        	{
	        		previousNode.next = currentNode;
	        		previousNode = currentNode;
	        	}
	        	else
	        	{
	        		resultNode = currentNode;
	        		previousNode = currentNode;
	        	}
	        	
	        }
	        if(inhand > 0)
	        {
	        	currentNode = new ListNode(inhand);
	        	previousNode.next = currentNode;
        		previousNode = currentNode;
	        }
	        
	        return resultNode;
	    }
	}
}

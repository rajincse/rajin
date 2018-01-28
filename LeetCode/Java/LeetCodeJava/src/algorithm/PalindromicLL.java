package algorithm;

/**
 * Created by rajin on 12/20/2017.
 * 234. Palindrome Linked List
 */
public class PalindromicLL {
    public static void main(String[] args)
    {
        int[] a = new int[]{1,2,3,2,1};
        ListNode node = ListNode.getList(a);
        System.out.println(new PalindromicLL().isPalindrome(node));
    }

    public boolean isPalindrome(ListNode head) {
        if(head ==null){
            return true;
        }

        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null)
        {
            fast = fast.next.next;
            slow = slow.next;
        }
        //case-1 even length, keep slow
        //case-2 odd length, put slow to next
        if(fast !=null && fast.next ==null)
        {
            slow = slow.next;
        }

        slow = reverseList(slow);
        //check reverse list

        while(slow != null)
        {
            if(head.val != slow.val)
            {
                return false;
            }
            slow = slow.next;
            head = head.next;
        }

        return true;
    }

    public ListNode reverseList(ListNode head) {
        if(head == null || head.next ==null)
        {
            return head;
        }
        else
        {
            ListNode next = head.next;
            ListNode reverse = reverseList(head.next);
            head.next = null;
            next.next = head;

            return reverse;
        }

    }
}

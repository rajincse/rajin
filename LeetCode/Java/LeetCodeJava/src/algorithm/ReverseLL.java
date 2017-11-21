package algorithm;

/**
 * Created by rajin on 11/20/2017.
 * 206. Reverse Linked List
 */
public class ReverseLL {
    public ListNode reverseList(ListNode head) {
        ListNode result = null;
        ListNode node = head;

        while(node != null)
        {
            ListNode temp = node;
            node = node.next;
            temp.next = result;
            result = temp;
        }

        return result;
    }

    public ListNode reverseListRecursive(ListNode head) {
        if(head == null || head.next ==null)
        {
            return head;
        }
        else
        {
            ListNode next = head.next;
            ListNode reverse = reverseListRecursive(head.next);
            head.next = null;
            next.next = head;

            return reverse;
        }

    }
    public  static  void main(String[] args)
    {
        ListNode node = ListNode.getList(new int[]{1,2,3});
        ListNode result = new ReverseLL().reverseList(node);
        System.out.println(result);
    }

}

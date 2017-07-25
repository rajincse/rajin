package algorithm;

/**
 * Created by rajin on 7/20/2017.
 */
public class RotateListRight {
    public static  void main(String []args){
        int[] a = new int[]{1,2,3};
        ListNode head = ListNode.getList(a);
        ListNode result = new RotateListRight().rotateRight(head,2000000000);
        System.out.println(result);
    }
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null)
        {
            return  head;
        }
        int size =0;
        ListNode sizeNode = head;
        while(sizeNode != null)
        {
            sizeNode = sizeNode.next;
            size++;
        }
        k  = k % size;
        ListNode fast = head;
        for(int i=0;i<k;i++)
        {
            fast = fast.next;
        }
        ListNode slow = head;
        while(fast.next != null)
        {
            fast = fast.next;
            slow = slow.next;
        }
        fast.next = head;
        head = slow.next;
        slow.next = null;
        return head;
    }
}

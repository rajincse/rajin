package algorithm;

import java.util.HashSet;

/**
 * Created by rajin on 8/9/2017.
 */
public class RemoveDuplicatesSorted {
    public static  void main(String[] args)
    {
        int[] a = new int[]{1,1,2,3,3,3};
        ListNode node = ListNode.getList(a);
        System.out.println(new RemoveDuplicatesSorted().deleteDuplicates(node));
    }
    public ListNode deleteDuplicates(ListNode head) {

        int lastElement= Integer.MIN_VALUE;
        ListNode node = head;
        ListNode previous = null;
        while (node != null)
        {
            if(lastElement == node.val)
            {
                previous.next = node.next;
            }
            else
            {
                lastElement = node.val;
                previous = node;
            }

            node  = node.next;
        }
        return head;

    }
}

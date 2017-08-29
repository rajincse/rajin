package algorithm;

/**
 * Created by rajin on 8/17/2017.
 * Leetcode #92. Reverse Linked List II
 */
public class ReverseLinkedList2 {
    public static void main(String[] args)
    {
        int[] a = new int[]{1,2,3};
        ListNode node = ListNode.getList(a);
        int m = 1;
        int n =3;
        node = new ReverseLinkedList2().reverseBetween(node, m,n);
        System.out.println(node);
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {

        ListNode stackHead = null;
        ListNode stackTail = null;
        int position =1;
        ListNode node = head;
        ListNode previous = null;
        ListNode listHead = head;
        while (position<n+1)
        {
            if(position<m)
            {
                previous = node;
                node = node.next;

                position++;
            }
            else if(position>= m && position<=n)
            {
                ListNode item = node;
                node = node.next;
                position++;


                if(previous != null)
                {
                    previous.next = item.next;
                    item.next = stackHead;
                    if(stackHead ==null)
                    {
                        stackTail = item;
                    }
                    stackHead = item;
                }
                else
                {
                    item.next = stackHead;
                    if(stackHead ==null)
                    {
                        stackTail = item;
                    }
                    stackHead = item;
                    listHead = stackHead;
                }

            }
        }
        ListNode tail = node;
        if(previous != null)
        {
            previous.next = stackHead;
        }
        stackTail.next = tail;

        return listHead;
    }
}

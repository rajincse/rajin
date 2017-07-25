package algorithm;

/**
 * Created by rajin on 7/20/2017.
 */
public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }

    @Override
    public String toString() {
        if(next ==null)
        {
            return val+"-> NULL";
        }
        else
        {
            return val+"->"+next.toString();
        }
    }
    public static ListNode getList(int[] a)
    {
        ListNode head = null;
        for(int i=a.length-1;i>=0;i--)
        {
            ListNode node = new ListNode(a[i]);
            node.next = head;
            head = node;
        }

        return  head;
    }
}

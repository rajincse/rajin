package algorithm;

/**
 * Created by rajin on 12/11/2017.
 * 160. Intersection of Two Linked Lists
 */
public class IntersectionLL {
    public static void main(String[] args)
    {
        int[]a = new int[]{1,2,3};
        ListNode node = ListNode.getList(a);
        ListNode node1 = new ListNode(4);
        node1.next = new ListNode(5);
        node1.next.next = node;

        ListNode node2 = new ListNode(6);
        node2.next = node;

        ListNode result = new IntersectionLL().getIntersectionNode(node2, node1);
        System.out.println(result);
    }
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA ==null || headB ==null)
        {
            return null;
        }

        ListInfo infoA = getTailAndSize(headA);
        ListInfo infoB = getTailAndSize(headB);
        if(infoA.tail != infoB.tail)
        {
            return null;
        }

        ListNode longer = infoA.size> infoB.size? headA: headB;
        ListNode shorter = infoA.size> infoB.size? headB: headA;
        int diff = Math.abs(infoA.size- infoB.size);
        longer = getKthNode(longer, diff);

        while(longer != shorter)
        {
            longer = longer.next;
            shorter = shorter.next;
        }

        return longer;
    }
    public ListNode getKthNode(ListNode head, int k)
    {
        ListNode current = head;
        while (k>0 && current!=null)
        {
            current= current.next;
            k--;
        }

        return current;
    }
    public ListInfo getTailAndSize(ListNode head)
    {
        if(head ==null)
        {
            return new ListInfo(null, 0);
        }
        else
        {

            ListNode current = head;
            int size =1;
            while (current.next != null)
            {
                current = current.next;
                size++;
            }

            return new ListInfo(current,size);
        }
    }
    class ListInfo
    {
        ListNode tail;
        int size;
        public ListInfo(ListNode tail, int size)
        {
            this.tail = tail;
            this.size = size;
        }
    }
}

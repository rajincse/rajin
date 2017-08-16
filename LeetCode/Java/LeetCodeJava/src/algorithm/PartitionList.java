package algorithm;

/**
 * Created by rajin on 8/10/2017.
 */
public class PartitionList {
    public static void main(String[] args){
        int[] data = new int[]{1,2,3};
        int x = 0;

        ListNode input = ListNode.getList(data);
        ListNode result = new PartitionList().partition(null,x);
        System.out.println(result);
    }
    public ListNode partition(ListNode head, int x) {
        ListNode smallList = null;
        ListNode smallListHead = smallList;
        ListNode bigList = null;
        ListNode bigListHead = bigList;
        ListNode node = head;
        while (node != null)
        {
            if(node.val < x)
            {
                if(smallList == null)
                {
                    smallList = node;

                    smallListHead = smallList;
                }
                else
                {
                    smallList.next = node;
                    smallList = node;
                }
            }
            else
            {
                if(bigList == null)
                {
                    bigList = node;
                    bigListHead = node;
                }
                else
                {
                    bigList.next = node;
                    bigList = node;
                }
            }

            node = node.next;
        }
        if(bigList != null)
        {
            bigList.next = null;
        }


        if(smallList != null)
        {
            smallList.next = bigListHead;
            return smallListHead;
        }
        else
        {
            return bigListHead;
        }


    }
}

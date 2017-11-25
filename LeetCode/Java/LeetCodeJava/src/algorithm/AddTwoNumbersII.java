package algorithm;

/**
 * Created by rajin on 11/24/2017.
 * 445. Add Two Numbers II
 */
public class AddTwoNumbersII {
    public static  void main(String[] args){
        int[] a = new int[]{2,4,3};
        int[] b = new int[]{4,9,9};
        ListNode result = new AddTwoNumbersII().addTwoNumbers(ListNode.getList(a), ListNode.getList(b));
        System.out.println(result);
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int len1 = getSize(l1);
        int len2 = getSize(l2);
        ListNode big= len1>len2? l1:l2;
        ListNode small = len1>len2? l2:l1;
        int diff = Math.abs(len1-len2);
        ListNode align = big;
        ListNode result = null;
        ListNode resultNode = null;
        while(diff>0)
        {
            if(result ==null)
            {
                result = new ListNode(align.val);
                resultNode = result;
            }
            else
            {
                resultNode.next = new ListNode(align.val);
                resultNode = resultNode.next;
            }
            align = align.next;
            diff--;
        }
        ListNode smallIterator = small;

        while(align != null)
        {
            if(result ==null)
            {
                result = new ListNode(align.val+ smallIterator.val);
                resultNode = result;
            }
            else
            {
                resultNode.next = new ListNode(align.val+ smallIterator.val);
                resultNode = resultNode.next;
            }

            align= align.next;
            smallIterator= smallIterator.next;

        }
        int val = adjustCarryouts(result);
        if(val > 0)
        {
            ListNode extra = new ListNode(val);
            extra.next = result;
            result = extra;
        }

        return result;
    }

    public int adjustCarryouts(ListNode head){
        if(head.next ==null)
        {
            int val = head.val;
            head.val = val %10;
            return val /10;
        }
        else
        {
            int val = head.val + adjustCarryouts(head.next);
            head.val = val %10;
            return val /10;
        }
    }

    public int getSize(ListNode head){
        if(head ==null)
        {
            return 0;
        }
        else
        {
            return 1+getSize(head.next);
        }
    }
}

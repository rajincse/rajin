package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajin on 11/11/2017.
 */
public class SplitLinkedList {
    public static void main(String[] args){
        int[] a = new int[]{1, 2, 3,4,5,6,7,8,9,10,11};
        int k = 3;
        ListNode root = ListNode.getList(a);
        ListNode[] result = new SplitLinkedList().splitListToParts(null, k);
        for(ListNode list: result)
        {
            System.out.println(list);
        }
    }
    public ListNode[] splitListToParts(ListNode root, int k) {

        int n = getLength(root);
        List<ListNode> result = new ArrayList<ListNode>();
        result = getSplitList(root, result, n, k);
        ListNode[] arrayResult = new ListNode[result.size()];
        for(int i=0;i<arrayResult.length;i++)
        {
            arrayResult[i] = result.get(i);
        }
        return arrayResult;
    }
    public int getLength(ListNode root){
        ListNode node =root;
        int length =0;
        while (node != null)
        {
            node = node.next;
            length++;
        }

        return length;
    }
    public List<ListNode> getSplitList(ListNode root, List<ListNode> result, int n, int k){
        if(n ==0 )
        {
            if(k>0)
            {
                result.add(null);
                return getSplitList(root, result, n, k-1);
            }
            else
            {
                return result;
            }
        }
        else
        {
            int l = (int)Math.ceil(1.0 * n /k);
            ListNode node = root;
            ListNode tail = null;
            int total = l;
            while (total!= 0)
            {
                tail = node;
                node = node.next;
                total--;
            }
            tail.next = null;
            result.add(root);

            return getSplitList(node, result, n-l, k-1);
        }
    }
}

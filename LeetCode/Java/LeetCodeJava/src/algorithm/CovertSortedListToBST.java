package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajin on 9/15/2017.
 * 109. Convert Sorted List to Binary Search Tree
 */
public class CovertSortedListToBST {
    public static  void main(String[] args){
        int[] nums = new int[]{1,2,3,4,5,6,7};
        ListNode node = ListNode.getList(nums);
        TreeNode root = new CovertSortedListToBST().sortedListToBST(node);
        System.out.println(root.getPrintString());
    }

    public TreeNode sortedListToBST(ListNode head) {

        if(head ==null)
        {
            return null;
        }
        else
        {

            ArrayList<Integer> numList = new ArrayList<Integer>();
            ListNode node = head;
            while (node != null)
            {
                numList.add(node.val);
                node = node.next;
            }
            return getNode(numList, 0, numList.size()-1);
        }

    }
    public TreeNode getNode(ArrayList<Integer> numList, int start, int end){
        if(start> end)
        {
            return null;
        }
        else if(start== end)
        {
            return new TreeNode(numList.get(start));
        }
        else
        {
            int length = end-start+1;
            int midIndex =start+length/2;
            TreeNode root = new TreeNode(numList.get(midIndex));
            root.left = getNode(numList, start, midIndex-1);
            root.right = getNode(numList, midIndex+1, end);
            return root;
        }

    }
}

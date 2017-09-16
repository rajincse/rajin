package algorithm;

/**
 * Created by rajin on 9/15/2017.
 * 108. Convert Sorted Array to Binary Search Tree
 */
public class ConvertSortedArraytoBST {
    public static void main(String[] args){
        int[] nums = new int[]{1,2};

        TreeNode node = new ConvertSortedArraytoBST().sortedArrayToBST(nums);
        System.out.println(node.getPrintString());
    }
    public TreeNode sortedArrayToBST(int[] nums) {

        if(nums ==null || nums.length ==0)
        {
            return null;
        }

        return getNode(nums, 0, nums.length-1);
    }

    public TreeNode getNode(int[] nums, int start, int end){
        if(start> end)
        {
            return null;
        }
        else if(start== end)
        {
            return new TreeNode(nums[start]);
        }
        else
        {
            int length = end-start+1;
            int midIndex =start+length/2;
            TreeNode root = new TreeNode(nums[midIndex]);
            root.left = getNode(nums, start, midIndex-1);
            root.right = getNode(nums, midIndex+1, end);
            return root;
        }

    }
}

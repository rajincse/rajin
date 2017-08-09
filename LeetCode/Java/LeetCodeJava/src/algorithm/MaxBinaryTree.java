package algorithm;

/**
 * Created by rajin on 8/8/2017.
 */
public class MaxBinaryTree {
    public static void main(String[] args)
    {
        int[] nums = new int[]{3,4,2,1,6,0,5,1};
        TreeNode node = new MaxBinaryTree().constructMaximumBinaryTree(nums);
        System.out.println(node.getPrintString());
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {

        return construct(nums, 0, nums.length);
    }

    public TreeNode construct(int[] nums, int start, int end)
    {
        if(start ==end)
        {
            return null;
        }

        int maxVal = nums[start];
        int maxIndex =start;
        for(int i=start;i<end;i++)
        {
            if(nums[i] > maxVal)
            {
                maxVal = nums[i];
                maxIndex = i;
            }

        }

        TreeNode left = construct(nums, start, maxIndex);
        TreeNode right = construct(nums, maxIndex+1, end);
        TreeNode result = new TreeNode(maxVal);

        result.left = left;
        result.right = right;

        return result;
    }

}

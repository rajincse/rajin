package algorithm;

import sun.reflect.generics.tree.Tree;

/**
 * Created by rajin on 11/24/2017.
 * 298. Binary Tree Longest Consecutive Sequence
 */
public class LongestConsecutiveBT {
    public static void main(String[] args){
        String data = "[1,null,3,2,4,null,null,null,5]";
        TreeNode node = TreeNode.getTreeNode(data);
        System.out.println(new LongestConsecutiveBT().longestConsecutive(node));
    }
    public int longestConsecutive(TreeNode root) {
        if(root ==null)
        {
            return 0;
        }
        return longestConsecutive(root, 1);
    }

    public int longestConsecutive(TreeNode root, int current)
    {
        int maxVal =current;
        if(root.left != null)
        {
            maxVal = Math.max(maxVal, longestConsecutive(root.left, root.val+1 == root.left.val ? current+1: 1));
        }

        if(root.right != null)
        {
            maxVal = Math.max(maxVal, longestConsecutive(root.right, root.val+1 == root.right.val ? current+1: 1));
        }

        return maxVal;
    }
}

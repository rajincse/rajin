package algorithm;

/**
 * Created by rajin on 9/15/2017.
 * 111. Minimum Depth of Binary Tree
 */
public class MinDepthBT {
    public  static void main(String[] args)
    {
        String data = "[1,2,3,4,null,6,null,7]";
        TreeNode node = TreeNode.getTreeNode(data);

        System.out.println(node.getPrintString()+"=>"+new MinDepthBT().minDepth(node));
    }
    public int minDepth(TreeNode root) {
        if(root==null)
        {
            return 0;
        }
        else if(root.left ==null && root.right==null)
        {
            return 1;
        }
        else if(root.left !=null && root.right ==null)
        {
            return minDepth(root.left)+1;
        }
        else if(root.left ==null && root.right !=null)
        {
            return minDepth(root.right)+1;
        }
        else
        {
            return Math.min(minDepth(root.left), minDepth(root.right))+1;
        }
    }
}

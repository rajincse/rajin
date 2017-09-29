package algorithm;

/**
 * Created by rajin on 9/23/2017.
 * 112. Path Sum
 */
public class PathSum {
    public static void main(String[] args){
        String data ="[5,4,8,11,13,4,7,2,null,1]";
        TreeNode node = TreeNode.getTreeNode(data);
        int sum = 12;
        System.out.println(node+"=>\n"+new PathSum().hasPathSum(node, sum));
    }
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null)
        {
            return false;
        }
        else if(root.left ==null && root.right ==null){
            return root.val==sum;
        }
        else
        {
            return hasPathSum(root.left, sum-root.val) || hasPathSum(root.right, sum-root.val);
        }
    }
}

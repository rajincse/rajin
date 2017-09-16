package algorithm;

/**
 * Created by rajin on 9/4/2017.
 */
public class MaxDepthBT {
    public static void main(String[] args){

        String data = "[1,2,3,4,null,5,null,7]";
        TreeNode node = TreeNode.getTreeNode(data);
        System.out.println(node.getPrintString()+":"+new MaxDepthBT().maxDepth(node));
    }
    public int maxDepth(TreeNode root) {
        if(root ==null)
        {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right))+1;
    }
}

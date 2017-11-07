package algorithm;


import sun.reflect.generics.tree.Tree;

/**
 * Created by rajin on 11/6/2017.
 */
public class LowestCommonAncestorBT {
    public static void main(String[] args){
        String data = "[1,2,3,4,5]";
        TreeNode node1 = TreeNode.getTreeNode(data);
        TreeNode nodeR = new TreeNode(5);
        TreeNode node4 = new TreeNode(4);
        nodeR.left = node1;
        nodeR.right = node4;
        TreeNode root = new TreeNode(6);
        root.left = nodeR;

        TreeNode result = new LowestCommonAncestorBT().lowestCommonAncestor(root, node1, node4);
        System.out.println(result.getPrintString());
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root ==null){
            return null;
        }
        if(root.equals(p) || root.equals(q)){
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if(left != null &&  right != null){
            return root;
        }
        else if(left != null){
            return left;
        }
        else
        {
            return right;
        }
    }
}

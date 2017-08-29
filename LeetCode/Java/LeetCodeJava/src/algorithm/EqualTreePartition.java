package algorithm;

import sun.reflect.generics.tree.Tree;

/**
 * Created by rajin on 8/19/2017.
 */
class TreeNodeWithSum{
    TreeNode node;
    TreeNodeWithSum left;
    TreeNodeWithSum right;
    int sum ;
    int parentSum;
    TreeNodeWithSum(TreeNode node, int sum, int parentSum)
    {
        this.node = node;
        this.sum = sum;
        this.parentSum = parentSum;
    }

    @Override
    public String toString() {
        return "TWS{" +
                "node=" + node.val +
                ", sum=" + sum +
                ", parentSum=" + parentSum +
                '}';
    }
}
public class EqualTreePartition {
    public static  void main(String[] args)
    {
        String data = "[1,2,10,null,null,2,20]";
        TreeNode node = TreeNode.getTreeNode(data);
        System.out.println(new EqualTreePartition().checkEqualTree(node));
    }
    public boolean checkEqualTree(TreeNode root) {
        if(root.left ==null && root.right ==null)
        {
            return false;
        }

        TreeNodeWithSum rootTWS = getTreeWithSum(root);
        rootTWS.parentSum=0;

        populateParentSum(rootTWS);
        return checkIfExist(rootTWS);
    }
    public boolean checkIfExist(TreeNodeWithSum rootTWS)
    {
        if (rootTWS ==null)
        {
            return false;
        }
        return rootTWS.sum == rootTWS.parentSum || checkIfExist(rootTWS.left) || checkIfExist(rootTWS.right);
    }
    public void populateParentSum(TreeNodeWithSum rootTWS)
    {

        if(rootTWS.left != null)
        {
            rootTWS.left.parentSum = rootTWS.sum+rootTWS.parentSum - rootTWS.left.sum;
            populateParentSum(rootTWS.left);
        }
        if(rootTWS.right != null)
        {
            rootTWS.right.parentSum = rootTWS.sum+rootTWS.parentSum - rootTWS.right.sum;
            populateParentSum(rootTWS.right);
        }
    }
    public TreeNodeWithSum getTreeWithSum(TreeNode root)
    {
        TreeNodeWithSum left = null;
        int leftSum=0;
        TreeNodeWithSum right = null;
        int rightSum =0;
        if(root.left != null)
        {
            left = getTreeWithSum(root.left);
            leftSum = left.sum;
        }
        if(root.right != null)
        {
            right = getTreeWithSum(root.right);
            rightSum = right.sum;
        }
        TreeNodeWithSum rootNode = new TreeNodeWithSum(root,root.val+leftSum+rightSum,0);
        rootNode.left = left;
        rootNode.right = right;
        return rootNode;
    }
}

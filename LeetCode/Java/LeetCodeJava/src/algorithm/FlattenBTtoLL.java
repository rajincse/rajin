package algorithm;



/**
 * Created by rajin on 9/28/2017.
 * 114. Flatten Binary Tree to Linked List
 */
public class FlattenBTtoLL {
    public static  void main(String[] args){
        String data ="[1,2,5,3,4,null,6]";
        TreeNode node = TreeNode.getTreeNode(data);
        new FlattenBTtoLL().flatten(node);
        System.out.println(node.getPrintString());
    }

    public void flatten(TreeNode root) {
        getTail(root);
    }

    public TreeNode getTail(TreeNode root)
    {
        if(root == null)
        {
            return null;
        }

        TreeNode tail = root;
        TreeNode leftTree = root.left;
        TreeNode rightTree = root.right;
        if(leftTree != null)
        {
            tail = getTail(leftTree);
            root.left = null;
            root.right = leftTree;
        }
        if (rightTree != null)
        {
            tail.right = rightTree;
            tail = getTail(rightTree);
        }

        return tail;
    }
}

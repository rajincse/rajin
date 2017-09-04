package algorithm;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;

/**
 * Created by rajin on 8/31/2017.
 */
public class SymmetricTree {
    public static void main(String[] args){
        String data1 = "[1,2,2,3,4,4,3]";
        String data2 = "[2,3,3,4,5,5]";

        TreeNode node = TreeNode.getTreeNode(data2);
        System.out.println(node.getPrintString()+":"+new SymmetricTree().isSymmetric(node));
    }
    public boolean isSymmetric(TreeNode root) {
        if(root ==null){
            return true;
        }

        if(root.left ==null && root.right ==null){
            return true;
        }
        else if(root.left == null || root.right == null){
            return false;
        }
        else
        {
            return isMirrorIterative(root.left, root.right);
        }
    }

    public boolean isMirror(TreeNode node1, TreeNode node2){
        if(node1 ==null && node2 ==null){
            return true;
        }
        else if(node1 == null || node2 == null)
        {
            return false;
        }
        else if(node1.val != node2.val){
            return false;
        }
        else{
            return isMirror(node1.left, node2.right) && isMirror(node1.right, node2.left);
        }
    }
    public boolean isMirrorIterative(TreeNode node1, TreeNode node2){
        ArrayList<TreeNode> stack1 = new ArrayList<TreeNode>();
        ArrayList<TreeNode> stack2 = new ArrayList<TreeNode>();

        stack1.add(0,node1);
        stack2.add(0,node2);

        while (!stack1.isEmpty() && !stack2.isEmpty())
        {
            TreeNode n1 = stack1.remove(0);
            TreeNode n2 = stack2.remove(0);
            if(n1 ==null && n2 ==null){
                continue;
            }
            else if(n1 == null || n2 == null)
            {
                return false;
            }
            else if(n1.val != n2.val){
                return false;
            }
            else{
                stack1.add(0,n1.left);
                stack2.add(0,n2.right);

                stack1.add(0,n1.right);
                stack2.add(0,n2.left);
            }

        }
        return stack1.isEmpty() && stack2.isEmpty();
    }
}

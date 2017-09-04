package algorithm;

/**
 * Created by rajin on 8/31/2017.
 * 100. Same Tree
 * Cracking the coding problem 4.10 , pp. 267 isMatchTree
 */
public class SameTree {
    public static void main(String[] args){
        String data1 = "[1,2,3,4,5]";
        String data2 ="[1,2,3,4]";
        TreeNode node1 = TreeNode.getTreeNode(data1);
        TreeNode node2 = TreeNode.getTreeNode(data1);
        System.out.println(new SameTree().isSameTree(node1,node2));
    }
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null && q ==null){
            return true;
        }
        else if(p==null || q== null){
            return false;
        }
        else if(p.val != q.val){
            return false;
        }
        else
        {
            return isSameTree(p.left, q.left) && isSameTree(p.right,q.right);
        }
    }
}

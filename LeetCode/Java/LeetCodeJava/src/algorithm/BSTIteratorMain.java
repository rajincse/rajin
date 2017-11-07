package algorithm;

import java.util.Stack;

/**
 * Created by rajin on 11/5/2017.
 */
class BSTIterator {

    Stack<TreeNode> previousNodes;
    TreeNode currentNode;
    public BSTIterator(TreeNode root) {

        this.currentNode = root;
        previousNodes = new Stack<TreeNode>();
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {

        return this.currentNode != null;
    }

    /** @return the next smallest number */
    public int next() {
        int smallest = getSmallest(this.currentNode);
        return smallest;
    }

    private int getSmallest(TreeNode node){
        if(node.left != null)
        {
            this.currentNode = node.left;
            node.left = null;
            previousNodes.push(node);

            int smallest = getSmallest(this.currentNode);
            return smallest;
        }
        else
        {
            int smallest = this.currentNode.val;
            this.currentNode = node.right;
            if(this.currentNode == null && !previousNodes.isEmpty()) {
                this.currentNode = previousNodes.pop();
            }
            return smallest;
        }
    }
}
public class BSTIteratorMain {
    public  static void main(String[] args)
    {
        String data = "[5,2,7,1,3]";
        TreeNode node = TreeNode.getTreeNode(data);
        System.out.println(node.getPrintString());
        BSTIterator it  = new BSTIterator(node);
        while (it.hasNext())
        {
            System.out.print(it.next()+", ");
        }
        System.out.println();
    }


}

package algorithm;


import java.util.ArrayList;

/**
 * Created by rajin on 8/19/2017.
 */
class BinaryTreeWidthIndex{
    TreeNode node;
    int index;
    int depth;
    public BinaryTreeWidthIndex(TreeNode node, int index, int depth)
    {
        this.node = node;
        this.index = index;
        this.depth = depth;
    }

    @Override
    public String toString() {
        return "{" +
                "node=" + node.val +
                ", index=" + index +
                ", depth=" + depth +
                '}';
    }
}
public class MaxWidthBinaryTree {
    public  static void main(String[] args)
    {
        String data ="[1,3,2,5,null,null,9,6,null,null,7]";
        TreeNode node = TreeNode.getTreeNode(data);
        int width = new MaxWidthBinaryTree().widthOfBinaryTree(node);
        System.out.println(width);
    }
    public int widthOfBinaryTree(TreeNode root) {

        ArrayList<BinaryTreeWidthIndex> queue = new ArrayList<BinaryTreeWidthIndex>();
        queue.add(new BinaryTreeWidthIndex(root,0,0));
        int maxWidth = 1;
        int currentDepth = -1;
        int start =0;
        int end =0;
        while (!queue.isEmpty())
        {
            BinaryTreeWidthIndex diNode = queue.remove(0);
            if(diNode.node.left != null)
            {
                queue.add(new BinaryTreeWidthIndex(diNode.node.left,diNode.index*2,diNode.depth+1));
            }

            if(diNode.node.right != null)
            {
                queue.add(new BinaryTreeWidthIndex(diNode.node.right,diNode.index*2+1,diNode.depth+1));
            }
            if(diNode.depth>currentDepth)
            {
                start = diNode.index;
                end = diNode.index;
                currentDepth = diNode.depth;
            }
            if(diNode.depth == currentDepth)
            {
                end = diNode.index;
            }
            int length =end-start+1;
            if( length> maxWidth)
            {
                maxWidth = length;
            }
        }

        return maxWidth;
    }
}

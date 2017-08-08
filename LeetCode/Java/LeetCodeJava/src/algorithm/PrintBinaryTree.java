package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajin on 8/5/2017.
 */
class TreeNodeWithDepth{
    int depth;
    int position;
    TreeNode node;
    public TreeNodeWithDepth(TreeNode node, int depth, int position)
    {
        this.node = node;
        this.depth = depth;
        this.position = position;
    }

}
public class PrintBinaryTree {
    public static void main(String[] args)
    {
//        String tree ="[1,2,5,3,null,null,null,4]";
//        String tree ="[1,2,3,4,5,6,7]";
        String tree ="[5,3,6,2,4,null,7]";
        TreeNode node = TreeNode.getTreeNode(tree);
        PrintBinaryTree obj = new PrintBinaryTree();
        List<List<String>> print = obj.printTree(node);
        for(List<String> list: print)
        {
            System.out.println(list);
        }
    }
    public List<List<String>> printTree(TreeNode root) {

        List<List<String>> result = new ArrayList<List<String>>();

        ArrayList<TreeNodeWithDepth> queue = new ArrayList<TreeNodeWithDepth>();

        int treeHeight = getTreeHeight(root); //m
        int width = (1<<(treeHeight+1))-1;//n

        System.out.println("Height:"+treeHeight);

        int currentDepth =0;
        ArrayList<String> currentArray = new ArrayList<String>();
        for(int i=0;i<width;i++)
        {
            currentArray.add("");
        }
        int currentNodeCount=0;
        TreeNodeWithDepth node = new TreeNodeWithDepth(root, 0, 0);

        queue.add(node);
        while (!queue.isEmpty())
        {

            TreeNodeWithDepth nodeDepth = queue.remove(0);
            System.out.println(nodeDepth.node+", depth:"+nodeDepth.depth+", pos:"+nodeDepth.position+" h:"+(treeHeight-nodeDepth.depth));
            if(nodeDepth.node.left != null)
            {
                queue.add(new TreeNodeWithDepth(nodeDepth.node.left, nodeDepth.depth+1,2* nodeDepth.position));
            }


            if(nodeDepth.node.right != null)
            {
                queue.add(new TreeNodeWithDepth(nodeDepth.node.right, nodeDepth.depth+1, 2* nodeDepth.position+1));
            }


            if(nodeDepth.depth > currentDepth)
            {
                result.add(currentArray);
                currentArray = new ArrayList<String>();
                for(int i=0;i<width;i++)
                {
                    currentArray.add("");
                }
                currentDepth= nodeDepth.depth;
            }
            int index = (nodeDepth.position*2+1) * (1<<(treeHeight-nodeDepth.depth))-1;
            currentArray.set(index, nodeDepth.node.val+"");


        }
        result.add(currentArray);

        return result;
    }



    public int getTreeHeight(TreeNode root)
    {
        if(root == null)
        {
            return -1;
        }

        int leftHeight =getTreeHeight(root.left);
        int rightHeight = getTreeHeight(root.right);

        return Math.max(leftHeight, rightHeight)+1;
    }
}

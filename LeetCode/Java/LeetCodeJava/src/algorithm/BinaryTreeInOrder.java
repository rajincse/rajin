package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajin on 8/28/2017.
 * 94. Binary Tree Inorder Traversal
 */

public class BinaryTreeInOrder {
    class TreeNodeVisited{
        TreeNode node;
        boolean visited;
        public TreeNodeVisited(TreeNode node)
        {
            this.node = node;
            this.visited = false;
        }

        public boolean isVisited() {
            return visited;
        }

        public void setVisited(boolean visited) {
            this.visited = visited;
        }

        @Override
        public String toString() {
            return node.val+"";
        }
    }
    public static void main(String[] args)
    {
        String data ="[1,4,2,7,null,3,5]";
        TreeNode node = TreeNode.getTreeNode(data);
        System.out.println(new BinaryTreeInOrder().inorderTraversal(node));
    }
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if(root ==null)
        {
            return result;
        }

        ArrayList<TreeNodeVisited> stack = new ArrayList<TreeNodeVisited>();
        TreeNodeVisited visitedNode = new TreeNodeVisited(root);
        stack.add(visitedNode);

        while (!stack.isEmpty())
        {
            TreeNodeVisited n = stack.remove(0); //pop
            if(n.isVisited())
            {
                result.add(n.node.val);
            }
            else
            {
                if(n.node.right != null)
                {
                    stack.add(0,new TreeNodeVisited(n.node.right));
                }

                n.setVisited(true);
                stack.add(0,n);

                if(n.node.left != null)
                {
                    stack.add(0,new TreeNodeVisited(n.node.left));
                }
            }

            System.out.println(stack);
        }
        return result;
    }

    public List<Integer> inorderTraversalRecursive(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        populateRecursively(root,result);

        return result;
    }

    public void populateRecursively(TreeNode root, List<Integer> result)
    {

        if(root ==null)
        {
            return;
        }
        System.out.println(root.val);
        populateRecursively(root.left,result);
        result.add(root.val);
        populateRecursively(root.right,result);
    }


}

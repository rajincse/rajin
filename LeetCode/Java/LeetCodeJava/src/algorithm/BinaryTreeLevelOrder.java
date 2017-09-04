package algorithm;

import java.util.*;

/**
 * Created by rajin on 9/1/2017.
 * 102. Binary Tree Level Order Traversal
 */
public class BinaryTreeLevelOrder {
    class TreeLevel{
        TreeNode node;
        int level ;

        public TreeLevel(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }
    }
    public static  void main(String[] args){
        String data ="[3,9,20,null,null,15,7]";
        TreeNode node = TreeNode.getTreeNode(data);
        List<List<Integer>> result = new BinaryTreeLevelOrder().levelOrder(node);
        System.out.println(Utility.<Integer>getListPrintString(result));
    }
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<Integer> currentList = new ArrayList<Integer>();
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(root ==null){
            return result;
        }

        TreeLevel current = new TreeLevel(root,0);
        Queue<TreeLevel> queue = new LinkedList<TreeLevel>();
        queue.add(current);
        int currentLevel =0;


        while (!queue.isEmpty()){
            current = queue.poll();
            if(current.level > currentLevel){
                result.add(currentList);
                currentLevel = current.level;
                currentList = new ArrayList<Integer>();
            }
            currentList.add(current.node.val);
            if(current.node.left != null)
            {
                queue.add(new TreeLevel(current.node.left, current.level+1));
            }
            if(current.node.right != null)
            {
                queue.add(new TreeLevel(current.node.right, current.level+1));
            }
        }
        result.add(currentList);

        return result;
    }
}

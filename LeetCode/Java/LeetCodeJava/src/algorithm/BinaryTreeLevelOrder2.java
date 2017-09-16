package algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by rajin on 9/15/2017.
 * 107. Binary Tree Level Order Traversal II
 */
public class BinaryTreeLevelOrder2 {
    class TreeLevel{
        TreeNode node;
        int level ;

        public TreeLevel(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }
    }
    public static  void main(String[] args) {
        String data = "[3,9,20,null,null,15,7]";
        TreeNode node = TreeNode.getTreeNode(data);
        List<List<Integer>> result = new BinaryTreeLevelOrder2().levelOrderBottom(node);
        System.out.println(Utility.<Integer>getListPrintString(result));
    }
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<Integer> currentList = new ArrayList<Integer>();
        LinkedList<List<Integer>> result = new LinkedList<List<Integer>>();
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
                result.addFirst(currentList);
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
        result.addFirst(currentList);

        return result;
    }
}

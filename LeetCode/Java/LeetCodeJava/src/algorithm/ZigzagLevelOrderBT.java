package algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by rajin on 9/4/2017.
 */
public class ZigzagLevelOrderBT {
    class TreeLevel{
        TreeNode node;
        int level ;

        public TreeLevel(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }
    }
    public static  void main(String[] args){
        String data ="[3,9,20,null,null,15,7, 1,2]";
        TreeNode node = TreeNode.getTreeNode(data);
        List<List<Integer>> result = new ZigzagLevelOrderBT().zigzagLevelOrder(node);
        System.out.println(Utility.<Integer>getListPrintString(result));
    }
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        LinkedList<Integer> currentList = new LinkedList<Integer>();
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(root ==null){
            return result;
        }

        ZigzagLevelOrderBT.TreeLevel current = new ZigzagLevelOrderBT.TreeLevel(root,0);
        Queue<ZigzagLevelOrderBT.TreeLevel> queue = new LinkedList<ZigzagLevelOrderBT.TreeLevel>();
        queue.add(current);
        int currentLevel =0;


        while (!queue.isEmpty()){
            current = queue.poll();
            if(current.level > currentLevel){
                result.add(currentList);
                currentLevel = current.level;
                currentList = new LinkedList<Integer>();
            }
            if(currentLevel %2 ==0)
            {
                currentList.add(current.node.val);
            }
            else
            {
                currentList.addFirst(current.node.val);
            }

            if(current.node.left != null)
            {
                queue.add(new ZigzagLevelOrderBT.TreeLevel(current.node.left, current.level+1));
            }
            if(current.node.right != null)
            {
                queue.add(new ZigzagLevelOrderBT.TreeLevel(current.node.right, current.level+1));
            }
        }
        result.add(currentList);

        return result;
    }
}

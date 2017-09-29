package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajin on 9/28/2017.
 * 113. Path Sum II
 */
public class PathSum2 {
    public static void main(String[] args)
    {
        String data ="[5,4,8,11,null,13,4,7,2,null,null,5,1]";
        TreeNode node = TreeNode.getTreeNode(data);
        int sum =22;

        List<List<Integer>> result = new PathSum2().pathSum(node, sum);
        for(List<Integer> res: result)
        {
            System.out.println(res);
        }
    }
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> current = new ArrayList<Integer>();

        dfs(root, sum, result, current);

        return result;
    }

    public void dfs(TreeNode node, int sum, List<List<Integer>> result, List<Integer> current)
    {
        if(node == null)
        {
            return;
        }
        else if(node.left == null && node.right == null) // detect leaf
        {
            if(sum == node.val)
            {
                current.add(node.val);
                result.add(new ArrayList<Integer>(current));
                current.remove(current.size()-1);
            }
            else {
                return;
            }
        }
        else
        {
            current.add(node.val);
            dfs(node.left, sum - node.val, result, current);
            dfs(node.right, sum - node.val, result, current);
            current.remove(current.size()-1);
        }

    }

}

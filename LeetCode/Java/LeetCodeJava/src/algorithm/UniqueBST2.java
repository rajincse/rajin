package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajin on 8/30/2017.
 * 95. Unique Binary Search Trees II
 */
public class UniqueBST2 {
    public static void main(String[] args)
    {
        int n = 7;
        List<TreeNode> result = new UniqueBST2().generateTrees(n);
        for(TreeNode t: result)
        {
            System.out.println(t.getPrintString());
        }
    }

    public List<TreeNode> generateTrees(int n) {
        List<TreeNode>[][] mem= (List<TreeNode>[][])new List[n][n];
        List<TreeNode> result = getTrees(1,n,mem);

        return result;
    }
    public List<TreeNode> getTrees(int start, int end, List<TreeNode>[][] mem)
    {
        List<TreeNode> result = new ArrayList<TreeNode>();
        if(start > end)
        {
            return result;
        }
        else if(mem[start-1][end-1] != null)
        {
            return mem[start-1][end-1];
        }
        else if(start == end)
        {
            TreeNode node = new TreeNode(start);
            result.add(node);
            mem[start-1][end-1] = result;
        }
        else
        {
            for(int i = start;i<=end;i++)
            {

                List<TreeNode> childLeftSubtrees = getTrees(start,i-1,mem);
                List<TreeNode> childRightSubtrees = getTrees(i+1,end,mem);
                if(childLeftSubtrees.isEmpty() && childRightSubtrees.isEmpty())
                {
                    TreeNode node = new TreeNode(i);
                    result.add(node);
                }
                else if(!childLeftSubtrees.isEmpty() && childRightSubtrees.isEmpty())
                {
                    for(TreeNode l: childLeftSubtrees)
                    {
                        TreeNode node = new TreeNode(i);
                        node.left = l;
                        result.add(node);
                    }
                }
                else if(childLeftSubtrees.isEmpty() && !childRightSubtrees.isEmpty())
                {
                    for(TreeNode r: childRightSubtrees)
                    {
                        TreeNode node = new TreeNode(i);
                        node.right = r;
                        result.add(node);
                    }
                }
                else
                {
                    for(TreeNode l: childLeftSubtrees)
                    {
                        for(TreeNode r: childRightSubtrees)
                        {
                            TreeNode node = new TreeNode(i);
                            node.left = l;
                            node.right = r;
                            result.add(node);
                        }
                    }

                }

            }
        }
        mem[start-1][end-1] = result;

        return mem[start-1][end-1];
    }

}

package algorithm;

/**
 * Created by rajin on 10/11/2017.
 * 129. Sum Root to Leaf Numbers
 */
public class SumRootToLeaf {
    public static void main(String[] args){
        String data ="[1,2,3,4,5]";
        TreeNode node = TreeNode.getTreeNode(data);
        System.out.println(node.getPrintString()+"=>"+new SumRootToLeaf().sumNumbers(node));
    }
    public int sumNumbers(TreeNode root) {
        if(root ==null)
        {
            return 0;
        }
        return getSum(0, root);
    }

    public int getSum(int rootVal, TreeNode node)
    {
        if(node.left ==null && node.right ==null)
        {
            return rootVal*10+node.val;
        }
        else
        {
            int val = rootVal *10+node.val;
            int left =0;
            int right =0;
            if(node.left != null)
            {
                left = getSum(val, node.left);
            }
            if(node.right != null)
            {
                right = getSum(val, node.right);
            }

            return left+right;
        }
    }

}


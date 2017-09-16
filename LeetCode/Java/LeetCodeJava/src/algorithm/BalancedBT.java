package algorithm;

/**
 * Created by rajin on 9/15/2017.
 * 110. Balanced Binary Tree
 */
public class BalancedBT {
    class Tuple{
        int intVal;
        boolean boolVal;

        public Tuple(int intVal, boolean boolVal) {
            this.intVal = intVal;
            this.boolVal = boolVal;
        }
    }
    public  static  void main(String[] args)
    {
        String data = "[1]";
        TreeNode node = TreeNode.getTreeNode(data);
        System.out.println(node.getPrintString()+"=>"+new BalancedBT().isBalanced(node));
    }
    public boolean isBalanced(TreeNode root) {
        if(root ==null)
        {
            return true;
        }
        else if(isBalanced(root.left) && isBalanced(root.right))
        {
            Tuple tuple = getBalanceAndHeight(root);
            return tuple.boolVal;
        }
        else
        {
            return false;
        }
    }

    public Tuple getBalanceAndHeight(TreeNode root)
    {
        if(root ==null)
        {
            return new Tuple(-1,true);
        }
        else
        {
            Tuple left = getBalanceAndHeight(root.left);
            Tuple right = getBalanceAndHeight(root.right);
            if(left.boolVal && right.boolVal)
            {
                int height = Math.max(left.intVal, right.intVal)+1;
                int diff = Math.abs(left.intVal-right.intVal);
                return new Tuple(height, diff<2);
            }
            else
            {
                return new Tuple(-2,false);
            }
        }
    }


    public int getHeight(TreeNode root)
    {
        if (root ==null)
        {
            return -1;
        }
        else
        {
            return Math.max(getHeight(root.left), getHeight(root.right))+1;
        }
    }

}

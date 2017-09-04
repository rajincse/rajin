package algorithm;

/**
 * Created by rajin on 8/30/2017.
 */
public class ValidateBST {
    public static void main(String[] args){
        String data="[-2147483648,null,2147483647]";
        TreeNode node = TreeNode.getTreeNode(data);
        System.out.println(node.getPrintString()+"="+new ValidateBST().isValidBST(node));
    }
    public boolean isValidBST(TreeNode root) {

        if(root ==null)
        {
            return true;
        }
        if(root.left == null && root.right ==null){
            return true;
        }
        else if(root.left != null && root.right==null)
        {
            int leftTreeMaxVal = getMaxVal(root.left);
            return root.val> leftTreeMaxVal  && isValidBST(root.left);
        }
        else if(root.left == null && root.right!=null)
        {
            int rightTreeMinVal = getMinVal(root.right);
            return root.val<rightTreeMinVal  && isValidBST(root.right);
        }
        else
        {
            int leftTreeMaxVal = getMaxVal(root.left);
            int rightTreeMinVal = getMinVal(root.right);


            return root.val> leftTreeMaxVal && root.val < rightTreeMinVal && isValidBST(root.left) && isValidBST(root.right);
        }
    }

    public int getMinVal(TreeNode root){
        if(root==null){
            return Integer.MAX_VALUE;
        }

        int minVal = Math.min(getMinVal(root.left), getMinVal(root.right));
        minVal = Math.min(minVal, root.val);
        return minVal;
    }
    public int getMaxVal(TreeNode root){
        if(root==null){

            return Integer.MIN_VALUE;
        }
        int maxVal = Math.max(getMaxVal(root.left), getMaxVal(root.right));
        maxVal = Math.max(maxVal, root.val);
        return maxVal;
    }
}

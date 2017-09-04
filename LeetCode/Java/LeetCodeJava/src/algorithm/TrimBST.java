package algorithm;

/**
 * Created by rajin on 9/2/2017.
 */
public class TrimBST {
    public static void main(String[] args){
        String data ="[5,0,8,null,1,6,null,null,2,null,7]";
        int L =6;
        int R = 7;
        TreeNode node = TreeNode.getTreeNode(data);
        System.out.println(node.getPrintString()+"=>\n"+new TrimBST().trimBST(node, L, R).getPrintString());
    }
    public TreeNode trimBST(TreeNode root, int L, int R) {

        if(root ==null)
        {
            return null;
        }
        if(root.val<L){
            return trimBST(root.right, L, R);
        }
        else if(root.val > R)
        {
            return trimBST(root.left, L, R);
        }
        else
        {
            root.left = trimBST(root.left, L, R);
            root.right = trimBST(root.right, L, R);
            return root;
        }
    }
}

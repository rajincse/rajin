package algorithm;

/**
 * Created by rajin on 11/24/2017.
 * 235. Lowest Common Ancestor of a Binary Search Tree
 */
public class LowestCommonAncestorBST {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root ==null || root.val == p.val || root.val ==q.val){
            return root;
        }
        else
        {
            if(root.val > p.val && root.val>q.val)
            {
                return lowestCommonAncestor(root.left, p, q);
            }
            else if(root.val<p.val && root.val <q.val)
            {
                return lowestCommonAncestor(root.right,p, q);
            }
            else
            {
                return root;
            }
        }
    }
}

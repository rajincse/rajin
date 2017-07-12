package algorithm;

/**
 * Created by rajin on 6/10/2017.
 */
public class MergeTreeNodes {

    public static void main(String[] args){
        TreeNode nd1 = new TreeNode(1);
        TreeNode nd2 = new TreeNode(2);
        TreeNode nd3 = new TreeNode(3);
        TreeNode nd4 = new TreeNode(4);
        TreeNode nd5 = new TreeNode(5);

        nd1.right = nd2;

        nd3.left = nd4;
        nd3.right = nd5;

        TreeNode result = new MergeTreeNodes().mergeTrees(nd1, nd3);

        System.out.println(result);
    }
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {

        TreeNode result = null;
        result = populate(t1, t2);

        return result;
    }

    public TreeNode populate( TreeNode t1, TreeNode t2)
    {

        if(t1 ==null && t2 == null){
            return null;
        }

        TreeNode r = null;
        int val =0;
        TreeNode l1 = null;
        TreeNode l2 = null;
        TreeNode r1 = null;
        TreeNode r2 = null;

        if(t1 != null){
            val += t1.val;
            l1 = t1.left;
            r1 = t1.right;
        }

        if(t2 != null){
            val += t2.val;
            l2 = t2.left;
            r2 = t2.right;
        }


        r = new TreeNode(val);
        if(l1 != null || l2 != null){
            r.left = populate( l1, l2);
        }

        if(r1 != null || r2  != null){
            r.right = populate( r1, r2);
        }
        return r;
    }



}

package algorithm;

/**
 * Created by rajin on 12/10/2017.
 * 742. Closest Leaf in a Binary Tree
 */
public class ClosestLeafBT {
    public static void main(String[] args)
    {
        String data = "[1,2,3,4,null,null,null,5,null,6]";
        TreeNode node = TreeNode.getTreeNode(data);
        int k = 2;
        System.out.println(new ClosestLeafBT().findClosestLeaf(node, k));
    }

    public int findClosestLeaf(TreeNode root, int k) {

        Info info = getLeaf(root, k);

        return info.targetLeaf;
    }

    public Info getLeaf(TreeNode root, int target)
    {
        if(root==null)
        {
            return null;
        }
        else if(root.left ==null && root.right ==null) // leaf
        {
            Info info = new Info();
            info.leafVal = root.val;
            info.leafDistance = 0;
            info.gotTarget = root.val == target;
            info.targetDistance = info.gotTarget? 0: -1;
            info.targetLeaf = info.gotTarget? root.val:-1;
            info.targetLeafDistance= info.gotTarget? 0:-1;

            return info;
        }
        else{
            Info left = getLeaf(root.left, target);
            Info right = getLeaf(root.right, target);


            if(left != null && right != null)
            {
                Info info = new Info();
                if(left.leafDistance < right.leafDistance)
                {
                    info.leafVal = left.leafVal;
                    info.leafDistance = left.leafDistance+1;
                }
                else
                {
                    info.leafVal = right.leafVal;
                    info.leafDistance = right.leafDistance+1;
                }

                if(left.gotTarget || right.gotTarget)
                {
                    info.gotTarget = true;
                    info.targetLeaf = left.gotTarget? left.targetLeaf:right.targetLeaf;
                    info.targetDistance = left.gotTarget? left.targetDistance+1:right.targetDistance+1;
                    info.targetLeafDistance = left.gotTarget? left.targetLeafDistance:right.targetLeafDistance;
                    if(info.targetLeafDistance> info.leafDistance+info.targetDistance)
                    {
                        info.targetLeaf = info.leafVal;
                        info.targetLeafDistance = info.leafDistance+info.targetDistance;
                    }
                }
                else
                {
                    info.gotTarget = root.val == target;
                    info.targetDistance = info.gotTarget? 0: -1;
                    info.targetLeaf = info.gotTarget? info.leafVal:-1;
                    info.targetLeafDistance = info.gotTarget? info.leafDistance: -1;
                }

                return info;

            }
            else
            {
                Info info = left !=null?   left: right;
                info.leafDistance++;
                if(info.gotTarget)
                {
                    info.targetDistance++;
                }
                else
                {
                    info.gotTarget = root.val == target;
                    info.targetDistance = info.gotTarget? 0: -1;
                    info.targetLeaf = info.gotTarget? info.leafVal:-1;
                    info.targetLeafDistance= info.gotTarget? info.leafDistance:-1;
                }

                return info;
            }

        }
    }
    class Info
    {
        int leafVal;
        int leafDistance;
        boolean gotTarget;
        int targetDistance;
        int targetLeaf;
        int targetLeafDistance;

        @Override
        public String toString() {
            return "{" +
                    "leafVal=" + leafVal +
                    ", leafDistance=" + leafDistance +
                    ", gotTarget=" + gotTarget +
                    ", targetDistance=" + targetDistance +
                    ", targetLeaf=" + targetLeaf +
                    ", targetLeafDistance=" + targetLeafDistance +
                    '}';
        }
    }
}

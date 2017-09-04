package algorithm;

/**
 * Created by rajin on 9/2/2017.
 */
public class SecondMinNodeBinaryTree {
    public static void main(String[] args){
        String data ="[2,2,2]";
        TreeNode node = TreeNode.getTreeNode(data);
        System.out.println(node.getPrintString()+":"+new SecondMinNodeBinaryTree().findSecondMinimumValue(node));
    }
    public int findSecondMinimumValue(TreeNode root) {
        if(root ==null) return -1;
        int min= getMinVal(root, -1);
        int secondMin = getMinVal(root, min);

        return secondMin;

    }
    public int getMinVal(TreeNode root, int minimumVal){
        if(root.left==null && root.right ==null){
            if(root.val > minimumVal)
            {
                return root.val;
            }
            else
            {
                return -1;
            }

        }
        else
        {
            int minValLeft = getMinVal(root.left, minimumVal);
            int minValRight = getMinVal(root.right, minimumVal);

            if(minValLeft <0  && minValRight < 0)
            {
                if(root.val > minimumVal)
                {
                    return root.val;
                }
                else
                {
                    return -1;
                }

            }
            else if(minValLeft > -1 && minValRight < 0)
            {
                int min = root.val > minimumVal? Math.min(root.val, minValLeft): minValLeft;
                if(min > minimumVal)
                {
                    return min;
                }
                else
                {
                    return -1;
                }
            }
            else if(minValLeft <0 && minValRight > -1)
            {
                int min = root.val > minimumVal? Math.min(root.val, minValRight): minValRight;
                if(min > minimumVal)
                {
                    return min;
                }
                else
                {
                    return -1;
                }

            }
            else
            {
                int min = Math.min( minValLeft, minValRight);
                min = root.val> minimumVal?Math.min(root.val, min): min;
                if(min > minimumVal)
                {
                    return min;
                }
                else
                {
                    return -1;
                }

            }

        }
    }
}
